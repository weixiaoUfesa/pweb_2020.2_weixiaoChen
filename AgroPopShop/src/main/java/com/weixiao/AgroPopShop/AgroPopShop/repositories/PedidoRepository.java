package com.weixiao.AgroPopShop.AgroPopShop.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weixiao.AgroPopShop.AgroPopShop.model.Cliente;
import com.weixiao.AgroPopShop.AgroPopShop.model.Dependente;
import com.weixiao.AgroPopShop.AgroPopShop.model.Pedido;
import com.weixiao.AgroPopShop.AgroPopShop.model.Produto;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{
	Pedido findByCliente(Cliente cliente);
	List<Pedido> findByIdPedido(long id);
}
