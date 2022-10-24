package com.ApiControleCiclo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ApiControleCiclo.exception.CicloException;
import com.ApiControleCiclo.model.Ciclo;
import com.ApiControleCiclo.repository.CicloRepository;

@Service
public class CicloService {

	@Autowired
	private CicloRepository cicloRepository;
	
	public List<Ciclo> buscarCiclosComStatusDiferenteDeInativo(){
		List<Ciclo> ciclosSalvos = cicloRepository.buscarCiclosComStatusDiferenteDeInativo();
		
		if(ciclosSalvos == null || ciclosSalvos.isEmpty()) {
			return ciclosSalvos = new ArrayList<Ciclo>();
		}
		
		return ciclosSalvos;
	}
	
	public Ciclo salvarCiclo(Ciclo ciclo) {
		Ciclo cicloSalvo = new Ciclo();
		
		cicloSalvo = cicloRepository.save(ciclo);
		if(cicloSalvo == null || cicloSalvo.getIdenCiclo() == null) {
			throw new CicloException(new RuntimeException(), "Não foi possivel salvar o Ciclo");
		}
		return cicloSalvo;
	}
	
}
