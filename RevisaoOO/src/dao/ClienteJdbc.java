package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.edu.unoesc.revisaoOO.modelo.Agencia;
import br.edu.unoesc.revisaoOO.modelo.Cliente;
import br.edu.unoesc.revisaoOO.modelo.ConexaoUtil;

public class ClienteJdbc implements ClienteDao{

	@Override
	public List<Cliente> listar() {
		List<Cliente> clientes = new ArrayList<>();
		
		try {


				Connection con = ConexaoUtil.getCon();
				// classe que sabe executar comandos sql
				Statement stmt = con.createStatement();
				// comando sql q desejo executar
				String sql = "select * from cliente";
				// execução do comando e o resultado é armazenado no ResultSet
				ResultSet rs = stmt.executeQuery(sql); // uma consulta, resultado
														// esta
														// dentro do ResultSet
				while (rs.next()) {
					Cliente cliente = new Cliente();
					cliente.setCodigo(rs.getLong("codigo"));
					cliente.setNome(rs.getString("nome"));
					cliente.setCpf(rs.getString("cpf"));
					
					Agencia agencia = new Agencia();
					agencia.setCodigo(rs.getLong("agenciaPreferencial"));
					cliente.setAgenciaPreferencial(agencia);
					
					cliente.setDataNascimento(rs.getDate("data").toLocalDate());
					clientes.add(cliente);
				}

			} catch (SQLException e) {
				e.printStackTrace();

			}
			return clientes;
		}
		
		@Override
		public void inserir(Cliente cliente){
			
			try {

			Connection con = ConexaoUtil.getCon();
			String insert = "insert into cliente values (codigo, ?, ?, ?, ?) "; // cadastra a
			// variavel do
			// scan no banco

			// novo statement para executar o insert
			PreparedStatement insertStmt = con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);

			insertStmt.setString(1, cliente.getNome());
			insertStmt.setString(2, cliente.getCpf());
			
			insertStmt.setDate(3, Date.valueOf(cliente.getDataNascimento()));
			
			insertStmt.setLong(4, cliente.getAgenciaPreferencial().getCodigo()); //pega o codigo da agencia
			
			// execução do insert
			insertStmt.executeUpdate();
			
			// vão popular o codigo da agencia
			ResultSet resultSet = insertStmt.getGeneratedKeys();
			resultSet.next();
			cliente.setCodigo(resultSet.getLong(1));

			} catch (SQLException e) {
				// tratamento do erro de conexão com o banco
				e.printStackTrace();
			}
		}
			
//			--abaixo metodo generico que funciona só para 1 campo--
			
//			Connection con = ConexaoUtil.getCon();
//			Scanner sc = new Scanner(System.in);
//			System.out.println("digite o/a "+nomeCampo+" da tabela "+nomeTabela+": ");
//			String atributo = sc.nextLine();
//			sc.close();
//			String insert = "insert into "+nomeTabela+" ("+nomeCampo+") values (?)"; // cadastra a
//			// variavel do
//			// scan no banco
	//
//			// novo statement para executar o insert
//			PreparedStatement insertStmt = con.prepareStatement(insert);
	//
//			insertStmt.setString(1, atributo);
//			// execução do insert
//			insertStmt.executeUpdate();
	//
//			} catch (SQLException e) {
//				// tratamento do erro de conexão com o banco
//				e.printStackTrace();
//			}
			
		@Override
			public void alterar(Cliente cliente){
				
				try {

				Connection con = ConexaoUtil.getCon();
				String update = "update cliente set nome= ?, cpf= ?, data=?, agenciaPreferencial=? where codigo= ?"; // cadastra a
				// variavel do
				// scan no banco

				// novo statement para executar o insert
				PreparedStatement updateStmt = con.prepareStatement(update);

				updateStmt.setString(1, cliente.getNome());
				updateStmt.setString(2, cliente.getCpf());
				
				updateStmt.setDate(3, Date.valueOf(cliente.getDataNascimento()));
				updateStmt.setLong(4, cliente.getAgenciaPreferencial().getCodigo());
				
				updateStmt.setLong(5, cliente.getCodigo());

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
				String delete = "delete from cliente where codigo= ?"; // cadastra a
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
	public Cliente get(Long codigo) { //pega somente um cliente, pelo codigo
	

	
			try {

				Connection con = ConexaoUtil.getCon();
				// classe que sabe executar comandos sql
				Statement stmt = con.createStatement();
				// comando sql q desejo executar
				String sql = "select * from cliente where codigo = "+codigo;
				// execução do comando e o resultado é armazenado no ResultSet
				ResultSet rs = stmt.executeQuery(sql); // uma consulta, resultado
				rs.next();										// esta
														// dentro do ResultSet
	
					Cliente cliente = new Cliente();
					cliente.setCodigo(rs.getLong("codigo"));
					cliente.setNome(rs.getString("nome"));
					return cliente;
				

			} catch (SQLException e) {
				e.printStackTrace();

			}
			return null;

	}

}