package DELETEAPIs;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import PUTAPIs.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class DeleteUserTest {

	public static String getRandomEmail() {
		return "apiautomation" + System.currentTimeMillis() + "@gmail.com";
	}

	@Test
	public void deleteUserTest() {

		User user = new User("Naveen", getRandomEmail(), "male", "active");

		baseURI = "https://gorest.co.in";

		Response postResponse = given().contentType(ContentType.JSON)
				.header("Authorization", "Bearer 082d8bf459f772ca1d2babc8f780bb1d71f33914b3e52656328a68659846e1ce")
				.body(user).when().log().all().post("/public/v2/users");

		Integer userId = postResponse.jsonPath().get("id");
		System.out.println("User ID is " + userId);
		System.out.println("------------------------------------------------");

		given().contentType(ContentType.JSON)
				.header("Authorization", "Bearer 082d8bf459f772ca1d2babc8f780bb1d71f33914b3e52656328a68659846e1ce")
				.when().log().all().delete("/public/v2/users/" + userId).then().assertThat()
				.	statusCode(204);
		
		given().contentType(ContentType.JSON)
		.header("Authorization", "Bearer 082d8bf459f772ca1d2babc8f780bb1d71f33914b3e52656328a68659846e1ce")
		.when().log().all().get("/public/v2/users/" + userId).then().assertThat()
		.statusCode(404)
		.and()
		.body("message",equalTo("Resource not found"));

	}

}
