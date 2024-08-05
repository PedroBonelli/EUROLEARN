package com.eurolearn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eurolearn.models.UsuarioAutenticacaoModel;

public interface UsuarioAutenticacaoRepository extends JpaRepository<UsuarioAutenticacaoModel, Integer> {

}
