package com.ApiControleCiclo.service.negocio.cicloEtapa.strategy.monitoramento;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component("AndamentoCicloECicloEtapa")
public interface AndamentoCicloECicloEtapa{
	

	public abstract void efetuaAndamentoDoCicloECicloEtapa(Date dataHoraDoAndamento, Date dataHoraInicio,Date dataHoraFinal);
	

}
