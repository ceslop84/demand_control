package com.copel.sgd;

import com.copel.dao.Controle;
import com.copel.Ferramentas;


public class Demanda{
	private Empreendimento empreendimento;
	private Projeto projeto;
	private Categoria categoria;
	private Conta responsavel;
	private long dataConclusaoPrevista = 0;
	private long dataConclusaoReal = 0;
	private boolean tarefaContinua;
	private String titulo = "";
	private String descricao;
	private Controle controle = new Controle();
	
	public String getObjeto(){
		return "demanda";
	}
	
	public Demanda(){		
	}
	
	public Demanda(String id, String titulo, String empreendimento, String projeto, String categoria, String responsavel, long dataConclusaoPrevista, String tarefaContinua, String descricao, long dataConclusaoReal) throws Exception{		
		this.getControle().setId(id);
		this.setTitulo(titulo);
		this.setEmpreendimento(empreendimento);
		this.setCategoria(categoria);
		this.setProjeto(projeto);
		this.setResponsavel(responsavel);
		this.setDataConclusaoPrevista(dataConclusaoPrevista, tarefaContinua);
		this.setDataConclusaoReal(dataConclusaoReal);
		this.setDescricao(descricao);
	}

	public Demanda(String campo) throws Exception {
		try {
			this.getControle().setId(campo);
		} catch (Exception e) {
			this.setTitulo(campo);
		}
	}
	
	public Controle getControle() {
		return controle;
	}

	public void setControle(Controle controle) {
		this.controle = controle;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Conta getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Conta responsavel) {
		this.responsavel = responsavel;
	}
	
	public void setDataConclusaoPrevista(String dataConclusaoPrevista) throws Exception {
		this.dataConclusaoPrevista = Ferramentas.verificarData("Data de conclusão prevista", dataConclusaoPrevista);
	}
	
	public void setDataConclusaoPrevista(long dataConclusaoPrevista, boolean tarefaContinua) throws Exception {
		this.setTarefaContinua(tarefaContinua);
		if (tarefaContinua){
			this.dataConclusaoPrevista = 0;
		}else{
			this.setDataConclusaoPrevista(dataConclusaoPrevista);
		}
	}
	
	public void setDataConclusaoPrevista(long dataConclusaoPrevista, String tarefaContinua) throws Exception {
		boolean valor = Ferramentas.verificarBoolean("Tarefa contínua?", tarefaContinua);
		setDataConclusaoPrevista(dataConclusaoPrevista, valor);
	}
	
	public long getDataConclusaoPrevista(){
		return dataConclusaoPrevista;
	}

	public void setDataConclusaoPrevista(long dataConclusaoPrevista) {
		this.dataConclusaoPrevista = dataConclusaoPrevista;
	}
	
	public long getDataConclusaoReal() {
		return dataConclusaoReal;
	}

	public void setDataConclusaoReal(long dataConclusaoReal) {
		this.dataConclusaoReal = dataConclusaoReal;
	}
	
	public void encerrar() throws Exception{
		this.setDataConclusaoReal(Ferramentas.horaAgoraDataDeHojeBancoDeDados());
	}
	
	public void reabrir() throws Exception {
		this.setDataConclusaoReal(0);		
	}

	public boolean isTarefaContinua() {
		return tarefaContinua;
	}
	
	public void setTarefaContinua(String tarefaContinua) throws Exception {
		boolean valor = Ferramentas.verificarBoolean("Tarefa contínua", tarefaContinua);	
		this.setTarefaContinua(valor);
	}
	
	public void setTarefaContinua(boolean tarefaContinua) throws Exception {
		this.tarefaContinua = tarefaContinua;	
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) throws Exception {
		this.titulo = Ferramentas.verificarTexto("Título", titulo);
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) throws Exception {
		this.descricao = Ferramentas.verificarTexto("Descrição", descricao);
	}
		

	public Empreendimento getEmpreendimento() {
		return empreendimento;
	}

	public void setEmpreendimento(Empreendimento empreendimento) {
		this.empreendimento = empreendimento;
	}
	
	private void setEmpreendimento(String empreendimento) throws Exception {
		String ok = Ferramentas.verificarTexto("Empreendimento", empreendimento);		
		this.setEmpreendimento(new Empreendimento(ok));
	}
	
	private void setProjeto(String projeto) throws Exception {
		String ok = Ferramentas.verificarTexto("Projeto", projeto);		
		this.setProjeto(new Projeto(ok));
	}
	
	private void setCategoria(String categoria) throws Exception {
		String ok = Ferramentas.verificarTexto("Categoria", categoria);		
		this.setCategoria(new Categoria(ok));
	}
	
	private void setResponsavel(String responsavel) throws Exception {
		String ok = Ferramentas.verificarTexto("Responsável", responsavel);		
		this.setResponsavel(new Conta(ok));
	}

	public String[] toArray() throws Exception{
		String[] elemento = new String[10];
		elemento[0] = this.getTitulo();
		elemento[1] = this.getEmpreendimento().getTipo().getDescricao();
		elemento[2] = this.getEmpreendimento().getDescricao();
		elemento[3] = this.getProjeto().getDescricao();
		elemento[4] = this.getCategoria().getDescricao();
		elemento[5] = this.getResponsavel().getNome();
		elemento[6] = Ferramentas.exibirData(this.getDataConclusaoPrevista());
		elemento[7] = Ferramentas.converterBooleanSimNao(this.isTarefaContinua());
		elemento[8] = this.getDescricao();
		elemento[9] = Ferramentas.exibirData(this.getDataConclusaoReal());
		return this.controle.toArray(elemento);
	}

	public String toString() {
		try {
			return Ferramentas.deArrayParaString(this.toArray());
		} catch (Exception e) {
			return null;
		}
	}
	
	public boolean direitoEdicao(Conta conta) throws Exception{
		String usuario = conta.getRegistro();
		String criador = this.getControle().getCriador().getRegistro();
		if (usuario.equals(criador)|conta.isAdministrador()){
			return true;
		}else{
			return false;
		}
	}
	
	public void copiar(Demanda original) throws Exception {
		this.descricao = original.descricao;
		this.titulo = original.titulo;	
		this.dataConclusaoPrevista = original.dataConclusaoPrevista;
		this.empreendimento = original.empreendimento;
		this.categoria = original.categoria;
		this.projeto = original.projeto;
		this.responsavel = original.responsavel;
		this.tarefaContinua = original.tarefaContinua;
		this.dataConclusaoReal = original.dataConclusaoReal;
		this.controle = (Controle) original.controle.clonar();
	}
	
	public Object clonar() throws Exception {
		Demanda clone = new Demanda();
		clone.copiar(this);
		return clone;
	}	
	
	public String calcularTrabalholId(){
		return String.valueOf(this.getControle().getId()) + String.valueOf(this.getResponsavel().getControle().getId());
	}
}
