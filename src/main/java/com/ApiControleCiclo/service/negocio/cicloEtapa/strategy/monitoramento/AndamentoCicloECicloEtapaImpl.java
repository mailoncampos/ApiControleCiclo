package com.ApiControleCiclo.service.negocio.cicloEtapa.strategy.monitoramento;

import java.util.Date;

import org.springframework.context.ApplicationContext;

public class AndamentoCicloECicloEtapaImpl extends MonitoraCicloECicloEtapa{
	
	

	public AndamentoCicloECicloEtapaImpl(ApplicationContext applicationContext) {
		super(applicationContext);
		// TODO Auto-generated constructor stub
	}

	public void efetuaAndamentoDoCicloECicloEtapa(Date dataHoraDoAndamento) {
		
		AndamentoCicloECicloEtapa andamentoCicloECicloEtapa = this.cicloAtual.getStatusCiclo().contextoCicloEtapa(applicationContext);
		
			
		andamentoCicloECicloEtapa.efetuaAndamentoDoCicloECicloEtapa(dataHoraDoAndamento, this.cicloAtual.getDataHoraInicio(),this.cicloAtual.getDataHoraFim());
	}

	public Date monitoraAndamentoDoCiclo() {
		return monitoraCiclo();
		
	}
	
	
}
