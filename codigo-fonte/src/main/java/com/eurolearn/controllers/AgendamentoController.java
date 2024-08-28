package com.eurolearn.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eurolearn.dto.TreinamentoAgendadoDTO;
import com.eurolearn.services.TipoTreinamentoService;
import com.eurolearn.services.TreinamentoAgendadoService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/agendamento")
public class AgendamentoController {
	
	@Autowired
	private TreinamentoAgendadoService service;
	
	@Autowired
	private TipoTreinamentoService ttservice;
	
	@GetMapping("/form")
	public String loadFormTipoTreinamento(Model model) {
		model.addAttribute("treinamentoAgendadoDTO", new TreinamentoAgendadoDTO());
		model.addAttribute("tiposTreinamento", ttservice.findAll());
		return "agendamento/novo-agendamento";
	}
	
	@PostMapping
	public String insert(@Valid TreinamentoAgendadoDTO dto, BindingResult result, RedirectAttributes attributes) {
		
		if(result.hasErrors()) {
			return "agendamento/novo-agendamento";
		}
		dto = service.insert(dto);
		attributes.addFlashAttribute("mensagem", "Treinamento agendado com sucesso!");
		
		
		return "redirect:/agendamento";
	}
	
	@GetMapping
	public String findAll(Model model) {
		model.addAttribute("treinamentosAgendados",service.findAll());
		return "agendamento/listar-treinamentos-agendados";
		
	}
	
	@GetMapping("/{id}")
	public String findById(@PathVariable("id") int id, Model model) {
		
		TreinamentoAgendadoDTO dto = service.findById(id);
		model.addAttribute("agendamento",dto);
		model.addAttribute("tiposTreinamento", ttservice.findAll());
		return "agendamento/editar-agendamento";
	}
	
	@PutMapping("/{id}")
	public String update(@PathVariable("id") int id, Model model, @Valid TreinamentoAgendadoDTO dto, BindingResult result) {
		
		if(result.hasErrors()) {
			dto.setId(id);
			return "agendamento/editar-agendamento";
		}
		
		return "redirect:/agendamento";
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") int id) {
		service.delete(id);
		return "redirect:/agendamento";
	}
}
