package com.sales.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sales.domain.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {




}
