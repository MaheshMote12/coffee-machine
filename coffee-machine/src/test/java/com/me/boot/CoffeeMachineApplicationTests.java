package com.me.boot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=CoffeeMachineApplication.class, webEnvironment=WebEnvironment.RANDOM_PORT)
public class CoffeeMachineApplicationTests {

	@LocalServerPort
	private int port;
	
	
	@Test
	public void contextLoads() {
		
		
	}

	@Test
	public void controller() {

		
		
	}

}
