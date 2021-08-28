package Sintatico;

import compilador.analisador.lexico.Token;
import compilador.estruturas.ListaLigada;
import compilador.estruturas.String;

public class Submaquina {
	
	/**
	 * Indica um estado inv‡lido.
	 */
	public static final int ESTADO_INVALIDO = -1;
	
	/**
	 * Indica que a transi�‹o ocorreu normalmente.
	 */
	public static final int TRANSICAO_OK = 10;
	
	/**
	 * Indica que a transi�‹o normal falhou. Deve ocorrer ent‹o um retorno ou chamada de subm‡quina.
	 */
	public static final int TRANSICAO_FALHOU = 11;
	
	/**
	 * O nome da subm‡quina.
	 */
	private String nome;
	
	/**
	 * O estado inicial da subm‡quina.
	 */
	private int estadoInicial;
	
	/**
	 * Array com os estados iniciais da subm‡quina.
	 */
	private int[] estadosFinais;
	
	/**
	 * Estado atual da subm‡quina.
	 */
	private int estadoAtual;
	
	/**
	 * Tabela de transi�‹o de estados da subm‡quina.
	 */
	private int[][][] tabelaTransicao;
	
	/**
	 * Tabela com a rela�‹o de quais subm‡quinas podem ser chamadas em cada estado.
	 */
	private int[][] tabelaChamadaSubmaquinas;
	
	public Submaquina(String nome, int estadoInicial, int[] estadosFinais, int[][][] tabelaTransicao, int[][] tabelaChamadaSubmaquinas) {
		this.nome = nome;
		this.estadoInicial = this.estadoAtual = estadoInicial;
		this.estadosFinais = estadosFinais;
		this.tabelaTransicao = tabelaTransicao;
		this.tabelaChamadaSubmaquinas = tabelaChamadaSubmaquinas;
	}
	
	/**
	 * @return o nome da subm‡quina.
	 */
	public String getNome() {
		return this.nome;
	}
	
	/**
	 * @return o estado inicial da subm‡quina.
	 */
	public int getEstadoInicial() {
		return this.estadoInicial;
	}
	
	/**
	 * @return o array de estados finais.
	 */
	public int[] getEstadosFinais() {
		return this.estadosFinais;
	}
	
	/**
	 * @return o estado atual da subm‡quina.
	 */
	public int getEstadoAtual() {
		return this.estadoAtual;
	}
	
	/**
	 * Seta o estado atual da sum‡quina.
	 * 
	 * @param estadoAtual
	 */
	public void setEstadoAtual(int estadoAtual) {
		this.estadoAtual = estadoAtual;
	}
	
	/**
	 * Executa uma transi�‹o na subm‡quina.
	 * 
	 * @param token
	 * @return
	 */
	public int transicao(Token token) {
		try{
			int proximoEstado = this.tabelaTransicao[this.estadoAtual][token.getClasse()][token.getID()];
		
			if(proximoEstado == ESTADO_INVALIDO) {
				// Deve tentar chamar uma subm‡quina.
				return TRANSICAO_FALHOU;
			} else {
				// H‡ transi�‹o dispon’vel.
				this.estadoAtual = proximoEstado;
				return TRANSICAO_OK;
			}
		} catch(Exception e) {
			System.exit(0);
		}
		return TRANSICAO_FALHOU;
	}
	
	/**
	 * Identifica qual subm‡quina deve ser chamada.
	 * 
	 * @param token
	 * @return o identificador da subm‡quina a ser chamada.
	 */
	public ListaLigada<Integer> possiveisSubmaquinas(Token token) {
		ListaLigada<Integer> possiveisSubmaquinas = new ListaLigada<Integer>();
		
		// Identificar as subm‡quinas que podem ser chamadas neste estado.
		for(int i = 0; i < this.tabelaChamadaSubmaquinas[this.estadoAtual].length; i++)
			if(this.tabelaChamadaSubmaquinas[this.estadoAtual][i] != -1)
				possiveisSubmaquinas.put(i);
		
		return possiveisSubmaquinas;
	}
	
	/**
	 * Verifica se a subm‡quina possui transi�‹o no estado atual para o token dado.
	 * 
	 * @param token 
	 * @return <code>true</code>, caso exista a transi�‹o. <code>false</code>, caso n‹o exista.
	 */
	public boolean haTransicao(Token token) {
		int proximoEstado = this.tabelaTransicao[this.estadoAtual][token.getClasse()][token.getID()];
		
		if(proximoEstado == ESTADO_INVALIDO)
			return false;
		return true;
	}
	
	/**
	 * Verifica se a subm‡quina possui, no estado atual, chamada de outra subm‡quina.
	 * 
	 * @return <code>true</code>, caso exista a chamada. <code>false</code>, caso n‹o exista.
	 */
	public boolean haChamadaSubmaquina() {
		for(int i = 0; i < this.tabelaChamadaSubmaquinas[this.estadoAtual].length; i++)
			if(this.tabelaChamadaSubmaquinas[this.estadoAtual][i] != ESTADO_INVALIDO)
				return true;
		
		return false;
	}
	
	/**
	 * Executa uma transi�‹o de estado baseada em chamada de subm‡quina.
	 * N‹o chama efetivamente outra subm‡quina, apenas verifica a possibilidade e muda para o estado de retorno.
	 * 
	 * @param idSubmaquina
	 * @return <code>true</code>, caso seja possivel chamar a subm‡quina. <code>false</code>, caso n‹o exista.
	 */
	public boolean chamarSubmaquina(int idSubmaquina) {
		int proximoEstado = this.tabelaChamadaSubmaquinas[this.estadoAtual][idSubmaquina];
		
		if(proximoEstado == ESTADO_INVALIDO)
			return false;
		
		this.estadoAtual = proximoEstado;
		return true;
	}
}