package fun;



import java.util.ArrayList;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.google.common.net.MediaType;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;



public class SampleTest extends Base {


	public void test1() {
		System.out.println("I am in test1 test method and it will pass.");
	}
	@Test
	public void jsonLogin(){

		String srtBaseURI="http://198.58.98.34:4350/api/user/login";
		String strLoginBody="{\"email\":\"pradip@zaptechsolutions.com\",\"pass\": \"pradip123#\"}";
		Response res=postwithURL(srtBaseURI,strLoginBody);
		HashMap<?,?> apik=res.jsonPath().get("data");
		String body = res.getBody().asString();
		System.out.println(apik.containsKey("token"));
		System.out.println(apik.get("token"));
		System.out.println(body);
		int intStatusCode=res.getStatusCode();
		String strContentType=res.getContentType();
//		if(intStatusCode==200 &&strContentType.contains(""+MediaType.JSON_UTF_8)) {
//			String strBaseURI4LiveApp="http://198.58.98.34:4350/api/publish/liveapp";
//
//			Response resLiveApp=postAuthenticaion(strBaseURI4LiveApp,apik.get("token").toString());
//			System.out.println("Try"+resLiveApp.getBody().asString());
//		}
		
		if(intStatusCode==200 &&strContentType.contains(""+MediaType.JSON_UTF_8)) {
			String strBaseURI4LiveApp="http://198.58.98.34:4350/api/publish/list";

			Response resLiveApp=postAuthenticaion(strBaseURI4LiveApp,apik.get("token").toString());
			System.out.println("Try"+resLiveApp.getBody().asString());
			Variables v=new Variables();
			
			//  Base
			v.type=resLiveApp.jsonPath().get("data.type").toString();
			v.user_id=resLiveApp.jsonPath().get("data.user_id").toString();
			v.id=resLiveApp.jsonPath().get("data.id").toString();
			
			System.out.println("Type: "+v.type.replace("[", "").replace("]", ""));
			System.out.println("User ID: "+v.user_id.replace("[", "").replace("]", ""));
			System.out.println("ID: "+v.id.replace("[", "").replace("]", ""));
			
			
			// Step 1
			v.application_primary_language_1=resLiveApp.jsonPath().get("data.publish_data.step1.application_primary_language_1").toString();
			v.application_name=resLiveApp.jsonPath().get("data.publish_data.step1.application_name").toString();
			v.application_primary_language_2=resLiveApp.jsonPath().get("data.publish_data.step1.application_primary_language_2").toString();
			v.application_website=resLiveApp.jsonPath().get("data.publish_data.step1.application_website").toString();
			v.application_phone_screens=resLiveApp.jsonPath().get("data.publish_data.step1.application_phone_screens").toString().split(",");
			v.application_email_address=resLiveApp.jsonPath().get("data.publish_data.step1.application_email_address").toString();
			v.application_privacy_policy=resLiveApp.jsonPath().get("data.publish_data.step1.application_privacy_policy").toString();
			v.application_short_description=resLiveApp.jsonPath().get("data.publish_data.step1.application_short_description").toString();
			v.application_full_description=resLiveApp.jsonPath().get("data.publish_data.step1.application_full_description").toString();
			v.application_icon=resLiveApp.jsonPath().get("data.publish_data.step1.application_icon").toString();
	
			

			ArrayList<?> hashStep1=resLiveApp.jsonPath().get("data.publish_data.step1.application_primary_language_1");
			//String body = res.getBody().asString();
			System.out.println("Application Primary Language: "+v.application_primary_language_1.replace("[", "").replace("]", ""));
			System.out.println("App Name: "+v.application_name.replace("[", "").replace("]", ""));
			System.out.println("application_primary_language_2: "+v.application_primary_language_2.replace("[", "").replace("]", ""));
			System.out.println("application_website: "+v.application_website.replace("[", "").replace("]", ""));
			System.out.println("application_phone_screens: "+v.application_phone_screens[0].replace("[", "").replace("]", ""));
			System.out.println("application_email_address: "+v.application_email_address.replace("[", "").replace("]", ""));
			System.out.println("application_privacy_policy: "+v.application_privacy_policy.replace("[", "").replace("]", ""));
			System.out.println("application_short_description: "+v.application_short_description.replace("[", "").replace("]", ""));
			System.out.println("application_full_description: "+v.application_full_description.replace("[", "").replace("]", ""));
			System.out.println("application_icon: "+v.application_icon.replace("[", "").replace("]", ""));
			
			
			
			//System.out.println("App Name: "+v.application_name.replace("[", "").replace("]", ""));
			
//			String[] arr=hashStep1.get(0).toString().split(",");
//			for(int i=0;i<arr.length;i++) {
//				System.out.println("Step 1: "+arr[i]);
//			}
		}

	}

	//@Test(expectedExceptions=ElementNotVisibleException.class)
	public Response postwithURL(String baseURI,String strLoginBody) {
		Response response=null;
		RestAssured.baseURI =baseURI;

		response = RestAssured.given()
				.contentType("application/json").
				body(strLoginBody).
				when().
				post("");

		return response;
	}
	public Response postAuthenticaion(String baseURI1,String strHeader) {
		Response response=null;
		RestAssured.baseURI =baseURI1;

		response = RestAssured.given().header("Authorization",strHeader)
				.contentType("application/json").

				when().
				post("");
		System.out.println(response.getHeaders());
		return response;
	}
	public void test2() {
		System.out.println("I am in test2 test method and it will fail.");
	}

	//@Test
	public void test3() {
		throw new SkipException("Skipping the test3 test method!");
	}

	private int i=0;
	//@Test(successPercentage=60, invocationCount=5)
	public void test4() {
		i++;
		System.out.println("test4 test method, invocation count: " + i);
		if (i == 1 || i == 2) {
			System.out.println("test4 failed!");
			Assert.assertEquals(i, 8);
		}
	}
}
