/*package com.altayer.web.tests;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Properties;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.altayer.web.actions.Action_TC_01_UserRegistration;
import com.altayer.web.actions.Action_TC_02_AddToCart;
import com.altayer.web.utilitiy.BaseClass;
import com.altayer.web.utilitiy.ConstantOnlineReg;
import com.altayer.web.utilitiy.ExcelUtils;
import com.altayer.web.utilitiy.Log;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;



public class TC_0_NISNASS {
	private String sTestCaseName;
	private int iTestCaseRow;
	public static SoftAssert softas = new SoftAssert();
	public static RemoteWebDriver driver;
	public static String appURL = "https://www.nisnass.ae/";
	public static ExtentReports extent;
	public static ExtentTest logger;
	public static Properties prop = null; 
	
    @BeforeClass
    public void beforeClass() throws Exception {
        long id = Thread.currentThread().getId();
        System.out.println("Before test-class. Thread id is: " + id);
    	if(prop==null){
			prop = new Properties();
			try{
				FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"\\ElementLocators.properties");
				prop.load(fs);

			}catch (Exception e){
				e.printStackTrace();
			}

		}
    	// Configuring Log4j logs
		DOMConfigurator.configure("log4j.xml");
		// The main use is to get the TestCase row from the Test Data Excel sheet
		sTestCaseName = this.toString();
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
		// Launching the browser, this will take the Browser Type from Test Data Sheet
		extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/ExtentReport.html", true);
		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
		driver.get(appURL);
		driver.manage().window().maximize();
    }
	

	@Test
	public void Nisnass_Tests() throws Exception {

		try{
			logger = extent.startTest(sTestCaseName);
			fn_UserRegistration(iTestCaseRow);
		
		if(BaseClass.bResult==true){
				logger.log(LogStatus.PASS, "NISNASS Tests has been verified successfully");
				// If the value of boolean variable is True, then your test is complete pass and do this
				ExcelUtils.setCellData("Pass", iTestCaseRow, ConstantOnlineReg.Col_Result);
				ExcelUtils.writeWorkbook();
				//BaseClass.driver.quit();

			}else{
				logger.log(LogStatus.FAIL, "NISNASS Tests Failed");
				throw new Exception("NISNASS Tests Failed because of Verification");

			}

			// Below are the steps you may like to perform in case of failed test or any exception faced before ending your test 
		}catch (Exception e){
			logger.log(LogStatus.FAIL, "NISNASS Tests Failed");
			// If in case you got any exception during the test, it will mark your test as Fail in the test result sheet
			ExcelUtils.setCellData("Fail", iTestCaseRow, ConstantOnlineReg.Col_Result);
			ExcelUtils.writeWorkbook();
			// If the exception is in between the test, bcoz of any element not found or anything, this will take a screen shot
			Utils.takeScreenshot(driver, sTestCaseName);
			// This will print the error log message
			Log.error(e.getMessage());
			//softas.fail();
			//softas.assertAll();
			// Again throwing the exception to fail the test completely in the TestNG results
			throw (e);
		}

	}

	// Its time to close the finish the test case		
	@AfterClass
    public void afterClass() {
        long id = Thread.currentThread().getId();
        System.out.println("After test-class. Thread id is: " + id);
    	// Printing beautiful logs to end the test case
		Log.endTestCase(sTestCaseName);
		extent.endTest(BaseClass.logger);
		extent.flush();
		//BaseClass.driver.close();
		driver.quit();
    }
	
	
	private static void fn_UserRegistration(int iTestCaseRow) throws Exception{
		logger.log(LogStatus.INFO, "Tests Execution started for Online Registration: ");
		if(Utils.isElementPresent(By.cssSelector(prop.getProperty("lnk_logo")))){
			Log.info("NISNASS HP Launched");
			logger.log(LogStatus.PASS, "NISNASS HP Launched");
			Reporter.log("NISNASS HP Launched");
			//Click Account Link to SignUp
			driver.findElement(By.cssSelector(prop.getProperty("btn_Acount_css"))).click();
		
			
			//Verify that popup opened successfully
			if(Utils.isElementPresent(By.cssSelector(prop.getProperty("btn_SignUP")))){
				//check the individual fields on the page
					logger.log(LogStatus.PASS, "Sigup Popup opened sucessfully");
					Reporter.log("Sigup Popup opened sucessfully");
					driver.findElement(By.cssSelector(prop.getProperty("btn_SignUP"))).click();
					BaseClass.wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(prop.getProperty("txt_FirstName_css"))));
					fn_RegistrationDetail(iTestCaseRow);
				}
			else{
				Log.info("Sigup Popup Does not opened");
				logger.log(LogStatus.FAIL, "Sigup Popup Does not opened");
				Reporter.log("Sigup Popup Does not opened");
				BaseClass.bResult = false;
				BaseClass.takeScreenshot(driver, "Sigup Popup");

			}

		}
		else{
			Log.info("NISNASS HP Does not Launched Successfully");
			logger.log(LogStatus.FAIL, "NISNASS HP Does not Launched Successfully");
			Reporter.log("NISNASS HP Does not Launched Successfully");
			BaseClass.bResult = false;
			BaseClass.takeScreenshot(driver, "Login");
		}
	}


	public static void fn_RegistrationDetail(int iTestCaseRow)throws Exception{

		driver.findElement(By.cssSelector(prop.getProperty("txt_FirstName_css"))).clear();
		driver.findElement(By.cssSelector(prop.getProperty("txt_FirstName_css"))).sendKeys (BaseClass.fn_getAlphabeticString_en());
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
		BaseClass.wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(prop.getProperty("lnk_MyAccount_css"))));
		driver.findElement(By.cssSelector(prop.getProperty("lnk_MyAccount_css"))).click();
		//wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(prop.getProperty("verification_code_css")))));
		//Verify that verification code page is displayed
		if(Utils.isElementPresent(By.cssSelector(prop.getProperty("txt_MyAccount_css")))){
		
			logger.log(LogStatus.PASS, "User Loggin Sucess");
			Log.info("User Loggin Sucess");
			Reporter.log("User Loggin Sucess");
			//Click on MyProfile
			driver.findElement(By.cssSelector(prop.getProperty("lnk_MyProfile_css"))).click();
			//Verify that email is non editable
			
		//	String readonlyEmail = driver.findElement(By.cssSelector(prop.getProperty("txt_Email_css"))).getAttribute("disabled");
			if (driver.findElement(By.cssSelector(prop.getProperty("txt_Email_css"))).isEnabled()==false){
				logger.log(LogStatus.PASS, "Email Field is non editable");
				Log.info("Email Field is non editable");
				Reporter.log("Email Field is non editable");
			}
				else
				{
					Log.info("Email Field should be non editable");
					logger.log(LogStatus.FAIL, "Email Field should be non editable");
					Reporter.log("Email Field should be non editable");
					BaseClass.bResult = false;
					BaseClass.takeScreenshot(driver, "MyAccount");
				}
			
			//Update mobile number and verify
			driver.findElement(By.cssSelector(prop.getProperty("txt_PhoneNumber_css"))).sendKeys(Keys.chord(Keys.CONTROL,"a"));;
			driver.findElement(By.cssSelector(prop.getProperty("txt_PhoneNumber_css"))).sendKeys(Keys.BACK_SPACE);
			driver.findElement(By.cssSelector(prop.getProperty("txt_PhoneNumber_css"))).sendKeys(ExcelUtils.getCellData(iTestCaseRow,ConstantOnlineReg.Col_NewPhoneNumber));
			
			//Click on Update
			driver.findElement(By.cssSelector(prop.getProperty("btn_UpdateProfile_css"))).click();
			BaseClass.wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(BaseClass.prop.getProperty("msg_Success_css"))));
			//check the message is showing as update success
			String strSuccessMsg = driver.findElement(By.cssSelector(prop.getProperty("msg_Success_css"))).getText();
			if (strSuccessMsg.contains("updated")){
				//Check the number is updated
				logger.log(LogStatus.PASS, "Profile is updated successfully");
				Log.info("Profile is updated successfully");
				Reporter.log("Profile is updated successfully");
				if (driver.findElement(By.cssSelector(prop.getProperty("txt_PhoneNumber_css"))).getAttribute("value").equals((ExcelUtils.getCellData(iTestCaseRow,ConstantOnlineReg.Col_NewPhoneNumber)))){
					logger.log(LogStatus.PASS, "New mobile number is updated successfully");
					Log.info("New mobile number is updated successfully");
					Reporter.log("New mobile number is updated successfully");
					
				}
				else {
					Log.info("Mobile number is not updated with new number");
					logger.log(LogStatus.FAIL, "Mobile number is not updated with new number");
					Reporter.log("Mobile number is not updated with new number");
					BaseClass.bResult = false;
					BaseClass.takeScreenshot(driver, "MyAccount");
				}
			}
			else {
				Log.info("Update Message is not displayed on Profile Update");
				logger.log(LogStatus.FAIL, "Update Message is not displayed on Profile Update");
				Reporter.log("Update Message is not displayed on Profile Update");
				BaseClass.bResult = false;
				BaseClass.takeScreenshot(driver, "MyAccount");
			}
					
		}
		else{
			
			Log.info("User in not loggedin");
			logger.log(LogStatus.FAIL, "User in not loggedin");
			Reporter.log("User in not loggedin");
			BaseClass.bResult = false;
			BaseClass.takeScreenshot(driver, "Login");
		}
			


		}

	
}





*/