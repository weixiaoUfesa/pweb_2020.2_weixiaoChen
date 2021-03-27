package com.weixiao.cadpessoas.cadastraPessoas.controllers;

import java.security.PublicKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.weixiao.cadpessoas.cadastraPessoas.repositories.PessoaRepository;

@Controller
@RequestMapping ("/")
public class PessoaController {

	@Autowired
	PessoaRepository pessoaRepo;
	
	@GetMapping
	public String index() {
		return "index.html";
		
	}
	 @GetMapping("/listarPessoas")
	 public ModelAndView listarPessoas() {
		 list<Pessoa> list= pessoaRepo.findAll();
		 ModelAndView mav =new ModelAndView("listarPessoas");
				 mav.addObject("Pessoa",lista);
				 return mav;
				 
	 }
	  
}
