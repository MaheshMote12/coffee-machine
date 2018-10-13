package com.me.boot.serviceImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

import java.util.Map;

import org.junit.Test;

import com.me.boot.model.Inventory;
import com.me.boot.service.Iinventory;

/**
 * Abstract base class for for {@link Inventory} tests.
 */
public abstract class InventoryImplTests {

	protected abstract Iinventory getInventory();

	@Test
	public void getIngredientsShouldReturnTheIngredientsInInventory() {
		Iinventory inventory = getInventory();
		
		Map<String, Integer> ingredients = inventory.getIngredients();
		assertThat(ingredients).contains(entry("coffee", 50));
		assertThat(ingredients).contains(entry("sugar", 50));
		assertThat(ingredients).contains(entry("cream", 50));
	}

	@Test
	public void deductShouldReduceQuantity() {
	
		Iinventory inventory = getInventory();
		inventory.deduct("coffee", 2);
		Map<String, Integer> ingredients = inventory.getIngredients();
		assertThat(ingredients).contains(entry("coffee", 48));
		
		
		
	}

}
