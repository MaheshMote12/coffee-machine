package com.me.boot.mapper;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.me.boot.dto.InventoryDto;
import com.me.boot.model.Inventory;

import lombok.Synchronized;

@Component
public class InventoryToInventoryDto implements Converter<Inventory, InventoryDto>{

	@Synchronized
	@Override
	public InventoryDto convert(Inventory source) {

		final InventoryDto dto = new InventoryDto();
		dto.setId(source.getId());
		dto.setItemName(source.getItemName());
		dto.setUnits(source.getUnits());
		
		return dto;
	}

}
