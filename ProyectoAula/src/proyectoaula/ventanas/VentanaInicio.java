package proyectoaula.ventanas;
import javax.swing.JOptionPane;
import proyectoaula.objects.Usuario;
public class VentanaInicio extends javax.swing.JFrame {
    Usuario usuario = new Usuario();
    public VentanaInicio() {
        initComponents();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtCedula = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        botonEntrar = new javax.swing.JButton();
        txtContraseña1 = new javax.swing.JPasswordField();
        Registrarse = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        Cerrar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        jPanel2.setBackground(new java.awt.Color(204, 255, 204));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel3.setText("Contraseña:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 100, -1, 30));

        txtCedula.setFont(new java.awt.Font("Segoe UI Symbol", 0, 14)); // NOI18N
        txtCedula.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.add(txtCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 40, 150, 28));

        jLabel5.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel5.setText("Nro.documento:");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, 130, 30));

        botonEntrar.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        botonEntrar.setText("ENTRAR");
        botonEntrar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        botonEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEntrarActionPerformed(evt);
            }
        });
        jPanel2.add(botonEntrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 160, 100, 30));
        jPanel2.add(txtContraseña1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 100, 150, 31));

        Registrarse.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        Registrarse.setText("Registrate aquí");
        Registrarse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegistrarseActionPerformed(evt);
            }
        });
        jPanel2.add(Registrarse, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 230, -1, 30));

        jLabel6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel6.setText("¿No está registrado?");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 230, 180, 30));

        Cerrar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        Cerrar.setText("Cerrar");
        Cerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CerrarActionPerformed(evt);
            }
        });
        jPanel2.add(Cerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 280, 80, 30));

        jLabel1.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 36)); // NOI18N
        jLabel1.setText("ECO-POWER");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(142, 142, 142)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void botonEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEntrarActionPerformed
    String cedula; 
    String contrasena;
    contrasena = txtCedula.getText();
    cedula = txtContraseña1.getText();
    if(contrasena == null || cedula==null){
        JOptionPane.showMessageDialog(null, "Falta ingresar datos", "Error", JOptionPane.ERROR_MESSAGE);
    }else if(txtCedula.getText().equals(usuario.contraseña) && txtContraseña1.getText().equals(usuario.nroDocumento)) {
    Ventana abc = new Ventana();
    abc.setVisible(true);
   
    }
    }//GEN-LAST:event_botonEntrarActionPerformed

    private void RegistrarseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegistrarseActionPerformed
        RegistrarUsuario abcd = new RegistrarUsuario();
        abcd.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_RegistrarseActionPerformed

    private void CerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CerrarActionPerformed
        this.dispose();
    }//GEN-LAST:event_CerrarActionPerformed

    public static void main(String[] args) {
        
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
            java.util.logging.Logger.getLogger(VentanaInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaInicio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cerrar;
    private javax.swing.JButton Registrarse;
    private javax.swing.JButton botonEntrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JPasswordField txtContraseña1;
    // End of variables declaration//GEN-END:variables
}
