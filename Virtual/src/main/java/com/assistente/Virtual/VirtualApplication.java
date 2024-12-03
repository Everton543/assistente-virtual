package com.assistente.Virtual;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VirtualApplication {
	static {
		// Chamar o inicializador do dotenv
		DotenvInitializer dotenvInitializer = new DotenvInitializer();
	}

	public static void main(String[] args) {
		SpringApplication.run(VirtualApplication.class, args);
	}
}
