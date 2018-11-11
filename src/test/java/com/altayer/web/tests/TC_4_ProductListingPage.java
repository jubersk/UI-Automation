package com.altayer.web.tests;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.altayer.web.actions.Action_TC_04_ProductListing;
import com.altayer.web.utilitiy.BaseClass;
import com.altayer.web.utilitiy.ConstantOnlineReg;
import com.altayer.web.utilitiy.ExcelUtils;
import com.altayer.web.utilitiy.Log;
import com.relevantcodes.extentreports.LogStatus;

public class TC_4_ProductListingPage {/*
	public WebDriver driver;
	private String sTestCaseName;
	private int iTestCaseRow;
	public static SoftAssert softas = new SoftAssert();

	@BeforeMethod
	public void beforeSuite() throws Exception {
		// Configuring Log4j logs
		DOMConfigurator.configure("log4j.xml");
		// The main use is to get the TestCase row from the Test Data Excel sheet
		sTestCaseName = this.toString();
		// The below method will refine your test case name, exactly the name use have used
		sTestCaseName = Utils.getTestCaseName(this.toString());
		System.out.println("Test Case Name:" + sTestCaseName);
		// Start printing the logs and printing the Test Case name
		Log.startTestCase("Test Case Name:" + sTestCaseName);

		// Setting up the Test Data Excel file using Constants variables
		ExcelUtils.setExcelFile(ConstantOnlineReg.Path_TestData + ConstantOnlineReg.File_TestData,"TestData");
		// Fetching the Test Case row number from the Test Data Sheet
		// This row number will be feed to so many functions, to get the relevant data from the Test Data sheet 
		iTestCaseRow = ExcelUtils.getRowContains(sTestCaseName,ConstantOnlineReg.Col_TestCaseName);
		System.out.println("TestCase Row Number" +iTestCaseRow);
		
		
	}
	@Test
	public void AddToCart_Tests() throws Exception {

		try{
			BaseClass.logger = BaseClass.extent.startTest(sTestCaseName);
			Action_TC_04_ProductListing.Execute(iTestCaseRow);
			Thread.sleep(1000);

		if(BaseClass.bResult==true){
				BaseClass.logger.log(LogStatus.PASS, "Product Listing Tests been verified successfully");
				// If the value of boolean variable is True, then your test is complete pass and do this
				ExcelUtils.setCellData("Pass", iTestCaseRow, ConstantOnlineReg.Col_Result);
				ExcelUtils.writeWorkbook();
				//BaseClass.driver.quit();

			}else{
				BaseClass.logger.log(LogStatus.FAIL, "Product Listing Tests Failed");
				throw new Exception("Product Listing Tests Failed because of Verification");

			}

			// Below are the steps you may like to perform in case of failed test or any exception faced before ending your test 
		}catch (Exception e){
			BaseClass.logger.log(LogStatus.FAIL, "Product Listing Tests Failed because of Verification");
			// If in case you got any exception during the test, it will mark your test as Fail in the test result sheet
			ExcelUtils.setCellData("Fail", iTestCaseRow, ConstantOnlineReg.Col_Result);
			ExcelUtils.writeWorkbook();
			// If the exception is in between the test, bcoz of any element not found or anything, this will take a screen shot
			Utils.takeScreenshot(driver, sTestCaseName);
			// This will print the error log message
			Log.error(e.getMessage());
			softas.fail();
			softas.assertAll();
			//	BaseClass.driver.quit();
			// Again throwing the exception to fail the test completely in the TestNG results
			//throw (e);
		}



	}

	
	// Its time to close the finish the test case		
		@AfterMethod
		public void afterSuite() {

			// Printing beautiful logs to end the test case
			Log.endTestCase(sTestCaseName);
			//BaseClass.extent.endTest(BaseClass.logger);
			BaseClass.extent.flush();
			//BaseClass.extent.close();	
			BaseClass.driver.close();
			BaseClass.driver.quit();
		}
*/}



