/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladorprojeto;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author vicemc
 */
public class Main {

    public static void main(String[] args) throws Exception {
        String pathA = "/home/vicemc/NetBeansProjects/CompiladorProjeto/src/compiladorprojeto/Gramatica.flex";
        String pathB = "/home/vicemc/NetBeansProjects/CompiladorProjeto/src/compiladorprojeto/GramaticaCup.flex";
        String[] pathS = {"-parser", "Sintaxe","/home/vicemc/NetBeansProjects/CompiladorProjeto/src/compiladorprojeto/Sintaxe.cup"};
        gerar(pathA, pathB, pathS);
    }
    public static void gerar(String pathA, String pathB, String[] pathS) throws IOException, Exception {
        File arq;
        arq = new File (pathA);
        jflex.Main.generate(arq);
        arq = new File (pathB);
        jflex.Main.generate(arq);
        java_cup.Main.main(pathS);
        
        Path pathSym = Paths.get("home/vicemc/NetBeansProjects/CompiladorProjeto/src/compiladorprojeto/sym.java");
        if (Files.exists(pathSym)) {
            Files.delete(pathSym);
        }
        Files.move(
                Paths.get("home/vicemc/NetBeansProjects/CompiladorProjeto/sym.java"),
                Paths.get("home/vicemc/NetBeansProjects/CompiladorProjeto/src/compiladorprojeto/sym.java")
        );
        
        Path pathSint = Paths.get("home/vicemc/NetBeansProjects/CompiladorProjeto/src/compiladorprojeto/Sintaxe.java");

        if (Files.exists(pathSint)) {
            Files.delete(pathSint);
        }
        Files.move(
                Paths.get("home/vicemc/NetBeansProjects/CompiladorProjeto/Sintaxe.java"),
                Paths.get("home/vicemc/NetBeansProjects/CompiladorProjeto/src/compiladorprojeto/Sintaxe.java")
        );
        
    }
    
}
/**
 *
 * @author joao
 */
 /*
public class Main {

    public static void main(String[] args) throws Exception {
        String pathA = "~/Documents/CompiladorProjeto/src/compiladorprojeto/Gramatica.flex";
        String pathB = "~/Documents/CompiladorProjeto/src/compiladorprojeto/GramaticaCup.flex";
        String[] pathS = {"-parser", "Sintaxe","~/Documents/CompiladorProjeto/src/compiladorprojeto/Sintaxe.cup"};
        gerar(pathA, pathB, pathS);
    }
    public static void gerar(String pathA, String pathB, String[] pathS) throws IOException, Exception {
        File arq;
        arq = new File (pathA);
        jflex.Main.generate(arq);
        arq = new File (pathB);
        jflex.Main.generate(arq);
        java_cup.Main.main(pathS);
        
        Path pathSym = Paths.get("~/Documents/CompiladorProjeto/src/compiladorprojeto/sym.java");
        if (Files.exists(pathSym)) {
            Files.delete(pathSym);
        }
        Files.move(
                Paths.get("~/Documents/CompiladorProjeto/sym.java"),
                Paths.get("~/Documents/CompiladorProjeto/src/compiladorprojeto/sym.java")
        );
        
        Path pathSint = Paths.get("~/Documents/CompiladorProjeto/src/compiladorprojeto/Sintaxe.java");

        if (Files.exists(pathSint)) {
            Files.delete(pathSint);
        }
        Files.move(
                Paths.get("~/Documents/CompiladorProjeto/Sintaxe.java"),
                Paths.get("~/Documents/CompiladorProjeto/src/compiladorprojeto/Sintaxe.java")
        );
        
    }
    
} */