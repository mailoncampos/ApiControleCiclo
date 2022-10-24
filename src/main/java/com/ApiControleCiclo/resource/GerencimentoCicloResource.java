package com.ApiControleCiclo.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ApiControleCiclo.dto.CicloEtapaDto;
import com.ApiControleCiclo.model.Ciclo;
import com.ApiControleCiclo.model.CicloEtapa;
import com.ApiControleCiclo.model.Exercicio;
import com.ApiControleCiclo.model.Questionario;
import com.ApiControleCiclo.model.Termo;
import com.ApiControleCiclo.model.enuns.TipoCicloEtapaEnum;
import com.ApiControleCiclo.service.CicloEtapaService;

@RestController
@RequestMapping("gerenciamento-ciclo")
public class GerencimentoCicloResource {
	
	@Autowired
	private CicloEtapaService cicloEtapaService;

	@PostMapping
	public ResponseEntity<CicloEtapaDto> salvar(@RequestBody CicloEtapaDto cicloEtapaDto) {
		
		
		CicloEtapa cicloEtapa = new CicloEtapa();
		cicloEtapa.setIdenCicloEtapa(cicloEtapaDto.getIdenCicloEtapa());
		cicloEtapa.setTipoCicloEtapa(TipoCicloEtapaEnum.toEnum(cicloEtapaDto.getTipoCicloEtapa()));
		cicloEtapa.setDataHoraInicio(cicloEtapaDto.getDataHoraInicio());
		cicloEtapa.setDataHoraFim(cicloEtapaDto.getDataHoraFim());
		
		Exercicio exercicio = new Exercicio(null, 2022L, null);
		exercicio.setAnoExercicio(cicloEtapaDto.getAnoExercicio());
		
		Questionario questionario = new Questionario(1L, null);
		Termo termo = new Termo(1L, null);
		
		Ciclo ciclo = new Ciclo();
		ciclo.setExercicio(exercicio);
		ciclo.setTermo(termo);
		ciclo.setQuestionario(questionario);
		ciclo.setNomeCiclo(cicloEtapaDto.getNomeCiclo());
		
		
			CicloEtapa cicloEtapaSalvo = cicloEtapaService.salvar(cicloEtapa, ciclo);			
			
			cicloEtapaDto = new CicloEtapaDto(cicloEtapaSalvo.getIdenCicloEtapa(), cicloEtapaSalvo.getTipoCicloEtapa().getDescricao(), 
					cicloEtapaSalvo.getDataHoraInicio(), cicloEtapaSalvo.getDataHoraFim(), 
						ciclo.getNomeCiclo(), ciclo.getExercicio().getAnoExercicio());
			return ResponseEntity.status(HttpStatus.CREATED).body(cicloEtapaDto);
			
			
		
		
		
	}
}
