package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.edu.unoesc.revisaoOO.modelo.Agencia;
import br.edu.unoesc.revisaoOO.modelo.ConexaoUtil;

public class AgenciaJdbc implements AgenciaDao {

	@Override
	public List<Agencia> listar() {
		List<Agencia> agencias = new ArrayList<>();
		try {



				Connection con = ConexaoUtil.getCon();
				// classe que sabe executar comandos sql
				Statement stmt = con.createStatement();
				// comando sql q desejo executar
				String sql = "select * from agencia";
				// execução do comando e o resultado é armazenado no ResultSet
				ResultSet rs = stmt.executeQuery(sql); // uma consulta, resultado
														// esta
														// dentro do ResultSet
				while (rs.next()) {
					Agencia agencia = new Agencia();
					agencia.setCodigo(rs.getLong("codigo"));
					agencia.setNome(rs.getString("nome"));
					agencia.setNumero(rs.getString("numero"));
					agencias.add(agencia);
				}

			} catch (SQLException e) {
				e.printStackTrace();

			}
			return agencias;
		}
		
		@Override
		public void inserir(Agencia agencia){
			
			try {

			Connection con = ConexaoUtil.getCon();
			String insert = "insert into agencia values (codigo, ?, ?) "; // cadastra a
			// variavel do
			// scan no banco

			// novo statement para executar o insert
			PreparedStatement insertStmt = con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);

			insertStmt.setString(1, agencia.getNome());
			insertStmt.setString(2, agencia.getNumero());
			
			// execução do insert
			insertStmt.executeUpdate();
			
			// vão popular o codigo da agencia
			ResultSet resultSet = insertStmt.getGeneratedKeys();
			resultSet.next();
			agencia.setCodigo(resultSet.getLong(1));

			} catch (SQLException e) {
				// tratamento do erro de conexão com o banco
				e.printStackTrace();
			}
		}
			

			
		@Override
			public void alterar(Agencia agencia){
				
				try {

				Connection con = ConexaoUtil.getCon();
				String update = "update agencia set nome= ?, numero= ? where codigo= ?"; // cadastra a
				// variavel do
				// scan no banco

				// novo statement para executar o insert
				PreparedStatement updateStmt = con.prepareStatement(update);

				updateStmt.setString(1, agencia.getNome());
				updateStmt.setString(2, agencia.getNumero());
				updateStmt.setLong(3, agencia.getCodigo());

				updateStmt.executeUpdate();

				} catch (SQLException e) {
					// tratamento do erro de conexão com o banco
					e.printStackTrace();
				}
		}
			
			@Override
			public void excluir(Long codigo){ // delete(Long codigo)
				
				try {

				Connection con = ConexaoUtil.getCon();
				String delete = "delete from agencia where codigo= ?"; // cadastra a
				// variavel do
				// scan no banco

				// novo statement para executar o insert
				PreparedStatement deleteStmt = con.prepareStatement(delete);

				deleteStmt.setLong(1, codigo);
				

				deleteStmt.executeUpdate();

				} catch (SQLException e) {
					// tratamento do erro de conexão com o banco
					e.printStackTrace();
				}
				
			
		}
	

	@Override
	public Agencia get(Long codigo) {

	
			try {

				Connection con = ConexaoUtil.getCon();
				// classe que sabe executar comandos sql
				Statement stmt = con.createStatement();
				// comando sql q desejo executar
				String sql = "select * from agencia where codigo = "+codigo;
				// execução do comando e o resultado é armazenado no ResultSet
				ResultSet rs = stmt.executeQuery(sql); // uma consulta, resultado
				rs.next();										// esta
														// dentro do ResultSet
	
					Agencia agencia = new Agencia();
					agencia.setCodigo(rs.getLong("codigo"));
					agencia.setNome(rs.getString("nome"));
					return agencia;
				

			} catch (SQLException e) {
				e.printStackTrace();

			}
			return null;

	}
	
//	--abaixo metodo generico que funciona só para 1 campo--
	
//	Connection con = ConexaoUtil.getCon();
//	Scanner sc = new Scanner(System.in);
//	System.out.println("digite o/a "+nomeCampo+" da tabela "+nomeTabela+": ");
//	String atributo = sc.nextLine();
//	sc.close();
//	String insert = "insert into "+nomeTabela+" ("+nomeCampo+") values (?)"; // cadastra a
//	// variavel do
//	// scan no banco
//
//	// novo statement para executar o insert
//	PreparedStatement insertStmt = con.prepareStatement(insert);
//
//	insertStmt.setString(1, atributo);
//	// execução do insert
//	insertStmt.executeUpdate();
//
//	} catch (SQLException e) {
//		// tratamento do erro de conexão com o banco
//		e.printStackTrace();
//	}
	
}
