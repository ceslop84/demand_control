package com.copel.sgd;

import com.copel.dao.Controle;
import com.copel.Ferramentas;

public class Conta{

	private String nome = "";
	private String registro = "";
	private boolean administrador = false;
	private Controle controle = new Controle();
	
	public String getObjeto(){
		return "conta";
	}
	
	public Conta(String id, String nome, String registro, boolean administrador) throws Exception {
		this.getControle().setId(id);
		this.setNome(nome);
		this.setRegistro(registro);
		this.setAdministrador(administrador);
	}
	
	public Conta(String id, String nome, String registro, String administrador) throws Exception {
		this.getControle().setId(id);
		this.setNome(nome);
		this.setRegistro(registro);
		this.setAdministrador(administrador);
	}
	
	public Conta(String campo) throws Exception{
		super();
		try {
			this.getControle().setId(campo);
		} catch (Exception e1) {
			try {
				this.setRegistro(campo);
				
			}catch (Exception e2){
				this.setNome(campo);
			}
		}
	}
	
	public Conta(String registro, String nome) throws Exception{
		super();
		this.setRegistro(registro);
		this.setNome(nome);
	}
		
	public Conta(){		
	}

	public Conta(int id) {
		this.getControle().setId(id);
	}

	public Controle getControle() {
		return controle;
	}

	public void setControle(Controle controle) {
		this.controle = controle;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) throws Exception {
		this.nome = Ferramentas.verificarTexto("Nome", nome);
	}

	public String getRegistro() {
		return registro;
	}

	public void setRegistro(String registro) throws Exception {
		this.registro = registro.substring(0,2) + Ferramentas.verificarInteiro("Registro", registro.substring(2));
	}

	public boolean isAdministrador() {
		//A linha abaixo é usada para testar um usuário não-administrador.
		//return false;
		return administrador;
	}
	
	public String isAdministradorString() {
		if (this.administrador){
			return "1";
		}else{
			return "0";
		}
	}
	
	public void setAdministrador(boolean administrador) {
		this.administrador = administrador;
	}
	
	public void setAdministrador(String administrador) throws Exception {
		this.administrador = Ferramentas.verificarBoolean("Administrador", administrador);
	}
	
	public void adicionarAdministrador() {
		this.administrador = true;
	}
	
	public void removerAdministrador() {
		this.administrador = false;
	}
		
	public String toString() {
		try {
			return Ferramentas.deArrayParaString(this.toArray());
		} catch (Exception e) {
			return null;
		}
	}
	
	public String[] toArray() throws Exception {
		String[] elemento = new String[3];
		elemento[0] = this.getNome();
		elemento[1] = this.getRegistro();
		elemento[2] = this.isAdministradorString();
		return this.controle.toArray(elemento);
	}
	
	protected void copiar(Conta original){
		this.nome = original.nome;
		this.registro = original.registro;
		this.administrador = original.administrador;
		this.controle = (Controle) original.controle.clonar();
	}
	
	public Object clonar(){
		Conta clone = new Conta();
		clone.copiar(this);
		return clone;
	}
}
