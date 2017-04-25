package br.edu.unoesc.revisaoOO.modelo;

public class Movimento {
	
	private Double valor;

	private String tipo;
	

	private Conta conta;

	
	

	public Movimento() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Movimento(Double valor,  Conta conta) {
		super();
		this.valor = valor;
	
		this.conta = conta;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}


	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	


	

	
	
}
