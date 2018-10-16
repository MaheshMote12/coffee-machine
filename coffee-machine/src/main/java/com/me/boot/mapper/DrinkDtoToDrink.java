package com.me.boot.mapper;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.me.boot.dto.DrinkDto;
import com.me.boot.model.Drink;

import lombok.Synchronized;

@Component
public class DrinkDtoToDrink implements Converter<DrinkDto, Drink> {

	@Synchronized
	@Override
	public Drink convert(DrinkDto source) {

		final Drink drink = new Drink();
		
		drink.setId(source.getId());
		drink.setCost(source.getCost());
		drink.setFilePath(source.getFilePath());
		drink.setName(source.getName());
		drink.setIngredients(source.getIngredients());
		
		return drink;
	}

	
}
