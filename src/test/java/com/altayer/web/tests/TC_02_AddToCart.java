package com.altayer.web.tests;

import org.testng.annotations.Test;
import org.testng.internal.ExitCode;

import com.altayer.web.utilitiy.BaseClass;
import com.altayer.web.utilitiy.ConstantOnlineReg;
import com.altayer.web.utilitiy.ExcelUtils;
import com.altayer.web.utilitiy.Log;
import com.google.common.base.Function;
import com.relevantcodes.extentreports.LogStatus;
import java.io.FileInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;



public class TC_02_AddToCart extends BaseClass{
	public static RemoteWebDriver driver = null;
	private static String rootPath = "";
	public static WebDriverWait wait;
	public static Properties prop = null; 
	private String sTestCaseName;
	private int iTestCaseRow;
	public static boolean bResult;
	private static String navigateURL = "";



	// This method is to navigate 
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
		/*String driverPath = rootPath + "Drivers\\";
		System.setProperty("webdriver.gecko.driver", driverPath+ "geckodriver.exe");
		driver =new FirefoxDriver();*/
		Log.info("New driver instantiated");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Log.info("Implicit wait applied on the driver for 20 seconds");
		driver.get("https://www.nisnass.ae/");
		logger = extent.startTest(sTestCaseName);
		Log.info("Web application launched successfully");
		logger.log(LogStatus.INFO, "Web application launched successfully");
		wait = new WebDriverWait(driver, 120);
	}

	//Verify Add To Cart Tests
	@Test
	public void AddToCart() throws Exception {
		try{

			logger.log(LogStatus.INFO, "NISNASS Add to Cart Test Started");
			Log.info("NISNASS Add to Cart Test Started");
			Reporter.log("NISNASS Add to Cart Test Started");
			//Tests Actual Steps
			fn_AddToCart();
			if(bResult==true){
				Reporter.log("NISNASS Add to Cart Tests verified successfully");
				// If the value of boolean variable is True, then your test is complete pass and do this
				ExcelUtils.setCellData("Pass", iTestCaseRow, ConstantOnlineReg.Col_Result);
				ExcelUtils.writeWorkbook();

			}else{

				Log.info("NISNASS Add to Cart Tests Failed");
				Reporter.log("NISNASS Add to Cart Tests Failed");
				logger.log(LogStatus.FAIL, "NISNASS Add to Cart Tests Failed");
				throw new Exception("NISNASS Add to Cart Tests Failed");

			}



		}catch (Exception e){
			Log.info("NISNASS Add to Cart Tests Failed");
			Reporter.log("NISNASS Add to Cart Tests Failed");
			// If in case you got any exception during the test, it will mark your test as Fail in the test result sheet
			ExcelUtils.setCellData("Fail", iTestCaseRow, ConstantOnlineReg.Col_Result);
			ExcelUtils.writeWorkbook();
			//Take Screenshot
			takeScreenshot(driver, sTestCaseName);
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
		//driver.close();
		driver.quit();
	}


	public static void fn_AddToCart() throws Exception{
		List<String> items = new ArrayList<String>();

		//Add First Item
		String urlItem1 = "https://www.nisnass.ae/shop-quench-intense-hydration-mask-1-sheet-for-womens-212054000.html";
		fnAddToBag(urlItem1,items);
		
		//Add Second Item
		String urlItems2 = "https://www.nisnass.ae/shop-ciaran-point-toe-pumps-for-womens-212341719.html";
		fnAddToBag(urlItems2,items);
		
		//Add Third Item
		String urlItems3 = "https://www.nisnass.ae/shop-embellishment-kamma-block-heels-for-womens-211870808.html";
		fnAddToBag(urlItems3,items);
		

		//Click on bag and check that item is added to cart
		driver.findElement(By.cssSelector("i.icon-bag")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".CartTotal-secureCheckout")));

		//Read all the items from cart table and match with above title
		//get the child object of class
		List<WebElement> list = driver.findElements(By.xpath("//div[@class='CartItem-name']"));

		List<String> strActual = new ArrayList<String>();

		for(WebElement element:list)
		{

			strActual.add(element.getText());

		}

		try{
			Assert.assertEquals(items, strActual);
			Reporter.log("All the items addedd in cart successfully");

		}catch(Throwable e){
			System.err.println("Added items are not available in cart "+e.getMessage());
			Log.info("Added items are not available in cart");
			Reporter.log("Added items are not available in cart");
			bResult = false;
			takeScreenshot(driver, "Cart Page");
		}

		//softas.assertAll();


	}
	
	public static void fnAddToBag(String url,List<String> items) throws Exception{
		//Add First Item
				driver.get(url);
				//get the name of item
				String itemTitle = driver.findElement(By.cssSelector(".Brief-title")).getText();
				//Check the item found on page and its ready to add to cart & Read the name of item
				//Then Add to Cart
				String strOutOfStock =  driver.findElement(By.cssSelector(".AddToBag")).getText();
				System.out.println(strOutOfStock);
				if (strOutOfStock.contains("ADD")){

					items.add(itemTitle);
					//Click on size One
					driver.findElement(By.cssSelector(".SizeSelection-option")).click();
					driver.findElement(By.cssSelector(".AddToBag")).click();
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.CartTotal-secureCheckout > span")));
					
				}
				else{
					//throw new SkipException(itemTitle + "->Item is out of stock or Add to Bag is not enabled, Skipping this exception");
					//System.err.println(itemTitle + "->Item is out of stock or Add to Bag button is not enabled");
					Log.info(itemTitle + "->Item is out of stock or Add to Bag button is not enabled");
					Reporter.log(itemTitle + "->Item is out of stock or Add to Bag button is not enabled");
					bResult = false;
					takeScreenshot(driver, "Add To Bag Page");
					
					

				}

		
	}
}
