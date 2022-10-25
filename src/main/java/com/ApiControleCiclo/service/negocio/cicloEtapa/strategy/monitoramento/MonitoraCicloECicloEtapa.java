package com.ApiControleCiclo.service.negocio.cicloEtapa.strategy.monitoramento;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.ApiControleCiclo.exception.CicloException;
import com.ApiControleCiclo.model.Ciclo;
import com.ApiControleCiclo.model.CicloEtapa;
import com.ApiControleCiclo.model.enuns.StatusCicloEnum;
import com.ApiControleCiclo.service.CicloEtapaService;
import com.ApiControleCiclo.service.CicloService;

@Component
public abstract class MonitoraCicloECicloEtapa {
	
	@Autowired
	ApplicationContext applicationContext;

	@Autowired
	protected CicloService cicloService;

	@Autowired
	protected CicloEtapaService cicloEtapaService;

	protected CicloEtapa cicloEtapaAtual;

	protected Ciclo cicloAtual;

	protected List<Ciclo> ciclosDiferenteDeInativo = new ArrayList<Ciclo>();

	protected List<CicloEtapa> cicloEtapasDiferenteDeInativo = new ArrayList<CicloEtapa>();
	
	protected LocalDateTime dataInicioCiclo;
	
	protected LocalDateTime dataFinalCiclo;

	public MonitoraCicloECicloEtapa(ApplicationContext applicationContext) {
		cicloService = applicationContext.getBean(CicloService.class);
		cicloEtapaService = applicationContext.getBean(CicloEtapaService.class);
		
		this.ciclosDiferenteDeInativo.clear();
		this.cicloEtapasDiferenteDeInativo.clear();
		this.ciclosDiferenteDeInativo = cicloService.buscarCiclosComStatusDiferenteDeInativo();
		this.cicloEtapasDiferenteDeInativo = cicloEtapaService.buscarCicloEtapasComStatusDiferenteDeInativo();

		if (this.ciclosDiferenteDeInativo.isEmpty()) {
			throw new CicloException(new RuntimeException(), "Não existe nenhum ciclo ativo");

		}
		if (this.cicloEtapasDiferenteDeInativo.isEmpty()) {
			throw new CicloException(new RuntimeException(), "Não existe nenhum ciclo etapa ativo");
		}

		this.cicloAtual = informaCicloAtual(this.ciclosDiferenteDeInativo);
		this.cicloEtapaAtual = informaCicloEtapaAtual(this.cicloEtapasDiferenteDeInativo, this.cicloAtual);
		
		this.applicationContext = applicationContext;
	}

	private Ciclo informaCicloAtual(List<Ciclo> ciclosDiferenteDeInativo) {

		List<Ciclo> ciclosPendentes = new ArrayList<Ciclo>();
		List<Ciclo> ciclosAbertos = new ArrayList<Ciclo>();
		List<Ciclo> ciclosFechados = new ArrayList<Ciclo>();

		Ciclo cicloAtual = new Ciclo();

		for (Ciclo auxCiclo : ciclosDiferenteDeInativo) {
			if (auxCiclo.getStatusCiclo().equals(StatusCicloEnum.PENDENTE)) {
				ciclosPendentes.add(auxCiclo);
			} else if (auxCiclo.getStatusCiclo().equals(StatusCicloEnum.ABERTO)) {
				ciclosAbertos.add(auxCiclo);
			} else if (auxCiclo.getStatusCiclo().equals(StatusCicloEnum.FECHADO)) {
				ciclosFechados.add(auxCiclo);
			} else if (auxCiclo.getStatusCiclo().equals(StatusCicloEnum.SUSPENSO)) {
				throw new CicloException(new RuntimeException(), "Existe um ciclo SUSPENSO");
			}
		}

		if (!ciclosPendentes.isEmpty()) {
			if (ciclosPendentes.size() > 1) {
				throw new CicloException(new RuntimeException(),
						"Inconsistecia na tabela ciclo. Não pode haver mais de um ciclo PENDENTE ao mesmo tempo");
			}
			if (!ciclosAbertos.isEmpty()) {
				throw new CicloException(new RuntimeException(),
						"Inconsistecia na tabela ciclo. Não pode haver um ciclo PENDENTE e ABERTO ao mesmo tempo");
			}
			cicloAtual = ciclosPendentes.get(0);
		}
		if (!ciclosAbertos.isEmpty()) {
			if (ciclosAbertos.size() > 1) {
				throw new CicloException(new RuntimeException(), "Existe mais de um ciclo ABERTO");
			}
			cicloAtual = ciclosAbertos.get(0);
		}
		if (!ciclosFechados.isEmpty()) {

			if (cicloAtual.getIdenCiclo() != null) {
				for (Ciclo auxCiclo : ciclosFechados) {
					if ((cicloAtual.getExercicio().getAnoExercicio() == auxCiclo.getExercicio().getAnoExercicio())
							&& auxCiclo.getDataHoraInicio().after(cicloAtual.getDataHoraFim())) {
						throw new CicloException(new RuntimeException(),
								"Inconsistecia na tabela ciclo. Existe um ciclo FECHADO posterior ao " + "ciclo aberto "
										+ cicloAtual.getStatusCiclo().toString() + " para o " + "exercício de "
										+ cicloAtual.getExercicio().getAnoExercicio().toString());
					}
				}
			} else {
				for (Ciclo auxCiclo : ciclosFechados) {
					if (cicloAtual.getIdenCiclo() == null
							|| auxCiclo.getDataHoraFim().after(cicloAtual.getDataHoraFim()))
						cicloAtual = auxCiclo;
				}
			}
		}

		return cicloAtual;
	}

	private CicloEtapa informaCicloEtapaAtual(List<CicloEtapa> cicloEtapasDiferenteDeInativo, Ciclo cicloAtual) {

		List<CicloEtapa> ciclosEtapaPendentes = new ArrayList<CicloEtapa>();
		List<CicloEtapa> ciclosEtapaAbertos = new ArrayList<CicloEtapa>();
		List<CicloEtapa> ciclosEtapaFechados = new ArrayList<CicloEtapa>();

		CicloEtapa cicloEtapaAtual = new CicloEtapa();

		if (cicloAtual == null || cicloAtual.getIdenCiclo() == null) {
			throw new CicloException(new RuntimeException(), "Não existe um ciclo ativo");
		}

		for (CicloEtapa auxCicloEtapa : cicloEtapasDiferenteDeInativo) {
			if (auxCicloEtapa.getStatusCicloEtapa().equals(StatusCicloEnum.PENDENTE)) {
				ciclosEtapaPendentes.add(auxCicloEtapa);
			} else if (auxCicloEtapa.getStatusCicloEtapa().equals(StatusCicloEnum.ABERTO)) {
				ciclosEtapaAbertos.add(auxCicloEtapa);
			} else if (auxCicloEtapa.getStatusCicloEtapa().equals(StatusCicloEnum.FECHADO)) {
				ciclosEtapaFechados.add(auxCicloEtapa);
			} else if (auxCicloEtapa.getStatusCicloEtapa().equals(StatusCicloEnum.SUSPENSO)) {
				throw new CicloException(new RuntimeException(), "Existe um ciclo SUSPENSO");
			}
		}

		if (!ciclosEtapaPendentes.isEmpty()) {
			for (CicloEtapa auxCicloEtapa : ciclosEtapaPendentes) {
				if (cicloAtual.getIdenCiclo() != auxCicloEtapa.getCiclo().getIdenCiclo()) {
					throw new CicloException(new RuntimeException(), "Existe um ciclo PENDENTE para outro ciclo");
				}
				if (cicloEtapaAtual == null || cicloEtapaAtual.getIdenCicloEtapa() == null
						|| (auxCicloEtapa.getDataHoraInicio().equals(cicloAtual.getDataHoraInicio()))
								&& auxCicloEtapa.getDataHoraFim().equals(cicloAtual.getDataHoraFim())
								&& auxCicloEtapa.getStatusCicloEtapa().equals(cicloAtual.getStatusCiclo())) {
					cicloEtapaAtual = auxCicloEtapa;
				}
			}
		}
		if (!ciclosEtapaAbertos.isEmpty() && ciclosEtapaAbertos.size() > 1) {
			throw new CicloException(new RuntimeException(),
					"Inconsistecia na tabela ciclo. Existe um ciclo FECHADO posterior ao " + "ciclo aberto "
							+ cicloAtual.getStatusCiclo().toString() + " para o " + "exercício de "
							+ cicloAtual.getExercicio().getAnoExercicio().toString());
		}else if (!ciclosEtapaAbertos.isEmpty()){
			cicloEtapaAtual = ciclosEtapaAbertos.get(0);
		}
		if (!ciclosEtapaFechados.isEmpty()) {

			if (cicloEtapaAtual.getIdenCicloEtapa() == null) {
				for (CicloEtapa auxCicloEtapa : ciclosEtapaFechados) {
					if (cicloEtapaAtual.getIdenCicloEtapa() == null || auxCicloEtapa.getDataHoraFim().after(cicloEtapaAtual.getDataHoraFim())) {
						cicloEtapaAtual = auxCicloEtapa;
					}
				}
			} else {
				for (CicloEtapa auxCicloEtapa : ciclosEtapaFechados) {
					if (auxCicloEtapa.getDataHoraFim().after(cicloEtapaAtual.getDataHoraFim())) {
						throw new CicloException(new RuntimeException(),
								"Existe um ciclo FECHADO posterior ao " + "ciclo etapa "
										+ cicloEtapaAtual.getStatusCicloEtapa().toString() + " para o ciclo "
										+ cicloAtual.getCiclo().getNomeCiclo());
					}
				}
			}

		}

		return cicloEtapaAtual;
	}
	
	public Date monitoraCiclo() {
		
		LocalDateTime dataAtualInicio = LocalDateTime.of(LocalDate.now(), LocalTime.of(0, 0));
		LocalDateTime dataAtualFinal = LocalDateTime.of(LocalDate.now(), LocalTime.of(23, 59));
		
		this.dataInicioCiclo = LocalDateTime.ofInstant(this.cicloAtual.getDataHoraInicio().toInstant(), ZoneId.of("America/Cuiaba"));
		this.dataFinalCiclo = LocalDateTime.ofInstant(this.cicloAtual.getDataHoraFim().toInstant(), ZoneId.of("America/Cuiaba"));
		
		if(dataAtualInicio.isBefore(this.dataInicioCiclo) && dataAtualFinal.isAfter(this.dataInicioCiclo) && this.dataFinalCiclo.isAfter(LocalDateTime.now())) {
			 return this.cicloAtual.getDataHoraInicio();
		}else if(dataAtualInicio.isBefore(this.dataFinalCiclo) && dataAtualFinal.isAfter(this.dataFinalCiclo)) {
			return this.cicloAtual.getDataHoraFim();
		}
		
		fechaCiclosAbertosIndevidamente();
		
		return null;
	}
	
	private void fechaCiclosAbertosIndevidamente() {
		for(CicloEtapa auxCicloEtapa : this.cicloEtapasDiferenteDeInativo) {
			if(auxCicloEtapa.getDataHoraFim().before(Date.from(Instant.now()))) {
				if(auxCicloEtapa.getStatusCicloEtapa().equals(StatusCicloEnum.ABERTO) 
						|| auxCicloEtapa.getStatusCicloEtapa().equals(StatusCicloEnum.PENDENTE)) {
					
					CicloEtapa cicloEtapaComNovoStatus = auxCicloEtapa;
					
					cicloEtapaComNovoStatus.setStatusCicloEtapa(StatusCicloEnum.FECHADO);
					
					try {
						this.cicloEtapaService.salvarCicloEtapa(cicloEtapaComNovoStatus);
					} catch (Exception e) {
						// TODO: handle exception
					}
					
				}
			}
		}
		for(Ciclo auxCiclo : this.ciclosDiferenteDeInativo) {
			if(auxCiclo.getDataHoraFim().before(Date.from(Instant.now()))) {
				if(auxCiclo.getStatusCiclo().equals(StatusCicloEnum.ABERTO) 
						|| auxCiclo.getStatusCiclo().equals(StatusCicloEnum.PENDENTE)) {
					Ciclo cicloComNovoStatus = auxCiclo;
					
					cicloComNovoStatus.setStatusCiclo(StatusCicloEnum.FECHADO);
					
					try {
						this.cicloService.salvarCiclo(cicloComNovoStatus);
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}
		}
	}
	
	public CicloEtapa buscaCicloEtapaPendente() {
		CicloEtapa cicloEtapaPendente = new CicloEtapa();
		for(CicloEtapa auxCicloEtapa : this.cicloEtapasDiferenteDeInativo) {
			if(cicloEtapaPendente.getIdenCicloEtapa() == null
					|| (auxCicloEtapa.getStatusCicloEtapa().equals(StatusCicloEnum.PENDENTE) 
					&& auxCicloEtapa.getDataHoraInicio().after(Date.from(Instant.now())) 
					&& auxCicloEtapa.getCiclo().getIdenCiclo().equals(cicloAtual.getIdenCiclo())
					&& auxCicloEtapa.getDataHoraInicio().after(cicloEtapaPendente.getDataHoraInicio()))){
				
				cicloEtapaPendente = auxCicloEtapa;
			}
		}
		if(cicloEtapaPendente.getIdenCicloEtapa() != null){
			return cicloEtapaPendente;
		}
		
		return null;
	}

	public LocalDateTime getDataInicioCiclo() {
		return dataInicioCiclo;
	}

	public LocalDateTime getDataFinalCiclo() {
		return dataFinalCiclo;
	}	
}
