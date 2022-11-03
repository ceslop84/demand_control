package com.copel;

import java.util.Vector;

import com.copel.dao.Proxy;

public interface BO{
	
	public Proxy proxyDao();	
			
	public String salvar(Object[] obj) throws Exception;

	public String remover(Object obj) throws Exception;

	public void carregar(Object obj) throws Exception;
		
	public Vector<?> carregarTodos() throws Exception;
	
	public Vector<?> carregarSimilares() throws Exception;
}
