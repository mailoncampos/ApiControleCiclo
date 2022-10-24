package com.ApiControleCiclo.service.negocio.cicloEtapa.strategy.criacao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.ApiControleCiclo.exception.CicloException;
import com.ApiControleCiclo.model.Ciclo;
import com.ApiControleCiclo.model.CicloEtapa;
import com.ApiControleCiclo.model.enuns.StatusCicloEnum;
import com.ApiControleCiclo.model.enuns.TipoCicloEtapaEnum;

@Component
public class CriarCicloEtapaProrrogacao extends CriarCicloEtapa{

	public CriarCicloEtapaProrrogacao(ApplicationContext applicationContext) {
		super(applicationContext);
	}

	@Override
	public void verificaStatuCicloParaCricao(Ciclo ciclo, CicloEtapa cicloEtapa) {
		if(ciclo.getIdenCiclo() == null) {
			throw new CicloException(new RuntimeException(), "Não é possivel criar um ciclo do tipo PRORROGAÇÃO pois não existe nenhum ciclo criado", HttpStatus.NO_CONTENT);			
		}
	}

	@Override
	public void regrasDosStatusParaCricaoDoCicloEtapa(Ciclo ciclo, CicloEtapa cicloEtapa) {
		if(this.cicloEtapasDiferenteDeInativaDoCiclo.isEmpty()) {
			listaCiclosEtapasPorCicloComStatusDiferenteDeInativo(ciclo.getIdenCiclo());
		}
		
		if(this.cicloEtapasDiferenteDeInativaDoCiclo.isEmpty()) {
			throw new CicloException(new RuntimeException(), "Não é possivel criar um ciclo etapa do tipo PRORROGAÇÃO pois não existe nenhum ciclo etapa criado", HttpStatus.NO_CONTENT);						
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
					throw new CicloException(new RuntimeException(), "Não é possivel criar um novo ciclo do tipo PRORROGAÇÃO pois existe um ciclo do mesmo tipo com status PENDENTE", HttpStatus.NO_CONTENT);						
				}
			}else if(auxCicloEtapa.getTipoCicloEtapa().equals(TipoCicloEtapaEnum.EXTEMPORANEO)) {
				
			}else if(auxCicloEtapa.getTipoCicloEtapa().equals(TipoCicloEtapaEnum.REGULAR)) {
				
			}
		}
		
	}

	@Override
	public Ciclo verificaRegrasParaCricaoDoCicloPorTipoDeCicloERealizaPersistencia(Ciclo ciclo,
			CicloEtapa cicloEtapa) {
		Ciclo novoCiclo = new Ciclo();
		if(ciclo.getStatusCiclo().equals(StatusCicloEnum.PENDENTE)) {
			novoCiclo = ciclo;
		}else if(ciclo.getStatusCiclo().equals(StatusCicloEnum.ABERTO)) {
			novoCiclo = ciclo;
		}else if(ciclo.getStatusCiclo().equals(StatusCicloEnum.FECHADO)) {
			novoCiclo = ciclo;
		}
		return verificaCicloERealizaPersistencia(novoCiclo, cicloEtapa);
	}

//	@Override
//	protected CicloEtapa verificaRegrasDeCriacaoDoCicloEtapa(Ciclo ciclo, CicloEtapa cicloEtapa) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
