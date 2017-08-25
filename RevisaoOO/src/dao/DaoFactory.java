package dao;

public class DaoFactory {
	
	
	private static DaoFactory daoFactory;
	
	public static DaoFactory get(){
		if(daoFactory == null){
			daoFactory = new DaoFactory();
		}
		return daoFactory;
	}
	
	
	public AgenciaDao agenciaDao(){
		return new AgenciaJdbc();
	}
	
	public ClienteDao clienteDao(){
		return new ClienteJdbc();
	}

}
