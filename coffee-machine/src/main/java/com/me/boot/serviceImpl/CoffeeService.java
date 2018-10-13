package com.me.boot.serviceImpl;

import java.util.List;
 

import org.springframework.stereotype.Service;

import com.me.boot.model.Drink;
import com.me.boot.repository.DrinkRepository;
import com.me.boot.service.ICoffeeService;

@Service
public class CoffeeService implements ICoffeeService{

	private InventoryService inventoryUtil;
	private DrinkRepository drinkRepo;
 	 

	public CoffeeService(InventoryService inventoryUtil, DrinkRepository drinkRepo) {
		super();
		this.inventoryUtil = inventoryUtil;
		this.drinkRepo = drinkRepo;
	}

	/**
	 * Returns the menu for this coffee machine.
	 *
	 * @return a map of drink name to drink cost
	 */
	public List<Drink> getMenu() {
		
		return drinkRepo.findAll();
		
	}

	/**
	 * Make a drink using the given name. Ingredients for the drink are deducted from the inventory.
	 *
	 * @param name the name of the drink
	 */
	public Drink makeDrink(Long drinkId) {
		
		 Drink drink = drinkRepo.findOne(drinkId);
		 
//		 drink.getIngredients().stream().forEach( i -> inventory.deduct(i.getName(), i.getUnits()));
		 
		 drink.getIngredients().entrySet().stream().forEach( e -> {
			 inventoryUtil.deduct(e.getKey(), e.getValue());
		 });
		 
		 return drink;
 	}

}
