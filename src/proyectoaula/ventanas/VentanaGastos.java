package proyectoaula.ventanas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class VentanaGastos extends javax.swing.JFrame {

    String gas = File.separator;
    String crearblock = System.getProperty("user.dir") + gas + "UsuariosBD" + gas;
    String rutaelectrodomestico = "UsuariosBD";
    private DefaultTableModel modelo;

    public VentanaGastos() {
        initComponents();
    }

    private void guardarGastos() {
        String cedula = cedulaTXT.getText();
        String archivoUsuario = cedula + ".txt";
        File rutaArchivo = new File(crearblock + archivoUsuario);

        if (rutaArchivo.exists()) {
            String nserie = nroserieTXT.getText() + "";
            String electrodomesticoBD = rutaelectrodomestico + gas + cedula + "_electrodomesticos" + gas;
            File rutaelectrodomestico = new File(electrodomesticoBD, nserie);

            if (rutaelectrodomestico.exists()) {
                String fecha = fechaTXT.getText();
                String gastoBD = rutaelectrodomestico.getAbsolutePath() + "_gastos" + gas;
                File creargasto = new File(gastoBD);
                File gastoGuardar = new File(creargasto, fecha + ".txt");

                if (fecha.isBlank() || fecha.isEmpty()) {
                    JOptionPane.showMessageDialog(rootPane, "Debes colocar fecha a registrar (AA/MM/DD)");
                } else {
                    if (!gastoTXT.getText().isEmpty() && gastoTXT.getText().matches("\\d+(\\.\\d+)?")) {
                        double gasto = Double.parseDouble(gastoTXT.getText());
                        try {
                            if (creargasto.exists()) {
                                gastoGuardar.createNewFile();
                                Writer escritorDeArchivo = new FileWriter(gastoGuardar);
                                String registrogasto = "gasto: " + gasto + "\n";
                                registrogasto += "fecha: " + fecha + "\n";
                                escritorDeArchivo.write(registrogasto);
                                escritorDeArchivo.flush();
                                escritorDeArchivo.close();
                                mostrarGastosEnTabla(cedula);

                                JOptionPane.showMessageDialog(rootPane, "gasto registrado");

                            } else {
                                creargasto.mkdirs();
                                gastoGuardar.createNewFile();
                                Writer escritorDeArchivo = new FileWriter(gastoGuardar);
                                String registrogasto = "gasto: " + gasto + "\n";
                                registrogasto += "fecha: " + fecha + "\n";
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
                        JOptionPane.showMessageDialog(rootPane, "Debe colocar un gasto válido para registrar");
                    }
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
        String cedula = cedulaTXT.getText();
        String archivoUsuario = cedula + ".txt";
        File rutaArchivo = new File(crearblock + archivoUsuario);

        if (rutaArchivo.exists()) {
            String nserie = nroserieTXT.getText() + ".txt";
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
        jButtonCalcular = new javax.swing.JButton();
        jButtonGuardar = new javax.swing.JButton();
        gastoTXT = new javax.swing.JTextField();
        RegresarVentana = new javax.swing.JButton();
        fechaTXT = new javax.swing.JTextField();
        cedulaTXT = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        nroserieTXT = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        background.setBackground(new java.awt.Color(255, 255, 255));
        background.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectoaula/imagenes/electronica (1).png"))); // NOI18N
        background.add(jLabelIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 130, 80, 110));

        jLabel2.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("ELECTRODOMESTICOS");
        background.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 190, 100));

        jLabel4.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("ECO-POWER");
        background.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 200, 140, 120));

        jLabel5.setFont(new java.awt.Font("Copperplate Gothic Bold", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(153, 0, 204));
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

        background.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 100, 540, 190));

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
        jButtonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarActionPerformed(evt);
            }
        });
        background.add(jButtonGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 370, 100, 80));

        gastoTXT.setBorder(null);
        background.add(gastoTXT, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 400, 190, -1));

        RegresarVentana.setBackground(new java.awt.Color(153, 0, 204));
        RegresarVentana.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        RegresarVentana.setForeground(new java.awt.Color(255, 255, 255));
        RegresarVentana.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectoaula/imagenes/atras.png"))); // NOI18N
        RegresarVentana.setText("Regresar");
        RegresarVentana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegresarVentanaActionPerformed(evt);
            }
        });
        background.add(RegresarVentana, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 500, 140, 40));

        fechaTXT.setBorder(null);
        background.add(fechaTXT, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 440, 190, -1));

        cedulaTXT.setBorder(null);
        background.add(cedulaTXT, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 320, 190, -1));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 0, 204));
        jLabel3.setText("Cedula:");
        background.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 320, -1, -1));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(153, 0, 204));
        jLabel6.setText("Gasto:");
        background.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 400, -1, -1));

        jLabel7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(153, 0, 204));
        jLabel7.setText("Fecha/Hora:");
        background.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 440, -1, -1));

        jPanel1.setBackground(new java.awt.Color(153, 0, 204));

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

        jSeparator1.setBackground(new java.awt.Color(153, 0, 204));
        jSeparator1.setForeground(new java.awt.Color(153, 0, 204));
        background.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 340, 190, 10));

        jSeparator3.setBackground(new java.awt.Color(153, 0, 204));
        jSeparator3.setForeground(new java.awt.Color(153, 0, 204));
        background.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 460, 190, -1));

        jSeparator2.setBackground(new java.awt.Color(153, 0, 204));
        jSeparator2.setForeground(new java.awt.Color(153, 0, 204));
        background.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 420, 190, -1));

        nroserieTXT.setBorder(null);
        background.add(nroserieTXT, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 360, 190, -1));

        jSeparator4.setBackground(new java.awt.Color(153, 0, 204));
        jSeparator4.setForeground(new java.awt.Color(153, 0, 204));
        background.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 380, 190, 10));

        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(153, 0, 204));
        jLabel8.setText("nro.Serie:");
        background.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 360, -1, -1));

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
        calcularPromedioGastos();
    }//GEN-LAST:event_jButtonCalcularActionPerformed

    private void RegresarVentanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegresarVentanaActionPerformed
        Ventana abc = new Ventana();
        abc.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_RegresarVentanaActionPerformed

    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarActionPerformed
        guardarGastos();
        gastoTXT.setText("");
        fechaTXT.setText("");
    }//GEN-LAST:event_jButtonGuardarActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaGastos().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton RegresarVentana;
    private javax.swing.JPanel background;
    private javax.swing.JTextField cedulaTXT;
    private javax.swing.JTextField fechaTXT;
    private javax.swing.JTextField gastoTXT;
    private javax.swing.JTable gastosTable;
    private javax.swing.JButton jButtonCalcular;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelIcon;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTextField nroserieTXT;
    // End of variables declaration//GEN-END:variables

}
