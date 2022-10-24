package com.ApiOnlineSecurity.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ApiOnlineSecurity.service.negocio.cicloEtapa.strategy.monitoramento.MonitoraCicloECicloEtapa;

@Component
@EnableScheduling
public class ScheduledTasks{
	
	@Autowired
	private MonitoraCicloECicloEtapa monitoraCicloECicloEtapa;
	
	@Autowired
	ApplicationContext applicationContext;

//	private final String CRON = "0 0 0 * * *"; // meia noite de todo dia;
	private final String CRON = "*/20 * * * * *" ;
	private final String TIME_ZONE = "America/Cuiaba";
	
	@Scheduled(cron = CRON, zone = TIME_ZONE)
	public void scheduleFutireTask() {
		
		monitoraCicloECicloEtapa.verificarIntegridadeDosCiclos();
		monitoraCicloECicloEtapa.verificarIntegridadeDosCicloEtapas();
//		monitoraCicloECicloEtapa.efetuaAndamentoDoCicloECicloEtapa();
		
	}
	
	/*
	 * #Sobre o STATUS SUSPENSO
	 * 
	 * 	O Ciclo SUSPENSO é o estado no qual o Ciclo do Recadastramento foi parado por uma ação judicial.
	 * 
	 * 		Pode ser ABERTO novamente caso tenha resolvido o problema judicial e ainda exista prazo para terminar o Ciclo. Obs.: Mantem o mesmo ciclo e ciclo etapa
	 * 			//Ver regras daqui.
	 * 		Pode ser FECHADO caso tenha resolvido o problema judicial.
	 * 			//Caso o ciclo seja fechado, fica disponivel para abrir um PRORROGACAO do mesmo ciclo ou criar um novo ciclo REGULAR ou EXTEMPORANEO
	 * 
	 * 		Pode ser INATIVADO caso deseje excluir todas as informações atualizadas referente ao Ciclo.
	 * 
	 * A ação de suspender ativa o status SUSPENSO no ciclo selecionado e em todos os ciclo etapas vinculados ao ciclo.
	 * 
	 * ##############################################################################################
	 * 
	 * Verificar integridade da tabela Ciclo e tabela de Ciclo Etapa
	 * 
	 * 	Só pode ter um Ciclo e um Ciclo etapa ABERTO
	 * 
	 * 	Pode ter varios Ciclo Etapas com status PENDENTE mas somente um Ciclo com o esse status
	 * 
	 * 	Pode ter varios Ciclos e Ciclo Etapas com status FECHADO
	 * 
	 * 		
	 * 
	 * 	Verifica se existe  Ciclo e Ciclo etapas SUSPENSO
	 * 
	 * 	Verifica se todos os Ciclos e Ciclo etapas com data final anterior a data atual e com status diferente de SUSPENSO e INATIVO estão fechados
	 * 
	 * 	Caso exista um Ciclo ou Ciclo Etapa SUSPENSO
	 * 			
	 * 
	 * Fazer andamento do Ciclo
	 * 
	 * Todos os dias a meia noite
	 * 
	 *	Buscar ciclo etapa atual
	 *
	 *
	 *	Buscar ciclo atual
	 *		Verifica o status
	 *
	 *		Se PENDENTE
	 *			//Verifica data de inicio
	 *		Se ABERTO
	 *			//Verifica data final
	 *		Se FECHADO
	 *			//Verifica se existe um proximo ciclo etapa
	 *		Se SUSPENSO
	 *			//Finaliza task
	 *		Se INATIVO
	 *			//Ignora
	 *	
	 * 	Verificar coerencia dos ciclos etapas
	 * 		Busca todos os ciclos etapas diferentes de inativo
	 * 		
	 *		Verifica o status do ciclo etapa
	 *
	 *		Se PENDENTE
	 *			//Verifica se é referente ao ciclo atual
	 *				Se Sim
	 *					agrupa todos os pendentes
	 *					//Verifica se a data de inicio é para o dia atual
	 *						Se sim
	 *							Aguarda o momento e ABRE o ciclo e ciclo etapa atual
	 *							
	 *							
	 *				Se não
	 *					retorna INCONSISTENCIA DOS DADOS
	 *		Se ABERTO
	 *			//Verifica se corresponde as informações do ciclo atual caso seja diferente retorna INCONSISTENCIA DOS DADOS
	 *
	 *			//Verifica data e hora final é para o dia atual se for
	 *					Aguarda o momento e FECHA o ciclo e ciclo etapa atual
	 *					//Verifica se há algum ciclo etapa PENDENTE
	 *						Se Sim
	 *							atualiza o ciclo com as informações do proximo ciclo etapa
	 *						Se não
	 *							mantém ciclo fechado
	 *			
	 *				
	 *					
	 *		Se FECHADO
	 *			//Verifica se todos estão fechados caso esteja finaliza task
	 *			
	 *		Se algum for SUSPENSO
	 *			//Finaliza task
	 *		Se INATIVO
	 *			//Ignora
	 * 		
	 */
}
