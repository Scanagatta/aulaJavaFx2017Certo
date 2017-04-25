package br.edu.unoesc.revisaoOO.modelo;

public class Agencia {
	
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
	

	
}
