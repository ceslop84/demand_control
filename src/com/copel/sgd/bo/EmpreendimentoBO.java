package com.copel.sgd.bo;

import java.util.Vector;

import com.copel.BO;
import com.copel.Ferramentas;
import com.copel.dao.Proxy;
import com.copel.sgd.Empreendimento;
import com.copel.sgd.dao.EmpreendimentoDAO;
import com.copel.swing.Funcoes;

public class EmpreendimentoBO extends Empreendimento implements BO {
	
	public Proxy proxyDao() {
		return new Proxy();
	}
	
	public EmpreendimentoBO(){
	}
	
	@SuppressWarnings("unchecked")
	public Vector<Empreendimento> carregarTodos() throws Exception {
		EmpreendimentoDAO dao = new EmpreendimentoDAO();
		
		return (Vector<Empreendimento>) this.proxyDao().carregarTodos(dao);
	}

	public void carregar(Object obj) throws Exception {
		String campo = Funcoes.getTexto(obj);
		EmpreendimentoDAO dao = new EmpreendimentoDAO(campo);
		
		this.proxyDao().carregar(dao);
		this.copiar(dao);	
	}

	public String salvar(Object[] obj) throws Exception {
		String id = Funcoes.getTexto(obj[0]);
		String descricao = Funcoes.getTexto(obj[1]);
		String acronimo = Funcoes.getTexto(obj[2]);
		String tipo = Funcoes.getTexto(obj[3]);
		EmpreendimentoDAO dao = new EmpreendimentoDAO(id, descricao, acronimo, tipo);

		return this.proxyDao().salvar(dao);
	}
	
	public String remover(Object obj) throws Exception {
		int id = Ferramentas.varificarId(obj);
		EmpreendimentoDAO dao = new EmpreendimentoDAO(id);
		this.proxyDao().carregar(dao);	
		return this.proxyDao().remover(dao);
	}

	@SuppressWarnings("unchecked")
	public Vector<Empreendimento> carregarSimilares() throws Exception {
		EmpreendimentoDAO dao = new EmpreendimentoDAO();
		dao.copiar(this);
		return (Vector<Empreendimento>) this.proxyDao().carregarSimilares(dao);
	}
}
