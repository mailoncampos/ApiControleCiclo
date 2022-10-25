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
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="ciclo", schema = "recadastramento")
public class Ciclo implements Serializable {

	@Serial
    private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "ciclo_pkey", initialValue = 0, 
					sequenceName = "ciclo_iden_ciclo_seq", schema = "recadastramento")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iden_ciclo")
	private Long idenCiclo;
	
	@Column(name = "nome_ciclo")
	private String nomeCiclo;
	
	@OneToOne
	@JoinColumn(name = "iden_ciclo_ref", referencedColumnName = "iden_ciclo")
	private Ciclo ciclo;
	
	@OneToOne
	@JoinColumn(name = "iden_exercicio", referencedColumnName = "iden_exercicio")
	private Exercicio exercicio;
	
	@OneToOne
	@JoinColumn(name = "iden_questionario", referencedColumnName = "iden_questionario")
	private Questionario questionario;
	
	@OneToOne
	@JoinColumn(name = "iden_termo", referencedColumnName = "iden_termo")
	private Termo termo;
	
	@JsonFormat(timezone = "America/Cuiaba")
	@Column(name = "data_hora_inicio")
	private Date dataHoraInicio;
	
	@JsonFormat(timezone = "America/Cuiaba")
	@Column(name = "data_hora_fim")
	private Date dataHoraFim;
	
	@Column(name = "status_ciclo_etapa")
	private StatusCicloEnum statusCiclo;

	public Long getIdenCiclo() {
		return idenCiclo;
	}

	public void setIdenCiclo(Long idenCiclo) {
		this.idenCiclo = idenCiclo;
	}

	public String getNomeCiclo() {
		return nomeCiclo;
	}

	public void setNomeCiclo(String nomeCiclo) {
		this.nomeCiclo = nomeCiclo;
	}

	public Exercicio getExercicio() {
		return exercicio;
	}

	public void setExercicio(Exercicio exercicio) {
		this.exercicio = exercicio;
	}

	public Ciclo getCiclo() {
		return ciclo;
	}

	public void setCiclo(Ciclo ciclo) {
		this.ciclo = ciclo;
	}

	public Questionario getQuestionario() {
		return questionario;
	}

	public void setQuestionario(Questionario questionario) {
		this.questionario = questionario;
	}

	public Termo getTermo() {
		return termo;
	}

	public void setTermo(Termo termo) {
		this.termo = termo;
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

	public StatusCicloEnum getStatusCiclo() {
		return statusCiclo;
	}

	public void setStatusCiclo(StatusCicloEnum statusCiclo) {
		this.statusCiclo = statusCiclo;
	}
	
	

}
