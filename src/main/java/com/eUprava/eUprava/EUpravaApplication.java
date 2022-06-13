package com.eUprava.eUprava;

import com.eUprava.eUprava.service.impl.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EUpravaApplication implements CommandLineRunner {

	@Autowired
	private UtilService utilService;

	public static void main(String[] args) {
		SpringApplication.run(EUpravaApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		utilService.createUsers();
	}
}
