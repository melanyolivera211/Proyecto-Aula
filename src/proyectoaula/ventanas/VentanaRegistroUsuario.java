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

                    usuario.setNroDocumento(txtCedula.getText());
                    usuario.setNombre(txtNombre.getText());
                    usuario.setApellido(txtApellido.getText());
                    usuario.setTelefono(txtTelefono.getText());
                    usuario.setEmail(txtEmail.getText());
                    usuario.setContraseña(txtContraseña.getText());

                    try (Writer escritorDeArchivo = new FileWriter(creararchivo.getAbsolutePath())) {
                        String datosUsuarios = "Cedula: " + usuario.getNroDocumento() + "\n";
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
        txtContraseña.setText("");
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
                        usuario.setNroDocumento(linea.substring(8).trim());
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
        if (usuario.getNroDocumento() != null && !usuario.getNroDocumento().isEmpty()) {
            // Establecer los valores en los elementos de la interfaz de usuario (asumiendo que tienes esos elementos)
            txtNombre.setText(usuario.getNombre());
            txtApellido.setText(usuario.getApellido());
            txtTelefono.setText(usuario.getTelefono());
            txtEmail.setText(usuario.getEmail());
            txtContraseña.setText(usuario.getContraseña());
        }
    }

    private void editarUsuario() {
        String cedula = txtCedula.getText();
        String archivo = cedula + ".txt";
        File archivoAEditar = new File(crearblock + archivo);

        if (archivoAEditar.exists()) {
            // Verificar que todos los campos no estén vacíos
            if (camposNoVacios()) {
                // Obtener los nuevos valores de los campos de texto
                String nuevoNombre = txtNombre.getText();
                String nuevoApellido = txtApellido.getText();
                String nuevoTelefono = txtTelefono.getText();
                String nuevoEmail = txtEmail.getText();
                String nuevaContraseña = txtContraseña.getText();
                int resultado = JOptionPane.showConfirmDialog(rootPane, "¿Desea editar este usuario?", "Editar Usuario", JOptionPane.YES_NO_OPTION);

                if (resultado == JOptionPane.YES_OPTION) {
                    try {
                        // Guardar los cambios en el archivo
                        BufferedWriter escritor = new BufferedWriter(new FileWriter(archivoAEditar.getAbsolutePath()));
                        escritor.write("Cedula: " + cedula + "\n");
                        escritor.write("Nombre: " + nuevoNombre + "\n");
                        escritor.write("Apellido: " + nuevoApellido + "\n");
                        escritor.write("Télefono: " + nuevoTelefono + "\n");
                        escritor.write("Email: " + nuevoEmail + "\n");
                        escritor.write("Contraseña: " + nuevaContraseña + "\n");
                        escritor.close();

                        JOptionPane.showMessageDialog(rootPane, "Usuario editado exitosamente.");
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(null, "Error al editar el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Todos los campos deben estar completos.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
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

    private boolean camposNoVacios() {
        // Verificar que todos los campos no estén vacíos
        return !txtNombre.getText().isEmpty()
                && !txtApellido.getText().isEmpty()
                && !txtTelefono.getText().isEmpty()
                && !txtEmail.getText().isEmpty()
                && !txtContraseña.getText().isEmpty();
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
        RegresarVentana = new javax.swing.JButton();

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

        RegresarVentana.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        RegresarVentana.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectoaula/imagenes/atras.png"))); // NOI18N
        RegresarVentana.setText("Regresar");
        RegresarVentana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegresarVentanaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(RegresarVentana, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(86, 86, 86)))
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
                        .addGap(18, 18, 18)
                        .addComponent(RegresarVentana, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50))))
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
        String nombre1 = txtNombre.getText();
        String apellido1 = txtApellido.getText();
        String cedula1 = txtCedula.getText();
        String email1 = txtEmail.getText();
        String telefono1 = txtTelefono.getText();
        String contraseña1 = txtContraseña.getText();
        Usuario usuario = new Usuario();
        usuario.nroDocumento = cedula1;
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
        String contraseña1 = txtContraseña.getText();
        Usuario usuario = new Usuario();
        usuario.nroDocumento = cedula1;
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
            limpiarCampos();
        }
    }//GEN-LAST:event_botonEditarActionPerformed

    private void botonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarActionPerformed
        eliminarUsuario();
        limpiarCampos();
    }//GEN-LAST:event_botonEliminarActionPerformed

    private void RegresarVentanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegresarVentanaActionPerformed
        VentanaLogin abc = new VentanaLogin();
        abc.setVisible(true);
        this.dispose();
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
private boolean camposVacios() {
        return txtCedula.getText().isEmpty() || txtNombre.getText().isEmpty()
                || txtApellido.getText().isEmpty() || txtTelefono.getText().isEmpty()
                || txtEmail.getText().isEmpty() || txtContraseña.getText().isEmpty();
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
