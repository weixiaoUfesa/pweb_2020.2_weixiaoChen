package com.weixiao.AgroPopShop.AgroPopShop.repositories;

import java.util.List;

import javax.persistence.criteria.Order;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weixiao.AgroPopShop.AgroPopShop.model.Cliente;
import com.weixiao.AgroPopShop.AgroPopShop.model.ItemPedido;
import com.weixiao.AgroPopShop.AgroPopShop.model.Pedido;
import com.weixiao.AgroPopShop.AgroPopShop.model.Venda;


public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {
	List<ItemPedido> findByPedido(Pedido pedido);

	List<ItemPedido> findAllByPedido(Pedido pedido);
	List<ItemPedido> findByPedidoIn(List<Pedido> pedido);
	//ItemPedido findLastItemPedido();
	//ItemPedido findTopByOrderByIdDesc();
	ItemPedido   findTopByOrderByIdItemPedidoDesc();
	ItemPedido   findByIdItemPedido(long id);
}
