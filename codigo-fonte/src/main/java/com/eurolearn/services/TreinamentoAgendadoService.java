package com.eurolearn.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eurolearn.dto.TreinamentoAgendadoDTO;
import com.eurolearn.models.AlertaModel;
import com.eurolearn.models.ConfirmacaoPresencaModel;
import com.eurolearn.models.FeedbackModel;
import com.eurolearn.models.GrupoUsuariosModel;
import com.eurolearn.models.JustificativaNaoConfirmacaoModel;
import com.eurolearn.models.TipoTreinamentoModel;
import com.eurolearn.models.TreinamentoAgendadoModel;
import com.eurolearn.repository.AlertaRepository;
import com.eurolearn.repository.ConfirmacaoPresencaRepository;
import com.eurolearn.repository.FeedbackRepository;
import com.eurolearn.repository.GrupoUsuariosRepository;
import com.eurolearn.repository.JustificativaNaoConfirmacaoRepository;
import com.eurolearn.repository.TipoTreinamentoRepository;
import com.eurolearn.repository.TreinamentoAgendadoRepository;
import com.eurolearn.repository.UsuarioRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TreinamentoAgendadoService {

	@Autowired
	TreinamentoAgendadoRepository repository;
	@Autowired
	TipoTreinamentoRepository TTRepository;
	@Autowired
	AlertaRepository alertaRepository;
	JustificativaNaoConfirmacaoRepository justificativaRepository;
	@Autowired
	ConfirmacaoPresencaRepository confirmacaoRepository;
	@Autowired
	FeedbackRepository feedbackRepository;
	@Autowired
	UsuarioRepository usuarioRepository;
	@Autowired
	GrupoUsuariosRepository grupoRepository;

	@Transactional(readOnly = true)
	public List<TreinamentoAgendadoDTO> findAll() {
		List<TreinamentoAgendadoModel> lista = repository.findAll();
		return lista.stream().map(TreinamentoAgendadoDTO::new).collect(Collectors.toList());
	}

	@Transactional
	public TreinamentoAgendadoDTO insert(TreinamentoAgendadoDTO dto) {
		TreinamentoAgendadoModel entity = new TreinamentoAgendadoModel();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new TreinamentoAgendadoDTO(entity);
	}

	@Transactional(readOnly = true)
	public TreinamentoAgendadoDTO findById(int id) {
		TreinamentoAgendadoModel entity = repository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Recurso Inválido"));
		return new TreinamentoAgendadoDTO(entity);
	}

	@Transactional
	public TreinamentoAgendadoDTO update(TreinamentoAgendadoDTO dto) {

		try {
			TreinamentoAgendadoModel entity = new TreinamentoAgendadoModel();
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new TreinamentoAgendadoDTO(entity);
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			throw new IllegalArgumentException("Recurso não encontrado");
		}

	}

	@Transactional
	public void delete(int id) {
		if (!repository.existsById(id)) {
			throw new IllegalArgumentException("Recurso não encontrado");
		}
		try {

			repository.deleteById(id);

		} catch (Exception e) {
			throw new IllegalArgumentException("Não foi possível deletar");
		}
	}

	private void copyDtoToEntity(TreinamentoAgendadoDTO dto, TreinamentoAgendadoModel entity) {
		entity.setNome(dto.getNome());
		entity.setData(dto.getData());
		entity.setLocal(dto.getLocal());

		TipoTreinamentoModel tipoTreinamento = TTRepository
				.getReferenceById(dto.getTipoTreinamento().getIdTipoTreinamento());
		entity.setTipoTreinamento(tipoTreinamento);
		if (entity.getAlertas() != null) {
			entity.getAlertas().clear();
			for (AlertaModel item : dto.getAlertas()) {
				item = alertaRepository.getReferenceById(item.getIdAlerta());
				entity.getAlertas().add(item);
			}
		}

		if (entity.getJustificativas() != null) {
			entity.getJustificativas().clear();
			for (JustificativaNaoConfirmacaoModel item : dto.getJustificativas()) {
				item = justificativaRepository.getReferenceById(item.getIdJustificativa());
				entity.getJustificativas().add(item);
			}
		}
		if (entity.getConfirmacoes() != null) {
			entity.getConfirmacoes().clear();
			for (ConfirmacaoPresencaModel item : dto.getConfirmacoes()) {
				item = confirmacaoRepository.getReferenceById(item.getIdConfirmacao());
				entity.getConfirmacoes().add(item);
			}
		}
		if (entity.getFeedbacks() != null) {
			entity.getFeedbacks().clear();
			for (FeedbackModel item : dto.getFeedbacks()) {
				item = feedbackRepository.getReferenceById(item.getIdFeedback());
				entity.getFeedbacks().add(item);
			}
		}

		// entity.getUsuarios().clear();
		// for(UsuarioModel item : dto.getUsuarios()) {
		// item = usuarioRepository.getReferenceById(item.getCpf());
		// dto.getUsuarios().add(item);
		// }
		if (entity.getGrupos() != null) {
			entity.getGrupos().clear();
			for (GrupoUsuariosModel item : dto.getGrupos()) {
				item = grupoRepository.getReferenceById(item.getIdGrupo());
				dto.getGrupos().add(item);
			}
		}
	}
}
