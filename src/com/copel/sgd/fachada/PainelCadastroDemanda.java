package com.copel.sgd.fachada;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.copel.Ferramentas;
import com.copel.sgd.bo.CategoriaBO;
import com.copel.sgd.bo.DemandaBO;
import com.copel.sgd.bo.EmpreendimentoBO;
import com.copel.sgd.bo.ProjetoBO;
import com.copel.sgd.bo.TipoEmpreendimentoBO;
import com.copel.swing.BarraDeNotificacoes;
import com.copel.swing.CaixaDeMensagem;
import com.copel.swing.CaixaDeSelecao;
import com.copel.swing.Funcoes;
import com.toedter.calendar.JDateChooser;

public class PainelCadastroDemanda extends JPanel {  
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7583797755152697177L;
	public Toolkit toolkit = Toolkit.getDefaultToolkit();  
	public int largura = toolkit.getScreenSize().width -25;
	public int altura = toolkit.getScreenSize().height - 100;
	
	public JScrollPane painelRolante = new JScrollPane();
	public JPanel painelPrincipal = new JPanel();
	public JPanel areaDeTrabalho = new JPanel();
	public JPanel barraDeNotificacoes = new BarraDeNotificacoes();
	public JPanel barraDeBotoes = new JPanel();
	public JPanel areaCampos = new JPanel();
	
	public JButton btnConsultar = new JButton("Consultar");
	public JButton btnRemover = new JButton("Remover");	
	public JButton btnSalvar = new JButton("Salvar");
	public JButton btnCancelar = new JButton("Cancelar");
	public JButton btnLimpar = new JButton("Limpar");
	
	private CaixaDeSelecao cbEmpreendimento = new CaixaDeSelecao();
	private CaixaDeSelecao cbTipo = new CaixaDeSelecao();
	private CaixaDeSelecao cbResponsavel = new CaixaDeSelecao();
	private CaixaDeSelecao cbCategoria = new CaixaDeSelecao();
	private CaixaDeSelecao cbTitulo = new CaixaDeSelecao();
	private CaixaDeSelecao cbProjeto = new CaixaDeSelecao();
	private JTextField textId = new JTextField();
	private JTextField textCriador = new JTextField();
	private JTextField textDataCriacao = new JTextField();
	private JTextField textRevisor = new JTextField();
	private JTextField textDataRevisao = new JTextField();
	private JDateChooser dcDataReal = new JDateChooser();
	private JDateChooser dcDataPrevista = new JDateChooser();
	private JCheckBox chbxProcurarPorId = new JCheckBox("Procurar por Id");
	private JCheckBox chbxTarefaContinua = new JCheckBox("A tarefa \u00E9 cont\u00EDnua?");
	private JTextArea textDescricao = new JTextArea();
		
	Object [] listaDeCampos = {textId, cbTitulo, cbTipo, cbEmpreendimento, cbProjeto, cbCategoria, cbResponsavel, dcDataPrevista, chbxTarefaContinua, textDescricao, 
			dcDataReal, textCriador, textDataCriacao, textRevisor, textDataRevisao};
	
//	String[] elemento = new String[9];
//	elemento[0] = this.getTitulo();
//	elemento[1] = this.getEmpreendimento().getTipo().getDescricao();
//	elemento[2] = this.getEmpreendimento().getDescricao();
//	elemento[3] = this.getProjeto().getDescricao();
//	elemento[4] = this.getCategoria().getDescricao();
//	elemento[5] = this.getResponsavel().getNome();
//	elemento[6] = Ferramentas.exibirData(this.getDataConclusaoPrevista());
//	elemento[7] = Ferramentas.converterBooleanSimNao(this.isTarefaContinua());
//	elemento[8] = this.getDescricao();
//	return this.controle.toArray(elemento);
	
	TipoEmpreendimentoBO tipoEmpreendimentoBo = new TipoEmpreendimentoBO();
	EmpreendimentoBO empreendimentoBo = new EmpreendimentoBO();
	ProjetoBO projetoBo = new ProjetoBO();
	DemandaBO demandaBo = new DemandaBO();
	CategoriaBO categoriaBo = new CategoriaBO();
		
	public PainelCadastroDemanda() {	
		super();
		
		BarraDeNotificacoes.atualizar("Cadastro de demanda.");
		
		setPreferredSize(new Dimension(largura, altura));
		setLayout(new BorderLayout(0, 0));
		add(painelRolante, BorderLayout.CENTER);
		
		areaDeTrabalho.setLayout(null);
		
		painelRolante.setPreferredSize(new Dimension(largura, altura));
		painelRolante.setViewportView(painelPrincipal);
		
		painelPrincipal.setPreferredSize(new Dimension(largura, altura));
		painelPrincipal.setLayout(new BorderLayout(0, 0));	
		painelPrincipal.add(areaDeTrabalho, BorderLayout.CENTER);
		painelPrincipal.add(barraDeNotificacoes, BorderLayout.NORTH);		
		painelPrincipal.add(barraDeBotoes, BorderLayout.SOUTH);	
		
		barraDeBotoes.add(btnConsultar);
		barraDeBotoes.add(btnSalvar);
		barraDeBotoes.add(btnRemover);
		barraDeBotoes.add(btnCancelar);
		barraDeBotoes.add(btnLimpar);	
						
		JLabel lblEmpreendimento = new JLabel("Empreendimento:");
		lblEmpreendimento.setBounds(14, 110, 91, 14);
		areaDeTrabalho.add(lblEmpreendimento);
		cbEmpreendimento.setBounds(107, 110, 400, 20);
		areaDeTrabalho.add(cbEmpreendimento);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(14, 76, 75, 14);
		areaDeTrabalho.add(lblTipo);
				
		cbTipo.setBounds(107, 76, 400, 20);
		try {
			cbTipo.carregar(Ferramentas.deClasseParaArray(tipoEmpreendimentoBo.carregarTodos()));
		} catch (Exception e) {
			BarraDeNotificacoes.atualizar(e);
		}	
		areaDeTrabalho.add(cbTipo);
		
		JLabel lblCategoria = new JLabel("Categoria:");
		lblCategoria.setBounds(14, 172, 75, 14);
		areaDeTrabalho.add(lblCategoria);
		
		JLabel lblResponsável = new JLabel("Respons\u00E1vel:");
		lblResponsável.setBounds(14, 203, 75, 14);
		areaDeTrabalho.add(lblResponsável);
		
		cbCategoria.setBounds(107, 172, 400, 20);
		try {
			cbCategoria.carregar(Ferramentas.deClasseParaArray(categoriaBo.carregarTodos()));
		} catch (Exception e) {
			BarraDeNotificacoes.atualizar(e);
		}	
		areaDeTrabalho.add(cbCategoria);
		cbResponsavel.setBounds(107, 203, 400, 20);
		try {
			cbResponsavel.carregar(Ferramentas.deClasseParaArray(SGD.contaBo.carregarTodos()));
		} catch (Exception e) {
			BarraDeNotificacoes.atualizar(e);
		}	
		areaDeTrabalho.add(cbResponsavel);
		
		areaCampos.setBounds(10, 140, 490, 0);
		areaDeTrabalho.add(areaCampos);
		areaCampos.setLayout(null);
		
		JLabel lblDataPrevista = new JLabel("Data prevista:");
		lblDataPrevista.setBounds(14, 239, 75, 14);
		areaDeTrabalho.add(lblDataPrevista);
		
		JLabel lblProjeto = new JLabel("Projeto:");
		lblProjeto.setBounds(14, 141, 75, 14);
		areaDeTrabalho.add(lblProjeto);
		
		cbProjeto.setBounds(107, 141, 400, 20);
		try {
			cbProjeto.carregar(Ferramentas.deClasseParaArray(projetoBo.carregarTodos()));
		} catch (Exception e) {
			BarraDeNotificacoes.atualizar(e);
		}	
		areaDeTrabalho.add(cbProjeto);
		chbxTarefaContinua.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (chbxTarefaContinua.isSelected()){
					dcDataPrevista.setEnabled(false);
					dcDataPrevista.setDate(null);
				} else{
					dcDataPrevista.setEnabled(true);
				}
			}
		});		
		chbxTarefaContinua.setHorizontalAlignment(SwingConstants.LEFT);
		chbxTarefaContinua.setBounds(229, 235, 123, 23);
		areaDeTrabalho.add(chbxTarefaContinua);

		dcDataPrevista.setBounds(107, 236, 116, 20);
		areaDeTrabalho.add(dcDataPrevista);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Descri\u00E7\u00E3o:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(14, 294, 505, 235);
		areaDeTrabalho.add(panel);
		panel.setLayout(null);
		textDescricao.setLineWrap(true);
		textDescricao.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textDescricao.setWrapStyleWord(true);
		
		textDescricao.setBounds(6, 16, 493, 213);
		panel.add(textDescricao);
		
		JLabel lblTitulo = new JLabel("T\u00EDtulo:");
		lblTitulo.setBounds(14, 45, 75, 14);
		areaDeTrabalho.add(lblTitulo);
		cbTitulo.setEditable(true);
		cbTitulo.setBounds(107, 45, 400, 20);
		try {
			cbTitulo.carregar(Ferramentas.deClasseParaArray(demandaBo.carregarSimilares()));
		} catch (Exception e) {
			BarraDeNotificacoes.atualizar(e);
		}	
		areaDeTrabalho.add(cbTitulo);
		
		JLabel lblId = new JLabel("Id:");
		lblId.setBounds(14, 11, 75, 14);
		areaDeTrabalho.add(lblId);
		
		textId.setEditable(false);
		textId.setColumns(10);
		textId.setBounds(107, 11, 100, 20);
		areaDeTrabalho.add(textId);
		chbxProcurarPorId.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (chbxProcurarPorId.isSelected()){
					textId.setEditable(true);
					cbTitulo.setEditable(false);
					btnSalvar.setEnabled(false);
					btnRemover.setEnabled(false);
					try {
						cbTitulo.carregar(Ferramentas.deClasseParaArray(demandaBo.carregarTodos()));
					} catch (Exception e) {
						BarraDeNotificacoes.atualizar(e);
					}
				} else{
					textId.setEditable(false);
					cbTitulo.setEditable(true);
					btnSalvar.setEnabled(true);
					btnRemover.setEnabled(true);
					try {
						cbTitulo.carregar(Ferramentas.deClasseParaArray(demandaBo.carregarSimilares()));
						Funcoes.atualizarCampos(listaDeCampos, null);
						Funcoes.ativarCampos(new Object[]{btnSalvar,  btnRemover},true);
					} catch (Exception e) {
						BarraDeNotificacoes.atualizar(e);
					}
					
				}
			}
		});
				
		chbxProcurarPorId.setBounds(213, 7, 294, 23);
		areaDeTrabalho.add(chbxProcurarPorId);
		
		JLabel lblDataReal = new JLabel("Data real:");
		lblDataReal.setBounds(14, 268, 75, 14);
		areaDeTrabalho.add(lblDataReal);
		
		
		dcDataReal.setBounds(107, 265, 116, 20);
		dcDataReal.setEnabled(false);
		areaDeTrabalho.add(dcDataReal);
		
		JLabel lblCriador = new JLabel("Criado por:");
		lblCriador.setBounds(14, 540, 75, 14);
		areaDeTrabalho.add(lblCriador);
		
		JLabel lblRevisor = new JLabel("Revisado por:");
		lblRevisor.setBounds(14, 572, 91, 14);
		areaDeTrabalho.add(lblRevisor);
		
		textCriador.setEditable(false);
		textCriador.setColumns(10);
		textCriador.setBounds(107, 540, 100, 20);
		areaDeTrabalho.add(textCriador);
		
		JLabel lblDataCriacao = new JLabel("em:");
		lblDataCriacao.setBounds(223, 540, 75, 14);
		areaDeTrabalho.add(lblDataCriacao);
		
		textDataCriacao.setEditable(false);
		textDataCriacao.setColumns(10);
		textDataCriacao.setBounds(259, 540, 100, 20);
		areaDeTrabalho.add(textDataCriacao);
		
		textRevisor.setEditable(false);
		textRevisor.setColumns(10);
		textRevisor.setBounds(107, 572, 100, 20);
		areaDeTrabalho.add(textRevisor);
		
		JLabel lblDataRevisao = new JLabel("em:");
		lblDataRevisao.setBounds(223, 572, 75, 14);
		areaDeTrabalho.add(lblDataRevisao);
		
		textDataRevisao.setEditable(false);
		textDataRevisao.setColumns(10);
		textDataRevisao.setBounds(259, 572, 100, 20);
		areaDeTrabalho.add(textDataRevisao);
					
		painelPrincipal.add(barraDeBotoes, BorderLayout.SOUTH);
		
		cbTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				try {
					if (Funcoes.getTexto(cbTipo)!=""|!Funcoes.getTexto(cbTipo).equals("")){
						tipoEmpreendimentoBo.carregar(cbTipo);
						empreendimentoBo.setTipo(tipoEmpreendimentoBo);
						cbEmpreendimento.recarregar(Ferramentas.deClasseParaArray(empreendimentoBo.carregarSimilares()));		
					}
				} catch (Exception e) {
					BarraDeNotificacoes.atualizar(e);
				}
				setCursor(null);
			}
		});
		

							
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (chbxProcurarPorId.isSelected()){
						demandaBo.carregar(textId);
					} else{
						demandaBo.carregar(cbTitulo);
					}
					Funcoes.atualizarCampos(listaDeCampos, demandaBo.toArray());
					Funcoes.ativarCampos(new Object[]{btnSalvar,  btnRemover},demandaBo.direitoEdicao(SGD.contaBo));
					Funcoes.ativarCampos(new Object[]{cbResponsavel},false);
				} catch (Exception e) {
					BarraDeNotificacoes.atualizar(e);
				}
			}
		});
		
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				try {	
					new CaixaDeMensagem().confirmarAcao();
					BarraDeNotificacoes.atualizar(demandaBo.salvar(listaDeCampos));
					Funcoes.atualizarCampos(listaDeCampos, null);
				} catch (Exception e) {
					BarraDeNotificacoes.atualizar(e);
				} 
				setCursor(null);
			}
		});
		
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {	
					new CaixaDeMensagem().confirmarAcao();
					BarraDeNotificacoes.atualizar(demandaBo.remover(textId));
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
					Funcoes.ativarCampos(new Object[]{btnSalvar,  btnRemover, cbResponsavel},true);
				} catch (Exception e) {
					BarraDeNotificacoes.atualizar(e);
				}
			}
		});
				
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new CaixaDeMensagem().confirmarAcao();
					if (chbxProcurarPorId.isSelected()){
						demandaBo.carregar(textId);
					} else{
						demandaBo.carregar(cbTitulo);
					}	
					Funcoes.atualizarCampos(listaDeCampos, demandaBo.toArray());
				} catch (Exception e) {
					BarraDeNotificacoes.atualizar(e);
				}
			}
		});
	}
	
	public void procurar(String id){
		this.textId.setText(id);
		this.chbxProcurarPorId.setSelected(true);
		this.btnConsultar.doClick();
	}
}
