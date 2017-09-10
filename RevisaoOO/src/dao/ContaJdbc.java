package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.edu.unoesc.revisaoOO.modelo.Agencia;
import br.edu.unoesc.revisaoOO.modelo.Cliente;
import br.edu.unoesc.revisaoOO.modelo.ConexaoUtil;
import br.edu.unoesc.revisaoOO.modelo.Conta;

public class ContaJdbc implements ContaDao{

	@Override
	public List<Conta> listar() {
		List<Conta> contas = new ArrayList<>();
		
		try {


				Connection con = ConexaoUtil.getCon();
				// classe que sabe executar comandos sql
				Statement stmt = con.createStatement();
				// comando sql q desejo executar
				String sql = "select * from conta";
				// execução do comando e o resultado é armazenado no ResultSet
				ResultSet rs = stmt.executeQuery(sql); // uma consulta, resultado
														// esta
														// dentro do ResultSet
				while (rs.next()) {
					Conta conta = new Conta();
					conta.setCodigo(rs.getLong("codigo"));
					conta.setNumero((rs.getString("numero")));
					
					
					Agencia agencia = new Agencia();
					agencia.setCodigo(rs.getLong("agencia"));
					conta.setAgenciaPreferencial(agencia);
					
					Cliente cliente = new Cliente();
					cliente.setCodigo(rs.getLong("cliente"));
					conta.setCliente(cliente);
					
					contas.add(conta);
				}

			} catch (SQLException e) {
				e.printStackTrace();

			}
			return contas;
		}
		
		@Override
		public void inserir(Conta conta){
			
			try {

			Connection con = ConexaoUtil.getCon();
			String insert = "insert into conta values (codigo, ?, ?, ?) "; // cadastra a
			// variavel do
			// scan no banco

			// novo statement para executar o insert
			PreparedStatement insertStmt = con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);

			insertStmt.setString(1, conta.getNumero());
		
			insertStmt.setLong(2, conta.getCliente().getCodigo());
			
			insertStmt.setLong(3, conta.getAgenciaPreferencial().getCodigo()); //pega o codigo da agencia
			
			
			
			// execução do insert
			insertStmt.executeUpdate();
			
			// vão popular o codigo da agencia
			ResultSet resultSet = insertStmt.getGeneratedKeys();
			resultSet.next();
			conta.setCodigo(resultSet.getLong(1));

			} catch (SQLException e) {
				// tratamento do erro de conexão com o banco
				e.printStackTrace();
			}
		}

		@Override
			public void alterar(Conta conta){
				
				try {

				Connection con = ConexaoUtil.getCon();
				String update = "update conta set numero= ?, agencia=?, cliente=? where codigo= ?"; // cadastra a
				// variavel do
				// scan no banco

				// novo statement para executar o insert
				PreparedStatement updateStmt = con.prepareStatement(update);

				updateStmt.setString(1, conta.getNumero());
				
				updateStmt.setLong(2, conta.getAgenciaPreferencial().getCodigo());
				
				updateStmt.setLong(3, conta.getCliente().getCodigo());
				
				updateStmt.setLong(4, conta.getCodigo());

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
				String delete = "delete from conta where codigo= ?"; // cadastra a
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
	public Conta get(Long codigo) { //pega somente uma conta, pelo codigo
	

	
			try {

				Connection con = ConexaoUtil.getCon();
				// classe que sabe executar comandos sql
				Statement stmt = con.createStatement();
				// comando sql q desejo executar
				String sql = "select * from conta where codigo = "+codigo;
				// execução do comando e o resultado é armazenado no ResultSet
				ResultSet rs = stmt.executeQuery(sql); // uma consulta, resultado
				rs.next();										// esta
														// dentro do ResultSet
	
					Conta conta = new Conta();
					conta.setCodigo(rs.getLong("codigo"));
					conta.setNumero(rs.getString("numero"));
					return conta;
				

			} catch (SQLException e) {
				e.printStackTrace();

			}
			return null;

	}

}