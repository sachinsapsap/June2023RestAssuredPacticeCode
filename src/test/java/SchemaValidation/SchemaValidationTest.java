package SchemaValidation;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class SchemaValidationTest {

	@Test
	public void createUserAPISchemaValidationTest() {

		baseURI = "https://gorest.co.in";

//		Add a user

		given().log().all().contentType(ContentType.JSON)
				.headers("Authorization", "Bearer 082d8bf459f772ca1d2babc8f780bb1d71f33914b3e52656328a68659846e1ce")
				.body(new File("./src/test/resources/Data/GoRestAddUser.json")).when().log().all()
				.post("/public/v2/users/").then().log().all().assertThat().statusCode(201).and()
				.body(matchesJsonSchemaInClasspath("createuserschema.json"));

	}
	
	@Test
	public void getUsersAPISchemaValidationTest() {

		baseURI = "https://gorest.co.in";

		given().log().all().contentType(ContentType.JSON)
				.headers("Authorization", "Bearer 082d8bf459f772ca1d2babc8f780bb1d71f33914b3e52656328a68659846e1ce")
				.when().log().all()
				.get("/public/v2/users/").then().log().all().assertThat().statusCode(200).and()
				.body(matchesJsonSchemaInClasspath("getusersschema.json"));

	}

}
