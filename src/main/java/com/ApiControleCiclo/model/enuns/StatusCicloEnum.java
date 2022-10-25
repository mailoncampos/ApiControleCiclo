package com.ApiControleCiclo.model.enuns;

import org.springframework.context.ApplicationContext;

import com.ApiControleCiclo.service.negocio.cicloEtapa.strategy.monitoramento.AndamentoAberto;
import com.ApiControleCiclo.service.negocio.cicloEtapa.strategy.monitoramento.AndamentoCicloECicloEtapa;
import com.ApiControleCiclo.service.negocio.cicloEtapa.strategy.monitoramento.AndamentoFechado;
import com.ApiControleCiclo.service.negocio.cicloEtapa.strategy.monitoramento.AndamentoPendente;

public enum StatusCicloEnum {
	
	INATIVO {
		@Override
		public AndamentoCicloECicloEtapa contextoCicloEtapa(ApplicationContext applicationContext) {
			// TODO Auto-generated method stub
			return null;
		}
	},
	PENDENTE {
		@Override
		public AndamentoCicloECicloEtapa contextoCicloEtapa(ApplicationContext applicationContext) {
			return applicationContext.getBean(AndamentoPendente.class);
		}
	},
	ABERTO {
		@Override
		public AndamentoCicloECicloEtapa contextoCicloEtapa(ApplicationContext applicationContext) {
			return applicationContext.getBean(AndamentoAberto.class);
		}
	},
	SUSPENSO {
		@Override
		public AndamentoCicloECicloEtapa contextoCicloEtapa(ApplicationContext applicationContext) {
			// TODO Auto-generated method stub
			return null;
		}
	},
	FECHADO {
		@Override
		public AndamentoCicloECicloEtapa contextoCicloEtapa(ApplicationContext applicationContext) {
			return applicationContext.getBean(AndamentoFechado.class);		}
	};
	
	public abstract AndamentoCicloECicloEtapa contextoCicloEtapa(ApplicationContext applicationContext);

}
