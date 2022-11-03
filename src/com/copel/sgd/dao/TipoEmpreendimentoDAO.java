package com.copel.sgd.dao;

import com.copel.dao.BancoDeDados;
import com.copel.dao.DAO;
import com.copel.dao.Proxy;
import com.copel.sgd.TipoEmpreendimento;

public class TipoEmpreendimentoDAO extends TipoEmpreendimento implements DAO{
	
	public Proxy getProxy() {
		return new Proxy();
	}
			
	public TipoEmpreendimentoDAO() throws Exception {
		super();
	}
	
	public TipoEmpreendimentoDAO(String id, String descricao, String acronimo) throws Exception {
		super(id, descricao, acronimo);
	}
	
	public TipoEmpreendimentoDAO(int id) throws Exception {
		super(id);
	}
	
	public TipoEmpreendimentoDAO(String descricao) throws Exception {
		super(descricao);
	}
	
	public void parametrosInserir(BancoDeDados bd) throws Exception {
		bd.getSql().setString(1,this.getDescricao());
		bd.getSql().setString(2,this.getAcronimo());
	}

	public void parametrosRemover(BancoDeDados bd) throws Exception {
		bd.getSql().setInt(1,this.getControle().getId());
	}

	public void parametrosAtualizar(BancoDeDados bd) throws Exception {
		bd.getSql().setString(1,this.getDescricao());
		bd.getSql().setString(2,this.getAcronimo());
		bd.getSql().setInt(5, this.getControle().getId());
	}

	public void parametrosSelecionar(BancoDeDados bd) throws Exception {
		bd.adicionarCondicao(this.getControle().getId(),"ID = ? ");
		bd.adicionarCondicao(this.getDescricao(),"DESCRICAO = ? ","OR");
	}

	public void parametrosSelecionarSimilares(BancoDeDados bd)
			throws Exception {
	}

	public void lerColunas(BancoDeDados bd) throws Exception {
		this.getControle().setId(bd.getRs().getInt("ID"));
		this.setDescricao(bd.getRs().getString("DESCRICAO"));
		this.setAcronimo(bd.getRs().getString("ACRONIMO"));		
		
		this.getControle().lerControle(bd);
	}
	
	public String sentencaInserir(){
		return "INSERT INTO TIPO_EMPREENDIMENTO " +
				"(DESCRICAO, " +
				"ACRONIMO, " +
				"CRIADOR, " +
				"DATA_CRIACAO, " +
				"REVISOR, " +
				"DATA_REVISAO) " +
				"VALUES (?, ?, ?, ?, ?, ?)";
	}
				
	public String sentencaRemover(){
		return "DELETE FROM TIPO_EMPREENDIMENTO " +
				"WHERE ID = ?";
	}			
	
	public String sentencaAtualizar() {
		return "UPDATE TIPO_EMPREENDIMENTO SET " +
			"DESCRICAO = ?, " +
			"ACRONIMO =?, " +
			"REVISOR = ?, " +
			"DATA_REVISAO = ? " +
			"WHERE ID = ?";
	}
	
	public String sentencaSelecionar() {
		return  "SELECT ID, " +
			"DESCRICAO, " +
			"ACRONIMO, " +
			"CRIADOR, " +
			"DATA_CRIACAO, " +
			"REVISOR, " +
			"DATA_REVISAO " +
			"FROM TIPO_EMPREENDIMENTO ";
	}
}
