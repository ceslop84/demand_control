package com.copel.swing;

import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JLabel;

import com.copel.Ferramentas;
import com.copel.Mensagem;

import java.awt.Font;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class BarraDeNotificacoes extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 97380552374122039L;
	static JLabel lblNotificacoes = new JLabel("");
	
	public BarraDeNotificacoes() {
		setLayout(new BorderLayout(0, 0));
		lblNotificacoes.setFont(new Font("Tahoma", Font.PLAIN, 10));
		add(lblNotificacoes, BorderLayout.CENTER);
	}

	public static void atualizar(String mensagem){
		lblNotificacoes.setText(mensagem + " " + Ferramentas.exibirDataHora(Ferramentas.horaAgoraDataDeHojeBancoDeDados()));
	}

	public static void atualizar(Exception e){
		if (e.getMessage()==null){
		    StringWriter sw = new StringWriter();
		    PrintWriter pw = new PrintWriter(sw);
		    try {
		    	e.printStackTrace(pw);
				pw.close();
				sw.close();
				atualizar(sw.toString());
			} catch (IOException e1) {
				atualizar(Mensagem.erroMostrarErro);
			}			
		}else{
			atualizar(e.getMessage());
		}
	}

}
