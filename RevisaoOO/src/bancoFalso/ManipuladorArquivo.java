package bancoFalso;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ManipuladorArquivo {
	
	/**
	 * 
	 * Serializabla objetos de uma lista ce um arquivo
	 * 
	 * @param dados
	 * 		List extends serializable
	 * @param pathFile
	 * 		String caminho do arquivo que deseja gravar
	 */
	
	public void gravar(List<? extends Serializable> dados, String pathFile) {
		try {
			// cria o arquivo que sera utilizado
			FileOutputStream file = new FileOutputStream(pathFile);
			// cria o manipular do arquivo
			ObjectOutputStream out = new ObjectOutputStream(file);
			// escreve os objetos dentro do arquivo
			out.writeObject(dados);
			//fecha o manipular do arquivo
			out.close();
			// fecha o arqivo
			file.close();
		} catch (IOException e) {
			// se dar algum erro cai aqui, ele imprimi o erro
			e.printStackTrace();
		}
	}
	
	public <T extends Serializable> List<T> recuperar(String pathFile) {
		List<T> retorno = new ArrayList<>();
		try {
			//Cria ou encontra arquivo
			FileInputStream file = new FileInputStream(pathFile);
			//Manipulador de arquivo
			ObjectInputStream ois = new ObjectInputStream(file);
			//Recupera do arquivos os objetos serializados.
			retorno = (List) ois.readObject();
			ois.close();
			file.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
		}
		return retorno;
}

}
