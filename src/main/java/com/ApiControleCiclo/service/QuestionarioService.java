package com.ApiControleCiclo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ApiControleCiclo.model.Questionario;
import com.ApiControleCiclo.repository.QuestionarioRepository;

@Service
public class QuestionarioService {
	
	@Autowired
	private QuestionarioRepository questionarioRepository;
	
	public Questionario buscarQuestionarioPorIden(Long idenQuestionario) {
		return questionarioRepository.findById(idenQuestionario).get();
	}

}
