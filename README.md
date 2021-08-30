# compiladorTrilhaJava

# Projeto de Compiladores

Esse projeto visa a criação de um analisador léxico e sintático na linguagem JAVA, utilizando as ferramentas JFlex e Java Cup para a matéria de Compiladores do Instituto Federal de Brasília, sob tutela do professor Roberto Duarte Fontes. Realizado por Victor Maciel Clímaco e João Paulo Euzébio, utilizando os materiais dispostos na disciplina e vídeos tutoriais externos como base teórica.

# Notas relevantes
O programa é dependente do uso de caminhos do sistema, então, é imperativo a mudança do caminho para o sistema utilizado quando executado em outra máquina.

# Como usar
Executa-se primeiro a Main.java, copia a versão nova dos arquivos Sintaxe.java e sym.java da pasta /compiladorprojeto/ para /src/compiladorprojeto/ e então executa-se a FrmPrincipal.java.

# Ferramentas 
Para a realização do projeto, foram utilizados:
- Apache Netbeans 12.4
- JDK 16
- JFlex 1.7.0
- Java Cup 11b
- Linux Mint 20
- VMware Workstation 16 Player
- Ubuntu 20.04.3 LTS

Não se é conhecida a viabilidade em outros sistemas operacionais ou IDEs. É imperativo que o JFlex seja versão 1.7.0 e que o Apache Netbeans seja versão 12.4. 

# Problemas Conhecidos
- Por razões não compreendidas pelos alunos, o Analisador não funciona na versão 1.8.2 do JFlex, em qualquer versão da IDE Eclipse e nem na versão 10.0 da IDE Apache Netbeans. 
- Por razões igualmente não compreendidas pelos alunos, o analisador exige que o programa termine na última linha da sentença, acusando um erro de sintaxe caso a linha seja pulada. Em algumas ocasiões, ele não acusa o erro, e por causa da arbitrariedade, não encontramos ao certo onde estaria o problema.

# Histórico
Anteriormente, o projeto se tratava de uma continuação do projeto proposto nas unidades do livro, em SQLBr. Porém, devido a complicações com a execução do projeto, os alunos resolveram mudar o rumo para uma versão simplificada da linguagem C. 
