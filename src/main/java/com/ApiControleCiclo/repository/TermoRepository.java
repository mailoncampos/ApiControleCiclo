package com.ApiControleCiclo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ApiControleCiclo.model.Termo;

@Repository
public interface TermoRepository extends JpaRepository<Termo, Long>{

}
