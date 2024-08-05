package com.eurolearn.models;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "USUARIOSAUTENTICACAO")
public class UsuarioAutenticacaoModel {
	
	//atributos de relacionamento
	@OneToOne
	private UsuarioModel usuario;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idAutenticacao;
	private String senha; 
	private Date dataUltimaSenha;
	private String nivelAcesso;
	
}
