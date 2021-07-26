package com.sales.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sales.domain.Cidade;
import com.sales.repository.CidadeRepository;
import com.sales.service.exception.ObjectNotFoundException;

@Service
public class CidadeService {
	
	@Autowired
	private CidadeRepository cidades;
	

	public Optional<Cidade> buscar(Long codigo) {
		Optional<Cidade> cid = cidades.findById(codigo);
		if(cid.isEmpty() || cid.get().getCodigo()== null) {
		 throw new ObjectNotFoundException("Cidade não Encontrado! codigo:" + codigo +  " Tipo:" +
		Cidade.class.getName());
				
			
			
		}
		
	return cid;
	}

}
