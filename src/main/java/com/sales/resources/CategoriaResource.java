package com.sales.resources;


import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sales.domain.Categoria;
import com.sales.domain.dto.CategoriaDTO;
import com.sales.service.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;	
	
	//@GetMapping("/{codigo}")
	@RequestMapping(value ="/{codigo}",method=RequestMethod.GET)
	public ResponseEntity<Optional<Categoria>> find(@PathVariable Long codigo) {
		Optional<Categoria> obj = service.find(codigo);
		
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
	
	@RequestMapping(value = "/{codigo}",method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Categoria categoria, @PathVariable Long codigo){
		categoria.setCodigo(codigo);
		categoria = service.update(categoria);
		
		return ResponseEntity.noContent().build();
	}
	
	
	@RequestMapping(value ="/{codigo}",method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long codigo) {
		service.delete(codigo);
		
		return ResponseEntity.noContent().build();	
		
	}

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<CategoriaDTO>> findAll() {
		List<Categoria> list = service.findAll();
		List<CategoriaDTO> listDto= list.stream().map(obj -> new CategoriaDTO(obj) ).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDto);		
		
	}
	
	
}
