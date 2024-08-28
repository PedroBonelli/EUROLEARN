package com.eurolearn.dto;

import java.sql.Date;
import java.util.List;

import com.eurolearn.models.AlertaModel;
import com.eurolearn.models.ConfirmacaoPresencaModel;
import com.eurolearn.models.FeedbackModel;
import com.eurolearn.models.GrupoUsuariosModel;
import com.eurolearn.models.JustificativaNaoConfirmacaoModel;
import com.eurolearn.models.TipoTreinamentoModel;
import com.eurolearn.models.TreinamentoAgendadoModel;
import com.eurolearn.models.UsuarioModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TreinamentoAgendadoDTO {
	private TipoTreinamentoModel tipoTreinamento;
	
	private List<AlertaModel> alertas;
	
	private List<JustificativaNaoConfirmacaoModel>	justificativas;
	
	private List<ConfirmacaoPresencaModel> confirmacoes;
	
	private List<FeedbackModel> feedbacks;
	
	private List<UsuarioModel> usuarios;
	
	private List<GrupoUsuariosModel> grupos;
	
	private int id;
	private String nome;
	private Date data;
	private String local;
	
	public TreinamentoAgendadoDTO(TreinamentoAgendadoModel entity) {
		this.id = entity.getIdTreinamentoAgendado();
		this.nome = entity.getNome();
		this.data = entity.getData();
		this.local = entity.getLocal();
		this.alertas = entity.getAlertas();
		this.tipoTreinamento = entity.getTipoTreinamento();
		this.justificativas = entity.getJustificativas();
		this.confirmacoes = entity.getConfirmacoes();
		this.feedbacks = entity.getFeedbacks();
		this.usuarios = entity.getUsuarios();
		this.grupos = entity.getGrupos();
	}
	
	
}
