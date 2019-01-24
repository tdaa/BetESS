/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package betess.presentation;

import betess.business.Aposta;
import betess.business.BetESS;
import betess.business.Evento;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author tiagoalves
 */
public class JDialogNotificacoes extends javax.swing.JDialog {
    
    private BetESS betEss;
    private ArrayList<Evento> eventos;

    /**
     * Creates new form JDialogNotificacoes
     */
    public JDialogNotificacoes(java.awt.Frame parent, boolean modal, BetESS b) {
        super(parent, modal);
        initComponents();
        this.betEss = b;
        this.eventos = new ArrayList<>();
        
        setUp();
    }
    
    private void setUp(){
        DefaultTableModel model = (DefaultTableModel) this.jTableEventos.getModel();
        
        Map<Integer, Boolean> vistos = this.betEss.getApostadores()
                                                  .get(this.betEss.getUser())
                                                  .getVistos();
        
        Collection<Aposta> apostas = this.betEss.getApostasUser(this.betEss.getUser());
        
        for(Aposta a: apostas){
            for(Evento e: a.getEventos().values()){
                if(!vistos.get(e.getIdEvento()) && e.getEstado().equals("FECHADO")){
                    
                    this.eventos.add(e);
                    
                    model.addRow(new Object[] {
                        e.getIdEvento(),
                        e.getEquipaUm(),
                        e.getEquipaDois(),
                        e.getResultado()
                    });
                    
                }
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableEventos = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        okButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(36, 47, 65));

        jTableEventos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Evento", "Equipa Casa", "Equipa Fora", "Resultado"
            }
        ));
        jScrollPane1.setViewportView(jTableEventos);

        jLabel1.setFont(new java.awt.Font("Avenir", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Notificações");

        okButton.setForeground(new java.awt.Color(102, 102, 102));
        okButton.setText("OK");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(okButton)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(114, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(43, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(55, 55, 55)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(okButton)
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        // TODO add your handling code here:
        Map<Integer, Boolean> vistos = this.betEss.getApostadores()
                                                  .get(this.betEss.getUser())
                                                  .getVistos();
        
        for(Evento e: this.eventos){
            vistos.put(e.getIdEvento(), true);
        }
        this.betEss.getApostadores()
                   .get(this.betEss.getUser())
                   .setVistos(vistos);
        
        this.dispose();
    }//GEN-LAST:event_okButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableEventos;
    private javax.swing.JButton okButton;
    // End of variables declaration//GEN-END:variables
}