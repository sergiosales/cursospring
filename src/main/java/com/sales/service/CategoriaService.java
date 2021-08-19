package com.sales.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.sales.domain.Categoria;
import com.sales.domain.dto.CategoriaDTO;
import com.sales.repository.CategoriaRepository;
import com.sales.service.exception.DataIntegrityException;
import com.sales.service.exception.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categorias;
	

	public Categoria find(Long codigo) {
		Optional<Categoria> cate = categorias.findById(codigo);		
		
	return cate.orElseThrow(() ->  new  ObjectNotFoundException("Categoria não Encontrado! codigo:" + codigo +  " Tipo:" +
			Categoria.class.getName()));
	}


	public Categoria insert(Categoria categoria) {
		categoria.setCodigo(null);
		return categorias.save(categoria);
	}

	public Categoria update(Categoria obj) {
		Categoria newObj = find(obj.getCodigo());

		updateDados(newObj, obj);
		return categorias.save(newObj);
	}

	public void delete(Long codigo) {
		find(codigo);
		try {
			 categorias.deleteById(codigo);
			
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir uma categoria que possui produtos! ");
			
		}
	
		
	}


	public List<Categoria> findAll() {
		
		return categorias.findAll();
	}
	
	public Page<Categoria> findPage(Integer page,Integer linesPerPage,String orderBy,String direction){
		
		PageRequest pageRequest = PageRequest.of(page,linesPerPage,Direction.valueOf(direction),orderBy);	
		return categorias.findAll(pageRequest);
	}
	
	public Categoria fromDTO(CategoriaDTO objDTO) {
		
		return new Categoria(objDTO.getCodigo(), objDTO.getNome());
		
	}
	
	private void updateDados(Categoria newCategoria, Categoria categoria) {
		newCategoria.setNome(categoria.getNome());
		

	}

}
