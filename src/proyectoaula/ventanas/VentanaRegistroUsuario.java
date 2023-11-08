package proyectoaula.ventanas;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import javax.swing.JOptionPane;

public class VentanaRegistroUsuario extends javax.swing.JDialog {
   String usu= File.separator;
    String crearblock = System.getProperty("user.dir") + usu + "UsuariosBD" + usu;
  
    int c = 0;

    public VentanaRegistroUsuario(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
    
    //Crear base de datos
    private void crearUsuarios() {
        String archivo = txtCedula.getText() + ".txt";
        File crearubi = new File(crearblock);
        File creararchivo = new File(crearblock + archivo);

        if (txtCedula.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Este usuario no existe.");

        } else {
            try {
                if (creararchivo.exists()) {
                    JOptionPane.showMessageDialog(rootPane, "Este usuario ya está registrado.");
                } else {
                    crearubi.mkdirs();
                    creararchivo.createNewFile();
                    Writer escritorDeArchivo = new FileWriter(creararchivo.getAbsolutePath());
                    String datosElectrodomestico = "Cedula: "+  txtCedula.getText()+"\n";
                    datosElectrodomestico += "Nombre: " + txtNombre.getText()+"\n";
                    datosElectrodomestico +=  "Apellido: " + txtApellido.getText()+"\n";
                    datosElectrodomestico +=  "Télefono: " + txtTelefono.getText()+"\n";
                    datosElectrodomestico +=  "Email: " + txtEmail.getText()+"\n";
                    datosElectrodomestico +=  "Contraseña: " + txtContraseña.getText()+"\n";
                    escritorDeArchivo.write(datosElectrodomestico);
                    escritorDeArchivo.flush();
                    escritorDeArchivo.close();
                    JOptionPane.showMessageDialog(rootPane, "¡El usuario ha sido registrado con éxito!");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "No se pudo registrar el usuario.", "Error", JOptionPane.ERROR_MESSAGE);
          
            }
            crearElectrodomesticos(txtNombre.getText(), txtCedula.getText());
        }

    }
     // método para limpiar campos
     public void limpiarCampos() {
        txtNombre.setText("");
        txtApellido.setText("");
        txtEmail.setText(""); 
        txtTelefono.setText("");
        txtCedula.setText("");
        txtContraseña.setText("");     
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        telefono = new javax.swing.JLabel();
        txtContraseña = new javax.swing.JTextField();
        contraseña = new javax.swing.JLabel();
        nombre = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        apellido = new javax.swing.JLabel();
        txtApellido = new javax.swing.JTextField();
        cedula = new javax.swing.JLabel();
        txtCedula = new javax.swing.JTextField();
        email = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        botonGuardar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        botonBuscar = new javax.swing.JButton();
        botonEditar = new javax.swing.JButton();
        botonEliminar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 204), 2), "Datos de Usuario", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 18))); // NOI18N

        telefono.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        telefono.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        telefono.setText("Télefono:");

        txtContraseña.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtContraseña.setAlignmentX(5.0F);
        txtContraseña.setMinimumSize(new java.awt.Dimension(60, 23));
        txtContraseña.setPreferredSize(new java.awt.Dimension(80, 30));

        contraseña.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        contraseña.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        contraseña.setText("Contraseña:");

        nombre.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        nombre.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        nombre.setText("Nombre:");

        txtNombre.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtNombre.setAlignmentX(5.0F);
        txtNombre.setMinimumSize(new java.awt.Dimension(60, 23));
        txtNombre.setPreferredSize(new java.awt.Dimension(80, 30));

        apellido.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        apellido.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        apellido.setText("Apellido: ");

        txtApellido.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtApellido.setAlignmentX(5.0F);
        txtApellido.setMinimumSize(new java.awt.Dimension(60, 23));
        txtApellido.setPreferredSize(new java.awt.Dimension(80, 30));

        cedula.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        cedula.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        cedula.setText("Cédula:");

        txtCedula.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtCedula.setAlignmentX(5.0F);
        txtCedula.setMinimumSize(new java.awt.Dimension(60, 23));
        txtCedula.setPreferredSize(new java.awt.Dimension(80, 30));

        email.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        email.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        email.setText("Email:");

        txtTelefono.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtTelefono.setAlignmentX(5.0F);
        txtTelefono.setMinimumSize(new java.awt.Dimension(60, 23));
        txtTelefono.setPreferredSize(new java.awt.Dimension(80, 30));

        txtEmail.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtEmail.setAlignmentX(5.0F);
        txtEmail.setMinimumSize(new java.awt.Dimension(60, 23));
        txtEmail.setPreferredSize(new java.awt.Dimension(80, 30));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(contraseña, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(apellido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cedula, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(telefono, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(email, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCedula, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cedula, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(apellido, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(telefono, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 16, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(contraseña)
                    .addComponent(txtContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );

        jLabel5.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel5.setText("FORMULARIO PARA REGISTRO DE USUARIOS");

        botonGuardar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        botonGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectoaula/imagenes/salvar.png"))); // NOI18N
        botonGuardar.setText("GUARDAR");
        botonGuardar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        botonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGuardarActionPerformed(evt);
            }
        });

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectoaula/imagenes/trabajo-en-equipo.png"))); // NOI18N

        botonBuscar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        botonBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectoaula/imagenes/buscar.png"))); // NOI18N
        botonBuscar.setText("BUSCAR");
        botonBuscar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        botonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonBuscarActionPerformed(evt);
            }
        });

        botonEditar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        botonEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectoaula/imagenes/editar-codigo.png"))); // NOI18N
        botonEditar.setText("EDITAR");
        botonEditar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        botonEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEditarActionPerformed(evt);
            }
        });

        botonEliminar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        botonEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectoaula/imagenes/borrar.png"))); // NOI18N
        botonEliminar.setText("ELIMINAR");
        botonEliminar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        botonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(botonGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(botonBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(botonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(26, 26, 26)
                            .addComponent(botonEliminar))))
                .addContainerGap(70, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(botonGuardar)
                            .addComponent(botonBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(botonEliminar)
                            .addComponent(botonEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(45, 45, 45))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(108, 108, 108))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGuardarActionPerformed
    String nombre = txtNombre.getText();
    String apellido = txtApellido.getText();  
    String cedula = txtCedula.getText(); 
    String email = txtEmail.getText();
    String telefono = txtTelefono.getText();
    String contraseña = txtContraseña.getText();
        
    if(nombre.isEmpty()||nombre.isBlank()||apellido.isEmpty()||apellido.isBlank()||cedula.isEmpty()||cedula.isBlank()||telefono.isEmpty()||telefono.isBlank()
    ||email.isEmpty()||email.isEmpty()||contraseña.isEmpty()||contraseña.isBlank()){
    JOptionPane.showMessageDialog(this, "Rellene todos los campos para continuar.");
    }if (!cedula.matches("\\d+")) {
    JOptionPane.showMessageDialog(rootPane, "El número de cédula debe contener solo números enteros.", "Error de formato", JOptionPane.ERROR_MESSAGE);
    return; // Sale del método si el formato es incorrecto
    }if (!telefono.matches("\\d+")) {
    JOptionPane.showMessageDialog(rootPane, "El número de teléfono debe contener solo números enteros.", "Error de formato", JOptionPane.ERROR_MESSAGE);
    return; // Sale del método si el formato es incorrecto
    }else{
    crearUsuarios();
    limpiarCampos();
    }
    }//GEN-LAST:event_botonGuardarActionPerformed

    private void botonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonBuscarActionPerformed
    mostrarDatosAlmacenados();
    }//GEN-LAST:event_botonBuscarActionPerformed
    private void mostrarDatosAlmacenados() {
    String archivo = txtCedula.getText() + ".txt";
    File archivoALeer = new File(crearblock + archivo);
    if (archivoALeer.exists()) {
        try {
            BufferedReader lector = new BufferedReader(new FileReader(archivoALeer.getAbsolutePath()));
            String linea;
            String datos = "";

             while ((linea = lector.readLine()) != null) {
                datos += linea + "\n";
                if (linea.startsWith("Nombre:")) {
                    txtNombre.setText(linea.substring(8));
                } else if (linea.startsWith("Apellido:")) {
                    txtApellido.setText(linea.substring(9));
                } else if (linea.startsWith("Télefono:")) {
                    txtTelefono.setText(linea.substring(10));
                } else if (linea.startsWith("Email:")) {
                    txtEmail.setText(linea.substring(7));
                } else if (linea.startsWith("Contraseña:")) {
                    txtContraseña.setText(linea.substring(12));
                }
            }
            lector.close();
           
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al leer el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(rootPane, "No se encontraron datos para este usuario.", "Datos no Encontrados", JOptionPane.INFORMATION_MESSAGE);
    }
    }
    private void botonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEditarActionPerformed
    editarDatosAlmacenados();
    limpiarCampos();
    }//GEN-LAST:event_botonEditarActionPerformed
    private void editarDatosAlmacenados() {
    String archivo = txtCedula.getText() + ".txt";
    File archivoAEditar = new File(crearblock + archivo);
    if (archivoAEditar.exists()) {
        try {
            BufferedReader lector = new BufferedReader(new FileReader(archivoAEditar.getAbsolutePath()));
            String linea;
            StringBuilder datos = new StringBuilder();

            while ((linea = lector.readLine()) != null) {
                if (linea.startsWith("Nombre:") && !txtNombre.getText().equals("")) {
                    datos.append("Nombre: ").append(txtNombre.getText()).append("\n");
                } else if (linea.startsWith("Apellido:") && !txtApellido.getText().equals("")) {
                    datos.append("Apellido: ").append(txtApellido.getText()).append("\n");
                } else if (linea.startsWith("Télefono:") && !txtTelefono.getText().equals("")) {
                    datos.append("Télefono: ").append(txtTelefono.getText()).append("\n");
                } else if (linea.startsWith("Email:") && !txtEmail.getText().equals("")) {
                    datos.append("Email: ").append(txtEmail.getText()).append("\n");
                } else if (linea.startsWith("Contraseña:") && !txtContraseña.getText().equals("")) {
                    datos.append("Contraseña: ").append(txtContraseña.getText()).append("\n");
                } else {
                    datos.append(linea).append("\n");
                }
            }
            lector.close();

            BufferedWriter escritor = new BufferedWriter(new FileWriter(archivoAEditar.getAbsolutePath()));
            escritor.write(datos.toString());
            escritor.close();

            JOptionPane.showMessageDialog(rootPane, "Los datos se han actualizado correctamente.", "Edición Exitosa", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al editar el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(rootPane, "No se encontraron datos para este usuario.", "Datos no Encontrados", JOptionPane.INFORMATION_MESSAGE);
    }
}
    private void botonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarActionPerformed
    eliminarDatosAlmacenados();
    limpiarCampos();
    }//GEN-LAST:event_botonEliminarActionPerformed
   private void eliminarDatosAlmacenados() {
    String archivo = txtCedula.getText() + ".txt";
    File archivoAEliminar = new File(crearblock + archivo);

    if (archivoAEliminar.exists()) {
        try {
            if (archivoAEliminar.delete()) {
                JOptionPane.showMessageDialog(rootPane, "Los datos se han eliminado correctamente.", "Eliminación Exitosa", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(rootPane, "No se pudo eliminar el archivo.", "Error al Eliminar", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SecurityException e) {
            JOptionPane.showMessageDialog(null, "Error de permisos al intentar eliminar el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(rootPane, "No se encontraron datos para este usuario.", "Datos no Encontrados", JOptionPane.INFORMATION_MESSAGE);
    }
    }
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
            java.util.logging.Logger.getLogger(VentanaRegistroUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaRegistroUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaRegistroUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaRegistroUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                VentanaRegistroUsuario dialog = new VentanaRegistroUsuario(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel apellido;
    private javax.swing.JButton botonBuscar;
    private javax.swing.JButton botonEditar;
    private javax.swing.JButton botonEliminar;
    private javax.swing.JButton botonGuardar;
    private javax.swing.JLabel cedula;
    private javax.swing.JLabel contraseña;
    private javax.swing.JLabel email;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel nombre;
    private javax.swing.JLabel telefono;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtContraseña;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables

    private void crearElectrodomesticos(String text, String text0) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
