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
@Table(name="questionario", schema = "recadastramento")
public class Questionario implements Serializable {

	@Serial
    private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "iden_questionario")
	private Long idenQuestionario;
	
	@Column(name = "nome_questionario")
	private String nomeQuestionario;

	public Questionario(Long idenQuestionario, String nomeQuestionario) {
		super();
		this.idenQuestionario = idenQuestionario;
		this.nomeQuestionario = nomeQuestionario;
	}
	
	public Questionario() {
		// TODO Auto-generated constructor stub
	}

	public Long getIdenQuestionario() {
		return idenQuestionario;
	}

	public void setIdenQuestionario(Long idenQuestionario) {
		this.idenQuestionario = idenQuestionario;
	}

	public String getNomeQuestionario() {
		return nomeQuestionario;
	}

	public void setNomeQuestionario(String nomeQuestionario) {
		this.nomeQuestionario = nomeQuestionario;
	}
}
