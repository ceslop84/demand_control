package com.copel.sgd.dao;

import com.copel.Ferramentas;
import com.copel.dao.BancoDeDados;
import com.copel.dao.DAO;
import com.copel.dao.Proxy;
import com.copel.sgd.Demanda;

public class DemandaDAO extends Demanda implements DAO{
	
	public Proxy getProxy() {
		return new Proxy();
	}
		
	public DemandaDAO() throws Exception {
		super();
	}
	
	public DemandaDAO(String id, String titulo, String empreendimento, String projeto, String categoria, String responsavel, long dataConclusaoPrevista, String tarefaContinua, String descricao, long dataConclusaoReal) throws Exception{		
		super(id, titulo, empreendimento, projeto, categoria, responsavel, dataConclusaoPrevista, tarefaContinua, descricao, dataConclusaoReal);
	}
	
	public DemandaDAO(String campo) throws Exception{		
		super(campo);
	}
	
	public DemandaDAO(int id) throws Exception{		
		this.getControle().setId(id);
	}	
	
	public void parametrosInserir(BancoDeDados bd) throws Exception {
		EmpreendimentoDAO empreendimentoDao = new EmpreendimentoDAO(this.getEmpreendimento().getDescricao());
		this.getProxy().carregar(empreendimentoDao);
		this.setEmpreendimento(empreendimentoDao);

		ProjetoDAO projetoDao = new ProjetoDAO(this.getProjeto().getDescricao());
		this.getProxy().carregar(projetoDao);
		this.setProjeto(projetoDao);
						
		CategoriaDAO categoriaDao = new CategoriaDAO(this.getCategoria().getDescricao());
		this.getProxy().carregar(categoriaDao);
		this.setCategoria(categoriaDao);
		
		ContaDAO responsavelDao = new ContaDAO(this.getResponsavel().getNome());
		this.getProxy().carregar(responsavelDao);
		this.setResponsavel(responsavelDao);
		
		bd.getSql().setString(1,this.getTitulo());
		bd.getSql().setInt(2,this.getEmpreendimento().getControle().getId());
		bd.getSql().setInt(3,this.getProjeto().getControle().getId());
		bd.getSql().setInt(4,this.getCategoria().getControle().getId());
		bd.getSql().setInt(5,this.getResponsavel().getControle().getId());
		bd.getSql().setLong(6,this.getDataConclusaoPrevista());
		bd.getSql().setString(7,Ferramentas.converterBooleanUmZero(this.isTarefaContinua()));
		bd.getSql().setLong(8,this.getDataConclusaoReal());
		bd.getSql().setString(9,this.getDescricao());
	}

	public void parametrosRemover(BancoDeDados bd) throws Exception {
		bd.getSql().setInt(1,this.getControle().getId());
	}

	public void parametrosAtualizar(BancoDeDados bd) throws Exception {
		EmpreendimentoDAO empreendimentoDao = new EmpreendimentoDAO(this.getEmpreendimento().getDescricao());
		this.getProxy().carregar(empreendimentoDao);
		this.setEmpreendimento(empreendimentoDao);

		ProjetoDAO projetoDao = new ProjetoDAO(this.getProjeto().getDescricao());
		this.getProxy().carregar(projetoDao);
		this.setProjeto(projetoDao);
						
		CategoriaDAO categoriaDao = new CategoriaDAO(this.getCategoria().getDescricao());
		this.getProxy().carregar(categoriaDao);
		this.setCategoria(categoriaDao);
		
		ContaDAO responsavelDao = new ContaDAO(this.getResponsavel().getNome());
		this.getProxy().carregar(responsavelDao);
		this.setResponsavel(responsavelDao);				
		
		bd.getSql().setString(1,this.getTitulo());
		bd.getSql().setInt(2,this.getEmpreendimento().getControle().getId());
		bd.getSql().setInt(3,this.getProjeto().getControle().getId());
		bd.getSql().setInt(4,this.getCategoria().getControle().getId());
		bd.getSql().setInt(5,this.getResponsavel().getControle().getId());
		bd.getSql().setLong(6,this.getDataConclusaoPrevista());
		bd.getSql().setString(7,Ferramentas.converterBooleanUmZero(this.isTarefaContinua()));
		bd.getSql().setLong(8,this.getDataConclusaoReal());
		bd.getSql().setString(9,this.getDescricao());
		bd.getSql().setInt(12, this.getControle().getId());
	}

	public void parametrosSelecionar(BancoDeDados bd) throws Exception {
		bd.adicionarCondicao(this.getControle().getId(),"ID = ?");
		bd.adicionarCondicao(this.getTitulo(),"TITULO = ?", "OR");
			
	}
	
	public void parametrosSelecionarSimilares(BancoDeDados bd) throws Exception {
		try {
			bd.addCondicao(String.valueOf(this.getResponsavel().getControle().getId()), "RESPONSAVEL = ? ");
		} catch (Exception e) {
		}
		try {
			bd.adicionarCondicao(this.getControle().getCriador().getControle().getId(), "CRIADOR = ? ", "OR");
		} catch (Exception e) {
		}
		try {
			if (this.getDataConclusaoReal()==0){
				bd.adicionarCondicao(0, "DATA_CONCLUSAO_REAL = ?", "AND");
			}else{
				bd.adicionarCondicao(0, "DATA_CONCLUSAO_REAL <> ?", "AND");
			}				
		} catch (Exception e) {				
		}
	}

	public void lerColunas(BancoDeDados bd) throws Exception {
		this.getControle().setId(bd.getRs().getInt("ID"));
		this.setTitulo(bd.getRs().getString("TITULO"));
		this.setDataConclusaoPrevista(bd.getRs().getLong("DATA_CONCLUSAO_PREVISTA"), bd.getRs().getBoolean(("TAREFA_CONTINUA")));
		this.setDescricao(bd.getRs().getString("DESCRICAO"));
		this.setDataConclusaoReal(bd.getRs().getLong("DATA_CONCLUSAO_REAL"));
		
		EmpreendimentoDAO empreendimentoDao = new EmpreendimentoDAO(bd.getRs().getInt("EMPREENDIMENTO"));
		this.getProxy().carregar(empreendimentoDao);
		this.setEmpreendimento(empreendimentoDao);				
		
		ProjetoDAO projetoDao = new ProjetoDAO(bd.getRs().getInt("PROJETO"));
		this.getProxy().carregar(projetoDao);
		this.setProjeto(projetoDao);
		
		CategoriaDAO categoriaDao = new CategoriaDAO(bd.getRs().getInt("CATEGORIA"));	
		this.getProxy().carregar(categoriaDao);
		this.setCategoria(categoriaDao);
		
		ContaDAO responsavelDao = new ContaDAO(bd.getRs().getInt("RESPONSAVEL"));
		this.getProxy().carregar(responsavelDao);
		this.setResponsavel(responsavelDao);		
		
		this.getControle().lerControle(bd);
	}

	public String sentencaInserir() {
		return  "INSERT INTO DEMANDA " +
			"(TITULO, "+
			"EMPREENDIMENTO, " +
			"PROJETO, " +
			"CATEGORIA, " +
			"RESPONSAVEL, " +
			"DATA_CONCLUSAO_PREVISTA, " +
			"TAREFA_CONTINUA, " +
			"DATA_CONCLUSAO_REAL, " +
			"DESCRICAO, " +
			"CRIADOR, " +
			"DATA_CRIACAO, " +
			"REVISOR, " +
			"DATA_REVISAO) " +
			"VALUES " +
			"(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
	}
	
	public String sentencaRemover() {
		return "DELETE FROM DEMANDA " +
		"WHERE ID = ?";
	}

		public String sentencaAtualizar() {
			return "UPDATE DEMANDA SET " +	
		"TITULO = ?,  "+
		"EMPREENDIMENTO = ?,  " +
		"PROJETO = ?,  " +
		"CATEGORIA = ?,  " +
		"RESPONSAVEL = ?,  " +
		"DATA_CONCLUSAO_PREVISTA = ?,  " +
		"TAREFA_CONTINUA = ?,  " +
		"DATA_CONCLUSAO_REAL = ?,  " +
		"DESCRICAO = ?,  " +
		"REVISOR = ?,  " +
		"DATA_REVISAO = ? " +
		"WHERE " +
		"ID = ? ";
	}
	
	public String sentencaSelecionar() {
					return "SELECT " +
			"ID, " +
			"TITULO, " +
			"EMPREENDIMENTO, " +
			"PROJETO, " +
			"CATEGORIA, " +
			"RESPONSAVEL, " +
			"DATA_CONCLUSAO_PREVISTA, " +
			"TAREFA_CONTINUA, " +
			"DATA_CONCLUSAO_REAL, " +
			"DESCRICAO, " +
			"CRIADOR, " +
			"REVISOR, " +
			"DATA_CRIACAO, " +
			"DATA_REVISAO " +
			"FROM  DEMANDA ";
	}

}
