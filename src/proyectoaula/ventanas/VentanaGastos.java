package proyectoaula.ventanas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import proyectoaula.objects.Gastos;

public class VentanaGastos extends javax.swing.JFrame {

    Gastos gastos = new Gastos();
    String gas = File.separator;
    String crearblock = System.getProperty("user.dir") + gas + "UsuariosBD" + gas;
    String rutaelectrodomestico = "UsuariosBD";
    private DefaultTableModel modelo;

    public VentanaGastos() {
        initComponents();
    }

    private void guardarGastos(Gastos gastos) {
        String cedula = txtCedula.getText();
        String archivoUsuario = cedula + ".txt";
        File rutaArchivo = new File(crearblock + archivoUsuario);

        if (rutaArchivo.exists()) {
            String nserie = txtNroSerie.getText() + ".txt";
            String electrodomesticoBD = rutaelectrodomestico + gas + cedula + "_electrodomesticos" + gas;
            File rutaelectrodomestico = new File(electrodomesticoBD, nserie);

            if (rutaelectrodomestico.exists()) {
                String gastoBD = rutaelectrodomestico.getAbsolutePath() + "_gastos" + gas;
                File creargasto = new File(gastoBD);
                File gastoGuardar = new File(creargasto, gastos.fecha + ".txt");

                try {
                    if (creargasto.exists()) {
                        gastoGuardar.createNewFile();
                        Writer escritorDeArchivo = new FileWriter(gastoGuardar);
                        String registrogasto = "gasto: " + gastos.gasto + "\n";
                        registrogasto += "fecha: " + gastos.fecha + "\n";
                        escritorDeArchivo.write(registrogasto);
                        escritorDeArchivo.flush();
                        escritorDeArchivo.close();
                        mostrarGastosEnTabla(cedula);

                        JOptionPane.showMessageDialog(rootPane, "gasto registrado");

                    } else {
                        creargasto.mkdirs();
                        gastoGuardar.createNewFile();
                        Writer escritorDeArchivo = new FileWriter(gastoGuardar);
                        String registrogasto = "gasto: " + gastos.gasto + "\n";
                        registrogasto += "fecha: " + gastos.fecha + "\n";
                        escritorDeArchivo.write(registrogasto);
                        escritorDeArchivo.flush();
                        escritorDeArchivo.close();
                        mostrarGastosEnTabla(cedula);

                        JOptionPane.showMessageDialog(rootPane, "gasto registrado");

                    }
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Error al registrar gasto: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(rootPane, "El electrodoméstico no existe");
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "El usuario no existe");
        }
    }

    private void mostrarGastosEnTabla(String cedula) {
        String electrodomesticoBD = rutaelectrodomestico + gas + cedula + "_electrodomesticos" + gas;

        File directorioElectrodomesticos = new File(electrodomesticoBD);
        File[] archivos = directorioElectrodomesticos.listFiles();

        if (archivos != null) {
            DefaultTableModel modelo = new DefaultTableModel();
            modelo.addColumn("Nro. de Serie");
            modelo.addColumn("Gasto");
            modelo.addColumn("Fecha");

            for (File archivo : archivos) {
                String numeroSerie = archivo.getName();
                String rutaGastos = electrodomesticoBD + gas + numeroSerie + "_gastos" + gas;

                File directorioGastos = new File(rutaGastos);
                File[] archivosGastos = directorioGastos.listFiles();

                if (archivosGastos != null) {
                    for (File archivoGasto : archivosGastos) {
                        try {
                            BufferedReader lectorGastos = new BufferedReader(new FileReader(archivoGasto.getAbsolutePath()));
                            String linea;
                            String gasto = "";
                            String fecha = "";

                            while ((linea = lectorGastos.readLine()) != null) {
                                if (linea.startsWith("gasto:")) {
                                    gasto = linea.substring(7);
                                } else if (linea.startsWith("fecha:")) {
                                    fecha = linea.substring(7);
                                }
                            }

                            modelo.addRow(new Object[]{numeroSerie, gasto, fecha});
                            lectorGastos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            gastosTable.setModel(modelo);
        } else {
            JOptionPane.showMessageDialog(rootPane, "No hay electrodomésticos registrados.");
        }
    }

    private void calcularPromedioGastos() {
        String cedula = txtCedula.getText();
        String archivoUsuario = cedula + ".txt";
        File rutaArchivo = new File(crearblock + archivoUsuario);

        if (rutaArchivo.exists()) {
            String nserie = txtNroSerie.getText() + ".txt";
            String electrodomesticoBD = rutaelectrodomestico + gas + cedula + "_electrodomesticos" + gas;
            File rutaelectrodomestico = new File(electrodomesticoBD, nserie);

            if (rutaelectrodomestico.exists()) {
                String rutaGastos = electrodomesticoBD + gas + nserie + "_gastos" + gas;

                File directorioGastos = new File(rutaGastos);
                File[] archivosGastos = directorioGastos.listFiles();

                if (archivosGastos != null && archivosGastos.length > 0) {
                    double sumaGastos = 0;
                    int cantidadGastos = 0;

                    for (File archivoGasto : archivosGastos) {
                        try {
                            BufferedReader lectorGastos = new BufferedReader(new FileReader(archivoGasto.getAbsolutePath()));
                            String linea;
                            String gasto = "";

                            while ((linea = lectorGastos.readLine()) != null) {
                                if (linea.startsWith("gasto:")) {
                                    gasto = linea.substring(7);
                                    sumaGastos += Double.parseDouble(gasto);
                                    cantidadGastos++;
                                }
                            }

                            lectorGastos.close();
                        } catch (IOException | NumberFormatException e) {
                            e.printStackTrace();
                        }
                    }

                    if (cantidadGastos > 0) {
                        double promedio = sumaGastos / cantidadGastos;
                        JOptionPane.showMessageDialog(rootPane, "El promedio de gastos es: " + promedio);
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "No hay gastos registrados para calcular el promedio.");
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "No hay archivos de gastos para calcular el promedio.");
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "El electrodoméstico no existe");
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "El usuario no existe");
        }
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
        gastosTable = new javax.swing.JTable();
        txtGastos = new javax.swing.JTextField();
        txtFecha = new javax.swing.JTextField();
        txtCedula = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        RegresarVentana = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        txtNroSerie = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        Guardar = new javax.swing.JButton();
        Mostrar = new javax.swing.JButton();
        Editar = new javax.swing.JButton();
        Eliminar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        background.setBackground(new java.awt.Color(204, 204, 204));
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

        gastosTable.setBackground(new java.awt.Color(204, 204, 204));
        gastosTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102), 3));
        gastosTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nro. de serie", "Marca", "Fecha"
            }
        ));
        gastosTable.setInheritsPopupMenu(true);
        gastosTable.setSelectionBackground(new java.awt.Color(0, 102, 204));
        jScrollPane1.setViewportView(gastosTable);

        background.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 90, 540, 190));

        txtGastos.setBackground(new java.awt.Color(204, 204, 204));
        txtGastos.setBorder(null);
        background.add(txtGastos, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 396, 190, 20));

        txtFecha.setBackground(new java.awt.Color(204, 204, 204));
        txtFecha.setBorder(null);
        background.add(txtFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 440, 190, -1));

        txtCedula.setBackground(new java.awt.Color(204, 204, 204));
        txtCedula.setBorder(null);
        background.add(txtCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 320, 190, -1));

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        RegresarVentana.setBackground(new java.awt.Color(102, 102, 102));
        RegresarVentana.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        RegresarVentana.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectoaula/imagenes/atras.png"))); // NOI18N
        RegresarVentana.setText("Regresar");
        RegresarVentana.setBorder(null);
        RegresarVentana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegresarVentanaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(54, Short.MAX_VALUE)
                .addComponent(RegresarVentana, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(448, Short.MAX_VALUE)
                .addComponent(RegresarVentana, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(92, 92, 92))
        );

        background.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 580));

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator1.setForeground(new java.awt.Color(51, 51, 51));
        background.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 340, 190, 10));

        jSeparator3.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator3.setForeground(new java.awt.Color(51, 51, 51));
        background.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 460, 190, -1));

        jSeparator2.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator2.setForeground(new java.awt.Color(51, 51, 51));
        background.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 420, 190, -1));

        txtNroSerie.setBackground(new java.awt.Color(204, 204, 204));
        txtNroSerie.setBorder(null);
        background.add(txtNroSerie, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 360, 190, -1));

        jSeparator4.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));
        background.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 380, 190, 10));

        Guardar.setBackground(new java.awt.Color(204, 204, 204));
        Guardar.setFont(new java.awt.Font("Lucida Sans", 0, 14)); // NOI18N
        Guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectoaula/imagenes/salvar.png"))); // NOI18N
        Guardar.setText("Guardar");
        Guardar.setBorder(null);
        Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuardarActionPerformed(evt);
            }
        });
        background.add(Guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 300, 120, 40));

        Mostrar.setBackground(new java.awt.Color(204, 204, 204));
        Mostrar.setFont(new java.awt.Font("Lucida Sans", 0, 14)); // NOI18N
        Mostrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectoaula/imagenes/mostrar.png"))); // NOI18N
        Mostrar.setText("Mostrar");
        Mostrar.setBorder(null);
        Mostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MostrarActionPerformed(evt);
            }
        });
        background.add(Mostrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 350, 120, 40));

        Editar.setBackground(new java.awt.Color(204, 204, 204));
        Editar.setFont(new java.awt.Font("Lucida Sans", 0, 14)); // NOI18N
        Editar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectoaula/imagenes/editar-codigo.png"))); // NOI18N
        Editar.setText("Editar");
        Editar.setBorder(null);
        Editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditarActionPerformed(evt);
            }
        });
        background.add(Editar, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 400, 120, 40));

        Eliminar.setBackground(new java.awt.Color(204, 204, 204));
        Eliminar.setFont(new java.awt.Font("Lucida Sans", 0, 14)); // NOI18N
        Eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectoaula/imagenes/borrar.png"))); // NOI18N
        Eliminar.setText("Eliminar");
        Eliminar.setBorder(null);
        Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarActionPerformed(evt);
            }
        });
        background.add(Eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 450, 120, 40));

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        jLabel8.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Nro.Serie:");

        jLabel3.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Cédula:");

        jLabel6.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Gasto:");

        jLabel7.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Fecha/Hora:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(17, 17, 17)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(213, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        background.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 290, 320, 200));

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

    private void RegresarVentanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegresarVentanaActionPerformed
        Ventana abc = new Ventana();
        abc.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_RegresarVentanaActionPerformed

    private void GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardarActionPerformed

        if (!txtCedula.getText().isBlank() || !txtCedula.getText().isEmpty()) {
            if (!txtNroSerie.getText().isBlank() || !txtNroSerie.getText().isEmpty()) {
                if (!txtFecha.getText().isBlank() || !txtFecha.getText().isEmpty() || !txtGastos.getText().isBlank() || !txtGastos.getText().isEmpty()) {
                    if (txtGastos.getText().matches("\\d+(\\.\\d+)?")) {
                        String fecha = txtFecha.getText();
                        double consumo = Double.parseDouble(txtGastos.getText());
                        gastos.fecha = fecha;
                        gastos.gasto = consumo;
                        guardarGastos(gastos);
                        txtFecha.setText("");
                        txtGastos.setText("");
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "el campo de texto para gastos no puede ser un digito diferente de los numericos");
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "ingrese la fecha(AA/MM/DD) y/o el gasto del electrodomestico");
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "ingrese el numero de serie del electrodomestico");
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "ingrese la cedula del usuarios");
        }

    }//GEN-LAST:event_GuardarActionPerformed

    private void MostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MostrarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MostrarActionPerformed

    private void EditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EditarActionPerformed

    private void EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EliminarActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaGastos().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Editar;
    private javax.swing.JButton Eliminar;
    private javax.swing.JButton Guardar;
    private javax.swing.JButton Mostrar;
    private javax.swing.JButton RegresarVentana;
    private javax.swing.JPanel background;
    private javax.swing.JTable gastosTable;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelIcon;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtGastos;
    private javax.swing.JTextField txtNroSerie;
    // End of variables declaration//GEN-END:variables

}
