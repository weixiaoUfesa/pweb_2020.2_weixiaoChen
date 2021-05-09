package com.weixiao.AgroPopShop.AgroPopShop.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.postgresql.translation.messages_bg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.sun.org.apache.bcel.internal.generic.NEW;
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
	mav.addObject(new Pedido());
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
		 List<Produto> lista = produtoRepo.findAllByOrderByNomeAsc();
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
@RequestMapping("/paginaComprar/{id}")
public ModelAndView paginaComprar(@PathVariable("id") long id) {
		 List<Produto> lista = produtoRepo.findAllByOrderByNomeAsc();
		 ModelAndView mav =new ModelAndView("paginaComprar");
				 mav.addObject("produtos",lista);
				 
				
				 Optional<Cliente> cliente1=clienteRepo.findById(id);
				 Cliente cliente = cliente1.get();
				
				 Pedido pedido1 =pedidoRepo.findByCliente(cliente);
			    
				
				 ItemPedido novoItempedido	= new ItemPedido();
				 
			     novoItempedido.setPedido(pedido1);
			     this.iPRepo.save(novoItempedido);
				 
				mav.addObject(new ItemPedido());
				
			    long idPedido= pedido1.getIdPedido();
				
				mav.addObject("idPedido", idPedido);
				 return mav;
				 
				 }


//cadastrar_Pedido
@PostMapping("/cadastrarPedido/{id}")
public ModelAndView cadastrarPedido(@PathVariable("id") long id,Pedido P) {

	  Cliente add=clienteRepo.findById(id)
			  .orElseThrow(()->new IllegalArgumentException("id inválido:"+id));
	 P.setCliente(add);
	 pedidoRepo.save(P);
	 P.getIdPedido();
     long idnovo=P.getIdPedido();
     
    
     String string = String.format("redirect:/paginaComprar/%o", idnovo);
     
	  return new ModelAndView(string);
	}


//add

@PostMapping("/adicionarNoPedido/{id1}/{id2}")
public ModelAndView adicionarProduto(@PathVariable("id1") long id1,@PathVariable("id2") long id2, ItemPedido itemPedido)
{
	Optional<Produto> OPprodutro=produtoRepo.findById(id2);
     Produto produto=OPprodutro.get();//produto
     
     //Optional<Pedido> pedido1=pedidoRepo.findById(id1);
     //Pedido pedido =pedido1.get();  
     
     //itemPedido.setPedido(pedido);
     //itemPedido.setProduto(produto);
    
     //ItemPedido addItemPedido=iPRepo.findByPedido(pedido);
     ItemPedido addItemPedido=iPRepo.findTopByOrderByIdItemPedidoDesc();
     long idVelho=addItemPedido.getIdItemPedido();
     
     addItemPedido.setQuantidade(itemPedido.getQuantidade());
     addItemPedido.setProduto(produto);	
     addItemPedido.setValorUnidade(produto.getPreco());
     addItemPedido.setIdItemPedido(idVelho);
     this.iPRepo.save(addItemPedido);
     //itemPedido.getIdItemPedido();
     //this.iPRepo.save(itemPedido);
	 
	 return new ModelAndView("redirect:/paginaComprar/{id1}");}
//Exibir_Pedidos.
@PostMapping("/confirmarPedido/{id1}")public ModelAndView confirmarPedido(@PathVariable("id1") long id1)
{   
	
	Optional<Pedido> OpPedido=pedidoRepo.findById(id1);
	List<ItemPedido> ItemPedidos= iPRepo.findByPedido(OpPedido.get());
	List<Produto> lista = produtoRepo.findByItemPedidosIn(ItemPedidos);
    //List<Produto> lista = produtoRepo.findAll();
	 ModelAndView mav =new ModelAndView("confirmarPedido");
	//mav.addObject("produtos",lista);
	         int total=0;
	         double taxa=0.225;
			 mav.addObject("produtos",lista);
			 Map<Produto,Integer> map = new HashMap<>();
			 for (int i=0;i<lista.size();i++){
			 map.put(lista.get(i),ItemPedidos.get(i).getQuantidade());
			 total=total+Integer.parseInt(lista.get(i).getPreco())*ItemPedidos.get(i).getQuantidade();}
			 mav.addObject("produtos",map);
			 mav.addObject("total",total);
			 mav.addObject("imposto",(total*taxa));
			 //Optional<Cliente> cliente1=clienteRepo.findById(id1);
			// Cliente cliente = cliente1.get();
			
			// Pedido pedido1 =pedidoRepo.findByCliente(cliente);
		    
			
			 //ItemPedido novoItempedido	= new ItemPedido();
			 
		     //novoItempedido.setPedido(pedido1);
		     //this.iPRepo.save(novoItempedido);
			 
			//mav.addObject(new ItemPedido());
			
		    //long idPedido= pedido1.getIdPedido();
			
			//mav.addObject("idPedido", idPedido);
			 return mav;
			 
			 


}

		
		
		              




}
