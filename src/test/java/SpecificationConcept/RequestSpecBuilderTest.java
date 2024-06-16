package SpecificationConcept;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestSpecBuilderTest {

	public static RequestSpecification userRequestSpec() {

		RequestSpecification requestSpec = new RequestSpecBuilder().setBaseUri("https://gorest.co.in")
				.setContentType(ContentType.JSON)
				.addHeader("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
				.build();

		return requestSpec;

	}

	@Test
	public void getUserWithRequestSpec() {

		RestAssured.given(userRequestSpec()).log().all().when().log().all().get("/public/v2/users").then().log().all()
				.assertThat().statusCode(200);

	}

	@Test
	public void getUserWithQueryParamRequestSpec() {

		RestAssured.given(userRequestSpec()).queryParam("name", "naveen").log().all().when().log().all()
				.get("/public/v2/users").then().log().all().assertThat().statusCode(200);

	}

}
