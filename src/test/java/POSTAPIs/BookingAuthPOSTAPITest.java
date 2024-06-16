package POSTAPIs;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class BookingAuthPOSTAPITest {
	
	@Test
	public void getBookingAuthTokenTestWithJSONStringBody() {
		
		baseURI="https://restful-booker.herokuapp.com";
		
		String tokenID=given().log().all()
		.contentType(ContentType.JSON)
		.body("{\r\n"
				+ "    \"username\" : \"admin\",\r\n"
				+ "    \"password\" : \"password123\"\r\n"
				+ "}")
		.when().log().all().post("/auth")
		.then().log().all().assertThat().statusCode(200)
		.extract().response().path("token");
		
		System.out.println(tokenID);
		
		Assert.assertNotNull(tokenID);
	}
	
	@Test
	public void getBookingAuthTokenTestWithJSONFileBody() {
		
		baseURI="https://restful-booker.herokuapp.com";
		
		String tokenID=given().log().all()
		.contentType(ContentType.JSON)
		.body(new File("./src/test/resources/Data/BasicAuth.json"))
		.when().log().all().post("/auth")
		.then().log().all().assertThat().statusCode(200)
		.extract().response().path("token");
		
		System.out.println(tokenID);
		
		Assert.assertNotNull(tokenID);
	}
	
	
	@Test
	public void addUserTest() {
		
		baseURI="https://gorest.co.in";
		
//		Add a user
		
		int userID=given()
		.contentType(ContentType.JSON)
		.headers("Authorization", "Bearer 082d8bf459f772ca1d2babc8f780bb1d71f33914b3e52656328a68659846e1ce")
		.body(new File("./src/test/resources/Data/GoRestAddUser.json"))
		.when()
		.post("/public/v2/users/")
		.then()
		.assertThat()
		.statusCode(201)
		.body("name",equalTo("Naveen"))
		.extract().response().path("id");
		
		System.out.println(userID);
		
		
//		Get User
		
		given()
		.headers("Authorization", "Bearer 082d8bf459f772ca1d2babc8f780bb1d71f33914b3e52656328a68659846e1ce")
		.when().log().all()
		.pathParam("ID", userID)
		.get("/public/v2/users/{ID}")
		.then()
		.assertThat()
		.statusCode(200)
		.and()
		.body("name",equalTo("Naveen"))
		.and()
		.body("id", equalTo(userID));
		
		
		
		
	}

}
