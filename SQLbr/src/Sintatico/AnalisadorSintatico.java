package Sintatico;

import java.io.IOException;

import Lexico.GeraLexer;
// import Lexico.Lexer;

/*
import compilador.analisador.lexico.AnalisadorLexico;
import compilador.analisador.lexico.Token;
import compilador.analisador.semantico.AcoesSemanticas;
import compilador.analisador.semantico.AnalisadorSemantico;
import compilador.analisador.semantico.Escopos;
import compilador.analisador.semantico.TabelaSimbolos;
import compilador.estruturas.ListaLigada;
*/

public class AnalisadorSintatico {
	
	/**
	 * O aut™mato de pilha estruturado do analisador sint‡tico.
	 */
	private AutomatoPilhaEstruturado ape;
	
	/**
	 * O analisador lŽxico do compilador.
	 */
	private AnalisadorLexico lexico;
	
	/**
	 * Lista que armazena os erros sint‡ticos do c—digo-fonte.
	 */
	private ListaLigada<ErroSintatico> erros;
	
	public AnalisadorSintatico() {
		// Inicializa o analisador lŽxico.
		this.lexico = new AnalisadorLexico();
		this.lexico.carregarCodigoFonte("src/compilador/testes/source.ling");
		
		// Inicializa o APE.
		this.ape = new AutomatoPilhaEstruturado();
		
		// Inicializa a lista de erros sint‡ticos.
		this.erros = new ListaLigada<ErroSintatico>();
		
		// Inicializa a primeira tabela de s’mbolos.
		AcoesSemanticas.criarEscopo();
	}
	
	/**
	 * Processa o c—digo fonte.
	 * 
	 * @return <code>true</code> caso o c—digo-fonte esteja sintaticamente correto. <code>false</code> caso contr‡rio.
	 */
	public boolean processarCodigoFonte() { 
		try{
			Token token;
			
			while(lexico.haMaisTokens()) {
				token = lexico.proximoToken();
				AnalisadorSemantico.armazenarToken(token);
				this.ape.consumirToken(token);
				
				// Verifica se houve erro sint‡tico no aut™mato de pilha.
				ErroSintatico erro = this.ape.getUltimoErroSintatico(); 
				if(erro != null)
					this.erros.put(erro);
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		// Condi�‹o para que o c—digo esteja sintaticamente correto.
		if(this.ape.estaNoEstadoAceitacao() && this.ape.pilhaVazia() && this.erros.vazia())
			return true;
		
		// Imprime os erros encontrados.
		for(int i = this.erros.tamanho() - 1; i >= 0; i--) {
			System.out.print("\nToken ");
			Token.getClasseTokenString(this.erros.get(i).getClasseToken()).imprimir();
			System.out.println(" inesperado. Linha " + this.erros.get(i).getLinha() + " - Coluna: " + this.erros.get(i).getColuna());
		}
		
		return false;
	}
	
	public static void main(String[] args) {
		AnalisadorSintatico sintatico = new AnalisadorSintatico();
		boolean resultado = sintatico.processarCodigoFonte();
		
//		if(resultado)
//			System.out.println("\nRESULTADO: O programa esta sintaticamente correto.");
//		else
//			System.out.println("\nRESULTADO: O programa nao esta sintaticamente correto.");
	}
}