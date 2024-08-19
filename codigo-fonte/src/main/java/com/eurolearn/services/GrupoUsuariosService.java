package com.eurolearn.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eurolearn.dto.GrupoUsuariosDTO;
import com.eurolearn.models.GrupoUsuariosModel;
import com.eurolearn.repository.GrupoUsuariosRepository;
import com.eurolearn.repository.TreinamentoAgendadoRepository;
import com.eurolearn.repository.UsuarioRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class GrupoUsuariosService {

	@Autowired
	private GrupoUsuariosRepository repository; 
	
	public List<GrupoUsuariosDTO> findAll(){
		List<GrupoUsuariosDTO> gruposDTO = new ArrayList<GrupoUsuariosDTO>();
		List<GrupoUsuariosModel> gruposEntity = repository.findAll();
		
		for(GrupoUsuariosModel entity : gruposEntity) {
			gruposDTO.add(new GrupoUsuariosDTO(entity));
		}
		
		return gruposDTO;
	}
	
	public GrupoUsuariosDTO findById(int id) {
		return new GrupoUsuariosDTO(repository.findById(id).orElseThrow(() -> 
			new IllegalArgumentException("Recurso não encontrado - id:"+id)
		));
	}
	
	public GrupoUsuariosDTO insert(GrupoUsuariosDTO dto) {
		GrupoUsuariosModel entity = new GrupoUsuariosModel();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new GrupoUsuariosDTO(entity);
	}
	
	public GrupoUsuariosDTO update(GrupoUsuariosDTO dto, int id) {
		try {
			GrupoUsuariosModel entity = repository.getReferenceById(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new GrupoUsuariosDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new IllegalArgumentException("Recurso não encontrado - id:"+id);
		}
	}
	
	public void delete(int id) {
		if(!repository.existsById(id)) {
			throw new IllegalArgumentException("Produto inválido - id:"+id);
		}
		repository.deleteById(id);
	}
	
	
	
	private void copyDtoToEntity(GrupoUsuariosDTO dto, GrupoUsuariosModel entity) {
		entity.setIdGrupo(dto.getIdGrupo());
		entity.setNomeGrupo(dto.getNomeGrupo());
		entity.setTreinamentosAgendados(dto.getTreinamentosAgendados());
		entity.setUsuarios(dto.getUsuarios());
	}
	
}
