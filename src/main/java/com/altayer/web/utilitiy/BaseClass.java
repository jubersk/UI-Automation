package com.altayer.web.utilitiy;

import java.awt.Robot;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class BaseClass {
	
	public static WebDriverWait wait;
	public static boolean bResult;
	public static Properties prop = null; 
	public static Properties messageValdition = null; 
	public static Properties api = null;
	public static Properties docsprop = null; 
	//public static SoftAssert softas = new SoftAssert();
	public static Robot object;
	public static ExtentReports extent;
	public static ExtentTest logger;
	
	//public static WebDriver driver;
	private static String rootPath = "";
	private static String navigateURL = "";

	/*public BaseClass(WebDriver driver){
		
		BaseClass.bResult = true;
		extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/ExtentReport.html", true);
		//softas.assertTrue(true);
		wait = new WebDriverWait(driver, 120);
		// Tell webdriver to wait
		FluentWait wait = new FluentWait(driver)  
				.withTimeout(30, TimeUnit.SECONDS)  
				.pollingEvery(2, TimeUnit.SECONDS)  
				.ignoring(NoSuchElementException.class); 
		if(prop==null){
			prop = new Properties();
			try{
				FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"\\ElementLocators.properties");
				prop.load(fs);

			}catch (Exception e){
				e.printStackTrace();
			}

		}

		if(api==null){
			api = new Properties();
			try{
				FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"\\RestAPIs.properties");
				api.load(fs);

			}catch (Exception e){
				e.printStackTrace();
			}

		}

		
		

	}*/

	
	/*public static  WebDriver OpenBrowser(int iTestCaseRow) throws Exception{
		String sBrowserName;


		try{
			sBrowserName = ExcelUtils.getCellData(iTestCaseRow, ConstantOnlineReg.Col_Browser);
			System.out.println(sBrowserName);
		//	Dictionary<String, String> dict = new Hashtable<String, String>();
			//dict = UtilityClass.LoadAppSettings();
			Path currentRelativePath = Paths.get("");
			rootPath = currentRelativePath.toAbsolutePath().toString() + "\\";
			System.out.println(rootPath);
			navigateURL = ExcelUtils.getCellData(iTestCaseRow, ConstantOnlineReg.Col_URL);
					//UtilityClass.GetAppSetttings("NavigateURL",dict);

			if(sBrowserName.equalsIgnoreCase("Mozilla")){
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
			else if (sBrowserName.equalsIgnoreCase("Chrome")){
				//Kill all the chrome processes
				String driverPath = rootPath + "Drivers\\";
				System.setProperty("webdriver.chrome.driver", driverPath+ "chromedriver.exe");
				//driver = new HtmlUnitDriver();
				driver = new ChromeDriver();
				Log.info("New driver instantiated");
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				Log.info("Implicit wait applied on the driver for 20 seconds");
				driver.get(navigateURL);
				Log.info("Web application launched successfully");
			} 
			else if (sBrowserName.equalsIgnoreCase("IE")){
				String driverPath = rootPath + "Drivers\\";
				System.setProperty("webdriver.ie.driver", driverPath+"IEDriverServer.exe");
				//Runtime.getRuntime().exec("taskkill /IM IEDriverServer.exe"); 
				driver = new InternetExplorerDriver();
				Log.info("New driver instantiated");
				//		driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
				Log.info("Implicit wait applied on the driver for 40 seconds");
				//	driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
				driver.get(navigateURL);
				Log.info("Web application launched successfully");
			}

		}catch (Exception e){
			Log.error("Class Utils | Method OpenBrowser | Exception desc : "+e.getMessage());
		}
		return driver;


	}*/
	//Function to enter the text in text box
	/*public static void fn_enterText(By locator, String textToEnter){
		try{
			Actions actions = new Actions(driver);
			actions.moveToElement(driver.findElement(locator));
			actions.click();
			actions.sendKeys(textToEnter);
			actions.build().perform();

			//driver.findElement(locator).sendKeys(textToEnter);
		}catch(Exception e){
			e.printStackTrace();

		}
	}
*/
	//Function to select the dropdown item

	/*public static void fn_selectItem(By locator, String itemToSelect) throws Exception{
		try{
			Select sdropDownItem = new Select(driver.findElement(locator));
			sdropDownItem.selectByVisibleText(itemToSelect);
			BaseClass.logger.log(LogStatus.INFO, itemToSelect + ":" +"Selected Successfully");
		}catch(Exception e){
			BaseClass.logger.log(LogStatus.INFO, "error while loading the dropdown item, " + itemToSelect);
			BaseClass.bResult = false;
			takeScreenshot(driver, "Service Selection Advanced Search Page");
			e.printStackTrace();
		}

	}*/


	

	public static boolean isElementPresent(WebDriver driver,By Locator){

		if(driver.findElements(Locator).size()>0)
		{
			return true;
		}else
		{
			return false;
		}
	}

	

/*	public static void waitAndclickUntilSelectOptionsPopulated(By locator){
		WebDriverWait Wait = new WebDriverWait(driver,40);
		driver.findElement(locator).click();
		Wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}*/


	public static String getTestCaseName(String sTestCase)throws Exception{
		String value = sTestCase;
		try{
			int posi = value.indexOf("@");
			value = value.substring(0, posi);
			posi = value.lastIndexOf(".");	
			value = value.substring(posi + 1);
			//System.out.println("Value of the getTestCaseName in class Utils" + value);
			return value;
		}catch (Exception e){
			Log.error("Class Utils | Method getTestCaseName | Exception desc : "+e.getMessage());
			throw (e);
		}
	}

		
	public static void takeScreenshot(WebDriver driver, String sTestCaseName) throws Exception{
		try{
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(ConstantOnlineReg.Path_ScreenShot + sTestCaseName +".jpg"));	
		} catch (Exception e){
			Log.error("Class Utils | Method takeScreenshot | Exception occured while capturing ScreenShot : "+e.getMessage());
			throw new Exception();
		}
	}

	public static void deleteScreenshot()
	{
		try {
			Files.deleteIfExists(Paths.get(ConstantOnlineReg.Path_ScreenShot + ".jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String fn_getAlphabeticString_en() {
		String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 10) {
			int index = (int) (rnd.nextFloat() * CHARS.length());
			salt.append(CHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;

	}

	public static String fn_getNumber() {
		String CHARS = "123456789";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 5) {
			int index = (int) (rnd.nextFloat() * CHARS.length());
			salt.append(CHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;

	}
	public static String fn_getAlphabeticString_ar() {
		String CHARS = "أ ب ت ث ج ح خ د ذ ر ز س ش ص ض ط ظ ع غ ف ق ك ل م ن هـ و ي ";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 10) {
			int index = (int) (rnd.nextFloat() * CHARS.length());
			salt.append(CHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;

	}



}
