package com.user.api;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateUserTestWithPOJOLombok {
	
	public static String getRandomEmail() {
		return "apiautomation"+System.currentTimeMillis()+"@gmail.com";
	}

	@Test
	public void createUserTest() {

		baseURI = "https://gorest.co.in";

		User user = new User("Naveen", getRandomEmail(), "male", "active");

		Response postResponse = 
				given().contentType(ContentType.JSON)
				.header("Authorization", "Bearer 082d8bf459f772ca1d2babc8f780bb1d71f33914b3e52656328a68659846e1ce")
				.body(user).when().post("/public/v2/users");
		
		Integer userId = postResponse.jsonPath().get("id");
		System.out.println("User ID is " + userId);

		Response getResponse = given().contentType(ContentType.JSON)
				.header("Authorization", "Bearer 082d8bf459f772ca1d2babc8f780bb1d71f33914b3e52656328a68659846e1ce")
				.when().get("/public/v2/users/" + userId);

		ObjectMapper mapper = new ObjectMapper();
		try {
			User userResponse = mapper.readValue(getResponse.getBody().asString(), User.class);
			System.out.println(userResponse.getId() + " " + userResponse.getName() + " " + userResponse.getEmail() + " "
					+ userResponse.getGender() + " " + userResponse.getStatus());
			
			Assert.assertEquals(userId, userResponse.getId());
			Assert.assertEquals(user.getName(), userResponse.getName());
			Assert.assertEquals(user.getEmail(), userResponse.getEmail());

		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Test
	public void createUserWithBuilderPatternTest() {

		baseURI = "https://gorest.co.in";

		User user=new User.UserBuilder().name("Naveen")
		.email(getRandomEmail())
		.gender("male")
		.status("active")
		.build();

		Response postResponse = 
				given().contentType(ContentType.JSON)
				.header("Authorization", "Bearer 082d8bf459f772ca1d2babc8f780bb1d71f33914b3e52656328a68659846e1ce")
				.body(user).when().post("/public/v2/users");
		
		Integer userId = postResponse.jsonPath().get("id");
		System.out.println("User ID is " + userId);

		Response getResponse = given().contentType(ContentType.JSON)
				.header("Authorization", "Bearer 082d8bf459f772ca1d2babc8f780bb1d71f33914b3e52656328a68659846e1ce")
				.when().get("/public/v2/users/" + userId);

		ObjectMapper mapper = new ObjectMapper();
		try {
			User userResponse = mapper.readValue(getResponse.getBody().asString(), User.class);
			System.out.println(userResponse.getId() + " " + userResponse.getName() + " " + userResponse.getEmail() + " "
					+ userResponse.getGender() + " " + userResponse.getStatus());
			
			Assert.assertEquals(userId, userResponse.getId());
			Assert.assertEquals(user.getName(), userResponse.getName());
			Assert.assertEquals(user.getEmail(), userResponse.getEmail());

		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
