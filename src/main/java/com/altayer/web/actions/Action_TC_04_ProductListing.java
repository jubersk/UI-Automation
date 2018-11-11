/*package com.altayer.web.actions;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Reporter;
import com.altayer.web.utilitiy.BaseClass;
import com.altayer.web.utilitiy.Log;


public class Action_TC_04_ProductListing extends BaseClass{
	// iTestcaseRow is the row number of our Testcase name in the Test Data sheet
	// iTestcaseRow is passed as an Argument to this method, so that it can used inside this method

public Action_TC_04_ProductListing(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public static void Execute(int iTestCaseRow) throws Exception{
		driver.get("https://www.ounass.ae/clothing/");
		BaseClass.bResult = true;
		
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
				Thread.sleep(1000);
				
				if(BaseClass.isElementPresent(By.cssSelector(prop.getProperty("btn_loadmore_css")))){
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
							BaseClass.bResult = false;
							takeScreenshot(driver, "ProductListingPage");
						}
					}
				}
				else{
					//Load more button is not being displayed
					Log.info("Load more button is not being displayed");
					Reporter.log("Load more button is not being displayed");
					BaseClass.bResult = false;
					takeScreenshot(driver, "ProductListingPage");
				}
			}
			//Verify Product Listing Page
			//List<WebElement> objList  = driver.findElements(By.cssSelector("img-loaded"));
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(prop.getProperty("btn_Amber_css"))));

				
	}


*/