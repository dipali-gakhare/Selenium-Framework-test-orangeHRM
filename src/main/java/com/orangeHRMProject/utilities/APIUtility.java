package com.orangeHRMProject.utilities;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class APIUtility {
	
	public static Response sendgetEndpoint(String Endpoint) {
		return RestAssured.get(Endpoint);
		
	}
	
	public static Response sendpostRequest(String Endpoint,String payload) {
		return RestAssured.given().header("content-type","aplication/json")
				.body(payload)
				.post();
		 }
	public static boolean validateStatusCode(Response response,int statuscode) {
		return response.getStatusCode()==statuscode;
	}
	
	public static String getjsonvalue(Response response,String value) {
		return response.jsonPath().getString(value);
	}

}
