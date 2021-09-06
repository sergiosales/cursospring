package com.sales.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class SmtpEmailService extends AbstractEmailService{

	private static final Logger Log = LoggerFactory.getLogger(SmtpEmailService.class); 
	
	@Autowired
	private MailSender mailSender;
	
	
	@Override
	public void sendEmail(SimpleMailMessage msg) {
		Log.info("Enviando email...");
		mailSender.send(msg);
		Log.info("Email Enviado!");
		
	}



}
