package com.ApiControleCiclo.service.negocio.cicloEtapa.strategy.monitoramento;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.ApiControleCiclo.model.Ciclo;
import com.ApiControleCiclo.model.CicloEtapa;
import com.ApiControleCiclo.model.enuns.StatusCicloEnum;

@Component("AndamentoPendente")
public class AndamentoPendente extends AndamentoCicloECicloEtapaImpl implements AndamentoCicloECicloEtapa{

	public AndamentoPendente(ApplicationContext applicationContext) {
		super(applicationContext);
	}

	@Override
	public void efetuaAndamentoDoCicloECicloEtapa(Date dataHoraDoAndamento) {
		
		if(this.dataFinalCiclo.equals(LocalDateTime.ofInstant(dataHoraDoAndamento.toInstant(), ZoneId.of("America/Cuiaba")))) {
			return;
		}
		
		Ciclo cicloComNovoStatus = this.cicloAtual;
		CicloEtapa cicloEtapaComNovoStatus = this.cicloEtapaAtual;
		
		cicloComNovoStatus.setStatusCiclo(StatusCicloEnum.ABERTO);
		cicloEtapaComNovoStatus.setStatusCicloEtapa(StatusCicloEnum.ABERTO);
		cicloComNovoStatus.setDataHoraInicio(cicloEtapaComNovoStatus.getDataHoraInicio());
		cicloComNovoStatus.setDataHoraFim(cicloEtapaComNovoStatus.getDataHoraFim());
		
		try {
			
			this.cicloService.salvarCiclo(cicloComNovoStatus);
			this.cicloEtapaService.salvarCicloEtapa(cicloEtapaComNovoStatus);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}


	

	

}
