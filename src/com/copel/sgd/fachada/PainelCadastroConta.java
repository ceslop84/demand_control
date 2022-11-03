package com.copel.sgd.fachada;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.copel.Ferramentas;
import com.copel.sgd.bo.ContaBO;
import com.copel.swing.BarraDeNotificacoes;
import com.copel.swing.CaixaDeMensagem;
import com.copel.swing.CaixaDeSelecao;
import com.copel.swing.Funcoes;

public class PainelCadastroConta extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -808259226826472582L;
	Toolkit toolkit = Toolkit.getDefaultToolkit();  
	int largura = toolkit.getScreenSize().width -25;
	int altura = toolkit.getScreenSize().height - 100;
	
	JScrollPane painelRolante = new JScrollPane();
	JPanel painelPrincipal = new JPanel();
	JPanel areaDeTrabalho = new JPanel();
	JPanel barraDeNotificacoes = new BarraDeNotificacoes();
	
	ContaBO contaBo = new ContaBO();
	
	private JTextField textId = new JTextField();
	private CaixaDeSelecao cbNome = new CaixaDeSelecao();
	private JTextField textRegistro = new JTextField();
	private JTextField textCriador = new JTextField();
	private JTextField textDataCriacao = new JTextField();
	private JTextField textRevisor = new JTextField();
	private JTextField textDataRevisao = new JTextField();
	JCheckBox chbxAdministrador = new JCheckBox("Administrador?");
	Object [] listaDeCampos = {textId, cbNome, textRegistro, chbxAdministrador, textCriador, textDataCriacao, textRevisor, textDataRevisao};

	public PainelCadastroConta() {	
		super();
		
		BarraDeNotificacoes.atualizar("Cadastro de conta.");
		
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
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(12, 37, 75, 14);
		areaDeTrabalho.add(lblNome);
		
		cbNome.setEditable(true);
		cbNome.setBounds(105, 37, 400, 20);
		try {
			cbNome.carregar(Ferramentas.deClasseParaArray(contaBo.carregarTodos()));
		} catch (Exception e) {
			BarraDeNotificacoes.atualizar(e);
		}	
		areaDeTrabalho.add(cbNome);
		
		JLabel lblRegistro = new JLabel("Registro:");
		lblRegistro.setBounds(12, 68, 75, 14);
		areaDeTrabalho.add(lblRegistro);
		
		textRegistro.setColumns(10);
		textRegistro.setBounds(105, 68, 100, 20);
		areaDeTrabalho.add(textRegistro);
		
		JLabel lblId = new JLabel("Id:");
		lblId.setBounds(12, 5, 75, 14);
		areaDeTrabalho.add(lblId);
		
		textId.setEditable(false);
		textId.setColumns(10);
		textId.setBounds(105, 5, 100, 20);
		areaDeTrabalho.add(textId);
		
		JLabel lblCriador = new JLabel("Criado por:");
		lblCriador.setBounds(12, 101, 75, 14);
		areaDeTrabalho.add(lblCriador);
		
		textCriador.setEditable(false);
		textCriador.setColumns(10);
		textCriador.setBounds(105, 101, 100, 20);
		areaDeTrabalho.add(textCriador);
		
		JLabel lblDataCriacao = new JLabel("em:");
		lblDataCriacao.setBounds(221, 101, 75, 14);
		areaDeTrabalho.add(lblDataCriacao);
		
		textDataCriacao.setEditable(false);
		textDataCriacao.setColumns(10);
		textDataCriacao.setBounds(257, 101, 100, 20);
		areaDeTrabalho.add(textDataCriacao);
		
		JLabel lblRevisor = new JLabel("Revisado por:");
		lblRevisor.setBounds(12, 133, 91, 14);
		areaDeTrabalho.add(lblRevisor);
		
		textRevisor.setEditable(false);
		textRevisor.setColumns(10);
		textRevisor.setBounds(105, 133, 100, 20);
		areaDeTrabalho.add(textRevisor);
		
		JLabel lblDataRevisao = new JLabel("em:");
		lblDataRevisao.setBounds(221, 133, 75, 14);
		areaDeTrabalho.add(lblDataRevisao);
		
		textDataRevisao.setEditable(false);
		textDataRevisao.setColumns(10);
		textDataRevisao.setBounds(257, 133, 100, 20);
		areaDeTrabalho.add(textDataRevisao);
		chbxAdministrador.setHorizontalAlignment(SwingConstants.TRAILING);
		
		chbxAdministrador.setBounds(12, 167, 97, 23);
		areaDeTrabalho.add(chbxAdministrador);
		
		JPanel barraDeBotoes = new JPanel();
		painelPrincipal.add(barraDeBotoes, BorderLayout.SOUTH);
		
		JButton btnConsultar = new JButton("Consultar");
		barraDeBotoes.add(btnConsultar);
		
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					contaBo.carregar(cbNome);
					Funcoes.atualizarCampos(listaDeCampos, contaBo.toArray());
				} catch (Exception e) {
					BarraDeNotificacoes.atualizar(e);
				}
			}
		});
		
		JButton btnSalvar = new JButton("Salvar");
		barraDeBotoes.add(btnSalvar);
		
		JButton btnRemover = new JButton("Remover");
		barraDeBotoes.add(btnRemover);
		
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {	
					new CaixaDeMensagem().confirmarAcao();
					BarraDeNotificacoes.atualizar(contaBo.remover(textId));
					Funcoes.atualizarCampos(listaDeCampos, null);
				} catch (Exception e) {
					BarraDeNotificacoes.atualizar(e);
				}  
			}
		});
		
		JButton btnCancelar = new JButton("Cancelar");
		barraDeBotoes.add(btnCancelar);
		
		JButton btnLimpar = new JButton("Limpar");
		barraDeBotoes.add(btnLimpar);
		
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
					contaBo.carregar(textId);
					Funcoes.atualizarCampos(listaDeCampos, contaBo.toArray());
				} catch (Exception e) {
					BarraDeNotificacoes.atualizar(e);
				}
			}
		});
		
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {	
					new CaixaDeMensagem().confirmarAcao();
					BarraDeNotificacoes.atualizar(contaBo.salvar(listaDeCampos));
					cbNome.carregar(Ferramentas.deClasseParaArray(contaBo.carregarTodos()));
					Funcoes.atualizarCampos(listaDeCampos, null);
				} catch (Exception e) {
					BarraDeNotificacoes.atualizar(e);
				}  
			}
		});
		
		cbNome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					cbNome.recarregar(Ferramentas.deClasseParaArray(contaBo.carregarTodos()));
				} catch (Exception e) {
					BarraDeNotificacoes.atualizar(e);
				}	
			}
		});
	}
}
