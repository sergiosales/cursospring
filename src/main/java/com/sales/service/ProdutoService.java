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
import com.sales.service.exception.ObjectNotFoundException;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtos;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	

/*	public Optional<Produto> find(Long codigo) {
		Optional<Produto> prod = produtos.findById(codigo);
		
	return prod;
	}*/
	

	public Produto find(Long codigo) {
		Optional<Produto> obj = produtos.findById(codigo);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Produto não encontrado! Id: " + codigo + ", Tipo: " + Produto.class.getName()));
	}

	
	public Page<Produto> search(String nome, List<Long> codigo,Integer page,Integer linesPerPage,String orderBy,String direction){
		
		PageRequest pageRequest = PageRequest.of(page,linesPerPage,Direction.valueOf(direction),orderBy);
		List<Categoria> categorias= categoriaRepository.findAllById(codigo);
		return produtos.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
		
		
	}

}
