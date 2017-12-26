package com.vanius.cursomc.repositories;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vanius.cursomc.domain.Categoria;
import com.vanius.cursomc.domain.Produto;

@Transactional(readOnly=true)
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
	
	//@Query("Select distinct obj from Produto obj inner join obj.categorias cat where obj.nome like %:nome% and cat in :categorias")
	Page<Produto> findDistinctByNomeContainingAndCategoriasIn(String nome, List<Categoria> categorias,Pageable pageRequest);

}
