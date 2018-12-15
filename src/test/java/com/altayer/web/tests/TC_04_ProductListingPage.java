package com.altayer.web.tests;

import org.testng.annotations.Test;

import com.altayer.web.utilitiy.BaseClass;
import com.altayer.web.utilitiy.ConstantOnlineReg;
import com.altayer.web.utilitiy.ExcelUtils;
import com.altayer.web.utilitiy.Log;
import com.relevantcodes.extentreports.LogStatus;
import java.io.FileInputStream;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Platform;
//import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class TC_04_ProductListingPage extends BaseClass {
	
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
		
		DesiredCapabilities cap = DesiredCapabilities.firefox();
		cap.setCapability("version", "");
		cap.setPlatform(Platform.LINUX);
		
		driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
		
	/*	String driverPath = rootPath + "Drivers\\";
		System.setProperty("webdriver.chrome.driver", driverPath+ "chromedriver.exe");
		//driver = new HtmlUnitDriver();
		driver = new ChromeDriver();*/
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

	// Product Listing
	@Test
	public void ounassProductListing() throws Exception {
		try{
			//extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/ExtentReportNew.html", true);
		
			logger.log(LogStatus.INFO, "OUNASS Product Feature Listing Test started");
			Log.info("OUNASS Product Feature Listing Test started::" + sTestCaseName);
			Reporter.log("OUNASS Product Feature Listing  Test started::" + sTestCaseName);
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
			fn_ProductListing();

		if(bResult==true){
				Reporter.log("OUNASS Product Feature Listing Tests has been verified successfully");
				// If the value of boolean variable is True, then your test is complete pass and do this
				ExcelUtils.setCellData("Pass", iTestCaseRow, ConstantOnlineReg.Col_Result);
				ExcelUtils.writeWorkbook();
		

			}else{
				Log.info("OUNASS Product Feature Listing Tests Failed");
				Reporter.log("OUNASS Product Feature Listing Tests Failed");
			}

			// Below are the steps you may like to perform in case of failed test or any exception faced before ending your test 
		}catch (Exception e){
			Log.info("OUNASS Product Feature Listing Tests Failed because of Verification");
			Reporter.log("OUNASS Product Feature Listing Tests Failed because of Verification");
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

	public static void fn_ProductListing() throws Exception{

		driver.get("https://www.ounass.ae/clothing/");
		bResult = true;
		
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
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(prop.getProperty("txt_products_count_css"))));
		}
		
		
			//Check the total count of products in result
			String strCount = driver.findElement(By.cssSelector(prop.getProperty("txt_products_count_css"))).getText();
			//Take the number only from string and check its greater than 72
			strCount = (strCount.replaceAll("[^-?0-9]+", " ")); 
			String trmStrCount = strCount.trim(); 
			System.out.println(trmStrCount);
			System.out.println(Integer.parseInt(trmStrCount));
			int totalCount = Integer.parseInt(trmStrCount);
			
			List<WebElement> objListItems  = driver.findElements(By.className(("img-loaded")));
			System.out.println(objListItems.size());
			if(objListItems.size()<72){
				((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.cssSelector(prop.getProperty("btn_loadmore_css"))));
				System.out.println(objListItems.size());
			}
		
		
	 
			if(totalCount>72){
				//Then scroll until Load more button displays
				((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.cssSelector(prop.getProperty("btn_loadmore_css"))));
				Thread.sleep(2000);
				((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.cssSelector(prop.getProperty("btn_loadmore_css"))));
				Thread.sleep(2000);
				
				if(isElementPresent(driver,By.cssSelector(prop.getProperty("btn_loadmore_css")))){
					Reporter.log("Load More button is displayed successfully");
					//Click on load more button
					driver.findElement(By.cssSelector(prop.getProperty("btn_loadmore_css"))).click();
					Thread.sleep(2000);
					//Now get the count of total items loaded on page
					//It should be more than 85
					List<WebElement> objList  = driver.findElements(By.className("img-loaded"));
					System.out.println(objList.size());
					if (objList.size()>85){
						Reporter.log("Items loaded are more than 12 items");
					}
						else
						{
							Log.info("Items loaded are less than 12 items");
							Reporter.log("Items loaded are less than 12 items");
							bResult = false;
							takeScreenshot(driver, "ProductListingPage");
						}
					}
				}
				else{
					//Load more button is not being displayed
					Log.info("Load more button is not being displayed");
					Reporter.log("Load more button is not being displayed");
					bResult = false;
					takeScreenshot(driver, "ProductListingPage");
				}
			
	}

	
}
