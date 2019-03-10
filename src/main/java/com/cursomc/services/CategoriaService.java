package com.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.cursomc.CategoriaDTO;
import com.cursomc.domain.Categoria;
import com.cursomc.repositories.CategoriaRepository;
import com.cursomc.services.exceptions.DataIntegrityException;
import com.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;
	
	public Categoria buscar(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> 
			new ObjectNotFoundException("Não encontrado: " + id + "\nTipo: " + Categoria.class.getName()));
		
	}
	
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Categoria update(Categoria obj) {
		buscar(obj.getId());
		
		return repo.save(obj);
	}
	
	public void delete(Categoria obj) {
		buscar(obj.getId());
		
		try {
			repo.deleteById(obj.getId());
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Essa categoria possui produtos.");
		}
	}
	
	public List<Categoria> listar(){
		return repo.findAll();
	}
	
	public Page<Categoria> buscaPagina(Integer page, Integer linhasPorPag, String ordenarPor, String direcao){
		PageRequest pageReq = PageRequest.of(page, linhasPorPag, Direction.valueOf(direcao), ordenarPor);
		return repo.findAll(pageReq);
	}
	
	public Categoria fromDTO(CategoriaDTO dto) {
		return new Categoria(dto.getId(), dto.getNome());
	}
}
