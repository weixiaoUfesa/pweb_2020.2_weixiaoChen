package com.weixiao.AgroPopShop.AgroPopShop.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weixiao.AgroPopShop.AgroPopShop.model.ItemPedido;
import com.weixiao.AgroPopShop.AgroPopShop.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
             List<Produto> findAllByOrderByNomeAsc(); 
            
             List<Produto> findByItemPedidosIn(List<ItemPedido> itemPedidos);
}
