package com.me.boot.controller.api;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.me.boot.dto.DrinkDto;
import com.me.boot.model.Drink;
import com.me.boot.repository.DrinkRepository;
import com.me.boot.service.Iinventory;
import com.me.boot.serviceImpl.CoffeeService;
import com.me.boot.util.FileStorage;

import lombok.extern.slf4j.Slf4j;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/api/coffee")
@Slf4j
public class CoffeeMachineResource {
	
	
	private CoffeeService coffeeService;
	private DrinkRepository drinkRepo; 
	private Iinventory inventoryService;
	private FileStorage fileStorage;
	
	public CoffeeMachineResource(CoffeeService coffeeService, DrinkRepository drinkRepo, Iinventory inventoryService,
			FileStorage fileStorage) {
		super();
		this.coffeeService = coffeeService;
		this.drinkRepo = drinkRepo;
		this.inventoryService = inventoryService;
		this.fileStorage = fileStorage;
	}
	
	
	@GetMapping(value= {""})
	public ResponseEntity<List<DrinkDto>> getDrink(){
		
		List<DrinkDto> menuList = coffeeService.getMenu();

		menuList.stream().forEach( d -> {
			
			if(d.getFilePath()!= null) {
				String fileUrl = MvcUriComponentsBuilder.fromMethodName(getClass(), "downloadFile", d.getFilePath())
						.build().toString();
				d.setFilePath(fileUrl);
			}
			
		});
		
		return ResponseEntity.ok().allow(HttpMethod.GET).contentType(MediaType.APPLICATION_JSON_UTF8).body(menuList);
		
	}
	
	
	@GetMapping("/drink/{drinkId}")
	public ResponseEntity<DrinkDto> makeDrink( @PathVariable("drinkId") Long drinkId) {
		
		DrinkDto drinkDto = coffeeService.makeDrink( drinkId );
		
//		make sure first that file is added to the drink. if not then add filename as {fileName} 
//		to show it in swagger doc.
		if(drinkDto.getFilePath() == null) {
			drinkDto.setFilePath("{fileName}");
		}
		
		String fileUrl = MvcUriComponentsBuilder.fromMethodName(getClass(), "downloadFile", drinkDto.getFilePath()).build().toString();
		drinkDto.setFilePath(fileUrl);
		
		return ResponseEntity.ok(drinkDto);
	}
	
	
	
	@GetMapping("/drink/image/{fileName:.+}")
	@ResponseBody
	@ApiIgnore
	public ResponseEntity<Resource> downloadFile(@PathVariable("fileName") String fileName) {
		
		log.info("File Path to download is: " +fileName);
		Resource file = fileStorage.loadFile(fileName);
		
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.IMAGE_JPEG);
			headers.setContentLength(file.contentLength());
			headers.add("Content-Disposition", "inline;filename="+file.getFilename());
			return new ResponseEntity<Resource>(file, headers, HttpStatus.OK);
			
		} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException("FAIL => message: "+e.getMessage());
		}
		
	}
	
	
	@PostMapping("/drink/add")
	@ResponseStatus(value=HttpStatus.CREATED)
	public DrinkDto addDrink(@RequestBody DrinkDto drinkDto ) {
		return coffeeService.saveDrink(drinkDto);
	}

	@PostMapping("/drink/{drinkId}/upload")
	@ResponseStatus(value=HttpStatus.CREATED)
	public DrinkDto addImage(@PathVariable("drinkId") Long drinkId, @RequestPart("file") MultipartFile file, HttpSession session) {
		

		log.info("saving file.. " +file.getOriginalFilename());
		
		DrinkDto drinkDto = coffeeService.findOne(drinkId);
		fileStorage.init(session);
		String savedFileName = fileStorage.store(file);
		drinkDto.setFilePath(savedFileName);
		String fileUrl = MvcUriComponentsBuilder.fromMethodName(getClass(), "downloadFile", drinkDto.getFilePath()).build().toString();
//		drinkDto.setFilePath(fileUrl);
		
		  DrinkDto savedDrink = coffeeService.saveDrink(drinkDto);
		  savedDrink.setFilePath(fileUrl);
		  
		  return savedDrink;
	}

}
