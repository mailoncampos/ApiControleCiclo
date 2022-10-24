package com.ApiOnlineSecurity.service.negocio.cicloEtapa.strategy.criacao;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.ApiOnlineSecurity.exception.CicloException;
import com.ApiOnlineSecurity.model.Ciclo;
import com.ApiOnlineSecurity.model.CicloEtapa;
import com.ApiOnlineSecurity.model.enuns.StatusCicloEnum;
import com.ApiOnlineSecurity.model.enuns.TipoCicloEtapaEnum;

@Component
public class CriarCicloEtapaExtemporaneo extends CriarCicloEtapa{

	public CriarCicloEtapaExtemporaneo(ApplicationContext applicationContext) {
		super(applicationContext);
	}

	@Override
	public void verificaStatuCicloParaCricao(Ciclo ciclo, CicloEtapa cicloEtapa) {
		//Liberado em todos os status
		
	}

	@Override
	public void regrasDosStatusParaCricaoDoCicloEtapa(Ciclo ciclo, CicloEtapa cicloEtapa) {
		if(this.cicloEtapasDiferenteDeInativaDoCiclo.isEmpty()) {
			listaCiclosEtapasPorCicloComStatusDiferenteDeInativo(ciclo.getIdenCiclo());
		}
		
		if(this.cicloEtapasDiferenteDeInativaDoCiclo.isEmpty()) {
			return;
		}
		
		CicloEtapa cicloEtapaAtual = new CicloEtapa();
		List<CicloEtapa> cicloEtapasRestante = new ArrayList<CicloEtapa>();
		for(CicloEtapa auxCicloEtapa : this.cicloEtapasDiferenteDeInativaDoCiclo) {
			if(auxCicloEtapa.getDataHoraInicio().equals(ciclo.getDataHoraInicio()) && auxCicloEtapa.getDataHoraFim().equals(ciclo.getDataHoraFim())) {
				cicloEtapaAtual = auxCicloEtapa;
			}
		}
		
		cicloEtapasRestante.add(cicloEtapaAtual);
		for(CicloEtapa auxCicloEtapa : this.cicloEtapasDiferenteDeInativaDoCiclo) {
			if(cicloEtapaAtual.getDataHoraFim().before(auxCicloEtapa.getDataHoraFim())) {
				cicloEtapasRestante.add(auxCicloEtapa);
			}
		}
		
		for(CicloEtapa auxCicloEtapa : cicloEtapasRestante) {
			
			if(auxCicloEtapa.getTipoCicloEtapa().equals(TipoCicloEtapaEnum.PRORROGACAO)) {
				if(auxCicloEtapa.getStatusCicloEtapa().equals(StatusCicloEnum.PENDENTE)) {
					throw new CicloException(new RuntimeException(), "Não é possivel criar um novo ciclo do tipo EXTEMPORANEO pois existe um ciclo do tipo PRORROGAÇÃO com status PENDENTE", HttpStatus.NO_CONTENT);						
				}
			}else if(auxCicloEtapa.getTipoCicloEtapa().equals(TipoCicloEtapaEnum.EXTEMPORANEO)) {
				if(auxCicloEtapa.getStatusCicloEtapa().equals(StatusCicloEnum.PENDENTE)) {
					throw new CicloException(new RuntimeException(), "Não é possivel criar um novo ciclo do tipo EXTEMPORANEO pois existe um ciclo do mesmo tipo com status PENDENTE", HttpStatus.NO_CONTENT);						
				}
			}else if(auxCicloEtapa.getTipoCicloEtapa().equals(TipoCicloEtapaEnum.REGULAR)) {
				if(auxCicloEtapa.getStatusCicloEtapa().equals(StatusCicloEnum.PENDENTE)) {
					throw new CicloException(new RuntimeException(), "Não é possivel criar um novo ciclo do tipo EXTEMPORANEO pois existe um ciclo do tipo REGULAR com status PENDENTE", HttpStatus.NO_CONTENT);						
				}
			}
		}
		
	}

	@Override
	public Ciclo verificaRegrasParaCricaoDoCicloPorTipoDeCicloERealizaPersistencia(Ciclo ciclo,
			CicloEtapa cicloEtapa) {
		Ciclo novoCiclo = new Ciclo();
		if(ciclo.getIdenCiclo() == null) {
			novoCiclo = ciclo;
		}else if(ciclo.getStatusCiclo().equals(StatusCicloEnum.FECHADO)) {
			if(ciclo.getDataHoraFim().before(Date.from(Instant.now()))) {
				novoCiclo.setCiclo(ciclo);
				novoCiclo.setIdenCiclo(null);
			}
		}else {
			novoCiclo = ciclo;
		}
		return verificaCicloERealizaPersistencia(novoCiclo, cicloEtapa);
	}

}
