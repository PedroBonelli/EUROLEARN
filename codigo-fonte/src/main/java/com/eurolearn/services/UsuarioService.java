package com.eurolearn.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eurolearn.dto.TipoTreinamentoDTO;
import com.eurolearn.dto.UsuarioDTO;
import com.eurolearn.models.TipoTreinamentoModel;
import com.eurolearn.models.UsuarioModel;
import com.eurolearn.repository.UsuarioRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	@Transactional(readOnly = true)
	public List<UsuarioDTO> findAll(){
			List<UsuarioModel> list = repository.findAll();
			return list.stream().map(UsuarioDTO::new).collect(Collectors.toList());
	}
	
	@Transactional
	public UsuarioDTO insert(UsuarioDTO dto) {
		UsuarioModel entity = new UsuarioModel();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new UsuarioDTO(entity);
	}
	
	 @Transactional(readOnly = true)
	    public UsuarioDTO findById(int id) {

	    	UsuarioModel usuario = repository.findById(id).orElseThrow(
	                () -> new IllegalArgumentException("Recurso inválido - " + id)
	        );
	        return new UsuarioDTO(usuario);
	 }
	 
	 
	 @Transactional
		public UsuarioDTO update(int id, UsuarioDTO dto) {
			try {
				UsuarioModel usuario = repository.getReferenceById(id);
				copyDtoToEntity(dto, usuario);
				usuario = repository.save(usuario);
				return new UsuarioDTO(usuario);
			} catch (EntityNotFoundException e) {
				throw new IllegalArgumentException("Recurso não encontrado"); 
			}
		}
	 
	 @Transactional
		public void delete(int id) {
			if(!repository.existsById(id)) {
				throw new IllegalArgumentException("Tipo de treinamento inválido - id: "+id);
			}
			try {
				repository.deleteById(id);
			} catch (Exception e) {
				throw new IllegalArgumentException("Tipo de treinamento inválido - id: "+id);
			}
		}
	
	
	private void copyDtoToEntity(UsuarioDTO dto, UsuarioModel entity) {
		entity.setCpf(dto.getCpf());
		entity.setRg(dto.getRg());
		entity.setNome(dto.getNome());
		entity.setSobrenome(dto.getSobrenome());
		entity.setGenero(dto.getGenero());
		entity.setEmail(dto.getEmail());
		entity.setDataNasc(dto.getDataNasc());
		
		entity.setUsuarioAutenticacao(dto.getUsuarioAutenticacao());
		entity.setUsuarioProfissional(dto.getUsuarioProfissional());
		
		
		//falta adicionar o código que pega a lista de elementos associados na dto e passa pra entity.
	}
	
	
}
	

