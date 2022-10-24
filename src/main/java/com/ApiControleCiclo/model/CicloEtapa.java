package com.ApiControleCiclo.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.ApiControleCiclo.model.enuns.StatusCicloEnum;
import com.ApiControleCiclo.model.enuns.TipoCicloEtapaEnum;

@Entity
@Table(name="ciclo_etapa", schema = "recadastramento")
public class CicloEtapa implements Serializable {

	@Serial
    private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "ciclo_etapa_pkey", initialValue = 0, 
		sequenceName = "ciclo_etapa_iden_ciclo_seq", schema = "recadastramento")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iden_ciclo_etapa")
	private Long idenCicloEtapa;
	
	@Column(name = "tipo_ciclo_etapa")
	private TipoCicloEtapaEnum tipoCicloEtapa;
	
	@OneToOne
	@JoinColumn(name = "iden_ciclo", referencedColumnName = "iden_ciclo")
	private Ciclo ciclo;
	
	@Column(name = "data_hora_inicio")
	private Date dataHoraInicio;
	
	@Column(name = "data_hora_fim")
	private Date dataHoraFim;
	
	@Column(name = "status_ciclo_etapa")
	private StatusCicloEnum statusCicloEtapa;
	
	

	public CicloEtapa() {
		super();
	}

	public CicloEtapa(Long idenCicloEtapa, TipoCicloEtapaEnum tipoCicloEtapa, Ciclo ciclo, Date dataHoraInicio,
			Date dataHoraFim, StatusCicloEnum statusCicloEtapa) {
		super();
		this.idenCicloEtapa = idenCicloEtapa;
		this.tipoCicloEtapa = tipoCicloEtapa;
		this.ciclo = ciclo;
		this.dataHoraInicio = dataHoraInicio;
		this.dataHoraFim = dataHoraFim;
		this.statusCicloEtapa = statusCicloEtapa;
	}

	public Long getIdenCicloEtapa() {
		return idenCicloEtapa;
	}

	public void setIdenCicloEtapa(Long idenCicloEtapa) {
		this.idenCicloEtapa = idenCicloEtapa;
	}

	public TipoCicloEtapaEnum getTipoCicloEtapa() {
		return tipoCicloEtapa;
	}

	public void setTipoCicloEtapa(TipoCicloEtapaEnum tipoCicloEtapa) {
		this.tipoCicloEtapa = tipoCicloEtapa;
	}

	public Ciclo getCiclo() {
		return ciclo;
	}

	public void setCiclo(Ciclo ciclo) {
		this.ciclo = ciclo;
	}

	public Date getDataHoraInicio() {
		return dataHoraInicio;
	}

	public void setDataHoraInicio(Date dataHoraInicio) {
		this.dataHoraInicio = dataHoraInicio;
	}

	public Date getDataHoraFim() {
		return dataHoraFim;
	}

	public void setDataHoraFim(Date dataHoraFim) {
		this.dataHoraFim = dataHoraFim;
	}

	public StatusCicloEnum getStatusCicloEtapa() {
		return statusCicloEtapa;
	}

	public void setStatusCicloEtapa(StatusCicloEnum statusCicloEtapa) {
		this.statusCicloEtapa = statusCicloEtapa;
	}
	
	
}
