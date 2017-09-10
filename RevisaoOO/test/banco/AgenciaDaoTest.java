package banco;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.edu.unoesc.revisaoOO.modelo.Agencia;
import dao.AgenciaDao;
import dao.DaoFactory;

public class AgenciaDaoTest {
	
	
	@Before
	public void init(){
		System.setProperty("ambiente", "test");
	
	}
	
	@Test
	public void deveInserirUmaUf(){
		AgenciaDao agenciaDao = DaoFactory.get().agenciaDao();
		Agencia agencia = new Agencia();
		agencia.setNome("teste");
		agencia.setNumero("123");
		agenciaDao.inserir(agencia);
		assertNotNull(agencia.getCodigo());
	}
	




}
