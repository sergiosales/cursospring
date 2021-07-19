package com.sales;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sales.domain.Categoria;
import com.sales.repository.CategoriaRepository;

@SpringBootApplication
public class ProjetoSpringsApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	public static void main(String[] args) {
		SpringApplication.run(ProjetoSpringsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	 Categoria cat  = new Categoria(null, "Informatica");
	 Categoria cat1  = new Categoria(null, "Escritorio");
	 
	 categoriaRepository.saveAll(Arrays.asList(cat,cat1));
		
	}

}
