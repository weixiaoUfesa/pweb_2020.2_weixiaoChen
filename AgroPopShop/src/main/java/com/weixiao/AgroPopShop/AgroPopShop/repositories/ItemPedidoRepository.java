package com.weixiao.AgroPopShop.AgroPopShop.repositories;

import java.util.List;

import javax.persistence.criteria.Order;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weixiao.AgroPopShop.AgroPopShop.model.Cliente;
import com.weixiao.AgroPopShop.AgroPopShop.model.ItemPedido;
import com.weixiao.AgroPopShop.AgroPopShop.model.Pedido;


public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {
	List<ItemPedido> findByPedido(Pedido pedido);

	//ItemPedido findLastItemPedido();
	//ItemPedido findTopByOrderByIdDesc();
	ItemPedido   findTopByOrderByIdItemPedidoDesc();
}
