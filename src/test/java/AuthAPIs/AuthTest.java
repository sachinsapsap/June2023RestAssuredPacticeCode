package AuthAPIs;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class AuthTest {
	
	@BeforeTest
	public void alluerSetUpMethod() {
		RestAssured.filters(new AllureRestAssured());
	}

	@Test
	public void jwtAuthWithJsonBody() {

		baseURI = "https://fakestoreapi.com";

		String jwtTokenId = given().log().all().contentType(ContentType.JSON)
				.body("{\r\n" + "  \"username\": \"mor_2314\",\r\n" + "  \"password\": \"83r5^_\"\r\n" + "}").when()
				.log().all().post("/auth/login").then().log().all().assertThat().statusCode(200).and().extract().body()
				.path("token");

		System.out.println(jwtTokenId);

		String[] jwtTokenArray = jwtTokenId.split("\\.");
		System.out.println(jwtTokenArray[0]);

	}

	@Test
	public void basicAuthTest() {

		baseURI = "https://the-internet.herokuapp.com";

		String responseBody = given().log().all().auth().basic("admin", "admin").when().log().all().get("/basic_auth")
				.then().log().all().assertThat().statusCode(200).and().extract().body().asString();

		System.out.println(responseBody);

	}

	@Test
	public void preemptiveAuthTest() {

		baseURI = "https://the-internet.herokuapp.com";

		String responseBody = given().log().all().auth().preemptive().basic("admin", "admin").when().log().all()
				.get("/basic_auth").then().log().all().assertThat().statusCode(200).and().extract().body().asString();

		System.out.println(responseBody);

	}

	@Test
	public void digestAuthTest() {

		baseURI = "https://the-internet.herokuapp.com";

		String responseBody = given().log().all().auth().digest("admin", "admin").when().log().all().get("/basic_auth")
				.then().log().all().assertThat().statusCode(200).and().extract().body().asString();

		System.out.println(responseBody);

	}

	@Test
	public void apiKeyTest() {

		baseURI = "http://api.weatherapi.com";

		String responseBody = given().log().all().queryParam("q", "London").queryParam("aqi", "no")
				.queryParam("key", "6cc02c045e8445fea0b82701240805").when().log().all().get("/v1/current.json").then()
				.log().all().assertThat().statusCode(200).and().extract().body().asString();

		System.out.println(responseBody);
	}

}
