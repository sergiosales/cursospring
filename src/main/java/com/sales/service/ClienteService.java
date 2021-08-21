package com.sales.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sales.domain.Cidade;
import com.sales.domain.Cliente;
import com.sales.domain.Endereco;
import com.sales.domain.dto.ClienteDTO;
import com.sales.domain.dto.ClienteNewDTO;
import com.sales.domain.enuns.TipoCliente;
import com.sales.repository.CidadeRepository;
import com.sales.repository.ClienteRepository;
import com.sales.repository.EnderecoRepository;
import com.sales.service.exception.DataIntegrityException;
import com.sales.service.exception.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clientes;
	
	@Autowired
	private EnderecoRepository enderecoRepository;

	public Cliente find(Long codigo) {
		Optional<Cliente> clie = clientes.findById(codigo);

		return clie.orElseThrow(() -> new ObjectNotFoundException(
				"Cliente não Encontrado! codigo:" + codigo + " Tipo:" + Cliente.class.getName()));

	}

	public void delete(Long codigo) {
		find(codigo);
		try {
			clientes.deleteById(codigo);

		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir uma cliente que possui Pedidos! ");

		}

	}

	public List<Cliente> findAll() {

		return clientes.findAll();
	}
	
	
	@Transactional
	public Cliente insert(Cliente cliente) {
		cliente.setCodigo(null);
		cliente = clientes.save(cliente);
		enderecoRepository.saveAll(cliente.getEnderecos());
	
		
		return cliente;
	}
	

	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getCodigo());

		updateDados(newObj, obj);
		return clientes.save(newObj);
	}


	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {

		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return clientes.findAll(pageRequest);
	}

	public Cliente fromDTO(ClienteDTO objDTO) {
		return new Cliente(objDTO.getCodigo(), objDTO.getNome(), objDTO.getEmail(), null, null);

	}
	
	public Cliente fromDTO(ClienteNewDTO objDTO) {
		Cliente cli = new Cliente(null, objDTO.getNome(), objDTO.getEmail(), objDTO.getCpfOuCnpj(), TipoCliente.toEnum(objDTO.getTipo()));
		Cidade cid = new Cidade(objDTO.getCidadeId(), null, null);
		Endereco end = new Endereco(null, objDTO.getLogradouro(), objDTO.getNumero(), objDTO.getComplemento(), objDTO.getBairro(), objDTO.getCep(), cli, cid);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDTO.getTelefone1());
		if(objDTO.getTelefone2()!= null) {
			cli.getTelefones().add(objDTO.getTelefone2());
			
		}
		if(objDTO.getTelefone3()!= null) {
			cli.getTelefones().add(objDTO.getTelefone3());
			
		}
		return cli;

	}
	
	private void updateDados(Cliente newCliente, Cliente cliente) {
		newCliente.setNome(cliente.getNome());
		newCliente.setEmail(cliente.getEmail());

	}

}
