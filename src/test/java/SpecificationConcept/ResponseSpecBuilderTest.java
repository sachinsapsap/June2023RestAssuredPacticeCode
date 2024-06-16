package SpecificationConcept;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

public class ResponseSpecBuilderTest {

	public static ResponseSpecification getResponseSpec200OK() {

		ResponseSpecification responseSpec200OK = new ResponseSpecBuilder().expectContentType(ContentType.JSON)
				.expectStatusCode(200).expectHeader("Server", "cloudflare").build();

		return responseSpec200OK;

	}

	public static ResponseSpecification getResponseSpec200OKWithBody() {

		ResponseSpecification responseSpec200OKWithBody = new ResponseSpecBuilder().expectContentType(ContentType.JSON)
				.expectStatusCode(200).expectHeader("Server", "cloudflare").expectBody("$.size()", equalTo(10))
				.expectBody("id",hasSize(10))
				.build();

		return responseSpec200OKWithBody;

	}

	public static ResponseSpecification getResponseSpec401Unauthorized() {

		ResponseSpecification responseSpec401Unauthorized = new ResponseSpecBuilder()
				.expectContentType(ContentType.JSON).expectStatusCode(401).expectHeader("Server", "cloudflare").build();

		return responseSpec401Unauthorized;

	}

	@Test
	public void getUserResponse200SpecTest() {

		RestAssured.baseURI = "https://gorest.co.in";

		given().log().all()
				.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
				.when().log().all().get("/public/v2/users").then().assertThat().spec(getResponseSpec200OKWithBody());

	}

	@Test
	public void getUserResponse401SpecTest() {

		RestAssured.baseURI = "https://gorest.co.in";

		given().log().all().when().log().all().get("/public/v2/users").then().assertThat()
				.spec(getResponseSpec401Unauthorized());

	}

}
