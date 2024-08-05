package com.eurolearn.models;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "USUARIOSPROFISSIONAL")
public class UsuarioProfissionalModel {
	
	//atributos de relacionamento
	@OneToOne
	private UsuarioModel usuario;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idProfissional;
	private String cargo;
	private String setor; 
	private Date dataAdmissao;
}
