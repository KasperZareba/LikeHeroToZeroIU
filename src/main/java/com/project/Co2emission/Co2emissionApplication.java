package com.project.Co2emission;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
public class Co2emissionApplication {

//	static EntityManagerFactory factory = Persistence.createEntityManagerFactory("mysql");
//	static EntityManager entityManager = factory.createEntityManager();

	public static void main(String[] args) {
		SpringApplication.run(Co2emissionApplication.class, args);

	}

}

