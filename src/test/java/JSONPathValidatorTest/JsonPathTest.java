package JSONPathValidatorTest;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;

import io.restassured.response.Response;

public class JsonPathTest {

	@Test
	public void getCircuitDataAPIWithYearTest() {

		baseURI = "http://ergast.com";

		Response response = given().log().all().when().log().all().get("/api/f1/2017/circuits.json");

		String jsonResponse = response.getBody().asString();
		System.out.println(jsonResponse);

		List<String> countryList = JsonPath.read(jsonResponse, "$..country");
		System.out.println(countryList.size());
		System.out.println(countryList);

		int totalCircuits = JsonPath.read(jsonResponse, "$..Circuits.length()");
		System.out.println(totalCircuits);

	}

	@Test
	public void getProductTest() {

		baseURI = "https://fakestoreapi.com";

		Response response = given().log().all().when().get("/products");

		String jsonResponse = response.asString();
		System.out.println(jsonResponse);
		
		List<Float> rateLessThan3List= JsonPath.read(jsonResponse, "$[?(@.rating.rate<3)].rating.rate");
		System.out.println(rateLessThan3List);
		
//		Fetching 2 attributes
		List<Map<String,Object>> titlepriceJeweleryList= JsonPath.read(jsonResponse, "$[?(@.category == 'jewelery')].[\"title\",\"price\"]");
		System.out.println(titlepriceJeweleryList);
		
		for(Map<String,Object> e: titlepriceJeweleryList) {
			String title=(String)e.get("title");
			Object price=(Object)e.get("price");
			
			System.out.println("Title "+title);
			System.out.println("Price "+price);
			System.out.println("==========");
		}
		
//		Fetching 3 attributes
		List<Map<String,Object>> titlepriceIdJeweleryList= JsonPath.read(jsonResponse, "$[?(@.category == 'jewelery')].[\"title\",\"price\",\"id\"]");
		System.out.println(titlepriceIdJeweleryList);
		
		for(Map<String,Object> e: titlepriceIdJeweleryList) {
			String title=(String)e.get("title");
			Object price=(Object)e.get("price");
			Integer id=(Integer)e.get("id");
			
			System.out.println("Title "+title);
			System.out.println("Price "+price);
			System.out.println("ID "+id);
			System.out.println("==========");
		}

	}

}
