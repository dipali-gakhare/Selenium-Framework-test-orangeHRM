package com.orangeHRMProject.test;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.orangeHRMProject.utilities.APIUtility;
import com.orangeHRMProject.utilities.ExtendsManager;
import io.restassured.response.Response;

public class Apittest {
	@Test
	public void VerifyGetuserAPI() {
		SoftAssert softassert=new SoftAssert();
		//Step1: Define the Api endpoint 
		String endPoint="https://jsonplaceholder.typicode.com/users/1";
		ExtendsManager.logstrp("API Endpoint"+endPoint);
		//Step2: send API request
		ExtendsManager.logstrp("Sending API requst");
		Response response=APIUtility.sendgetEndpoint(endPoint);
		//Step3: validate status code
		
		ExtendsManager.logstrp("Verify the status code");
		boolean statusCode=APIUtility.validateStatusCode(response, 200);
		softassert.assertTrue(statusCode,"Status code as Expected");
		if(statusCode) {
			ExtendsManager.VerifylogscrinshotforAPI("Status code validation Passed!");
		}else {
			ExtendsManager.logfailureforAPI("Status code validation Fails!");
		}
		//Step3: verift the username
		ExtendsManager.logstrp("Verify the usename");
		String userName=APIUtility.getjsonvalue(response, "username");
		boolean UsernameID="Bret".equals(userName);
		softassert.assertTrue(UsernameID,"uername is not valid");
		if(UsernameID) {
			ExtendsManager.VerifylogscrinshotforAPI("username validation is passed!");
		}else {
			ExtendsManager.logfailureforAPI("usename validation Fails!");
		}
		//Step3: verift the Email
		ExtendsManager.logstrp("Verify the usename");
		String userEmail=APIUtility.getjsonvalue(response, "email");
		boolean userEmailID="Sincere@april.biz".equals(userEmail);
		softassert.assertTrue(UsernameID,"email is not valid");
		if(UsernameID) {
			ExtendsManager.VerifylogscrinshotforAPI("email validation is passed!");
		}else {
			ExtendsManager.logfailureforAPI("email validation Fails!");
		}
		softassert.assertAll();
}

}
