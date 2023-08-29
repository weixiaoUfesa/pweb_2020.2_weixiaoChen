package com.weixiao.AgroPopShop.AgroPopShop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weixiao.AgroPopShop.AgroPopShop.model.Dependente;
import com.weixiao.AgroPopShop.AgroPopShop.model.Cliente;
import java.util.List;
import java.util.Optional;


public interface DependenteRepository extends JpaRepository<Dependente, Long> {
	List<Dependente> findByCliente(Cliente cliente);
 
	


}

