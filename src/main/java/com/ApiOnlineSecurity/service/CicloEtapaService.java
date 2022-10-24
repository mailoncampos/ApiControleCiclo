package com.ApiOnlineSecurity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.ApiOnlineSecurity.model.Ciclo;
import com.ApiOnlineSecurity.model.CicloEtapa;
import com.ApiOnlineSecurity.repository.CicloEtapaRepository;
import com.ApiOnlineSecurity.service.negocio.cicloEtapa.factory.CicloEtapaFactory;
import com.ApiOnlineSecurity.service.negocio.cicloEtapa.strategy.CicloEtapaImpl;

@Service
public class CicloEtapaService {
	
	@Autowired
	ApplicationContext applicationContext;
	
	@Autowired
	private CicloEtapaRepository cicloEtapaRepository;

	public CicloEtapa salvar (CicloEtapa cicloEtapa, Ciclo ciclo) {
		
		CicloEtapaFactory cicloEtapaFactory = cicloEtapa.getTipoCicloEtapa().contextoCicloEtapa(applicationContext);
		
		return cicloEtapaFactory.criarCicloEtapa(new CicloEtapaImpl(cicloEtapa, ciclo), applicationContext);
	}
	
	public List<CicloEtapa>buscarCicloEtapasComStatusDiferenteDeInativo(){
		return cicloEtapaRepository.buscarCicloEtapasComStatusDiferenteDeInativo();
	}
	
	public List<CicloEtapa> buscarCicloEtapasPorCicloComStatusDiferenteDeInativo(Long idenCiclo){
		return cicloEtapaRepository.buscarCicloEtapasPorCicloComStatusDiferenteDeInativo(idenCiclo);
	}
	
	public CicloEtapa salvarCicloEtapa(CicloEtapa cicloEtapa) {
		return cicloEtapaRepository.save(cicloEtapa);
	}
}
