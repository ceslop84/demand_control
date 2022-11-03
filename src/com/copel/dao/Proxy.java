package com.copel.dao;

import java.util.Vector;

import com.copel.Mensagem;
import com.copel.sgd.dao.ContaDAO;

public class Proxy{
	
	private BancoDeDados bd;
	
	public Proxy(){		
	}
	
	public String salvar(DAO dao) throws Exception {
		bd = new BancoDeDados();
		String saida = "";
		if (dao.getControle().existe()){
			try {
				bd.atualizar(dao);
				bd.executar();
				saida = Mensagem.sucessoAtualizar;
			} catch (Exception e) {			
				bd.getLog().adicionarEJogarExcecao(Mensagem.erroAtualizar + dao.getObjeto(), e);
			}
		}else{
			try {
				bd.inserir(dao);
				bd.executar();
				saida = Mensagem.sucessoAdicionar;
			} catch (Exception e) {
				bd.getLog().adicionarEJogarExcecao(Mensagem.erroAdicionar + dao.getObjeto(), e);
			}
		}
		bd.fechar();
		return saida + dao.getObjeto();
	}

	public String remover(DAO dao) throws Exception {
		bd = new BancoDeDados();
		String saida = "";
			try {
				bd.remover(dao);
				bd.executar();
				bd.fechar();
				saida = Mensagem.sucessoRemover;
			} catch (Exception e) {
				bd.getLog().adicionarEJogarExcecao(Mensagem.erroRemover + dao.getObjeto(), e);
			}
			bd.fechar();
		return saida + dao.getObjeto();
	}
	
	public void carregar(DAO dao) throws Exception {
		bd = new BancoDeDados();
		try {
			bd.selecionar(dao);
			bd.executar();
			bd.carregar(dao);
			bd.fechar();
		} catch (Exception e) {
			bd.fechar();
			bd.getLog().adicionarEJogarExcecao(Mensagem.erroCarregar + dao.getObjeto(), e);
		}
	}
	
	public Vector<?> carregarTodos(DAO dao) throws Exception {
		bd = new BancoDeDados();
		Vector<?> saida = null;
		try {
			bd.selecionarTodos(dao);
			bd.executar();
			saida = bd.carregarVarios(dao);
			bd.fechar();			
		} catch (Exception e) {
			bd.fechar();
			bd.getLog().adicionarEJogarExcecao(Mensagem.erroCarregarTodos + dao.getObjeto(), e);
		}
		return saida;
	}
	
	public Vector<?> carregarSimilares(DAO dao) throws Exception {
		bd = new BancoDeDados();
		Vector<?> saida = null;
		try {
			bd.selecionarSimilares(dao);
			bd.executar();
			saida = bd.carregarVarios(dao);
			bd.fechar();	
		} catch (Exception e) {
			bd.fechar();
			bd.getLog().adicionarEJogarExcecao(Mensagem.erroCarregarTodos + dao.getObjeto(), e);
		}
		return saida;
	}
	
	public void carregarSemControles(ContaDAO contaDao) throws Exception {
		bd = new BancoDeDados();
		try {
			bd.selecionar(contaDao);
			bd.executar();
			bd.carregarSemControle(contaDao);
			bd.fechar();
		} catch (Exception e) {
			bd.fechar();
			bd.getLog().adicionarEJogarExcecao(Mensagem.erroCarregar + contaDao.getObjeto(), e);
		}	
	}
}
