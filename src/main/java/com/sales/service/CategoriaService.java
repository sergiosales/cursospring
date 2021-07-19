package com.sales.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.sales.domain.Categoria;
import com.sales.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categorias;
	

	public Optional<Categoria> buscar(Long codigo) {
		Optional<Categoria> cate = categorias.findById(codigo);
		
	return cate;
	}

}
