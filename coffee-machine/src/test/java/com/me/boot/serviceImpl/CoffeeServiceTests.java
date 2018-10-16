package com.me.boot.serviceImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.me.boot.bootstrap.DataBootstrap;
import com.me.boot.dto.DrinkDto;
import com.me.boot.mapper.DrinkDtoToDrink;
import com.me.boot.mapper.DrinkToDrinkDtoMapper;
import com.me.boot.model.Drink;
import com.me.boot.repository.DrinkRepository;
import com.me.boot.repository.InventoryRepository;

/**
 * Tests for {@link CoffeeService}.
 */
@RunWith(SpringRunner.class)
@DataJpaTest(showSql=true)
public class CoffeeServiceTests {

	private CoffeeService machine;

	@Autowired
	private DrinkRepository drinkRepo;
	
	@MockBean
	InventoryService inventoryUtil;
	
	@MockBean
	private DrinkToDrinkDtoMapper toDto;
	
	@Autowired
	InventoryRepository inventoryRepo;
	
	@Autowired
	TestEntityManager entityManager;

	@MockBean
	private DrinkDtoToDrink toDrink;

	@Before
	public void setUp() {
		this.machine = new CoffeeService(this.inventoryUtil,   this.drinkRepo, this.toDto, toDrink);
	
//		inserting data in h2
		DataBootstrap db = new DataBootstrap(  inventoryRepo, this.drinkRepo);
		db.onApplicationEvent(null);
	}

	
	@Test
	public void getMenu() {
		
		  List<DrinkDto> menu = machine.getMenu();
		  assertThat(menu).hasSize(3);
	}

	@Test
	public void makeDrink() {
		
		
/*		verify(this.inventory).deduct("coffee", 2);
		verify(this.inventory).deduct("sugar", 1);
		verify(this.inventory).deduct("cream", 2);*/
	}

}