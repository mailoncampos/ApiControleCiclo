package com.ApiControleCiclo.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

public class CicloException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private HttpStatus httpStatus;
	
	private Logger logger;
	
	private String mensagem;
	
	private Throwable causa;
	
	public CicloException(Throwable causa, String mensagem){
		this.causa = causa;
		this.mensagem = mensagem;
		this.logger = LoggerFactory.getLogger(mensagem);
	}
	public CicloException(Throwable causa, String mensagem, HttpStatus httpStatus){
		this.causa = causa;
		this.logger = LoggerFactory.getLogger(mensagem);
		this.httpStatus = httpStatus;
		this.mensagem = mensagem;
	}
	
	public Throwable getCausa() {
		return causa;
	}
	public HttpStatus getHttpStatus() {
		return this.httpStatus;
	}
	
	public Logger getLogger() {
		return this.logger;
	}
	public String getMensagem() {
		return this.mensagem;
	}
	

}
