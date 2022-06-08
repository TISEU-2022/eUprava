package ftn.euprava.zdravstvo;

import ftn.euprava.zdravstvo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ZdravstvoApplication implements CommandLineRunner {
	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(ZdravstvoApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		userService.createUser();
	}
}
