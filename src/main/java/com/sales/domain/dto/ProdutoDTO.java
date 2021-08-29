package com.sales.domain.dto;

import java.io.Serializable;

import com.sales.domain.Produto;

public class ProdutoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long codigo;
	private String nome;
	private Double preco;
	
	public ProdutoDTO(){	
		
		
	}
	
	public ProdutoDTO(Produto obj) {
		codigo = obj.getCodigo();
		nome= obj.getNome();
		preco = obj.getPreco();
		
		
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	
}
