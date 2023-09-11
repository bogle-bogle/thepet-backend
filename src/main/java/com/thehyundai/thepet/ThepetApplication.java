package com.thehyundai.thepet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication
@EnableCaching
public class ThepetApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThepetApplication.class, args);
	}

}
