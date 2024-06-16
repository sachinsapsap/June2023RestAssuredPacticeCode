package PUTAPIs;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UpdateUserTest {
	
	public static String getRandomEmail() {
		return "apiautomation"+System.currentTimeMillis()+"@gmail.com";
	}

	@Test
	public void updateUserTest() {

		User user = new User("Naveen", getRandomEmail(), "male", "active");

		baseURI = "https://gorest.co.in";

		Response postResponse = 
				given().contentType(ContentType.JSON)
				.header("Authorization", "Bearer 082d8bf459f772ca1d2babc8f780bb1d71f33914b3e52656328a68659846e1ce")
				.body(user).when().log().all().post("/public/v2/users");
		
		Integer userId = postResponse.jsonPath().get("id");
		System.out.println("User ID is " + userId);
		System.out.println("------------------------------------------------");
		
		user.setGender("female");
		user.setName("Naveen Automation Labs");
		
		Response putResponse = given().contentType(ContentType.JSON)
				.header("Authorization", "Bearer 082d8bf459f772ca1d2babc8f780bb1d71f33914b3e52656328a68659846e1ce")
				.body(user).when().log().all().put("/public/v2/users/"+userId);
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			User userResponse = mapper.readValue(putResponse.getBody().asString(), User.class);
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
