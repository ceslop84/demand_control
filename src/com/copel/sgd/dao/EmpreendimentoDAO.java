package com.copel.sgd.dao;

import com.copel.dao.BancoDeDados;
import com.copel.dao.DAO;
import com.copel.dao.Proxy;
import com.copel.sgd.Empreendimento;

public class EmpreendimentoDAO extends Empreendimento implements DAO{
	
	public Proxy getProxy() {
		return new Proxy();
	}
	
	public EmpreendimentoDAO(){
		super();
	}
		
	public EmpreendimentoDAO(String id, String descricao, String acronimo, String tipo) throws Exception {
		super(id, descricao, acronimo, tipo);
	}

	public EmpreendimentoDAO(int id){
		super(id);
	}
	
	public EmpreendimentoDAO(String descricao) throws Exception {
		super(descricao);
	}

	public void parametrosInserir(BancoDeDados bd) throws Exception {	
		TipoEmpreendimentoDAO tipoEmpreendimentoDao = new TipoEmpreendimentoDAO(this.getTipo().getDescricao());
		this.getProxy().carregar(tipoEmpreendimentoDao);
		this.setTipo(tipoEmpreendimentoDao);
		
		bd.getSql().setString(1,this.getDescricao());
		bd.getSql().setString(2,this.getAcronimo());
		bd.getSql().setInt(3,this.getTipo().getControle().getId());
	}

	public void parametrosRemover(BancoDeDados bd) throws Exception {
		bd.getSql().setInt(1,this.getControle().getId());
	}

	public void parametrosAtualizar(BancoDeDados bd) throws Exception{			
		TipoEmpreendimentoDAO tipoEmpreendimentoDao = new TipoEmpreendimentoDAO(this.getTipo().getDescricao());
		this.getProxy().carregar(tipoEmpreendimentoDao);
		this.setTipo(tipoEmpreendimentoDao);
		
		bd.getSql().setString(1,this.getDescricao());
		bd.getSql().setString(2,this.getAcronimo());
		bd.getSql().setInt(3,this.getTipo().getControle().getId());
		bd.getSql().setInt(6, this.getControle().getId());
	}
	
	public void parametrosSelecionar(BancoDeDados bd) throws Exception {	
		bd.adicionarCondicao(this.getControle().getId(),"ID = ?");
		bd.adicionarCondicao(this.getDescricao(),"DESCRICAO = ?","OR");
	}

	public void parametrosSelecionarSimilares(BancoDeDados bd) throws Exception {		
		bd.adicionarCondicao(this.getTipo().getControle().getId(), "TIPO_EMPREENDIMENTO = ? ");
	}
	
	public void lerColunas(BancoDeDados bd) throws Exception {
		this.getControle().setId(bd.getRs().getInt("ID"));
		this.setDescricao(bd.getRs().getString("DESCRICAO"));
		this.setAcronimo(bd.getRs().getString("ACRONIMO"));
		
		TipoEmpreendimentoDAO tipoEmpreendimentoDao = new TipoEmpreendimentoDAO(bd.getRs().getInt("TIPO_EMPREENDIMENTO"));
		this.getProxy().carregar(tipoEmpreendimentoDao);
		this.setTipo(tipoEmpreendimentoDao);	
		
		this.getControle().lerControle(bd);
	}
	
	public String sentencaInserir(){
		return "INSERT INTO EMPREENDIMENTO " +
				"(DESCRICAO, " +
				"ACRONIMO, " +
				"TIPO_EMPREENDIMENTO, " +
				"CRIADOR, " +
				"DATA_CRIACAO, " +
				"REVISOR, " +
				"DATA_REVISAO) " +
				"VALUES (?,?, ?, ?, ?, ?, ?)";
	}			
	
	public String sentencaRemover(){
		return "DELETE FROM EMPREENDIMENTO " +
				"WHERE ID = ?";
	}			
	
	public String sentencaAtualizar(){
		return "UPDATE EMPREENDIMENTO SET " +
				"DESCRICAO = ?, " +
				"ACRONIMO =?, " +
				"TIPO_EMPREENDIMENTO = ?, " +
				"REVISOR = ?, " +
				"DATA_REVISAO = ? " +
				"WHERE ID = ?";
	}
	
	public String sentencaSelecionar(){
		return "SELECT ID, " +
				"DESCRICAO, " +
				"ACRONIMO, " +
				"TIPO_EMPREENDIMENTO, " +
				"CRIADOR, " +
				"REVISOR, " +
				"DATA_CRIACAO, " +
				"DATA_REVISAO " +
				"FROM EMPREENDIMENTO ";
	}
}
