package com.altayer.web.tests;

import org.testng.annotations.Test;

import com.altayer.web.utilitiy.BaseClass;
import com.altayer.web.utilitiy.ConstantOnlineReg;
import com.altayer.web.utilitiy.ExcelUtils;
import com.altayer.web.utilitiy.Log;
import com.relevantcodes.extentreports.LogStatus;
import java.io.FileInputStream;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class TC_03_OUNASS extends BaseClass {

	public static RemoteWebDriver driver = null;
	private static String rootPath = "";
	public static WebDriverWait wait;
	public static Properties prop = null; 
	private String sTestCaseName;
	private int iTestCaseRow;
	public static boolean bResult;
	private static String navigateURL = "";


	
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
		
		//*************Local driver***********************//
		/*Path currentRelativePath = Paths.get("");
		rootPath = currentRelativePath.toAbsolutePath().toString() + "\\";
		System.out.println(rootPath);
		String driverPath = rootPath + "Drivers\\";
		System.setProperty("webdriver.chrome.driver", driverPath+ "chromedriver.exe");
		//driver = new HtmlUnitDriver();
		driver = new ChromeDriver();*/
		//**************************************************//
		
		
		//**********Remote Driver**********************************//
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		cap.setCapability("version", "");
		cap.setPlatform(Platform.LINUX);
		
		driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
		
		//************************************************************//
		
		Log.info("New driver instantiated");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Log.info("Implicit wait applied on the driver for 20 seconds");
		//				driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.get("https://www.ounass.ae/");
		Log.info("Web application launched successfully");
		logger = extent.startTest(sTestCaseName);
		logger.log(LogStatus.INFO, "Web application launched successfully");
		wait = new WebDriverWait(driver, 120);
	}

	// FB Login
	@Test
	public void ounassFacebookLogin() throws Exception {
		try{
			//extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/ExtentReportNew.html", true);

			logger.log(LogStatus.INFO, "Ounass FB Login Test started");
			Log.info("Ounass FB Login Test started::" + sTestCaseName);
			Reporter.log("Ounass FB Login Test started::" + sTestCaseName);
			//Execute the action on below test
			bResult = true;
			//Check popups
			//Check if modal popups are open
			if (isElementPresent(driver,By.xpath("//*[@id='onesignal-popover-cancel-button']"))){
				//Close popup
				driver.findElement(By.xpath("//*[@id='onesignal-popover-cancel-button']")).click();
				Thread.sleep(1000);
			}

			if (isElementPresent(driver,By.cssSelector(prop.getProperty("btn_popupnewsLetter_css")))){
				//Close popup
				driver.findElement(By.cssSelector(prop.getProperty("btn_popupnewsLetter_css"))).click();
				Thread.sleep(1000);

			}
			//Tests Actual Steps
			fn_LoginFlow();

			if(bResult==true){
				Reporter.log("OUNASS FB Login Tests has been verified successfully");
				// If the value of boolean variable is True, then your test is complete pass and do this
				ExcelUtils.setCellData("Pass", iTestCaseRow, ConstantOnlineReg.Col_Result);
				ExcelUtils.writeWorkbook();


			}else{
				Log.info("OUNASS FB Login  FB Tests Failed");
				Reporter.log("OUNASS FB Login  FB Tests Failed");
				logger.log(LogStatus.FAIL, "OUNASS FB Login  FB Tests Failed");
				throw new Exception("OUNASS FB Login  FB Tests Failed");
			}

			// Below are the steps you may like to perform in case of failed test or any exception faced before ending your test 
		}catch (Exception e){
			Log.info("OUNASS FB Login  Tests Failed because of Verification");
			Reporter.log("OUNASS FB Login  Tests Failed because of Verification");
			// If in case you got any exception during the test, it will mark your test as Fail in the test result sheet
			ExcelUtils.setCellData("Fail", iTestCaseRow, ConstantOnlineReg.Col_Result);
			ExcelUtils.writeWorkbook();
			// If the exception is in between the test, bcoz of any element not found or anything, this will take a screen shot
			takeScreenshot(driver, sTestCaseName);
			// This will print the error log message
			Log.error(e.getMessage());
			throw (e);
		}
	}



	@AfterClass
	public void quit() {
		Log.endTestCase(sTestCaseName);
		Reporter.log("OUNASS FB Login Test Ends::" + sTestCaseName);
		extent.endTest(logger);
		//driver.close();
		driver.quit();



	}


	public static void fn_LoginFlow() throws Exception{
		//then click on user icon
		driver.findElement(By.cssSelector(prop.getProperty("icon_user_css"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(prop.getProperty("btn_Amber_css"))));

		//Click on Amber button
		driver.findElement(By.cssSelector(prop.getProperty("btn_Amber_css"))).click();
		Thread.sleep(1000);
		// Store the current window handle
		String winHandleBefore = driver.getWindowHandle();
		driver.switchTo().frame(0);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prop.getProperty("btn_LoginwithFacebook_id"))));
		//Click on Login with FB button
		driver.findElement(By.id(prop.getProperty("btn_LoginwithFacebook_id"))).click();
		Thread.sleep(2000);
		// Switch to new window opened
		for(String winHandle : driver.getWindowHandles()){
			driver.switchTo().window(winHandle);
		}

		// Perform the actions on new window
		driver.findElement(By.cssSelector("#email")).sendKeys("jubersk@gmail.com");
		driver.findElement(By.cssSelector("#pass")).sendKeys("@sonu&mywife#amru84");
		driver.findElement(By.cssSelector("#u_0_0")).click();
		Thread.sleep(3000);
		// Close the new window, if that window no more required
		//driver.close();

		// Switch back to original browser (first window)
		driver.switchTo().window(winHandleBefore);


		//Check that user is logged in to FB account successfully
		//if not then check the error message on frame(0)
		driver.switchTo().frame(0);
		String strErrorData = driver.findElement(By.cssSelector("#errorData")).getText();

		if(strErrorData.contains("Please try again")) {

			//User is not logged in successfully
			Log.info("Social Account Error while FB Login");
			Reporter.log("Social Account Error while FB Login");
			logger.log(LogStatus.FAIL, "Social Account Error while FB Login");
			bResult = false;
			
			//takeScreenshot(driver, "ProductListingPage");

		}
		driver.switchTo().parentFrame();
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prop.getProperty("btn_LoginwithFacebook_css"))));

		//Enter FB Credentials and return to site and make sure user is logged in


	}
}
