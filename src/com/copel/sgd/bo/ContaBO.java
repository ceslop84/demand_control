package com.copel.sgd.bo;

import java.util.Vector;

import com.copel.BO;
import com.copel.Ferramentas;
import com.copel.dao.Proxy;
import com.copel.sgd.Conta;
import com.copel.sgd.dao.ContaDAO;
import com.copel.swing.Funcoes;

public class ContaBO extends Conta implements BO{
	
	public Proxy proxyDao() {
		return new Proxy();
	}
	
	public ContaBO(){
	}
	
	@SuppressWarnings("unchecked")
	public Vector<Conta> carregarTodos() throws Exception {
		ContaDAO dao = new ContaDAO();
		return (Vector<Conta>) this.proxyDao().carregarTodos(dao);
	}
	
	public void carregar(Object obj) throws Exception {
		String campo = Funcoes.getTexto(obj);
//		ContaDAO dao = new ContaDAO("C052405");
		ContaDAO dao = new ContaDAO(campo);
		this.proxyDao().carregar(dao);
		this.copiar(dao);
	}

	public String salvar(Object[] obj) throws Exception {
		String id = Funcoes.getTexto(obj[0]);
		String nome = Funcoes.getTexto(obj[1]);
		String registro = Funcoes.getTexto(obj[2]);
		String administrador = Funcoes.getTexto(obj[3]);
		ContaDAO dao = new ContaDAO(id, nome, registro, administrador);
		
		return this.proxyDao().salvar(dao);
	}

	public String remover(Object obj) throws Exception {
		int id = Ferramentas.varificarId(obj);
		ContaDAO dao = new ContaDAO(id);
		this.proxyDao().carregar(dao);	
		return this.proxyDao().remover(dao);
	}

	public Vector<?> carregarSimilares() throws Exception {
		return null;
	}
}
