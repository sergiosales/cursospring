package com.sales.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ItemPedido implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@EmbeddedId
	private ItemPedidoPk codigo = new ItemPedidoPk();

	private Double desconto;
	private Double preco;
	private Integer quantidade;

	public ItemPedido() {

	}

	public ItemPedido(Pedido pedido, Produto produto, Double desconto, Double preco, Integer quantidade) {
		super();
		codigo.setPedido(pedido);
		codigo.setProduto(produto);
		this.desconto = desconto;
		this.preco = preco;
		this.quantidade = quantidade;
	}
	
	
	
	@JsonIgnore
	public Pedido getPedido() {

		return codigo.getPedido();
	}


	public Produto getProduto() {

		return codigo.getProduto() ;
	}

	public ItemPedidoPk getCodigo() {
		return codigo;
	}

	public void setCodigo(ItemPedidoPk codigo) {
		this.codigo = codigo;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
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
		ItemPedido other = (ItemPedido) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
	
	

}
