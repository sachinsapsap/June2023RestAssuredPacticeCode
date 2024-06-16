package com.pet.api;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PetLombok {

	private Integer id;
	private Category category;
	private String name;
	private List<String> photoUrls;
	private List<Tag> tags;
	private String status;

	@NoArgsConstructor
	@AllArgsConstructor
	@Data
	@Builder
	public static class Category {
		private Integer id;
		private String name;
	}

	@NoArgsConstructor
	@AllArgsConstructor
	@Data
	@Builder
	public static class Tag {
		private Integer id;
		private String name;

	}
}
