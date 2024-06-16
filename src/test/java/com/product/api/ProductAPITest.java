package com.product.api;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import com.fake.api.Product;
import com.fake.api.ProductWithLombok;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.response.Response;

public class ProductAPITest {

	@Test
	public void getProductTestWithPOJO() {
		baseURI = "https://fakestoreapi.com";

		Response response= given().when().get("/products");
		
//		Json to POJO De-serialization
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			Product product[]=mapper.readValue(response.getBody().asString(), Product[].class);
			
			for(Product p: product) {
				System.out.println("ID "+p.getId());
				System.out.println("Title "+p.getTitle());
				System.out.println("Price "+p.getPrice());
				System.out.println("Description "+p.getDescription());
				System.out.println("Rating Rate "+p.getRating().getRate());
				System.out.println("Rating Count "+p.getRating().getCount());
				System.out.println("========================================");
			}
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Test
	public void getProductTestWithLombokPOJO() {
		baseURI = "https://fakestoreapi.com";

		Response response= given().when().get("/products");
		
//		Json to POJO De-serialization
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			ProductWithLombok product[]=mapper.readValue(response.getBody().asString(), ProductWithLombok[].class);
			
			for(ProductWithLombok p: product) {
				System.out.println("ID "+p.getId());
				System.out.println("Title "+p.getTitle());
				System.out.println("Price "+p.getPrice());
				System.out.println("Description "+p.getDescription());
				System.out.println("Rating Rate "+p.getRating().getRate());
				System.out.println("Rating Count "+p.getRating().getCount());
				System.out.println("========================================");
			}
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Test
	public void getProductTestWithLombokPOJOBuilderPattern() {
		baseURI = "https://fakestoreapi.com";

		
		

	}


}
