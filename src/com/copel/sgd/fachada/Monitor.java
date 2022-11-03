package com.copel.sgd.fachada;


public class Monitor implements Runnable {
	private Thread thread;
	boolean loopInfinito = true;

	public Monitor() {		
	}

	public void run() {
//		try {
//			dormir();
			//Loop infinito.
//			while (loopInfinito){
//
//				try {
//					if (trabalhos.size()==0){
//						SGD.trayIcon.displayMessage("SGD", Mensagem.getMensagemOcioAleatoria(), TrayIcon.MessageType.INFO);
//					}
//				} catch (Exception e) {
//					SGD.log.adicionar("Falha ao acessar o objeto " + trabalhoBo.getObjeto(), e);	
//				}
//				
//				dormir();
//			}			
//		} catch (InterruptedException e) {
//			SGD.log.adicionar("Monitor interrompido", e);
//		}
	}

	public void start() {
		if (thread == null) {
			thread = new Thread(this);
			thread.start();
		}
	}
	
	@SuppressWarnings("unused")
	private void dormir() throws InterruptedException{
		Thread.sleep(5*60*1000);
	}
	
}
