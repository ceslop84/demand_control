package com.copel.dao;

public interface DAO{

	public String sentencaInserir();
	public String sentencaRemover();
	public String sentencaAtualizar();
	public String sentencaSelecionar();
		
	public void parametrosInserir(BancoDeDados bd) throws Exception;
	public void parametrosRemover(BancoDeDados bd) throws Exception;	
	public void parametrosAtualizar(BancoDeDados bd) throws Exception;
	public void parametrosSelecionar(BancoDeDados bd) throws Exception;
	public void parametrosSelecionarSimilares(BancoDeDados bd) throws Exception;
	
	public void lerColunas(BancoDeDados bd)  throws Exception;	
	public String[] toArray() throws Exception;
	public String getObjeto();	
	public Object clonar() throws Exception;
	public Controle getControle();
	public Proxy getProxy();
}
