package com.sales.domain.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.sales.domain.Cliente;

public class ClienteDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long codigo;
	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 5,max = 120, message = "O nome deve ter entre 5 a 120 caracteres")
	private String nome;
	@NotEmpty(message = "Preenchimento obrigatório")
	@Email(message = "Email inválido")
	private String email;
	
	public ClienteDTO(Cliente obj) {
		super();
		this.codigo = obj.getCodigo();
		this.nome = obj.getNome();
		this.email = obj.getEmail();
	}

	public ClienteDTO() {
		
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	
	
}
