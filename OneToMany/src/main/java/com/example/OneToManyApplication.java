package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
//@EnableJpaAuditing
public class OneToManyApplication {

	//https://www.callicoder.com/spring-boot-rest-api-tutorial-with-mysql-jpa-hibernate/
	public static void main(String[] args) {
		SpringApplication.run(OneToManyApplication.class, args);
	}

}
