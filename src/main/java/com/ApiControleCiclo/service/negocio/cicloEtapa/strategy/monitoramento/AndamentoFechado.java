package com.ApiControleCiclo.service.negocio.cicloEtapa.strategy.monitoramento;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.ApiControleCiclo.model.Ciclo;
import com.ApiControleCiclo.model.CicloEtapa;
import com.ApiControleCiclo.model.enuns.StatusCicloEnum;
@Component("AndamentoFechado")
public class AndamentoFechado extends AndamentoCicloECicloEtapaImpl implements AndamentoCicloECicloEtapa{

	public AndamentoFechado(ApplicationContext applicationContext) {
		super(applicationContext);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void efetuaAndamentoDoCicloECicloEtapa(Date dataHoraDoAndamento, Date dataHoraInicio,Date dataHoraFinal) {
		
		
		Ciclo cicloComNovoStatus = this.cicloAtual;
		CicloEtapa cicloEtapaComNovoStatus = this.cicloEtapaAtual;
		
		cicloEtapaComNovoStatus = buscaCicloPendente();
		
		if(cicloEtapaComNovoStatus == null) {
			return;
		}
		
		cicloComNovoStatus.setDataHoraInicio(cicloEtapaComNovoStatus.getDataHoraInicio());
		cicloComNovoStatus.setDataHoraFim(cicloEtapaComNovoStatus.getDataHoraFim());
		cicloComNovoStatus.setStatusCiclo(StatusCicloEnum.PENDENTE);
		
		
		try {
			
			this.cicloService.salvarCiclo(cicloComNovoStatus);
			this.cicloEtapaService.salvarCicloEtapa(cicloEtapaComNovoStatus);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	private CicloEtapa buscaCicloPendente() {
		
		List<CicloEtapa> cicloEtapas = cicloEtapaService.buscarCicloEtapasPorCicloComStatusDiferenteDeInativo(this.cicloAtual.getIdenCiclo());
		
		List<CicloEtapa> cicloEtapasFuturos = new ArrayList<CicloEtapa>();
		cicloEtapasFuturos.clear();
		for(CicloEtapa auxCicloEtapa : cicloEtapas) {
			if(auxCicloEtapa.getDataHoraInicio().after(this.cicloAtual.getDataHoraFim())) {
				cicloEtapasFuturos.add(auxCicloEtapa);
			}
		}
		
		if(cicloEtapasFuturos.isEmpty()) {
			return null;
		}
		CicloEtapa proximoCicloEtapa = cicloEtapasFuturos.get(0);
		for(CicloEtapa auxCicloEtapa : cicloEtapasFuturos) {
			if(proximoCicloEtapa.getDataHoraInicio().after(auxCicloEtapa.getDataHoraInicio())) {
				proximoCicloEtapa = auxCicloEtapa;
			}
		}
		return proximoCicloEtapa;
		
	}
}
