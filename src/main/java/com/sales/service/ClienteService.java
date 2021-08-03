package com.sales.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sales.domain.Cliente;
import com.sales.repository.ClienteRepository;
import com.sales.service.exception.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clientes;
	

	public Optional<Cliente> find(Long codigo) {
		Optional<Cliente> clie = clientes.findById(codigo);
		if(clie.isEmpty() || clie.get().getCodigo()== null) {
		 throw new ObjectNotFoundException("Cliente não Encontrado! codigo:" + codigo +  " Tipo:" +
		Cliente.class.getName());
				
			
			
		}
		
	return clie;
	}

}
