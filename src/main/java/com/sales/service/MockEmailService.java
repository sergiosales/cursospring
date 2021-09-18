package com.sales.service;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

public class MockEmailService extends AbstractEmailService {
	
	private static final Logger Log = LoggerFactory.getLogger(MockEmailService.class); 	
	
	

	@Override
	public void sendEmail(SimpleMailMessage msg) {
	Log.info("Simulando envio de email...");
	Log.info(msg.toString());
	Log.info("Email Enviado!");
		
	}



	@Override //com html
	public void sendHtmlEmail(MimeMessage msg) {
		Log.info("Simulando envio de email html...");
		Log.info(msg.toString());
		Log.info("Email Enviado!");
		
	}

}
