package com.sales.resources;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sales.domain.Categoria;
import com.sales.service.CategoriaService;

@RestController
@RequestMapping(value="/estados")
public class EstadoResource {
	
	@Autowired
	private CategoriaService service;	
	
	//@GetMapping("/{codigo}")
	@RequestMapping(value ="/{codigo}",method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Long codigo) {
		Optional<Categoria> obj = service.buscar(codigo);
		
		return ResponseEntity.ok().body(obj);
		
		
		
	}

}
