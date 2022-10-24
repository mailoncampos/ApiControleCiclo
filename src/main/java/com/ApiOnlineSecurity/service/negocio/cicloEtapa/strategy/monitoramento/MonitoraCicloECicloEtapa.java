package com.ApiOnlineSecurity.service.negocio.cicloEtapa.strategy.monitoramento;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;

import com.ApiOnlineSecurity.exception.CicloException;
import com.ApiOnlineSecurity.model.Ciclo;
import com.ApiOnlineSecurity.model.CicloEtapa;
import com.ApiOnlineSecurity.model.enuns.StatusCicloEnum;
import com.ApiOnlineSecurity.service.CicloEtapaService;
import com.ApiOnlineSecurity.service.CicloService;

public abstract class MonitoraCicloECicloEtapa {
	
	protected CicloService cicloService;
	
	protected CicloEtapaService cicloEtapaService;
	
	private CicloEtapa cicloEtapaAtual;
	
	private Ciclo cicloAtual;
	
	private List<Ciclo> ciclosDiferenteDeInativo = new ArrayList<Ciclo>();
	private List<CicloEtapa> cicloEtapasDiferenteDeInativo = new ArrayList<CicloEtapa>();
	
	public MonitoraCicloECicloEtapa(ApplicationContext applicationContext) {
		cicloService = applicationContext.getBean(CicloService.class);
		cicloEtapaService = applicationContext.getBean(CicloEtapaService.class);
		
		this.ciclosDiferenteDeInativo = cicloService.buscarCiclosComStatusDiferenteDeInativo();
		this.cicloEtapasDiferenteDeInativo = cicloEtapaService.buscarCicloEtapasComStatusDiferenteDeInativo();
		
		if(this.ciclosDiferenteDeInativo.isEmpty()) {
			return; //Gerar um log aqui
		}
		if(this.cicloEtapasDiferenteDeInativo.isEmpty()) {
			return; //Gerar um diferente log aqui
		}
		
		this.cicloAtual = informaCicloAtual(this.ciclosDiferenteDeInativo);
		
	}
	
	private Ciclo informaCicloAtual(List<Ciclo> ciclosDiferenteDeInativo) {
		
		List<Ciclo> ciclosPendentes = new ArrayList<Ciclo>();
		List<Ciclo> ciclosAbertos = new ArrayList<Ciclo>();
		List<Ciclo> ciclosFechados = new ArrayList<Ciclo>();
		
		Ciclo cicloAtual = new Ciclo();
		
		for(Ciclo auxCiclo : ciclosDiferenteDeInativo) {
			if(auxCiclo.getStatusCiclo().equals(StatusCicloEnum.PENDENTE)) {
				ciclosPendentes.add(auxCiclo);
			}else if(auxCiclo.getStatusCiclo().equals(StatusCicloEnum.ABERTO)) {
				ciclosAbertos.add(auxCiclo);
			}else if(auxCiclo.getStatusCiclo().equals(StatusCicloEnum.FECHADO)) {
				ciclosFechados.add(auxCiclo);
			}else if(auxCiclo.getStatusCiclo().equals(StatusCicloEnum.SUSPENSO)) {
				throw new CicloException(new RuntimeException(), "Existe um ciclo SUSPENSO");
			}
		}
		
		if(!ciclosPendentes.isEmpty()) {
			if(ciclosPendentes.size() > 1) {
				throw new CicloException(new RuntimeException(), "Inconsistecia na tabela ciclo. Não pode haver mais de um ciclo PENDENTE ao mesmo tempo");
			}	
			if(!ciclosAbertos.isEmpty()) {
				throw new CicloException(new RuntimeException(), "Inconsistecia na tabela ciclo. Não pode haver um ciclo PENDENTE e ABERTO ao mesmo tempo");
			}
			cicloAtual = ciclosPendentes.get(0);
		}
		if(!ciclosAbertos.isEmpty()) {
			if(ciclosAbertos.size() > 1) {
				throw new CicloException(new RuntimeException(), "Existe mais de um ciclo ABERTO");
			}
			cicloAtual = ciclosAbertos.get(0);
		}
		if(!ciclosFechados.isEmpty()) {
			
				if(cicloAtual.getIdenCiclo() != null) {
					for(Ciclo auxCiclo : ciclosFechados) {
						if( (cicloAtual.getExercicio().getAnoExercicio() == auxCiclo.getExercicio().getAnoExercicio()) 
								&& auxCiclo.getDataHoraInicio().after(cicloAtual.getDataHoraFim())) {
							throw new CicloException(new RuntimeException(), "Inconsistecia na tabela ciclo. Existe um ciclo FECHADO");
						}
						
					}					
				}else {
					for(Ciclo auxCiclo : ciclosFechados) {
						if(cicloAtual.getIdenCiclo() == null || auxCiclo.getDataHoraFim().after(cicloAtual.getDataHoraFim()))
							cicloAtual = auxCiclo;
					}
				}
		}
		
		return null;
	}
	
	public void verificarIntegridadeDosCiclos () {
		List<Ciclo> ciclosPendentes = new ArrayList<Ciclo>();
		for(Ciclo auxCiclo : this.ciclosDiferenteDeInativo) {
			if(auxCiclo.getStatusCiclo().equals(StatusCicloEnum.PENDENTE)) {
				ciclosPendentes.add(auxCiclo);
			}else if(auxCiclo.getStatusCiclo().equals(StatusCicloEnum.ABERTO)) {
				
			}else if(auxCiclo.getStatusCiclo().equals(StatusCicloEnum.SUSPENSO)) {
				
			}else if(auxCiclo.getStatusCiclo().equals(StatusCicloEnum.FECHADO)) {
				
			}
		}
		
	}
	
	public void verificarIntegridadeDosCicloEtapas () {
		
	}
	
	protected void efetuaAndamentoDoCicloECicloEtapa() {
		
	}

}
