package com.me.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		
		return new Docket(DocumentationType.SWAGGER_12)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.me.boot.controller.api"))
				.paths(PathSelectors.ant("/api/**"))
				.build()
				.apiInfo(metaData());
	}

	private ApiInfo metaData() {

		return new ApiInfoBuilder()
				.title("Coffee Dispensing Machine API with Spring Boot.")
				.description("Exposes API's of coffee dispensing machine.")
				.version("Version 1.1")
				.contact(new Contact("Mahesh", "https://github.com/MaheshMote12", "mahesh.mote44@gmail.com"))
				.licenseUrl("Terms and Conditions.")
				.build();
	}
	
}
