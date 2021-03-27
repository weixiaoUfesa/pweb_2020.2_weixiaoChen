package com.weixiao.cadpessoas.cadastraPessoas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weixiao.cadpessoas.cadastraPessoas.model.Pessoa;

public interface PessoaRepository  extends JpaRepository<Pessoa, Long>{

}
