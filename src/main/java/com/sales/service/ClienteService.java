package com.sales.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import com.sales.service.exception.*;

import com.sales.domain.Cliente;
import com.sales.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository categorias;
	

	public Optional<Cliente> buscar(Long codigo) {
		Optional<Cliente> clie = categorias.findById(codigo);
		if(clie.isEmpty() || clie.get().getCodigo()== null) {
		 throw new ObjectNotFoundException("Cliente não Encontrado! codigo:" + codigo +  " Tipo:" +
		Cliente.class.getName());
				
			
			
		}
		
	return clie;
	}

}
