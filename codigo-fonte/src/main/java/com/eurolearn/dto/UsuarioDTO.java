package com.eurolearn.dto;

import java.sql.Date;

import com.eurolearn.models.TipoTreinamentoModel;
import com.eurolearn.models.UsuarioAutenticacaoModel;
import com.eurolearn.models.UsuarioModel;
import com.eurolearn.models.UsuarioProfissionalModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsuarioDTO {

	private long cpf;
	private long rg; 
	private String nome;
	private String sobrenome;
	private String genero;
	private String email; 
	private Date dataNasc;
	
	private UsuarioProfissionalModel usuarioProfissional;
	private UsuarioAutenticacaoModel usuarioAutenticacao;
	
	//faltam as outras entidades associadas
	
	public UsuarioDTO(UsuarioModel entity) {
		this.cpf = entity.getCpf();
		this.rg = entity.getRg();
		this.nome = entity.getNome();
		this.sobrenome = entity.getSobrenome();
		this.genero = entity.getGenero();
		this.email = entity.getEmail();
		this.dataNasc = entity.getDataNasc();
		
		this.usuarioProfissional = entity.getUsuarioProfissional();
		this.usuarioAutenticacao = entity.getUsuarioAutenticacao();
		
//		for(TreinamentoAgendadoModel treinamentoAgendado : entity.getTreinamentosAgendados()) {
//			this.treinamentosAgendados.add(treinamentoAgendado);
//		}
	}
}
