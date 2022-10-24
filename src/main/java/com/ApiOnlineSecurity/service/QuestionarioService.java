package com.ApiOnlineSecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ApiOnlineSecurity.model.Questionario;
import com.ApiOnlineSecurity.repository.QuestionarioRepository;

@Service
public class QuestionarioService {
	
	@Autowired
	private QuestionarioRepository questionarioRepository;
	
	public Questionario buscarQuestionarioPorIden(Long idenQuestionario) {
		return questionarioRepository.findById(idenQuestionario).get();
	}

}
