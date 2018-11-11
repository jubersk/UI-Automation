package com.altayer.web.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Reporter;
import com.altayer.web.utilitiy.BaseClass;
import com.altayer.web.utilitiy.ConstantOnlineReg;
import com.altayer.web.utilitiy.ExcelUtils;
import com.altayer.web.utilitiy.Log;
import org.openqa.selenium.Keys;

public class Action_TC_01_UserRegistration extends BaseClass{
	// iTestcaseRow is the row number of our Testcase name in the Test Data sheet
	// iTestcaseRow is passed as an Argument to this method, so that it can used inside this method

	public Action_TC_01_UserRegistration(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public static void Execute(int iTestCaseRow) throws Exception{
		BaseClass.bResult = true;
			fn_UserRegistration(iTestCaseRow);
			/*if(BaseClass.bResult==true){
				// If the value of boolean variable is True, then your test is complete pass and do this
				ExcelUtils.setCellData("Pass", iTestCaseRow, ConstantOnlineReg.Col_Result);
				ExcelUtils.writeWorkbook();
				//BaseClass.driver.quit();

			}else{
				BaseClass.logger.log(LogStatus.FAIL, "Login Failed");
				ExcelUtils.setCellData("Fail", iTestCaseRow, ConstantOnlineReg.Col_Result);
				ExcelUtils.writeWorkbook();
				throw new Exception("Test Case Failed because of Verification");

			}*/
		

		//Delete the existing screenshots
		//Utils.deleteScreenshot();
	}
	private static void fn_UserRegistration(int iTestCaseRow) throws Exception{
		if(BaseClass.isElementPresent(By.cssSelector(prop.getProperty("lnk_logo")))){
			Log.info("NISNASS HP Launched");
			Reporter.log("NISNASS HP Launched");
			//Click Account Link to SignUp
			driver.findElement(By.cssSelector(prop.getProperty("btn_Acount_css"))).click();
		
			
			//Verify that popup opened successfully
			if(BaseClass.isElementPresent(By.cssSelector(prop.getProperty("btn_SignUP")))){
				//check the individual fields on the page
					Reporter.log("Sigup Popup opened sucessfully");
					driver.findElement(By.cssSelector(prop.getProperty("btn_SignUP"))).click();
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(prop.getProperty("txt_FirstName_css"))));
					fn_RegistrationDetail(iTestCaseRow);
				}
			else{
				Log.info("Sigup Popup Does not opened");
				Reporter.log("Sigup Popup Does not opened");
				BaseClass.bResult = false;
				takeScreenshot(driver, "Sigup Popup");

			}

		}
		else{
			Log.info("NISNASS HP Does not Launched Successfully");
			Reporter.log("NISNASS HP Does not Launched Successfully");
			BaseClass.bResult = false;
			takeScreenshot(driver, "Login");
		}
	}


	public static void fn_RegistrationDetail(int iTestCaseRow)throws Exception{

		driver.findElement(By.cssSelector(prop.getProperty("txt_FirstName_css"))).clear();
		driver.findElement(By.cssSelector(prop.getProperty("txt_FirstName_css"))).sendKeys (fn_getAlphabeticString_en());
		//(ExcelUtils.getCellData(iTestCaseRow,ConstantOnlineReg.Col_FirstName));
		
		driver.findElement(By.cssSelector(prop.getProperty("txt_LastName_css"))).clear();
		driver.findElement(By.cssSelector(prop.getProperty("txt_LastName_css"))).sendKeys(fn_getAlphabeticString_en());
		
		driver.findElement(By.cssSelector(prop.getProperty("txt_Email_css"))).clear();
		driver.findElement(By.cssSelector(prop.getProperty("txt_Email_css"))).sendKeys(fn_getAlphabeticString_en() + "@" + "hotmail.com");
		
		driver.findElement(By.cssSelector(prop.getProperty("txt_Password_css"))).clear();
		driver.findElement(By.cssSelector(prop.getProperty("txt_Password_css"))).sendKeys(fn_getAlphabeticString_en());
		
		driver.findElement(By.cssSelector(prop.getProperty("txt_PhoneNumber_css"))).clear();
		driver.findElement(By.cssSelector(prop.getProperty("txt_PhoneNumber_css"))).sendKeys(ExcelUtils.getCellData(iTestCaseRow,ConstantOnlineReg.Col_PhoneNumber));
		
		//Click signup button
		driver.findElement(By.cssSelector(prop.getProperty("btn_CreateAccount"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(prop.getProperty("lnk_MyAccount_css"))));
		driver.findElement(By.cssSelector(prop.getProperty("lnk_MyAccount_css"))).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(prop.getProperty("txt_MyAccount_css")))));
		//Verify that verification code page is displayed
		if(BaseClass.isElementPresent(By.cssSelector(prop.getProperty("txt_MyAccount_css")))){
		
			Log.info("User Loggin Sucess");
			Reporter.log("User Loggin Sucess");
			//Click on MyProfile
			driver.findElement(By.cssSelector(prop.getProperty("lnk_MyProfile_css"))).click();
			//Verify that email is non editable
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(prop.getProperty("txt_Email_css")))));
		//	String readonlyEmail = driver.findElement(By.cssSelector(prop.getProperty("txt_Email_css"))).getAttribute("disabled");
			if (driver.findElement(By.cssSelector(prop.getProperty("txt_Email_css"))).isEnabled()==false){
				Log.info("Email Field is non editable");
				Reporter.log("Email Field is non editable");
			}
				else
				{
					Log.info("Email Field should be non editable");
					Reporter.log("Email Field should be non editable");
					BaseClass.bResult = false;
					takeScreenshot(driver, "MyAccount");
				}
			
			//Update mobile number and verify
			driver.findElement(By.cssSelector(prop.getProperty("txt_PhoneNumber_css"))).sendKeys(Keys.chord(Keys.CONTROL,"a"));;
			driver.findElement(By.cssSelector(prop.getProperty("txt_PhoneNumber_css"))).sendKeys(Keys.BACK_SPACE);
			driver.findElement(By.cssSelector(prop.getProperty("txt_PhoneNumber_css"))).sendKeys(ExcelUtils.getCellData(iTestCaseRow,ConstantOnlineReg.Col_NewPhoneNumber));
			
			//Click on Update
			driver.findElement(By.cssSelector(prop.getProperty("btn_UpdateProfile_css"))).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(prop.getProperty("msg_Success_css"))));
			//check the message is showing as update success
			String strSuccessMsg = driver.findElement(By.cssSelector(prop.getProperty("msg_Success_css"))).getText();
			if (strSuccessMsg.contains("updated")){
				//Check the number is updated
				Log.info("Profile is updated successfully");
				Reporter.log("Profile is updated successfully");
				if (driver.findElement(By.cssSelector(prop.getProperty("txt_PhoneNumber_css"))).getAttribute("value").equals((ExcelUtils.getCellData(iTestCaseRow,ConstantOnlineReg.Col_NewPhoneNumber)))){
					Log.info("New mobile number is updated successfully");
					Reporter.log("New mobile number is updated successfully");
					
				}
				else {
					Log.info("Mobile number is not updated with new number");
					Reporter.log("Mobile number is not updated with new number");
					BaseClass.bResult = false;
					takeScreenshot(driver, "MyAccount");
				}
			}
			else {
				Log.info("Update Message is not displayed on Profile Update");
				Reporter.log("Update Message is not displayed on Profile Update");
				BaseClass.bResult = false;
				takeScreenshot(driver, "MyAccount");
			}
					
		}
		else{
			
			Log.info("User in not loggedin");
			Reporter.log("User in not loggedin");
			BaseClass.bResult = false;
			takeScreenshot(driver, "Login");
		}
			


		}


	}


