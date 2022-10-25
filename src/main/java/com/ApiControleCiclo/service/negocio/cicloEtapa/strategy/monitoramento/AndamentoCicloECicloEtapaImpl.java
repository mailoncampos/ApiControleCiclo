package com.ApiControleCiclo.service.negocio.cicloEtapa.strategy.monitoramento;

import java.util.Date;

import org.springframework.context.ApplicationContext;

public class AndamentoCicloECicloEtapaImpl extends MonitoraCicloECicloEtapa{
	
	

	public AndamentoCicloECicloEtapaImpl(ApplicationContext applicationContext) {
		super(applicationContext);
		// TODO Auto-generated constructor stub
	}

	public void efetuaAndamentoDoCicloECicloEtapa(Date dataHoraDoAndamento) {
		
		AndamentoCicloECicloEtapa andamentoCicloECicloEtapa = this.cicloEtapaAtual.getStatusCicloEtapa().contextoCicloEtapa(applicationContext);
		
			
		andamentoCicloECicloEtapa.efetuaAndamentoDoCicloECicloEtapa(dataHoraDoAndamento);
	}

	public Date monitoraAndamentoDoCiclo() {
		return monitoraCiclo();
		
	}
	
	
}
