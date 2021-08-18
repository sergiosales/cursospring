package com.sales.resources;


import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sales.domain.Cliente;
import com.sales.domain.dto.ClienteDTO;
import com.sales.repository.ClienteRepository;
import com.sales.service.ClienteService;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService service;	
	
	@Autowired
	private ClienteRepository rep;
	
	//@GetMapping("/{codigo}")
	@RequestMapping(value ="/{codigo}",method=RequestMethod.GET)
	public ResponseEntity<Cliente> find(@PathVariable Long codigo) {
		Cliente obj = service.find(codigo);
		
		return ResponseEntity.ok().body(obj);		
		
		
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert( @Valid @RequestBody ClienteDTO clienteDto){
		Cliente categoria = service.fromDTO(clienteDto);
		categoria = service.insert(categoria);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{codigo}")
				.buildAndExpand(categoria.getCodigo()).toUri();
		
		return ResponseEntity.created(uri).build(); 
	}
	
	/*@RequestMapping(value = "/{codigo}",method = RequestMethod.PUT)
//	@ResponseStatus(HttpStatus.NO_CONTENT)
//	@PutMapping("/{codigo}")
	public ResponseEntity<Cliente> update(@PathVariable Long codigo,@Valid @RequestBody Cliente cliente){
	
	 Cliente clienteSalvo = service.update(cliente, codigo);
	 
	
		
		return ResponseEntity.ok(clienteSalvo);
	}*/
	
	@RequestMapping(value = "/{codigo}",method = RequestMethod.PUT)
	public ResponseEntity<Cliente> update(@Valid @RequestBody ClienteDTO clienteDto,@PathVariable Long codigo){
		Cliente obj = service.fromDTO(clienteDto);
		obj.setCodigo(codigo);
		obj= service.update(obj);
		
		return ResponseEntity.ok(obj);
	}
	
	
	
	@RequestMapping(value ="/{codigo}",method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long codigo) {
		service.delete(codigo);
		
		return ResponseEntity.noContent().build();	
		
	}

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ClienteDTO>> findAll() {
		List<Cliente> list = service.findAll();
		List<ClienteDTO> listDto= list.stream().map(obj -> new ClienteDTO(obj) ).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDto);		
		
	}
	
	@RequestMapping(value="/page",method=RequestMethod.GET)
	public ResponseEntity<Page<ClienteDTO>> findPage(
		@RequestParam(value = "page",defaultValue = "0") Integer page,
		@RequestParam(value = "linesPerPage",defaultValue = "24") Integer linesPerPage,
		@RequestParam(value = "orderBy",defaultValue = "nome") String orderBy,
		@RequestParam(value = "direction",defaultValue = "ASC") String direction) {
		Page<Cliente> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<ClienteDTO> listDto= list.map(obj -> new ClienteDTO(obj) );
		
		return ResponseEntity.ok().body(listDto);		
		
	}
	

}
