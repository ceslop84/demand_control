package com.copel.sgd.bo;

import java.util.Vector;

import com.copel.BO;
import com.copel.Ferramentas;
import com.copel.dao.Proxy;
import com.copel.sgd.Projeto;
import com.copel.sgd.dao.ProjetoDAO;
import com.copel.swing.Funcoes;

public class ProjetoBO extends Projeto implements BO {
	
	public Proxy proxyDao() {
		return new Proxy();
	}
	
	public ProjetoBO(){
	}
	
	@SuppressWarnings("unchecked")
	public Vector<Projeto> carregarTodos() throws Exception {
		ProjetoDAO dao = new ProjetoDAO();
		
		return (Vector<Projeto>) this.proxyDao().carregarTodos(dao);
	}

	public void carregar(Object obj) throws Exception {
		String campo = Funcoes.getTexto(obj);
		ProjetoDAO dao = new ProjetoDAO(campo);

		this.proxyDao().carregar(dao);
		this.copiar(dao);
	}

	public String salvar(Object[] obj) throws Exception {
		String id = Funcoes.getTexto(obj[0]);
		String nome = Funcoes.getTexto(obj[1]);
		String acronimo = Funcoes.getTexto(obj[2]);
		ProjetoDAO dao = new ProjetoDAO(id, nome, acronimo);

		return this.proxyDao().salvar(dao);
	}
	
	public String remover(Object obj) throws Exception {
		int id = Ferramentas.varificarId(obj);
		ProjetoDAO dao = new ProjetoDAO(id);
		this.proxyDao().carregar(dao);	
		return this.proxyDao().remover(dao);
	}

	public Vector<?> carregarSimilares() throws Exception {
		return null;
	}
}
