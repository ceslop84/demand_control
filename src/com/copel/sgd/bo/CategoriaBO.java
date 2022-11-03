package com.copel.sgd.bo;

import java.util.Vector;

import com.copel.BO;
import com.copel.Ferramentas;
import com.copel.dao.Proxy;
import com.copel.sgd.Categoria;
import com.copel.sgd.dao.CategoriaDAO;
import com.copel.swing.Funcoes;


public class CategoriaBO extends Categoria implements BO{
	
	public Proxy proxyDao() {
		return new Proxy();
	}
		
	public CategoriaBO(){
	}
	
	@SuppressWarnings("unchecked")
	public Vector<Categoria> carregarTodos() throws Exception {
		CategoriaDAO dao = new CategoriaDAO();
		return (Vector<Categoria>) this.proxyDao().carregarTodos(dao);
	}
	
	public void carregar(Object obj) throws Exception {
		String campo = Funcoes.getTexto(obj);
		CategoriaDAO dao = new CategoriaDAO(campo);
		this.proxyDao().carregar(dao);
		this.copiar(dao);
	}

	public String salvar(Object[] obj) throws Exception {
		String id = Funcoes.getTexto(obj[0]);	
		String descricao = Funcoes.getTexto(obj[1]);
		String acronimo = Funcoes.getTexto(obj[2]);
		CategoriaDAO dao = new CategoriaDAO(id, descricao, acronimo);
		
		return this.proxyDao().salvar(dao);
	}

	public String remover(Object obj) throws Exception {
		int id = Ferramentas.varificarId(obj);
		CategoriaDAO dao = new CategoriaDAO(id);
		this.proxyDao().carregar(dao);			
		return this.proxyDao().remover(dao);
	}

	public Vector<?> carregarSimilares() throws Exception {
		return null;
	}
}
