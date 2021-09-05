package com.sales.service;

import org.springframework.mail.SimpleMailMessage;

import com.sales.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido obj);
	void sendEmail(SimpleMailMessage msg);

}
