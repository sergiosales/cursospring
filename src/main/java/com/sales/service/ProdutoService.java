package com.sales.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sales.domain.Produto;
import com.sales.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtos;
	

	public Optional<Produto> find(Long codigo) {
		Optional<Produto> prod = produtos.findById(codigo);
		
	return prod;
	}

}
