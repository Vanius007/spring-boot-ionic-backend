package com.vanius.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vanius.cursomc.domain.Pedido;
import com.vanius.cursomc.repositories.PedidoRepository;
import com.vanius.cursomc.services.exceptions.ObjectNotFoundException;



@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	
	public Pedido Buscar(Integer id) {
		
		Pedido obj = repo.findOne(id);
		
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id
					+" , Tipo: "+ Pedido.class.getName());
		}
		
		return obj;
	}
  
}
