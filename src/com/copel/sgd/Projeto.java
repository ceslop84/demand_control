package com.copel.sgd;

import com.copel.dao.Controle;
import com.copel.Ferramentas;

public class Projeto{
	
	private String descricao = "";
	private String acronimo = "";
	private Controle controle = new Controle();
	
	public String getObjeto() {
		return "projeto";
	}
	
	public Projeto() {
	}
	
	public Projeto(String campo) throws Exception{
		try {
			this.getControle().setId(campo);
		} catch (Exception e) {
			this.setDescricao(campo);
		}
	}
		
	public Projeto(String id, String descricao, String acronimo) throws Exception{
		this.getControle().setId(id);
		this.setDescricao(descricao);
		this.setAcronimo(acronimo);
	}
	
	public Projeto(int id, String descricao, String acronimo, String empreendimento) throws Exception{
		this.getControle().setId(id);
		this.setDescricao(descricao);
		this.setAcronimo(acronimo);
	}
		
	public Projeto(int id) {
		this.getControle().setId(id);
	}

	public Controle getControle() {
		return controle;
	}
	
	public void setControle(Controle controle) {
		this.controle = controle;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) throws Exception {
		this.descricao = Ferramentas.verificarTexto("Descrição/Nome", descricao);
	}

	public String getAcronimo() {
		return acronimo;
	}

	public void setAcronimo(String acronimo) throws Exception {
		this.acronimo = Ferramentas.verificarAcronimo(acronimo);
	}
	
	public String toString() {
		try {
			return Ferramentas.deArrayParaString(this.toArray());
		} catch (Exception e) {
			return null;
		}
	}
	
	public String[] toArray() throws Exception {
		String[] elemento = new String[2];
		elemento[0] = this.getDescricao();
		elemento[1] = this.getAcronimo();
		return this.controle.toArray(elemento);
	}	
	
	public void copiar(Projeto original) throws Exception {
		this.descricao = original.descricao;
		this.acronimo = original.acronimo;	
		this.controle = (Controle) original.controle.clonar();
	}
	
	public Object clonar() throws Exception {
		Projeto clone = new Projeto();
		clone.copiar(this);
		return clone;
	}
}
