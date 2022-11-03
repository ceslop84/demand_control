package com.copel;

public class Mensagem{
	
	public static final String erroAdicionar = "Erro ao adicionar ";
	public static final String erroRemover = "Erro ao remover ";
	public static final String erroAtualizar = "Erro ao atualizar ";
	public static final String erroCarregar = "Erro ao carregar ";
	public static final String erroCarregarTodos = "Erro ao carregar mais de um(a) ";
	
	public static final String erroDesconhecidoObjeto = "Erro desconhecido no objeto ";
	public static final String erroObjetoNulo = "Objeto com preenchimento inv�lido. Valor nulo.";
	public static final String erroObjetoNaoEncontrado = "Elemento n�o encontrado ou inexistente ";
	
	public static final String erroSystemTray = "Erro no SystemTray. SystemTray n�o � suportado!";
	public static final String erroAdicionarSystemTray = "Erro na barra de tarefas. N�o foi poss�vel adicionar o aplicativo na barra de tarefas.";
	public static final String erroAbrirLog = "Erro ao abrir o arquivo de log. Consulte o administrador.";
	public static final String erroCarregarUsuario = "Erro ao carregar usu�rio " + System.getProperty("user.name").toUpperCase() + " para efetuar logon. Consulte o administrador.";
	public static final String erroMostrarErro = "Erro ao mostrar mensagem. N�o foi poss�vel mostrar a mensagem de erro.";
	
	public static final String sucessoAdicionar = "Sucesso ao adicionar ";
	public static final String sucessoRemover = "Sucesso ao remover ";
	public static final String sucessoAtualizar = "Sucesso ao atualizar ";
	public static final String sucessoCarregar = "Sucesso ao carregar ";
	public static final String sucessoCarregarTodos = "Sucesso ao carregar mais de um(a) ";
	
	private static final String [] mensagensOcio = new String[]{
	"Est� sem nada para fazer? Quel tal revisar os seus trabalhos pendentes?",
	 "zZzZzZzZ...",
	"Voc� sabia que voc�s n�o est� trabalhando em nada nesse momento?  Pense nisso...",
	"Voc� sabia que a Guiana Francesa � o �nico local da Am�rica do Sul onde a moeda oficial � o euro?\nAgora volte ao trabalho!",
	"Voc� sabia que Io, uma lua de J�piter, � o corpo vulcanicamente mais ativo do Sistema Solar?\nAgora, que tal voltar ao trabalho?",
	"T� sem nada para fazer?\nQue tal verificar se seus colegas precisam de ajuda?",
	};
	
	public static final String acronimoAplicacao = "SGD";
	public static final String nomeAplicacao = "SGD - Sistema de Gest�o de Demandas";
	public static final String versao = "1.1.6";
	public static final String sobre = "O SGD, ou Sistema de Gest�o de Demandas, � um sistema desenvolvido com o objetivo de cadastrar e acompanhar demandas, tanto por parte do executor quanto por parte do solicitante, bem como por parte do corpo gerencial.\nVers�o " + versao;
	public static final String suporte ="Para suporte t�cnico entrar em contato com Cesar Rafael Lopes:\nE-mail: cesar.lopes@copel.com\nRamal: (120) 4005";
	
	
	public static String getMensagemOcioAleatoria(){
	    return mensagensOcio[Ferramentas.numeroAleatorio(0, mensagensOcio.length-1)];
	};
	
}
