package com.ApiOnlineSecurity.service.negocio.cicloEtapa.strategy.criacao;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.ApiOnlineSecurity.exception.CicloException;
import com.ApiOnlineSecurity.model.Ciclo;
import com.ApiOnlineSecurity.model.CicloEtapa;
import com.ApiOnlineSecurity.model.Exercicio;
import com.ApiOnlineSecurity.model.Questionario;
import com.ApiOnlineSecurity.model.Termo;
import com.ApiOnlineSecurity.model.enuns.StatusCicloEnum;
import com.ApiOnlineSecurity.service.CicloEtapaService;
import com.ApiOnlineSecurity.service.CicloService;
import com.ApiOnlineSecurity.service.ExercicioService;
import com.ApiOnlineSecurity.service.QuestionarioService;
import com.ApiOnlineSecurity.service.TermoService;

@Component
public abstract class CriarCicloEtapa {
	
	@Autowired
	public CicloService cicloService;
	
	@Autowired
	protected CicloEtapaService cicloEtapaService;
	
	@Autowired
	private ExercicioService exercicioService;
	
	@Autowired
	private TermoService termoService;
	
	@Autowired
	private QuestionarioService questionarioService;
	
	public CriarCicloEtapa(ApplicationContext applicationContext) {
		cicloService = applicationContext.getBean(CicloService.class);
		cicloEtapaService = applicationContext.getBean(CicloEtapaService.class);
		exercicioService = applicationContext.getBean(ExercicioService.class);
		termoService = applicationContext.getBean(TermoService.class);
		questionarioService = applicationContext.getBean(QuestionarioService.class);
	}
	
	protected List<Ciclo> listaCiclosDiferenteDeInativo = new ArrayList<Ciclo>();
	
	protected List<CicloEtapa> cicloEtapasDiferenteDeInativaDoCiclo = new ArrayList<CicloEtapa>();
	
	public abstract void verificaStatuCicloParaCricao(Ciclo ciclo, CicloEtapa cicloEtapa);
	
	public abstract void regrasDosStatusParaCricaoDoCicloEtapa(Ciclo ciclo, CicloEtapa cicloEtapa);

	public abstract Ciclo verificaRegrasParaCricaoDoCicloPorTipoDeCicloERealizaPersistencia(Ciclo ciclo, CicloEtapa cicloEtapa);
	
//	protected abstract CicloEtapa verificaRegrasDeCriacaoDoCicloEtapa(Ciclo ciclo, CicloEtapa cicloEtapa);
	
	public CicloEtapa verificaRegrasDePersistenciaDoCicloEtapa(CicloEtapa cicloEtapa, Ciclo cicloSalvo) {
		
		if(cicloEtapa.getDataHoraFim().before(Date.from(Instant.now()))) {
			throw new CicloException(new RuntimeException().getCause(), "Data final informada é anterior a data atual");
		}
		
		if(cicloEtapa.getDataHoraInicio().after(cicloEtapa.getDataHoraFim())) {
			throw new CicloException(new RuntimeException(), "Data inicial é posterior a data final");
		}else {
			cicloEtapa.setStatusCicloEtapa(StatusCicloEnum.PENDENTE);
		}
		
		if(cicloEtapa.getDataHoraInicio().before(Date.from(Instant.now()))) {
			throw new CicloException(new RuntimeException(), "Data inicial informada é anterior a data atual");
		}
		
		for(CicloEtapa auxCicloEtapa : this.cicloEtapasDiferenteDeInativaDoCiclo) {			
			if(auxCicloEtapa.getDataHoraInicio().after(cicloEtapa.getDataHoraInicio()) && auxCicloEtapa.getDataHoraFim().before(cicloEtapa.getDataHoraInicio())) {
				throw new CicloException(new RuntimeException(), "Data inicial está dentro do periodo de outro ciclo etapa");
			}
			if(auxCicloEtapa.getDataHoraInicio().after(cicloEtapa.getDataHoraFim()) && auxCicloEtapa.getDataHoraFim().before(cicloEtapa.getDataHoraFim())) {
				throw new CicloException(new RuntimeException(), "Data final está dentro do periodo de outro ciclo etapa");
				
			}
		}
		
		cicloEtapa.setCiclo(cicloSalvo);
		
		return cicloEtapaService.salvarCicloEtapa(cicloEtapa);
	}
	
	
	
	protected Ciclo verificaCicloERealizaPersistencia(Ciclo ciclo, CicloEtapa cicloEtapa) {
		
		if(ciclo.getIdenCiclo() == null) {
			
			if(cicloEtapa.getDataHoraInicio().after(Date.from(Instant.now()))) {
				ciclo.setStatusCiclo(StatusCicloEnum.PENDENTE);
			}
			
			ciclo.setDataHoraInicio(cicloEtapa.getDataHoraInicio());
			ciclo.setDataHoraFim(cicloEtapa.getDataHoraFim());
			
		}else {
			return ciclo;
		}
		
		
		if(ciclo.getDataHoraFim().before(Date.from(Instant.now()))) {
			throw new CicloException(new RuntimeException(), "Data final informada é anterior a data atual");
		}
		
		if(ciclo.getDataHoraInicio().after(ciclo.getDataHoraFim())) {
			throw new CicloException(new RuntimeException(), "Data inicial é posterior a data final");
		}
		
		if(ciclo.getDataHoraInicio().before(Date.from(Instant.now()))) {
			throw new CicloException(new RuntimeException(), "Data inicial informada é anterior a data atual");
		}
		
		for(Ciclo auxCiclo : this.listaCiclosDiferenteDeInativo) {			
			if(auxCiclo.getDataHoraInicio().after(ciclo.getDataHoraInicio()) && auxCiclo.getDataHoraFim().before(ciclo.getDataHoraInicio())) {
				throw new CicloException(new RuntimeException(), "Data inicial está dentro do periodo de outro ciclo");
			}
			if(auxCiclo.getDataHoraInicio().after(ciclo.getDataHoraFim()) && auxCiclo.getDataHoraFim().before(ciclo.getDataHoraFim())) {
				throw new CicloException(new RuntimeException(), "Data final está dentro do periodo de outro ciclo");
				
			}
		}
		
		
					
		return cicloService.salvarCiclo(ciclo);
			
	}
	
	protected void listaCiclosComStatusDiferenteDeInativo(){
		this.listaCiclosDiferenteDeInativo = cicloService.buscarCiclosComStatusDiferenteDeInativo();
	}
	
	protected void listaCiclosEtapasPorCicloComStatusDiferenteDeInativo(Long idenCiclo){
		this.cicloEtapasDiferenteDeInativaDoCiclo = cicloEtapaService.buscarCicloEtapasPorCicloComStatusDiferenteDeInativo(idenCiclo);
	}
	
	public void verificaCicloEtapaDosOutrosCiclos(CicloEtapa cicloEtapa, Ciclo ciclo) {
		
		if(this.cicloEtapasDiferenteDeInativaDoCiclo.isEmpty()) {
			listaCiclosEtapasPorCicloComStatusDiferenteDeInativo(ciclo.getIdenCiclo());
		}
		
		for(CicloEtapa auxCicloEtapa : cicloEtapasDiferenteDeInativaDoCiclo) {
			
			if(auxCicloEtapa.getCiclo() == null || auxCicloEtapa.getCiclo().getIdenCiclo() != ciclo.getIdenCiclo()){
				if(auxCicloEtapa.getStatusCicloEtapa().equals(StatusCicloEnum.ABERTO)){
					throw new CicloException(new RuntimeException(), "Não é possivel criar um novo ciclo enquanto existir um ciclo ABERTO em outro ciclo");
				}else if(auxCicloEtapa.getStatusCicloEtapa().equals(StatusCicloEnum.PENDENTE)) {
					throw new CicloException(new RuntimeException(), "Não é possivel criar um novo ciclo enquanto existir um ciclo PENDENTE em outro ciclo");
				}else if(auxCicloEtapa.getStatusCicloEtapa().equals(StatusCicloEnum.SUSPENSO)) {
					throw new CicloException(new RuntimeException(), "Não é possivel criar um novo ciclo enquanto existir um ciclo SUSPENSO em outro ciclo");
				}				
			}
			
		}
	}
	
	public Ciclo verificarExercicioTermoEQuestionario(Ciclo ciclo) {
		
		Exercicio exercicioSalvo = exercicioService.buscarExercicioPorAno(ciclo.getExercicio().getAnoExercicio());
		Termo termoSalvo = termoService.buscarTermoPorIden(ciclo.getTermo().getIdenTermo());
		Questionario questionarioSalvo = questionarioService.buscarQuestionarioPorIden(ciclo.getQuestionario().getIdenQuestionario());
		
		ciclo.setExercicio(exercicioSalvo);
		ciclo.setTermo(termoSalvo);
		ciclo.setQuestionario(questionarioSalvo);
		
		return ciclo;
		
	}
	
	public void verificaCiclosDosExerciciosAnteriores(Exercicio exercicio) {
		if(listaCiclosDiferenteDeInativo.isEmpty()) {
			listaCiclosComStatusDiferenteDeInativo();
		}
		for(Ciclo auxCiclo : listaCiclosDiferenteDeInativo) {
			if(auxCiclo.getExercicio().getAnoExercicio() != exercicio.getAnoExercicio()) {
				if(auxCiclo.getStatusCiclo().equals(StatusCicloEnum.SUSPENSO)) {				
					throw new CicloException(new RuntimeException(), "Não é possivel criar um novo ciclo enquanto o ciclo:" + auxCiclo.getNomeCiclo() + ", "
							+ "do ano de " + exercicio.getAnoExercicio().toString() + " estiver SUSPENSO.");	
				}else if(auxCiclo.getStatusCiclo().equals(StatusCicloEnum.PENDENTE)){
					throw new CicloException(new RuntimeException(), "Não é possivel criar um novo ciclo enquanto existir um ciclo PENDENTE em outro exercício");
				}else if(auxCiclo.getStatusCiclo().equals(StatusCicloEnum.ABERTO)){
					throw new CicloException(new RuntimeException(), "Não é possivel criar um novo ciclo enquanto existir um ciclo ABERTO em outro exercício");
				}
			}
		}
	}
	
	public Ciclo verificaCiclosDoAnoDeExercicio(Ciclo ciclo, CicloEtapa cicloEtapa) {
		
		if(listaCiclosDiferenteDeInativo.isEmpty()) {
			listaCiclosComStatusDiferenteDeInativo();
		}
		Ciclo cicloAtual = new Ciclo();
		for(Ciclo auxCiclo : listaCiclosDiferenteDeInativo) {
			if(auxCiclo.getExercicio().getAnoExercicio() == ciclo.getExercicio().getAnoExercicio()) {
				if(auxCiclo.getStatusCiclo().equals(StatusCicloEnum.FECHADO)) {
					if(cicloAtual == null || cicloAtual.getIdenCiclo() == null) {
						cicloAtual = auxCiclo;
					}else if(cicloAtual.getDataHoraFim().before(auxCiclo.getDataHoraFim())){
						cicloAtual = auxCiclo;
					}
				}else if(auxCiclo.getStatusCiclo().equals(StatusCicloEnum.PENDENTE) || auxCiclo.getStatusCiclo().equals(StatusCicloEnum.ABERTO)) {
					if(listaCiclosDiferenteDeInativo.get(listaCiclosDiferenteDeInativo.size() - 1).getIdenCiclo() == auxCiclo.getIdenCiclo()) {
						cicloAtual = auxCiclo;
					}else {
						throw new CicloException(new RuntimeException(), "Inconsistencia nos dados armazenados na tabela Ciclo");
					}
				}
			}
		}
		
		if(cicloAtual.getIdenCiclo() == null) {
			return ciclo;
		}
					
		return cicloAtual;	
	}
}
