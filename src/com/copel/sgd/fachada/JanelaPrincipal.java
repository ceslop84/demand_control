package com.copel.sgd.fachada;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.copel.Mensagem;
import com.copel.swing.BarraDeNotificacoes;
import com.copel.swing.CaixaDeMensagem;
import com.copel.swing.Funcoes;

public class JanelaPrincipal extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4541999239244656222L;
	public Toolkit toolkit = Toolkit.getDefaultToolkit();  
	public int largura = toolkit.getScreenSize().width -25;
	public int altura = toolkit.getScreenSize().height - 100;
	
	public JScrollPane painelRolante = new JScrollPane();
	public JPanel painelPrincipal = new JPanel();
	public JPanel barraDeNotificacoes = new BarraDeNotificacoes();
	public JPanel areaDeTrabalho = new JPanel();
						
	public JanelaPrincipal() {		
		super();
		setIconImage(Toolkit.getDefaultToolkit().getImage(JanelaPrincipal.class.getResource("/com/copel/sgd/fachada/icon.gif")));
		
		setPreferredSize(new Dimension(largura, altura));
		getContentPane().setLayout(new BorderLayout(0, 0));
		getContentPane().add(painelRolante, BorderLayout.CENTER);
		setBounds(10, 10, largura, altura);
		
		painelRolante.setPreferredSize(new Dimension(largura, altura));
		painelRolante.setViewportView(painelPrincipal);
		
		painelPrincipal.setPreferredSize(new Dimension(largura, altura));
		painelPrincipal.setLayout(new BorderLayout(0, 0));
		
		painelPrincipal.add(areaDeTrabalho, BorderLayout.CENTER);
		areaDeTrabalho.setLayout(null);
		
		painelPrincipal.add(barraDeNotificacoes, BorderLayout.NORTH);
				
		setTitle(Mensagem.nomeAplicacao);
			
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnArquivo = new JMenu("Arquivo");
		menuBar.add(mnArquivo);
		
		JMenuItem mntmSair = new JMenuItem("Sair");	
		mnArquivo.add(mntmSair);
		
		JMenu mnCadastro = new JMenu("Cadastro");
		menuBar.add(mnCadastro);
		
		JMenuItem mntmConta = new JMenuItem("Conta");	
		Funcoes.filtrar(SGD.contaBo.isAdministrador(), mnCadastro, mntmConta);
		
		JMenuItem mntmTipoEmpreendimento = new JMenuItem("Tipo de Empreendimento");	
		Funcoes.filtrar(SGD.contaBo.isAdministrador(), mnCadastro, mntmTipoEmpreendimento);
		
		JMenuItem mntmEmpreendimento = new JMenuItem("Empreendimento");	
		Funcoes.filtrar(SGD.contaBo.isAdministrador(), mnCadastro, mntmEmpreendimento);
		
		JMenuItem mntmCategoria = new JMenuItem("Categoria");
		Funcoes.filtrar(SGD.contaBo.isAdministrador(), mnCadastro, mntmCategoria);
		
		JMenuItem mntmProjeto = new JMenuItem("Projeto");
		Funcoes.filtrar(SGD.contaBo.isAdministrador(), mnCadastro, mntmProjeto);
		
		JMenuItem mntmDemanda = new JMenuItem("Demanda");
		
		mnCadastro.add(mntmDemanda);
		
		JMenu mnConsulta = new JMenu("Consulta");
		menuBar.add(mnConsulta);
		
		JMenu mnDemandas = new JMenu("Demandas");
		mnConsulta.add(mnDemandas);
		
		JMenuItem mntmMinhasDemandas = new JMenuItem("Minhas Demandas");
		mnDemandas.add(mntmMinhasDemandas);
		
		JMenuItem mntmDemandasDelegadas = new JMenuItem("Demandas Delegadas");
		mnDemandas.add(mntmDemandasDelegadas);		
		
		JMenuItem mntmTodasDemandas = new JMenuItem("Todas as Demandas");
		Funcoes.filtrar(SGD.contaBo.isAdministrador(), mnDemandas, mntmTodasDemandas);
		
		JMenu mnDemandasHistorico = new JMenu("Hist\u00F3rico");
		mnDemandas.add(mnDemandasHistorico);
		
		JMenuItem mntmMinhasDemandasHistorico = new JMenuItem("Minhas Demandas");
		mnDemandasHistorico.add(mntmMinhasDemandasHistorico);
		
		JMenuItem mntmDemandasDelegadasHistorico = new JMenuItem("Demandas Delegadas");
		mnDemandasHistorico.add(mntmDemandasDelegadasHistorico);
		
		JMenuItem mntmTodasDemandasHistorico = new JMenuItem("Todas as Demandas");
		Funcoes.filtrar(SGD.contaBo.isAdministrador(), mnDemandasHistorico, mntmTodasDemandasHistorico);
		
		
		mntmMinhasDemandasHistorico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				PainelDemanda pd = new PainelDemanda();
				pd.minhasDemandasHistorico();
				Funcoes.atualizarPainel(getContentPane(), pd, BorderLayout.CENTER);
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		
		mntmDemandasDelegadasHistorico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				PainelDemanda pd = new PainelDemanda();
				pd.demandasDelegadasHistorico();				
				Funcoes.atualizarPainel(getContentPane(), pd, BorderLayout.CENTER);
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});	
		
		mntmDemandasDelegadas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				PainelDemanda pd = new PainelDemanda();
				pd.demandasDelegadas();
				Funcoes.atualizarPainel(getContentPane(), pd, BorderLayout.CENTER);
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});		
		
		mntmMinhasDemandas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				PainelDemanda pd = new PainelDemanda();
				pd.minhasDemandas();
				Funcoes.atualizarPainel(getContentPane(), pd, BorderLayout.CENTER);
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		
		mntmTodasDemandas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				PainelDemanda pd = new PainelDemanda();
				pd.todasDemandas();
				Funcoes.atualizarPainel(getContentPane(), pd, BorderLayout.CENTER);
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		
		mntmTodasDemandasHistorico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				PainelDemanda pd = new PainelDemanda();
				pd.todasDemandasHistorico();
				Funcoes.atualizarPainel(getContentPane(), pd, BorderLayout.CENTER);
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		
//		JMenu mnAnalise = new JMenu("An\u00E1lise");
//		menuBar.add(mnAnalise);
		
	
//		JMenuItem mntmAnaliseDemandas = new JMenuItem("Demandas");
//		mnAnalise.add(mntmAnaliseDemandas);
		
		JMenu mnAjuda = new JMenu("Ajuda");
		menuBar.add(mnAjuda);
		
		JMenuItem mntmSuporte = new JMenuItem("Suporte técnico");
		mntmSuporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new CaixaDeMensagem().mensagem(Mensagem.suporte);
			}
		});
		mnAjuda.add(mntmSuporte);
		
		JMenuItem mntmSobre = new JMenuItem("Sobre o " + Mensagem.acronimoAplicacao);
		mntmSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new CaixaDeMensagem().mensagem(Mensagem.sobre);
			}
		});
		mnAjuda.add(mntmSobre);
		
		JMenuItem mntmManual = new JMenuItem("Manual de Utilização");
		mntmManual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				File pdf = new File("S:\\DMC_Cartografia\\documentos\\Aplicativos\\sgd\\Manual de Utilização SGD.pdf");  
		        try {  
		            Desktop.getDesktop().open(pdf);  
		        } catch (Exception ex) {  
//		            ex.printStackTrace();  
//		            JOptionPane.showMessageDialog(null, ex,  "ERRO",JOptionPane.ERROR_MESSAGE);  
		  
		        }  
			}
		});
		mnAjuda.add(mntmManual);
		
		BarraDeNotificacoes.atualizar(SGD.contaBo.getNome() + ", bem vindo ao " + Mensagem.acronimoAplicacao + "!");

		mntmEmpreendimento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Funcoes.atualizarPainel(getContentPane(), new PainelCadastroEmpreendimento(), BorderLayout.CENTER);
			}
		});
		
//		mntmAnaliseTrabalhos.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				Funcoes.atualizarPainel(getContentPane(), new PainelAnalise(), BorderLayout.CENTER);
//			}
//		});
		
		mntmDemanda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Funcoes.atualizarPainel(getContentPane(), new PainelCadastroDemanda(), BorderLayout.CENTER);
			}
		});
		
		mntmTipoEmpreendimento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Funcoes.atualizarPainel(getContentPane(), new PainelCadastroTipoEmpreendimento(), BorderLayout.CENTER);
			}
		});
		
		mntmConta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Funcoes.atualizarPainel(getContentPane(), new PainelCadastroConta(), BorderLayout.CENTER);
			}
		});
		
		mntmCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Funcoes.atualizarPainel(getContentPane(), new PainelCadastroCategoria(), BorderLayout.CENTER);
			}
		});
		
		mntmProjeto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Funcoes.atualizarPainel(getContentPane(), new PainelCadastroProjeto(), BorderLayout.CENTER);
			}
		});
		
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new CaixaDeMensagem().confirmarAcao();
					System.exit(0);
				} catch (Exception e) {
					BarraDeNotificacoes.atualizar(e);
				}				
			}
		});
	}
}
