package org.controle_empregado_projeto.controle_empregado_projeto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ControleEmpregadoProjetoApplication {

	public static final Logger log = LoggerFactory.getLogger(ControleEmpregadoProjetoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ControleEmpregadoProjetoApplication.class, args);
		log.info("Aplicaçâo iniciada...");
	}

}
