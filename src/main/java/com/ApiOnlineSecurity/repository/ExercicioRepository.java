package com.ApiOnlineSecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ApiOnlineSecurity.model.Exercicio;

@Repository
public interface ExercicioRepository extends JpaRepository<Exercicio, Long>{

	@Query("SELECT e FROM Exercicio e WHERE e.anoExercicio = :anoExercicio")
	public Exercicio findOneByAnoExercicio(Long anoExercicio);
}
