package com.vanius.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.vanius.cursomc.domain.Cliente;
import com.vanius.cursomc.dto.ClienteDTO;
import com.vanius.cursomc.repositories.ClienteRepository;
import com.vanius.cursomc.services.exceptions.DataIntegrityException;
import com.vanius.cursomc.services.exceptions.ObjectNotFoundException;



@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	public Cliente find(Integer id) {
		
		Cliente obj = repo.findOne(id);
		
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id
					+" , Tipo: "+ Cliente.class.getName());
		}
		
		return obj;
	}
	
public List<Cliente> findAll(){
		
		return repo.findAll();
		
	}
	
	
	public Cliente update(Cliente obj) {
		
	  Cliente newObj =	find(obj.getId());
	  updateData(newObj, obj);
	  
	  return repo.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		
		try {	
			repo.delete(id);
		} catch (DataIntegrityViolationException e) {
			
			throw new DataIntegrityException("Não é possivel excluir um Cliente pois há entidades relacionadas!");
		}
		
		
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String direction, String orderBy){
		
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		return repo.findAll(pageRequest);
		
	}
	
	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
	}
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
  
}
