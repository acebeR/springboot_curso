package com.rebeca.cursomc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rebeca.cursomc.domain.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
