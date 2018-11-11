package APIAutomation;

import java.util.regex.Pattern;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.altayer.web.utilitiy.BaseClass;
import com.altayer.web.utilitiy.Log;
import com.relevantcodes.extentreports.LogStatus;
import bsh.StringUtil;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;


public class TestAPI extends BaseClass {
	public TestAPI(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public static int statusCode;
	public static Response response;
	public static String serviceAPI = "https://www.ounass.ae/categories/getcategorytree/";
	public static SoftAssert softas = new SoftAssert();
		@Test
		public void getPlaceAPI() throws Exception
		{
			
				statusCode= RestAssured.get(serviceAPI).statusCode();
		
				if (statusCode==200){
					Log.info("Response Sucess::" + statusCode);
//					logger.log(LogStatus.PASS, "Response Sucess::" + statusCode);
					Reporter.log("Response Sucess::" + statusCode);
					
					//***************************************First node should have more than 10 categories on children data node.***********
					ResponseBody jsonresponseBody= RestAssured.get("https://www.ounass.ae/categories/getcategorytree/").getBody();
					CategoryNode objCategoryNode = jsonresponseBody.as(CategoryNode.class);
					
					softas.assertTrue(objCategoryNode.getChildren().size()>=10);
					
					
					verifyNode(objCategoryNode);	
					
				
				}
				else{
					Assert.fail("Response Failed::"+statusCode);
					Log.info("Response Failed::" + statusCode);
					//logger.log(LogStatus.FAIL, "Response failed::" + statusCode);
					Reporter.log("Response failed::" + statusCode);
					
				}
			
			softas.assertAll();	
		}
		
		private void verifyNode(CategoryNode categoryNode) {
			
		
			softas.assertNotNull(categoryNode.getCategoryId(),"CategoryID");
			softas.assertTrue(StringUtils.isNotBlank(categoryNode.getUrl()),"Blank CategoryID for category id"+categoryNode.getCategoryId() );
			softas.assertTrue(StringUtils.isNotBlank(categoryNode.getUrl()),"Blank url for category id"+categoryNode.getCategoryId() );
			
			softas.assertTrue(StringUtils.isNotBlank(categoryNode.getName()),"name");
				
			if(categoryNode.getLevel()!=3){
				softas.assertTrue(StringUtils.isNotBlank(categoryNode.getImage()));
			}
			else{
				softas.assertFalse(categoryNode.getIncludeInMenu());
			}
			
			if(isLastLevel(categoryNode)){
				softas.assertTrue(categoryNode.getAvailableSortBy().size()>=2);
				softas.assertTrue(categoryNode.getAvailableSortBy().contains("shoeSizeCode"));
				softas.assertTrue(categoryNode.getAvailableSortBy().contains("designer"));
			}
			
			if(isFeatureEditLink(categoryNode.getFeatureLink())){
				softas.assertEquals(categoryNode.getLevel(), 3);
				
			}
			
			if(CollectionUtils.isNotEmpty(categoryNode.getChildren())){
				categoryNode.getChildren().forEach(this::verifyNode);
			}
			
		  }
		
		private boolean isLastLevel(CategoryNode categoryNode) {
		    return CollectionUtils.isEmpty(categoryNode.getChildren());
		  }
		
		private boolean isFeatureEditLink(String featureLink) {
	        return StringUtils.isNotBlank(featureLink) && Pattern.matches("\\/edit\\/\\w*", featureLink);
	        
	    }
		
		
}
