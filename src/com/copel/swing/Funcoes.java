package com.copel.swing;

import java.awt.Container;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.copel.Ferramentas;
import com.copel.Mensagem;
import com.toedter.calendar.JDateChooser;

public class Funcoes {
		
	public static void carregarTabela(DefaultTableModel modelo, Vector<String[]> lista) {
		for (int i = 0; i < lista.size(); i++) {
			String[] elemento = lista.get(i);
		    modelo.addRow(elemento);
		}		
	}
	
	public static void filtrar(boolean isVisivel, JMenu menu, JMenuItem menuItem) {
		if (isVisivel){
			menu.add(menuItem);	
		}
	}
	
	public static void atualizarPainel(Container container, JPanel painel, String alinhamento){
		container.removeAll();
		container.add(painel, alinhamento);
		container.validate();
	}
	
	public static void atualizarCampos(Object[] listaDeCampos, String [] valorDosCampos) throws Exception{
		for (int i=0;i<listaDeCampos.length;i++){
			if (valorDosCampos!=null){
				Funcoes.setTexto(listaDeCampos[i], valorDosCampos[i]);
			}else{
				Funcoes.setTexto(listaDeCampos[i], null);
			}
		}
	}
		
	public static void atualizarCampos(Object[] listaDeCampos, String [] valorDosCampos, Object[] listaDeCamposManter) throws Exception{
		String [] texto = new String[listaDeCamposManter.length];
		for (int i=0;i<listaDeCamposManter.length;i++){
			texto[i] = Funcoes.getTexto(listaDeCamposManter[i]);
		}
		atualizarCampos(listaDeCampos, valorDosCampos);
		for (int i=0;i<listaDeCamposManter.length;i++){
			Funcoes.setTexto(listaDeCamposManter[i], texto[i]);
		}
	}
	
	public static void atualizarCampos(Object[] listaDeCampos, String [] valorDosCampos, Object campoManter) throws Exception{
		Object [] listaDeCamposManter = {campoManter};
		atualizarCampos(listaDeCampos, valorDosCampos, listaDeCamposManter);
	}
	
	public static void ativarCampos(Object[] listaDeCampos, boolean valor) {
		for (int i = 0; i < listaDeCampos.length; i++) {
			if (listaDeCampos[i] instanceof JTextField) {
				((JTextField) listaDeCampos[i]).setEditable(valor);
			} else if (listaDeCampos[i] instanceof CaixaDeSelecao) {
				((CaixaDeSelecao) listaDeCampos[i]).setEnabled(valor);
			} else if (listaDeCampos[i] instanceof JCheckBox) {
				((JCheckBox) listaDeCampos[i]).setEnabled(valor);
			} else if (listaDeCampos[i] instanceof JDateChooser) {
				((JDateChooser) listaDeCampos[i]).setEnabled(valor);
			} else if (listaDeCampos[i] instanceof JTextArea) {
				((JTextArea) listaDeCampos[i]).setEditable(valor);
			} else if (listaDeCampos[i] instanceof JButton) {
				((JButton) listaDeCampos[i]).setEnabled(valor);
			}
		}
	}

	public static String getTexto(Object obj) throws Exception {
		try {
			if (obj instanceof JTextField){
				return ((JTextField)obj).getText();
			} else if (obj instanceof CaixaDeSelecao){
				return ((CaixaDeSelecao)obj).getSelectedItem().toString();
			} else if (obj instanceof String){
				return (String)obj;
			}else if(obj instanceof JCheckBox){
				if (((JCheckBox)obj).isSelected()){
					return "true";
				}else{
					return "false";
				}
			} else if (obj instanceof JDateChooser){
				try {
					return  String.valueOf(Ferramentas.converterDataHoraBancoDeDados(((JDateChooser)obj).getDate()));
				} catch (NullPointerException e) {
					return "0";
				}				
			}else if (obj instanceof JTextArea){
				return ((JTextArea)obj).getText();
			}else if (obj instanceof String){
				return (String) obj;
			}else{
				return "";
			}
		} catch (NullPointerException e) {
			return "";
		}
	}

	public static void setTexto(Object obj, String valor) throws Exception {
		String valorString = null;
		boolean valorBoolean = false;
		String valorStringSelecao = "";
		
		if (valor!=null){
			valorString = valor;
			if (valor.equals("true")|valor.equals("1")|valor.equals("Sim")){
				valorBoolean = true;
			}
			valorStringSelecao = valor;
		}
		
		try {
			if (obj instanceof JTextField){
				((JTextField)obj).setText(valorString);
			} else if (obj instanceof CaixaDeSelecao){
				((CaixaDeSelecao)obj).setSelectedItem(valorStringSelecao);
			}else if(obj instanceof JCheckBox){		
				((JCheckBox)obj).setSelected(valorBoolean);
			}else if (obj instanceof JDateChooser){
				try {
					((JDateChooser)obj).setDate(Ferramentas.converterDataHoraBancoDeDados(Ferramentas.verificarData("Data", valor)).toDate());
				} catch (Exception e) {
					((JDateChooser)obj).setCalendar(null);
				}
			}else if (obj instanceof JTextArea){
				((JTextArea)obj).setText(valorString);
			}
		} catch (NullPointerException e) {
			throw new Exception(Mensagem.erroObjetoNulo, null);
		}
	}
}