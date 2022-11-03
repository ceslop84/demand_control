package com.copel;

public class Mensagem{
	
	public static final String erroAdicionar = "Erro ao adicionar ";
	public static final String erroRemover = "Erro ao remover ";
	public static final String erroAtualizar = "Erro ao atualizar ";
	public static final String erroCarregar = "Erro ao carregar ";
	public static final String erroCarregarTodos = "Erro ao carregar mais de um(a) ";
	
	public static final String erroDesconhecidoObjeto = "Erro desconhecido no objeto ";
	public static final String erroObjetoNulo = "Objeto com preenchimento inválido. Valor nulo.";
	public static final String erroObjetoNaoEncontrado = "Elemento não encontrado ou inexistente ";
	
	public static final String erroSystemTray = "Erro no SystemTray. SystemTray não é suportado!";
	public static final String erroAdicionarSystemTray = "Erro na barra de tarefas. Não foi possível adicionar o aplicativo na barra de tarefas.";
	public static final String erroAbrirLog = "Erro ao abrir o arquivo de log. Consulte o administrador.";
	public static final String erroCarregarUsuario = "Erro ao carregar usuário " + System.getProperty("user.name").toUpperCase() + " para efetuar logon. Consulte o administrador.";
	public static final String erroMostrarErro = "Erro ao mostrar mensagem. Não foi possível mostrar a mensagem de erro.";
	
	public static final String sucessoAdicionar = "Sucesso ao adicionar ";
	public static final String sucessoRemover = "Sucesso ao remover ";
	public static final String sucessoAtualizar = "Sucesso ao atualizar ";
	public static final String sucessoCarregar = "Sucesso ao carregar ";
	public static final String sucessoCarregarTodos = "Sucesso ao carregar mais de um(a) ";
	
	private static final String [] mensagensOcio = new String[]{
	"Está sem nada para fazer? Quel tal revisar os seus trabalhos pendentes?",
	 "zZzZzZzZ...",
	"Você sabia que vocês não está trabalhando em nada nesse momento?  Pense nisso...",
	"Você sabia que a Guiana Francesa é o único local da América do Sul onde a moeda oficial é o euro?\nAgora volte ao trabalho!",
	"Você sabia que Io, uma lua de Júpiter, é o corpo vulcanicamente mais ativo do Sistema Solar?\nAgora, que tal voltar ao trabalho?",
	"Tá sem nada para fazer?\nQue tal verificar se seus colegas precisam de ajuda?",
	};
	
	public static final String acronimoAplicacao = "SGD";
	public static final String nomeAplicacao = "SGD - Sistema de Gestão de Demandas";
	public static final String versao = "1.1.6";
	public static final String sobre = "O SGD, ou Sistema de Gestão de Demandas, é um sistema desenvolvido com o objetivo de cadastrar e acompanhar demandas, tanto por parte do executor quanto por parte do solicitante, bem como por parte do corpo gerencial.\nVersão " + versao;
	public static final String suporte ="Para suporte técnico entrar em contato com Cesar Rafael Lopes:\nE-mail: cesar.lopes@copel.com\nRamal: (120) 4005";
	
	
	public static String getMensagemOcioAleatoria(){
	    return mensagensOcio[Ferramentas.numeroAleatorio(0, mensagensOcio.length-1)];
	};
	
}
