package com.me.boot.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.me.boot.model.Inventory;
import com.me.boot.repository.InventoryRepository;
import com.me.boot.service.Iinventory;

@Component
public class InventoryService implements Iinventory  {
	
	private InventoryRepository inventoryRepo;
	
	public InventoryService(InventoryRepository inventoryRepo) {
		super();
		this.inventoryRepo = inventoryRepo;
	}

	@Override
	public Map<String, Integer> getIngredients() {
		
		Map<String, Integer> ingredientsMap = new HashMap<>();

		List<Inventory> ingredientsList = inventoryRepo.findAll();
		
		ingredientsList.stream().forEach( i ->  ingredientsMap.put(i.getItemName() , i.getUnits()));
		
		return ingredientsMap ;
	}
	
	@Override
	public void deduct(String name, Integer amount) {

		Inventory inventory = inventoryRepo.findByitemName(name);
		if(inventory.getUnits() <= 0)
			throw new RuntimeException("Not Enough "+ inventory.getItemName()+ " in Inventory");
		
		inventory.setUnits(inventory.getUnits() - amount);
		inventoryRepo.save(inventory);
 	}

	@Override
	public List<String> getAllItems() {
		return inventoryRepo.getAllItems();
	}

}
