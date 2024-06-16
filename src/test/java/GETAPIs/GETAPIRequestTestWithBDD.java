package GETAPIs;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GETAPIRequestTestWithBDD {

	@Test
	public void getProductsTest() {

		given().log().all().when().log().all().get("https://fakestoreapi.com/products").then().log().all().assertThat()
				.statusCode(200).and().contentType(ContentType.JSON).and().header("Connection", "keep-alive").and()
				.body("$.size()", equalTo(20)).and().body("id", is(notNullValue())).and()
				.body("title", hasItem("Mens Cotton Jacket"));

	}

	@Test
	public void getUserAPITest() {

		baseURI = "https://gorest.co.in";

		given().log().all()
				.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
				.when().log().all().get("/public/v2/users").then().log().all().assertThat().statusCode(200).and()
				.contentType(ContentType.JSON).and().body("$.size()", equalTo(10));

	}

	@Test
	public void getProductDataAPIWithQueryParamTest() {

		baseURI = "https://fakestoreapi.com";

		given().log().all().queryParam("limit", 5).when().log().all().get("/products").then().log().all().assertThat()
				.statusCode(200);

	}

	@Test
	public void getProductDataAPIWithExtractBody() {

		baseURI = "https://fakestoreapi.com";

		Response response = given().log().all().queryParam("limit", 5).when().log().all().get("/products");

		JsonPath js = response.jsonPath();
		
//		JsonPath js= new JsonPath(response.getBody().asString());
		
		

//		get the ID of the first product ID.

		int firstProductID = js.getInt("[0].id");
		System.out.println("First Product ID is " + firstProductID);

		String firstProductTitle = js.getString("[0].title");
		System.out.println("First Product Title is " + firstProductTitle);

		float price = js.getFloat("[0].price");
		System.out.println("Price of first Product is " + price);

		int count = js.getInt("[0].rating.count");
		System.out.println("Count is " + count);

	}

	@Test
	public void getProductDataAPIWithExtractBodyWithJSONArray() {

		baseURI = "https://fakestoreapi.com";

		Response response = given().log().all().queryParam("limit", 10).when().log().all().get("/products");

		JsonPath js = response.jsonPath();

		List<Integer> idList = js.getList("id");
		System.out.println(idList);

		List<String> titleList = js.getList("title");
		System.out.println(titleList);

		List<Object> rateList = js.getList("rating.rate");

		List<Integer> countList = js.getList("rating.count", Integer.class);

		for (int i = 0; i < idList.size(); i++) {
			System.out.println(idList.get(i));
			System.out.println(titleList.get(i));
			System.out.println(rateList.get(i));
			System.out.println(countList.get(i));
		}

	}

	@Test
	public void getUserAPIWithExtractBodyWithJSON() {

		baseURI = "https://gorest.co.in";

		Response response = given().log().all()
				.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
				.when().log().all().get("/public/v2/users/6914818");

		JsonPath js = response.jsonPath();

		System.out.println(js.getInt("id"));
		System.out.println(js.getString("email"));

	}

	@Test
	public void getUserAPIWithExtractBodyWithJSONExtract() {

		baseURI = "https://gorest.co.in";

		int userID = given().log().all()
				.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
				.when().log().all().get("/public/v2/users/6914818").then().extract().path("id");

		System.out.println(userID);

		Response response = given()
				.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
				.when().get("/public/v2/users/6914818").then().extract().response();

		String email = response.path("email");

		System.out.println(email);

	}

}
