package br.edu.unoesc.revisaoOO.modelo;

import java.io.Serializable;

import br.edu.unoesc.componente.RenderizaCombo;

public class Agencia implements Serializable, RenderizaCombo{
	

	// implementado o serializable para salvar arquivos no pc, substituindo o banco de dados
	private static final long serialVersionUID = -1320599973517592837L;
	private String nome;
	private String numero;
	
	
	
	public Agencia() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Agencia(String nome, String numero) {
		super();
		this.nome = nome;
		this.numero = numero;
	}



	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public String getNumero() {
		return numero;
	}



	public void setNumero(String numero) {
		this.numero = numero;
	}

	
	@Override
	public String toString(){
		return this.nome+ " "+this.numero;
	}



	@Override
	public String getText() {
		
		return nome;
	}
	

	
}
