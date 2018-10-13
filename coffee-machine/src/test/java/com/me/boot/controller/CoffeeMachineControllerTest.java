package com.me.boot.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.me.boot.model.Drink;
import com.me.boot.repository.DrinkRepository;
import com.me.boot.service.Iinventory;
import com.me.boot.serviceImpl.CoffeeService;

@RunWith(SpringRunner.class)
//@WebMvcTest(controllers=CoffeeMachineController.class)
public class CoffeeMachineControllerTest {

	MockMvc mockMvc;
	
	CoffeeMachineController controller;
	
	@MockBean
	CoffeeService coffeeService;
	@MockBean
	private Iinventory inventoryUtil;
	
	@MockBean
	private DrinkRepository drinkRepo; 
	
	@Before
	public void setUp() throws Exception {
		
		controller = new CoffeeMachineController(coffeeService, drinkRepo, inventoryUtil);
		
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("templates/");
		resolver.setSuffix(".jsp");
		
		mockMvc = MockMvcBuilders.standaloneSetup(controller)
						.setViewResolvers(resolver).build();
		
	}
	
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void should_return_drink() throws Exception {
		
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/coffee/drink/1");
		
		  MvcResult result = mockMvc.perform(builder).andExpect(status().isOk() )
								/*.andExpect(MockMvcResultMatchers.model().attributeExists("drink"))*/
								.andExpect(view().name("drink"))
								.andDo(print()).andReturn();
		  
		  assertThat("drink", is(result.getModelAndView().getViewName()));
		
	}
	
	@Test
	public void should_returnAllDrinks() throws Exception {
		
		
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/coffee/");
		
		mockMvc.perform(builder).andExpect( MockMvcResultMatchers.view().name("menu"))
								.andExpect(model().attributeExists("menu"))
								.andExpect(status().isOk()).andDo(print()) ;
		
		
	}
	
	

}
