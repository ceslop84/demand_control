package com.copel.sgd.fachada;
import java.awt.AWTException;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.copel.Log;
import com.copel.Mensagem;
import com.copel.sgd.bo.ContaBO;
import com.copel.swing.CaixaDeMensagem;
 
public class SGD {
	
	public static ContaBO contaBo;
	public static TrayIcon trayIcon;
	public static Log log;
	
    public static void main(String[] args) {   	
        /* Use an appropriate Look and Feel */
        try {
        	UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	criarEAbrirLog();
            	carregarUsuario();
                criarEMostrarGUI();
                criarERodarMonitor();
            }
        });
    }
     
    private static void criarEMostrarGUI() {
        //Verifica o suporte so SystemTray
        if (!SystemTray.isSupported()) {
			new CaixaDeMensagem().alerta(log.adicionar(Mensagem.erroSystemTray));
			System.exit(0);
        }
        
        trayIcon = new TrayIcon(Toolkit.getDefaultToolkit().getImage(SGD.class.getResource("/com/copel/sgd/fachada/icon.gif")));
        trayIcon.setImageAutoSize(true);
        trayIcon.setToolTip(Mensagem.acronimoAplicacao);
        
        final PopupMenu popup = new PopupMenu();
        final SystemTray tray = SystemTray.getSystemTray();
        final JanelaPrincipal janelaPrincipal = new JanelaPrincipal();
         
        // Create a popup menu components
        MenuItem abrir = new MenuItem("Abrir");
        MenuItem sair = new MenuItem("Sair");
         
        //Add components to popup menu
        popup.add(abrir);  
        popup.add(sair);
         
        trayIcon.setPopupMenu(popup);
         
        try {
            tray.add(trayIcon);            
        } catch (AWTException e) {
			new CaixaDeMensagem().alerta(log.adicionar(Mensagem.erroAdicionarSystemTray, e));
			System.exit(0);
        }
         
        trayIcon.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
				janelaPrincipal.setVisible(true);
            }
        });
         
        abrir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
				janelaPrincipal.setVisible(true);	
            }
        });
        
        sair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
				try {
					new CaixaDeMensagem().confirmarAcao();
					tray.remove(trayIcon);
					System.exit(0);
				} catch (Exception e) {
				}	
            }
        });           
  }
    
    public static void criarEAbrirLog(){
    	try {
    		log = new Log();
		} catch (Exception e) {
			new CaixaDeMensagem().alerta(Mensagem.erroAbrirLog, e);
			System.exit(0);
		}
    }
    
    public static void criarERodarMonitor(){
        Monitor m = new Monitor();
        m.start();
    }
    
    public static void carregarUsuario(){
		try {
			contaBo = new ContaBO();
			contaBo.carregar(System.getProperty("user.name").toUpperCase());
		} catch (Exception e) {	
			new CaixaDeMensagem().alerta(log.adicionar(Mensagem.erroCarregarUsuario, e));
			System.exit(0);
		}	
    }
    
}