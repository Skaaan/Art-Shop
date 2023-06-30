package com.example.cbd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })

public class CbdOnlineshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(CbdOnlineshopApplication.class, args);
	}

}


