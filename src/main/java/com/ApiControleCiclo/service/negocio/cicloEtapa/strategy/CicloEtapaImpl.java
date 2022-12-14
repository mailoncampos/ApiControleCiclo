package com.ApiControleCiclo.service.negocio.cicloEtapa.strategy;

import com.ApiControleCiclo.exception.CicloException;
import com.ApiControleCiclo.model.Ciclo;
import com.ApiControleCiclo.model.CicloEtapa;
import com.ApiControleCiclo.service.negocio.cicloEtapa.strategy.criacao.CriarCicloEtapa;


public class CicloEtapaImpl {

	
	private CicloEtapa cicloEtapa;
	
	
	private Ciclo ciclo;
	
	public CicloEtapaImpl(CicloEtapa cicloEtapa, Ciclo ciclo) {
		this.cicloEtapa = cicloEtapa;
		this.ciclo = ciclo;
	}
	
	public CicloEtapa criaCicloEtapa(CriarCicloEtapa criarCicloEtapa) {
		
		Ciclo novoCiclo = criarCicloEtapa.verificarExercicioTermoEQuestionario(this.ciclo);
		
		criarCicloEtapa.verificaCiclosDosExerciciosAnteriores(novoCiclo.getExercicio());
		
		this.ciclo = criarCicloEtapa.verificaCiclosDoAnoDeExercicio(novoCiclo, cicloEtapa);
		
		criarCicloEtapa.verificaStatuCicloParaCricao(this.ciclo, cicloEtapa);
		
		criarCicloEtapa.verificaCicloEtapaDosOutrosCiclos(cicloEtapa, this.ciclo);
		
		criarCicloEtapa.regrasDosStatusParaCricaoDoCicloEtapa(this.ciclo, cicloEtapa);
		
		try {
			
			this.ciclo = criarCicloEtapa.verificaRegrasParaCricaoDoCicloPorTipoDeCicloERealizaPersistencia(this.ciclo, cicloEtapa);				
		
			
		}catch (Exception e) {
			throw new CicloException(e, "Problemas na percistencia do Ciclo");
		}
		this.cicloEtapa = criarCicloEtapa.verificaRegrasDePersistenciaDoCicloEtapa(cicloEtapa, this.ciclo);
				
		return this.cicloEtapa;
	}
	
	public CicloEtapa atualizaCicloEtapa(CriarCicloEtapa criarCicloEtapa) {
		return null;
	}
}
