/*package com.altayer.web.tests;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.altayer.web.actions.Action_TC_02_AddToCart;
import com.altayer.web.utilitiy.BaseClass;
import com.altayer.web.utilitiy.ConstantOnlineReg;
import com.altayer.web.utilitiy.ExcelUtils;
import com.altayer.web.utilitiy.Log;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;


public class TC_02_AddToCart {
	public WebDriver driver;
	private String sTestCaseName;
	private int iTestCaseRow;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static SoftAssert softas = new SoftAssert();

	@BeforeMethod
	public void beforeSuite() throws Exception {
		// Configuring Log4j logs
		DOMConfigurator.configure("log4j.xml");
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
		driver = BaseClass.OpenBrowser(iTestCaseRow);
		//new BaseClass(driver);  
	}
	
	@Test
	public void AddToCart_Tests() throws Exception {

		try{
			Log.info("NISNASS Add To Cart Test started::" + sTestCaseName);
			Reporter.log("NISNASS Add To Cart Test started::" + sTestCaseName);
			Action_TC_02_AddToCart.Execute(iTestCaseRow);


		if(BaseClass.bResult==true){
				Reporter.log("Add to Cart Tests has been verified successfully");
				// If the value of boolean variable is True, then your test is complete pass and do this
				ExcelUtils.setCellData("Pass", iTestCaseRow, ConstantOnlineReg.Col_Result);
				ExcelUtils.writeWorkbook();
				
			}else{
				
				Log.info("Add to Cart Tests Failed");
				Reporter.log("Add to Cart Tests Failed");
			
				throw new Exception("Add to Cart Tests Failed because of Verification");

			}

			// Below are the steps you may like to perform in case of failed test or any exception faced before ending your test 
		}catch (Exception e){
			Log.info("Add to Cart Testss Failed because of Verification");
			Reporter.log("Add to Cart Testss Failed because of Verification");
			// If in case you got any exception during the test, it will mark your test as Fail in the test result sheet
			ExcelUtils.setCellData("Fail", iTestCaseRow, ConstantOnlineReg.Col_Result);
			ExcelUtils.writeWorkbook();
			// If the exception is in between the test, bcoz of any element not found or anything, this will take a screen shot
			BaseClass.takeScreenshot(driver, sTestCaseName);
			// This will print the error log message
			Log.error(e.getMessage());
		
			// Again throwing the exception to fail the test completely in the TestNG results
			throw (e);
		}
		


	}

	
		@AfterMethod
		public void afterSuite() {
			Log.endTestCase(sTestCaseName);
			Reporter.log("Nisnass User Registration Test Ends::" + sTestCaseName);
			BaseClass.driver.quit();
		}
}



*/