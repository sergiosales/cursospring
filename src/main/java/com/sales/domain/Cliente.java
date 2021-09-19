package com.sales.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sales.domain.enuns.Perfil;
import com.sales.domain.enuns.TipoCliente;

@Entity
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	private String Nome;
	private String email;
	private String cpfOuCnpj;
	
	@JsonIgnore
	private String senha;
	
	
	
	private Integer tipo;
	

	@OneToMany(mappedBy = "cliente",cascade = CascadeType.ALL)
	private List<Endereco> enderecos = new ArrayList<Endereco>();
	
	@ElementCollection
	@CollectionTable(name = "Telefone")
	private Set<String> telefones = new HashSet<String>();
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "PERFIS")
	private Set<Integer> perfis = new HashSet<Integer>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "cliente")
	private List<Pedido>pedidos = new ArrayList<Pedido>(); 
	
	public Cliente() {
		addPerfil(Perfil.Cliente);

	}

	public Cliente(Long codigo, String nome, String email, String cpfOuCnpj, TipoCliente tipoCliente,String senha) {
		super();
		this.codigo = codigo;
		this.Nome = nome;
		this.email = email;
		this.cpfOuCnpj = cpfOuCnpj;
		this.tipo = (tipoCliente == null) ? null : tipoCliente.getCod();
		this.senha = senha;
		addPerfil(Perfil.Cliente);
		
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public TipoCliente getTipo() {
		return TipoCliente.toEnum(tipo);
	}

	public void setTipo(TipoCliente tipo) {
		this.tipo = tipo.getCod();
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public Set<String> getTelefones() {
		return telefones;
	}

	public void setTelefones(Set<String> telefones) {
		this.telefones = telefones;
	}
	
	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public Set<Perfil> getPerfis(){
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
		
		 
	}
	
	public void addPerfil(Perfil perfil) {
	perfis.add(perfil.getCod());	

	}

 

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	
}
