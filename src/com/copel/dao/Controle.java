package com.copel.dao;

import java.sql.SQLException;

import com.copel.Ferramentas;
import com.copel.sgd.Conta;
import com.copel.sgd.dao.ContaDAO;
import com.copel.sgd.fachada.SGD;


public class Controle{
	
	private Proxy proxy = new Proxy();
	private String campoCriador = "CRIADOR";
	private String campoDataCriacao = "DATA_CRIACAO";
	private String campoRevisor = "REVISOR";
	private String campoDataRevisao = "DATA_REVISAO"; 
	private int indexCriador;
	private int indexRevisor;
	private int indexDataCriacao;
	private int indexDataRevisao;	
	private Conta conta = SGD.contaBo;
	protected int id;
	protected long dataCriacao;
	protected long dataRevisao;
	protected Conta criador;
	protected Conta revisor;
	
	public String getObjeto() {
		return "controle";
	}
		
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public void setId(String id) throws Exception {
		this.setId(Ferramentas.verificarInteiro("ID", id));
	}
	
	public boolean existe() {
		if (this.getId()>0){
			return true;
		}else{
			return false;
		}
	}
	
	public long getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(long dataCriacao) {
		this.dataCriacao = dataCriacao;
	}	

	public long getDataRevisao() {
		return dataRevisao;
	}

	public void setDataRevisao(long dataRevisao) {
		this.dataRevisao = dataRevisao;
	}
	
	public Conta getCriador() throws Exception{
		//Este método deve jogar uma exceção devido ao fato de que nos elementos DAO "Carregar Similares" é emrpegado desta forma.
		if (criador==null|criador.equals(null)){
			throw new Exception("Campo Criador com preenchimento inválido. Campo de preenchimento obrigatório.", null); 
		}else{
			return criador;
		}	
	}

	public void setCriador(Conta criador) {
		this.criador = criador;
	}

	public Conta getRevisor() throws Exception{
		//Este método deve jogar uma exceção devido ao fato de que nos elementos DAO "Carregar Similares" é emrpegado desta forma.
		if (revisor==null|revisor.equals(null)){
			throw new Exception("Campo Revisor com preenchimento inválido. Campo de preenchimento obrigatório.", null); 
		}else{
			return revisor;
		}
	}

	public void setRevisor(Conta revisor) {
		this.revisor = revisor;
	}
	
	public String[] toArray(String[] obj){
		
		String[] elemento = new String[5+obj.length];
		elemento[0] = Integer.toString(this.getId());
		int i;
		for (i=0;obj.length>i;i++){
			elemento[i+1] = obj[i];
		}
		try {
			elemento[i+1] = this.getCriador().getNome();
			elemento[i+2] = Ferramentas.exibirDataHora(this.getDataCriacao());
			elemento[i+3] = this.getRevisor().getNome();
			elemento[i+4] = Ferramentas.exibirDataHora(this.getDataRevisao());
		} catch (Exception e) {
		}
		return elemento;
	}	

	public void lerControle(BancoDeDados bd) throws Exception {
		
		ContaDAO contaDao;			
		contaDao = new ContaDAO(bd.getRs().getString(campoCriador));
		//Esse método deve ser usado assim para evitar loop infinito ao ler os controles do objeto Conta.
		proxy.carregarSemControles(contaDao);
		setCriador(contaDao);	
		
		contaDao = new ContaDAO(bd.getRs().getString(campoRevisor));
		proxy.carregarSemControles(contaDao);
		setRevisor(contaDao);	
		
		setDataCriacao(bd.getRs().getLong(campoDataCriacao));
		setDataRevisao(bd.getRs().getLong(campoDataRevisao));		
	}
	
	public void registrarCriacao (BancoDeDados bd) throws Exception {
		this.determinarIndices(bd);
		bd.getSql().setInt(indexCriador,conta.getControle().getId());
		bd.getSql().setLong(indexDataCriacao,Ferramentas.horaAgoraDataDeHojeBancoDeDados());
		bd.getSql().setInt(indexRevisor,conta.getControle().getId());
		bd.getSql().setLong(indexDataRevisao,Ferramentas.horaAgoraDataDeHojeBancoDeDados());		
	}

	public void registrarRevisao(BancoDeDados bd) throws Exception {		
		this.determinarIndices(bd);
		bd.getSql().setInt(indexRevisor,conta.getControle().getId());
		bd.getSql().setLong(indexDataRevisao,Ferramentas.horaAgoraDataDeHojeBancoDeDados());			
	}
	
	private void determinarIndices(BancoDeDados bd) throws SQLException{
		String separador = "[,]+";
		String[] campos = bd.getSentenca().split(separador);
		for (int i = 0; i < campos.length; i++){
			String teste = campos[i].replaceAll("[() ]","");
		    if (teste.contains(campoCriador)){
		    	indexCriador = i+1;
		    }else if (teste.contains( campoRevisor)){
		    	indexRevisor = i+1;
		    }else if (teste.contains(campoDataCriacao)){
		    	indexDataCriacao = i+1;
		    }else if (teste.contains(campoDataRevisao)){
		    	indexDataRevisao = i+1;
		    }
		}
	}
	
	public void copiar(Controle original){
		this.campoCriador = original.campoCriador;
		this.campoDataCriacao = original.campoDataCriacao;
		this.campoRevisor = original.campoRevisor;
		this.campoDataRevisao = original.campoDataRevisao;
		this.indexCriador = original.indexCriador;
		this.indexRevisor = original.indexRevisor;
		this.indexDataCriacao = original.indexDataCriacao;
		this.indexDataRevisao = original.indexDataRevisao;	
		this.id = original.id;
		this.dataCriacao = original.dataCriacao;
		this.dataRevisao = original.dataRevisao;
		try {
			this.criador = (Conta) original.criador.clonar();
			this.revisor = (Conta) original.revisor.clonar();
		} catch (Exception e) {
			this.criador = null;
			this.revisor = null;
		}	
	}

	public Object clonar(){
		Controle clone = new Controle();
		clone.copiar(this);		
		return clone;
	}
}
