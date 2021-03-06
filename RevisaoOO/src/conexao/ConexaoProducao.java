package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoProducao implements Conexao{

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
		String password = "joao1234";

		// vai até o banco de dados e cria uma conexão
		try {
			con = DriverManager.getConnection(url, username, password); // pega
																		// conexao
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}

	public Connection get() { //
		try {
			if (con.isClosed()) {
				open();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return con;
	}

	public  void close() {
		try {
			con.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

}
