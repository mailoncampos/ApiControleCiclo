package com.ApiControleCiclo.model.enuns;

import org.springframework.context.ApplicationContext;

import com.ApiControleCiclo.service.negocio.cicloEtapa.factory.CicloEtapaFactory;

public enum StatusCicloEnum {
	
	INATIVO,
	PENDENTE,
	ABERTO,
	SUSPENSO,
	FECHADO;
	
//	public abstract CicloEtapaFactory contextoCicloEtapa(ApplicationContext applicationContext);

}
