package com.eurolearn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eurolearn.models.UsuarioModel;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {
	
}
