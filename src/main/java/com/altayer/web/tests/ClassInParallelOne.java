/*package com.altayer.web.tests;

import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.altayer.web.actions.Action_TC_01_UserRegistration;
import com.altayer.web.actions.Action_TC_02_AddToCart;
import com.altayer.web.utilitiy.BaseClass;
import com.altayer.web.utilitiy.ConstantOnlineReg;
import com.altayer.web.utilitiy.ExcelUtils;
import com.altayer.web.utilitiy.Log;

public class ClassInParallelOne {
	static WebDriver driver=null;
	private static String rootPath = "";
	private static int iTestCaseRow;
	private String sTestCaseName;
	@BeforeMethod
	public void beforeMethod() throws Exception{
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
		ExcelUtils.setExcelFile(ConstantOnlineReg.Path_TestData + ConstantOnlineReg.File_TestData,"TestData");
		iTestCaseRow = ExcelUtils.getRowContains(sTestCaseName,ConstantOnlineReg.Col_TestCaseName);
		Path currentRelativePath = Paths.get("");
		rootPath = currentRelativePath.toAbsolutePath().toString() + "\\";
		String driverPath = rootPath + "Drivers\\";
		System.setProperty("webdriver.gecko.driver", driverPath+ "geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		
	
		
	}
	
	@Test()
	public void firstTestMethod() throws Exception {
		driver.get("https://www.nisnass.ae/");
		Action_TC_01_UserRegistration.Execute(iTestCaseRow);
		System.out.println("First test method from Class One, ThreadID: https://www.nisnass.ae/::" + Thread.currentThread().getId());
	}

	@Test()
	public void secondTestMethod() throws Exception {
		//driver.get("https://www.nisnass.ae/clothing");
		Action_TC_02_AddToCart.Execute(iTestCaseRow);
		System.out.println("Second test method from Class One, ThreadID: https://www.nisnass.ae/clothing::" + Thread.currentThread().getId());
	}
	
	@AfterMethod
	public void afterMethod(){
		System.out.println("Closing Tests");
		driver.quit();
	}
	
	
}*/