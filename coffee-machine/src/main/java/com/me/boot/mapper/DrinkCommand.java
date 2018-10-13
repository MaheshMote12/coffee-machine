package com.me.boot.mapper;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class DrinkCommand {

	private Long id;
	
	 
	private String name;
 	private Double cost;

	private Map<String, Integer> ingredients = new HashMap<>();

	
	
}
