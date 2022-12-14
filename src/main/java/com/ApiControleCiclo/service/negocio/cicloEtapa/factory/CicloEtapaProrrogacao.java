package com.ApiControleCiclo.service.negocio.cicloEtapa.factory;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.ApiControleCiclo.model.Ciclo;
import com.ApiControleCiclo.model.CicloEtapa;
import com.ApiControleCiclo.service.negocio.cicloEtapa.strategy.CicloEtapaImpl;
import com.ApiControleCiclo.service.negocio.cicloEtapa.strategy.criacao.CriarCicloEtapaProrrogacao;

@Component("CicloEtapaProrrogacao")
public class CicloEtapaProrrogacao implements CicloEtapaFactory{


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

	@Override
	public CicloEtapa criarCicloEtapa(CicloEtapaImpl cicloEtapaStrategy, ApplicationContext applicationContext) {
		CicloEtapa cicloEtapaSalvo = cicloEtapaStrategy.criaCicloEtapa(new CriarCicloEtapaProrrogacao(applicationContext));
		
		return cicloEtapaSalvo;
	}

}
