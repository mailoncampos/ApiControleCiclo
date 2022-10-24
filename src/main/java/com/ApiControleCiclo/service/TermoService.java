package com.ApiControleCiclo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ApiControleCiclo.model.Termo;
import com.ApiControleCiclo.repository.TermoRepository;

@Service
public class TermoService {
	
	@Autowired
	private TermoRepository termoRepository;
	
	public Termo buscarTermoPorIden(Long idenTermo) {
		return termoRepository.findById(idenTermo).get();
	}

}
