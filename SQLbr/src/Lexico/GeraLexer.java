package Lexico;
import java.io.File;
import java.io.IOException;

public class GeraLexer {
     public static void main(String[] args) throws IOException {  
        String Path = "C:\\Users\\João Paulo\\Desktop\\compiladores\\trabalho compilador\\SQLbr\\src\\Lexico\\gramatica.flex";
        File arq = new File(Path);
        jflex.Main.generate(arq);
        
     }
}