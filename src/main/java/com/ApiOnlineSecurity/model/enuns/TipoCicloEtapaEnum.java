package com.ApiOnlineSecurity.model.enuns;

import org.springframework.context.ApplicationContext;

import com.ApiOnlineSecurity.exception.CicloException;
import com.ApiOnlineSecurity.service.negocio.cicloEtapa.factory.CicloEtapaExtemporaneo;
import com.ApiOnlineSecurity.service.negocio.cicloEtapa.factory.CicloEtapaFactory;
import com.ApiOnlineSecurity.service.negocio.cicloEtapa.factory.CicloEtapaProrrogacao;
import com.ApiOnlineSecurity.service.negocio.cicloEtapa.factory.CicloEtapaRegular;

public enum TipoCicloEtapaEnum {
	REGULAR(0L, "Regular") {
		@Override
		public CicloEtapaFactory contextoCicloEtapa(ApplicationContext applicationContext) {
			return applicationContext.getBean("CicloEtapaRegular",CicloEtapaRegular.class);
		}
	},
	PRORROGACAO(1L, "Prorrogação") {
		@Override
		public CicloEtapaFactory contextoCicloEtapa(ApplicationContext applicationContext) {
			return applicationContext.getBean("CicloEtapaProrrogacao",CicloEtapaProrrogacao.class);
		}
	},
	EXTEMPORANEO(2L, "Extemporâneo") {
		@Override
		public CicloEtapaFactory contextoCicloEtapa(ApplicationContext applicationContext) {
			return applicationContext.getBean("CicloEtapaExtemporaneo",CicloEtapaExtemporaneo.class);
		}
	};
	
	private Long numero;
	
	private String descricao;
	
	TipoCicloEtapaEnum (Long numero, String descricao) {
		this.numero = numero;
		this.descricao = descricao;
	}
	
	TipoCicloEtapaEnum () {
	}

	public Long getNumero() {
		return numero;
	}

	public String getDescricao() {
		return descricao;
	}

	
	public static TipoCicloEtapaEnum toEnum(Long numero) {
		try {
			for(TipoCicloEtapaEnum aux : TipoCicloEtapaEnum.values()) {
				if(aux.getNumero() == numero) {
					return aux;
				}
			};
			CicloException re = new CicloException(new Throwable(), "teste");
			throw re;
		}catch(CicloException re) {
			throw new CicloException(re, "teste");
		}
	}
	
	public static TipoCicloEtapaEnum toEnum(String descricao) {
		try {
			for(TipoCicloEtapaEnum aux : TipoCicloEtapaEnum.values()) {
				if(aux.getDescricao().equals(descricao)) {
					return aux;
				}else if(aux.toString().equals(descricao)) {
					return aux;
				}
			};
			CicloException re = new CicloException(new Throwable(), "teste");
			throw re;
		}catch(CicloException re) {
			throw new CicloException(re, "teste");
		}
	}
	
	public abstract CicloEtapaFactory contextoCicloEtapa(ApplicationContext applicationContext);
}
