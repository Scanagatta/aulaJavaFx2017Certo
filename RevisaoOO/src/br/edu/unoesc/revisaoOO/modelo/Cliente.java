package br.edu.unoesc.revisaoOO.modelo;

import java.time.LocalDate;

public class Cliente {
	
	private String cpf;
	private String nome;
	private LocalDate dataNascimento;
	
	private Agencia agenciaPreferencial;
	
	
	public Cliente() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cliente(String string, String nome, LocalDate dataNascimento) {
		
		this.cpf = string;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
	}
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	/**
	 * sobrescreve o metodo toString do object, fazendo com que seja impresso o nome
	 * e cpf no lugar do pacote de dados
	 */
	@Override
	public String toString(){
		return this.nome+ " "+this.cpf;
	}

	public Agencia getAgenciaPreferencial() {
		return agenciaPreferencial;
	}

	public void setAgenciaPreferencial(Agencia agenciaPreferencial) {
		this.agenciaPreferencial = agenciaPreferencial;
	}
	
	

}
