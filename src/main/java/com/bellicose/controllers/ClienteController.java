package com.bellicose.controllers;

import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.bellicose.entity.Cliente;
import com.bellicose.services.IClienteService;

@Controller
@SessionAttributes("cliente")
public class ClienteController {

	@Autowired
//	@Qualifier("clienteDaoJPA")
	private IClienteService clienteService;
	
	@RequestMapping(value="/listar", method=RequestMethod.GET)
	public String listar(Model model){
		model.addAttribute("titulo", "Listado de clientes");
		model.addAttribute("clientes", clienteService.findAll());
		return "listar";
	}
	
	@RequestMapping(value="/form")
	public String crear(Map<String, Object> map){
		Cliente cliente = new Cliente();
		map.put("titulo", "Formulario del cliente");
		map.put("cliente", cliente);
		return "form";
	}
	
	@RequestMapping(value="/form", method=RequestMethod.POST)
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model, SessionStatus status){
		if(result.hasErrors()){
			model.addAttribute("titulo", "Formulario del cliente");
			return "form";
		}
		clienteService.save(cliente);
		status.setComplete();
		return "redirect:listar";
	}
	
	@RequestMapping(value="/form/{id}")
	public String editar(@PathVariable(value="id") Long id, Map<String, Object> model){
		Optional<Cliente> cliente = null;
		if(id > 0){
			cliente = clienteService.findOne(id);
		} else {
			return "redirect:/listar";
		}
		model.put("titulo", "Editar cliente");
		model.put("cliente", cliente);
		
		return "form";
	}
	
	@RequestMapping(value="/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") Long id){
		if(id > 0){
			clienteService.elimina(id);
		}
		return "redirect:/listar";
	}
}
