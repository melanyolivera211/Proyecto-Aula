package proyectoaula.ventanas;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import javax.swing.JOptionPane;
import proyectoaula.objects.Usuario;

public class VentanaRegistroUsuario extends javax.swing.JDialog {

    String usu = File.separator;
    public String crearblock = System.getProperty("user.dir") + usu + "UsuariosBD" + usu;

    public VentanaRegistroUsuario(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    private void crearUsuarios(Usuario usuario) {
    String archivo = txtCedula.getText() + ".txt";
    File crearubi = new File(crearblock);
    File creararchivo = new File(crearblock, archivo);

    if (camposVacios()) {
        JOptionPane.showMessageDialog(rootPane, "Todos los campos deben estar llenos.");
    } else if (!validarCedula()) {
        JOptionPane.showMessageDialog(rootPane, "La cédula debe tener entre 8 y 10 dígitos enteros.");
    } else if (!validarEmail()) {
        JOptionPane.showMessageDialog(rootPane, "El formato del email no es válido.");
    } else if (!validarTelefono()) {
        JOptionPane.showMessageDialog(rootPane, "El número de teléfono debe ser un número entero.");
    } else {
        try {
            if (creararchivo.exists()) {
                JOptionPane.showMessageDialog(rootPane, "Este usuario ya está registrado.");
            } else {
                crearubi.mkdirs();
                creararchivo.createNewFile();

                usuario.setCedula(txtCedula.getText());
                usuario.setNombre(txtNombre.getText());
                usuario.setApellido(txtApellido.getText());
                usuario.setTelefono(txtTelefono.getText());
                usuario.setEmail(txtEmail.getText());
                usuario.setContraseña(jPasswordField1.getText());

                try (Writer escritorDeArchivo = new FileWriter(creararchivo.getAbsolutePath())) {
                    String datosUsuarios = "Cedula: " + usuario.getCedula() + "\n";
                    datosUsuarios += "Nombre: " + usuario.getNombre() + "\n";
                    datosUsuarios += "Apellido: " + usuario.getApellido() + "\n";
                    datosUsuarios += "Télefono: " + usuario.getTelefono() + "\n";
                    datosUsuarios += "Email: " + usuario.getEmail() + "\n";
                    datosUsuarios += "Contraseña: " + usuario.getContraseña() + "\n";
                    datosUsuarios += "\n";

                    escritorDeArchivo.write(datosUsuarios);
                    JOptionPane.showMessageDialog(rootPane, "¡El usuario ha sido registrado con éxito!");
                    limpiarCampos();
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "No se pudo registrar el usuario. Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
    // método para limpiar campos
    public void limpiarCampos() {
        txtNombre.setText("");
        txtApellido.setText("");
        txtEmail.setText("");
        txtTelefono.setText("");
        txtCedula.setText("");
        jPasswordField1.setText("");
    }
    private Usuario obtenerUsuarioDesdeArchivo(String cedula) {
    String archivo = cedula + ".txt";
    File archivoALeer = new File(crearblock + archivo);
    Usuario usuario = new Usuario();
    if (archivoALeer.exists()) {
        try {
            BufferedReader lector = new BufferedReader(new FileReader(archivoALeer.getAbsolutePath()));
            String linea;
            while ((linea = lector.readLine()) != null) {
                if (linea.startsWith("Cedula:")) {
                    usuario.setCedula(linea.substring(8).trim());
                } else if (linea.startsWith("Nombre:")) {
                    usuario.setNombre(linea.substring(8).trim());
                } else if (linea.startsWith("Apellido:")) {
                    usuario.setApellido(linea.substring(9).trim());
                } else if (linea.startsWith("Télefono:")) {
                    usuario.setTelefono(linea.substring(10).trim());
                } else if (linea.startsWith("Email:")) {
                    usuario.setEmail(linea.substring(7).trim());
                } else if (linea.startsWith("Contraseña:")) {
                    usuario.setContraseña(linea.substring(12).trim());
                }
            }
            lector.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al leer el archivo. Detalles: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(rootPane, "No se encontraron datos para este usuario.", "Datos no Encontrados", JOptionPane.INFORMATION_MESSAGE);
    }
    return usuario;
}

    private void cargarDatosUsuario() {
    String cedula1 = txtCedula.getText();
    Usuario usuario = obtenerUsuarioDesdeArchivo(cedula1);
    if (usuario.getCedula() != null && !usuario.getCedula().isEmpty()) {
        // Establecer los valores en los elementos de la interfaz de usuario (asumiendo que tienes esos elementos)
        txtNombre.setText(usuario.getNombre());
        txtApellido.setText(usuario.getApellido());
        txtTelefono.setText(usuario.getTelefono());
        txtEmail.setText(usuario.getEmail());
        jPasswordField1.setText(usuario.getContraseña());
    }
}
   private void editarUsuario() {
    String cedula1 = txtCedula.getText();
    String archivo = cedula1 + ".txt";
    File archivoAEditar = new File(crearblock + archivo);

    if (archivoAEditar.exists()) {
        // Verificar que todos los campos no estén vacíos y cumplan con los requisitos
        if (!camposVacios() && validarCedula() && validarEmail() && validarTelefono()) {
            // Obtener los nuevos valores de los campos de texto
            String nuevoNombre = txtNombre.getText();
            String nuevoApellido = txtApellido.getText();
            String nuevoTelefono = txtTelefono.getText();
            String nuevoEmail = txtEmail.getText();
            String nuevaContraseña = jPasswordField1.getText();

            int resultado = JOptionPane.showConfirmDialog(rootPane, "¿Desea editar este usuario?", "Editar Usuario", JOptionPane.YES_NO_OPTION);

            if (resultado == JOptionPane.YES_OPTION) {
                try {
                    // Guardar los cambios en el archivo
                    BufferedWriter escritor = new BufferedWriter(new FileWriter(archivoAEditar.getAbsolutePath()));
                    escritor.write("Cedula: " + cedula1 + "\n");
                    escritor.write("Nombre: " + nuevoNombre + "\n");
                    escritor.write("Apellido: " + nuevoApellido + "\n");
                    escritor.write("Télefono: " + nuevoTelefono + "\n");
                    escritor.write("Email: " + nuevoEmail + "\n");
                    escritor.write("Contraseña: " + nuevaContraseña + "\n");
                    escritor.close();
                    JOptionPane.showMessageDialog(rootPane, "Usuario editado exitosamente.");
                    limpiarCampos();
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Error al editar el archivo. Detalles: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Todos los campos deben estar completos y cumplir con los requisitos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(rootPane, "No se encontraron datos para este usuario.", "Datos no Encontrados", JOptionPane.INFORMATION_MESSAGE);
    }
}
private void eliminarUsuario() {
    String cedula = txtCedula.getText();
    String archivo = cedula + ".txt";
    File archivoAEliminar = new File(crearblock + archivo);

    if (archivoAEliminar.exists()) {
        int resultado = JOptionPane.showConfirmDialog(rootPane, "¿Desea eliminar este usuario?", "Eliminar Usuario", JOptionPane.YES_NO_OPTION);
        
        if (resultado == JOptionPane.YES_OPTION) {
            if (archivoAEliminar.delete()) {
                JOptionPane.showMessageDialog(rootPane, "Usuario eliminado exitosamente.");
                limpiarCampos(); 
            } else {
                JOptionPane.showMessageDialog(null, "Error al eliminar el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    } else {
        JOptionPane.showMessageDialog(rootPane, "No se encontraron datos para este usuario.", "Datos no Encontrados", JOptionPane.INFORMATION_MESSAGE);
    }
}
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        telefono = new javax.swing.JLabel();
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
        jPasswordField1 = new javax.swing.JPasswordField();
        botonGuardar = new javax.swing.JButton();
        botonBuscar = new javax.swing.JButton();
        botonEditar = new javax.swing.JButton();
        botonEliminar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        RegresarVentana = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        telefono.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        telefono.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        telefono.setText("Télefono:");
        telefono.setMinimumSize(new java.awt.Dimension(60, 23));
        jPanel2.add(telefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, 85, 30));

        contraseña.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        contraseña.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        contraseña.setText("Contraseña:");
        contraseña.setMinimumSize(new java.awt.Dimension(60, 23));
        jPanel2.add(contraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 350, 90, 30));

        nombre.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        nombre.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        nombre.setText("Nombre:");
        nombre.setMinimumSize(new java.awt.Dimension(60, 23));
        jPanel2.add(nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, 85, 30));

        txtNombre.setFont(new java.awt.Font("Lucida Sans", 0, 14)); // NOI18N
        txtNombre.setAlignmentX(5.0F);
        txtNombre.setMinimumSize(new java.awt.Dimension(60, 23));
        txtNombre.setPreferredSize(new java.awt.Dimension(80, 30));
        jPanel2.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 150, 252, -1));

        apellido.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        apellido.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        apellido.setText("Apellido: ");
        apellido.setMinimumSize(new java.awt.Dimension(60, 23));
        jPanel2.add(apellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 200, 85, 30));

        txtApellido.setFont(new java.awt.Font("Lucida Sans", 0, 14)); // NOI18N
        txtApellido.setAlignmentX(5.0F);
        txtApellido.setMinimumSize(new java.awt.Dimension(60, 23));
        txtApellido.setPreferredSize(new java.awt.Dimension(80, 30));
        jPanel2.add(txtApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 200, 252, -1));

        cedula.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        cedula.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        cedula.setText("Cédula:");
        cedula.setMinimumSize(new java.awt.Dimension(60, 23));
        jPanel2.add(cedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 100, 85, 30));

        txtCedula.setFont(new java.awt.Font("Lucida Sans", 0, 14)); // NOI18N
        txtCedula.setAlignmentX(5.0F);
        txtCedula.setMinimumSize(new java.awt.Dimension(60, 23));
        txtCedula.setPreferredSize(new java.awt.Dimension(80, 30));
        jPanel2.add(txtCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 100, 252, -1));

        email.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        email.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        email.setText("Email:");
        email.setMinimumSize(new java.awt.Dimension(60, 23));
        jPanel2.add(email, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 300, 85, 30));

        txtTelefono.setFont(new java.awt.Font("Lucida Sans", 0, 14)); // NOI18N
        txtTelefono.setAlignmentX(5.0F);
        txtTelefono.setMinimumSize(new java.awt.Dimension(60, 23));
        txtTelefono.setPreferredSize(new java.awt.Dimension(80, 30));
        jPanel2.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 250, 252, -1));

        txtEmail.setFont(new java.awt.Font("Lucida Sans", 0, 14)); // NOI18N
        txtEmail.setAlignmentX(5.0F);
        txtEmail.setMinimumSize(new java.awt.Dimension(60, 23));
        txtEmail.setPreferredSize(new java.awt.Dimension(80, 30));
        jPanel2.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 300, 252, -1));

        jPasswordField1.setFont(new java.awt.Font("Lucida Sans", 0, 14)); // NOI18N
        jPanel2.add(jPasswordField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 350, 252, 33));

        botonGuardar.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        botonGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectoaula/imagenes/salvar.png"))); // NOI18N
        botonGuardar.setText("GUARDAR");
        botonGuardar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        botonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGuardarActionPerformed(evt);
            }
        });
        jPanel2.add(botonGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 434, 117, 40));

        botonBuscar.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        botonBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectoaula/imagenes/buscar.png"))); // NOI18N
        botonBuscar.setText("BUSCAR");
        botonBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        botonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonBuscarActionPerformed(evt);
            }
        });
        jPanel2.add(botonBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 434, 108, 40));

        botonEditar.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        botonEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectoaula/imagenes/editar-codigo.png"))); // NOI18N
        botonEditar.setText("EDITAR");
        botonEditar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        botonEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEditarActionPerformed(evt);
            }
        });
        jPanel2.add(botonEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 434, 102, 40));

        botonEliminar.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        botonEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectoaula/imagenes/borrar.png"))); // NOI18N
        botonEliminar.setText("ELIMINAR");
        botonEliminar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        botonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEliminarActionPerformed(evt);
            }
        });
        jPanel2.add(botonEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 434, -1, 40));

        jLabel2.setFont(new java.awt.Font("Lucida Sans", 1, 18)); // NOI18N
        jLabel2.setText("FORMULARIO DE REGISTRO DE USUARIO");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, 400, 40));
        jPanel2.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 130, 250, -1));
        jPanel2.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 390, 250, 0));
        jPanel2.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 180, 250, 10));
        jPanel2.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 230, 250, 10));
        jPanel2.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 280, 250, 10));
        jPanel2.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 330, 250, 10));
        jPanel2.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 380, 250, 10));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 0, 530, 540));

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectoaula/imagenes/trabajo-en-equipo.png"))); // NOI18N
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 276, 324));

        RegresarVentana.setBackground(new java.awt.Color(153, 153, 153));
        RegresarVentana.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        RegresarVentana.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectoaula/imagenes/atras.png"))); // NOI18N
        RegresarVentana.setText("Regresar");
        RegresarVentana.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        RegresarVentana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegresarVentanaActionPerformed(evt);
            }
        });
        jPanel1.add(RegresarVentana, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 440, 120, 40));

        jLabel1.setFont(new java.awt.Font("Lucida Sans", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("GESTIÓN DE USUARIOS");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, 270, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 930, 540));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGuardarActionPerformed
        String nombre1 = txtNombre.getText();
        String apellido1 = txtApellido.getText();
        String cedula1 = txtCedula.getText();
        String email1 = txtEmail.getText();
        String telefono1 = txtTelefono.getText();
        String contraseña1 = jPasswordField1.getText();
        Usuario usuario = new Usuario(); 
        usuario.cedula = cedula1;
        usuario.nombre = nombre1;
        usuario.apellido = apellido1;
        usuario.email = email1;
        usuario.telefono = telefono1;
        usuario.contraseña = contraseña1;
        crearUsuarios(usuario);
    }//GEN-LAST:event_botonGuardarActionPerformed
    
    private void botonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonBuscarActionPerformed
        cargarDatosUsuario();
    }//GEN-LAST:event_botonBuscarActionPerformed

    private void botonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEditarActionPerformed
        String nombre1 = txtNombre.getText();
        String apellido1 = txtApellido.getText();
        String cedula1 = txtCedula.getText();
        String email1 = txtEmail.getText();
        String telefono1 = txtTelefono.getText();
        String contraseña1 = jPasswordField1.getText();
        Usuario usuario = new Usuario(); 
        usuario.cedula = cedula1;
        usuario.nombre = nombre1;
        usuario.apellido = apellido1;
        usuario.email = email1;
        usuario.telefono = telefono1;
        if (nombre1.isEmpty() || nombre1.isBlank() || apellido1.isEmpty() || apellido1.isBlank() || cedula1.isEmpty() || cedula1.isBlank() || telefono1.isEmpty() || telefono1.isBlank()
                || email1.isEmpty() || email1.isEmpty() || contraseña1.isEmpty() || contraseña1.isBlank()) {
            JOptionPane.showMessageDialog(this, "Rellene todos los campos para continuar.");
        }
        if (!cedula1.matches("\\d+")) {
            JOptionPane.showMessageDialog(rootPane, "El número de cédula debe contener solo números enteros.", "Error de formato", JOptionPane.ERROR_MESSAGE);
            return; // Sale del método si el formato es incorrecto
        }
        if (!telefono1.matches("\\d+")) {
            JOptionPane.showMessageDialog(rootPane, "El número de teléfono debe contener solo números enteros.", "Error de formato", JOptionPane.ERROR_MESSAGE);
        } else {
        editarUsuario();
        }
    }//GEN-LAST:event_botonEditarActionPerformed

    private void botonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarActionPerformed
        eliminarUsuario();
        limpiarCampos();
    }//GEN-LAST:event_botonEliminarActionPerformed

    private void RegresarVentanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegresarVentanaActionPerformed
        VentanaLogin abc = new VentanaLogin();
        abc.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_RegresarVentanaActionPerformed


    public static void main(String args[]) {
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
    private javax.swing.JButton RegresarVentana;
    private javax.swing.JLabel apellido;
    private javax.swing.JButton botonBuscar;
    private javax.swing.JButton botonEditar;
    private javax.swing.JButton botonEliminar;
    private javax.swing.JButton botonGuardar;
    private javax.swing.JLabel cedula;
    private javax.swing.JLabel contraseña;
    private javax.swing.JLabel email;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JLabel nombre;
    private javax.swing.JLabel telefono;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
private boolean camposVacios() {
    return txtCedula.getText().isEmpty() || txtNombre.getText().isEmpty() ||
           txtApellido.getText().isEmpty() || txtTelefono.getText().isEmpty() ||
           txtEmail.getText().isEmpty() || jPasswordField1.getText().isEmpty();
}

private boolean validarCedula() {
    String cedula = txtCedula.getText();
    return cedula.matches("\\d{8,10}");
}

private boolean validarEmail() {
    String email = txtEmail.getText();
    return email.matches(".*@.*\\.com");
}

private boolean validarTelefono() {
    String telefono = txtTelefono.getText();
    return telefono.matches("\\d+");
}
}
