package com.vanius.cursomc.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.vanius.cursomc.domain.Pedido;

public abstract class AbstractEmailMessage implements EmailService {
	
	@Value("${default.sender}")
	 private String sender;
	 @Override
     public void sendOrderConfirmationEmail(Pedido obj) {
    	SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(obj); 
    	sendEmail(sm);
     }

	protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido obj) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(obj.getCliente().getEmail());
		sm.setFrom(sender);
		sm.setSubject("Pedido confirmado! "+ obj.getId());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(obj.toString());
		return sm;
	}
}
