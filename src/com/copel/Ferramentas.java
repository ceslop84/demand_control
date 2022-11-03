package com.copel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.Vector;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.copel.swing.Funcoes;

public class Ferramentas {
	
	public static String separador = "|";	
	public static String separadorData = "/";
	public static String separadorHora = ":";
		
	public static long horaAgoraDataDeHojeBancoDeDados(){;
		return converterDataHoraBancoDeDados(DateTime.now());
	}
	
	public static DateTime converterDataHoraBancoDeDados(long entrada) {
		try {
			String data = String.valueOf(entrada);
			if (data.length()<8|data.length()>12){
				return null;
			}else{
				int ano = Integer.valueOf(data.substring(0, 4));
				int mes = Integer.valueOf(data.substring(4, 6));
				int dia = Integer.valueOf(data.substring(6, 8));
				int hora = Integer.valueOf(data.substring(8, 10));
				int minuto = Integer.valueOf(data.substring(10, 12));
				DateTime saida = new DateTime(ano, mes, dia, hora, minuto);
				return saida;
			}
		}catch (Exception e) {
			return null;
		} 	
	}
	
	public static long converterDataHoraBancoDeDados(Date entrada) {
		try {
			SimpleDateFormat formato = new SimpleDateFormat("yyyyMMddHHmm");
			return Long.valueOf(formato.format(entrada.getTime()));
		}catch (Exception e) {
			return 0;
		} 	
	}
	
	public static long converterDataHoraBancoDeDados(DateTime entrada) {
		try {	
			DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyyMMddHHmm");
			return Long.valueOf(fmt.print(entrada));
		}catch (Exception e) {
			return 0;
		} 	
	}
	
	public static String exibirData(long entrada){			
		try {
			String data = String.valueOf(entrada);
			if (data.length()<12|data.length()>12){
				return "-";
			}else{
				String ano = String.valueOf(data.substring(0, 4));
				String mes = String.valueOf(data.substring(4, 6));
				String dia = String.valueOf(data.substring(6, 8));
				return dia + separadorData + mes + separadorData + ano;
			}
		} catch (Exception e) {
			return "Data inválida";
		}		
	}	
	
	public static String exibirDataHora(long entrada){		
		try {
			String data = String.valueOf(entrada);
			if (data.length()<12|data.length()>12){
				return "-";
			}else{
				String ano = String.valueOf(data.substring(0, 4));
				String mes = String.valueOf(data.substring(4, 6));
				String dia = String.valueOf(data.substring(6, 8));
				String hora = String.valueOf(data.substring(8, 10));
				String minuto =String.valueOf(data.substring(10, 12));
				return dia + separadorData + mes + separadorData + ano + " " + hora + separadorHora + minuto;
			}
		} catch (Exception e) {
			return "Data inválida";
		}		
	}	
			
	public static long verificarData(String campo, String entrada) throws Exception {		
        try {  
    		String separador = "["+ separadorData +"]+";
    		String[] data = entrada.split(separador);
    		if (data.length<3|data.length>3){
    			throw new Exception(campo + " com preenchimento inválido. Data no formato inválido.", null);
			}else{
				return Long.valueOf(data[2] + data[1] + data[0]+"0000");
			}
        } catch (ArrayIndexOutOfBoundsException e) {              
        	throw new Exception(campo + " com preenchimento inválido. Data no formato inválido.", null);
        } catch (NullPointerException e){
        	return 0;
		}	
    }
	
	public static int verificarInteiro(String campo, String numero) throws Exception {
		if (numero.isEmpty()){
			return -1;
		}else{
			try {
				return Integer.valueOf(numero);
			} catch (NumberFormatException e) {
				throw new Exception(campo +" com preenchimento inválido. Não são permitidos textos.", null);
			}
		}
	}
	
	public static double verificarDouble(String campo, String numero)
			throws Exception {
		if (numero.isEmpty()) {
			return -1;
		} else {
			try {
				return Double.valueOf(numero);
			} catch (NumberFormatException e) {
				throw new Exception(
						campo
								+ " com preenchimento inválido. Não são permitidos textos.",
						null);
			}
		}
	}
			
	public static int varificarId(Object obj) throws Exception {
		String campoTexto = Funcoes.getTexto(obj);
		try {
			return Integer.valueOf(campoTexto);
		} catch (NumberFormatException e) {
			throw new Exception("ID com preenchimento inválido. O valor não é um inteiro.", null);
		}
	}
	
	public static String verificarTexto(String campo, String texto) throws Exception {
		if (texto.isEmpty()){
			throw new Exception(campo +" com preenchimento inválido. Campo de preenchimento obrigatório.", null);
		}else{
			try {
				Integer.valueOf(texto);
				throw new Exception(campo +" com preenchimento inválido. Não são permitidos valores numéricos.", null);
			} catch (NumberFormatException e) {
				return texto;
			}
		}
	}
	
	public static String verificarAcronimo(String acronimo) throws Exception {
		if (acronimo.isEmpty()){
			throw new Exception("Acrônimo inválido. Campo de preenchimento obrigatório.", null);
		}else{
			try {
				Integer.valueOf(acronimo);
				throw new Exception("Acrônimo inválido. Não são permitidos valores numéricos.", null);
			} catch (NumberFormatException e) {
				if (acronimo.length()<10){
					return acronimo.toUpperCase();
				}else{
					throw new Exception("Acrônimo inválido. O texto deve ser menor que 10 caracteres.", null);
				}
			}
		}
	}
	
	public static boolean verificarBoolean(String campo, String valor) throws Exception {
		if (valor.isEmpty()) {
			throw new Exception(
					"Booleano com preenchimento inválido. O valor está vazio.",
					null);
		} else {
			if (valor.equals("true")|valor.equals("1")) {
				return true;
			} else if (valor.equals("false")|valor.equals("0")) {
				return false;
			} else {
				throw new Exception(
						campo + " com preenchimento inválido. Texto digitado incorretamente.",
						null);
			}
		}
	}
		
	public static Vector<String[]> deClasseParaArray(Vector<?> lista) throws Exception {
		Vector <String[]> listaString = new Vector<String[]>();
		for (int i=0;i<lista.size();i++){
			listaString.add(Ferramentas.deStringParaArray(lista.get(i).toString()));
		};
		return listaString;
	}

	public static String[] deStringParaArray(String entrada) throws Exception {
		String[] saida = null;
		try {
			if (entrada != null) {
				StringTokenizer st;
				st = new StringTokenizer(entrada, separador);
				saida = new String[st.countTokens()];
				int i = 0;
				while (st.hasMoreTokens()) {
					saida[i] = st.nextToken();
					i++;
				}
			}
		} catch (NullPointerException e) {
			throw new Exception("Objeto com preenchimento inválido.", null);
		}catch (NoSuchElementException e){
			throw new Exception("Objeto com preenchimento inválido.", null);
		}	
		return saida;
	}

	public static String deArrayParaString(String[] entrada) {
		String saida = "";
		if (entrada != null) {
			for (int i = 0; i < entrada.length; i++) {
				saida = saida + entrada[i] + separador;
			}
		}
		return saida;
	}
	
	public static String converterBooleanUmZero(boolean valor) {
		if (valor){
			return "1";
		}else{
			return "0";
		}
	}
	
	public static String converterBooleanSimNao(boolean valor) {
		if (valor){
			return "Sim";
		}else{
			return "Não";
		}
	}
	
	public static int numeroAleatorio(int aStart, int aEnd){
		Random random = new Random();
		if (aStart > aEnd) {
			throw new IllegalArgumentException("Número de início não pode exceder o número final.");
		}
		// get the range, casting to long to avoid overflow problems
		long range = (long) aEnd - (long) aStart + 1;
		// compute a fraction of the range, 0 <= frac < range
		long fraction = (long) (range * random.nextDouble());
		return (int) (fraction + aStart);
	}
}
