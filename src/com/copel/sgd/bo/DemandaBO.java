package com.copel.sgd.bo;

import java.util.Vector;

import com.copel.BO;
import com.copel.Ferramentas;
import com.copel.dao.Proxy;
import com.copel.sgd.Demanda;
import com.copel.sgd.dao.DemandaDAO;
import com.copel.swing.Funcoes;

public class DemandaBO extends Demanda implements BO{
	
	public Proxy proxyDao() {
		return new Proxy();
	}
	
	public DemandaBO(){
	}
	
	@SuppressWarnings("unchecked")
	public Vector<Demanda> carregarTodos() throws Exception {
		DemandaDAO dao = new DemandaDAO();

		return (Vector<Demanda>) this.proxyDao().carregarTodos(dao);
	}
		
	public void carregar(Object obj) throws Exception {
		String campo = Funcoes.getTexto(obj);
		DemandaDAO dao = new DemandaDAO(campo);
		
		this.proxyDao().carregar(dao);
		this.copiar(dao);
	}

	public String salvar(Object[] obj) throws Exception {
		DemandaDAO dao = new DemandaDAO();
		try {
			String id =  Funcoes.getTexto(obj[0]);
			String titulo =  Funcoes.getTexto(obj[1]);
			//Elemento não utilizado, empregado unicamente para filtrar os empreendimentos.
			//String tipoEmpreendimento = Ferramentas.getTexto(obj[2]);
			String empreendimento = Funcoes.getTexto(obj[3]);
			String projeto = Funcoes.getTexto(obj[4]);
			String categoria = Funcoes.getTexto(obj[5]);
			String responsavel = Funcoes.getTexto(obj[6]);
			long dataConclusaoPrevista = Long.valueOf(Funcoes.getTexto(obj[7]));
			String tarefaContinua = Funcoes.getTexto(obj[8]);
			String descricao = Funcoes.getTexto(obj[9]);
			long dataConclusaoReal =  Long.valueOf(Funcoes.getTexto(obj[10]));
			dao = new DemandaDAO(id, titulo, empreendimento, projeto, categoria, responsavel, dataConclusaoPrevista, tarefaContinua, descricao, dataConclusaoReal);
		} catch (NullPointerException e) {
			dao.copiar(this);
		}
		return this.proxyDao().salvar(dao);
	}

	public String remover(Object obj) throws Exception {
		int id = Ferramentas.varificarId(obj);
		DemandaDAO dao = new DemandaDAO(id);
		this.proxyDao().carregar(dao);		
		return this.proxyDao().remover(dao);
	}

	@SuppressWarnings("unchecked")
	public Vector<Demanda> carregarSimilares() throws Exception {
		DemandaDAO dao = new DemandaDAO();
		dao.copiar(this);
		return (Vector<Demanda>) this.proxyDao().carregarSimilares(dao);
	}
	
	public void finalizar() throws Exception{
		try {
			try{
				this.encerrar();
				this.salvar(null);
			}catch (Exception e2) {
				throw e2;				
			}
		} catch (Exception e3) {
			throw e3;
		}
	}
	
	
	public void delegar(String responsavelId) throws Exception {
		ContaBO contaBo = new ContaBO();
		contaBo.carregar(responsavelId);
		this.setResponsavel(contaBo);
		this.salvar(null);
	}

	public void reiniciar() throws Exception {
		try{
			this.reabrir();
			this.salvar(null);
		}catch (Exception e2) {
			throw e2;				
		}
	}
}
