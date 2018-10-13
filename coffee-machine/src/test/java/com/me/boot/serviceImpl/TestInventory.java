package com.me.boot.serviceImpl;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.me.boot.bootstrap.DataBootstrap;
import com.me.boot.model.Inventory;
import com.me.boot.repository.DrinkRepository;
import com.me.boot.repository.InventoryRepository;

//h2 not adding data
@RunWith(SpringRunner.class)
@DataJpaTest(showSql=true)
public class TestInventory extends InventoryImplTests {

//	@Mock
	@Autowired
	private InventoryRepository inventoryRepo;
	
	@MockBean
	private Inventory inv;
	
	@Autowired
	DrinkRepository drinkRepo;
	
	@Before
	public void before() {
		
//		adding data in h2
		DataBootstrap db = new DataBootstrap( inventoryRepo, drinkRepo);
		db.onApplicationEvent(null);
		
		
/*		Inventory inv = new InventoryUtil(ingrRepo);

		
		List<Ingredients> ingrList = new ArrayList<>();
		ingrList.add(new Ingredients("coffee", 10));
		ingrList.add(new Ingredients("cream", 10));
		ingrList.add(new Ingredients("sugar", 10));
*/		
	}
	
	@After
	public void after() {
		
		inventoryRepo.deleteAllInBatch();
	}
	
	@Override
	protected InventoryService getInventory() {
		return new InventoryService(inventoryRepo);
	}

}
