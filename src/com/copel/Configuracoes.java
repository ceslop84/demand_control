package com.copel;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;


public class Configuracoes extends Properties{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8620476243909716228L;
	private Log log = new Log();

	public Log getLog() {
		return log;
	}

	public void setLog(Log log) {
		this.log = log;
	}

	public  Configuracoes() throws Exception {
		super();
		 // Ler arquivo de properties com informações do banco de dados.  
        try {
			this.carregar(new FileInputStream(new File("system.properties")));
		} catch (FileNotFoundException e) {
			// Arquivo não encontrado.
			this.getLog().adicionarEJogarExcecao("!ERRO! - Arquivo de configurações não encontrado. Arquivo NÃO carregado.", e);
		} catch (IOException e) {
			// Falha ao carregar o arquivo.
			this.getLog().adicionarEJogarExcecao("!ERRO! - Falha ao carregar o arquivo de propriedades. Arquivo NÃO carregado.", e);
		}   
	}
	
    @SuppressWarnings("resource")
	public void carregar(FileInputStream fis) throws Exception {
        try {
			Scanner in = new Scanner(fis);
			ByteArrayOutputStream out = new ByteArrayOutputStream();

			while(in.hasNext()) {
			    out.write(in.nextLine().replace("\\","\\\\").getBytes());
			    out.write("\n".getBytes());
			}

			InputStream is = new ByteArrayInputStream(out.toByteArray());
			super.load(is);
		} catch (IOException e) {
			// Falha ao carregar o arquivo.
			this.getLog().adicionarEJogarExcecao("!ERRO! - Falha ao carregar o arquivo de propriedades. Arquivo NÃO carregado.", e);
		}
    }
}
