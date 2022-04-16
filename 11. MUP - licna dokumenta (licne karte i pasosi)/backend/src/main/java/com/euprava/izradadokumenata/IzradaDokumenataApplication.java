package com.euprava.izradadokumenata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class IzradaDokumenataApplication {


	@GetMapping("/hello")
	public String helloWorld(){
		return "Hello world";
	}
	public static void main(String[] args) {
		SpringApplication.run(IzradaDokumenataApplication.class, args);
	}

}
