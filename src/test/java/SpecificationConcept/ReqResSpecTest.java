package SpecificationConcept;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class ReqResSpecTest {
	
	public static RequestSpecification userRequestSpec() {

		RequestSpecification requestSpec = new RequestSpecBuilder().setBaseUri("https://gorest.co.in")
				.setContentType(ContentType.JSON)
				.addHeader("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
				.build();

		return requestSpec;

	}
	
	public static ResponseSpecification getResponseSpec200OKWithBody() {

		ResponseSpecification responseSpec200OKWithBody = new ResponseSpecBuilder().expectContentType(ContentType.JSON)
				.expectStatusCode(200).expectHeader("Server", "cloudflare").expectBody("$.size()", equalTo(10))
				.expectBody("id",hasSize(10))
				.build();

		return responseSpec200OKWithBody;

	}
	
	@Test
	public void getUserWithReqResSpecTest() {
		
		given(userRequestSpec()).log().all().
		when().log().all().get("/public/v2/users").
		then().log().all().assertThat().spec(getResponseSpec200OKWithBody());
		
	}
	
	

}
