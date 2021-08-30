package com.sales.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.sales.service.DBService;

@Configuration
@Profile("test")
public class TestConfig {
	
	@Autowired
	private DBService DBService;
	
	@Bean
	public boolean instantiateDataBase() throws ParseException {
		DBService.instanciateTestDataBase();
		
		
		return true;
	}

}
