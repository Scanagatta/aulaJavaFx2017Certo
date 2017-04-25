package br.edu.unoesc.revisaoOO.modelo;

import javafx.scene.control.Alert;

public class Conta {

	private String numero;
	private Double saldo;
	private Double valor;
	
	private Cliente cliente;
	
	private Agencia agenciaPreferencial;
	
	private Cliente clientePreferencial;

	

	public Conta(String numero, Double saldo, Cliente cliente) {
		super();
		this.numero = numero;
		this.saldo = saldo;
		this.cliente = cliente;
	}

	// metodo contrutor vazio sem aprametros de inicializaï¿½ï¿½o
	public Conta() {
		saldo = 0.0;

	}

	// metodo contrutor para criar objetos com valores inicializados
	public Conta(String numero, Cliente cliente) {

		this();
		this.numero = numero;
		this.cliente = cliente;

	}

	/**
	 * metodo depositar, recebe como parametro um valor e soma esse valor no
	 * saldo
	 * 
	 * @param valor
	 */
	public void depositar(Double valor) {
		this.saldo += valor;

        Alert dialogoOK1 = new Alert(Alert.AlertType.CONFIRMATION);
        dialogoOK1.setTitle("SUCESSO");
        dialogoOK1.setHeaderText("DEPOSITO REALIZADO COM ÊXITO");
        dialogoOK1.showAndWait();
	}

	/**
	 * metodo sacar, recebe como parametro um valor e diminui esse saldo
	 * 
	 * @param valor
	 */
	public boolean sacar(Double valor) {
		if (this.saldo >= valor) {
			this.setSaldo(this.getSaldo()-valor);
			
            Alert dialogoOK = new Alert(Alert.AlertType.CONFIRMATION);
            dialogoOK.setTitle("SUCESSO");
            dialogoOK.setHeaderText("SAQUE REALIZADO COM ÊXITO");
            dialogoOK.showAndWait();
			return true;
		}else{
            Alert dialogoError = new Alert(Alert.AlertType.ERROR);
            dialogoError.setTitle("ERRO");
            dialogoError.setHeaderText("AÇÃO NÃO PERMITIDA");
            dialogoError.setContentText("Saldo insuficiente");
            dialogoError.showAndWait();
			return false;
		}
	}
	
	public void transferir(Conta destino, Double valor) {

		this.setSaldo(this.getSaldo()-valor);
		destino.setSaldo(destino.getSaldo()+valor);
	}
	


	// gets e sets
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Double getSaldo() {
		return saldo;
	}
	
	

	public Double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
		
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	@Override
	public String toString(){
		return this.cliente.getNome()+ " "+this.numero;
	}
	


	public Agencia getAgenciaPreferencial() {
		return agenciaPreferencial;
	}


	public void setAgenciaPreferencial(Agencia agenciaPreferencial) {
		this.agenciaPreferencial = agenciaPreferencial;
	}

	public Cliente getClientePreferencial() {
		return clientePreferencial;
	}

	public void setClientePreferencial(Cliente clientePreferencial) {
		this.clientePreferencial = clientePreferencial;
	}



}
