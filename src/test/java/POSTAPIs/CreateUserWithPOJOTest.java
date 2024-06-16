package POSTAPIs;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import POJO.User;
import io.restassured.http.ContentType;

public class CreateUserWithPOJOTest {
	
	public static String getRandomEmail() {
		return "apiautomation"+System.currentTimeMillis()+"@gmail.com";
	}

	@Test
	public void addUserTest() {

		baseURI = "https://gorest.co.in";
		User user = new User("Naveen", getRandomEmail(), "male", "active");

//		Add a user

		int userID = given().log().all().contentType(ContentType.JSON)
				.header("Authorization", "Bearer 082d8bf459f772ca1d2babc8f780bb1d71f33914b3e52656328a68659846e1ce")
				.body(user).when().log().all().post("/public/v2/users/").then().log().all().assertThat().statusCode(201)
				.body("name", equalTo(user.getName())).extract().response().path("id");

		System.out.println(userID);

//		Get User

		given().log().all().header("Authorization", "Bearer 082d8bf459f772ca1d2babc8f780bb1d71f33914b3e52656328a68659846e1ce")
				.when().log().all().pathParam("ID", userID).get("/public/v2/users/{ID}").then().log().all().assertThat()
				.statusCode(200).and().body("name", equalTo(user.getName())).and().body("id", equalTo(userID));

	}

}
