package com.vanius.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vanius.cursomc.domain.Categoria;
import com.vanius.cursomc.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria Buscar(Integer id) {
		
		Categoria obj = repo.findOne(id);
		return obj;
	}
  
}
