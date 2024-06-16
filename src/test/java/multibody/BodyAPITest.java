package multibody;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.io.File;

public class BodyAPITest {

	@Test
	public void bodyWithTextTest() {

		baseURI = "http://httpbin.org";

		String payload = "Hi This is Naveen";

		Response response = given().log().all().contentType(ContentType.TEXT).body(payload).when().log().all()
				.post("/post");

		response.prettyPrint();
		
		String dataResponse=response.jsonPath().get("data");
		
		Assert.assertEquals(payload, dataResponse);

	}
	
	@Test
	public void bodyWithJavaScriptTest() {

		baseURI = "http://httpbin.org";

		String payload = "function login(){\r\n"
				+ "	let x=10;\r\n"
				+ "	let y=20;\r\n"
				+ "	console.log(x+y);\r\n"
				+ "}";

		Response response = given().log().all().header("Content-Type","application/javascript").body(payload).when().log().all()
				.post("/post");

		response.prettyPrint();
		
		String dataResponse=response.jsonPath().get("data");
		
		System.out.println(dataResponse);

	}
	
	@Test
	public void bodyWithHTMLTest() {

		baseURI = "http://httpbin.org";

		String payload = "<!DOCTYPE html>\r\n"
				+ "<html dir=\"ltr\" lang=\"en\">\r\n"
				+ "<head>\r\n"
				+ "<meta charset=\"UTF-8\" />\r\n"
				+ "</head>\r\n"
				+ "</html>";

		Response response = given().log().all().contentType(ContentType.HTML).body(payload).when().log().all()
				.post("/post");

		response.prettyPrint();
		
		String dataResponse=response.jsonPath().get("data");
		
		System.out.println(dataResponse);

	}
	
	@Test
	public void bodyWithXMLTest() {

		baseURI = "http://httpbin.org";

		String payload = "<properties>\r\n"
				+ "		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>\r\n"
				+ "		<maven.compiler.source>1.11</maven.compiler.source>\r\n"
				+ "		<maven.compiler.target>1.11</maven.compiler.target>\r\n"
				+ "	</properties>";

		Response response = given().log().all().contentType(ContentType.XML).body(payload).when().log().all()
				.post("/post");

		response.prettyPrint();
		
		String dataResponse=response.jsonPath().get("data");
		
		System.out.println(dataResponse);

	}
	
	@Test
	public void bodyWithMultiPartFormDataTest() {

		baseURI = "http://httpbin.org";

		

		Response response = given().log().all().contentType(ContentType.MULTIPART)
				.multiPart("name", "testing")
				.multiPart("fileName", new File("C:\\Users\\91858\\Desktop\\selenium.png"))
				.when().log().all()
				.post("/post");

		response.prettyPrint();

	}
	
	@Test
	public void bodyWithBinaryFileTest() {

		baseURI = "http://httpbin.org";

		Response response = given().log().all().header("Content-Type","application/image")
				.body(new File("C:\\Users\\91858\\Desktop\\selenium.png"))
				.when().log().all()
				.post("/post");

		response.prettyPrint();

	}

}
