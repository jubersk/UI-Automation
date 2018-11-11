package com.altayer.web.tests;

import org.testng.annotations.Test;
import com.altayer.web.utilitiy.BaseClass;
import com.altayer.web.utilitiy.ConstantOnlineReg;
import com.altayer.web.utilitiy.ExcelUtils;
import com.altayer.web.utilitiy.Log;
import com.altayer.web.utilitiy.utils;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;


public class Tests_01_NISNASS extends utils{
	public static WebDriver driver = null;
	private static String rootPath = "";
	public static WebDriverWait wait;
	public static Properties prop = null; 
	private String sTestCaseName;
	private int iTestCaseRow;
	public static boolean bResult;
	private static String navigateURL = "";



	// This method is to navigate flipkart URL
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
		//BaseClass baseClass=new BaseClass();
		// The below method will refine your test case name, exactly the name use have used
		sTestCaseName = BaseClass.getTestCaseName(this.toString());
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
		String driverPath = rootPath + "Drivers\\";
		System.setProperty("webdriver.gecko.driver", driverPath+ "geckodriver.exe");
	
		driver =new FirefoxDriver();
		Log.info("New driver instantiated");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Log.info("Implicit wait applied on the driver for 20 seconds");
		driver.get(navigateURL);
		Log.info("Web application launched successfully");
		logger.log(LogStatus.INFO, "Web application launched successfully");
		wait = new WebDriverWait(driver, 120);
	}

	// Account Registration
	@Test
	public void accountRegistration() throws Exception {
		try{
			extent.startTest(sTestCaseName);
			logger.log(LogStatus.INFO, "User Registration Test Started");
			Log.info("NISNASS User Registration Tests started");
			Reporter.log("NISNASS User Registration Tests Failed");
			fn_UserRegistration(iTestCaseRow);

		}catch (Exception e){
			Log.info("NISNASS User Registration Tests Failed");
			Reporter.log("NISNASS User Registration Tests Failed");
			// If in case you got any exception during the test, it will mark your test as Fail in the test result sheet

			//Take Screenshot

			// This will print the error log message
			Log.error(e.getMessage());
			throw (e);
		}

	}


	@AfterClass
	public void quit() {
		Log.endTestCase(sTestCaseName);
		Reporter.log("Nisnass User Registration Test Ends::" + sTestCaseName);
		extent.endTest(logger);
		//extent.flush();
		driver.close();
		//driver.quit();
	}

	private static void fn_UserRegistration(int iTestCaseRow) throws Exception{
		if(BaseClass.isElementPresent(driver,By.cssSelector(".CustomerPopup > button:nth-child(1)"))){
			Log.info("NISNASS HP Launched");
			Reporter.log("NISNASS HP Launched");
			//Click Account Link to SignUp
			driver.findElement(By.cssSelector(".CustomerPopup > button:nth-child(1)")).click();


			//Verify that popup opened successfully
			if(BaseClass.isElementPresent(driver,By.cssSelector(".SignInForm-signUpButton"))){
				//check the individual fields on the page
				Reporter.log("Sigup Popup opened sucessfully");
				driver.findElement(By.cssSelector(".SignInForm-signUpButton")).click();
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".Profile-firstName")));
				fn_RegistrationDetail(iTestCaseRow);
			}
			else{
				Log.info("Sigup Popup Does not opened");
				Reporter.log("Sigup Popup Does not opened");
				bResult = false;
				BaseClass.takeScreenshot(driver, "Sigup Popup");

			}

		}
		else{
			Log.info("NISNASS HP Does not Launched Successfully");
			Reporter.log("NISNASS HP Does not Launched Successfully");
			bResult = false;
			BaseClass.takeScreenshot(driver, "Login");
		}
	}

	public static void fn_RegistrationDetail(int iTestCaseRow)throws Exception{

		driver.findElement(By.cssSelector(".Profile-firstName")).clear();
		driver.findElement(By.cssSelector(".Profile-firstName")).sendKeys (BaseClass.fn_getAlphabeticString_en());
		//(ExcelUtils.getCellData(iTestCaseRow,ConstantOnlineReg.Col_FirstName));

		driver.findElement(By.cssSelector(prop.getProperty("txt_LastName_css"))).clear();
		driver.findElement(By.cssSelector(prop.getProperty("txt_LastName_css"))).sendKeys(BaseClass.fn_getAlphabeticString_en());

		driver.findElement(By.cssSelector(prop.getProperty("txt_Email_css"))).clear();
		driver.findElement(By.cssSelector(prop.getProperty("txt_Email_css"))).sendKeys(BaseClass.fn_getAlphabeticString_en() + "@" + "hotmail.com");

		driver.findElement(By.cssSelector(prop.getProperty("txt_Password_css"))).clear();
		driver.findElement(By.cssSelector(prop.getProperty("txt_Password_css"))).sendKeys(BaseClass.fn_getAlphabeticString_en());

		driver.findElement(By.cssSelector(prop.getProperty("txt_PhoneNumber_css"))).clear();
		driver.findElement(By.cssSelector(prop.getProperty("txt_PhoneNumber_css"))).sendKeys(ExcelUtils.getCellData(iTestCaseRow,ConstantOnlineReg.Col_PhoneNumber));

		//Click signup button
		driver.findElement(By.cssSelector(prop.getProperty("btn_CreateAccount"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(prop.getProperty("lnk_MyAccount_css"))));
		driver.findElement(By.cssSelector(prop.getProperty("lnk_MyAccount_css"))).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(prop.getProperty("txt_MyAccount_css")))));
		//Verify that verification code page is displayed
		if(BaseClass.isElementPresent(driver,By.cssSelector(prop.getProperty("txt_MyAccount_css")))){

			Log.info("User Loggin Sucess");
			Reporter.log("User Loggin Sucess");
			//Click on MyProfile
			driver.findElement(By.cssSelector(prop.getProperty("lnk_MyProfile_css"))).click();
			//Verify that email is non editable
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(prop.getProperty("txt_Email_css")))));
			//	String readonlyEmail = driver.findElement(By.cssSelector(prop.getProperty("txt_Email_css"))).getAttribute("disabled");
			if (driver.findElement(By.cssSelector(prop.getProperty("txt_Email_css"))).isEnabled()==false){
				Log.info("Email Field is non editable");
				Reporter.log("Email Field is non editable");
			}
			else
			{
				Log.info("Email Field should be non editable");
				Reporter.log("Email Field should be non editable");
				bResult = false;
				BaseClass.takeScreenshot(driver, "MyAccount");
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
				Log.info("Profile is updated successfully");
				Reporter.log("Profile is updated successfully");
				if (driver.findElement(By.cssSelector(prop.getProperty("txt_PhoneNumber_css"))).getAttribute("value").equals((ExcelUtils.getCellData(iTestCaseRow,ConstantOnlineReg.Col_NewPhoneNumber)))){
					Log.info("New mobile number is updated successfully");
					Reporter.log("New mobile number is updated successfully");

				}
				else {
					Log.info("Mobile number is not updated with new number");
					Reporter.log("Mobile number is not updated with new number");
					bResult = false;
					BaseClass.takeScreenshot(driver, "MyAccount");
				}
			}
			else {
				Log.info("Update Message is not displayed on Profile Update");
				Reporter.log("Update Message is not displayed on Profile Update");
				bResult = false;
				BaseClass.takeScreenshot(driver, "MyAccount");
			}

		}
		else{

			Log.info("User in not loggedin");
			Reporter.log("User in not loggedin");
			bResult = false;
			BaseClass.takeScreenshot(driver, "Login");
		}



	}
}
