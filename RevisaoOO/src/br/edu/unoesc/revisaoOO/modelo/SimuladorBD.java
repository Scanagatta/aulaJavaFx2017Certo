package br.edu.unoesc.revisaoOO.modelo;

import java.util.ArrayList;
import java.util.List;


public class SimuladorBD {

	
	// variavel estatica tem o mesmo valor pra todo mundo
	private static List<Agencia> agencias;
	private static List<Cliente> clientes;
	private static List<Conta> contas;
	private static List<Movimento> movimentos; 
	
	static{
		agencias = new ArrayList<>();
		clientes = new ArrayList<>();
		contas = new ArrayList<>();
		movimentos = new ArrayList<>();
	}
	
	public static void insert(Conta conta){
		contas.add(conta);
		
	}
	
	public static void remover(Conta conta){
		contas.remove(conta);
		
	}
	
	
	public static List<Conta> getContas(){
		return contas;
	}
	
	//agencias
	
	public static void insert(Agencia agencia){
		agencias.add(agencia);
		
	}
	
	public static void remover(Agencia agencia){
		agencias.remove(agencia);
		
	}
	
	public static List<Agencia> getAgencias(){
		return agencias;
	}
	
	//Clientes
	
	public static void insert(Cliente cliente){
		clientes.add(cliente);
	}
	
	public static void remover(Cliente cliente){
		clientes.remove(cliente);
		
	}
	
	public static List<Cliente> getClientes(){
		return clientes;
	}
	
	//Movimentos
	
	public static void insert(Movimento movimento){
		movimentos.add(movimento);
	}
	
	public static void remover(Movimento movimento){
		movimentos.remove(movimento);
		
	}
	
	public static List<Movimento> getMovimentos(){
		return movimentos;
	}


	
	
	
}

