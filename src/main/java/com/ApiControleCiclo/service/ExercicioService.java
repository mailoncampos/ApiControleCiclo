package com.ApiControleCiclo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ApiControleCiclo.model.Exercicio;
import com.ApiControleCiclo.repository.ExercicioRepository;

@Service 
public class ExercicioService {
	
	@Autowired
	private ExercicioRepository exercicioRepository;
	
	public Exercicio buscarExercicioPorAno(Long ano) {
		
		Exercicio exercicioSalvo = exercicioRepository.findOneByAnoExercicio(ano);
		return exercicioSalvo;
	}

}
