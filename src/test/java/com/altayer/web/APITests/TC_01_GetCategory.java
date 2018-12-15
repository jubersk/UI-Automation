package com.altayer.web.APITests;

import java.util.regex.Pattern;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.altayer.web.utilitiy.BaseClass;
import com.altayer.web.utilitiy.Log;
import com.relevantcodes.extentreports.LogStatus;
import APIAutomation.Action_TC01_CategoryNode;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;


public class TC_01_GetCategory extends BaseClass {

	public static int statusCode;
	public static Response response;
	public static String serviceAPI = "https://www.ounass.ae/categories/getcategorytree/";
	public static SoftAssert softas = new SoftAssert();
	public static boolean bResult; 
	public static String apiURL = null;

	@BeforeClass
	public void init() throws Exception {

		logger = extent.startTest("Category API Tests");
		bResult = true;
		apiURL = "https://www.ounass.ae/categories/getcategorytree/";

	}
	@Test
	public void getPlaceAPI() throws Exception
	{
		try{

			logger.log(LogStatus.INFO, "getCategory API Tests Started::");
			Log.info("getCategory API Tests Started::");
			Reporter.log("getCategory API Tests Started::");
			//Tests Actual Steps
			fn_VerifyGetCateroy();

			if(bResult==true){
				Reporter.log("getCategory API Tests verified successfully");
				logger.log(LogStatus.PASS, "getCategory API Tests verified successfully");

			}else{

				Log.info("getCategory API Tests");
				Reporter.log("getCategory API Tests");
				logger.log(LogStatus.FAIL, "getCategory API Tests Failed");
				throw new Exception("getCategory API Tests Failed");

			}



		}catch (Exception e){
			Log.info("getCategory API Tests Failed");
			Reporter.log("getCategory API Tests Failed");
			Log.error(e.getMessage());
			throw (e);
		}

	}
	public static void fn_VerifyGetCateroy() throws Exception{

		statusCode= RestAssured.get(serviceAPI).statusCode();

		if (statusCode==200){
			Log.info("Response Sucess::" + statusCode);
			logger.log(LogStatus.PASS, "Response Sucess::" + statusCode);
			Reporter.log("Response Sucess::" + statusCode);

			//***************************************First node should have more than 10 categories on children data node.***********
			ResponseBody jsonresponseBody= RestAssured.get(apiURL).getBody();
			
			Action_TC01_CategoryNode objCategoryNode = jsonresponseBody.as(Action_TC01_CategoryNode.class);

			//softas.assertTrue(objCategoryNode.getChildren().size()>=10);
			if(objCategoryNode.getChildren().size()>=10){
				Log.info("Verifying First Node::Has more than 10 Categories");
				logger.log(LogStatus.PASS, "Verifying First Node::Has more than 10 Categories");
				Reporter.log("Verifying First Node::Has more than 10 Categories");
			}
			else{
				Log.info("Verifying First Node::Has less than 10 Categories");
				logger.log(LogStatus.FAIL, "Verifying First Node::Has less than 10 Categories");
				Reporter.log("Verifying First Node::Has less than 10 Categories");
				bResult = false;
			}

			verifyNode(objCategoryNode);	


		}
		else{
			Assert.fail("Response Failed::"+statusCode);
			Log.info("Response Failed::" + statusCode);
			logger.log(LogStatus.FAIL, "Response failed::" + statusCode);
			Reporter.log("Response failed::" + statusCode);
			bResult = false;


		}

		//	softas.assertAll();	
	}


	private static void verifyNode(Action_TC01_CategoryNode categoryNode) throws Exception {


		//	softas.assertNotNull(categoryNode.getCategoryId(),"CategoryID");
		if(categoryNode.getCategoryId()!= null){
			Log.info("Category ID Found::" + categoryNode.getCategoryId());
			//logger.log(LogStatus.PASS, "Category ID Found::" + categoryNode.getCategoryId());
			Reporter.log("Category ID Found::" + categoryNode.getCategoryId());
		}

		else{
			Log.info("Blank id for Category node::" + categoryNode.getCategoryId());
			logger.log(LogStatus.FAIL, "Blank id for Category node::" + categoryNode.getCategoryId());
			Reporter.log("Blank id for Category node::" + categoryNode.getCategoryId());
			bResult = false;
		}

		//softas.assertTrue(StringUtils.isNotBlank(categoryNode.getUrl()),"Blank CategoryID for category id"+categoryNode.getCategoryId() );

		if(categoryNode.getUrl()!=""){
			Log.info("URL Found For Category ID::" + categoryNode.getCategoryId());
			//logger.log(LogStatus.PASS, "URL Found For Category ID::" + categoryNode.getCategoryId());
			Reporter.log("URL Found For Category ID::" + categoryNode.getCategoryId());
		}
		else{
			Log.info("URL is blank For Category ID"+categoryNode.getCategoryId());
			logger.log(LogStatus.FAIL, "URL is blank For Category ID"+categoryNode.getCategoryId());
			Reporter.log("URL is blank For Category ID"+categoryNode.getCategoryId());
			bResult = false;
		}

		//softas.assertTrue(StringUtils.isNotBlank(categoryNode.getName()),"name");

		if(categoryNode.getName()!=""){

			Log.info("name Found For Category ID::" + categoryNode.getCategoryId());
			//logger.log(LogStatus.PASS, "name Found For Category ID::" + categoryNode.getCategoryId());
			Reporter.log("name Found For Category ID::" + categoryNode.getCategoryId());
		}
		else{
			Log.info("name is blank For Category ID"+categoryNode.getCategoryId());
			logger.log(LogStatus.FAIL, "name is blank For Category ID"+categoryNode.getCategoryId());
			Reporter.log("name is blank For Category ID"+categoryNode.getCategoryId());
			bResult = false;

		}



		if((categoryNode.getLevel()==3) && (categoryNode.getImage())!=""){
			//	softas.assertTrue(StringUtils.isNotBlank(categoryNode.getImage()));
			Log.info("image Found For Category ID::" + categoryNode.getCategoryId());
			//logger.log(LogStatus.PASS, "image Found For Category ID::" + categoryNode.getCategoryId());
			Reporter.log("image Found For Category ID::" + categoryNode.getCategoryId());
			bResult = false;

		}

		else{	//softas.assertFalse(categoryNode.getIncludeInMenu());
			logger.log(LogStatus.FAIL, "Should contains and image"+ "Category ID::" + categoryNode.getCategoryId() + "Level" + categoryNode.getLevel());
			bResult = false;

		}


		if(isLastLevel(categoryNode)){
			//softas.assertTrue(categoryNode.getAvailableSortBy().size()>=2);

			if((categoryNode.getAvailableSortBy().size()>=2)){
				if(categoryNode.getAvailableSortBy().contains("shoeSizeCode") && (categoryNode.getAvailableSortBy().contains("designer")) ){
					//logger.log(LogStatus.PASS, "Last level of categories availableSortBy has minimum of 2 sub nodes"+categoryNode.getCategoryId());
				}
				/*	softas.assertTrue(categoryNode.getAvailableSortBy().contains("shoeSizeCode"));
				softas.assertTrue(categoryNode.getAvailableSortBy().contains("designer"));
				 */
				else{
					logger.log(LogStatus.FAIL, "Last level of categories availableSortBy has less than 2 sub nodes::Category ID::" + categoryNode.getCategoryId() + "Level" + categoryNode.getLevel());
					bResult = false;
				}

			}



		}


		if(isFeatureEditLink(categoryNode.getFeatureLink())){

			if((categoryNode.getAvailableSortBy().size()>=2)){
				if(categoryNode.getLevel()==3){
					//logger.log(LogStatus.PASS, "featureLink node with /edits/* is only in 3rd level category "+categoryNode.getCategoryId());
				}
				//softas.assertEquals(categoryNode.getLevel(), 3);


			}

			else{
				logger.log(LogStatus.FAIL, "featureLink node with /edits/* should not be in other level except 3rd level category"+categoryNode.getCategoryId());
				bResult = false;

			}

		}

		if(CollectionUtils.isNotEmpty(categoryNode.getChildren())){
			categoryNode.getChildren().forEach(childNode -> validateChild(childNode));
		}

	}
	
	private static void validateChild(Action_TC01_CategoryNode childNode) {
		{
			try {
				verifyNode(childNode);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


	private static boolean isLastLevel(Action_TC01_CategoryNode categoryNode) {
		return CollectionUtils.isEmpty(categoryNode.getChildren());
		
	}

	private static boolean isFeatureEditLink(String featureLink) {
		return StringUtils.isNotBlank(featureLink) && Pattern.matches("\\/edit\\/\\w*", featureLink);

	}


}
