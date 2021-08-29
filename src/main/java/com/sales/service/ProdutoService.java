package com.sales.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.sales.domain.Categoria;
import com.sales.domain.Produto;
import com.sales.repository.CategoriaRepository;
import com.sales.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtos;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	

	public Optional<Produto> find(Long codigo) {
		Optional<Produto> prod = produtos.findById(codigo);
		
	return prod;
	}
	
	public Page<Produto> search(String nome, List<Long> codigo,Integer page,Integer linesPerPage,String orderBy,String direction){
		
		PageRequest pageRequest = PageRequest.of(page,linesPerPage,Direction.valueOf(direction),orderBy);
		List<Categoria> categorias= categoriaRepository.findAllById(codigo);
		return produtos.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
		
		
	}

}
