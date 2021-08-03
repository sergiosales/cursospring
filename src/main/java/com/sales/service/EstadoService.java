package com.sales.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sales.domain.Estado;
import com.sales.repository.EstadoRepository;
import com.sales.service.exception.ObjectNotFoundException;

@Service
public class EstadoService {
	
	@Autowired
	private EstadoRepository estados;
	

	public Optional<Estado> find(Long codigo) {
		Optional<Estado> uf= estados.findById(codigo);
		if(uf.isEmpty() || uf.get().getCodigo()== null) {
		 throw new ObjectNotFoundException("Estado não Encontrado! codigo:" + codigo +  " Tipo:" +
		Estado.class.getName());
				
			
			
		}
		
	return uf;
	}

}
