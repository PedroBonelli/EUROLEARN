package com.eurolearn.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eurolearn.dto.GrupoUsuariosDTO;
import com.eurolearn.dto.UsuarioDTO;
import com.eurolearn.services.GrupoUsuariosService;
import com.eurolearn.services.UsuarioService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/grupos")
public class GrupoUsuariosController {

	@Autowired
	public GrupoUsuariosService service; 
	
	@Autowired
	private UsuarioService userService;
	
	//Descomentar assim que desenvolver o treinamento agendado
//	@Autowired
//	private TreinamentoAgendadoService treinamentoService;
	
	@ModelAttribute("usuarios")
	public List<UsuarioDTO> usuarios(){
		return userService.findAll();
	}
	
	//talvez precise de um modelAttribute de treinamentos
	
	@GetMapping()
	public String findAll(Model model) {
		model.addAttribute("grupos", service.findAll());
		return "grupo/listar-grupos";
	}
	
	@GetMapping("/{id}")
	public String findById(@PathVariable("id") int id, Model model) {
		GrupoUsuariosDTO grupo = service.findById(id);
		model.addAttribute("grupoDTO", grupo);
		return "grupo/editar-grupo";
	}
	
	@GetMapping("/form")
	public String loadForm(Model model) {
		model.addAttribute("grupoDTO", new GrupoUsuariosDTO());
		return "grupo/novo-grupo";
	}
	
	@PostMapping
	public String insert(@Valid GrupoUsuariosDTO dto, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			return "grupo/novo-grupo";
		}
		dto = service.insert(dto);
		attributes.addFlashAttribute("mensagem", "grupo criado com sucesso");
		return "redirect:/grupos";
	}
	
	@PutMapping("/{id}")
	public String update(@PathVariable("id") int id, @Valid GrupoUsuariosDTO dto, BindingResult result) {
		if(result.hasErrors()) {
			return "grupo/editar-grupo";
		}
		service.update(dto, id);
		return "redirect:/grupos";
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") int id, RedirectAttributes attributes) {
		service.delete(id);
		attributes.addFlashAttribute("mensagem", "Deletado com sucesso");
		return "redirect:/grupos";
	}
}
