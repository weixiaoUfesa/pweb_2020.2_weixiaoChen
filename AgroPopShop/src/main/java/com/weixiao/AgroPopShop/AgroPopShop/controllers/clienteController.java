package com.weixiao.AgroPopShop.AgroPopShop.controllers;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.ListableBeanFactory;
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
import com.weixiao.AgroPopShop.AgroPopShop.model.Venda;
import com.weixiao.AgroPopShop.AgroPopShop.model.Pedido;
import com.weixiao.AgroPopShop.AgroPopShop.repositories.ClienteRepository;
import com.weixiao.AgroPopShop.AgroPopShop.repositories.ItemPedidoRepository;
import com.weixiao.AgroPopShop.AgroPopShop.repositories.PedidoRepository;
import com.weixiao.AgroPopShop.AgroPopShop.repositories.ProdutoRepository;
import com.weixiao.AgroPopShop.AgroPopShop.repositories.VendaRepository;
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
	@Autowired
	VendaRepository vendaRepository;
	
	
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
	  C.getIdCliente();
	  
	   
	
	  String string = String.format("redirect:/temp/%o",C.getIdCliente());
	     return new ModelAndView(string);
 
}

//editar_Cliente
//id_cliente  
@GetMapping("/editarCliente/{id}")
public ModelAndView formEditarCliente(@PathVariable("id") long id ) {
	  Cliente aEditar=clienteRepo.findById(id)
			  .orElseThrow(()->new IllegalArgumentException("id inválido:"+id));
	 
	  ModelAndView mav = new ModelAndView("editarCliente.html");
	  mav.addObject(aEditar) ;
	  return mav;
}
//id_cliente  
@PostMapping("/editarCliente/{id}")
public ModelAndView formEditarCliente(@PathVariable("id") long id,Cliente cliente ) {
	
	  
	  cliente.setIdCliente(id);   
	  this.clienteRepo.save(cliente);
	  
	  return new ModelAndView("redirect:/listarClientes");
}

//remover_Cliente
//id_cliente  
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
//id=cliente;
@GetMapping("/temp/{id}")
public ModelAndView temp(@PathVariable("id") long id) {
	
	Cliente cliente = clienteRepo.findByIdCliente(id);
	 ModelAndView mav =new ModelAndView("temp");
	 
    mav.addObject(new Dependente());
	mav.addObject("cliente",cliente);
	mav.addObject(new Pedido());
	return mav;
		}


// adicionar_Dependente
//id_cliente  
@PostMapping("/adicionarDependente/{id}")
public ModelAndView adicionarDependente(@PathVariable("id") long id,Dependente D) {

	  Cliente add=clienteRepo.findById(id)
			  .orElseThrow(()->new IllegalArgumentException("id inválido:"+id));
	  
	  D.setCliente(add);
	  dependenteRepo.save(D);
	  String string = String.format("redirect:/temp/%o",id);
	     return new ModelAndView(string);
	 
	}

	 
      
//exibir_Dependente
//id_cliente        
@GetMapping("/listarDependentes/{id}")
public ModelAndView listarDependetes(@PathVariable("id") long id) {
	Optional<Cliente> opCliente= clienteRepo.findById(id);
	Cliente cliente = opCliente.get();
	List<Dependente> lista = dependenteRepo.findByCliente(cliente);
	ModelAndView mav =new ModelAndView("listarDependentes");
    mav.addObject("dependentes",lista);
				 return mav;
				 }

//editar_Dependente
//id_Dependente
@GetMapping("/editarDependente/{id}") 
public ModelAndView formEditarDependente(@PathVariable("id") long id ) {
	Dependente aEditar=dependenteRepo.findById(id)
			  .orElseThrow(()->new IllegalArgumentException("id inválido:"+id));

	  ModelAndView mav = new ModelAndView("editarDependente.html");
	  mav.addObject(aEditar) ;
	  return mav;
}
//id_Dependente
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
//id_Dependente
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
//exibir_Produto
@GetMapping("/listarProdutos")
public ModelAndView listarProdutos() {
		 List<Produto> lista = produtoRepo.findAllByOrderByNomeAsc();
		 ModelAndView mav =new ModelAndView("listarProdutos");
				 mav.addObject("produtos",lista);
				 return mav;
				 }
//editar_Produto
//id_Produto
@GetMapping("/editarProduto/{id}")
public ModelAndView formEditarProduto(@PathVariable("id") long id ) {
	  Produto aEditar=produtoRepo.findById(id)
			  .orElseThrow(()->new IllegalArgumentException("id inválido:"+id));
	 
	  
	  ModelAndView mav = new ModelAndView("editarProduto.html");
	  mav.addObject(aEditar) ;
	  return mav;
}
//id_Produto
@PostMapping("/editarProduto/{id}")
public ModelAndView formEditarProduto(@PathVariable("id") long id,Produto produto ) {
	  produto.setIdProduto(id);
	  this.produtoRepo.save(produto);
	  
	  return new ModelAndView("redirect:/listarProdutos");
}

//remover_Produto
//id_Produto
@GetMapping("/removerproduto/{id}")
public ModelAndView removerProduto(@PathVariable("id") long id) {
	  Produto aRemover = produtoRepo.findById(id)
			  .orElseThrow(()-> new IllegalArgumentException("ID inválido:"+id));
	  produtoRepo.delete(aRemover);
	  return new ModelAndView("redirect:/listarProdutos");
}

//exibir_paginaDecomprar
//id_pedido
@GetMapping("/paginaComprar/{id}")
public ModelAndView paginaComprar(@PathVariable("id") long id) {
		 List<Produto> listaProduto = produtoRepo.findAllByOrderByNomeAsc();
		 ModelAndView mav =new ModelAndView("paginaComprar");
				 mav.addObject("produtos",listaProduto);
				 pedidoRepo.findByIdPedido(id);
				 
			
				 mav.addObject(new ItemPedido());
                 
				 mav.addObject("idPedido",id);
				 return mav;
				 
				 }


//cadastrar_Pedido
//id_cliente
@PostMapping("/cadastrarPedido/{id}")
public ModelAndView cadastrarPedido(@PathVariable("id") long id,Pedido P) {

	  Cliente addCliente = clienteRepo.findById(id)
			  .orElseThrow(()->new IllegalArgumentException("id inválido:"+id));
	 P.setCliente(addCliente);
	 pedidoRepo.save(P);
	 P.getIdPedido();
     long idPedido = P.getIdPedido();
     String string = String.format("redirect:/paginaComprar/%o", idPedido);
     return new ModelAndView(string);
	}


//adicionar_Produto_NoPedido
//id1=id_Pedido
//id2=id_Produto
@PostMapping("/adicionarNoPedido/{id1}/{id2}")
public ModelAndView adicionarProduto(@PathVariable("id1") long id1,@PathVariable("id2") long id2, ItemPedido itemPedido)
{
     Pedido  pedido = pedidoRepo.findByIdPedido(id1);
     Produto produto = produtoRepo.findByIdProduto(id2);

     ItemPedido novo =new ItemPedido();
     novo.setPedido(pedido);
     novo.setQuantidade(itemPedido.getQuantidade());
     novo.setProduto(produto);	
     novo.setValorUnidade(produto.getPreco());
     this.iPRepo.save(novo);
	 return new ModelAndView("redirect:/paginaComprar/{id1}");}


//listar_pedidos
@GetMapping("/listarPedidos/{id}")public ModelAndView listarPedido(@PathVariable("id") long id) {
	Optional<Cliente> opCliente= clienteRepo.findById(id);
	Cliente cliente = opCliente.get();
	List<Pedido> pedidos = pedidoRepo.findAllByCliente(cliente);
    
    ModelAndView mav =new ModelAndView("listarPedidos");
    mav.addObject("pedidos",pedidos);
return mav;
}



//Exibir_Pedidos.
//id1=id_Pedido
@PostMapping("/confirmarPedido/{id1}")public ModelAndView confirmarPedido(@PathVariable("id1") long id1)
{   
	
	Optional<Pedido> OpPedido=pedidoRepo.findById(id1);
	List<ItemPedido> ItemPedidos= iPRepo.findByPedido(OpPedido.get());
	List<Produto> listaProduto = produtoRepo.findByItemPedidosIn(ItemPedidos);
     
	 ModelAndView mav =new ModelAndView("confirmarPedido");
	         int total=0;
	         double taxa=0.225;
		 Map<ItemPedido,Produto> map =new HashMap<ItemPedido,Produto>();
	         for (int i = 0; i <listaProduto.size(); i++) {
	        	 
	        	 map.put(ItemPedidos.get(i), listaProduto.get(i));
	        	 total = total+ItemPedidos.get(i).getQuantidade()*Integer.parseInt(listaProduto.get(i).getPreco());
	
	         }
	        	
			 mav.addObject("map",map);
			 mav.addObject("total",total);
			 mav.addObject("imposto",(total*taxa));
			 mav.addObject("idPedido",id1);
			
			 return mav;
			 
}
//listar_Itens_do_pedido
//id=pedido
@GetMapping ("/listarItens/{id}")
public ModelAndView listarItens(@PathVariable("id") long id){
	List<ItemPedido> ItemPedidos= iPRepo.findAllByPedido(pedidoRepo.findByIdPedido(id));
	//List<ItemPedido> ItemPedidos= iPRepo.findByPedido(pedidoRepo.findByIdPedido(id));
	List<Produto> listaProduto = produtoRepo.findByItemPedidosIn(ItemPedidos);

	 ModelAndView mav =new ModelAndView("confirmarPedido");
     int total=0;
     double taxa=0.225;
 Map<ItemPedido,Produto> map =new HashMap<ItemPedido,Produto>();
     for (int i = 0; i <listaProduto.size(); i++) {
    	 map.put(ItemPedidos.get(i), listaProduto.get(i));
    	 total = total+ItemPedidos.get(i).getQuantidade()*Integer.parseInt(listaProduto.get(i).getPreco());
        
     }
    	
	 mav.addObject("map",map);
	 mav.addObject("total",total);
	 mav.addObject("imposto",(total*taxa));
	 mav.addObject("idPedido",id);
			
			 return mav;
    
}




//editar_Itens_do_pedido
//id=idItemPedido
@GetMapping("/editarItem/{id}")
public ModelAndView editarItem(@PathVariable("id") long id){ 
   long idItemPedido=id;
   List<Produto> listaProduto = produtoRepo.findAllByOrderByNomeAsc();
	ModelAndView mav =new ModelAndView("adicionarUmItem");
	mav.addObject("produtos",listaProduto);
	mav.addObject("idItemPedido",idItemPedido);
	mav.addObject(new ItemPedido());
	
	return mav;
}
//adicionarUmItem(depois de editar Itens do pedido)
//id1=idItemPedido
//id2=idProduto
@PostMapping("/adicionarUmItem/{id1}/{id2}")
public ModelAndView adicionarUmItem(@PathVariable("id1") long id1,@PathVariable("id2") long id2, ItemPedido itemPedido){
     Produto produto = produtoRepo.findByIdProduto(id2);
     ItemPedido aEditar = iPRepo.findByIdItemPedido(id1);
     long idVelho=aEditar.getIdItemPedido();
     long idPedido= aEditar.getPedido().getIdPedido();
     aEditar.setQuantidade(itemPedido.getQuantidade());
     aEditar.setProduto(produto);
     aEditar.setValorUnidade(produto.getPreco());
     aEditar.setIdItemPedido(idVelho);
                       
	this.iPRepo.save(aEditar);
	                   
	 String string = String.format("redirect:/listarItens/%o", idPedido);
     return new ModelAndView(string);
}
//removerItem
@GetMapping("/removerItem/{id}")
public ModelAndView removerItem(@PathVariable("id") long id){ 
	long idPedido=iPRepo.findByIdItemPedido(id).getPedido().getIdPedido();
	this.iPRepo.deleteById(id);
	
	 String string = String.format("redirect:/listarItens/%o", idPedido);
     return new ModelAndView(string);
}


//editar Pedido
//id=id_ItemPedidos
@GetMapping("/editarPedido/{id}")
public ModelAndView editarPedido(@PathVariable("id") long id){ 
	 
	String string = String.format("redirect:/listarItens/%o",id);
    return new ModelAndView(string);
}

//remover Pedido
//id=id_Pedido
@GetMapping("/removerPedido/{id}")
public ModelAndView removerPedido(@PathVariable("id") long id){ 
	long idCliente=pedidoRepo.findById(id).get().getCliente().getIdCliente();
	
	pedidoRepo.deleteById(id);
    
	
	String string = String.format("redirect:/listarPedidos/%o",idCliente);
    return new ModelAndView(string);
}
//id1=id_Pedido
@PostMapping("/realizarPagamento/{id1}")
public ModelAndView realizarPagamento(@PathVariable("id1") long id1){ 
	Optional<Pedido> OpPedido=pedidoRepo.findById(id1);
	Pedido pedido = OpPedido.get();
	List<ItemPedido> ItemPedidos= iPRepo.findByPedido(pedido);
	List<Produto> lista = produtoRepo.findByItemPedidosIn(ItemPedidos);
	ModelAndView mav =new ModelAndView("realizarPagamento");
	
    int total=0;
    double taxa=0.225;

    for (int i = 0; i < lista.size(); i++) {
   	total=total+Integer.parseInt(lista.get(i).getPreco())*ItemPedidos.get(i).getQuantidade();
		
	}
			 
			
	 
	mav.addObject("Pedido",pedido);

	 mav.addObject("total",total);
	 mav.addObject("imposto",(total*taxa));
	
		          
return mav;
}

//criar_venda
//id_pedido
@PostMapping ("/pagar/{id1}")
public ModelAndView pagar(@PathVariable("id1") long id1,Pedido P){
	            
	  
	     Pedido pedido= pedidoRepo.findByIdPedido(id1);
		 pedido.setCartao(P.getCartao());
		 pedido.setSituacaoPagamento(true);
		 pedido.setIdPedido(id1);
		 this.pedidoRepo.save(pedido);
		 
		 Venda venda=new Venda();
	     venda.setPedido(pedido);                 
	     this.vendaRepository.save(venda);
	      
	ModelAndView mav =new ModelAndView("cadastrarComSucesso");
	return mav;
}
//listar_venda
@GetMapping("/listarVendas")
public ModelAndView listarVendas() {
	List<Venda> vendas = vendaRepository.findAll();
	List<Pedido> pedidos = pedidoRepo.findByVendaIn(vendas);
	ModelAndView mav =new ModelAndView("listarVendas");
    	Map<Venda, Pedido> map= new HashMap<Venda, Pedido>();
    
    for (int i = 0; i <vendas.size(); i++) {
    	map.put(vendas.get(i),pedidos.get(i));
	}
	
	
    mav.addObject("vendas",map);
    
	return mav;
}
//detalhar_venda
//id_venda
             
@GetMapping("/detalhar/{id}")
public ModelAndView detalharVendas(@PathVariable("id") long id) {
	Venda venda=vendaRepository.findByIdVenda(id);
	Pedido pedido= pedidoRepo.findByVenda(venda);
	List<ItemPedido> ItemPedidos = iPRepo.findByPedido(pedido);
	List<Produto> listaProduto = produtoRepo.findByItemPedidosIn(ItemPedidos);
	ModelAndView mav =new ModelAndView("detalharVendas");
	                               
 
    Map<ItemPedido,Produto> map =new HashMap<ItemPedido,Produto>();
     for (int i = 0; i <listaProduto.size(); i++) {
    	 map.put(ItemPedidos.get(i), listaProduto.get(i));
     }
	mav.addObject("vendas",map);
    
	return mav;
}
//remover-Venda
//id=venda
@GetMapping("/removerVenda/{id}")
public ModelAndView removerVenda(@PathVariable("id") long id) {
	ModelAndView mav =new ModelAndView("listarVendas");
	this.vendaRepository.deleteById(id);
	return mav;
}
}
