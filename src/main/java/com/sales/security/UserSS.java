package com.sales.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.sales.domain.enuns.*;

public class UserSS  implements UserDetails{
	

	private static final long serialVersionUID = 1L;
	
	
	private Long codigo;
	private String email;
	private String senha;
	
	
	private Collection<? extends GrantedAuthority> authorities;
	
	public UserSS() {
		
		
	}
	
	
	
	
	public UserSS(Long codigo, String email, String senha,Set<Perfil> perfis) {
		super();
		this.codigo = codigo;
		this.email = email;
		this.senha = senha;
		this.authorities = perfis.stream().map(x -> new SimpleGrantedAuthority(x.getDescricao())).collect(Collectors.toList());
		
		
		
		
		
		
	}




	//retorna uma id
	public Long getCodigo() {
		
		return codigo;
	}
	
	
	
	
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return authorities ;
	}

	@Override
	public String getPassword() {

		return senha;
	}

	@Override
	public String getUsername() {
		
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		return true;
	}
	
}
