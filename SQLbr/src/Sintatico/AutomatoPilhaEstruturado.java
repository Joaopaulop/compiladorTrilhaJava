package compilador.analisador.sintatico;

import compilador.analisador.lexico.Token;
import compilador.analisador.semantico.AnalisadorSemantico;
import compilador.estruturas.ListaLigada;
import compilador.estruturas.Mapa;
import compilador.estruturas.Pilha;
import compilador.estruturas.String;
import compilador.gerador.submaquinas.GeradorSubmaquinas;
import compilador.helper.ArrayHelper;

public class AutomatoPilhaEstruturado {
	
	/**
	 * O nome da subm�quina inicial.
	 */
	public static final String SUBMAQUINA_INICIAL = new String("programa".toCharArray());
	
	/**
	 * Array com todas as subm�quinas do APE.
	 */
	private Submaquina[] submaquinas;
	
	/**
	 * Utilizado pelo <code>GeradorSubmaquinas</code>. Relaciona o nome da subm�quinha com seu identificador.
	 */
	private Mapa<String, Integer> mapa;
	
	/**
	 * A pilha que gerenciar� as transi��es entre subm�quinas.
	 */
	private Pilha<Par> pilha;
	
	/**
	 * A subm�quina que est� atualmente em execu��o.
	 */
	private Submaquina submaquinaAtual;
	
	/**
	 * O estado atual da subm�quina atualmente em execu��o.
	 */
	private int estadoAtual;
	
	/**
	 * O �ltimo erro sint�tico encontrado.
	 */
	private ErroSintatico ultimoErro;
	
	/**
	 * O analisador sem�ntico do compilador.
	 */
	private AnalisadorSemantico semantico;
	
	public AutomatoPilhaEstruturado() {
		this.inicializaSubmaquinas();
		
		this.submaquinaAtual = this.encontrarSubmaquina(AutomatoPilhaEstruturado.SUBMAQUINA_INICIAL);
		this.estadoAtual = this.submaquinaAtual.getEstadoInicial();
		
		this.pilha = new Pilha<Par>();
		
		// Inicializa o analisador sem�ntico.
		this.semantico = new AnalisadorSemantico();
	}
	
	/**
	 * Consume um token executando as transi��es necess�rias nas subm�quinas.
	 * 
	 * @param token o token a ser consumido.
	 */
	public void consumirToken(Token token) {
		this.semantico.executarAcaoSemantica(this.submaquinaAtual.getNome().toString(), this.estadoAtual, token);
		
		int statusTransicao = this.submaquinaAtual.transicao(token);
		
		if(statusTransicao == Submaquina.TRANSICAO_OK) {
			this.estadoAtual = this.submaquinaAtual.getEstadoAtual();
			
		} else if(statusTransicao == Submaquina.TRANSICAO_FALHOU) {
			// Se n�o conseguir chamar outra subm�quina, tenta retornar.
			
			if(!this.chamarSubmaquina(token)) {
				// Se n�o for poss�vel chamar outra subm�quina. 
				if(!this.retornarParaSubmaquina(token)) {
					// Se n�o for poss�vel retornar.
					
					this.ultimoErro = new ErroSintatico(token.getLinha(), token.getColuna(), token.getClasse());
				}
			}
		}
	}
	
	/**
	 * Localiza uma subm�quina a partir do seu nome.
	 * 
	 * @param nome
	 * @return
	 */
	private Submaquina encontrarSubmaquina(String nome) {
		for(int i = 0; i < this.submaquinas.length; i++) {
			if(this.submaquinas[i] != null && this.submaquinas[i].getNome().equals(nome))
				return this.submaquinas[i];
		}
		
		return null;
	}
	
	/**
	 * Chama uma subm�quina a partir da subm�quina atual.
	 * 
	 * @param token
	 * @return
	 */
	private boolean chamarSubmaquina(Token token) {
		boolean statusChamada = false;
		
		ListaLigada<Integer> possiveisSubmaquinas = this.submaquinaAtual.possiveisSubmaquinas(token);
		
		// Verfica as transi��es das subm�quinas com base no token.
		ListaLigada<Integer> submaquinasComTransicao = new ListaLigada<Integer>();
		for(int i = 0; i < possiveisSubmaquinas.tamanho(); i++)
			if(this.submaquinas[possiveisSubmaquinas.get(i)].haTransicao(token) || this.submaquinas[possiveisSubmaquinas.get(i)].haChamadaSubmaquina())
				submaquinasComTransicao.put(possiveisSubmaquinas.get(i));
		
		// TODO: chamar a subm�quina correta. Por enquanto enquanto a primeira � sempre chamada.
		Integer idSubmaquina = submaquinasComTransicao.get(0);
		if(idSubmaquina != null && this.submaquinaAtual.chamarSubmaquina(idSubmaquina)){
			// Empilha a situa��o atual.
			this.estadoAtual = this.submaquinaAtual.getEstadoAtual();
			Par par = new Par(this.submaquinaAtual, this.estadoAtual);
			this.pilha.push(par);
			
			// Chama a outra subm�quina.
			this.submaquinaAtual = this.submaquinas[idSubmaquina];
			this.submaquinaAtual.setEstadoAtual(this.submaquinaAtual.getEstadoInicial());
			this.estadoAtual = this.submaquinaAtual.getEstadoAtual();
			
			// Chama novamente o m�todo que consome o Token.
			this.consumirToken(token);
			statusChamada = true;
		}
		
		return statusChamada;
	}
	
	/**
	 * Faz um retorno a partir da subm�quina atual.
	 * 
	 * @param token
	 * @return
	 */
	private boolean retornarParaSubmaquina(Token token) {
		boolean statusRetorno = false;
		
		// Verifica se o estado atual � final.
		if(ArrayHelper.elementoNoVetor(this.submaquinaAtual.getEstadosFinais(), this.submaquinaAtual.getEstadoAtual())) {
			// Volta a subm�quina que terminou a execu��o para o estado inicial.
			this.submaquinaAtual.setEstadoAtual(this.submaquinaAtual.getEstadoInicial());
			
			// Desempilha a situa��o para a qual o retorno ser� feito.
			Par par = this.pilha.pop();
			this.submaquinaAtual = par.getSubmaquina();
			this.estadoAtual = par.getEstado();
			this.submaquinaAtual.setEstadoAtual(this.estadoAtual);
			
			// Chama novamente o m�todo que consome o Token.
			this.consumirToken(token);
			statusRetorno = true;
		}
		
		return statusRetorno;
	}
	
	/**
	 * Verifica se o APE est� no estado de aceita��o da subm�quina principal.
	 * 
	 * @return <code>true<code>, caso esteja no estado de aceita��o da subm�quina principal. <code>false</code>, caso contr�rio.
	 */
	public boolean estaNoEstadoAceitacao() {
		if(this.submaquinaAtual.getNome().equals(SUBMAQUINA_INICIAL) 
				&& ArrayHelper.elementoNoVetor(this.submaquinaAtual.getEstadosFinais(), this.submaquinaAtual.getEstadoAtual()))
			return true;
		
		return false;
	}
	
	/**
	 * Verifica se a pilha do APE est� vazia.
	 * 
	 * @return <code>true<code>, caso a pilha esteja vazia. <code>false</code>, caso contr�rio.
	 */
	public boolean pilhaVazia() {
		return this.pilha.vazia();
	}
	
	/**
	 * @return o �ltimo erro sint�tico encontrado.
	 */
	public ErroSintatico getUltimoErroSintatico() {
		ErroSintatico erro = this.ultimoErro;
		this.ultimoErro = null;
		return erro;
	}
	
	/**
	 * Inicializa as subm�quinas do APE.
	 */
	private void inicializaSubmaquinas() {
		this.mapa = new Mapa<String, Integer>();
		this.submaquinas = new Submaquina[10];
		int indice = 0;
		
		GeradorSubmaquinas geradorSubmaquinas = new GeradorSubmaquinas(this.mapa);
		Submaquina submaquina;
		
		geradorSubmaquinas.parseXML("src/compilador/config/programa.xml");
		submaquina = geradorSubmaquinas.gerarSubmaquina();
		this.submaquinas[indice] = submaquina;
		indice++;
		
		geradorSubmaquinas.parseXML("src/compilador/config/parametros.xml");
		submaquina = geradorSubmaquinas.gerarSubmaquina();
		this.submaquinas[indice] = submaquina;
		indice++;
		
		geradorSubmaquinas.parseXML("src/compilador/config/declaracoes.xml");
		submaquina = geradorSubmaquinas.gerarSubmaquina();
		this.submaquinas[indice] = submaquina;
		indice++;
		
		geradorSubmaquinas.parseXML("src/compilador/config/comandos.xml");
		submaquina = geradorSubmaquinas.gerarSubmaquina();
		this.submaquinas[indice] = submaquina;
		indice++;
		
		geradorSubmaquinas.parseXML("src/compilador/config/expressaoBooleana.xml");
		submaquina = geradorSubmaquinas.gerarSubmaquina();
		this.submaquinas[indice] = submaquina;
		indice++;
		
		geradorSubmaquinas.parseXML("src/compilador/config/expressao.xml");
		submaquina = geradorSubmaquinas.gerarSubmaquina();
		this.submaquinas[indice] = submaquina;
		indice++;
		
		geradorSubmaquinas.parseXML("src/compilador/config/texto.xml");
		submaquina = geradorSubmaquinas.gerarSubmaquina();
		this.submaquinas[indice] = submaquina;
		indice++;
		
		// Ordena o array de subm�quinas para poder ser indexador pelos valores do mapa de subm�quinas.;
		Submaquina[] temporario = new Submaquina[10]; 
		
		ListaLigada<String> chaves = this.mapa.chaves();
		for(int i = 0; i < chaves.tamanho(); i++) {
			String chave = chaves.get(i);
			
			for(int j = 0; j < this.submaquinas.length; j++)
				if(this.submaquinas[j] != null && this.submaquinas[j].getNome().equals(chave))
					temporario[mapa.get(chave).intValue()] = this.submaquinas[j];
		}
		
		this.submaquinas = temporario;
	}
	
}