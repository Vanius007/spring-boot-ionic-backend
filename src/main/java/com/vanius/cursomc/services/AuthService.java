package com.vanius.cursomc.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.vanius.cursomc.domain.Cliente;
import com.vanius.cursomc.repositories.ClienteRepository;
import com.vanius.cursomc.services.exceptions.ObjectNotFoundException;



@Service
public class AuthService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	private Random rand = new Random();
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private EmailService emailService;
	
	public void sendNewPassword(String email) {
		
		Cliente cliente = clienteRepository.findByEmail(email);
		
		if (cliente == null) {
			
			throw new ObjectNotFoundException("Cliente Não Encontrado");
		}
		
		String newPass = newPassword();
		cliente.setSenha(pe.encode(newPass));
		
		clienteRepository.save(cliente);
		emailService.sendNewPasswordEmail(cliente, newPass);
		
		
		
	}

	private String newPassword() {
		char[] vet = new char[10];
		
		for(int i=0; i<10; i++) {
			vet[i] = randomChar();
		}
				
		return new String(vet);
	}

	private char randomChar() {
		
		int opt = rand.nextInt(3);
		
		if (opt == 0) { //Gera um Digito
			
		   return  (char) (rand.nextInt(10) + 48);	
			
		}else if(opt == 1){ // Gera letra maiuscula
			
			return  (char) (rand.nextInt(26) + 65);	
			
		}else { // Gera letra minuscula
			return  (char) (rand.nextInt(26) + 97);
		}
		
		
	}

}
