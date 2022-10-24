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
@Table(name="termo", schema = "recadastramento")
public class Termo implements Serializable {

	@Serial
    private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "iden_termo")
	private Long idenTermo;
	
	@Column(name = "nome_termo")
	private String nomeTermo;

	public Termo(Long idenTermo, String nomeTermo) {
		super();
		this.idenTermo = idenTermo;
		this.nomeTermo = nomeTermo;
	}
	
	public Termo() {
		// TODO Auto-generated constructor stub
	}

	public Long getIdenTermo() {
		return idenTermo;
	}

	public void setIdenTermo(Long idenTermo) {
		this.idenTermo = idenTermo;
	}

	public String getNomeTermo() {
		return nomeTermo;
	}

	public void setNomeTermo(String nomeTermo) {
		this.nomeTermo = nomeTermo;
	}
	
}
