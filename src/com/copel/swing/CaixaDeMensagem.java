package com.copel.swing;

import java.awt.Toolkit;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.copel.Mensagem;
import com.copel.sgd.fachada.JanelaPrincipal;

public class CaixaDeMensagem {
	
	public String retorno;

	public CaixaDeMensagem() {
	}
	
	public String popUpCaixaDeSelecao(String titulo, Vector<String[]> listaDeValores, final Object obj) {	
	    final JFrame frame = new JFrame();
	    frame.setIconImage(Toolkit.getDefaultToolkit().getImage(JanelaPrincipal.class.getResource("/com/copel/sgd/fachada/icon.gif")));
	    
		Object[] lista = new String[listaDeValores.size()];
	    for (int i = 0; i<listaDeValores.size();i++){
	    	lista[i] = listaDeValores.get(i)[1];
	    }
		String saida = (String)JOptionPane.showInputDialog(
            frame,
            "Favor selecionar:\n",
            titulo,
            JOptionPane.PLAIN_MESSAGE,
            null,
            lista,
            null);		
		return saida;
	 }

	public void confirmarAcao() throws Exception {
		if (JOptionPane.showConfirmDialog(null, "Confirma a realização da ação?", "Confirmar ação", JOptionPane.YES_NO_OPTION)==1){
			throw new Exception("Ação cancelada!");
		}
	}

	public void alerta(String mensagem){
		JOptionPane.showMessageDialog(null, mensagem, "Atençao!", JOptionPane.ERROR_MESSAGE);
	}

	public void alerta(String mensagem, Exception e) {
		if (e.getMessage() == null) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			try {
				e.printStackTrace(pw);
				pw.close();
				sw.close();
				alerta(mensagem + sw.toString());
			} catch (IOException e1) {
				alerta(mensagem + Mensagem.erroMostrarErro);
			}
		} else {
			alerta(mensagem + e.getMessage());
		}
	}

	public void alerta(Exception e) {
		alerta(e.getMessage());
	}

	public void mensagem(String mensagem){
		JOptionPane.showMessageDialog(null, mensagem, "Informação...", JOptionPane.INFORMATION_MESSAGE);
	}

}
