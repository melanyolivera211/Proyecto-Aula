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
import proyectoaula.objects.Gastos;

public class Ventana extends javax.swing.JFrame {

    Electrodomestico electrodomestico = new Electrodomestico();
    String elec = File.separator;
    String crearblock = System.getProperty("user.dir") + elec + "UsuariosBD" + elec;
    String rutaelectrodomestico = "UsuariosBD";
    Gastos gastos = new Gastos();
    private DefaultTableModel modelo;

    public Ventana() {

        initComponents();
    }

    private void crearElectrodomestico(Electrodomestico electrodomestico) {
        String cedula = txtCedula1.getText();
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
        String cedula = txtCedula1.getText();
        String archivoUsuario = cedula + ".txt";
        File rutaArchivo = new File(crearblock + archivoUsuario);
        if (rutaArchivo.exists()) {
            String nserie = txtNroSerie1.getText() + ".txt";
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
                    txtElectrodomestico.setText(electrodomestico.nombreE);
                    txtNroSerie1.setText(electrodomestico.nroSerie);
                    txtMarca.setText(electrodomestico.marca);
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
        String cedula = txtCedula1.getText();
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
                        } else if (linea.startsWith("nro.serie:") && !txtNroSerie1.getText().equals("")) {
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
        String cedula = txtCedula1.getText();
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
        String cedula = txtCedula1.getText();
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

    public void limpiarCampos() {
        txtElectrodomestico.setText("");
        txtNroSerie1.setText("");
        txtMarca.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        RegresarVentana = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtGastos = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        Guardar = new javax.swing.JButton();
        Mostrar1 = new javax.swing.JButton();
        Editar = new javax.swing.JButton();
        Eliminar = new javax.swing.JButton();
        jSeparator6 = new javax.swing.JSeparator();
        txtFecha = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtCedula2 = new javax.swing.JTextField();
        jSeparator7 = new javax.swing.JSeparator();
        txtNroSerie2 = new javax.swing.JTextField();
        jSeparator8 = new javax.swing.JSeparator();
        jScrollPane9 = new javax.swing.JScrollPane();
        gastosTable = new rojerusan.RSTableMetro();
        jLabel14 = new javax.swing.JLabel();
        calcular = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtCedula1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tablaelectrodomestico = new rojerusan.RSTableMetro();
        jSeparator1 = new javax.swing.JSeparator();
        txtElectrodomestico = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        txtNroSerie1 = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        txtMarca = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        botonEditar = new javax.swing.JButton();
        botonGuardar = new javax.swing.JButton();
        Mostrar = new javax.swing.JButton();
        botonEliminar = new javax.swing.JButton();
        botonBuscar = new javax.swing.JButton();
        Cancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(66, 66, 66));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Lucida Sans", 1, 36)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("GESTIÓN DE ELECTRODOMÉSTICOS");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 670, 50));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 0, 1050, 70));

        jPanel3.setBackground(new java.awt.Color(66, 66, 66));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBackground(new java.awt.Color(66, 66, 66));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectoaula/imagenes/mano.png"))); // NOI18N
        jLabel8.setText("Administrar gastos");
        jPanel7.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, -1, -1));

        jPanel3.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 320, 90));

        jPanel8.setBackground(new java.awt.Color(66, 66, 66));
        jPanel8.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jPanel8AncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectoaula/imagenes/ele.png"))); // NOI18N
        jLabel4.setText("Explorar electrodomésticos");
        jPanel8.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 310, 70));

        jPanel3.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 208, 320, 90));

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
        jPanel3.add(RegresarVentana, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 490, 140, 50));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 320, 650));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Cédula:");
        jPanel9.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 70, 30));

        txtGastos.setBorder(null);
        jPanel9.add(txtGastos, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 70, 150, 30));

        jSeparator5.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator5.setForeground(new java.awt.Color(51, 51, 51));
        jPanel9.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 100, 150, 20));

        Guardar.setFont(new java.awt.Font("Lucida Sans", 0, 14)); // NOI18N
        Guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectoaula/imagenes/salvar.png"))); // NOI18N
        Guardar.setText("Guardar");
        Guardar.setBorder(null);
        Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuardarActionPerformed(evt);
            }
        });
        jPanel9.add(Guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 420, 120, 40));

        Mostrar1.setFont(new java.awt.Font("Lucida Sans", 0, 14)); // NOI18N
        Mostrar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectoaula/imagenes/mostrar.png"))); // NOI18N
        Mostrar1.setText("Mostrar");
        Mostrar1.setBorder(null);
        Mostrar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Mostrar1ActionPerformed(evt);
            }
        });
        jPanel9.add(Mostrar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 420, 120, 40));

        Editar.setFont(new java.awt.Font("Lucida Sans", 0, 14)); // NOI18N
        Editar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectoaula/imagenes/editar-codigo.png"))); // NOI18N
        Editar.setText("Editar");
        Editar.setBorder(null);
        Editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditarActionPerformed(evt);
            }
        });
        jPanel9.add(Editar, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 470, 120, 40));

        Eliminar.setFont(new java.awt.Font("Lucida Sans", 0, 14)); // NOI18N
        Eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectoaula/imagenes/borrar.png"))); // NOI18N
        Eliminar.setText("Eliminar");
        Eliminar.setBorder(null);
        Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarActionPerformed(evt);
            }
        });
        jPanel9.add(Eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 470, 120, 40));

        jSeparator6.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator6.setForeground(new java.awt.Color(0, 0, 0));
        jPanel9.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 180, 150, 20));

        txtFecha.setBorder(null);
        txtFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaActionPerformed(evt);
            }
        });
        jPanel9.add(txtFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 150, 150, 30));

        jLabel10.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Nro.Serie:");
        jPanel9.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 80, -1));

        jLabel11.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Gasto:");
        jPanel9.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 40, 60, 20));

        jLabel12.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Fecha/Hora:");
        jPanel9.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 130, 90, -1));

        txtCedula2.setBorder(null);
        jPanel9.add(txtCedula2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 150, 30));

        jSeparator7.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator7.setForeground(new java.awt.Color(51, 51, 51));
        jPanel9.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 150, 20));

        txtNroSerie2.setBorder(null);
        jPanel9.add(txtNroSerie2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 150, 30));

        jSeparator8.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator8.setForeground(new java.awt.Color(0, 0, 0));
        jPanel9.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 150, 20));

        gastosTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, "", "", null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Electrodoméstico", "Nro.serie", "Gasto", "Fecha"
            }
        ));
        gastosTable.setAltoHead(25);
        gastosTable.setColorBackgoundHead(new java.awt.Color(66, 66, 66));
        gastosTable.setColorFilasBackgound2(new java.awt.Color(240, 240, 240));
        gastosTable.setColorFilasForeground1(new java.awt.Color(51, 51, 51));
        gastosTable.setColorFilasForeground2(new java.awt.Color(51, 51, 51));
        gastosTable.setFuenteFilas(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        gastosTable.setFuenteHead(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        gastosTable.setGrosorBordeFilas(0);
        gastosTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                gastosTableMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(gastosTable);

        jPanel9.add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 20, 570, 390));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectoaula/imagenes/energia.png"))); // NOI18N
        jPanel9.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 200, 270, 270));

        calcular.setFont(new java.awt.Font("Lucida Sans", 0, 14)); // NOI18N
        calcular.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectoaula/imagenes/calcular.png"))); // NOI18N
        calcular.setText("calcular");
        calcular.setBorder(null);
        calcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calcularActionPerformed(evt);
            }
        });
        jPanel9.add(calcular, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 420, 120, 40));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 1058, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Registrar Gasto", jPanel6);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectoaula/imagenes/electrodomesticos.png"))); // NOI18N
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 240, 270, 260));

        jLabel5.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("Cédula:");
        jLabel5.setPreferredSize(new java.awt.Dimension(60, 23));
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 190, -1));

        txtCedula1.setFont(new java.awt.Font("Lucida Sans", 0, 14)); // NOI18N
        txtCedula1.setBorder(null);
        txtCedula1.setMinimumSize(new java.awt.Dimension(60, 23));
        jPanel4.add(txtCedula1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 150, 30));

        jLabel2.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Electrodomestico:");
        jLabel2.setPreferredSize(new java.awt.Dimension(60, 23));
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 190, -1));

        jLabel1.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("Nro. Serie:");
        jLabel1.setPreferredSize(new java.awt.Dimension(60, 23));
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 50, 190, -1));

        jLabel3.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Marca:");
        jLabel3.setPreferredSize(new java.awt.Dimension(60, 23));
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 130, 190, -1));

        tablaelectrodomestico.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {"", "", null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Electrodoméstico", "Nro.serie", "Marca"
            }
        ));
        tablaelectrodomestico.setAltoHead(25);
        tablaelectrodomestico.setColorBackgoundHead(new java.awt.Color(66, 66, 66));
        tablaelectrodomestico.setColorFilasBackgound2(new java.awt.Color(240, 240, 240));
        tablaelectrodomestico.setColorFilasForeground1(new java.awt.Color(51, 51, 51));
        tablaelectrodomestico.setColorFilasForeground2(new java.awt.Color(51, 51, 51));
        tablaelectrodomestico.setFuenteFilas(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tablaelectrodomestico.setFuenteHead(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tablaelectrodomestico.setGrosorBordeFilas(0);
        tablaelectrodomestico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaelectrodomesticoMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tablaelectrodomestico);

        jPanel4.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 20, 580, 390));
        jPanel4.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 150, 20));

        txtElectrodomestico.setFont(new java.awt.Font("Lucida Sans", 0, 14)); // NOI18N
        txtElectrodomestico.setBorder(null);
        txtElectrodomestico.setMinimumSize(new java.awt.Dimension(60, 23));
        jPanel4.add(txtElectrodomestico, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 150, 30));
        jPanel4.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 150, 20));

        txtNroSerie1.setFont(new java.awt.Font("Lucida Sans", 0, 14)); // NOI18N
        txtNroSerie1.setBorder(null);
        txtNroSerie1.setMinimumSize(new java.awt.Dimension(60, 23));
        txtNroSerie1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNroSerie1ActionPerformed(evt);
            }
        });
        jPanel4.add(txtNroSerie1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 80, 160, 30));
        jPanel4.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 110, 160, 20));

        txtMarca.setFont(new java.awt.Font("Lucida Sans", 0, 14)); // NOI18N
        txtMarca.setBorder(null);
        txtMarca.setMinimumSize(new java.awt.Dimension(60, 23));
        jPanel4.add(txtMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 160, 160, 30));
        jPanel4.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 190, 160, 20));

        botonEditar.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        botonEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectoaula/imagenes/editar-codigo.png"))); // NOI18N
        botonEditar.setText("Editar");
        botonEditar.setBorder(null);
        botonEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEditarActionPerformed(evt);
            }
        });
        jPanel4.add(botonEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 480, 100, 40));

        botonGuardar.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        botonGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectoaula/imagenes/salvar.png"))); // NOI18N
        botonGuardar.setText("Guardar");
        botonGuardar.setBorder(null);
        botonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGuardarActionPerformed(evt);
            }
        });
        jPanel4.add(botonGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 420, 110, 40));

        Mostrar.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        Mostrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectoaula/imagenes/mostrar.png"))); // NOI18N
        Mostrar.setText("Mostrar");
        Mostrar.setBorder(null);
        Mostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MostrarActionPerformed(evt);
            }
        });
        jPanel4.add(Mostrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 420, 110, 40));

        botonEliminar.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        botonEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectoaula/imagenes/borrar.png"))); // NOI18N
        botonEliminar.setText("Eliminar");
        botonEliminar.setBorder(null);
        botonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEliminarActionPerformed(evt);
            }
        });
        jPanel4.add(botonEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 480, 100, 40));

        botonBuscar.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        botonBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectoaula/imagenes/buscar.png"))); // NOI18N
        botonBuscar.setText("Buscar");
        botonBuscar.setBorder(null);
        botonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonBuscarActionPerformed(evt);
            }
        });
        jPanel4.add(botonBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 420, 110, 40));

        Cancelar.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        Cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectoaula/imagenes/cancelado.png"))); // NOI18N
        Cancelar.setText("Cancelar");
        Cancelar.setBorder(null);
        Cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelarActionPerformed(evt);
            }
        });
        jPanel4.add(Cancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 480, 110, 40));

        jTabbedPane1.addTab("Registrar Electrodomestico", jPanel4);

        jPanel1.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 70, 1050, 580));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel8AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jPanel8AncestorAdded
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_jPanel8AncestorAdded

    private void guardarGastos(Gastos gastos) {
        String cedula = txtCedula2.getText();
        String archivoUsuario = cedula + ".txt";
        File rutaArchivo = new File(crearblock + archivoUsuario);

        if (rutaArchivo.exists()) {
            String nserie = txtNroSerie2.getText() + ".txt";
            String electrodomesticoBD = rutaelectrodomestico + elec + cedula + "_electrodomesticos" + elec;
            File rutaelectrodomestico = new File(electrodomesticoBD, nserie);

            if (rutaelectrodomestico.exists()) {
                String verifi = gastos.fecha + ".txt";
                String gastoBD = rutaelectrodomestico.getAbsolutePath() + "_gastos" + elec;
                File creargasto = new File(gastoBD);
                File gastoGuardar = new File(creargasto, verifi);

                if (gastoGuardar.exists()) {
                    JOptionPane.showMessageDialog(rootPane, "gasto ya existe");
                } else {
                    try {
                        if (creargasto.exists()) {
                            gastoGuardar.createNewFile();
                            Writer escritorDeArchivo = new FileWriter(gastoGuardar);
                            String registrogasto = "gasto: " + gastos.gasto + "\n";
                            registrogasto += "fecha: " + gastos.fecha + "\n";
                            escritorDeArchivo.write(registrogasto);
                            escritorDeArchivo.flush();
                            escritorDeArchivo.close();
                            mostrarGastosEnTabla(cedula, nserie);

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
                            mostrarGastosEnTabla(cedula, nserie);

                            JOptionPane.showMessageDialog(rootPane, "gasto registrado");

                        }
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(null, "Error al registrar gasto: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "El electrodoméstico no existe");
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "El usuario no existe");
        }
    }

    private void mostrarGastosEnTabla(String cedula, String nserie) {
        String electrodomesticoBD = rutaelectrodomestico + elec + cedula + "_electrodomesticos" + elec;

        File directorioElectrodomesticos = new File(electrodomesticoBD);
        File[] archivos = directorioElectrodomesticos.listFiles();

        if (archivos != null) {
            DefaultTableModel modelo = new DefaultTableModel();
            modelo.addColumn("Electrodoméstico");
            modelo.addColumn("Nro. de Serie");
            modelo.addColumn("Gasto");
            modelo.addColumn("Fecha");

            for (File archivo : archivos) {
                String numeroSerie = archivo.getName();
                String rutaGastos = electrodomesticoBD + elec + numeroSerie + "_gastos" + elec;

                File directorioGastos = new File(rutaGastos);
                File[] archivosGastos = directorioGastos.listFiles();

                if (archivosGastos != null) {
                    for (File archivoGasto : archivosGastos) {
                        try {
                            BufferedReader lectoreelctrodomestico = new BufferedReader(new FileReader(electrodomesticoBD + nserie));
                            String linea;
                            String electro = "";
                            String nroserie = "";
                            while ((linea = lectoreelctrodomestico.readLine()) != null) {
                                if (linea.startsWith("Electrodomestico:")) {
                                    electro = linea.substring(18);
                                } else if (linea.startsWith("nro.serie:")) {
                                    nroserie = linea.substring(11);
                                }
                            }
                            lectoreelctrodomestico.close();

                            BufferedReader lectorGastos = new BufferedReader(new FileReader(archivoGasto.getAbsolutePath()));
                            String gasto = "";
                            String fecha = "";

                            while ((linea = lectorGastos.readLine()) != null) {
                                if (linea.startsWith("gasto:")) {
                                    gasto = linea.substring(7);
                                } else if (linea.startsWith("fecha:")) {
                                    fecha = linea.substring(7);
                                }
                            }

                            modelo.addRow(new Object[]{electro, nroserie, gasto, fecha});
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
        String cedula = txtCedula2.getText();
        String archivoUsuario = cedula + ".txt";
        File rutaArchivo = new File(crearblock + archivoUsuario);

        if (rutaArchivo.exists()) {
            String nserie = txtNroSerie2.getText() + ".txt";
            String electrodomesticoBD = rutaelectrodomestico + elec + cedula + "_electrodomesticos" + elec;
            File rutaelectrodomestico = new File(electrodomesticoBD, nserie);

            if (rutaelectrodomestico.exists()) {
                String rutaGastos = electrodomesticoBD + elec + nserie + "_gastos" + elec;

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

    private void mostrarGastosEnTablaboton(String cedula, String numeroSerie) {
        String electrodomesticoBD = rutaelectrodomestico + elec + cedula + "_electrodomesticos" + elec;

        File directorioElectrodomesticos = new File(electrodomesticoBD);

        if (directorioElectrodomesticos.exists()) {

            String rutagasto = electrodomesticoBD + elec + numeroSerie + ".txt" + "_gastos" + elec;
            File directoriogasto = new File(rutagasto);
            File[] archivosGastos = directoriogasto.listFiles();
            if (directoriogasto.exists()) {
                DefaultTableModel modelo = new DefaultTableModel();
                modelo.addColumn("Electrodoméstico");
                modelo.addColumn("Nro. de Serie");
                modelo.addColumn("Gasto");
                modelo.addColumn("Fecha");
                for (File archivoGasto : archivosGastos) {
                    try {
                        BufferedReader lectoreelctrodomestico = new BufferedReader(new FileReader(electrodomesticoBD + numeroSerie + ".txt"));
                        String linea;
                        String electro = "";
                        while ((linea = lectoreelctrodomestico.readLine()) != null) {
                            if (linea.startsWith("Electrodomestico:")) {
                                electro = linea.substring(18);
                            }
                        }
                        lectoreelctrodomestico.close();

                        BufferedReader lectorGastos = new BufferedReader(new FileReader(archivoGasto.getAbsolutePath()));
                        String gasto = "";
                        String fecha = "";

                        while ((linea = lectorGastos.readLine()) != null) {
                            if (linea.startsWith("gasto:")) {
                                gasto = linea.substring(7);
                            } else if (linea.startsWith("fecha:")) {
                                fecha = linea.substring(7);
                            }
                        }

                        modelo.addRow(new Object[]{electro, numeroSerie, gasto, fecha});
                        lectorGastos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    gastosTable.setModel(modelo);
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "electrodoméstico no registrado");
            }

        } else {
            JOptionPane.showMessageDialog(rootPane, "usuario no registrado");
        }
    }

    private void editargastos(Gastos gastos) {
        String cedula = txtCedula2.getText();
        String archivoUsuario = cedula + ".txt";
        File rutaArchivo = new File(crearblock + archivoUsuario);

        if (rutaArchivo.exists()) {
            String nserie = txtNroSerie2.getText() + ".txt";
            String electrodomesticoBD = rutaelectrodomestico + elec + txtCedula2.getText() + "_electrodomesticos" + elec;
            File rutaelectrodomesti = new File(electrodomesticoBD, nserie);

            if (rutaelectrodomesti.exists()) {
                String rutagasto = electrodomesticoBD + elec + nserie + "_gastos" + elec;
                File gastoBD = new File(rutagasto, gastos.fecha + ".txt");
                if (gastoBD.exists()) {
                    try {
                        BufferedReader lector = new BufferedReader(new FileReader(gastoBD.getAbsolutePath()));
                        String linea;
                        StringBuilder datos = new StringBuilder();
                        while ((linea = lector.readLine()) != null) {
                            if (linea.startsWith("gasto:") && !txtGastos.getText().equals("")) {
                                datos.append("gasto: ").append(gastos.gasto).append("\n");
                            } else if (linea.startsWith("fecha:") && !txtFecha.getText().equals("")) {
                                datos.append("fecha: ").append(gastos.fecha).append("\n");
                            } else {
                                datos.append(linea).append("\n");
                            }
                        }
                        lector.close();
                        BufferedWriter escritor = new BufferedWriter(new FileWriter(gastoBD.getAbsolutePath()));
                        escritor.write(datos.toString());
                        escritor.close();
                        JOptionPane.showMessageDialog(rootPane, "¡El archivo ha sido editado con éxito!");
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(rootPane, "No se ha podido el gasto");
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "El gasto no existe");
                }

            } else {
                JOptionPane.showMessageDialog(rootPane, "El electrodoméstico no existe");
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "El usuario no existe");
        }
    }

    private void eliminargastos(Gastos gastos) {
        String cedula = txtCedula2.getText();
        String archivoUsuario = cedula + ".txt";
        File rutaArchivo = new File(crearblock + archivoUsuario);

        if (rutaArchivo.exists()) {
            String nserie = txtNroSerie2.getText() + ".txt";
            String electrodomesticoBD = rutaelectrodomestico + elec + txtCedula2.getText() + "_electrodomesticos" + elec;
            File rutaelectrodomesti = new File(electrodomesticoBD, nserie);

            if (rutaelectrodomesti.exists()) {
                String rutagasto = electrodomesticoBD + elec + nserie + "_gastos" + elec;
                File gastoBD = new File(rutagasto, gastos.fecha + ".txt");
                if (gastoBD.exists()) {
                    try {
                        if (gastoBD.delete()) {
                            JOptionPane.showMessageDialog(rootPane, "el gasto a sido eliminado con exito");
                        } else {
                            JOptionPane.showMessageDialog(rootPane, "el gasto no se a podido eliminar");
                        }
                        JOptionPane.showMessageDialog(rootPane, "¡El archivo ha sido eliminado con éxito!");
                    } catch (SecurityException e) {
                        JOptionPane.showMessageDialog(rootPane, "No se ha podido el gasto");
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "El gasto no existe");
                }

            } else {
                JOptionPane.showMessageDialog(rootPane, "El electrodoméstico no existe");
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "El usuario no existe");
        }

    }

    private void RegresarVentanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegresarVentanaActionPerformed
        VentanaLogin abc = new VentanaLogin();
        abc.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_RegresarVentanaActionPerformed

    private void CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelarActionPerformed
        txtElectrodomestico.setText("");
        txtNroSerie1.setText("");
        txtMarca.setText("");
    }//GEN-LAST:event_CancelarActionPerformed

    private void botonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonBuscarActionPerformed
        String cedula = txtCedula1.getText();
        String nroserie = txtNroSerie1.getText();
        if (cedula.isEmpty() || cedula.isBlank() || nroserie.isEmpty() || nroserie.isBlank()) {
            JOptionPane.showMessageDialog(rootPane, "Uno o mas campos de texto están vacíos favor de rellenarlos");
        } else {
            buscarElectrodomestico();

        }
    }//GEN-LAST:event_botonBuscarActionPerformed

    private void botonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarActionPerformed
        eliminarElectrodomestico(electrodomestico);
    }//GEN-LAST:event_botonEliminarActionPerformed

    private void MostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MostrarActionPerformed
        String cedula = txtCedula1.getText();
        if (cedula.isEmpty() || cedula.isBlank()) {
            JOptionPane.showMessageDialog(rootPane, "Debe ingresar la cedula");
        } else {
            mostrarElectrodomesticosEnTabla();
            limpiarCampos();
        }
    }//GEN-LAST:event_MostrarActionPerformed

    private void botonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGuardarActionPerformed
        String nombreElectrodomestico = txtElectrodomestico.getText();
        String nroSerie = txtNroSerie1.getText();
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
            limpiarCampos();
        }
    }//GEN-LAST:event_botonGuardarActionPerformed

    private void botonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEditarActionPerformed
        String cedula = txtCedula1.getText();
        String nombreElectrodomestico = txtElectrodomestico.getText();
        String nroSerie = txtNroSerie1.getText();
        String marca = txtMarca.getText();
        if (nroSerie.isEmpty() || nroSerie.isBlank() || cedula.isEmpty() || cedula.isBlank()) {
            JOptionPane.showMessageDialog(rootPane, "el espacio para ingresar la cedula o el nro.serie esta/n vacios, favor rellenar ambos para buscar el electrodomestico");
        } else {
            electrodomestico.nombreE = nombreElectrodomestico;
            electrodomestico.nroSerie = nroSerie;
            electrodomestico.marca = marca;
            editarElectrodomestico(electrodomestico);

            txtElectrodomestico.setText("");
            txtNroSerie1.setText("");
            txtMarca.setText("");
        }
    }//GEN-LAST:event_botonEditarActionPerformed

    private void txtNroSerie1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNroSerie1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNroSerie1ActionPerformed

    private void tablaelectrodomesticoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaelectrodomesticoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaelectrodomesticoMouseClicked

    private void calcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calcularActionPerformed
        calcularPromedioGastos();
    }//GEN-LAST:event_calcularActionPerformed

    private void gastosTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gastosTableMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_gastosTableMouseClicked

    private void txtFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaActionPerformed

    private void EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarActionPerformed
        if (!txtCedula2.getText().isBlank() || !txtCedula2.getText().isEmpty()) {
            if (!txtNroSerie2.getText().isBlank() || !txtNroSerie2.getText().isEmpty()) {
                if (!txtFecha.getText().isBlank() || !txtFecha.getText().isEmpty()) {
                    gastos.fecha = txtFecha.getText();
                    eliminargastos(gastos);
                } else {
                    JOptionPane.showMessageDialog(rootPane, "ingrese la fecha(AA/MM/DD) y/o el gasto del electrodomestico");
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "ingrese el numero de serie del electrodomestico");
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "ingrese la cedula del usuarios");
        }
    }//GEN-LAST:event_EliminarActionPerformed

    private void EditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarActionPerformed
        if (!txtCedula2.getText().isBlank() || !txtCedula2.getText().isEmpty()) {
            if (!txtNroSerie2.getText().isBlank() || !txtNroSerie2.getText().isEmpty()) {
                if (!txtFecha.getText().isBlank() || !txtFecha.getText().isEmpty() || !txtGastos.getText().isBlank() || !txtGastos.getText().isEmpty()) {
                    if (txtGastos.getText().matches("\\d+(\\.\\d+)?")) {
                        String fecha = txtFecha.getText();
                        double consumo = Double.parseDouble(txtGastos.getText());
                        gastos.fecha = fecha;
                        gastos.gasto = consumo;
                        editargastos(gastos);
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
    }//GEN-LAST:event_EditarActionPerformed

    private void Mostrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Mostrar1ActionPerformed
        String cedula = txtCedula2.getText();
        String numeroSerie = txtNroSerie2.getText();

        if (!cedula.isEmpty() || cedula.isBlank()) {
            if (!numeroSerie.isEmpty() || numeroSerie.isBlank()) {
                mostrarGastosEnTablaboton(cedula, numeroSerie);
            } else {
                JOptionPane.showMessageDialog(rootPane, "Ingrese el número de serie antes de mostrar los gastos.");
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Ingrese la cedula");

        }
    }//GEN-LAST:event_Mostrar1ActionPerformed

    private void GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardarActionPerformed

        if (!txtCedula2.getText().isBlank() || !txtCedula2.getText().isEmpty()) {
            if (!txtNroSerie2.getText().isBlank() || !txtNroSerie2.getText().isEmpty()) {
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
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cancelar;
    private javax.swing.JButton Editar;
    private javax.swing.JButton Eliminar;
    private javax.swing.JButton Guardar;
    private javax.swing.JButton Mostrar;
    private javax.swing.JButton Mostrar1;
    private javax.swing.JButton RegresarVentana;
    private javax.swing.JButton botonBuscar;
    private javax.swing.JButton botonEditar;
    private javax.swing.JButton botonEliminar;
    private javax.swing.JButton botonGuardar;
    private javax.swing.JButton calcular;
    private rojerusan.RSTableMetro gastosTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JTabbedPane jTabbedPane1;
    private rojerusan.RSTableMetro tablaelectrodomestico;
    private javax.swing.JTextField txtCedula1;
    private javax.swing.JTextField txtCedula2;
    private javax.swing.JTextField txtElectrodomestico;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtGastos;
    private javax.swing.JTextField txtMarca;
    private javax.swing.JTextField txtNroSerie1;
    private javax.swing.JTextField txtNroSerie2;
    // End of variables declaration//GEN-END:variables
}
