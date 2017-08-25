package br.edu.unoesc.revisaoOO.modelo;

import java.io.Serializable;

import br.edu.unoesc.componente.RenderizaCombo;

public class Agencia implements Serializable, RenderizaCombo{
	

	// implementado o serializable para salvar arquivos no pc, substituindo o banco de dados
	private static final long serialVersionUID = -1320599973517592837L;
	private String nome;
	private String numero;
	private Long codigo;
	
	
	
	
	public Agencia() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Agencia(String nome, String numero) {
		super();
		this.nome = nome;
		this.numero = numero;
	}



	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public String getNumero() {
		return numero;
	}



	public void setNumero(String numero) {
		this.numero = numero;
	}

	
	@Override
	public String toString(){
		return this.nome+ " "+this.numero;
	}



	@Override
	public String getText() {
		
		return nome;
	}



	public Long getCodigo() {
		return codigo;
	}



	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}



	// criar com o botao direito do mouse source
	//usado para o java saber como comparar para aparecever no combobox do cliente quando editar
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Agencia other = (Agencia) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
	
	
	
	

	
}
