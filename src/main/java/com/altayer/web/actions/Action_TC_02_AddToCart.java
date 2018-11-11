/*package com.altayer.web.actions;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.SkipException;
import com.altayer.web.utilitiy.BaseClass;
import com.altayer.web.utilitiy.Log;




// This is called Modularization, when we club series of actions in to one Module

public class Action_TC_02_AddToCart extends BaseClass{
	// iTestcaseRow is the row number of our Testcase name in the Test Data sheet
	// iTestcaseRow is passed as an Argument to this method, so that it can used inside this method

	public Action_TC_02_AddToCart(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public static void Execute(int iTestCaseRow) throws Exception{
		//BaseClass.bResult = true;
		//driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		//Delete the existing screenshots
		//Utils.deleteScreenshot();
		List<String> items = new ArrayList<String>();
		
		//Add First Item
		driver.get("https://www.nisnass.ae/shop-quench-intense-hydration-mask-1-sheet-for-womens-212054000.html");
	       //Check the item found on page and its ready to add to cart & Read the name of item
	        //Then Add to Cart
		 String strOutOfStock =  driver.findElement(By.cssSelector(".AddToBag")).getText();
		 if (strOutOfStock.contains("Out of stock")){
			 
			 throw new SkipException("1st Item is out of stock, Skipping this exception");
		 }
			 else{
	        
	        String itemTitle = driver.findElement(By.cssSelector(".Brief-title")).getText();
	        items.add(itemTitle);
	        //Click on size One
	        driver.findElement(By.cssSelector(".SizeSelection-option")).click();
	        driver.findElement(By.cssSelector(".AddToBag")).click();
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.CartTotal-secureCheckout > span")));
		 }
		 
		 
	        //Add Second item
	        driver.navigate().to("https://www.nisnass.ae/shop-ciaran-point-toe-pumps-for-womens-212341719.html");
	        
	        String strOutOfStock1 =  driver.findElement(By.cssSelector(".AddToBag")).getText();
	        
	        if (strOutOfStock1.contains("Out of stock")){
				 
				 throw new SkipException("1st Item is out of stock, Skipping this exception");
			 }
				 else{
	        String itemTitle1 = driver.findElement(By.cssSelector(".Brief-title")).getText();
	        items.add(itemTitle1);
	        //select size
	        driver.findElement(By.cssSelector("div.SizeSelection-optionContainer:nth-child(8)")).click();
	        
	        driver.findElement(By.cssSelector(".AddToBag")).click();
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.CartTotal-secureCheckout > span")));
				 }
	        //Add third Item
	        driver.navigate().to("https://www.nisnass.ae/shop-embellishment-kamma-block-heels-for-womens-211870808.html");
	        
	        String strOutOfStock2 =  driver.findElement(By.cssSelector(".Brief-title")).getText();
	        if (strOutOfStock2.contains("Out of stock")){
				 
				 throw new SkipException("1st Item is out of stock, Skipping this exception");
			 }
				 else{
	        String itemTitle2 = driver.findElement(By.cssSelector(".Brief-title")).getText();
	        items.add(itemTitle2);
	        //select size
	        driver.findElement(By.cssSelector("div.SizeSelection-optionContainer:nth-child(7)")).click();
	        
	        driver.findElement(By.cssSelector(".AddToBag")).click();
	      
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.CartTotal-secureCheckout > span")));
				 }
	        
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
				BaseClass.bResult = false;
				takeScreenshot(driver, "Cart Page");
	        }
	       
		//softas.assertAll();
		

	}
}*/