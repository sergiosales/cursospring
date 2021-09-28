package com.sales.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sales.domain.Cliente;
import com.sales.repository.ClienteRepository;
import com.sales.security.UserSS;


@Service
public class UserDetailServiceImp implements UserDetailsService {

	@Autowired
	private ClienteRepository rep;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Cliente clie = rep.findByEmail(email);
		if(clie == null) {
			throw new UsernameNotFoundException(email);
			
		}
		
		return new UserSS(clie.getCodigo(), clie.getEmail(), clie.getSenha(), clie.getPerfis());
	}

}
