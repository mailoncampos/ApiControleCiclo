package com.ApiOnlineSecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ApiOnlineSecurity.model.Exercicio;
import com.ApiOnlineSecurity.repository.ExercicioRepository;

@Service 
public class ExercicioService {
	
	@Autowired
	private ExercicioRepository exercicioRepository;
	
	public Exercicio buscarExercicioPorAno(Long ano) {
		
		Exercicio exercicioSalvo = exercicioRepository.findOneByAnoExercicio(ano);
		return exercicioSalvo;
	}

}
