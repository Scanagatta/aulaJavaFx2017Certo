package br.edu.unoesc.revisaoOO.modelo;

import java.util.ArrayList;
import java.util.List;

import br.edu.unoesc.revisaoOO.dao.ManipuladorArquivo;

public class SimuladorBD {

	// variavel estatica tem o mesmo valor pra todo mundo
	private static List<Agencia> agencias;
	private static List<Cliente> clientes;
	private static List<Conta> contas;
	private static List<Movimento> movimentos;
	private static ManipuladorArquivo manipuladorArquivo;

	static {
		agencias = new ArrayList<>();
		clientes = new ArrayList<>();
		contas = new ArrayList<>();
		movimentos = new ArrayList<>();
		manipuladorArquivo = new ManipuladorArquivo();
	}

	public static void atualizarAgencias(){
		manipuladorArquivo.gravar(agencias, "agencias.ser");
	}
	
	public static void atualizarClientes(){
		manipuladorArquivo.gravar(clientes, "clientes.ser");
	}
	
	public static void atualizarContas(){
		manipuladorArquivo.gravar(contas, "contas.ser");
	}
	public static void atualizarMovimentos(){
		manipuladorArquivo.gravar(movimentos, "movimentos.ser");
	}
	
	/////////////////////////////////////
	
	public static void insert(Conta conta) {
		contas.add(conta);
		atualizarContas();

	}

	public static void remover(Conta conta) {
		contas.remove(conta);
		atualizarContas();

	}

	public static List<Conta> getContas() {
		contas = manipuladorArquivo.recuperar("contas.ser");
		return contas;
	}

	// agencias

	public static void insert(Agencia agencia) {
		agencias.add(agencia);
		// com isso eu chamo a classe e o metodo e ele grava no pc
		atualizarAgencias();

	}
	
	

	public static void remover(Agencia agencia) {
		agencias.remove(agencia);
		atualizarAgencias();

	}

	public static List<Agencia> getAgencias() {
		agencias = manipuladorArquivo.recuperar("agencias.ser");
		return agencias;
	}

	// Clientes

	public static void insert(Cliente cliente) {
		clientes.add(cliente);
		atualizarClientes();
	}

	public static void remover(Cliente cliente) {
		clientes.remove(cliente);
		atualizarClientes();

	}

	public static List<Cliente> getClientes() {
		clientes = manipuladorArquivo.recuperar("clientes.ser");
		return clientes;
	}

	// Movimentos

	public static void insert(Movimento movimento) {
		movimentos.add(movimento);
		atualizarMovimentos();
	}

	public static void remover(Movimento movimento) {
		movimentos.remove(movimento);
		atualizarMovimentos();

	}

	public static List<Movimento> getMovimentos() {
		movimentos = manipuladorArquivo.recuperar("movimentos.ser");
		return movimentos;
	}



}
