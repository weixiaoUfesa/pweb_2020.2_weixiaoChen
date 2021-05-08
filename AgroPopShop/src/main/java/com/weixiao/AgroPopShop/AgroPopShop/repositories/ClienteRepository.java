 package com.weixiao.AgroPopShop.AgroPopShop.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.weixiao.AgroPopShop.AgroPopShop.model.Cliente;
import com.weixiao.AgroPopShop.AgroPopShop.model.Dependente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	Cliente findByDependetes(Dependente dependente);
}
