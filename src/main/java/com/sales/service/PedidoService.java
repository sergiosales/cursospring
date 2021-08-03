package com.sales.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sales.domain.Pedido;
import com.sales.repository.PedidoRepository;
import com.sales.service.exception.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidos;
	

	public Optional<Pedido> find(Long codigo) {
		Optional<Pedido> ped = pedidos.findById(codigo);
		if(ped.isEmpty() || ped.get().getCodigo()== null) {
		 throw new ObjectNotFoundException("Pedido não Encontrado! codigo:" + codigo +  " Tipo:" +
		Pedido.class.getName());
				
			
			
		}
		
	return ped;
	}

}
