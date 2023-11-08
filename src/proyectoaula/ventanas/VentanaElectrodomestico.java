package proyectoaula.ventanas;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import proyectoaula.objects.Usuario;

public class VentanaElectrodomestico extends javax.swing.JFrame {
    String elec = File.separator;
    String UsuariosBD = "C:\\Users\\HP\\Documents\\NetBeansProjects\\ProyectoAula\\UsuariosBD";
    private DefaultTableModel modelo;

    public VentanaElectrodomestico() {
        initComponents();

        modelo = new DefaultTableModel();
        modelo.addColumn("Electrodomestico");
        modelo.addColumn("Nro.Serie");
        modelo.addColumn("Marca");
        this.tablaelectrodomestico.setModel(modelo);

    }

    private void agregar() {

        String electrodomestico = electrodomesticoTXT.getText();
        String nroserie = nroserieTXT.getText();
        String marca = marcaTXT.getText();

        String[] agre = new String[3];

        agre[0] = electrodomestico;
        agre[1] = nroserie;
        agre[2] = marca;
        modelo.addRow(agre);

    }

    private void crear() {
        String Electrodomesticos = UsuariosBD + elec + "" + elec;
        String archivo = nroserieTXT.getText() + ".txt";
        File crearubi = new File(Electrodomesticos);
        File creararchivo = new File(Electrodomesticos + archivo);

        if (nroserieTXT.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "este electrodomestico no existe");

        } else {
            try {
                if (creararchivo.exists()) {
                    JOptionPane.showMessageDialog(rootPane, "este electrodomestico ya esta registrado");
                } else {
                    crearubi.mkdirs();
                    creararchivo.createNewFile();
                    Writer escritorDeArchivo = new FileWriter(creararchivo.getAbsolutePath());
                    String datosElectrodomestico = "Electrodomestico: " + electrodomesticoTXT.getText() + "\n";
                    datosElectrodomestico += "Nro. Serie: " + nroserieTXT.getText() + "\n";
                    datosElectrodomestico += "Marca: " + marcaTXT.getText();
                    escritorDeArchivo.write(datosElectrodomestico);
                    escritorDeArchivo.flush();
                    escritorDeArchivo.close();
                    JOptionPane.showMessageDialog(rootPane, "el electrodomestico a sido guardado");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, "no se a podido ingresar el electrodomestico");

            }
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
        Guardar = new javax.swing.JButton();
        Cancelar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        PanelElectrodomestico.setBackground(new java.awt.Color(204, 204, 255));
        PanelElectrodomestico.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(222, 220, 220));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        RegresarVentana.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        RegresarVentana.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectoaula/imagenes/atras.png"))); // NOI18N
        RegresarVentana.setText("Regresar");
        RegresarVentana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegresarVentanaActionPerformed(evt);
            }
        });
        jPanel1.add(RegresarVentana, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 440, 140, 40));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel4.setText("INGRESO DE NUEVO ELECTRODOMESTICO");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 30, -1, -1));

        jPanel2.setBackground(new java.awt.Color(220, 220, 220));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)), "Datos Del Electrodomestico", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setText("Electrodomestico:");

        nroserieTXT.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setText("Nro. Serie:");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setText("Marca:");

        marcaTXT.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        electrodomesticoTXT.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nroserieTXT, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(marcaTXT, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(electrodomesticoTXT, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(60, 60, 60))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(electrodomesticoTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(nroserieTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(marcaTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 90, 480, 340));

        Guardar.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        Guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectoaula/imagenes/salvar.png"))); // NOI18N
        Guardar.setText("Guardar");
        Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuardarActionPerformed(evt);
            }
        });
        jPanel1.add(Guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 440, -1, -1));

        Cancelar.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        Cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectoaula/imagenes/cancelado.png"))); // NOI18N
        Cancelar.setText("Cancelar");
        Cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelarActionPerformed(evt);
            }
        });
        jPanel1.add(Cancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 440, -1, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectoaula/imagenes/electrodomesticos.png"))); // NOI18N
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 230, 310));

        PanelElectrodomestico.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 840, 500));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelElectrodomestico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(PanelElectrodomestico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void RegresarVentanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegresarVentanaActionPerformed
        Ventana abc = new Ventana();
        abc.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_RegresarVentanaActionPerformed

    private void GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardarActionPerformed
        agregar();
        crear();

        electrodomesticoTXT.setText("");
        nroserieTXT.setText("");
        marcaTXT.setText("");
    }//GEN-LAST:event_GuardarActionPerformed

    private void CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelarActionPerformed
        electrodomesticoTXT.setText("");
        nroserieTXT.setText("");
        marcaTXT.setText("");
    }//GEN-LAST:event_CancelarActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaElectrodomestico().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cancelar;
    private javax.swing.JButton Guardar;
    private javax.swing.JPanel PanelElectrodomestico;
    private javax.swing.JButton RegresarVentana;
    private javax.swing.JTextField electrodomesticoTXT;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField marcaTXT;
    private javax.swing.JTextField nroserieTXT;
    private javax.swing.JTable tablaelectrodomestico;
    // End of variables declaration//GEN-END:variables
}
