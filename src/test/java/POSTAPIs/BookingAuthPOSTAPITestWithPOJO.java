package POSTAPIs;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import POJO.Credentials;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;


public class BookingAuthPOSTAPITestWithPOJO {
	
//	POJO- Plain Old Java Object
//	cannot extend any other class. Cannot be parent of any other class
//	OOP- Encapsulation
//	private class variables -- JSON body
//	public setter getter
	
//	Serialization-->Java Object to other format: file,media,text,json,xml,pdf.(POJO to JSON)
//	De-serialization--> JSON to POJO
	
	
	@Test
	public void getBookingAuthTokenTestWithJSONStringBody() {
		
		baseURI="https://restful-booker.herokuapp.com";
		Credentials creds= new Credentials("admin","password123");
		
		String tokenID=given().log().all()
		.contentType(ContentType.JSON)
		.body(creds)
		.when().log().all().post("/auth")
		.then().log().all().assertThat().statusCode(200)
		.extract().response().path("token");
		
		System.out.println(tokenID);
		
		Assert.assertNotNull(tokenID);
	}

}
