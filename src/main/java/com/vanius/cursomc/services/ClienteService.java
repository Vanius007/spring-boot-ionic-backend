package com.vanius.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vanius.cursomc.domain.Cliente;
import com.vanius.cursomc.repositories.ClienteRepository;
import com.vanius.cursomc.services.exceptions.ObjectNotFoundException;



@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	public Cliente Buscar(Integer id) {
		
		Cliente obj = repo.findOne(id);
		
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id
					+" , Tipo: "+ Cliente.class.getName());
		}
		
		return obj;
	}
  
}
