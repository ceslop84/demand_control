package com.copel.sgd.dao;

import com.copel.dao.BancoDeDados;
import com.copel.dao.DAO;
import com.copel.dao.Proxy;
import com.copel.sgd.Conta;

public class ContaDAO extends Conta implements DAO{
	
	public Proxy getProxy() {
		return new Proxy();
	}
	
	public ContaDAO() throws Exception {
		super();		
	}

	public ContaDAO(int id) throws Exception {
		super(id);
	}
	
	public ContaDAO(String campo) throws Exception {
		super(campo);
	}
	
	public ContaDAO(String id, String nome, String registro, String administrador) throws Exception {
		super(id, nome, registro, administrador);
	}

	public void parametrosInserir(BancoDeDados bd) throws Exception {
		bd.getSql().setString(1,this.getNome());
		bd.getSql().setString(2,this.getRegistro());
		bd.getSql().setString(3,this.isAdministradorString());
	}

	public void parametrosRemover(BancoDeDados bd) throws Exception {
		bd.getSql().setInt(1,this.getControle().getId());
	}

	public void parametrosAtualizar(BancoDeDados bd) throws Exception{
		bd.getSql().setString(1,this.getNome());
		bd.getSql().setString(2,this.getRegistro());
		bd.getSql().setString(3,this.isAdministradorString());
		bd.getSql().setInt(6, this.getControle().getId());
	}

	public void parametrosSelecionar(BancoDeDados bd) throws Exception {
		bd.adicionarCondicao(this.getControle().getId(), "ID = ?");
		bd.adicionarCondicao(this.getRegistro(), "REGISTRO = ?", "OR");
		bd.adicionarCondicao(this.getNome(), "NOME = ?", "OR");
	}
	
	public void parametrosSelecionarSimilares(BancoDeDados bd)
			throws Exception {
	}
	
	public void lerColunas(BancoDeDados bd) throws Exception {
		this.getControle().setId(bd.getRs().getInt("ID"));
		this.setNome(bd.getRs().getString("NOME"));
		this.setRegistro(bd.getRs().getString("REGISTRO"));
		this.setAdministrador(bd.getRs().getString("ADMINISTRADOR"));	
		
		this.getControle().lerControle(bd);
	}
	
	public void lerColunasSemControle(BancoDeDados bd) throws Exception {
		this.getControle().setId(bd.getRs().getInt("ID"));
		this.setNome(bd.getRs().getString("NOME"));
		this.setRegistro(bd.getRs().getString("REGISTRO"));
		this.setAdministrador(bd.getRs().getString("ADMINISTRADOR"));			
	}

	public String sentencaInserir() {
		return "INSERT INTO CONTA " +
				"(NOME, " +
				"REGISTRO, " +
				"ADMINISTRADOR, " +
				"CRIADOR, " +
				"DATA_CRIACAO, " +
				"REVISOR, " +
				"DATA_REVISAO) " +
				"VALUES (?, ?, ?, ?, ?, ?, ?) ";
	}

	public String sentencaRemover() {
		return "DELETE FROM " +
				"CONTA WHERE ID = ?";
	}

	public String sentencaAtualizar() {
		return "UPDATE CONTA SET " +
				"NOME = ?, " +
				"REGISTRO = ?, " +
				"ADMINISTRADOR = ?, " +
				"REVISOR = ?, " +
				"DATA_REVISAO = ? " +
				"WHERE ID = ? ";
	}

	public String sentencaSelecionar() {
		return "SELECT " +
				"ID, " +
				"NOME, " +
				"REGISTRO, " +
				"ADMINISTRADOR," +
				"CRIADOR, " +
				"REVISOR, " +
				"DATA_CRIACAO, " +
				"DATA_REVISAO " +
				"FROM CONTA ";
	}
}
