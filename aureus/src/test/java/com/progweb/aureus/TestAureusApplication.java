package com.progweb.aureus;

import org.springframework.boot.SpringApplication;

public class TestAureusApplication {

	public static void main(String[] args) {
		SpringApplication.from(AureusApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
