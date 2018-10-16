package com.me.boot.mapper;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.me.boot.dto.DrinkDto;
import com.me.boot.model.Drink;

import lombok.Synchronized;

@Component
public class DrinkToDrinkDtoMapper implements Converter<Drink, DrinkDto> {

	@Synchronized
	@Override
	public DrinkDto convert(Drink source) {

		
		final DrinkDto dto = new DrinkDto();
		dto.setCost(source.getCost());
		dto.setFilePath(source.getFilePath());
		dto.setId(source.getId());
		dto.setIngredients(source.getIngredients());
		dto.setName(source.getName());
		
		return dto;
	}

}
