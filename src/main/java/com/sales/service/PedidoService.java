package com.sales.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sales.domain.ItemPedido;
import com.sales.domain.PagamentoComBoleto;
import com.sales.domain.Pedido;
import com.sales.domain.enuns.EstadoPagamento;
import com.sales.repository.ItemPedidoRepository;
import com.sales.repository.PagamentoRepository;
import com.sales.repository.PedidoRepository;
import com.sales.service.exception.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository rep;
	
	@Autowired 
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private EmailService emailService;
	
	
	
	
	

	public Pedido find(Long codigo) {
		Optional<Pedido> obj = rep.findById(codigo);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + codigo + ", Tipo: " + Pedido.class.getName()));
	}

	@Transactional
	public  Pedido insert( Pedido obj) {
	obj.setCodigo(null);
	obj.setInstante(new Date());
	obj.setCliente(clienteService.find(obj.getCliente().getCodigo()));
	obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
	obj.getPagamento().setPedido(obj); 
	
	if(obj.getPagamento() instanceof PagamentoComBoleto) {
		PagamentoComBoleto pagto =  (PagamentoComBoleto) obj.getPagamento();
		boletoService .preencherPagamentoComBoleto(pagto, obj.getInstante());
		
	}
	
		obj = rep.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		
		for(ItemPedido ip : obj.getItens() ) {
			ip.setDesconto(0.0);
			ip.setProduto(produtoService.find(ip.getProduto().getCodigo()));
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(obj);
			
		}
		
		itemPedidoRepository.saveAll(obj.getItens()); 
		emailService.sendOrderConfirmationEmail(obj);
		emailService.sendOrderConfirmationHtmlEmail(obj);
		return obj;
	
	}

}
