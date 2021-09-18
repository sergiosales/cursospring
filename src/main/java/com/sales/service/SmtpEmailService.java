package com.sales.service;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class SmtpEmailService extends AbstractEmailService{

	private static final Logger Log = LoggerFactory.getLogger(SmtpEmailService.class); 
	
	@Autowired
	private MailSender mailSender;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	
	@Override// metodo para  enviar sem html
	public void sendEmail(SimpleMailMessage msg) {
		Log.info("Enviando email...");
		mailSender.send(msg);
		Log.info("Email Enviado!");
		
	}


	@Override // metodo para  enviar com html
	public void sendHtmlEmail(MimeMessage msg) {
		Log.info("Enviando email html...");
		javaMailSender.send(msg);
		Log.info("Email Enviado!");
		
	}



}
