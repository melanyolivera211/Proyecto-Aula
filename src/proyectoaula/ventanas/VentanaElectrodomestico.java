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

public class VentanaElectrodomestico extends javax.swing.JFrame {

    String elec = File.separator;
    String crearblock = System.getProperty("user.dir") + elec + "UsuariosBD" + elec;
    String rutaelectrodomestico = "C:\\Users\\HP\\Documents\\NetBeansProjects\\ProyectoAula\\UsuariosBD";
    private DefaultTableModel modelo;

    public VentanaElectrodomestico() {
        initComponents();

    }

    private void registrarElectrodomestico() {
        String cedula = txtCedula.getText();
        String archivoUsuario = cedula + ".txt";
        File rutaArchivo = new File(crearblock + archivoUsuario);
        if (rutaArchivo.exists()) {
            String nserie = nroserieTXT.getText()+".txt";
            String electrodomesticoBD = rutaelectrodomestico + elec + cedula + "_electrodomesticos" + elec;
            File crearelec = new File(electrodomesticoBD);
            File CAelectrodomestico = new File(electrodomesticoBD + nserie);
            if (CAelectrodomestico.exists()) {
                JOptionPane.showConfirmDialog(rootPane, "el electrodomestico existe");
            } else {
                if (crearelec.exists()) {
                    try {
                        CAelectrodomestico.createNewFile();
                        Writer escritorDeArchivo = new FileWriter(CAelectrodomestico.getAbsolutePath());
                        String datosElectrodomestico = "Electrodomestico: " + electrodomesticoTXT.getText() + "\n";
                        datosElectrodomestico += "nro.serie: " + nroserieTXT.getText() + "\n";
                        datosElectrodomestico += "Marca: " + marcaTXT.getText() + "\n";
                        datosElectrodomestico += "\n";
                        escritorDeArchivo.write(datosElectrodomestico);
                        escritorDeArchivo.flush();
                        escritorDeArchivo.close();

                        JOptionPane.showMessageDialog(rootPane, "Electrodoméstico registrado con éxito.", "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);

                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(null, "Error al registrar el electrodoméstico.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {

                    try {
                        crearelec.mkdirs();
                        CAelectrodomestico.createNewFile();
                        Writer escritorDeArchivo = new FileWriter(CAelectrodomestico.getAbsolutePath());
                        String datosElectrodomestico = "Electrodomestico: " + electrodomesticoTXT.getText() + "\n";
                        datosElectrodomestico += "nro.serie: " + nroserieTXT.getText() + "\n";
                        datosElectrodomestico += "Marca: " + marcaTXT.getText() + "\n";
                        datosElectrodomestico += "\n";
                        escritorDeArchivo.write(datosElectrodomestico);
                        escritorDeArchivo.flush();
                        escritorDeArchivo.close();

                        JOptionPane.showMessageDialog(rootPane, "Electrodoméstico registrado con éxito.", "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);

                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(null, "Error al registrar el electrodoméstico.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

        } else {
            JOptionPane.showMessageDialog(rootPane, "el usuario no existe");

        }
    }

    private void buscarelectrodomestico() {
        String cedula = txtCedula.getText();
        String archivoUsuario = cedula + ".txt";
        File rutaArchivo = new File(crearblock + archivoUsuario);
        if (rutaArchivo.exists()) {
            String nserie = nroserieTXT.getText() + ".txt";
            String electrodomesticoBD = rutaelectrodomestico + elec + cedula + "_electrodomesticos" + elec;
            File crearelec = new File(electrodomesticoBD);
            File BAelectrodomestico = new File(electrodomesticoBD + nserie);
            if (crearelec.exists() && BAelectrodomestico.exists()) {
                try {
                    BufferedReader lector = new BufferedReader(new FileReader(BAelectrodomestico.getAbsolutePath()));
                    String linea;
                    String datos = "";
                    while ((linea = lector.readLine()) != null) {
                        if (linea.startsWith("Electrodomestico:")) {
                            datos = "Electrodomestico: " + linea.substring(18) + "\n";
                        } else if (linea.startsWith("nro.serie:")) {
                            datos += "nro.serie: " + linea.substring(11) + "\n";
                        } else if (linea.startsWith("Marca:")) {
                            datos += "Marca: " + linea.substring(7);
                        }
                    }
                    JOptionPane.showMessageDialog(rootPane, datos);
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

    private void editarelectrodomestico() {
        String cedula = txtCedula.getText();
        String archivoUsuario = cedula + ".txt";
        File rutaArchivo = new File(crearblock + archivoUsuario);
        if (rutaArchivo.exists()) {
            String nserie = nroserieTXT.getText() + ".txt";
            String electrodomesticoBD = rutaelectrodomestico + elec + cedula + "_electrodomesticos" + elec;
            File BAelectrodomestico = new File(electrodomesticoBD + nserie);
            if (BAelectrodomestico.exists()) {
                try {
                    BufferedReader lector = new BufferedReader(new FileReader(BAelectrodomestico.getAbsolutePath()));
                    String linea;
                    StringBuilder datos = new StringBuilder();
                    while ((linea = lector.readLine()) != null) {
                        if (linea.startsWith("Electrodomestico:") && !electrodomesticoTXT.getText().equals("")) {
                            datos.append("Electrodomestico: ").append(electrodomesticoTXT.getText()).append("\n");
                        } else if (linea.startsWith("nro.serie:") && !nroserieTXT.getText().equals("")) {
                            datos.append("nro.serie: ").append(nroserieTXT.getText()).append("\n");
                        } else if (linea.startsWith("Marca:") && !marcaTXT.getText().equals("")) {
                            datos.append("Marca: ").append(marcaTXT.getText()).append("\n");
                        } else {
                            datos.append(linea).append("\n");
                        }
                    }
                    lector.close();
                    BufferedWriter escritor = new BufferedWriter(new FileWriter(BAelectrodomestico.getAbsolutePath()));
                    escritor.write(datos.toString());
                    escritor.close();
                    JOptionPane.showMessageDialog(rootPane, "El archivo a sido editado con exito");
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

    private void mostrarElectrodomesticosEnTabla() {
        String cedula = txtCedula.getText();
        String electrodomesticoBD = rutaelectrodomestico + elec + cedula + "_electrodomesticos" + elec;

        File directorioElectrodomesticos = new File(electrodomesticoBD);
        File[] archivos = directorioElectrodomesticos.listFiles();

        if (archivos != null) {

            modelo = new DefaultTableModel();
            modelo.addColumn("Electrodomestico");
            modelo.addColumn("Número de Serie");
            modelo.addColumn("Marca");

            for (File archivo : archivos) {
                try {
                    BufferedReader lector = new BufferedReader(new FileReader(archivo.getAbsolutePath()));
                    String linea;
                    String electrodomestico = "";
                    String numeroSerie = "";
                    String marca = "";

                    while ((linea = lector.readLine()) != null) {
                        if (linea.startsWith("Electrodomestico:")) {
                            electrodomestico = linea.substring(18);
                        } else if (linea.startsWith("nro.serie:")) {
                            numeroSerie = linea.substring(11);
                        } else if (linea.startsWith("Marca:")) {
                            marca = linea.substring(7);
                        }
                    }

                    modelo.addRow(new Object[]{electrodomestico, numeroSerie, marca});

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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelElectrodomestico = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        RegresarVentana = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        nroserieTXT = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        marcaTXT = new javax.swing.JTextField();
        electrodomesticoTXT = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaelectrodomestico = new javax.swing.JTable();
        txtCedula = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        Cancelar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        botonEditar = new javax.swing.JButton();
        botonEliminar = new javax.swing.JButton();
        botonBuscar = new javax.swing.JButton();
        botonGuardar = new javax.swing.JButton();
        Mostrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        PanelElectrodomestico.setBackground(new java.awt.Color(204, 204, 255));
        PanelElectrodomestico.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(222, 220, 220));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        RegresarVentana.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        RegresarVentana.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectoaula/imagenes/atras.png"))); // NOI18N
        RegresarVentana.setText("Regresar");
        RegresarVentana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegresarVentanaActionPerformed(evt);
            }
        });
        jPanel1.add(RegresarVentana, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 430, 140, 40));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel4.setText("FORMULARIO PARA REGISTRO DE ELECTRODOMESTICOS");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 30, -1, -1));

        jPanel2.setBackground(new java.awt.Color(220, 220, 220));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)), "Datos Del Electrodomestico", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setText("Electrodomestico:");

        nroserieTXT.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setText("Nro. Serie:");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setText("Marca:");

        marcaTXT.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        electrodomesticoTXT.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

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

        txtCedula.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setText("Cédula:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nroserieTXT, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(marcaTXT, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(electrodomesticoTXT, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(60, 60, 60))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(electrodomesticoTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(nroserieTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(marcaTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 100, 460, 360));

        Cancelar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        Cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectoaula/imagenes/cancelado.png"))); // NOI18N
        Cancelar.setText("CANCELAR");
        Cancelar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelarActionPerformed(evt);
            }
        });
        jPanel1.add(Cancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 540, -1, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectoaula/imagenes/electrodomesticos.png"))); // NOI18N
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 230, 310));

        botonEditar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        botonEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectoaula/imagenes/editar-codigo.png"))); // NOI18N
        botonEditar.setText("EDITAR");
        botonEditar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        botonEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEditarActionPerformed(evt);
            }
        });
        jPanel1.add(botonEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 540, 110, -1));

        botonEliminar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        botonEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectoaula/imagenes/borrar.png"))); // NOI18N
        botonEliminar.setText("ELIMINAR");
        botonEliminar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        botonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(botonEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 540, 110, -1));

        botonBuscar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        botonBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectoaula/imagenes/buscar.png"))); // NOI18N
        botonBuscar.setText("BUSCAR");
        botonBuscar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        botonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonBuscarActionPerformed(evt);
            }
        });
        jPanel1.add(botonBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 480, 110, 40));

        botonGuardar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        botonGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectoaula/imagenes/salvar.png"))); // NOI18N
        botonGuardar.setText("GUARDAR");
        botonGuardar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        botonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGuardarActionPerformed(evt);
            }
        });
        jPanel1.add(botonGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 480, -1, 40));

        Mostrar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        Mostrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectoaula/imagenes/salvar.png"))); // NOI18N
        Mostrar.setText("Mostrar");
        Mostrar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Mostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MostrarActionPerformed(evt);
            }
        });
        jPanel1.add(Mostrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 480, 110, 40));

        PanelElectrodomestico.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1020, 620));

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
        this.dispose();
    }//GEN-LAST:event_RegresarVentanaActionPerformed

    private void CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelarActionPerformed
        electrodomesticoTXT.setText("");
        nroserieTXT.setText("");
        marcaTXT.setText("");
    }//GEN-LAST:event_CancelarActionPerformed

    private void botonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEditarActionPerformed
        String serie = nroserieTXT.getText();
        String cedula = txtCedula.getText();
        if (serie.isEmpty() || serie.isBlank() || cedula.isEmpty() || cedula.isBlank()) {
            JOptionPane.showMessageDialog(rootPane, "el espacio para ingresar la cedula o el nro.serie esta/n vacios favor rellenar ambos para buscar el electrodomestico");
        } else {
            editarelectrodomestico();
            electrodomesticoTXT.setText("");
            nroserieTXT.setText("");
            marcaTXT.setText("");
        }
    }//GEN-LAST:event_botonEditarActionPerformed

    private void botonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarActionPerformed

    }//GEN-LAST:event_botonEliminarActionPerformed

    private void botonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonBuscarActionPerformed
        String cedula = txtCedula.getText();
        String electrodomestico = electrodomesticoTXT.getText();
        String nroserie = nroserieTXT.getText();
        String marca = marcaTXT.getText();
        if (cedula.isEmpty() || cedula.isBlank() || electrodomestico.isEmpty() || electrodomestico.isBlank() || nroserie.isEmpty() || nroserie.isBlank() || marca.isEmpty() || marca.isBlank()) {
            JOptionPane.showMessageDialog(rootPane, "Uno o mas campos de texto esta vacios favor rellenarlos");
        } else {
            buscarelectrodomestico();
            electrodomesticoTXT.setText("");
            nroserieTXT.setText("");
            marcaTXT.setText("");
        }
    }//GEN-LAST:event_botonBuscarActionPerformed

    private void botonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGuardarActionPerformed
        String cedula = txtCedula.getText();
        String electrodomestico = electrodomesticoTXT.getText();
        String nroserie = nroserieTXT.getText();
        String marca = marcaTXT.getText();

        if (cedula.isEmpty() || cedula.isBlank() || electrodomestico.isEmpty() || electrodomestico.isBlank() || nroserie.isEmpty() || nroserie.isBlank() || marca.isEmpty() || marca.isBlank()) {
            JOptionPane.showMessageDialog(this, "Rellene todos los campos para continuar.");
        }
        if (!cedula.matches("\\d+")) {
            JOptionPane.showMessageDialog(rootPane, "El número de cédula debe contener solo números enteros.", "Error de formato", JOptionPane.ERROR_MESSAGE);
            return;
        } else {
            registrarElectrodomestico();
            limpiarCampos();
        }
    }//GEN-LAST:event_botonGuardarActionPerformed

    private void MostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MostrarActionPerformed
        String cedula = txtCedula.getText();
        if (cedula.isEmpty() || cedula.isBlank()) {
            JOptionPane.showMessageDialog(rootPane, "debe ingresar la cedula");
        } else {
            mostrarElectrodomesticosEnTabla();
        }
    }//GEN-LAST:event_MostrarActionPerformed

    public void limpiarCampos() {
        electrodomesticoTXT.setText("");
        nroserieTXT.setText("");
        marcaTXT.setText("");
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
    private javax.swing.JTextField electrodomesticoTXT;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField marcaTXT;
    private javax.swing.JTextField nroserieTXT;
    private javax.swing.JTable tablaelectrodomestico;
    private javax.swing.JTextField txtCedula;
    // End of variables declaration//GEN-END:variables
}
