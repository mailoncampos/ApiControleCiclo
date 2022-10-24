package com.ApiControleCiclo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.ApiControleCiclo.model.Ciclo;
import com.ApiControleCiclo.model.CicloEtapa;
import com.ApiControleCiclo.repository.CicloEtapaRepository;
import com.ApiControleCiclo.service.negocio.cicloEtapa.factory.CicloEtapaFactory;
import com.ApiControleCiclo.service.negocio.cicloEtapa.strategy.CicloEtapaImpl;

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
