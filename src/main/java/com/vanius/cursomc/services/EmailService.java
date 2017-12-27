package com.vanius.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.vanius.cursomc.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);

}
