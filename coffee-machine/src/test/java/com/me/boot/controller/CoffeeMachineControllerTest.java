package com.me.boot.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
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
import com.me.boot.util.FileStorage;

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
	private FileStorage fileStorage;
	
	@MockBean
	private DrinkRepository drinkRepo; 
	
	@Before
	public void setUp() throws Exception {
		
		
		MockitoAnnotations.initMocks(this);
		
		controller = new CoffeeMachineController(coffeeService, drinkRepo, inventoryUtil,fileStorage);
		
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
	
	@Test
//	@Ignore
	public void testFileDownloadSuccess() throws Exception {

		
		MockMultipartFile file = new MockMultipartFile("filename", "file".getBytes());
		
		Path path = Paths.get("filestorage").resolve("images");
		
		Resource r = new UrlResource(path.resolve("0m3.jpg").toUri());
		
		when(fileStorage.loadFile(anyString())).thenReturn(r);
		
		
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/coffee/drink/multipart/1");
		
		mockMvc.perform(builder).andExpect(MockMvcResultMatchers.content().contentType(MediaType.IMAGE_JPEG)).andDo(print());
		
	}
	
	

}
