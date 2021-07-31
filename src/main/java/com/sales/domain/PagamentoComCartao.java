package com.sales.domain;

import javax.persistence.Entity;

import com.sales.domain.enuns.EstadoPagamento;

@Entity
public class PagamentoComCartao extends Pagamento {

	private static final long serialVersionUID = 1L;
	
	private Integer numeroParcelas;

	public PagamentoComCartao() {

	}
	

	public PagamentoComCartao(Long codigo, EstadoPagamento estado, Pedido pedido,Integer numeroParcelas) {
		super(codigo, estado, pedido);

		this.numeroParcelas = numeroParcelas;
	}


	public Integer getNumeroParcelas() {
		return numeroParcelas;
	}

	public void setNumeroParcelas(Integer numeroParcelas) {
		this.numeroParcelas = numeroParcelas;
	}

}
