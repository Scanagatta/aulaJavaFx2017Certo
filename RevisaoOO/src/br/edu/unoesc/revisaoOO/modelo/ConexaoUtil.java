package br.edu.unoesc.revisaoOO.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoUtil {

	private static Connection con;

	static { // é um bloco statico executa quando chamar qualquer metodo dessa
		// classe, faz isso só uma vez e guarda na variavel conection q depois
		// retorna no fim
		open();
	}

	public static void open() { // abrindo a conexao
		String url = "jdbc:mysql://localhost:3306/banco"; // caminho q o jdbc //
															// espera
		String username = "root";
		String password = "ctg4662011";

		// vai até o banco de dados e cria uma conexão
		try {
			con = DriverManager.getConnection(url, username, password); // pega
																		// conexao
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}

	public static Connection getCon() { //
		try {
			if (con.isClosed()) {
				open();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return con;
	}

	public static void close() {
		try {
			con.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

}
