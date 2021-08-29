package com.sales.resources;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sales.domain.Produto;
import com.sales.domain.dto.CategoriaDTO;
import com.sales.domain.dto.ProdutoDTO;
import com.sales.resources.utils.URL;
import com.sales.service.ProdutoService;

@RestController
@RequestMapping(value="/produtos")
public class ProdutoResource {
	
	@Autowired
	private ProdutoService service;	
	
	//@GetMapping("/{codigo}")
	@RequestMapping(value ="/{codigo}",method=RequestMethod.GET)
	public ResponseEntity<Optional<Produto>> find(@PathVariable Long codigo) {
		Optional<Produto> prod = service.find(codigo);
		
		return ResponseEntity.ok().body(prod);
		
		
		
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>> findPage(
			@RequestParam(value = "nome", defaultValue = "") String nome,
			@RequestParam(value = "categorias", defaultValue = "") String categorias,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		String nomeDecoded = URL.decodeParam(nome);
		List<Long>ids = URL.decodeIntList(categorias);
		Page<Produto> list = service.search(nomeDecoded,ids,page, linesPerPage, orderBy, direction);
		Page<ProdutoDTO> listDto = list.map(obj -> new ProdutoDTO(obj));

		return ResponseEntity.ok().body(listDto);		
		
	}

}
