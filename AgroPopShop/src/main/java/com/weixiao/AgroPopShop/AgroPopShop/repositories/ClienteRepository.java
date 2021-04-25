 package com.weixiao.AgroPopShop.AgroPopShop.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.weixiao.AgroPopShop.AgroPopShop.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
