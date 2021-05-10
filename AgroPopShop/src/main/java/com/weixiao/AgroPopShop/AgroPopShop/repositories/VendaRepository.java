package com.weixiao.AgroPopShop.AgroPopShop.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weixiao.AgroPopShop.AgroPopShop.model.ItemPedido;
import com.weixiao.AgroPopShop.AgroPopShop.model.Venda;

public interface VendaRepository extends JpaRepository<Venda, Long> {
 Venda findByIdVenda (long id);
}
