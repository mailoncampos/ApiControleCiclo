package com.ApiControleCiclo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ApiControleCiclo.model.CicloEtapa;

@Repository
public interface CicloEtapaRepository extends JpaRepository<CicloEtapa, Long>{

	@Query("SELECT ce FROM CicloEtapa ce WHERE (ce.statusCicloEtapa != '%INATIVO%' AND ce.ciclo.idenCiclo = :idenCiclo)")
	List<CicloEtapa> buscarCicloEtapasPorCicloComStatusDiferenteDeInativo(Long idenCiclo);
	
	@Query("SELECT ce FROM CicloEtapa ce WHERE (ce.statusCicloEtapa != '%INATIVO%')")
	List<CicloEtapa> buscarCicloEtapasComStatusDiferenteDeInativo();
}
