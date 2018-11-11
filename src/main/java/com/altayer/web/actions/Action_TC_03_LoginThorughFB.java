package com.altayer.web.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Reporter;
import com.altayer.web.utilitiy.BaseClass;
import com.altayer.web.utilitiy.Log;


public class Action_TC_03_LoginThorughFB extends BaseClass{
	// iTestcaseRow is the row number of our Testcase name in the Test Data sheet
	// iTestcaseRow is passed as an Argument to this method, so that it can used inside this method

	public Action_TC_03_LoginThorughFB(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public static void Execute(int iTestCaseRow) throws Exception{
		BaseClass.bResult = true;
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
			
		}
		
		
			fn_LoginFlow();
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
			BaseClass.bResult = false;
			takeScreenshot(driver, "ProductListingPage");
			
		}
		driver.switchTo().parentFrame();
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prop.getProperty("btn_LoginwithFacebook_css"))));
		
		//Enter FB Credentials and return to site and make sure user is logged in
		
		
	}



	}


