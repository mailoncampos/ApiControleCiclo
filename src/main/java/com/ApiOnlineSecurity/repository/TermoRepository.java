package com.ApiOnlineSecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ApiOnlineSecurity.model.Termo;

@Repository
public interface TermoRepository extends JpaRepository<Termo, Long>{

}
