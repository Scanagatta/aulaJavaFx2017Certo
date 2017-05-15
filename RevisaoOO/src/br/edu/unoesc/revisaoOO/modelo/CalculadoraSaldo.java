package br.edu.unoesc.revisaoOO.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CalculadoraSaldo implements Serializable {

	private List<Conta> contas;

	public CalculadoraSaldo() {
		this.contas = new ArrayList<>();
	}

	public void adicionar(Conta contas) {
		this.contas.add(contas);
	}

	public Double calcular() {
		Double saldo = 0.0;
		// JAVA <7
		for (Conta conta : contas) { // classe /variacel /lista
			// for each ele percorre todas as linhas da lista contas
			// pegando uma conta por vez e populando a variavel conta
			saldo += conta.getSaldo();
		}
		// JAVA 8
		// return contas.stream().mapToDouble(Conta::getSaldo).sum(); //ISTO É
		// UM LAMBDA
		return saldo;
	}
}
