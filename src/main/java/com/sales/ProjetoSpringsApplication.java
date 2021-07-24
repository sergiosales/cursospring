package com.sales;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sales.domain.Categoria;
import com.sales.domain.Produto;
import com.sales.repository.CategoriaRepository;
import com.sales.repository.ProdutoRepository;

@SpringBootApplication
public class ProjetoSpringsApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;

	public static void main(String[] args) {
		SpringApplication.run(ProjetoSpringsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	 Categoria cat  = new Categoria(null, "Informatica");
	 Categoria cat1  = new Categoria(null, "Escritorio");
	 
	 Produto prod= new Produto(null, "impressora", 250.00);
	 Produto prod1= new Produto(null, "Computador", 2500.00);
	 Produto prod2= new Produto(null, "notebook", 4500.00);
	 Produto prod3= new Produto(null, "cadeira giratoria", 800.00);
	 
	 
	 cat1.getProdutos().addAll(Arrays.asList(prod,prod1,prod2));
	 cat.getProdutos().addAll(Arrays.asList(prod3));
	 
	 prod.getCategorias().add(cat);
	 prod1.getCategorias().add(cat);
	 prod2.getCategorias().add(cat);
	 prod3.getCategorias().add(cat1);
	 
	 

	
	 categoriaRepository.saveAll(Arrays.asList(cat,cat1));
	 produtoRepository.saveAll(Arrays.asList(prod,prod1,prod2,prod3));
	}

}
