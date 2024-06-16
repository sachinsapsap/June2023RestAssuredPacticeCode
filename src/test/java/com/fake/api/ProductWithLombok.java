package com.fake.api;

import groovy.transform.builder.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

public class ProductWithLombok {
	
	private int id;
	private String title;
	private double price;
	private String description;
	private String category;
	private String image;
	private Rating rating;
	
	
//	Inner Class
	@NoArgsConstructor
	@AllArgsConstructor
	@Data
	@Builder
	public static class Rating {

		private double rate;
		private int count;
}
}
