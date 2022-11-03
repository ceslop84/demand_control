package com.copel.sgd.fachada;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import com.copel.dao.BancoDeDados;
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

public class PainelAnalise extends JPanel {  
	
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
	public JButton btnLimpar = new JButton("Limpar");
	
	private CaixaDeSelecao cbEmpreendimento = new CaixaDeSelecao();
	private CaixaDeSelecao cbTipo = new CaixaDeSelecao();
	private CaixaDeSelecao cbResponsavel = new CaixaDeSelecao();
	private CaixaDeSelecao cbCategoria = new CaixaDeSelecao();
	private CaixaDeSelecao cbTitulo = new CaixaDeSelecao();
	private CaixaDeSelecao cbProjeto = new CaixaDeSelecao();
	private JTextField textId = new JTextField();
	private JDateChooser dcDataFim = new JDateChooser();
	private JDateChooser dcDataInicio = new JDateChooser();
	private JCheckBox chbxProcurarPorId = new JCheckBox("Procurar por Id");
	private JCheckBox chbxTarefaContinua = new JCheckBox("A tarefa \u00E9 cont\u00EDnua?");
		
	Object [] listaDeCampos = {textId, cbTitulo, cbTipo, cbEmpreendimento, cbProjeto, cbCategoria, cbResponsavel, dcDataInicio, chbxTarefaContinua, dcDataFim};
	TipoEmpreendimentoBO tipoEmpreendimentoBo = new TipoEmpreendimentoBO();
	EmpreendimentoBO empreendimentoBo = new EmpreendimentoBO();
	ProjetoBO projetoBo = new ProjetoBO();
	DemandaBO demandaBo = new DemandaBO();
	CategoriaBO categoriaBo = new CategoriaBO();
		
	public PainelAnalise() {	
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
//		try {
//			cbTipo.carregar(Ferramentas.deClasseParaArray(tipoEmpreendimentoBo.carregarTodos()));
//		} catch (Exception e) {
//			BarraDeNotificacoes.atualizar(e);
//		}	
		areaDeTrabalho.add(cbTipo);
		
		JLabel lblCategoria = new JLabel("Categoria:");
		lblCategoria.setBounds(517, 76, 75, 14);
		areaDeTrabalho.add(lblCategoria);
		
		JLabel lblResponsável = new JLabel("Respons\u00E1vel:");
		lblResponsável.setBounds(517, 107, 75, 14);
		areaDeTrabalho.add(lblResponsável);
		
		cbCategoria.setBounds(610, 76, 400, 20);
//		try {
//			cbCategoria.carregar(Ferramentas.deClasseParaArray(categoriaBo.carregarTodos()));
//		} catch (Exception e) {
//			BarraDeNotificacoes.atualizar(e);
//		}	
		areaDeTrabalho.add(cbCategoria);
		cbResponsavel.setBounds(610, 107, 400, 20);
//		try {
//			cbResponsavel.carregar(Ferramentas.deClasseParaArray(SGD.contaBo.carregarTodos()));
//		} catch (Exception e) {
//			BarraDeNotificacoes.atualizar(e);
//		}	
		areaDeTrabalho.add(cbResponsavel);
		
		areaCampos.setBounds(10, 140, 490, 0);
		areaDeTrabalho.add(areaCampos);
		areaCampos.setLayout(null);
		
		JLabel lblDataInicial = new JLabel("Data inicial:");
		lblDataInicial.setBounds(1020, 78, 75, 14);
		areaDeTrabalho.add(lblDataInicial);
		
		JLabel lblProjeto = new JLabel("Projeto:");
		lblProjeto.setBounds(517, 45, 75, 14);
		areaDeTrabalho.add(lblProjeto);
		
		cbProjeto.setBounds(610, 45, 400, 20);
//		try {
//			cbProjeto.carregar(Ferramentas.deClasseParaArray(projetoBo.carregarTodos()));
//		} catch (Exception e) {
//			BarraDeNotificacoes.atualizar(e);
//		}	
		areaDeTrabalho.add(cbProjeto);
//		chbxTarefaContinua.addChangeListener(new ChangeListener() {
//			public void stateChanged(ChangeEvent e) {
//				if (chbxTarefaContinua.isSelected()){
//					dcDataInicio.setEnabled(false);
//					dcDataInicio.setDate(null);
//				} else{
//					dcDataInicio.setEnabled(true);
//				}
//			}
//		});		
		chbxTarefaContinua.setHorizontalAlignment(SwingConstants.LEFT);
		chbxTarefaContinua.setBounds(1020, 45, 123, 23);
		areaDeTrabalho.add(chbxTarefaContinua);

		dcDataInicio.setBounds(1113, 75, 116, 20);
		areaDeTrabalho.add(dcDataInicio);
		
		JLabel lblTitulo = new JLabel("T\u00EDtulo:");
		lblTitulo.setBounds(14, 45, 75, 14);
		areaDeTrabalho.add(lblTitulo);
		cbTitulo.setBounds(107, 45, 400, 20);
//		try {
//			cbTitulo.carregar(Ferramentas.deClasseParaArray(demandaBo.carregarSimilares()));
//		} catch (Exception e) {
//			BarraDeNotificacoes.atualizar(e);
//		}	
		areaDeTrabalho.add(cbTitulo);
		
		JLabel lblId = new JLabel("Id:");
		lblId.setBounds(14, 11, 75, 14);
		areaDeTrabalho.add(lblId);
		
		textId.setEditable(false);
		textId.setColumns(10);
		textId.setBounds(107, 11, 100, 20);
		areaDeTrabalho.add(textId);
//		chbxProcurarPorId.addItemListener(new ItemListener() {
//			public void itemStateChanged(ItemEvent arg0) {
//				if (chbxProcurarPorId.isSelected()){
//					textId.setEditable(true);
//					cbTitulo.setEditable(false);
//					try {
//						cbTitulo.carregar(Ferramentas.deClasseParaArray(demandaBo.carregarTodos()));
//					} catch (Exception e) {
//						BarraDeNotificacoes.atualizar(e);
//					}
//				} else{
//					textId.setEditable(false);
//					cbTitulo.setEditable(true);
//					try {
//						cbTitulo.carregar(Ferramentas.deClasseParaArray(demandaBo.carregarSimilares()));
//						Funcoes.atualizarCampos(listaDeCampos, null);
//					} catch (Exception e) {
//						BarraDeNotificacoes.atualizar(e);
//					}
//					
//				}
//			}
//		});
				
		chbxProcurarPorId.setBounds(213, 7, 294, 23);
		areaDeTrabalho.add(chbxProcurarPorId);
		
		JLabel lblDataFinal = new JLabel("Data final:");
		lblDataFinal.setBounds(1020, 107, 75, 14);
		areaDeTrabalho.add(lblDataFinal);
		
		
		dcDataFim.setBounds(1113, 104, 116, 20);
		areaDeTrabalho.add(dcDataFim);
					
		painelPrincipal.add(barraDeBotoes, BorderLayout.SOUTH);
		
//		cbTipo.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
//				try {
//					if (Funcoes.getTexto(cbTipo)!=""|!Funcoes.getTexto(cbTipo).equals("")){
//						tipoEmpreendimentoBo.carregar(cbTipo);
//						empreendimentoBo.setTipo(tipoEmpreendimentoBo);
//						cbEmpreendimento.recarregar(Ferramentas.deClasseParaArray(empreendimentoBo.carregarSimilares()));		
//					}
//				} catch (Exception e) {
//					BarraDeNotificacoes.atualizar(e);
//				}
//				setCursor(null);
//			}
//		});
		

							
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					JasperCompileManager.compileReportToFile("relatorios/tipoEmpreendimento.jrxml", "relatorios/tipoEmpreendimento.jasper");		
					HashMap parametros = new HashMap();	
					JasperPrint print = (JasperPrint) JasperFillManager.fillReport("relatorios/tipoEmpreendimento.jasper", parametros, new BancoDeDados().getConexao());  
					JasperExportManager.exportReportToPdfFile(print, "relatorios/tipoEmpreendimento.pdf");
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
	}
	
}
