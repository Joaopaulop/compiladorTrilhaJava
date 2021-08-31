/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladorprojeto;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import java_cup.runtime.Symbol;
import javax.swing.JFileChooser;

public class FrmPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form FrmPrincipal
     */
    public FrmPrincipal() {
        initComponents();
        this.setLocationRelativeTo(null);
    }
    
    private void analisarLexico() throws IOException{
        int cont = 1;
        
        String expr = (String) txtResultado.getText();
        Gramatica lexer = new Gramatica(new StringReader(expr));
        String resultado = "LINHA " + cont + "\t\tSIMBOLO\n";
        while (true) {
            Tokens token = lexer.yylex();
            if (token == null) {
                txtAnalisarLex.setText(resultado);
                return;
            }
            switch (token) {
                case LINHA:
                    cont++;
                    resultado += "Linha " + cont + "\n";
                    break;
                case ASPAS:
                    resultado += "  <Aspas>\t\t" + lexer.lexema + "\n";
                    break;
                case STRING:
                    resultado += "  <Tipo de dado>\t" + lexer.lexema + "\n";
                    break;
                case DADO:
                    resultado += "  <Tipo de dado>\t" + lexer.lexema + "\n";
                    break;
                case SE:
                    resultado += "  <Decisão Se>\t" + lexer.lexema + "\n";
                    break;
                case SENAO:
                    resultado += "  <Decisão Se-Senão>\t" + lexer.lexema + "\n";
                    break;
                case FACA:
                    resultado += "  <Loop Faça>\t" + lexer.lexema + "\n";
                    break;
                case ENQUANTO:
                    resultado += "  <Loop Enquanto>\t" + lexer.lexema + "\n";
                    break;
                case PARA:
                    resultado += "  <Loop Para>\t" + lexer.lexema + "\n";
                    break;
                case IGUAL:
                    resultado += "  <Operador igual>\t" + lexer.lexema + "\n";
                    break;
                case SOMA:
                    resultado += "  <Operador soma>\t" + lexer.lexema + "\n";
                    break;
                case SUB:
                    resultado += "  <Operador subtração>\t" + lexer.lexema + "\n";
                    break;
                case MULT:
                    resultado += "  <Operador multiplicação>\t" + lexer.lexema + "\n";
                    break;
                case DIV:
                    resultado += "  <Operador divisão>\t" + lexer.lexema + "\n";
                    break;
                case OPLOG:
                    resultado += "  <Operador lógico>\t" + lexer.lexema + "\n";
                    break;
                case OPINCR:
                    resultado += "  <Operador incremento>\t" + lexer.lexema + "\n";
                    break;
                case OPREL:
                    resultado += "  <Operador relacional>\t" + lexer.lexema + "\n";
                    break;
                case OPATRI:
                    resultado += "  <Operador atribuição>\t" + lexer.lexema + "\n";
                    break;
                case OPBOOL:
                    resultado += "  <Operador booleano>\t" + lexer.lexema + "\n";
                    break;
                case ABREPAR:
                    resultado += "  <Abre parênteses>\t" + lexer.lexema + "\n";
                    break;
                case FECHAPAR:
                    resultado += "  <Fecha parênteses>\t" + lexer.lexema + "\n";
                    break;
                case ABRECHA:
                    resultado += "  <Abre chaves>\t" + lexer.lexema + "\n";
                    break;
                case FECHACHA:
                    resultado += "  <Fecha chaves>\t" + lexer.lexema + "\n";
                    break;
                case PUBLICO:
                    resultado += "  <Public>\t" + lexer.lexema + "\n";
                    break;
                case ESTATICO:
                    resultado += "  <Static>\t" + lexer.lexema + "\n";
                    break;
                case VAZIO:
                    resultado += "  <Void>\t" + lexer.lexema + "\n";
                    break;
                case PRINCIPAL:
                    resultado += "  <Main>\t" + lexer.lexema + "\n";
                    break;
                case PONTOV:
                    resultado += "  <Ponto e vírgula>\t" + lexer.lexema + "\n";
                    break;
                case ID:
                    resultado += "  <Identificador>\t\t" + lexer.lexema + "\n";
                    break;
                case NUM:
                    resultado += "  <Numero>\t\t" + lexer.lexema + "\n";
                    break;
                case ERROR:
                    resultado += "  <Simbolo nao definido>\n";
                    break;
                default:
                    resultado += "  < " + lexer.lexema + " >\n";
                    break;
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnArquivo = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtResultado = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAnalisarLex = new javax.swing.JTextArea();
        btnAnalisarLex = new javax.swing.JButton();
        btnLimparLex = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtAnalisarSin = new javax.swing.JTextArea();
        btnAnalisarSin = new javax.swing.JButton();
        btnLimparSin = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Analisador Lexico", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        btnArquivo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnArquivo.setText("Abrir arquivo");
        btnArquivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnArquivoActionPerformed(evt);
            }
        });

        txtResultado.setColumns(20);
        txtResultado.setRows(5);
        jScrollPane1.setViewportView(txtResultado);

        txtAnalisarLex.setEditable(false);
        txtAnalisarLex.setColumns(20);
        txtAnalisarLex.setRows(5);
        jScrollPane2.setViewportView(txtAnalisarLex);

        btnAnalisarLex.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnAnalisarLex.setText("Analisar");
        btnAnalisarLex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnalisarLexActionPerformed(evt);
            }
        });

        btnLimparLex.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnLimparLex.setText("Limpar");
        btnLimparLex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparLexActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 236, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnAnalisarLex)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLimparLex))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(386, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnArquivo)
                    .addComponent(btnAnalisarLex)
                    .addComponent(btnLimparLex))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addGap(0, 50, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Analisador Sintático", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        txtAnalisarSin.setEditable(false);
        txtAnalisarSin.setColumns(20);
        txtAnalisarSin.setRows(5);
        jScrollPane3.setViewportView(txtAnalisarSin);

        btnAnalisarSin.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnAnalisarSin.setText("Analisar");
        btnAnalisarSin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnalisarSinActionPerformed(evt);
            }
        });

        btnLimparSin.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnLimparSin.setText("Limpar");
        btnLimparSin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparSinActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnAnalisarSin)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLimparSin))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAnalisarSin)
                    .addComponent(btnLimparSin))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnArquivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnArquivoActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File archivo = new File(chooser.getSelectedFile().getAbsolutePath());
        
        try {
            String ST = new String(Files.readAllBytes(archivo.toPath()));
            txtResultado.setText(ST);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnArquivoActionPerformed

    private void btnLimparLexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparLexActionPerformed
        // TODO add your handling code here:
        txtAnalisarLex.setText(null);
    }//GEN-LAST:event_btnLimparLexActionPerformed

    private void btnLimparSinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparSinActionPerformed
        // TODO add your handling code here:
        txtAnalisarSin.setText(null);
    }//GEN-LAST:event_btnLimparSinActionPerformed

    private void btnAnalisarLexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnalisarLexActionPerformed
        try {
            analisarLexico();
        } catch (IOException ex) {
            Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAnalisarLexActionPerformed

    private void btnAnalisarSinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnalisarSinActionPerformed
        // TODO add your handling code here:
        String ST = txtResultado.getText();
        Sintaxe s = new Sintaxe(new compiladorprojeto.GramaticaCup(new StringReader(ST)));
        
        try {
            s.parse();
            txtAnalisarSin.setText("Analise realizada corretamente");
            txtAnalisarSin.setForeground(new Color(25, 111, 61));
        } catch (Exception ex) {
            Symbol sym = s.getS();
            txtAnalisarSin.setText("Erro de sintaxe. LINHA: " + (sym.right + 1) + " Columna: " + (sym.left + 1) + ", Texto: \"" + sym.value + "\"");
            txtAnalisarSin.setForeground(Color.red);
        }
    }//GEN-LAST:event_btnAnalisarSinActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnalisarLex;
    private javax.swing.JButton btnAnalisarSin;
    private javax.swing.JButton btnArquivo;
    private javax.swing.JButton btnLimparLex;
    private javax.swing.JButton btnLimparSin;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea txtAnalisarLex;
    private javax.swing.JTextArea txtAnalisarSin;
    private javax.swing.JTextArea txtResultado;
    // End of variables declaration//GEN-END:variables
}
