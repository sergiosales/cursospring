package com.sales.service.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.sales.domain.Cliente;
import com.sales.domain.dto.ClienteNewDTO;
import com.sales.domain.enuns.TipoCliente;
import com.sales.repository.ClienteRepository;
import com.sales.resources.excecption.FieldMessage;
import com.sales.service.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	
	@Autowired
	private ClienteRepository rep;
	
	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
// inclua os testes aqui, inserindo erros na lista
		if(objDto.getTipo().equals(TipoCliente.PessoaFisica.getCod()) && !BR.isValidCpf(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage ("CpfOuCnpj","Cpf Inválido"));
			
		}
		if(objDto.getTipo().equals(TipoCliente.PessoaJuridica.getCod()) && !BR.isValidCnpj(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage ("CpfOuCnpj","Cnpj Inválido"));
			
		}
		
		Cliente auxiliar = rep.findByEmail(objDto.getEmail());
		
		if(auxiliar!= null) {
			
			list.add(new FieldMessage("Email","Email ja existente"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}