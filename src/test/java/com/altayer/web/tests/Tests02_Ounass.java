package com.altayer.web.tests;

import org.testng.annotations.Test;

import com.altayer.web.actions.Action_TC_03_LoginThorughFB;
import com.altayer.web.utilitiy.BaseClass;
import com.altayer.web.utilitiy.ConstantOnlineReg;
import com.altayer.web.utilitiy.ExcelUtils;
import com.altayer.web.utilitiy.Log;

import java.io.FileInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class Tests02_Ounass {
	
	public static WebDriver driver = null;
	private static String rootPath = "";
	public static WebDriverWait wait;
	public static Properties prop = null; 
	private String sTestCaseName;
	private int iTestCaseRow;
	public static boolean bResult;
	private static String navigateURL = "";

	// This method is to navigate snapdeal URL
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
		//driver.manage().window().maximize();
		driver =new FirefoxDriver();
		Log.info("New driver instantiated");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Log.info("Implicit wait applied on the driver for 20 seconds");
		//				driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.get(navigateURL);
		Log.info("Web application launched successfully");
	}

	// To log in flipkart
	@Test
	public void login() throws Exception {
		try{
			Log.info("Ounass FB Login Test started::" + sTestCaseName);
			Reporter.log("Ounass FB Login Test started::" + sTestCaseName);
			//Execute the action on below test
			bResult = true;
			//Check popups
			//Check if modal popups are open
			if (BaseClass.isElementPresent(By.xpath("//*[@id='onesignal-popover-cancel-button']"))){
				//Close popup
				driver.findElement(By.xpath("//*[@id='onesignal-popover-cancel-button']")).click();
				Thread.sleep(1000);
			}
		
			if (BaseClass.isElementPresent(By.cssSelector(prop.getProperty("btn_popupnewsLetter_css")))){
				//Close popup
				driver.findElement(By.cssSelector(prop.getProperty("btn_popupnewsLetter_css"))).click();
				Thread.sleep(1000);
				
				fn_LoginFlow();
			}
			
			

		if(bResult==true){
				Reporter.log("OUNASS FB Login Tests has been verified successfully");
				// If the value of boolean variable is True, then your test is complete pass and do this
				ExcelUtils.setCellData("Pass", iTestCaseRow, ConstantOnlineReg.Col_Result);
				ExcelUtils.writeWorkbook();
		

			}else{
				Log.info("OUNASS FB Login  FB Tests Failed");
				Reporter.log("OUNASS FB Login  FB Tests Failed");
			}

			// Below are the steps you may like to perform in case of failed test or any exception faced before ending your test 
		}catch (Exception e){
			Log.info("OUNASS FB Login  Tests Failed because of Verification");
			Reporter.log("OUNASS FB Login  Tests Failed because of Verification");
			// If in case you got any exception during the test, it will mark your test as Fail in the test result sheet
			ExcelUtils.setCellData("Fail", iTestCaseRow, ConstantOnlineReg.Col_Result);
			ExcelUtils.writeWorkbook();
			// If the exception is in between the test, bcoz of any element not found or anything, this will take a screen shot
			BaseClass.takeScreenshot(driver, sTestCaseName);
			// This will print the error log message
			Log.error(e.getMessage());
			throw (e);
		}
	}

	

	@AfterClass
	public void quit() {
		Log.endTestCase(sTestCaseName);
   		Reporter.log("OUNASS FB Login Test Ends::" + sTestCaseName);
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
			Log.info("Error whil FB Login");
			Reporter.log("Error whil FB Login");
			bResult = false;
			BaseClass.takeScreenshot(driver, "ProductListingPage");
			
		}
		driver.switchTo().parentFrame();
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prop.getProperty("btn_LoginwithFacebook_css"))));
		
		//Enter FB Credentials and return to site and make sure user is logged in
		
		
	}
}
