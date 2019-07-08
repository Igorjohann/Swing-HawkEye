/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import database.Database;
import menu.*;

import java.awt.*;

public class TelaCadastroMaquina extends javax.swing.JFrame {

    public TelaCadastroMaquina() {
        initComponents();
        centralizarComponente();

    }
    Database db = new Database();

    public void centralizarComponente() {
        Dimension ds = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension dw = getSize();
        setLocation((ds.width - dw.width) / 2, (ds.height - dw.height) / 2);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        textFieldName = new javax.swing.JTextField();
        textFieldSector = new javax.swing.JTextField();
        textFieldOperationalSystem = new javax.swing.JTextField();
        textFieldAlertaCPU = new javax.swing.JTextField();
        textFieldAlertaRAM = new javax.swing.JTextField();
        textFieldAlertaDisk = new javax.swing.JTextField();
        labelName = new javax.swing.JLabel();
        labelOperationalSystem = new javax.swing.JLabel();
        labelSector = new javax.swing.JLabel();
        labelAlertaCPU = new javax.swing.JLabel();
        labelAlertaDisk = new javax.swing.JLabel();
        labelAlertaRAM = new javax.swing.JLabel();
        setFinalizarCadastro(new javax.swing.JButton());
        jPanel4 = new javax.swing.JPanel();

        textFieldOperationalSystem.setEditable(false);
        textFieldOperationalSystem.setText(Init.os.toString());


        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 51, 102));
        jPanel1.setToolTipText("");
        jPanel1.setAutoscrolls(true);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Cadastrar Servidor");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(6, 6, 6))
        );

        textFieldOperationalSystem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldOperationalSystemActionPerformed(evt);
            }
        });

        labelName.setText("NOME SERVIDOR");

        labelOperationalSystem.setText("SISTEMA OPERACIONAL ");

        labelSector.setText("LOCALIZAÇÃO");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        labelAlertaCPU.setText("ALERTA CPU");

        labelAlertaDisk.setText("ALERTA DISCO");

        labelAlertaRAM.setText("ALERTA MEMORIA");

        textFieldAlertaDisk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                discoActionPerformed(evt);
            }
        });

        getFinalizarCadastro().setBackground(new java.awt.Color(58, 65, 84));
        getFinalizarCadastro().setForeground(new java.awt.Color(255, 255, 255));
        getFinalizarCadastro().setText("FINALIZAR CADASTRO");
        getFinalizarCadastro().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                finalizarCadastroMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                finalizarCadastroMouseExited(evt);
            }
        });
        getFinalizarCadastro().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OnClick_Finalizar(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(textFieldAlertaRAM, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(324, 324, 324)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(74, 74, 74))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(labelAlertaCPU, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelSector, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelOperationalSystem, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelName, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textFieldAlertaCPU, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textFieldSector, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textFieldName, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textFieldOperationalSystem, javax.swing.GroupLayout.Alignment.LEADING))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelAlertaRAM)
                            .addComponent(textFieldAlertaDisk, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelAlertaDisk))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(getFinalizarCadastro())
                .addGap(217, 217, 217))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(labelOperationalSystem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textFieldOperationalSystem, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(labelSector)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textFieldSector, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(labelAlertaCPU)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textFieldAlertaCPU, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelAlertaDisk)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textFieldAlertaDisk, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelAlertaRAM)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textFieldAlertaRAM, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(70, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(getFinalizarCadastro())))
                        .addContainerGap())))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 489, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void OnClick_Finalizar(java.awt.event.ActionEvent evt) {
        try {
            db.setSistemaOperacional(textFieldOperationalSystem.getText());
            db.setNomeMaquina(textFieldName.getText());
            db.setSetor(textFieldSector.getText());
            db.setAlertaCpu(textFieldAlertaCPU.getText());
            db.setAlertaRam(textFieldAlertaRAM.getText());
            db.setAlertaDisco(textFieldAlertaDisk.getText());
            db.insertMaquina(getIdEmpresa(), System.getProperty("user.name"));

            MonitoramentoLocal monitoring = new MonitoramentoLocal();
            monitoring.setIdMaquina(db.selectMaquina1(getIdEmpresa(), System.getProperty("user.name")));
            this.setVisible(false);
            monitoring.setVisible(true);

        }catch (Exception e){

        }

    }


    private void finalizarCadastroMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_finalizarCadastroMouseEntered
        getFinalizarCadastro().setBackground(new Color(235, 235, 235));
        getFinalizarCadastro().setForeground(new Color(235, 235, 235));
    }//GEN-LAST:event_finalizarCadastroMouseEntered

    private void finalizarCadastroMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_finalizarCadastroMouseExited
        getFinalizarCadastro().setBackground(new Color(58,65,84));
        getFinalizarCadastro().setForeground(Color.WHITE);
    }//GEN-LAST:event_finalizarCadastroMouseExited

//    private void finalizarCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finalizarCadastroActionPerformed
//        // TODO add your handling code here:
//        OnClick_Finalizar(evt);
//    }//GEN-LAST:event_finalizarCadastroActionPerformed

    private void textFieldOperationalSystemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldOperationalSystemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldOperationalSystemActionPerformed

    private void discoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_discoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_discoActionPerformed

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

        } catch (InstantiationException ex) {

        } catch (IllegalAccessException ex) {

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {

        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCadastroMaquina().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel labelAlertaCPU;
    private javax.swing.JLabel labelName;
    private javax.swing.JLabel labelOperationalSystem;
    private javax.swing.JTextField textFieldOperationalSystem;
    private javax.swing.JTextField textFieldAlertaCPU;
    private javax.swing.JLabel labelAlertaDisk;
    private javax.swing.JTextField textFieldAlertaRAM;
    private javax.swing.JLabel labelAlertaRAM;
    private javax.swing.JTextField textFieldAlertaDisk;
    private javax.swing.JButton finalizarCadastro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField textFieldName;
    private javax.swing.JTextField textFieldSector;
    private javax.swing.JLabel labelSector;
    private String idEmpresa;

    public javax.swing.JButton getFinalizarCadastro() {
        return finalizarCadastro;
    }

    public void setFinalizarCadastro(javax.swing.JButton finalizarCadastro) {
        this.finalizarCadastro = finalizarCadastro;
    }

    public String getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(String idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    // End of variables declaration//GEN-END:variables
}
