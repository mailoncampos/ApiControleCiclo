package com.ApiControleCiclo.service.negocio.cicloEtapa.strategy.criacao;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.ApiControleCiclo.exception.CicloException;
import com.ApiControleCiclo.model.Ciclo;
import com.ApiControleCiclo.model.CicloEtapa;
import com.ApiControleCiclo.model.enuns.StatusCicloEnum;

@Component
public class CriarCicloEtapaRegular extends CriarCicloEtapa{
	
	

	public CriarCicloEtapaRegular(ApplicationContext applicationContext) {
		super(applicationContext);
	}

	@Override
	public void verificaStatuCicloParaCricao(Ciclo ciclo, CicloEtapa cicloEtapa) {

		if(ciclo.getIdenCiclo() != null) {
			if(ciclo.getStatusCiclo() == StatusCicloEnum.ABERTO || ciclo.getStatusCiclo() == StatusCicloEnum.PENDENTE) {
				throw new CicloException(new RuntimeException(), "Não é possivel criar um novo ciclo do tipo REGULAR enquanto existir um "
						+ "ciclo com status " + ciclo.getStatusCiclo().toString() + " para o exercício de " + ciclo.getExercicio().getAnoExercicio().toString());
			}
		}
	}

	@Override
	public void regrasDosStatusParaCricaoDoCicloEtapa(Ciclo ciclo, CicloEtapa cicloEtapa) {
		if(this.cicloEtapasDiferenteDeInativaDoCiclo.isEmpty()) {
			listaCiclosEtapasPorCicloComStatusDiferenteDeInativo(ciclo.getIdenCiclo());
		}
		
		if(this.cicloEtapasDiferenteDeInativaDoCiclo.isEmpty()) {
			//Caso seja o primeiro ciclo etapa			
		}else {
			for(CicloEtapa auxCicloEtapa : this.cicloEtapasDiferenteDeInativaDoCiclo) {
				if(ciclo.getCiclo() != null && ciclo.getCiclo().getIdenCiclo() != null && auxCicloEtapa.getCiclo().getIdenCiclo() ==  ciclo.getIdenCiclo()) {
					if(!auxCicloEtapa.getStatusCicloEtapa().equals(StatusCicloEnum.FECHADO)) {
						throw new CicloException(new RuntimeException(), "Não é possivel criar um novo ciclo do tipo REGULAR enquanto existir "
								+ "um ciclo do tipo " + auxCicloEtapa.getTipoCicloEtapa().toString()+" com status " + auxCicloEtapa.getStatusCicloEtapa().toString());
					}					
				}
			}			
		}	
	}

	@Override
	public Ciclo verificaRegrasParaCricaoDoCicloPorTipoDeCicloERealizaPersistencia(Ciclo ciclo, CicloEtapa cicloEtapa) {
		Ciclo novoCiclo = new Ciclo();
		if(ciclo.getIdenCiclo() == null) {
			novoCiclo = ciclo;
		}else if(ciclo.getStatusCiclo().equals(StatusCicloEnum.FECHADO)) {
			novoCiclo.setCiclo(ciclo);
			novoCiclo.setIdenCiclo(null);
		}
		return verificaCicloERealizaPersistencia(novoCiclo, cicloEtapa);
	}

//	@Override
//	protected CicloEtapa verificaRegrasDeCriacaoDoCicloEtapa(Ciclo ciclo, CicloEtapa cicloEtapa) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
}
