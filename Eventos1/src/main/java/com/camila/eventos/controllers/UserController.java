package com.camila.eventos.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.camila.eventos.models.EventModel;
import com.camila.eventos.models.Provincias;
import com.camila.eventos.models.User;
import com.camila.eventos.models.LogReg;


import com.camila.eventos.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import com.camila.eventos.services.EventService;
@Controller
public class UserController {

	@Autowired
	private final UserService userService;
	
	private final EventService eventService;
	
	public UserController(UserService uSer,EventService eSer) {
		this.userService = uSer;
		this.eventService=eSer;
	}

	@GetMapping("/")
	public String raiz(Model viewModel) {
		viewModel.addAttribute("user", new User());
		viewModel.addAttribute("login", new LogReg());
		viewModel.addAttribute("Provincia", Provincias.provincias);
		return "loginRe.jsp";

	}
	
	@PostMapping("/registro")
public String registro(@Valid @ModelAttribute("user") User usuario,
		BindingResult result, Model viewModel) {
		if(result.hasErrors()) {
			viewModel.addAttribute("login", new LogReg());
			viewModel.addAttribute("provincias", Provincias.provincias);
			return "loginRe.jsp";
		}User usuarioRegistrado = userService.registroUsuario(usuario, result);
		viewModel.addAttribute("login", new LogReg());
		if(usuarioRegistrado != null) {
			viewModel.addAttribute("registro", "Gracias por registrarte, ahora login por favor");
		}
		return "loginRe.jsp";
	}
	
	
	
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("login")LogReg loginuser,
			BindingResult result, Model viewModel, HttpSession sesion) {
		if(result.hasErrors()) {
			viewModel.addAttribute("user",new User());
			return "loginreg.jsp";
			
		}
		if(userService.authenticaterUser(
				loginuser.getEmail(), 
				loginuser.getPassword(), 
				result )) {
			User usuarioLog = userService.encontrarPorEmail(loginuser.getEmail());
			sesion.setAttribute("userID",usuarioLog.getId());
			return "redirect:/events";
		}else {
			viewModel.addAttribute("errorLog","Por favor intenta de nuevo");
			viewModel.addAttribute("user", new User());
			return "loginreg.jsp";
			
		}
	}
		@GetMapping("/events")
		public String bienvenida(@ModelAttribute("evento")EventModel evento,
				BindingResult result,
				HttpSession sesion, Model viewModel) {
			Long userId =(Long) sesion.getAttribute("userID");
			if(userId== null) {
				return "redirect:/";
			}User usuario = userService.encontrarUserPorId(userId);
			viewModel.addAttribute("usuario", usuario);
			viewModel.addAttribute("provincias",Provincias.provincias);
			viewModel.addAttribute("eventoProvinciaUser",eventService.eventoProvinciaUsuario(usuario.getProvincia()));
			viewModel.addAttribute("eventosNoProvinciaUser", eventService.eventoNoProvinciaUsuario(usuario.getProvincia()));
			return "dash.jsp";
		}
	@GetMapping("/logout")
public String logout(HttpSession sesion)
{
		sesion.setAttribute("userID", null);
		return "redirect:/";
}
}
