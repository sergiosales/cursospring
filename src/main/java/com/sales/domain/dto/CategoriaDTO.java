package com.sales.domain.dto;

import java.io.Serializable;

import com.sales.domain.Categoria;

public class CategoriaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long codigo;
	private String nome;

	public CategoriaDTO() {

	}

	public CategoriaDTO(Categoria cate) {
		this.codigo = cate.getCodigo();
		this.nome = cate.getNome();

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

}
