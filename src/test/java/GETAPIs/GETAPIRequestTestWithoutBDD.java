package GETAPIs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GETAPIRequestTestWithoutBDD {
	
//	Non BDD Approach

	RequestSpecification request;

	@BeforeTest
	public void setUp() {
//		Request
		RestAssured.baseURI = "https://gorest.co.in";
		request = RestAssured.given();
		request.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6");
	}

	@Test
	public void getAllUsersAPITest() {

		Response response = request.get("/public/v2/users");

//		Status Code
		int statusCode = response.statusCode();
		System.out.println("Status Code " + statusCode);

//		Assertion point
		Assert.assertEquals(statusCode, 200);

//		Status message
		String statusMessage = response.getStatusLine();
		System.out.println(statusMessage);

//		Fetch response body:
		response.prettyPrint();

//		Fetch Specific Response Header:
		String contentType = response.header("Content-Type");
		System.out.println("Response Header Content Type " + contentType);

//		Fetch Response Headers
		List<Header> headerList = response.headers().asList();
		System.out.println(headerList.size());

		for (Header h : headerList) {
			System.out.println(h.getName() + ":" + h.getValue());
		}

	}

	@Test
	public void getUserWithPathParamAPITest() {

		Response response = request.get("/public/v2/users/6914818");

//		Status Code
		int statusCode = response.statusCode();
		System.out.println("Status Code " + statusCode);

//		Assertion point
		Assert.assertEquals(statusCode, 200);

//		Status message
		String statusMessage = response.getStatusLine();
		System.out.println(statusMessage);

//		Fetch response body:
		response.prettyPrint();

	}

	@Test
	public void getUserWithQueryParamAPITest() {

		request.queryParam("name", "naveen");
		request.queryParam("status", "active");

		Response response = request.get("/public/v2/users");

//		Status Code
		int statusCode = response.statusCode();
		System.out.println("Status Code " + statusCode);

//		Assertion point
		Assert.assertEquals(statusCode, 200);

//		Status message
		String statusMessage = response.getStatusLine();
		System.out.println(statusMessage);

//		Fetch response body:
		response.prettyPrint();

	}

	@Test
	public void getUserWithQueryParamWithHashMapAPITest() {

		Map<String, String> queryParamsMap = new HashMap<String, String>();
		queryParamsMap.put("name", "naveen");
		queryParamsMap.put("status", "active");

		request.queryParams(queryParamsMap);
		Response response = request.get("/public/v2/users");

//		Status Code
		int statusCode = response.statusCode();
		System.out.println("Status Code " + statusCode);

//		Assertion point
		Assert.assertEquals(statusCode, 200);

//		Status message
		String statusMessage = response.getStatusLine();
		System.out.println(statusMessage);

//		Fetch response body:
		response.prettyPrint();

	}

}
