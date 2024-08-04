package com.springboot.crudspring1.Repository;

import com.springboot.crudspring1.Model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
    Optional<Pessoa> findByNome(String nome);
}
