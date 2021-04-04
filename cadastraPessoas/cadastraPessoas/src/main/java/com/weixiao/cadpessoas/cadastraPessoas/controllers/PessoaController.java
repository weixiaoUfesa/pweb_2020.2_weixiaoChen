package com.weixiao.cadpessoas.cadastraPessoas.controllers;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.weixiao.cadpessoas.cadastraPessoas.model.Pessoa;
import com.weixiao.cadpessoas.cadastraPessoas.repositories.PessoaRepository;

import javassist.expr.NewArray;

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
		 List<Pessoa> lista = pessoaRepo.findAll();
		 ModelAndView mav =new ModelAndView("listarPessoas");
				 mav.addObject("pessoas",lista);
				 return mav;
				  }
	 
	  @GetMapping("/adicionarPessoa")
	  public ModelAndView formAdicionarPessoa() {
		  ModelAndView modelAndView= new ModelAndView("adicionarPessoa");
		  modelAndView.addObject(new Pessoa());
		  System.out.println ("caonima");
		  return modelAndView;
	
	  }
	  
	  @PostMapping("/adicionarPessoa")
	  public String adicionarPessoa(Pessoa p) {
		 
		  String str=p.getTemp();
		  DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
		  LocalDate date = LocalDate.parse(str, fmt);
		  
		  
		  p.setDataNascimento(date);
		  this.pessoaRepo.save(p);
		  return "redirect:/listarPessoas";
	  }
	  
	  
	  
	  @GetMapping("/adicionadaComSucesso")
	  public String adc() {
		  return"adicionadaComSucesso.html";
	  }
	  
	  @GetMapping("/remover/{id}")
	  public ModelAndView removerPessoa(@PathVariable("id") long id) {
		  Pessoa aRemover = pessoaRepo.findById(id)
				  .orElseThrow(()-> new IllegalArgumentException("ID inválido:"+id));
		  pessoaRepo.delete(aRemover);
		  return new ModelAndView("redirect:/listarPessoas");
	  }
}
