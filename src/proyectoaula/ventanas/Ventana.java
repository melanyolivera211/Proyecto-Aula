package proyectoaula.ventanas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    public void limpiarCampos() {
        txtElectrodomestico.setText("");
        txtNroSerie.setText("");
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
        jPanel5 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtCedula = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tablaelectrodomestico = new rojerusan.RSTableMetro();
        jSeparator1 = new javax.swing.JSeparator();
        txtElectrodomestico = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        txtNroSerie = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        txtMarca = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        botonEditar = new javax.swing.JButton();
        botonGuardar = new javax.swing.JButton();
        Mostrar = new javax.swing.JButton();
        botonEliminar = new javax.swing.JButton();
        botonBuscar = new javax.swing.JButton();
        Cancelar = new javax.swing.JButton();
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
        tablaGastos = new rojerusan.RSTableMetro();
        jLabel14 = new javax.swing.JLabel();
        Guardar1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(66, 66, 66));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Lucida Sans", 1, 36)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("GESTIÓN DE ELECTRODOMÉSTICOS");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 670, 40));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 0, 1230, 130));

        jPanel3.setBackground(new java.awt.Color(66, 66, 66));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBackground(new java.awt.Color(66, 66, 66));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectoaula/imagenes/mano.png"))); // NOI18N
        jLabel8.setText("Administrar gastos");
        jPanel7.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 244, -1));

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
        jPanel8.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 310, 70));

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
        jPanel3.add(RegresarVentana, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 610, 140, 50));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 320, 710));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1230, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 545, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Inicio", jPanel5);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectoaula/imagenes/electrodomesticos.png"))); // NOI18N
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 240, 270, 260));

        jLabel5.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("Cédula:");
        jLabel5.setPreferredSize(new java.awt.Dimension(60, 23));
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 190, -1));

        txtCedula.setFont(new java.awt.Font("Lucida Sans", 0, 14)); // NOI18N
        txtCedula.setBorder(null);
        txtCedula.setMinimumSize(new java.awt.Dimension(60, 23));
        jPanel4.add(txtCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 190, 30));

        jLabel2.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Electrodomestico:");
        jLabel2.setPreferredSize(new java.awt.Dimension(60, 23));
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 190, -1));

        jLabel1.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("Nro. Serie:");
        jLabel1.setPreferredSize(new java.awt.Dimension(60, 23));
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 50, 190, -1));

        jLabel3.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Marca:");
        jLabel3.setPreferredSize(new java.awt.Dimension(60, 23));
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 130, 190, -1));

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

        jPanel4.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 30, 660, 390));
        jPanel4.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 190, 20));

        txtElectrodomestico.setFont(new java.awt.Font("Lucida Sans", 0, 14)); // NOI18N
        txtElectrodomestico.setBorder(null);
        txtElectrodomestico.setMinimumSize(new java.awt.Dimension(60, 23));
        jPanel4.add(txtElectrodomestico, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 190, 30));
        jPanel4.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 190, 20));

        txtNroSerie.setFont(new java.awt.Font("Lucida Sans", 0, 14)); // NOI18N
        txtNroSerie.setBorder(null);
        txtNroSerie.setMinimumSize(new java.awt.Dimension(60, 23));
        jPanel4.add(txtNroSerie, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 80, 190, 30));
        jPanel4.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 110, 190, 20));

        txtMarca.setFont(new java.awt.Font("Lucida Sans", 0, 14)); // NOI18N
        txtMarca.setBorder(null);
        txtMarca.setMinimumSize(new java.awt.Dimension(60, 23));
        jPanel4.add(txtMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 160, 190, 30));
        jPanel4.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 190, 190, 20));

        botonEditar.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        botonEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectoaula/imagenes/editar-codigo.png"))); // NOI18N
        botonEditar.setText("Editar");
        botonEditar.setBorder(null);
        botonEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEditarActionPerformed(evt);
            }
        });
        jPanel4.add(botonEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 490, 100, 40));

        botonGuardar.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        botonGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectoaula/imagenes/salvar.png"))); // NOI18N
        botonGuardar.setText("Guardar");
        botonGuardar.setBorder(null);
        botonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGuardarActionPerformed(evt);
            }
        });
        jPanel4.add(botonGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 430, 110, 40));

        Mostrar.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        Mostrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectoaula/imagenes/mostrar.png"))); // NOI18N
        Mostrar.setText("Mostrar");
        Mostrar.setBorder(null);
        Mostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MostrarActionPerformed(evt);
            }
        });
        jPanel4.add(Mostrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 430, 110, 40));

        botonEliminar.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        botonEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectoaula/imagenes/borrar.png"))); // NOI18N
        botonEliminar.setText("Eliminar");
        botonEliminar.setBorder(null);
        botonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEliminarActionPerformed(evt);
            }
        });
        jPanel4.add(botonEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 490, 100, 40));

        botonBuscar.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        botonBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectoaula/imagenes/buscar.png"))); // NOI18N
        botonBuscar.setText("Buscar");
        botonBuscar.setBorder(null);
        botonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonBuscarActionPerformed(evt);
            }
        });
        jPanel4.add(botonBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 430, 110, 40));

        Cancelar.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        Cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectoaula/imagenes/cancelado.png"))); // NOI18N
        Cancelar.setText("Cancelar");
        Cancelar.setBorder(null);
        Cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelarActionPerformed(evt);
            }
        });
        jPanel4.add(Cancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 490, 110, 40));

        jTabbedPane1.addTab("Electrodoméstico", jPanel4);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        jLabel9.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Cédula:");

        txtGastos.setBorder(null);

        jSeparator5.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator5.setForeground(new java.awt.Color(51, 51, 51));

        Guardar.setFont(new java.awt.Font("Lucida Sans", 0, 14)); // NOI18N
        Guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectoaula/imagenes/salvar.png"))); // NOI18N
        Guardar.setText("Guardar");
        Guardar.setBorder(null);
        Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuardarActionPerformed(evt);
            }
        });

        Mostrar1.setFont(new java.awt.Font("Lucida Sans", 0, 14)); // NOI18N
        Mostrar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectoaula/imagenes/mostrar.png"))); // NOI18N
        Mostrar1.setText("Mostrar");
        Mostrar1.setBorder(null);
        Mostrar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Mostrar1ActionPerformed(evt);
            }
        });

        Editar.setFont(new java.awt.Font("Lucida Sans", 0, 14)); // NOI18N
        Editar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectoaula/imagenes/editar-codigo.png"))); // NOI18N
        Editar.setText("Editar");
        Editar.setBorder(null);
        Editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditarActionPerformed(evt);
            }
        });

        Eliminar.setFont(new java.awt.Font("Lucida Sans", 0, 14)); // NOI18N
        Eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectoaula/imagenes/borrar.png"))); // NOI18N
        Eliminar.setText("Eliminar");
        Eliminar.setBorder(null);
        Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarActionPerformed(evt);
            }
        });

        jSeparator6.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator6.setForeground(new java.awt.Color(0, 0, 0));

        txtFecha.setBorder(null);
        txtFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Nro.Serie:");

        jLabel11.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Gasto:");

        jLabel12.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Fecha/Hora:");

        txtCedula2.setBorder(null);

        jSeparator7.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator7.setForeground(new java.awt.Color(51, 51, 51));

        txtNroSerie2.setBorder(null);

        jSeparator8.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator8.setForeground(new java.awt.Color(0, 0, 0));

        tablaGastos.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaGastos.setAltoHead(25);
        tablaGastos.setColorBackgoundHead(new java.awt.Color(66, 66, 66));
        tablaGastos.setColorFilasBackgound2(new java.awt.Color(240, 240, 240));
        tablaGastos.setColorFilasForeground1(new java.awt.Color(51, 51, 51));
        tablaGastos.setColorFilasForeground2(new java.awt.Color(51, 51, 51));
        tablaGastos.setFuenteFilas(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tablaGastos.setFuenteHead(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tablaGastos.setGrosorBordeFilas(0);
        tablaGastos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaGastosMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(tablaGastos);

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectoaula/imagenes/energia.png"))); // NOI18N

        Guardar1.setFont(new java.awt.Font("Lucida Sans", 0, 14)); // NOI18N
        Guardar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectoaula/imagenes/calcular1.png"))); // NOI18N
        Guardar1.setText("Calcular");
        Guardar1.setBorder(null);
        Guardar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Guardar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(txtCedula2, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addComponent(txtGastos, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(txtNroSerie2, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(180, 180, 180)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(160, 160, 160)
                                .addComponent(jLabel12))))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(40, 40, 40)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 660, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(Guardar1, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                            .addComponent(Guardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(40, 40, 40)
                        .addComponent(Mostrar1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(Editar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(Eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Mostrar1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Editar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Guardar1)
                .addContainerGap(15, Short.MAX_VALUE))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCedula2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGastos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel12))
                .addGap(3, 3, 3)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNroSerie2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Gastos", jPanel6);

        jPanel1.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 130, 1230, 580));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1544, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel8AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jPanel8AncestorAdded
       jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_jPanel8AncestorAdded

    private void CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelarActionPerformed
        txtElectrodomestico.setText("");
        txtNroSerie.setText("");
        txtMarca.setText("");
    }//GEN-LAST:event_CancelarActionPerformed

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

    private void botonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarActionPerformed
        eliminarElectrodomestico(electrodomestico);
    }//GEN-LAST:event_botonEliminarActionPerformed

    private void MostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MostrarActionPerformed
        String cedula = txtCedula.getText();
        if (cedula.isEmpty() || cedula.isBlank()) {
            JOptionPane.showMessageDialog(rootPane, "Debe ingresar la cedula");
        } else {
            mostrarElectrodomesticosEnTabla();
            limpiarCampos();
        }
    }//GEN-LAST:event_MostrarActionPerformed

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
            limpiarCampos();
        }
    }//GEN-LAST:event_botonGuardarActionPerformed

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

    private void tablaelectrodomesticoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaelectrodomesticoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaelectrodomesticoMouseClicked
    //Aquí empieza la parte de gastos
   
    private void guardarGastos1(Gastos gastos) {
    // Obtener la cédula del usuario desde el campo de texto
    String cedula = txtCedula2.getText();
    String archivoUsuario = cedula + ".txt";
    // Busca la ruta al archivo del usuario en el directorio principal
    File rutaArchivo = new File(crearblock, archivoUsuario);

    // Verificar si el archivo del usuario existe
    if (rutaArchivo.exists()) {
        // Obtener el número de serie desde el campo de texto
        String nserie = txtNroSerie2.getText() + ".txt";   
        // Construir la ruta al directorio de electrodomésticos específico del usuario
        String electrodomesticoBD = rutaelectrodomestico + elec + cedula + "_electrodomesticos" + elec;
        // Construir la ruta completa al archivo del electrodoméstico
        File rutaElectrodomestico = new File(electrodomesticoBD, nserie);

        // Verificar si el archivo del electrodoméstico existe
        if (rutaElectrodomestico.exists()) {
            try {
                // Abrir el archivo del electrodoméstico en modo de escritura (true para permitir agregar al final del archivo)
                FileWriter escritorDeArchivo = new FileWriter(rutaElectrodomestico, true);
                // Escribir la información de gastos directamente en el archivo
                String registroGasto = "Gasto: " + gastos.gasto + "\n";
                registroGasto += "Fecha: " + gastos.fecha + "\n";
                escritorDeArchivo.write(registroGasto);

                // Cerrar el escritor de archivos
                escritorDeArchivo.flush();
                escritorDeArchivo.close();

                // Actualizar la visualización de gastos en la tabla
                mostrarGastosEnTabla(cedula);

                // Mostrar mensaje de éxito al usuario
                JOptionPane.showMessageDialog(rootPane, "Gasto registrado");

            } catch (IOException e) {
                // Capturar y mostrar cualquier error al usuario
                JOptionPane.showMessageDialog(null, "Error al registrar el gasto: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            // Mostrar mensaje si el electrodoméstico no existe
            JOptionPane.showMessageDialog(rootPane, "El electrodoméstico no existe");
        }
    } else {
        // Mostrar mensaje si el usuario no existe
        JOptionPane.showMessageDialog(rootPane, "El usuario no existe");
    }
}String linea;
    private void mostrarGastosEnTabla(String cedula) {
        String electrodomesticoBD = rutaelectrodomestico + elec + cedula + "_electrodomesticos" + elec;

        File directorioElectrodomesticos = new File(electrodomesticoBD);
        File[] archivos = directorioElectrodomesticos.listFiles();

        if (archivos != null) {
            DefaultTableModel modelo = new DefaultTableModel();
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

            tablaGastos.setModel(modelo);
        } else {
            JOptionPane.showMessageDialog(rootPane, "No hay electrodomésticos registrados.");
        }
    }
    private void mostrarGastosEnTabla1(String cedula) {
        String electrodomesticoBD = rutaelectrodomestico + elec + cedula + "_electrodomesticos" + elec;
        double gasto;
        File directorioElectrodomesticos = new File(electrodomesticoBD);
        File[] archivos = directorioElectrodomesticos.listFiles();

        if (archivos != null) {

            DefaultTableModel modelo = new DefaultTableModel();
            modelo.addColumn("Electrodomestico");
            modelo.addColumn("Número de Serie");
            modelo.addColumn("Gastos");
            modelo.addColumn("Fecha");

            for (File archivo : archivos) {
                try {
                    BufferedReader lector = new BufferedReader(new FileReader(archivo.getAbsolutePath()));
                    
                    while ((linea = lector.readLine()) != null) {
                        if (linea.startsWith("Electrodomestico:")) {
                            electrodomestico.setNombreE(linea.substring(18).trim());
                        } else if (linea.startsWith("nro.serie:")) {
                            electrodomestico.setNroserie(linea.substring(11).trim());
                        } else if (linea.startsWith("Gasto:")) {
                            try {
                         gasto = Double.parseDouble(linea.substring(7).trim());
                        } catch (NumberFormatException e) {
                         // Manejar la excepción si la conversión falla
                        e.printStackTrace();
                        }
                        } else if (linea.startsWith("Fecha:")) {
                            gastos.setFecha(linea.substring(7).trim());
                        }
                    }

                    modelo.addRow(new Object[]{electrodomestico.nombreE, electrodomestico.nroSerie, gastos.gasto, gastos.fecha});

                    lector.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            tablaGastos.setModel(modelo);
        } else {
            JOptionPane.showMessageDialog(rootPane, "No hay electrodomésticos registrados.");
        }
    
}private void mostrarGastosEnTabla2(String cedula) {
    String electrodomesticoBD = rutaelectrodomestico + elec + cedula + "_electrodomesticos" + elec;
    File directorioElectrodomesticos = new File(electrodomesticoBD);
    File[] archivos = directorioElectrodomesticos.listFiles();

    if (archivos != null) {

        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Electrodomestico");
        modelo.addColumn("Número de Serie");
        modelo.addColumn("Gastos");
        modelo.addColumn("Fecha");

        Gastos gastos = new Gastos();  // Mover la creación de la instancia de Gastos fuera del bucle

        for (File archivo : archivos) {
            try {
                BufferedReader lector = new BufferedReader(new FileReader(archivo.getAbsolutePath()));
                Electrodomestico electrodomestico = new Electrodomestico();  // Crear una instancia de Electrodomestico

                while ((linea = lector.readLine()) != null) {
                    if (linea.startsWith("Electrodomestico:")) {
                        electrodomestico.setNombreE(linea.substring(18).trim());
                    } else if (linea.startsWith("nro.serie:")) {
                        electrodomestico.setNroserie(linea.substring(11).trim());
                    } else if (linea.startsWith("Gasto:")) {
                        try {
                            gastos.setGasto(Double.parseDouble(linea.substring(7).trim()));
                        } catch (NumberFormatException e) {
                            // Manejar la excepción si la conversión falla
                            e.printStackTrace();
                        }
                    } else if (linea.startsWith("Fecha:")) {
                        gastos.setFecha(linea.substring(7).trim());
                    }
                }

                modelo.addRow(new Object[]{electrodomestico.getNombreE(), electrodomestico.getNroserie(), gastos.getGasto(), gastos.getFecha()});

                lector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        tablaGastos.setModel(modelo);
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
    private void GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardarActionPerformed
   
        if (!txtCedula2.getText().isBlank()) {
        if (!txtNroSerie2.getText().isBlank()) {
            if (!txtFecha.getText().isBlank() || !txtGastos.getText().isBlank()) {
                if (txtGastos.getText().matches("\\d+(\\.\\d+)?")) {
                    String fecha = txtFecha.getText();
                    double consumo = Double.parseDouble(txtGastos.getText());
                    gastos.fecha = fecha;
                    gastos.gasto = consumo;
                    guardarGastos1(gastos);
                    txtFecha.setText("");
                    txtGastos.setText("");
                } else {
                    JOptionPane.showMessageDialog(rootPane, "El campo de texto para gastos no puede ser un dígito diferente de los numéricos");
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Ingrese la fecha (AA/MM/DD) y/o el gasto del electrodoméstico");
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Ingrese el número de serie del electrodoméstico");
        }
    } else {
        JOptionPane.showMessageDialog(rootPane, "Ingrese la cédula del usuario");
    }
    }//GEN-LAST:event_GuardarActionPerformed

    private void Mostrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Mostrar1ActionPerformed
        String cedula1 = txtCedula2.getText();
        mostrarGastosEnTabla2(cedula1);
    }//GEN-LAST:event_Mostrar1ActionPerformed

    private void EditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EditarActionPerformed

    private void EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EliminarActionPerformed

    private void RegresarVentanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegresarVentanaActionPerformed
        VentanaLogin abc = new VentanaLogin();
        abc.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_RegresarVentanaActionPerformed

    private void tablaGastosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaGastosMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaGastosMouseClicked

    private void txtFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaActionPerformed

    private void Guardar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Guardar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Guardar1ActionPerformed

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
    private javax.swing.JButton Guardar1;
    private javax.swing.JButton Mostrar;
    private javax.swing.JButton Mostrar1;
    private javax.swing.JButton RegresarVentana;
    private javax.swing.JButton botonBuscar;
    private javax.swing.JButton botonEditar;
    private javax.swing.JButton botonEliminar;
    private javax.swing.JButton botonGuardar;
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
    private javax.swing.JPanel jPanel5;
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
    private rojerusan.RSTableMetro tablaGastos;
    private rojerusan.RSTableMetro tablaelectrodomestico;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtCedula2;
    private javax.swing.JTextField txtElectrodomestico;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtGastos;
    private javax.swing.JTextField txtMarca;
    private javax.swing.JTextField txtNroSerie;
    private javax.swing.JTextField txtNroSerie2;
    // End of variables declaration//GEN-END:variables
}
