package com.ApiOnlineSecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ApiOnlineSecurity.model.Termo;
import com.ApiOnlineSecurity.repository.TermoRepository;

@Service
public class TermoService {
	
	@Autowired
	private TermoRepository termoRepository;
	
	public Termo buscarTermoPorIden(Long idenTermo) {
		return termoRepository.findById(idenTermo).get();
	}

}
