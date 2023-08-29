package com.weixiao.AgroPopShop.AgroPopShop.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weixiao.AgroPopShop.AgroPopShop.model.Cliente;
import com.weixiao.AgroPopShop.AgroPopShop.model.Dependente;
import com.weixiao.AgroPopShop.AgroPopShop.model.ItemPedido;
import com.weixiao.AgroPopShop.AgroPopShop.model.Pedido;
import com.weixiao.AgroPopShop.AgroPopShop.model.Produto;
import com.weixiao.AgroPopShop.AgroPopShop.model.Venda;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{
	Pedido findByCliente(Cliente cliente);
	
	List<Pedido> findAllByCliente(Cliente cliente);
	List<Pedido> findByVendaIn(List<Venda> vendas);
	Pedido findByVenda(Venda venda);
	Pedido findByIdPedido(long id);
	 
}
