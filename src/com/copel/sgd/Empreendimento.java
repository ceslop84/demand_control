package com.copel.sgd;

import com.copel.dao.Controle;
import com.copel.Ferramentas;

public class Empreendimento{
	
	private String descricao = "";
	private String acronimo = "";
	private TipoEmpreendimento tipo;
	private Controle controle = new Controle();
	
	public String getObjeto() {
		return "empreendimento";
	}
	
	public Empreendimento() {
	}
		
	public Empreendimento(String id, String descricao, String acronimo, String tipo) throws Exception{
		this.getControle().setId(id);
		this.setDescricao(descricao);
		this.setAcronimo(acronimo);
		this.setTipo(tipo);
	}
	
	public Empreendimento(int id, String descricao, String acronimo, String tipo) throws Exception{
		this.getControle().setId(id);
		this.setDescricao(descricao);
		this.setAcronimo(acronimo);
		this.setTipo(tipo);
	}
	
	public Empreendimento(String campo) throws Exception{
		try {
			this.getControle().setId(campo);
		} catch (Exception e) {
			this.setDescricao(campo);
		}
	}
		
	public Empreendimento(int id) {
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
		
	public TipoEmpreendimento getTipo() {
		return tipo;
	}

	public void setTipo(String tipoEmpreendimento) throws Exception {
		String tipo = Ferramentas.verificarTexto("Tipo de empreendimento", tipoEmpreendimento);
		this.tipo = new TipoEmpreendimento(tipo);
	}
	
	public void setTipo(TipoEmpreendimento tipoEmpreendimento) throws Exception {
		this.tipo = tipoEmpreendimento;
	}
		
	public String toString() {
		try {
			return Ferramentas.deArrayParaString(this.toArray());
		} catch (Exception e) {
			return null;
		}
	}
	
	public String[] toArray() throws Exception {
		String[] elemento = new String[3];
		elemento[0] = this.getDescricao();
		elemento[1] = this.getAcronimo();
		elemento[2] = this.getTipo().getDescricao();
		return this.controle.toArray(elemento);
	}
	
	public void copiar(Empreendimento original) throws Exception {
		this.descricao = original.descricao;
		this.acronimo = original.acronimo;	
		this.tipo = original.tipo;
		this.controle = (Controle) original.controle.clonar();		
	}
	
	public Object clonar() throws Exception {
		Empreendimento clone = new Empreendimento();
		clone.copiar(this);
		return clone;
	}
}
