package com.weixiao.AgroPopShop.AgroPopShop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.weixiao.AgroPopShop.AgroPopShop.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
