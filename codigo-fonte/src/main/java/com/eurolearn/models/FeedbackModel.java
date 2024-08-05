package com.eurolearn.models;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="FEEDBACKS")
public class FeedbackModel {
	
	//atributos de relacionamento
	@ManyToOne
	@JoinColumn(name="idTreinamentoAgendado", nullable = true)
	private TreinamentoAgendadoModel treinamentoAgendado;
	
	@ManyToOne
	@JoinColumn(name="cpf", nullable = true)
	private UsuarioModel usuario;
	
	@Id
	private int idFeedback;
	
	private double indiceSatisfacao;
	private Date dataFeedback;
	
}
