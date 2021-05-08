package com.weixiao.AgroPopShop.AgroPopShop.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;


import com.weixiao.AgroPopShop.AgroPopShop.model.Cliente;
import com.weixiao.AgroPopShop.AgroPopShop.model.Dependente;
import com.weixiao.AgroPopShop.AgroPopShop.model.ItemPedido;
import com.weixiao.AgroPopShop.AgroPopShop.model.Produto;
import com.weixiao.AgroPopShop.AgroPopShop.model.Pedido;
import com.weixiao.AgroPopShop.AgroPopShop.repositories.ClienteRepository;
import com.weixiao.AgroPopShop.AgroPopShop.repositories.ItemPedidoRepository;
import com.weixiao.AgroPopShop.AgroPopShop.repositories.PedidoRepository;
import com.weixiao.AgroPopShop.AgroPopShop.repositories.ProdutoRepository;
import com.weixiao.AgroPopShop.AgroPopShop.repositories.DependenteRepository;


@Controller
@RequestMapping("/")
public class clienteController {
	@Autowired
	ClienteRepository clienteRepo;
	@Autowired
	ProdutoRepository produtoRepo;
	@Autowired
	ItemPedidoRepository iPRepo;
	@Autowired
	DependenteRepository dependenteRepo;
	@Autowired
	PedidoRepository pedidoRepo;
	
	
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
public ModelAndView adicionarCliente(Cliente C) {
	  this.clienteRepo.save(C);
	  
	
	  
	  return new ModelAndView("redirect:/temp");       
}

//editar_Cliente
@GetMapping("/editarCliente/{id}")
public ModelAndView formEditarCliente(@PathVariable("id") long id ) {
	  Cliente aEditar=clienteRepo.findById(id)
			  .orElseThrow(()->new IllegalArgumentException("id inválido:"+id));
	 
	  ModelAndView mav = new ModelAndView("editarCliente.html");
	  mav.addObject(aEditar) ;
	  return mav;
}
@PostMapping("/editarCliente/{id}")
public ModelAndView formEditarCliente(@PathVariable("id") long id,Cliente cliente ) {
	
	  
	  cliente.setIdCliente(id);   
	  this.clienteRepo.save(cliente);
	  
	  return new ModelAndView("redirect:/listarClientes");
}

//remover_Cliente
@GetMapping("/remover/{id}")
public ModelAndView removerCliente(@PathVariable("id") long id) {
	  Cliente aRemover = clienteRepo.findById(id)
			  .orElseThrow(()-> new IllegalArgumentException("ID inválido:"+id));
	  List<Dependente> serRemoveido =dependenteRepo.findByCliente(aRemover);
	  dependenteRepo.deleteAll(serRemoveido);
	  clienteRepo.delete(aRemover);
	  
	  
	  return new ModelAndView("redirect:/listarClientes");
}



//pagina_temporario 
@GetMapping("/temp")
public ModelAndView temp() {
	List<Cliente> lista = clienteRepo.findAll();
	 ModelAndView mav =new ModelAndView("temp");
    mav.addObject(new Dependente());
	mav.addObject("clientes",lista.get(lista.size()-1));
	return mav;
		}


// adicionar_Dependente
     
@PostMapping("/adicionarDependente/{id}")
public ModelAndView adicionarDependente(@PathVariable("id") long id,Dependente D) {

	  Cliente add=clienteRepo.findById(id)
			  .orElseThrow(()->new IllegalArgumentException("id inválido:"+id));
	  
	  D.setCliente(add);
	  dependenteRepo.save(D);
	  return new ModelAndView("redirect:/temp");
	}

	 
      





//exibir_Dependente
          
@GetMapping("/listarDependentes/{id}")
public ModelAndView listarDependetes(@PathVariable("id") long id) {
	Optional<Cliente> cliente1= clienteRepo.findById(id);
	Cliente cliente = cliente1.get();
	List<Dependente> lista = dependenteRepo.findByCliente(cliente);
	ModelAndView mav =new ModelAndView("listarDependentes");
    mav.addObject("dependentes",lista);
				 return mav;
				 }

//editar_Dependente
@GetMapping("/editarDependente/{id}") 
public ModelAndView formEditarDependente(@PathVariable("id") long id ) {
	Dependente aEditar=dependenteRepo.findById(id)
			  .orElseThrow(()->new IllegalArgumentException("id inválido:"+id));

	  ModelAndView mav = new ModelAndView("editarDependente.html");
	  mav.addObject(aEditar) ;
	  return mav;
}

@PostMapping("/editarDependente/{id}")
public ModelAndView formEditarDependente(@PathVariable("id") long id,Dependente D ) {
	Optional<Dependente> temp= dependenteRepo.findById(id);
	Dependente dependente = temp.get();
	Cliente cliente=clienteRepo.findByDependetes(dependente);
	D.setCliente(cliente);
	D.setIdDependente(id);
	this.dependenteRepo.save(D);
	  
	  return new ModelAndView("redirect:/listarClientes");
}
//remover_Dependente
@GetMapping("/removerDependente/{id}") 
public ModelAndView removerDependente(@PathVariable("id") long id) {
	Dependente aRemover = dependenteRepo.findById(id)
			  .orElseThrow(()-> new IllegalArgumentException("ID inválido:"+id));
	dependenteRepo.delete(aRemover);
	  return new ModelAndView("redirect:/listarClientes");
}


//adicionar_Produto

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
	 
	  
	  ModelAndView mav = new ModelAndView("editarProduto.html");
	  mav.addObject(aEditar) ;
	  return mav;
}
@PostMapping("/editarProduto/{id}")
public ModelAndView formEditarProduto(@PathVariable("id") long id,Produto produto ) {
	  produto.setIdProduto(id);
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

//exibirpaginaDecomprara
@GetMapping("/paginaComprar")
public ModelAndView paginaComprar() {
		 List<Produto> lista = produtoRepo.findAll();
		 ModelAndView mav =new ModelAndView("paginaComprar");
				 mav.addObject("produtos",lista);
				 mav.addObject(new ItemPedido());
				 return mav;
				 }
//add

@PostMapping("/adicionarNoPedido/{id}")
public ModelAndView formEditarProduto(@PathVariable("id") long id,ItemPedido I )
{
	 Produto add=produtoRepo.findById(id)
			  .orElseThrow(()->new IllegalArgumentException("id inválido:"+id));
	 I.setProduto(add);	
	
	 I.setValorUnidade(add.getPreco());
	 this.iPRepo.save(I);
	 return new ModelAndView("redirect:/paginaComprar");
}


}
