package com.me.boot.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.me.boot.dto.DrinkDto;
import com.me.boot.exception.DrinkNotFoundException;
import com.me.boot.mapper.DrinkDtoToDrink;
import com.me.boot.mapper.DrinkToDrinkDtoMapper;
import com.me.boot.model.Drink;
import com.me.boot.repository.DrinkRepository;
import com.me.boot.service.ICoffeeService;

@Service
public class CoffeeService implements ICoffeeService{

	private InventoryService inventoryUtil;
	private DrinkRepository drinkRepo;
	private DrinkToDrinkDtoMapper toDto;
	private DrinkDtoToDrink toDrink;
	
	public CoffeeService(InventoryService inventoryUtil, DrinkRepository drinkRepo, DrinkToDrinkDtoMapper toDto,
			DrinkDtoToDrink toDrink) {
		super();
		this.inventoryUtil = inventoryUtil;
		this.drinkRepo = drinkRepo;
		this.toDto = toDto;
		this.toDrink = toDrink;
	}

	/**
	 * Returns the menu for this coffee machine.
	 *
	 * @return a map of drink name to drink cost
	 */
	public List<DrinkDto> getMenu() {
		
		  List<Drink> drinks = drinkRepo.findAll();
		  
		  return drinks.stream().map( d -> toDto.convert(d)).collect(Collectors.toList());
		
	}

	/**
	 * Make a drink using the given name. Ingredients for the drink are deducted from the inventory.
	 *
	 * @param name the name of the drink
	 */
	public DrinkDto makeDrink(Long drinkId) {
		
		 Drink drink = drinkRepo.findOne(drinkId);
		 
//		 drink.getIngredients().stream().forEach( i -> inventory.deduct(i.getName(), i.getUnits()));
		 
		 drink.getIngredients().entrySet().stream().forEach( e -> {
			 inventoryUtil.deduct(e.getKey(), e.getValue());
		 });
		 
		 return toDto.convert(drink);
		 
 	}

	@Override
	public DrinkDto saveDrink(DrinkDto drinkDto) {
		return toDto.convert(drinkRepo.save(toDrink.convert(drinkDto)));
	}

	@Override
	public DrinkDto findOne(Long drinkId) {

		Drink drink = drinkRepo.findOne(drinkId);
		if(drink != null) {
			return toDto.convert( drink );
		}else
			throw new DrinkNotFoundException("Drink with Id: "+drinkId+ " no available");
	}
}
