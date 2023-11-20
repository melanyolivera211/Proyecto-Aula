package proyectoaula.ventanas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import proyectoaula.objects.Electrodomestico;

public class VentanaElectrodomestico extends javax.swing.JFrame {

    Electrodomestico electrodomestico = new Electrodomestico();
    String elec = File.separator;
    String crearblock = System.getProperty("user.dir") + elec + "UsuariosBD" + elec;
    String rutaelectrodomestico = "UsuariosBD";
    private DefaultTableModel modelo;

    public VentanaElectrodomestico() {
        initComponents();

    }

    private void crearElectrodomestico(Electrodomestico electrodomestico) {
        String cedula = txtCedula.getText();
        String archivoUsuario = cedula + ".txt";
        File rutaArchivo = new File(crearblock + archivoUsuario);

        if (rutaArchivo.exists()) {
            String nserie = electrodomestico.nroSerie + ".txt";
            String electrodomesticoBD = rutaelectrodomestico + elec + cedula + "_electrodomesticos" + elec;
            File crearelec = new File(electrodomesticoBD);
            File CAelectrodomestico = new File(electrodomesticoBD + nserie);

            if (CAelectrodomestico.exists()) {
                JOptionPane.showMessageDialog(rootPane, "El electrodoméstico ya existe");
            } else {
                if (crearelec.exists() || crearelec.mkdirs()) {
                    try {
                        CAelectrodomestico.createNewFile();
                        Writer escritorDeArchivo = new FileWriter(CAelectrodomestico.getAbsolutePath());
                        String datosElectrodomestico = "Electrodomestico: " + electrodomestico.nombreE + "\n";
                        datosElectrodomestico += "nro.serie: " + electrodomestico.nroSerie + "\n";
                        datosElectrodomestico += "Marca: " + electrodomestico.marca + "\n";
                        datosElectrodomestico += "\n";
                        escritorDeArchivo.write(datosElectrodomestico);
                        escritorDeArchivo.flush();
                        escritorDeArchivo.close();

                        JOptionPane.showMessageDialog(rootPane, "Electrodoméstico registrado con éxito.", "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);

                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(null, "Error al registrar el electrodoméstico.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Error al crear el directorio de electrodomésticos.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "El usuario no existe");
        }
    }

    private void buscarElectrodomestico() {
        String cedula = txtCedula.getText();
        String archivoUsuario = cedula + ".txt";
        File rutaArchivo = new File(crearblock + archivoUsuario);
        if (rutaArchivo.exists()) {
            String nserie = txtNroSerie.getText() + ".txt";
            String electrodomesticoBD = rutaelectrodomestico + elec + cedula + "_electrodomesticos" + elec;
            File crearelec = new File(electrodomesticoBD);
            File BAelectrodomestico = new File(electrodomesticoBD + nserie);
            if (crearelec.exists() && BAelectrodomestico.exists()) {
                try {
                    BufferedReader lector = new BufferedReader(new FileReader(BAelectrodomestico.getAbsolutePath()));
                    String linea;
                    String dato = "";
                    while ((linea = lector.readLine()) != null) {
                        if (linea.startsWith("Electrodomestico:")) {
                            electrodomestico.setNombreE(linea.substring(18).trim());
                        } else if (linea.startsWith("nro.serie:")) {
                            electrodomestico.setNroserie(linea.substring(11).trim());
                        } else if (linea.startsWith("Marca:")) {
                            electrodomestico.setMarca(linea.substring(7).trim());
                        }
                    }
                    JOptionPane.showMessageDialog(rootPane, electrodomestico.nombreE + "\n" + electrodomestico.nroSerie + "\n" + electrodomestico.marca + "\n");
                    lector.close();
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(rootPane, "no se a podido encontrar el electrodomestico");
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "El electrodomestico no existe, verifique que alla colocado bien la informacion");
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "La cedula no existe, verifique que alla colocado bien la informacion");
        }
    }

    private void editarElectrodomestico(Electrodomestico electrodomestico) {
        String cedula = txtCedula.getText();
        String archivoUsuario = cedula + ".txt";
        File rutaArchivo = new File(crearblock + archivoUsuario);
        if (rutaArchivo.exists()) {
            String electrodomesticoBD = rutaelectrodomestico + elec + cedula + "_electrodomesticos" + elec;
            File BAelectrodomestico = new File(electrodomesticoBD + electrodomestico.nroSerie + ".txt");
            if (BAelectrodomestico.exists()) {
                try {
                    BufferedReader lector = new BufferedReader(new FileReader(BAelectrodomestico.getAbsolutePath()));
                    String linea;
                    StringBuilder datos = new StringBuilder();
                    while ((linea = lector.readLine()) != null) {
                        if (linea.startsWith("Electrodomestico:") && !txtElectrodomestico.getText().equals("")) {
                            datos.append("Electrodomestico: ").append(electrodomestico.nombreE).append("\n");
                        } else if (linea.startsWith("nro.serie:") && !txtNroSerie.getText().equals("")) {
                            datos.append("nro.serie: ").append(electrodomestico.nroSerie).append("\n");
                        } else if (linea.startsWith("Marca:") && !txtMarca.getText().equals("")) {
                            datos.append("Marca: ").append(electrodomestico.marca).append("\n");
                        } else {
                            datos.append(linea).append("\n");
                        }
                    }
                    lector.close();
                    BufferedWriter escritor = new BufferedWriter(new FileWriter(BAelectrodomestico.getAbsolutePath()));
                    escritor.write(datos.toString());
                    escritor.close();
                    JOptionPane.showMessageDialog(rootPane, "¡El archivo ha sido editado con éxito!");
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(rootPane, "No se ha podido encontrar el electrodomestico");
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "El electrodomestico no existe, verifique que haya colocado bien la información");
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "La cedula no existe, verifique que haya colocado bien la información");
        }

    }

    private void mostrarElectrodomesticosEnTabla() {
        String cedula = txtCedula.getText();
        String electrodomesticoBD = rutaelectrodomestico + elec + cedula + "_electrodomesticos" + elec;

        File directorioElectrodomesticos = new File(electrodomesticoBD);
        File[] archivos = directorioElectrodomesticos.listFiles();

        if (archivos != null) {

            DefaultTableModel modelo = new DefaultTableModel();
            modelo.addColumn("Electrodomestico");
            modelo.addColumn("Número de Serie");
            modelo.addColumn("Marca");

            for (File archivo : archivos) {
                try {
                    BufferedReader lector = new BufferedReader(new FileReader(archivo.getAbsolutePath()));
                    String linea;
                    while ((linea = lector.readLine()) != null) {
                        if (linea.startsWith("Electrodomestico:")) {
                            electrodomestico.setNombreE(linea.substring(18).trim());
                        } else if (linea.startsWith("nro.serie:")) {
                            electrodomestico.setNroserie(linea.substring(11).trim());
                        } else if (linea.startsWith("Marca:")) {
                            electrodomestico.setMarca(linea.substring(7).trim());
                        }
                    }

                    modelo.addRow(new Object[]{electrodomestico.nombreE, electrodomestico.nroSerie, electrodomestico.marca});

                    lector.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            tablaelectrodomestico.setModel(modelo);
        } else {
            JOptionPane.showMessageDialog(rootPane, "No hay electrodomésticos registrados.");
        }
    }

    private void eliminarElectrodomestico(Electrodomestico electrodomestico) {
        String cedula = txtCedula.getText();
        String archivoUsuario = cedula + ".txt";
        File rutaArchivo = new File(crearblock + archivoUsuario);
        if (rutaArchivo.exists()) {
            String electrodomesticoBD = rutaelectrodomestico + elec + cedula + "_electrodomesticos" + elec;
            File BAelectrodomestico = new File(electrodomesticoBD + electrodomestico.nroSerie + ".txt");
            if (BAelectrodomestico.exists()) {
                try {
                    if (BAelectrodomestico.delete()) {
                        JOptionPane.showMessageDialog(rootPane, "el electrodomestico a sido eliminado con exito");
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "el electrodomestico no se a podido eliminar");
                    }
                } catch (SecurityException e) {
                    JOptionPane.showMessageDialog(rootPane, "no se a podido eliminar el electrodomestico");
                }

            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelElectrodomestico = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        RegresarVentana = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtNroSerie = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtMarca = new javax.swing.JTextField();
        txtElectrodomestico = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaelectrodomestico = new javax.swing.JTable();
        txtCedula = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        botonEditar = new javax.swing.JButton();
        botonEliminar = new javax.swing.JButton();
        botonBuscar = new javax.swing.JButton();
        botonGuardar = new javax.swing.JButton();
        Mostrar = new javax.swing.JButton();
        Cancelar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        PanelElectrodomestico.setBackground(new java.awt.Color(204, 204, 255));
        PanelElectrodomestico.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        RegresarVentana.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        RegresarVentana.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectoaula/imagenes/atras.png"))); // NOI18N
        RegresarVentana.setText("Regresar");
        RegresarVentana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegresarVentanaActionPerformed(evt);
            }
        });
        jPanel1.add(RegresarVentana, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 500, 140, 40));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Electrodomestico:");
        jLabel2.setMinimumSize(null);
        jLabel2.setPreferredSize(new java.awt.Dimension(60, 23));
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 100, 140, -1));

        txtNroSerie.setFont(new java.awt.Font("Lucida Sans", 0, 14)); // NOI18N
        txtNroSerie.setMinimumSize(new java.awt.Dimension(60, 23));
        jPanel2.add(txtNroSerie, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 140, 190, -1));

        jLabel1.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Nro. Serie:");
        jLabel1.setMinimumSize(null);
        jLabel1.setPreferredSize(new java.awt.Dimension(60, 23));
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 140, 140, -1));

        jLabel3.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Marca:");
        jLabel3.setMinimumSize(null);
        jLabel3.setPreferredSize(new java.awt.Dimension(60, 23));
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 180, 140, -1));

        txtMarca.setFont(new java.awt.Font("Lucida Sans", 0, 14)); // NOI18N
        txtMarca.setMinimumSize(new java.awt.Dimension(60, 23));
        jPanel2.add(txtMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 180, 190, -1));

        txtElectrodomestico.setFont(new java.awt.Font("Lucida Sans", 0, 14)); // NOI18N
        txtElectrodomestico.setMinimumSize(new java.awt.Dimension(60, 23));
        jPanel2.add(txtElectrodomestico, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 100, 190, -1));

        tablaelectrodomestico.setBackground(new java.awt.Color(204, 204, 204));
        tablaelectrodomestico.setFont(new java.awt.Font("Lucida Sans", 0, 14)); // NOI18N
        tablaelectrodomestico.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Electrodomestico", "Nro. Serie", "Marca"
            }
        ));
        jScrollPane1.setViewportView(tablaelectrodomestico);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, 450, 210));

        txtCedula.setFont(new java.awt.Font("Lucida Sans", 0, 14)); // NOI18N
        txtCedula.setMinimumSize(new java.awt.Dimension(60, 23));
        jPanel2.add(txtCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 60, 190, -1));

        jLabel5.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Cédula:");
        jLabel5.setMinimumSize(null);
        jLabel5.setPreferredSize(new java.awt.Dimension(60, 23));
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 60, 140, -1));

        botonEditar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        botonEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectoaula/imagenes/editar-codigo.png"))); // NOI18N
        botonEditar.setText("EDITAR");
        botonEditar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        botonEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEditarActionPerformed(evt);
            }
        });
        jPanel2.add(botonEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 530, 130, -1));

        botonEliminar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        botonEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectoaula/imagenes/borrar.png"))); // NOI18N
        botonEliminar.setText("ELIMINAR");
        botonEliminar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        botonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEliminarActionPerformed(evt);
            }
        });
        jPanel2.add(botonEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 530, 130, -1));

        botonBuscar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        botonBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectoaula/imagenes/buscar.png"))); // NOI18N
        botonBuscar.setText("BUSCAR");
        botonBuscar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        botonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonBuscarActionPerformed(evt);
            }
        });
        jPanel2.add(botonBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 470, 130, 40));

        botonGuardar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        botonGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectoaula/imagenes/salvar.png"))); // NOI18N
        botonGuardar.setText("GUARDAR");
        botonGuardar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        botonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGuardarActionPerformed(evt);
            }
        });
        jPanel2.add(botonGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 470, 130, 40));

        Mostrar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        Mostrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectoaula/imagenes/mostrar.png"))); // NOI18N
        Mostrar.setText("MOSTRAR");
        Mostrar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Mostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MostrarActionPerformed(evt);
            }
        });
        jPanel2.add(Mostrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 470, 130, 40));

        Cancelar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        Cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectoaula/imagenes/cancelado.png"))); // NOI18N
        Cancelar.setText("CANCELAR");
        Cancelar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelarActionPerformed(evt);
            }
        });
        jPanel2.add(Cancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 530, -1, 40));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 0, 570, 600));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectoaula/imagenes/electrodomesticos.png"))); // NOI18N
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 270, 330));

        jLabel7.setFont(new java.awt.Font("Lucida Sans", 1, 18)); // NOI18N
        jLabel7.setText("GESTIÓN DE ELECTRODOMÉSTICOS");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 350, 40));

        PanelElectrodomestico.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 920, 600));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelElectrodomestico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelElectrodomestico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void RegresarVentanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegresarVentanaActionPerformed
        Ventana abc = new Ventana();
        abc.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_RegresarVentanaActionPerformed

    private void CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelarActionPerformed
        txtElectrodomestico.setText("");
        txtNroSerie.setText("");
        txtMarca.setText("");
    }//GEN-LAST:event_CancelarActionPerformed

    private void botonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEditarActionPerformed
        String cedula = txtCedula.getText();
        String nombreElectrodomestico = txtElectrodomestico.getText();
        String nroSerie = txtNroSerie.getText();
        String marca = txtMarca.getText();
        if (nroSerie.isEmpty() || nroSerie.isBlank() || cedula.isEmpty() || cedula.isBlank()) {
            JOptionPane.showMessageDialog(rootPane, "el espacio para ingresar la cedula o el nro.serie esta/n vacios, favor rellenar ambos para buscar el electrodomestico");
        } else {
            electrodomestico.nombreE = nombreElectrodomestico;
            electrodomestico.nroSerie = nroSerie;
            electrodomestico.marca = marca;
            editarElectrodomestico(electrodomestico);

            txtElectrodomestico.setText("");
            txtNroSerie.setText("");
            txtMarca.setText("");
        }
    }//GEN-LAST:event_botonEditarActionPerformed

    private void botonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarActionPerformed
        String nroSerie = txtNroSerie.getText();
        String cedula = txtCedula.getText();
        if (nroSerie.isEmpty() || nroSerie.isBlank() || cedula.isEmpty() || cedula.isBlank()) {
            JOptionPane.showMessageDialog(rootPane, "el espacio para ingresar la cedula o el nro.serie esta/n vacios, favor rellenar ambos para buscar el electrodomestico");
        } else {
            electrodomestico.nroSerie = nroSerie;
            eliminarElectrodomestico(electrodomestico);
            txtElectrodomestico.setText("");
            txtNroSerie.setText("");
            txtMarca.setText("");
        }
    }//GEN-LAST:event_botonEliminarActionPerformed

    private void botonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonBuscarActionPerformed
        String cedula = txtCedula.getText();
        String nroserie = txtNroSerie.getText();
        if (cedula.isEmpty() || cedula.isBlank() || nroserie.isEmpty() || nroserie.isBlank()) {
            JOptionPane.showMessageDialog(rootPane, "Uno o mas campos de texto están vacíos favor de rellenarlos");
        } else {
            buscarElectrodomestico();
            txtElectrodomestico.setText("");
            txtNroSerie.setText("");
            txtMarca.setText("");
        }
    }//GEN-LAST:event_botonBuscarActionPerformed

    private void botonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGuardarActionPerformed
        String nombreElectrodomestico = txtElectrodomestico.getText();
        String nroSerie = txtNroSerie.getText();
        String marca = txtMarca.getText();

        if (nombreElectrodomestico.isEmpty() || nombreElectrodomestico.isBlank()
                || nroSerie.isEmpty() || nroSerie.isBlank()
                || marca.isEmpty() || marca.isBlank()) {
            JOptionPane.showMessageDialog(null, "Rellene todos los campos antes de continuar", "Error", JOptionPane.INFORMATION_MESSAGE);
        } else {

            electrodomestico.nombreE = nombreElectrodomestico;
            electrodomestico.nroSerie = nroSerie;
            electrodomestico.marca = marca;

            crearElectrodomestico(electrodomestico);
            txtElectrodomestico.setText("");
            txtNroSerie.setText("");
            txtMarca.setText("");
        }
    }//GEN-LAST:event_botonGuardarActionPerformed

    private void MostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MostrarActionPerformed
        String cedula = txtCedula.getText();
        if (cedula.isEmpty() || cedula.isBlank()) {
            JOptionPane.showMessageDialog(rootPane, "Debe ingresar la cedula");
        } else {
            mostrarElectrodomesticosEnTabla();
            txtElectrodomestico.setText("");
            txtNroSerie.setText("");
            txtMarca.setText("");
        }
    }//GEN-LAST:event_MostrarActionPerformed

    public void limpiarCampos() {
        txtElectrodomestico.setText("");
        txtNroSerie.setText("");
        txtMarca.setText("");
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaElectrodomestico().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cancelar;
    private javax.swing.JButton Mostrar;
    private javax.swing.JPanel PanelElectrodomestico;
    private javax.swing.JButton RegresarVentana;
    private javax.swing.JButton botonBuscar;
    private javax.swing.JButton botonEditar;
    private javax.swing.JButton botonEliminar;
    private javax.swing.JButton botonGuardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaelectrodomestico;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtElectrodomestico;
    private javax.swing.JTextField txtMarca;
    private javax.swing.JTextField txtNroSerie;
    // End of variables declaration//GEN-END:variables
}
