package proyectoaula.ventanas;
public class VentanaGastos extends javax.swing.JFrame {
    public VentanaGastos() {
        initComponents();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        background = new javax.swing.JPanel();
        jLabelIcon = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButtonCalcular = new javax.swing.JButton();
        jButtonGuardar = new javax.swing.JButton();
        gastosTXT = new javax.swing.JTextField();
        RegresarVentana = new javax.swing.JButton();
        fechaTXT = new javax.swing.JTextField();
        numeroserieTXT = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        background.setBackground(new java.awt.Color(220, 220, 220));
        background.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectoaula/imagenes/electronica (1).png"))); // NOI18N
        background.add(jLabelIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 130, 80, 110));

        jLabel2.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 14)); // NOI18N
        jLabel2.setText("ELECTRODOMESTICOS");
        background.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 190, 100));

        jLabel4.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 14)); // NOI18N
        jLabel4.setText("ECO-POWER");
        background.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 200, 140, 120));

        jLabel5.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 18)); // NOI18N
        jLabel5.setText("Gastos de electrodomesticos");
        jLabel5.setFocusable(false);
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        background.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 50, 310, 20));

        jTable1.setBackground(new java.awt.Color(204, 204, 204));
        jTable1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102), 3));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Electrodomesticos", "Gasto", "Nro. de serie", "Marca", "Fecha"
            }
        ));
        jTable1.setInheritsPopupMenu(true);
        jTable1.setSelectionBackground(new java.awt.Color(0, 102, 204));
        jScrollPane1.setViewportView(jTable1);

        background.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 100, 540, 260));

        jButtonCalcular.setBackground(new java.awt.Color(0, 0, 0));
        jButtonCalcular.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectoaula/imagenes/calcular.png"))); // NOI18N
        jButtonCalcular.setContentAreaFilled(false);
        jButtonCalcular.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonCalcular.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectoaula/imagenes/calcular.png"))); // NOI18N
        jButtonCalcular.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectoaula/imagenes/calcular1.png"))); // NOI18N
        jButtonCalcular.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonCalcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCalcularActionPerformed(evt);
            }
        });
        background.add(jButtonCalcular, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 370, 110, 80));

        jButtonGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectoaula/imagenes/guardar.png"))); // NOI18N
        jButtonGuardar.setContentAreaFilled(false);
        jButtonGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonGuardar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectoaula/imagenes/guardar.png"))); // NOI18N
        jButtonGuardar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectoaula/imagenes/guardar2.png"))); // NOI18N
        jButtonGuardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        background.add(jButtonGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 370, 100, 80));

        gastosTXT.setBackground(new java.awt.Color(204, 204, 204));
        background.add(gastosTXT, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 410, 190, -1));

        RegresarVentana.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        RegresarVentana.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectoaula/imagenes/atras.png"))); // NOI18N
        RegresarVentana.setText("Regresar");
        RegresarVentana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegresarVentanaActionPerformed(evt);
            }
        });
        background.add(RegresarVentana, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 470, 140, 40));

        fechaTXT.setBackground(new java.awt.Color(204, 204, 204));
        background.add(fechaTXT, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 440, 190, -1));

        numeroserieTXT.setBackground(new java.awt.Color(204, 204, 204));
        background.add(numeroserieTXT, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 380, 190, -1));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setText("nro.Serie:");
        background.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 380, -1, -1));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setText("Gasto:");
        background.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 410, -1, -1));

        jLabel7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel7.setText("Fecha/Hora:");
        background.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 440, -1, -1));

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 220, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 580, Short.MAX_VALUE)
        );

        background.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, 580));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, 840, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCalcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCalcularActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonCalcularActionPerformed

    private void RegresarVentanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegresarVentanaActionPerformed
        Ventana abc = new Ventana();
        abc.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_RegresarVentanaActionPerformed

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
            java.util.logging.Logger.getLogger(VentanaGastos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaGastos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaGastos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaGastos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaGastos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton RegresarVentana;
    private javax.swing.JPanel background;
    private javax.swing.JTextField fechaTXT;
    private javax.swing.JTextField gastosTXT;
    private javax.swing.JButton jButtonCalcular;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabelIcon;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField numeroserieTXT;
    // End of variables declaration//GEN-END:variables
}
