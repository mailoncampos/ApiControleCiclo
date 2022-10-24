package com.ApiOnlineSecurity.service.negocio.cicloEtapa.factory;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.ApiOnlineSecurity.model.Ciclo;
import com.ApiOnlineSecurity.model.CicloEtapa;
import com.ApiOnlineSecurity.service.negocio.cicloEtapa.strategy.CicloEtapaImpl;
import com.ApiOnlineSecurity.service.negocio.cicloEtapa.strategy.criacao.CriarCicloEtapaRegular;

@Component("CicloEtapaRegular")
public class CicloEtapaRegular implements CicloEtapaFactory{
	
	
	@Override
	public CicloEtapa criarCicloEtapa(CicloEtapaImpl cicloEtapaStrategy, ApplicationContext applicationContext) {
		
		CicloEtapa cicloEtapaSalvo = cicloEtapaStrategy.criaCicloEtapa(new CriarCicloEtapaRegular(applicationContext));
		
		return cicloEtapaSalvo;
	}

	@Override
	public CicloEtapa atualizarCicloEtapa(CicloEtapa cicloEtapa, Ciclo ciclo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CicloEtapa inativarCicloEtapa(CicloEtapa cicloEtapa, Ciclo ciclo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CicloEtapa suspenderCicloEtapaECiclo(CicloEtapa cicloEtapa, Ciclo ciclo) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
