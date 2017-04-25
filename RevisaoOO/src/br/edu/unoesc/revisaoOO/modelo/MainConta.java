package br.edu.unoesc.revisaoOO.modelo;

import java.time.LocalDate;

public class MainConta {
	
	public static void main(String[] args) {
		
		Cliente jo = new Cliente("8888", "jao", LocalDate.of(2000, 10, 25));
		Conta joao = new Conta("123", jo);
		
		
		joao.depositar(500.0);
		joao.sacar(100.50);
		
		System.out.println(joao.getCliente().getNome());
		
		Cliente pe = new Cliente("99999", "pedro", LocalDate.of(2051, 5, 10) );
		Conta pedro = new Conta("123", pe);
		
		
		pedro.depositar(1000.0);
		pedro.sacar(500.0);
		
		System.out.println(pedro.getCliente().getNome());
		
		System.out.println(joao.getNumero());
		System.out.println(joao.getSaldo());
		
		System.out.println();
		


		System.out.println(pedro.getNumero());
		System.out.println(pedro.getSaldo());
		
		joao.transferir(pedro, 100.0);
		System.out.printf("\nSaldo %.2f do %s ", pedro.getSaldo(), pedro.getCliente().getNome());
		System.out.printf("\nSaldo %.2f do %s ", joao.getSaldo(), joao.getCliente().getNome());
		
		System.out.println();
		pedro.transferir(joao, 50.0);
		
		System.out.printf("\nSaldo %.2f do %s ", pedro.getSaldo(), pedro.getCliente().getNome());
		System.out.printf("\nSaldo %.2f do %s ", joao.getSaldo(), joao.getCliente().getNome());
		
		CalculadoraSaldo calculadoraSaldo = new CalculadoraSaldo();
		calculadoraSaldo.adicionar(joao);
		calculadoraSaldo.adicionar(pedro);
		System.out.println();
		System.out.println(calculadoraSaldo.calcular());
		
	}


}

