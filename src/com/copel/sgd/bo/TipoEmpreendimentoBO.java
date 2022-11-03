package com.copel.sgd.bo;

import java.util.Vector;

import com.copel.BO;
import com.copel.Ferramentas;
import com.copel.dao.Proxy;
import com.copel.sgd.TipoEmpreendimento;
import com.copel.sgd.dao.TipoEmpreendimentoDAO;
import com.copel.swing.Funcoes;

public class TipoEmpreendimentoBO extends TipoEmpreendimento implements BO{
	
	public Proxy proxyDao() {
		return new Proxy();
	}
	
	public TipoEmpreendimentoBO(){
	}

	@SuppressWarnings("unchecked")
	public Vector<TipoEmpreendimento> carregarTodos() throws Exception {
		TipoEmpreendimentoDAO dao = new TipoEmpreendimentoDAO();
		return (Vector<TipoEmpreendimento>) this.proxyDao().carregarTodos(dao);
	}

	public void carregar(Object obj) throws Exception {
		String campo = Funcoes.getTexto(obj);
		TipoEmpreendimentoDAO dao = new TipoEmpreendimentoDAO(campo);

		this.proxyDao().carregar(dao);
		this.copiar(dao);
	}

	public String salvar(Object[] obj) throws Exception {
		String id = Funcoes.getTexto(obj[0]);
		String descricao = Funcoes.getTexto(obj[1]);
		String acronimo = Funcoes.getTexto(obj[2]);
		TipoEmpreendimentoDAO dao = new TipoEmpreendimentoDAO(id, descricao, acronimo);

		return this.proxyDao().salvar(dao);
	}

	public String remover(Object obj) throws Exception {
		int id = Ferramentas.varificarId(obj);
		TipoEmpreendimentoDAO dao = new TipoEmpreendimentoDAO(id);
		this.proxyDao().carregar(dao);	
		return this.proxyDao().remover(dao);
	}

	public Vector<?> carregarSimilares() throws Exception {
		return null;
	}
}
