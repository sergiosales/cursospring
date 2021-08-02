package com.sales.resources;


import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sales.domain.Categoria;
import com.sales.service.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;	
	
	//@GetMapping("/{codigo}")
	@RequestMapping(value ="/{codigo}",method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Long codigo) {
		Optional<Categoria> obj = service.buscar(codigo);
		
		return ResponseEntity.ok().body(obj);		
		
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Categoria categoria){

		categoria = service.insert(categoria);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{codigo}")
				.buildAndExpand(categoria.getCodigo()).toUri();
		
		return ResponseEntity.created(uri).build(); 
	}
	
	
	

}
