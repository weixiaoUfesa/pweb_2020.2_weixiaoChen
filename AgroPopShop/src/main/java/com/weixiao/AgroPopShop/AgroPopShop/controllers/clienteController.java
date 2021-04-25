package com.weixiao.AgroPopShop.AgroPopShop.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.weixiao.AgroPopShop.AgroPopShop.model.Cliente;
import com.weixiao.AgroPopShop.AgroPopShop.model.Produto;
import com.weixiao.AgroPopShop.AgroPopShop.repositories.ClienteRepository;
import com.weixiao.AgroPopShop.AgroPopShop.repositories.ProdutoRepository;


@Controller
@RequestMapping("/")
public class clienteController {
	@Autowired
	ClienteRepository clienteRepo;
	@Autowired
	ProdutoRepository produtoRepo;
	@GetMapping
	public String index() {
		return "index.html";
		
	}
//exibir
@GetMapping("/listarClientes")
public ModelAndView listarClientes() {
		 List<Cliente> lista = clienteRepo.findAll();
		 ModelAndView mav =new ModelAndView("listarClientes");
				 mav.addObject("clientes",lista);
				 return mav;
				 }
//criar
@GetMapping("/adicionarCliente")
public ModelAndView formAdicionarCliente() {
		  ModelAndView modelAndView= new ModelAndView("adicionarCliente");
		  modelAndView.addObject(new Cliente());
		  return modelAndView;}
      
@PostMapping("/adicionarCliente")
public String adicionarCliente(Cliente C) {
	  this.clienteRepo.save(C);
	  return "cadastrarComSucesso";
}

//editar
@GetMapping("/editarCliente/{id}")
public ModelAndView formEditarCliente(@PathVariable("id") long id ) {
	  Cliente aEditar=clienteRepo.findById(id)
			  .orElseThrow(()->new IllegalArgumentException("id inválido:"+id));
	  Cliente aRemover = clienteRepo.findById(id)
			  .orElseThrow(()-> new IllegalArgumentException("ID inválido:"+id));
	  clienteRepo.delete(aRemover);
	  
	  ModelAndView mav = new ModelAndView("editarCliente.html");
	  mav.addObject(aEditar) ;
	  return mav;
}
@PostMapping("/editarCliente/{id}")
public ModelAndView formEditarCliente(@PathVariable("id") long id,Cliente cliente ) {
	  
	  this.clienteRepo.save(cliente);
	  
	  return new ModelAndView("redirect:/listarClientes");
}

//remover
@GetMapping("/remover/{id}")
public ModelAndView removerCliente(@PathVariable("id") long id) {
	  Cliente aRemover = clienteRepo.findById(id)
			  .orElseThrow(()-> new IllegalArgumentException("ID inválido:"+id));
	  clienteRepo.delete(aRemover);
	  return new ModelAndView("redirect:/listarClientes");
}


//criar
@GetMapping("/adicionarProduto")
public ModelAndView formAdicionarProduto() {
		  ModelAndView modelAndView= new ModelAndView("adicionarProduto");
		  modelAndView.addObject(new Produto());
		  return modelAndView;}
    
@PostMapping("/adicionarProduto")
public String adicionarProduto(Produto P) {
	  this.produtoRepo.save(P);
	                  
	  return "cadastrarComSucesso";
}           
//exibir
@GetMapping("/listarProdutos")
public ModelAndView listarProdutos() {
		 List<Produto> lista = produtoRepo.findAll();
		 ModelAndView mav =new ModelAndView("listarProdutos");
				 mav.addObject("produtos",lista);
				 return mav;
				 }
//editar
@GetMapping("/editarProduto/{id}")
public ModelAndView formEditarProduto(@PathVariable("id") long id ) {
	  Produto aEditar=produtoRepo.findById(id)
			  .orElseThrow(()->new IllegalArgumentException("id inválido:"+id));
	  Produto aRemover = produtoRepo.findById(id)
			  .orElseThrow(()-> new IllegalArgumentException("ID inválido:"+id));
	  produtoRepo.delete(aRemover);
	  
	  ModelAndView mav = new ModelAndView("editarProduto.html");
	  mav.addObject(aEditar) ;
	  return mav;
}
@PostMapping("/editarProduto/{id}")
public ModelAndView formEditarProduto(@PathVariable("id") long id,Produto produto ) {
	  
	  this.produtoRepo.save(produto);
	  
	  return new ModelAndView("redirect:/listarProdutos");
}

//remover
@GetMapping("/removerproduto/{id}")
public ModelAndView removerProduto(@PathVariable("id") long id) {
	  Produto aRemover = produtoRepo.findById(id)
			  .orElseThrow(()-> new IllegalArgumentException("ID inválido:"+id));
	  produtoRepo.delete(aRemover);
	  return new ModelAndView("redirect:/listarProdutos");
}



}
