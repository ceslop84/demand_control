package com.copel.sgd.fachada;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.copel.Ferramentas;
import com.copel.TableColumnAdjuster;
import com.copel.sgd.bo.DemandaBO;
import com.copel.swing.BarraDeNotificacoes;
import com.copel.swing.CaixaDeMensagem;
import com.copel.swing.Funcoes;


public class PainelDemanda extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -71000499193137121L;
	Toolkit toolkit = Toolkit.getDefaultToolkit();  
	int largura = toolkit.getScreenSize().width -25;
	int altura = toolkit.getScreenSize().height - 100;
	
	JScrollPane painelRolante = new JScrollPane();
	JPanel painelPrincipal = new JPanel();
	JPanel areaDeTrabalho = new JPanel();
	JPanel barraDeNotificacoes = new BarraDeNotificacoes();

	DemandaBO demandaBo  = new DemandaBO();
	int pagina; //Demanda = 10; Trabalho = 20;
		
	private DefaultTableModel modelo = new DefaultTableModel();
	private JTable tabela;

	public PainelDemanda() {	
		super();
		
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
		
		tabela = new JTable(modelo);
				
		tabela.addMouseListener( new MouseAdapter(){
			public void mousePressed(MouseEvent e)
			{
				if (SwingUtilities.isLeftMouseButton(e))
				{
				}
				else if ( SwingUtilities.isRightMouseButton(e)){
					final JPopupMenu popup; popup = new JPopupMenu();
					JMenuItem mntmEditar = new JMenuItem("Editar");
					JMenuItem mntmFinalizarDemanda = new JMenuItem("Finalizar Demanda");
					JMenuItem mntmReabrirDemanda = new JMenuItem("Reabrir Demanda");	
					JMenuItem mntmDelegarDemanda = new JMenuItem("Delegar Demanda");					
					
					//Mapa de menus que devem aparecer de acordo com cada página.
					if (pagina==11){
						popup.add(mntmFinalizarDemanda);
						popup.add(mntmDelegarDemanda);
						popup.add(mntmEditar);
					}else if (pagina==12){
						popup.add(mntmEditar);	
						popup.add(mntmReabrirDemanda);
					}else if (pagina==13){
					}
					else if (pagina==14){
						popup.add(mntmReabrirDemanda);
					}
					else if (pagina==15){
					}
					else if (pagina==16){
					}
					else if (pagina==21){
					}
					else if (pagina==22){
					}
					else if (pagina==23){
					}
					else if (pagina==24){
					}
					else if (pagina==25){
					}
					else if (pagina==26){
					}

		            final int row = tabela.rowAtPoint(e.getPoint());
		            final int column = tabela.columnAtPoint(e.getPoint());

		            if (!tabela.isRowSelected(row)){
		            	tabela.changeSelection(row, column, false, false);
		            }

		            popup.show(e.getComponent(), e.getX(), e.getY());
		            
					mntmEditar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
							String id = (String) modelo.getValueAt(row, 0);
							PainelCadastroDemanda pd = new PainelCadastroDemanda();
							pd.procurar(id);
							Funcoes.atualizarPainel(painelPrincipal, pd.painelPrincipal, BorderLayout.CENTER);
							setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
						}
					});
					
				
					mntmReabrirDemanda.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {	
							setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
							String id = (String) modelo.getValueAt(row, 0);
							try {
								demandaBo.carregar(id);
								demandaBo.reiniciar();
								PainelDemanda pdt = new PainelDemanda();
								pdt.minhasDemandasHistorico();
								Funcoes.atualizarPainel(painelPrincipal, pdt.painelPrincipal, BorderLayout.CENTER);
								BarraDeNotificacoes.atualizar("Demanda reaberta");
							}catch (Exception e) {
								BarraDeNotificacoes.atualizar(e);
							}
							setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
						}
					});
					
					mntmFinalizarDemanda.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {	
							setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
							String id = (String) modelo.getValueAt(row, 0);
							try {
								demandaBo.carregar(id);
								demandaBo.finalizar();
								PainelDemanda pdt = new PainelDemanda();
								pdt.minhasDemandas();
								Funcoes.atualizarPainel(painelPrincipal, pdt.painelPrincipal, BorderLayout.CENTER);
								BarraDeNotificacoes.atualizar("Demanda finalizada!");
							}catch (Exception e) {
								BarraDeNotificacoes.atualizar(e);
							}
							setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
						}
					});
					
					mntmDelegarDemanda.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {	
							setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
							try {
								String responsavel =  new CaixaDeMensagem().popUpCaixaDeSelecao("Escolher novo responsável", Ferramentas.deClasseParaArray(SGD.contaBo.carregarTodos()), this);	
								String id = (String) modelo.getValueAt(row, 0);
								try {
									demandaBo.carregar(id);
									demandaBo.delegar(responsavel);									
									PainelDemanda pdt = new PainelDemanda();
									pdt.minhasDemandas();
									Funcoes.atualizarPainel(painelPrincipal, pdt.painelPrincipal, BorderLayout.CENTER);
									BarraDeNotificacoes.atualizar("Demanda delegada!");
								}catch (Exception e2) {
									BarraDeNotificacoes.atualizar(e2);
								}
							} catch (Exception e1) {
								BarraDeNotificacoes.atualizar(e1);
							}
							setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
						}
					});
					
					
	

			        
				}
			}
		});
	}
	
	public void incluirTabelaDemandas(){
	    modelo.addColumn("Id");
	    modelo.addColumn("Título");
	    modelo.addColumn("Tipo Empreendimento");
	    modelo.addColumn("Empreendimento");
	    modelo.addColumn("Projeto");
	    modelo.addColumn("Categoria");
	    modelo.addColumn("Responsável");
	    modelo.addColumn("Data de Conclusão Prevista");
	    modelo.addColumn("Tarefa Contínua?");
	    modelo.addColumn("Descrição");
	    modelo.addColumn("Data de Conclusão Real");
	    modelo.addColumn("Criador");
	    modelo.addColumn("Data de Criação");
	    modelo.addColumn("Revisor");
	    modelo.addColumn("Data de revisão");
	    
	    tabela.setModel(modelo);
	}
	
	public void incluirTabelaTrabalhos(){
	    modelo.addColumn("Id");
	    modelo.addColumn("Demanda Id");
	    modelo.addColumn("Título");
	    modelo.addColumn("Trabalhando?");
	    modelo.addColumn("Encerrada?");
	    modelo.addColumn("Trabalho (h)");
	    modelo.addColumn("Responsável");
	    modelo.addColumn("Quem trabalhou?");
	    modelo.addColumn("Criador");
	    modelo.addColumn("Data de Criação");
	    modelo.addColumn("Revisor");
	    modelo.addColumn("Data de revisão");	   
	    
	    tabela.setModel(modelo);
	}
	
	public void minhasDemandas(){
		try {
			BarraDeNotificacoes.atualizar("Minhas demandas.");
			pagina = 11;
			incluirTabelaDemandas();
			demandaBo.setResponsavel(SGD.contaBo);
			demandaBo.reabrir();
			Funcoes.carregarTabela(modelo, Ferramentas.deClasseParaArray(demandaBo.carregarSimilares()));	
			ajustarTabela();
		} catch (Exception e) {
			BarraDeNotificacoes.atualizar(e);
		}		
	}
	
	public void todasDemandas(){
		try {
			BarraDeNotificacoes.atualizar("Todas as demandas.");
			pagina = 15;
			incluirTabelaDemandas();
			demandaBo.reabrir();;
			Funcoes.carregarTabela(modelo, Ferramentas.deClasseParaArray(demandaBo.carregarSimilares()));	
			ajustarTabela();
		} catch (Exception e) {
			BarraDeNotificacoes.atualizar(e);
		}		
	}
	
	public void minhasDemandasHistorico(){
		try {
			BarraDeNotificacoes.atualizar("Histórico - Minhas demandas.");
			pagina = 12;
			incluirTabelaDemandas();
			demandaBo.setResponsavel(SGD.contaBo);
			demandaBo.encerrar();
			Funcoes.carregarTabela(modelo, Ferramentas.deClasseParaArray(demandaBo.carregarSimilares()));
			ajustarTabela();
		} catch (Exception e) {
			BarraDeNotificacoes.atualizar(e);
		}		
	}
	
	public void todasDemandasHistorico(){
		try {
			BarraDeNotificacoes.atualizar("Histórico - Todas as demandas.");
			pagina = 16;
			incluirTabelaDemandas();
			demandaBo.encerrar();
			Funcoes.carregarTabela(modelo, Ferramentas.deClasseParaArray(demandaBo.carregarSimilares()));
			ajustarTabela();
		} catch (Exception e) {
			BarraDeNotificacoes.atualizar(e);
		}		
	}
	
	public void demandasDelegadas(){
		try {
			BarraDeNotificacoes.atualizar("Demandas delegadadas.");
			pagina = 13;
			incluirTabelaDemandas();
			demandaBo.getControle().setCriador(SGD.contaBo);
			demandaBo.reabrir();
			Funcoes.carregarTabela(modelo, Ferramentas.deClasseParaArray(demandaBo.carregarSimilares()));
		    ajustarTabela();
		    filtrarTabela();
		} catch (Exception e) {
			BarraDeNotificacoes.atualizar(e);
		}
	}
	
	public void demandasDelegadasHistorico(){
		try {
			BarraDeNotificacoes.atualizar("Histórico - Demandas delegadadas.");
			pagina = 14;
			incluirTabelaDemandas();
			demandaBo.getControle().setCriador(SGD.contaBo);
			demandaBo.encerrar();
			Funcoes.carregarTabela(modelo, Ferramentas.deClasseParaArray(demandaBo.carregarSimilares()));
		    ajustarTabela();
		    filtrarTabela();
		} catch (Exception e) {
			BarraDeNotificacoes.atualizar(e);
		}
	}
	
	public void ajustarTabela(){
		areaDeTrabalho.removeAll();
		tabela.setModel(modelo);
		tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabela.setAutoCreateRowSorter(true);
		TableColumnAdjuster tca = new TableColumnAdjuster(tabela);
		tca.adjustColumns();
		areaDeTrabalho.setLayout(new BorderLayout(0, 0));
		JScrollPane painel = new JScrollPane(tabela);
		areaDeTrabalho.add(painel, BorderLayout.CENTER);		
	}
	
	public void filtrarTabela(){
		//Filtro para mostrar apenas as demandas que eu criei e não sou o atual responsável.
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(modelo);
		tabela.setModel(modelo);
		tabela.setRowSorter(sorter);
		RowFilter<DefaultTableModel, Object> rf = null;
	    try {
	        rf = RowFilter.regexFilter("^((?!" + SGD.contaBo.getNome()+").)*$", 6);
	    } catch (java.util.regex.PatternSyntaxException e) {
	        return;
	    }
	    sorter.setRowFilter(rf);
	}
}
