package com.altayer.web.tests;
/*package com.altayer.web.tests;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.altayer.web.actions.Action_TC_04_ProductListing;
import com.altayer.web.utilitiy.BaseClass;
import com.altayer.web.utilitiy.ConstantOnlineReg;
import com.altayer.web.utilitiy.ExcelUtils;
import com.altayer.web.utilitiy.Log;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;


public class TC_04_ProductListingPage {
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
		sTestCaseName = BaseClass.getTestCaseName(this.toString());
		System.out.println("Test Case Name:" + sTestCaseName);
		
		// Start printing the logs and printing the Test Case name
		Log.startTestCase("Test Case Name:" + sTestCaseName);
		// Setting up the Test Data Excel file using Constants variables
		ExcelUtils.setExcelFile(ConstantOnlineReg.Path_TestData + ConstantOnlineReg.File_TestData,"TestData");
		// Fetching the Test Case row number from the Test Data Sheet
		iTestCaseRow = ExcelUtils.getRowContains(sTestCaseName,ConstantOnlineReg.Col_TestCaseName);
		System.out.println("TestCase Row Number" +iTestCaseRow);
		driver = BaseClass.OpenBrowser(iTestCaseRow);
		//new BaseClass(driver);  
		
	}
	@Test
	public void AddToCart_Tests() throws Exception {

		try{
			Log.info("Ounass Product Listing Test started::" + sTestCaseName);
			Reporter.log("Ounass Product Listing Test started::" + sTestCaseName);
			//Execute the Actions on test
			Action_TC_04_ProductListing.Execute(iTestCaseRow);


		if(BaseClass.bResult==true){
				//BaseClass.logger.log(LogStatus.PASS, "Product Listing Tests been verified successfully");
				Reporter.log("All the items addedd in cart successfully");
				// If the value of boolean variable is True, then your test is complete pass and do this
				ExcelUtils.setCellData("Pass", iTestCaseRow, ConstantOnlineReg.Col_Result);
				ExcelUtils.writeWorkbook();
				
			}else{
				Log.info("Product Listing Tests been verification Failed");
				Reporter.log("Product Listing Tests been verification Failed");
				throw new Exception("Product Listing Tests Failed because of Verification");

			}

		}catch (Exception e){
			Log.info("Product Listing Tests been verification Failed");
			Reporter.log("Product Listing Tests been verification Failed");
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
		
		@AfterMethod
		public void afterSuite() {
			Log.endTestCase(sTestCaseName);
			Reporter.log("Ounass Product Listing Test Ends::" + sTestCaseName);
			BaseClass.driver.quit();
		}
}



*/