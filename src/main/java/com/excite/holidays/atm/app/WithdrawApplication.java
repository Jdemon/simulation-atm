package com.excite.holidays.atm.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.excite.holidays.atm")
public class WithdrawApplication {

	public static void main(String[] args) {
		SpringApplication.run(WithdrawApplication.class, args);
	}

}

