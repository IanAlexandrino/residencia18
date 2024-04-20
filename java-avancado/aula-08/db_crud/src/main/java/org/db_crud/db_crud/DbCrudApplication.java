package org.db_crud.db_crud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DbCrudApplication {

	public static final Logger log = LoggerFactory.getLogger(DbCrudApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DbCrudApplication.class, args);
		log.info("Aplicação iniciada...");
	}

}
