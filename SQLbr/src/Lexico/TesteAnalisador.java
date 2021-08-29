package Lexico;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
public class TesteAnalisador {
    public static void main(String[] args) throws IOException {                
        String arq1 = "C:\\Users\\João Paulo\\Desktop\\compiladores\\trabalho compilador\\SQLbr\\gramatica.flex";
        String arq2 = "C:\\Users\\João Paulo\\Desktop\\compiladores\\trabalho compilador\\SQLbr\\LexerCup.flex";
        String[] arqS = {"-parse", "Sintax","C:\\Users\\João Paulo\\Desktop\\compiladores\\trabalho compilador\\SQLbr\\src\\Lexico\\Sintaxe.cup"};
        gerar(arq1, arq2, arqS);
    }
    
    public static void gerar(String arq1, String arq2, String[]arqS) {
    	File arquivo;
    	arquivo = new File(arq1);
    	JFlex.Main.generate(arquivo);
    	arquivo = new File(arq2);
    	JFlex.Main.generate(arquivo);  
    	java_cup.Main.main(arqS);
    	
    	Path arqS1 = Paths.get("C:\\Users\\João Paulo\\Desktop\\compiladores\\trabalho compilador\\SQLbr\\src\\Lexico\\sym.java");
    	if(Files.exists(arqS1)) {
    		Files.delete(arqS1);
    	}
    	Files.move(
    			Paths.get("C:\\Users\\João Paulo\\Desktop\\compiladores\\trabalho compilador\\SQLbr\\sym.java"),
    			Paths.get("C:\\Users\\João Paulo\\Desktop\\compiladores\\trabalho compilador\\SQLbr\\src\\Lexico\\sym.java")
    			);   
    	
    	Path arqS2 = Paths.get("C:\\Users\\João Paulo\\Desktop\\compiladores\\trabalho compilador\\SQLbr\\src\\Lexico\\Sintaxe.java");
    	    	if(Files.exists(arqS2)) {
    	    		Files.delete(arqS2);
    	    	}
    	Files.move(
    			Paths.get("C:\\Users\\João Paulo\\Desktop\\compiladores\\trabalho compilador\\SQLbr\\Sintaxe.java"),
    			Paths.get("C:\\Users\\João Paulo\\Desktop\\compiladores\\trabalho compilador\\SQLbr\\src\\Lexico\\Sintaxe.java")
    			); 
    }
}
