package com.me.boot.service;

import java.util.List;
import java.util.Map;

import com.me.boot.model.Drink;

public interface ICoffeeService { 
	
	
	public List<Drink> getMenu();

	/**
	 * Make a drink using the given name. Ingredients for the drink are deducted from the inventory.
	 *
	 * @param name the name of the drink
	 */
	
	public Drink makeDrink(Long drinkId);

}
