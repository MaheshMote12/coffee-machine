package com.me.boot.service;

import java.util.List;

import com.me.boot.dto.DrinkDto;

public interface ICoffeeService { 
	
	
	public List<DrinkDto> getMenu();

	/**
	 * Make a drink using the given name. Ingredients for the drink are deducted from the inventory.
	 *
	 * @param name the name of the drink
	 */
	
	public DrinkDto makeDrink(Long drinkId);
	
	public DrinkDto saveDrink(DrinkDto drink);
	
	public DrinkDto findOne(Long drinkId);

}
