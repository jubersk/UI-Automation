package com.altayer.web.utilitiy;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class utils {
	public static ExtentReports extent;
	public static ExtentTest logger;

	@BeforeSuite
	public static void extentReport(){
		extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/ExtentReportNew.html", true);
	}
	
	@AfterSuite
	public void flushExtentReport(){
	    extent.flush();
	   // extent.close();
	   }
	}
	

