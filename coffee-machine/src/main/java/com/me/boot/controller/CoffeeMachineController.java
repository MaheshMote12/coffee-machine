package com.me.boot.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.me.boot.model.Drink;
import com.me.boot.repository.DrinkRepository;
import com.me.boot.service.Iinventory;
import com.me.boot.serviceImpl.CoffeeService;

@Controller
@RequestMapping("/coffee")
public class CoffeeMachineController {

	private CoffeeService coffeeService;
	private DrinkRepository drinkRepo; 
	private Iinventory inventoryService;
	
	public CoffeeMachineController(CoffeeService coffeeService, DrinkRepository drinkRepo, Iinventory inventoryUtil) {
		super();
		this.coffeeService = coffeeService;
		this.drinkRepo = drinkRepo;
		this.inventoryService = inventoryUtil;
	}


	@GetMapping("/drink/{drinkId}")
	public String makeDrink( Model model, @PathVariable("drinkId") Long drinkId) {
		
		Drink drink = coffeeService.makeDrink(drinkId);
			
		model.addAttribute("drink", drink);
		
		return "drink";
	}
	
	@GetMapping(value= {"/", ""})
	public String getMenu( Model model) {
		
		List<Drink> menu = coffeeService.getMenu();
		model.addAttribute("menu", menu);
		
		Map<String, Integer> ingredients = inventoryService.getIngredients();
		
		Drink drink = new Drink();
		drink.setIngredients(ingredients);
		
		model.addAttribute("drink", drink);
		
		return "menu";
	}
		
	@PostMapping("/drink/add")
	public String orderDetail(@ModelAttribute("drink") Drink drink, Model model ) {
		
		 drinkRepo.save(drink);
		return "redirect:/coffee";
		
	}
	
	
	
}
