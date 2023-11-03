package proyectoaula.ventanas;

public class Ventana extends javax.swing.JFrame {

    public Ventana() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItem2 = new javax.swing.JRadioButtonMenuItem();
        jPanel1 = new javax.swing.JPanel();
        botonGastos = new javax.swing.JButton();
        botonElectrodomestico = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        RegresarInicio = new javax.swing.JButton();

        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("jRadioButtonMenuItem1");

        jRadioButtonMenuItem2.setSelected(true);
        jRadioButtonMenuItem2.setText("jRadioButtonMenuItem2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        botonGastos.setFont(new java.awt.Font("Maiandra GD", 0, 14)); // NOI18N
        botonGastos.setText("Gastos");
        botonGastos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGastosActionPerformed(evt);
            }
        });
        jPanel1.add(botonGastos, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 100, 86, 42));

        botonElectrodomestico.setFont(new java.awt.Font("Microsoft YaHei", 0, 14)); // NOI18N
        botonElectrodomestico.setText("Electrodomésticos");
        botonElectrodomestico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonElectrodomesticoActionPerformed(evt);
            }
        });
        jPanel1.add(botonElectrodomestico, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, 42));

        jLabel1.setFont(new java.awt.Font("MS Gothic", 0, 18)); // NOI18N
        jLabel1.setText("Consultar/Registrar");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, -1, -1));

        RegresarInicio.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        RegresarInicio.setText("Regresar");
        RegresarInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegresarInicioActionPerformed(evt);
            }
        });
        jPanel1.add(RegresarInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 210, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonGastosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGastosActionPerformed
        VentanaGastos abc = new VentanaGastos();
        abc.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_botonGastosActionPerformed

    private void botonElectrodomesticoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonElectrodomesticoActionPerformed
        VentanaElectrodomestico abcd = new VentanaElectrodomestico();
        abcd.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_botonElectrodomesticoActionPerformed

    private void RegresarInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegresarInicioActionPerformed
        VentanaLogin abc = new VentanaLogin();
        abc.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_RegresarInicioActionPerformed

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
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton RegresarInicio;
    private javax.swing.JButton botonElectrodomestico;
    private javax.swing.JButton botonGastos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem2;
    // End of variables declaration//GEN-END:variables
}
