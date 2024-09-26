package com.eurolearn.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eurolearn.dto.TreinamentoAgendadoDTO;
import com.eurolearn.dto.UsuarioTreinamentoDTO;
import com.eurolearn.models.UsuarioModel;
import com.eurolearn.services.TreinamentoAgendadoService;
import com.eurolearn.services.UsuarioService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/treinamento-usuario")
public class UsuarioTreinamentoController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private TreinamentoAgendadoService treinamentoService;
	
	
	@GetMapping("/{id}")
	public String loadForm(@PathVariable("id") int id , Model model) {
		
		var dto = new UsuarioTreinamentoDTO();
		dto.setTreinamentoId(id);
		model.addAttribute("usuarioTreinamentoDTO", dto);
		model.addAttribute("usuarios", usuarioService.findAll());
		System.out.println(id);
		return "agendamento/vincular-usuarios";
	}
	@PostMapping
	public String insert(@Valid UsuarioTreinamentoDTO dto,
			BindingResult result, RedirectAttributes attributes) {
		UsuarioTreinamentoDTO dtoFinal = treinamentoService.addUsuario(dto);
		return "redirect:/agendamento/" + dtoFinal.getTreinamentoId();
	}
// Usar este com um select multiple, é só settar o field do select como os "cpfs", deve funcionar :)	
//	@PostMapping
//	public String insertMultiple(@Valid UsuarioTreinamentoDTO dto) {
//		int id = treinamentoService.addUsuarioMultiple(dto.getCpfs(), dto.getTreinamentoId());
//		return "redirect:/agendamento/";
//	}
	@PostMapping("/{idAgendamento}/{idUsuario}")
    public String delete(@PathVariable("idAgendamento") int id, @PathVariable("idUsuario") long cpf) {
		System.out.println(cpf);
		System.out.println(id);
    	treinamentoService.deleteUsuario(cpf, id);
    	return "redirect:/agendamento/" + id;
    }
	
}
