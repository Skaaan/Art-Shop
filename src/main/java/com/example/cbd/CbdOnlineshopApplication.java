package com.example.cbd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication
//@EnableCaching
public class CbdOnlineshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(CbdOnlineshopApplication.class, args);
	}

}
