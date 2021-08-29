package com.sales.resources;


import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sales.domain.Pedido;
import com.sales.service.PedidoService;

@RestController
@RequestMapping(value="/pedidos")
public class PedidoResource {
	
	@Autowired
	private PedidoService service;	
	
	//@GetMapping("/{codigo}")
	@RequestMapping(value ="/{codigo}",method=RequestMethod.GET)
	public ResponseEntity<Pedido> find(@PathVariable Long codigo) {
		Pedido obj = service.find(codigo);
		
		return ResponseEntity.ok().body(obj);
	}
	
	@Transactional
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert( @Valid @RequestBody Pedido obj){
	
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{codigo}")
				.buildAndExpand(obj.getCodigo()).toUri();
		
		return ResponseEntity.created(uri).build(); 
	}
	

}
