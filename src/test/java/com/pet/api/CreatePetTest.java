package com.pet.api;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pet.api.PetLombok.Category;
import com.pet.api.PetLombok.Tag;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreatePetTest {

	@Test
	public void createPetTest() {

		baseURI = "https://petstore.swagger.io";

		Category category = new Category(1, "Shiba");

		Tag tag1 = new Tag(1, "Red");
		Tag tag2 = new Tag(1, "Black");

		List<Tag> tags = Arrays.asList(tag1, tag2);

		List<String> photoUrls = Arrays.asList("https://dog.com", "https://dog1.com");

		PetLombok pet = new PetLombok(1, category, "Ronney", photoUrls, tags, "available");

		Response addResponse = given().contentType(ContentType.JSON).body(pet).when().post("/v2/pet");

		System.out.println(addResponse.statusCode());
		addResponse.prettyPrint();

		ObjectMapper mapper = new ObjectMapper();
		try {
			PetLombok petResponse = mapper.readValue(addResponse.getBody().asString(), PetLombok.class);

			System.out.println(petResponse.getId());
			System.out.println(petResponse.getCategory().getId());
			System.out.println(petResponse.getPhotoUrls().get(0));
			System.out.println(petResponse.getTags().get(0).getId());

		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void createPetWithBuilderPatternTest() {

		baseURI = "https://petstore.swagger.io";

		Category category = new Category.CategoryBuilder().id(1).name("Shiba").build();

		Tag tag1 = new Tag.TagBuilder().id(1).name("Shiba").build();
		Tag tag2=  new Tag.TagBuilder().id(2).name("Husky").build();

		PetLombok pet = new PetLombok.PetLombokBuilder().id(1).category(category).name("Ronney")
				.photoUrls(Arrays.asList("https://dog.com", "https://dog1.com")).tags(Arrays.asList(tag1,tag2))
				.status("available").build();

		Response addResponse = given().contentType(ContentType.JSON).body(pet).when().post("/v2/pet");

		System.out.println(addResponse.statusCode());
		addResponse.prettyPrint();

		ObjectMapper mapper = new ObjectMapper();
		try {
			PetLombok petResponse = mapper.readValue(addResponse.getBody().asString(), PetLombok.class);

			System.out.println(petResponse.getId());
			System.out.println(petResponse.getCategory().getId());
			System.out.println(petResponse.getPhotoUrls().get(0));
			System.out.println(petResponse.getTags().get(0).getId());

		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
