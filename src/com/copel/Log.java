package com.copel;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Log {
	FileWriter logFile;
	PrintWriter log;
	
	public FileWriter getLogFile() {
		return logFile;
	}

	public void setLogFile(FileWriter logFile) {
		this.logFile = logFile;
	}

	public PrintWriter getLog() {
		return log;
	}

	public void setLog(PrintWriter log) {
		this.log = log;
	}
	
	public Log(String logFileStr) throws Exception {
			// Testa se o arquivo de log existe.
			try {
				File arqLog = new File(logFileStr);
				if (arqLog.exists()) {  
					// Arquivo existe.  
					this.setLogFile(new FileWriter(logFileStr, true));
					this.setLog(new PrintWriter(this.getLogFile(),true)); 
				} else {  
					// Arquivo não existe.  
					arqLog.createNewFile();
					this.setLogFile(new FileWriter(logFileStr, true));
					this.setLog(new PrintWriter(this.getLogFile(),true)); 
				}
			} catch (Exception e) {
				throw new Exception("!ERRO! - Falha ao abrir o arquivo de log.", e.getCause());
			}
	}
	
	public Log() throws Exception {
		Locale locale = new Locale("en","US");
		GregorianCalendar calendar = new GregorianCalendar();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd",locale);
		
		String logDate = formatter.format(calendar.getTime()).toString();
		String logFileStr = logDate + ".log";
		
		try {
			 // Ler arquivo de properties com informações do banco de dados.  
			File pastaDeLog = new File(System.getProperty("user.dir") + "\\log");
			// Testa se o arquivo de log existe.
			if(!pastaDeLog.exists()){
				pastaDeLog.mkdir();
			}
			Log log = new Log(pastaDeLog.getAbsolutePath() + "\\" + logFileStr);
			this.setLog(log.getLog());
			this.setLogFile(log.getLogFile());
		} catch (Exception e) {
			throw new Exception("!ERRO! - Falha ao abrir o arquivo de log.", e.getCause());
		}
		
		
	}

	public String adicionar(String mensagem){
		//Criação da data
		Locale locale = new Locale("en","US");
		GregorianCalendar calendar = new GregorianCalendar();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy'-'MMMMM'-'dd'@'HH':'mm':'ss,SSS",locale);
				
		// Adição da mensagem de log no arquivo do logs.
		this.getLog().print(formatter.format(calendar.getTime()));
		this.getLog().print(" - ");
		this.getLog().print(mensagem);
		this.getLog().println();
		return mensagem;
	}
	
	public void adicionarEJogarExcecao(String mensagem, Exception e) throws Exception{	
		adicionar(mensagem, e);
		throw new Exception(mensagem, e.getCause());
	}
	
	public String adicionar(String mensagem, Exception e){
		//Criação da data
		Locale locale = new Locale("en","US");
		GregorianCalendar calendar = new GregorianCalendar();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy'-'MMMMM'-'dd'@'HH':'mm':'ss,SSS",locale);
				
		// Adição da mensagem de log no arquivo do logs.
		this.getLog().print(formatter.format(calendar.getTime()));
		this.getLog().print(" - ");
		this.getLog().print(mensagem);
		this.getLog().println();
		this.getLog().println("Início da mensagem de erro:");
		e.printStackTrace(this.getLog());
		this.getLog().println("Final da mensagem de erro.");
		return mensagem;
	}
	
	public void fechar() throws Exception{
		try {
			this.logFile.close();  
			this.log.close();
		} catch (IOException e) {
			throw new Exception("!ERRO! - Falha ao fechar o arquivo de log.", e.getCause());
		}
	}
}
