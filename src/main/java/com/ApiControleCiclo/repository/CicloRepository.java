package com.ApiControleCiclo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ApiControleCiclo.model.Ciclo;

@Repository
public interface CicloRepository extends JpaRepository<Ciclo, Long>{

	@Query("SELECT c FROM Ciclo c WHERE c.statusCiclo != '%INATIVO%'")
	List<Ciclo> buscarCiclosComStatusDiferenteDeInativo();
}
