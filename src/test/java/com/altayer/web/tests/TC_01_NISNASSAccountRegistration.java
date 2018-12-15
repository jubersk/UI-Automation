package com.altayer.web.tests;

import org.testng.annotations.Test;
import com.altayer.web.utilitiy.BaseClass;
import com.altayer.web.utilitiy.ConstantOnlineReg;
import com.altayer.web.utilitiy.ExcelUtils;
import com.altayer.web.utilitiy.Log;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;



public class TC_01_NISNASSAccountRegistration extends BaseClass{
	public static RemoteWebDriver driver = null;
	private static String rootPath = "";
	public static WebDriverWait wait;
	public static Properties prop = null; 
	private String sTestCaseName;
	private int iTestCaseRow;
	public static boolean bResult;
	private static String navigateURL = "";

	@BeforeSuite
	public void extentReport(){
		extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/ExtentReportNew.html", true);
	}

	@AfterSuite
	public void flushExtentReport(){
		extent.flush();
	}


	@BeforeClass
	public void init() throws Exception {


		if(prop==null){
			prop = new Properties();
			try{
				FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"\\ElementLocators.properties");
				prop.load(fs);

			}catch (Exception e){
				e.printStackTrace();
			}

		}
		bResult = true;


		// Configuring Log4j logs
		DOMConfigurator.configure("log4j.xml");
		// The below method will refine your test case name, exactly the name use have used
		sTestCaseName = getTestCaseName(this.toString());
		System.out.println("Test Case Name:" + sTestCaseName);
		// Start printing the logs and printing the Test Case name
		Log.startTestCase("Test Case Name:" + sTestCaseName);

		// Setting up the Test Data Excel file using Constants variables
		ExcelUtils.setExcelFile(ConstantOnlineReg.Path_TestData + ConstantOnlineReg.File_TestData,"TestData");
		// Fetching the Test Case row number from the Test Data Sheet
		// This row number will be feed to so many functions, to get the relevant data from the Test Data sheet 
		iTestCaseRow = ExcelUtils.getRowContains(sTestCaseName,ConstantOnlineReg.Col_TestCaseName);
		System.out.println("TestCase Row Number" +iTestCaseRow);
		navigateURL = ExcelUtils.getCellData(iTestCaseRow, ConstantOnlineReg.Col_URL);	
		System.out.println(navigateURL);
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		cap.setCapability("version", "");
		cap.setPlatform(Platform.LINUX);

		driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);

		/*String driverPath = rootPath + "Drivers\\";
		System.setProperty("webdriver.gecko.driver", driverPath+ "geckodriver.exe");
		driver =new FirefoxDriver();*/
		Log.info("New driver instantiated");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Log.info("Implicit wait applied on the driver for 20 seconds");
		driver.get("https://www.nisnass.ae/");
		
		logger = extent.startTest(sTestCaseName);
		Log.info("Web application launched successfully");
		logger.log(LogStatus.INFO, "Web application launched successfully"+this.getClass().getName());
		wait = new WebDriverWait(driver, 120);
	}

	//Verify Account Registration
	@Test
	public void nisnassAccountRegistration() throws Exception {
		try{

			logger.log(LogStatus.INFO, "User Registration Test Started"+this.getClass().getName());
			Log.info("NISNASS User Registration Tests started");
			Reporter.log("NISNASS User Registration Tests started");
			//Tests Actual Steps
			fn_UserRegistration(iTestCaseRow);

			if(bResult==true){
				Reporter.log("NISNASS User Registration Tests verified successfully");
				// If the value of boolean variable is True, then your test is complete pass and do this
				ExcelUtils.setCellData("Pass", iTestCaseRow, ConstantOnlineReg.Col_Result);
				ExcelUtils.writeWorkbook();

			}else{

				Log.info("NISNASS User Registration Tests Failed");
				Reporter.log("NISNASS User Registration TestsFailed");
				logger.log(LogStatus.FAIL, "User Registration Test Failed"+this.getClass().getName());
				throw new Exception("User Registration Test Failed");

			}



		}catch (Exception e){
			Log.info("NISNASS User Registration Tests Failed because of Verification");
			Reporter.log("NISNASS User Registration Tests Failed because of Verification");
			// If in case you got any exception during the test, it will mark your test as Fail in the test result sheet
			ExcelUtils.setCellData("Fail", iTestCaseRow, ConstantOnlineReg.Col_Result);
			ExcelUtils.writeWorkbook();
			//Take Screenshot
			takeScreenshot(driver, sTestCaseName);
			// This will print the error log message
			Log.error(e.getMessage());
			throw (e);
		}

	}


	@AfterClass
	public void quit() {
		Log.endTestCase(sTestCaseName);
		Reporter.log("Nisnass User Registration Test Ends::" + sTestCaseName);
		logger.log(LogStatus.INFO, "Nisnass User Registration Test Ends::" + sTestCaseName+this.getClass().getName());
		extent.endTest(logger);
		//extent.close();
		//extent.flush();
		//driver.close();
		driver.quit();
	}

	private static void fn_UserRegistration(int iTestCaseRow) throws Exception{
		if(isElementPresent(driver,By.cssSelector(".CustomerPopup > button:nth-child(1)"))){
			Log.info("NISNASS HP Launched");
			Reporter.log("NISNASS HP Launched");
			//Click Account Link to SignUp
			driver.findElement(By.cssSelector(".CustomerPopup > button:nth-child(1)")).click();


			//Verify that popup opened successfully
			if(isElementPresent(driver,By.cssSelector(".SignInForm-signUpButton"))){
				//check the individual fields on the page
				logger.log(LogStatus.PASS, "Sigup Popup opened sucessfully"+TC_01_NISNASSAccountRegistration.class.getName());
				Reporter.log("Sigup Popup opened sucessfully");
				driver.findElement(By.cssSelector(".SignInForm-signUpButton")).click();
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".Profile-firstName")));
				fn_RegistrationDetail(iTestCaseRow);
			}
			else{
				logger.log(LogStatus.FAIL, "Sigup Popup Does not opened"+TC_01_NISNASSAccountRegistration.class.getName());
				Log.info("Sigup Popup Does not opened");
				Reporter.log("Sigup Popup Does not opened");
				bResult = false;
				takeScreenshot(driver, "Sigup Popup");

			}

		}
		else{
			Log.info("NISNASS HP Does not Launched Successfully");
			Reporter.log("NISNASS HP Does not Launched Successfully");
			bResult = false;
			takeScreenshot(driver, "Login");
		}
	}

	public static void fn_RegistrationDetail(int iTestCaseRow)throws Exception{

		driver.findElement(By.cssSelector(".Profile-firstName")).clear();
		driver.findElement(By.cssSelector(".Profile-firstName")).sendKeys (fn_getAlphabeticString_en());
		//(ExcelUtils.getCellData(iTestCaseRow,ConstantOnlineReg.Col_FirstName));

		driver.findElement(By.cssSelector(prop.getProperty("txt_LastName_css"))).clear();
		driver.findElement(By.cssSelector(prop.getProperty("txt_LastName_css"))).sendKeys(fn_getAlphabeticString_en());

		driver.findElement(By.cssSelector(prop.getProperty("txt_Email_css"))).clear();
		driver.findElement(By.cssSelector(prop.getProperty("txt_Email_css"))).sendKeys(fn_getAlphabeticString_en() + "@" + "hotmail.com");

		driver.findElement(By.cssSelector(prop.getProperty("txt_Password_css"))).clear();
		driver.findElement(By.cssSelector(prop.getProperty("txt_Password_css"))).sendKeys(fn_getAlphabeticString_en());

		driver.findElement(By.cssSelector(prop.getProperty("txt_PhoneNumber_css"))).clear();
		driver.findElement(By.cssSelector(prop.getProperty("txt_PhoneNumber_css"))).sendKeys(ExcelUtils.getCellData(iTestCaseRow,ConstantOnlineReg.Col_PhoneNumber));

		//Click signup button
		driver.findElement(By.cssSelector(prop.getProperty("btn_CreateAccount"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(prop.getProperty("lnk_MyAccount_css"))));
		driver.findElement(By.cssSelector(prop.getProperty("lnk_MyAccount_css"))).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(prop.getProperty("txt_MyAccount_css")))));
		//Verify that verification code page is displayed
		if(isElementPresent(driver,By.cssSelector(prop.getProperty("txt_MyAccount_css")))){
			logger.log(LogStatus.PASS, "User Loggin Sucess"+TC_01_NISNASSAccountRegistration.class.getName());
			Log.info("User Loggin Sucess");
			Reporter.log("User Loggin Sucess");
			//Click on MyProfile
			driver.findElement(By.cssSelector(prop.getProperty("lnk_MyProfile_css"))).click();
			//Verify that email is non editable
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(prop.getProperty("txt_Email_css")))));
			//	String readonlyEmail = driver.findElement(By.cssSelector(prop.getProperty("txt_Email_css"))).getAttribute("disabled");
			if (driver.findElement(By.cssSelector(prop.getProperty("txt_Email_css"))).isEnabled()==false){
				logger.log(LogStatus.PASS, "Email Field is non editable"+TC_01_NISNASSAccountRegistration.class.getName());
				Log.info("Email Field is non editable");
				Reporter.log("Email Field is non editable");
			}
			else
			{
				logger.log(LogStatus.FAIL, "Email Field should be non editable"+TC_01_NISNASSAccountRegistration.class.getName());
				Log.info("Email Field should be non editable");
				Reporter.log("Email Field should be non editable");
				bResult = false;
				takeScreenshot(driver, "MyAccount");
			}

			//Update mobile number and verify
			driver.findElement(By.cssSelector(prop.getProperty("txt_PhoneNumber_css"))).sendKeys(Keys.chord(Keys.CONTROL,"a"));;
			driver.findElement(By.cssSelector(prop.getProperty("txt_PhoneNumber_css"))).sendKeys(Keys.BACK_SPACE);
			driver.findElement(By.cssSelector(prop.getProperty("txt_PhoneNumber_css"))).sendKeys(ExcelUtils.getCellData(iTestCaseRow,ConstantOnlineReg.Col_NewPhoneNumber));

			//Click on Update
			driver.findElement(By.cssSelector(prop.getProperty("btn_UpdateProfile_css"))).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(prop.getProperty("msg_Success_css"))));
			//check the message is showing as update success
			String strSuccessMsg = driver.findElement(By.cssSelector(prop.getProperty("msg_Success_css"))).getText();
			if (strSuccessMsg.contains("updated")){
				//Check the number is updated
				logger.log(LogStatus.PASS, "Profile is updated successfully"+TC_01_NISNASSAccountRegistration.class.getName());
				Log.info("Profile is updated successfully");
				Reporter.log("Profile is updated successfully");
				if (driver.findElement(By.cssSelector(prop.getProperty("txt_PhoneNumber_css"))).getAttribute("value").equals((ExcelUtils.getCellData(iTestCaseRow,ConstantOnlineReg.Col_NewPhoneNumber)))){
					logger.log(LogStatus.PASS, "New mobile number is updated successfully"+TC_01_NISNASSAccountRegistration.class.getName());
					Log.info("New mobile number is updated successfully");
					Reporter.log("New mobile number is updated successfully");

				}
				else {
					logger.log(LogStatus.FAIL, "Mobile number is not updated with new number"+TC_01_NISNASSAccountRegistration.class.getName());
					Log.info("Mobile number is not updated with new number");
					Reporter.log("Mobile number is not updated with new number");
					bResult = false;
					takeScreenshot(driver, "MyAccount");
				}
			}
			else {
				logger.log(LogStatus.FAIL, "Update Message is not displayed on Profile Update"+TC_01_NISNASSAccountRegistration.class.getName());
				Log.info("Update Message is not displayed on Profile Update");
				Reporter.log("Update Message is not displayed on Profile Update");
				bResult = false;
				takeScreenshot(driver, "MyAccount");
			}

		}
		else{
			logger.log(LogStatus.FAIL, "User in not loggedin"+TC_01_NISNASSAccountRegistration.class.getName());
			Log.info("User in not loggedin");
			Reporter.log("User in not loggedin");
			bResult = false;
			takeScreenshot(driver, "Login");
		}



	}
}
