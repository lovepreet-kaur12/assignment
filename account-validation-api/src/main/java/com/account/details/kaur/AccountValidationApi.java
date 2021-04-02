package com.account.details.kaur;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.account.details")
public class AccountValidationApi {

	public static void main(String[] args) {
		SpringApplication.run(AccountValidationApi.class, args);
	}

}
