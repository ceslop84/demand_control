package com.copel.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import org.sqlite.SQLiteConfig;

import com.copel.Configuracoes;
import com.copel.Log;
import com.copel.sgd.dao.ContaDAO;

public class BancoDeDados {
	
	private Log log = new Log();
	private Connection conexao = null;
	private PreparedStatement sql;
	private ResultSet rs;
	private int contador = 0;
	private Vector<String[]> condicao = new Vector<String[]>();	
	private String sentenca;

    public static final String DB_URL = "jdbc:sqlite:";  
    public static final String DRIVER = "org.sqlite.JDBC";  

	public BancoDeDados() throws Exception {
		try {
			// Carrega o drive JDBC.
			 Class.forName(DRIVER);
			 // Ler arquivo de properties com informações do banco de dados.  
		    Configuracoes config1 = new Configuracoes(); 
		    SQLiteConfig config2 = new SQLiteConfig(); 
		    config2.enforceForeignKeys(true); 
			// Conexão ao banco de dados.
			this.setConexao(DriverManager.getConnection(DB_URL + config1.getProperty("bancoDeDados"), config2.toProperties()));
		} catch (ClassNotFoundException e) {
			this.getLog().adicionarEJogarExcecao("!ERRO! - Falha ao carregar o driver JDBC. Driver NÃO carregado.", e);
		} catch (SQLException e) {
			this.getLog().adicionarEJogarExcecao("!ERRO! - Falha ao conectar com o banco de dados. Conexão NÃO estabelecida.", e);
		}
	}

	public Log getLog() {
		return log;
	}

	public void setLog(Log log) {
		this.log = log;
	}
		
	public PreparedStatement getSql() {
		return sql;
	}

	public void setSql(String sql) throws Exception {
		
		if (this.getCondicao().size()>0){
			String condicao = "";
			
			int i;
			for (i=0;i<this.getCondicao().size();){
				if (i==0){
					condicao = " WHERE " + this.getCondicao().get(i)[1] + " ";
				}else{
						try {
							condicao = condicao + this.getCondicao().get(i)[2]+ " " + this.getCondicao().get(i)[1] + " ";
						} catch (ArrayIndexOutOfBoundsException  e) {
							this.getLog().adicionarEJogarExcecao("!ERRO! - Operador lógico AND/OR ausente.", e);
						}
				}	
				i++;
			}
			sql = sql + condicao;
		}
		
		this.sql = this.getConexao().prepareStatement(sql);
		
		if (this.getCondicao().size()>0){
			for (int i=0;i<this.getCondicao().size();i++){
				this.getSql().setString(i+1,this.getCondicao().get(i)[0]);
			}
		}
		
		this.setSentenca(sql);
	}

	public Connection getConexao() {
		return conexao;
	}

	public void setConexao(Connection conexao) {
		this.conexao = conexao;
	}
	
	public ResultSet getRs() {
		return rs;
	}

	public void setRs(ResultSet rs) {
		this.rs = rs;
	}
	
	public int getContador() {
		return contador;
	}

	public void setContador(int contador) {
		this.contador = contador;
	}
	
	public void fechar() throws Exception {	
		try {
			this.getSql().close();
			this.getConexao().close();
		} catch (SQLException e) {
			this.getLog().adicionarEJogarExcecao("!ERRO! - Falha ao fechar a conexão com o banco de dados.", e);
		}
	}
	
	public void executar() throws Exception{
		try {
			if (this.getSql().execute()){
				this.rs = this.getSql().getResultSet();
			}else{
				this.setContador(this.getSql().getUpdateCount());
			}
		} catch (SQLException e) {
			this.getLog().adicionar("Falha na execução do comando:" + this.getSql() + ". Comando NÃO executado.");
			this.getLog().adicionarEJogarExcecao("Ação não executada, valores inconsistentes: " + e.getMessage(), e);
		}
	}
	
	public Vector<String[]> getCondicao() {
		return condicao;
	}

	public String getSentenca() {
		return sentenca;
	}

	public void setSentenca(String sentenca) {
		this.sentenca = sentenca;
	}

	public void addCondicao(String value, String condicao) {
		if (value!=""){
			this.getCondicao().add(new String[]{value, condicao});
		}
	}
	
	public void adicionarCondicao(int value, String condicao) {
		addCondicao(String.valueOf(value), condicao);
	}
	
	public void adicionarCondicao(String value, String condicao, String operador) {
		if (value!=""){
			this.getCondicao().add(new String[]{value, condicao, operador});
		}
	}
	
	public void adicionarCondicao(int value, String condicao, String operador) {
		this.adicionarCondicao(String.valueOf(value), condicao, operador);
	}
	
	public void adSicionarCondicao(String condicao) {
		this.getCondicao().add(new String[]{"", condicao});
	}
	
	public void remover(DAO dao) throws Exception{
		this.setSql(dao.sentencaRemover());
		dao.parametrosRemover(this);
	}

	public void atualizar(DAO dao) throws Exception {
		this.setSql(dao.sentencaAtualizar());
		dao.parametrosAtualizar(this);		
		dao.getControle().registrarRevisao(this);
	}

	public void inserir(DAO dao) throws Exception {
		this.setSql(dao.sentencaInserir());
		dao.parametrosInserir(this);	
		dao.getControle().registrarCriacao(this);
	}

	public void selecionar(DAO dao) throws Exception {		
		dao.parametrosSelecionar(this);
		this.setSql(dao.sentencaSelecionar());		
	}

	public void selecionarTodos(DAO dao) throws Exception {
		this.setSql(dao.sentencaSelecionar());		
	}

	public void selecionarSimilares(DAO dao) throws Exception {
		dao.parametrosSelecionarSimilares(this);
		this.setSql(dao.sentencaSelecionar());
	}

	public void carregar(DAO dao) throws Exception {
		//Ir para o primeiro elemento.
		this.getRs().next();
		dao.lerColunas(this);		
	}
	
	public Vector<Object> carregarVarios(DAO dao) throws Exception {
		Vector <Object> lista = new Vector<Object>();		
		while (this.getRs().next()) {
			dao.lerColunas(this);
			lista.add(dao.clonar());
		}	
		return lista;
	}

	public void carregarSemControle(ContaDAO contaDao) throws Exception {
		//Ir para o primeiro elemento.
		this.getRs().next();
		contaDao.lerColunasSemControle(this);	
		
	}
}
