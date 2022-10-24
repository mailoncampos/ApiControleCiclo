package com.ApiControleCiclo.service.negocio.cicloEtapa.factory;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.ApiControleCiclo.model.Ciclo;
import com.ApiControleCiclo.model.CicloEtapa;
import com.ApiControleCiclo.service.negocio.cicloEtapa.strategy.CicloEtapaImpl;

@Component("CicloEtapaFactory")
public interface CicloEtapaFactory {
	
	public CicloEtapa criarCicloEtapa(CicloEtapaImpl cicloEtapaStrategy, ApplicationContext applicationContext);
	
	public CicloEtapa atualizarCicloEtapa(CicloEtapa cicloEtapa, Ciclo ciclo);
	
	public CicloEtapa inativarCicloEtapa(CicloEtapa cicloEtapa, Ciclo ciclo);
	
	public CicloEtapa suspenderCicloEtapaECiclo(CicloEtapa cicloEtapa, Ciclo ciclo);
	
	//Verifica se os ciclos dos outros anos estão FECHADOS
	//Verifica se há algum ciclo dos outros anos com Status SUSPENSO


	
	//Verificar se existe um ciclo criado com status ABERTO, PENDENTE, SUSPENSO ou FECHADO para o ano de exercicio
	
	//Se não existir
		//Verifica se existe Exercicio cadastrado
		//Verifica se existe Termo cadastrado
		//Verifica se existe Questionario cadastrado
		//Posso criar um cicloEtapa EXTEMPORANEO
		//Posso criar um cicloEtapa REGULAR
	
	
	//Se PENDENTE
		//Posso criar um cicloEtapa PRORROGACAO
		//Posso criar um cicloEtapa EXTEMPORANEO
	//Se ABERTO
		//Posso criar um cicloEtapa PRORROGACAO
		//Posso criar um cicloEtapa EXTEMPORANEO			
	//Se SUSPENSO
		//Posso criar nada
	//Se FECHADO
		//Posso criar um cicloEtapa PRORROGACAO
		//Posso criar um cicloEtapa EXTEMPORANEO
		//Posso criar um cicloEtapa REGULAR
	
	/*
	 * Um ciclo SUSPENSO pode ser reutilizado novamente. 
	 * 	se a data atual for anterior  datas final existente no ciclo 
	 * 		mantém o mesmo ciclo 
	 * 	caso a data atual seja posterior a data final
	 * 		finaliza esse ciclo e cria um novo um novo ciclo 
	 * 
	 * Um ciclo SUSPENSO pode ser FECHADO(Caso não tenha pendencia e não vai ABRIR novamemnte), 
	 * Um ciclo SUSPENSO pode ser INATIVADO(Caso precise apagar o que foi feito)
	 * 
	 */
	
	
}
