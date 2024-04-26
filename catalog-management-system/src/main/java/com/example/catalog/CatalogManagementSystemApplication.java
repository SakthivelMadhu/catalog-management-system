package com.example.catalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@ComponentScan("com.example.catalog") 
@Configuration
//@EntityScan({"com.example.catalog.entity" , "com.example.admin.user"})
public class CatalogManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatalogManagementSystemApplication.class, args);
	}

}
