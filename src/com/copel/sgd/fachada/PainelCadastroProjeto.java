package com.copel.sgd.fachada;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.copel.Ferramentas;
import com.copel.sgd.bo.ProjetoBO;
import com.copel.swing.BarraDeNotificacoes;
import com.copel.swing.CaixaDeMensagem;
import com.copel.swing.CaixaDeSelecao;
import com.copel.swing.Funcoes;

public class PainelCadastroProjeto extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -420529452765829623L;
	Toolkit toolkit = Toolkit.getDefaultToolkit();  
	int largura = toolkit.getScreenSize().width -25;
	int altura = toolkit.getScreenSize().height - 100;
	
	JScrollPane painelRolante = new JScrollPane();
	JPanel painelPrincipal = new JPanel();
	JPanel areaDeTrabalho = new JPanel();
	JPanel barraDeNotificacoes = new BarraDeNotificacoes();
	JPanel barraDeBotoes = new JPanel();
	
	ProjetoBO projetoBo = new ProjetoBO();
	
	private JTextField textId = new JTextField();
	private CaixaDeSelecao cbDescricao = new CaixaDeSelecao();
	private JTextField textAcronimo = new JTextField();
	private JTextField textCriador = new JTextField();
	private JTextField textDataCriacao = new JTextField();
	private JTextField textRevisor = new JTextField();
	private JTextField textDataRevisao = new JTextField();
	Object [] listaDeCampos = {textId, cbDescricao, textAcronimo, textCriador, textDataCriacao, textRevisor, textDataRevisao};

	public PainelCadastroProjeto() {	
		super();
		
		BarraDeNotificacoes.atualizar("Cadastro de projeto.");
				
		setPreferredSize(new Dimension(largura, altura));
		setLayout(new BorderLayout(0, 0));
		add(painelRolante, BorderLayout.CENTER);
		
		painelRolante.setPreferredSize(new Dimension(largura, altura));
		painelRolante.setViewportView(painelPrincipal);
		
		painelPrincipal.setPreferredSize(new Dimension(largura, altura));
		painelPrincipal.setLayout(new BorderLayout(0, 0));	
		painelPrincipal.add(areaDeTrabalho, BorderLayout.CENTER);
		areaDeTrabalho.setLayout(null);
		
		painelPrincipal.add(barraDeNotificacoes, BorderLayout.NORTH);
		
		JLabel lblDescricao = new JLabel("Descri\u00E7\u00E3o");
		lblDescricao.setBounds(12, 37, 75, 14);
		areaDeTrabalho.add(lblDescricao);
		
		cbDescricao.setEditable(true);
		cbDescricao.setBounds(105, 37, 400, 20);
		try {
			cbDescricao.carregar(Ferramentas.deClasseParaArray(projetoBo.carregarTodos()));
		} catch (Exception e) {
			BarraDeNotificacoes.atualizar(e);
		}
		areaDeTrabalho.add(cbDescricao);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.setBounds(12, 202, 91, 23);
		barraDeBotoes.add(btnConsultar);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(115, 202, 91, 23);
		barraDeBotoes.add(btnSalvar);
		
		JButton btnRemover = new JButton("Remover");
		btnRemover.setBounds(218, 202, 91, 23);
		barraDeBotoes.add(btnRemover);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(321, 202, 91, 23);
		barraDeBotoes.add(btnCancelar);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setBounds(424, 202, 91, 23);
		barraDeBotoes.add(btnLimpar);
		try {
		} catch (Exception e) {
			BarraDeNotificacoes.atualizar(e);
		}
		
		JLabel lblAcronimo = new JLabel("Acr\u00F4nimo:");
		lblAcronimo.setBounds(12, 68, 75, 14);
		areaDeTrabalho.add(lblAcronimo);
		
		textAcronimo.setColumns(10);
		textAcronimo.setBounds(105, 68, 100, 20);
		areaDeTrabalho.add(textAcronimo);
		
		JLabel lblId = new JLabel("Id:");
		lblId.setBounds(12, 5, 75, 14);
		areaDeTrabalho.add(lblId);
		
		textId.setEditable(false);
		textId.setColumns(10);
		textId.setBounds(105, 5, 100, 20);
		areaDeTrabalho.add(textId);
			
		JLabel lblCriador = new JLabel("Criado por:");
		lblCriador.setBounds(12, 98, 75, 14);
		areaDeTrabalho.add(lblCriador);
		
		textCriador.setEditable(false);
		textCriador.setColumns(10);
		textCriador.setBounds(105, 98, 100, 20);
		areaDeTrabalho.add(textCriador);
		
		JLabel lblDataCriacao = new JLabel("em:");
		lblDataCriacao.setBounds(221, 98, 75, 14);
		areaDeTrabalho.add(lblDataCriacao);
		
		textDataCriacao.setEditable(false);
		textDataCriacao.setColumns(10);
		textDataCriacao.setBounds(257, 98, 100, 20);
		areaDeTrabalho.add(textDataCriacao);
		
		JLabel lblRevisor = new JLabel("Revisado por:");
		lblRevisor.setBounds(12, 130, 91, 14);
		areaDeTrabalho.add(lblRevisor);
		
		textRevisor.setEditable(false);
		textRevisor.setColumns(10);
		textRevisor.setBounds(105, 130, 100, 20);
		areaDeTrabalho.add(textRevisor);
		
		JLabel lblDataRevisao = new JLabel("em:");
		lblDataRevisao.setBounds(221, 130, 75, 14);
		areaDeTrabalho.add(lblDataRevisao);
		
		textDataRevisao.setEditable(false);
		textDataRevisao.setColumns(10);
		textDataRevisao.setBounds(257, 130, 100, 20);
		areaDeTrabalho.add(textDataRevisao);
				
		painelPrincipal.add(barraDeBotoes, BorderLayout.SOUTH);

		cbDescricao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					cbDescricao.recarregar(Ferramentas.deClasseParaArray(projetoBo.carregarTodos()));
				} catch (Exception e) {
					BarraDeNotificacoes.atualizar(e);
				}
			}
		});
		
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {	
					new CaixaDeMensagem().confirmarAcao();
					BarraDeNotificacoes.atualizar(projetoBo.salvar(listaDeCampos));
					Funcoes.atualizarCampos(listaDeCampos, null);
				} catch (Exception e) {
					BarraDeNotificacoes.atualizar(e);
				}  
			}
		});
		
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					projetoBo.carregar(cbDescricao);
					Funcoes.atualizarCampos(listaDeCampos, projetoBo.toArray());
				} catch (Exception e) {
					BarraDeNotificacoes.atualizar(e);
				}
			}
		});
		
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {	
					new CaixaDeMensagem().confirmarAcao();
					BarraDeNotificacoes.atualizar(projetoBo.remover(textId));
					Funcoes.atualizarCampos(listaDeCampos, null);
				} catch (Exception e) {
					BarraDeNotificacoes.atualizar(e);
				}  
			}
		});
		
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new CaixaDeMensagem().confirmarAcao();
					Funcoes.atualizarCampos(listaDeCampos, null);
				} catch (Exception e) {
					BarraDeNotificacoes.atualizar(e);
				}
			}
		});
		
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new CaixaDeMensagem().confirmarAcao();
					projetoBo.carregar(textId);
					Funcoes.atualizarCampos(listaDeCampos, projetoBo.toArray());
				} catch (Exception e) {
					BarraDeNotificacoes.atualizar(e);
				}
			}
		});
	}
}
