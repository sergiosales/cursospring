package com.sales.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sales.domain.Categoria;
import com.sales.repository.CategoriaRepository;
import com.sales.service.exception.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categorias;
	

	public Optional<Categoria> buscar(Long codigo) {
		Optional<Categoria> cate = categorias.findById(codigo);
		if(cate.isEmpty() || cate.get().getCodigo()== null) {
		 throw new ObjectNotFoundException("Categoria não Encontrado! codigo:" + codigo +  " Tipo:" +
		Categoria.class.getName());
				
			
			
		}
		
	return cate;
	}

}
