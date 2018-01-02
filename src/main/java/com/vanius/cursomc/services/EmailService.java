package com.vanius.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.vanius.cursomc.domain.Cliente;
import com.vanius.cursomc.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
	
	void sendNewPasswordEmail(Cliente cliente, String newPass);

}
