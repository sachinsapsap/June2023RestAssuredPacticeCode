package POSTAPIs;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Oauth2Test {
	
	static String accessToken;
	
	@BeforeMethod
	public void getAccessToken() {
		
               baseURI="https://test.api.amadeus.com";
		
		       accessToken=
				            given().log().all()
		                       .header("Content-Type","application/x-www-form-urlencoded")
		                       .formParam("grant_type", "client_credentials")
		                       .formParam("client_id", "")
		                       .formParam("client_secret", "").
		                    when().log().all()
		                       .post("/v1/security/oauth2/token").
		                    then().log().all()
		                       .assertThat().statusCode(200)
		                       .and()
		                       .extract().response().path("access_token");
		
	}
	
	@Test
	public void getFlightInfoTest() {
		
		Response response=
				           given().log().all()
		                      .headers("Authorization","Bearer "+accessToken)
		                      .queryParams("origin", "PAR")
		                      .queryParams("maxPrice", 200).
		                   when().log().all()
		                      .get("/v1/shopping/flight-destinations").
		                   then().log().all()
		                      .assertThat().statusCode(200)
		                      .and()
		                      .body("data[0].type", equalTo("flight-destination"))
		                      .and()
		                      .extract().response();
		
		JsonPath js = response.jsonPath();
		List<Object> type=js.get("data.price.total");
	}

}
