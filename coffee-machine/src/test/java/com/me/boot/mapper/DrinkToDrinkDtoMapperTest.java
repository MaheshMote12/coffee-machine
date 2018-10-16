package com.me.boot.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.me.boot.dto.DrinkDto;
import com.me.boot.model.Drink;

@RunWith(MockitoJUnitRunner.class)
public class DrinkToDrinkDtoMapperTest {

	DrinkToDrinkDtoMapper mapper;
	
	@Before
	public void setUp() throws Exception {
		mapper = new DrinkToDrinkDtoMapper();
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void shouldReturnDrinkDto() {

		Drink coffee = new Drink();
		coffee.setCost(2.75);
		coffee.setName("coffee");
		coffee.getIngredients().put("coffee", 2);
		coffee.getIngredients().put("cream", 2);

		
		DrinkDto dto = mapper.convert(coffee);
		
		assertThat(2.75, is(dto.getCost()));
		assertThat(coffee.getName(), is(dto.getName()));
		
		assertThat(coffee.getIngredients()).containsEntry("coffee", 2);
		assertThat(coffee.getIngredients()).containsEntry("cream", 2);
		
	}

}
