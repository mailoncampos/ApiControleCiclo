package com.ApiOnlineSecurity.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CicloEtapaDto {

	private Long idenCicloEtapa;
	
	private String tipoCicloEtapa;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm", timezone = "America/Cuiaba")
	private Date dataHoraInicio;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm", timezone = "America/Cuiaba")
	private Date dataHoraFim;
	
	private String nomeCiclo;
	
	private Long anoExercicio;

	
	public CicloEtapaDto() {
		super();
	}

	public CicloEtapaDto(Long idenCicloEtapa, String tipoCicloEtapa, Date dataHoraInicio,
			Date dataHoraFim, String nomeCiclo, Long anoExercicio) {
		super();
		this.idenCicloEtapa = idenCicloEtapa;
		this.tipoCicloEtapa = tipoCicloEtapa;
		this.dataHoraInicio = dataHoraInicio;
		this.dataHoraFim = dataHoraFim;
		this.nomeCiclo = nomeCiclo;
		this.anoExercicio = anoExercicio;
	}
	
	public CicloEtapaDto(String tipoCicloEtapa, Date dataHoraInicio, Date dataHoraFim, String nomeCiclo,
			Long anoExercicio) {
		super();
		this.tipoCicloEtapa = tipoCicloEtapa;
		this.dataHoraInicio = dataHoraInicio;
		this.dataHoraFim = dataHoraFim;
		this.nomeCiclo = nomeCiclo;
		this.anoExercicio = anoExercicio;
	}

	public String getNomeCiclo() {
		return nomeCiclo;
	}
	public Long getAnoExercicio() {
		return anoExercicio;
	}
	public Long getIdenCicloEtapa() {
		return idenCicloEtapa;
	}
	public String getTipoCicloEtapa() {
		return tipoCicloEtapa;
	}
	public Date getDataHoraInicio() {
		return dataHoraInicio;
	}
	public Date getDataHoraFim() {
		return dataHoraFim;
	}
	
	
	
}
