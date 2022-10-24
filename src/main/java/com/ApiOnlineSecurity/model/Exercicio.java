package com.ApiOnlineSecurity.model;

import java.io.Serial;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="exercicio", schema = "geral")
public class Exercicio implements Serializable {

	@Serial
    private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "iden_exercicio")
	private Long idenExercicio;
	
	@Column(name = "ano_exercicio")
	private Long anoExercicio;
	
	@Column(name = "status_exercicio")
	private String statusExercicio;

	public Exercicio(Long idenExercicio, Long anoExercicio, String statusExercicio) {
		super();
		this.idenExercicio = idenExercicio;
		this.anoExercicio = anoExercicio;
		this.statusExercicio = statusExercicio;
	}
	
	public Exercicio() {

	}

	public Long getIdenExercicio() {
		return idenExercicio;
	}

	public void setIdenExercicio(Long idenExercicio) {
		this.idenExercicio = idenExercicio;
	}

	public Long getAnoExercicio() {
		return anoExercicio;
	}

	public void setAnoExercicio(Long anoExercicio) {
		this.anoExercicio = anoExercicio;
	}

	public String getStatusExercicio() {
		return statusExercicio;
	}

	public void setStatusExercicio(String statusExercicio) {
		this.statusExercicio = statusExercicio;
	}
	
	
}
