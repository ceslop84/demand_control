package com.copel.swing;

import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;

public class CaixaDeSelecao extends JComboBox<String>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7310286380010350084L;

	public CaixaDeSelecao() {
		super();
	}

	public CaixaDeSelecao(ComboBoxModel<String> aModel) {
		super(aModel);
	}

	public CaixaDeSelecao(String[] items) {
		super(items);
	}

	public CaixaDeSelecao(Vector<String> items) {
		super(items);
	}

	public void carregar(Vector<String[]> listaDeValores, int index){
		this.removeAllItems();
		this.addItem((String) "");
		try {
			for (int i=0;i<listaDeValores.size();i++){
				this.removeItem(listaDeValores.get(i)[index]);
				this.addItem(listaDeValores.get(i)[index]);
			}
		} catch (NullPointerException e) {
		}
		this.setSelectedItem("");
	}

	public void carregar(Vector<String[]> listaDeValores){
		this.carregar(listaDeValores, 1);
	}

	public void recarregar(Vector<String[]> listaDeValores){
		String itemSelecionado;
		try {
			itemSelecionado = (String) this.getSelectedItem().toString();
		} catch (NullPointerException e) {
			itemSelecionado = (String) "";
		}
		this.carregar(listaDeValores);
		this.setSelectedItem(itemSelecionado);
	}

	public void recarregar(Vector<String[]> listaDeValores, int index){
		String itemSelecionado;
		try {
			itemSelecionado = (String) this.getSelectedItem().toString();
		} catch (NullPointerException e) {
			itemSelecionado = (String) "";
		}
		carregar(listaDeValores, index);
		this.setSelectedItem(itemSelecionado);
	}

	public void recarregar(Vector<String[]> listaDeValores, int index, Object dependencia) throws Exception{
		String textoDependencia = (String) Funcoes.getTexto(dependencia);
		if (textoDependencia == ""){
			this.carregar(null);
		}else{
			String itemSelecionado;
			try {
				itemSelecionado = (String) this.getSelectedItem().toString();
			} catch (NullPointerException e) {
				itemSelecionado = (String) "";
			}
			carregar(listaDeValores, index);
			this.setSelectedItem(itemSelecionado);
		}	
	}

}
