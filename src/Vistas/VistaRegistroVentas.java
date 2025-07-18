/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vistas;

import java.util.List;
import Modelos.Empresa;
import Modelos.RegistroVenta;

/**
 * Clase RegistroVentas
 *
 * Esta clase representa una interfaz gráfica para registrar y mostrar ventas,
 * filtradas por trabajador, categoría de producto y período de tiempo (meses).
 * La interfaz también permite actualizar los registros de ventas y mostrar los
 * resultados en una tabla.
 *
 * @author GPatr
 */
public class VistaRegistroVentas extends javax.swing.JFrame {

    /**
     * Constructor de la clase RegistroVentas. Inicializa los componentes
     * gráficos de la interfaz de usuario.
     */
    public VistaRegistroVentas() {

        initComponents();
        cargarEmpresas();
    }

    //METODOS:
    private void cargarEmpresas() {
        List<Empresa> empresas = Empresa.obtenerEmpresas();
        for (Empresa empresa : empresas) {
            RVEmpresa.addItem(empresa);
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        CategoriaRV = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        MesFinalRV = new com.toedter.calendar.JMonthChooser();
        TrabajadorRV = new javax.swing.JButton();
        EmpresaRV = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        ProductoRV = new javax.swing.JButton();
        MesRV = new javax.swing.JButton();
        RVEmpresa = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        CerrarRV = new javax.swing.JButton();
        VolverRV = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(216, 255, 216));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(131, 179, 255), 5));

        jLabel1.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Registro de ventas");

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/IcoRV.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(192, Short.MAX_VALUE)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(220, 220, 220))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel1)))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 710, 90));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(131, 179, 255), 5));

        jLabel5.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Categoria:");

        jLabel2.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Trabajador:");

        CategoriaRV.setBackground(new java.awt.Color(179, 217, 255));
        CategoriaRV.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        CategoriaRV.setForeground(new java.awt.Color(0, 0, 0));
        CategoriaRV.setText("Generar excel");
        CategoriaRV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CategoriaRVActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Productos:");

        jLabel4.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Mes:");

        MesFinalRV.setBackground(new java.awt.Color(179, 217, 255));
        MesFinalRV.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N

        TrabajadorRV.setBackground(new java.awt.Color(179, 217, 255));
        TrabajadorRV.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        TrabajadorRV.setForeground(new java.awt.Color(0, 0, 0));
        TrabajadorRV.setText("Generar excel");
        TrabajadorRV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TrabajadorRVActionPerformed(evt);
            }
        });

        EmpresaRV.setBackground(new java.awt.Color(179, 217, 255));
        EmpresaRV.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        EmpresaRV.setForeground(new java.awt.Color(0, 0, 0));
        EmpresaRV.setText("Generar excel");
        EmpresaRV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EmpresaRVActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Empresa:");

        ProductoRV.setBackground(new java.awt.Color(179, 217, 255));
        ProductoRV.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        ProductoRV.setForeground(new java.awt.Color(0, 0, 0));
        ProductoRV.setText("Generar excel");
        ProductoRV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProductoRVActionPerformed(evt);
            }
        });

        MesRV.setBackground(new java.awt.Color(179, 217, 255));
        MesRV.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        MesRV.setForeground(new java.awt.Color(0, 0, 0));
        MesRV.setText("Generar excel");
        MesRV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MesRVActionPerformed(evt);
            }
        });

        RVEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RVEmpresaActionPerformed(evt);
            }
        });

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/CategoriaRV.png"))); // NOI18N

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/TrabajadorRV.png"))); // NOI18N

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ProductoRV.png"))); // NOI18N

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/EmpresaRV.png"))); // NOI18N

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/MesRV.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(RVEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(EmpresaRV, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MesRV, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(CategoriaRV, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jLabel2)
                                        .addGap(21, 21, 21))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(MesFinalRV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(ProductoRV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(TrabajadorRV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap(168, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(CategoriaRV)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(TrabajadorRV)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(ProductoRV)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(MesRV)
                        .addComponent(MesFinalRV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(EmpresaRV)
                    .addComponent(RVEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 440, 240));

        CerrarRV.setBackground(new java.awt.Color(179, 217, 255));
        CerrarRV.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        CerrarRV.setForeground(new java.awt.Color(0, 0, 0));
        CerrarRV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/IcoAdminexit.png"))); // NOI18N
        CerrarRV.setText("Cerrar Sesion");
        CerrarRV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CerrarRVActionPerformed(evt);
            }
        });
        jPanel1.add(CerrarRV, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 410, -1, -1));

        VolverRV.setBackground(new java.awt.Color(179, 217, 255));
        VolverRV.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        VolverRV.setForeground(new java.awt.Color(0, 0, 0));
        VolverRV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/AtrasRV.png"))); // NOI18N
        VolverRV.setText("Volver Atras");
        VolverRV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VolverRVActionPerformed(evt);
            }
        });
        jPanel1.add(VolverRV, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, -1, -1));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(131, 179, 255), 5));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/logo160.png"))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(39, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(41, 41, 41))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 120, 250, 240));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 730, 450));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void VolverRVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VolverRVActionPerformed
        // TODO add your handling code here:
        VistaAdministrador vistaAdmin = new VistaAdministrador(); // llama vista administrador
        vistaAdmin.setVisible(true);                              // La regresa
        this.dispose();                                            // Cierra la ventana
    }//GEN-LAST:event_VolverRVActionPerformed

    private void CategoriaRVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CategoriaRVActionPerformed
        // TODO add your handling code here:
        try {
            RegistroVenta.generarExcelPorCategoria();
            javax.swing.JOptionPane.showMessageDialog(this, "Excel de categorías generado con éxito.");
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error al generar el Excel: " + e.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_CategoriaRVActionPerformed

    private void TrabajadorRVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TrabajadorRVActionPerformed
        // TODO add your handling code here:
        try {
            RegistroVenta.generarExcelPorTrabajador();
            javax.swing.JOptionPane.showMessageDialog(this, "Excel de trabajadores generado con éxito.");
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error al generar el Excel: " + e.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_TrabajadorRVActionPerformed

    private void RVEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RVEmpresaActionPerformed
        // TODO add your handling code here:
        Empresa seleccionada = (Empresa) RVEmpresa.getSelectedItem();
            if (seleccionada != null) {
                System.out.println("Empresa seleccionada: " + seleccionada.getNombreEmpresa());
                System.out.println("ID: " + seleccionada.getIdempresa());
            }
    }//GEN-LAST:event_RVEmpresaActionPerformed

    private void ProductoRVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProductoRVActionPerformed
        // TODO add your handling code here:
        try {
            RegistroVenta.generarExcelPorProducto();
            javax.swing.JOptionPane.showMessageDialog(this, "Excel de productos generado con éxito.");
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error al generar el Excel: " + e.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_ProductoRVActionPerformed

    private void MesRVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MesRVActionPerformed
        // TODO add your handling code here:
        try {
            int mes = MesFinalRV.getMonth(); // 0 a 11
            RegistroVenta.generarExcelPorMes(mes);
            javax.swing.JOptionPane.showMessageDialog(this, "Excel de ventas del mes generado con éxito.");
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error al generar el Excel: " + e.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_MesRVActionPerformed

    private void EmpresaRVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EmpresaRVActionPerformed
        // TODO add your handling code here:
        Empresa empresaSeleccionada = (Empresa) RVEmpresa.getSelectedItem();
        if (empresaSeleccionada != null) {
            try {
                int idEmpresa = empresaSeleccionada.getIdempresa();
                RegistroVenta.generarExcelPorEmpresa(idEmpresa);
                javax.swing.JOptionPane.showMessageDialog(this, "Excel de ventas por empresa generado con éxito.");
            } catch (Exception e) {
                javax.swing.JOptionPane.showMessageDialog(this, "Error al generar el Excel: " + e.getMessage(), "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "Seleccione una empresa para continuar.", "Advertencia", javax.swing.JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_EmpresaRVActionPerformed

    private void CerrarRVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CerrarRVActionPerformed
        // TODO add your handling code here:
       System.exit(0);
    }//GEN-LAST:event_CerrarRVActionPerformed

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
            java.util.logging.Logger.getLogger(VistaRegistroVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaRegistroVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaRegistroVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaRegistroVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaRegistroVentas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CategoriaRV;
    private javax.swing.JButton CerrarRV;
    private javax.swing.JButton EmpresaRV;
    private com.toedter.calendar.JMonthChooser MesFinalRV;
    private javax.swing.JButton MesRV;
    private javax.swing.JButton ProductoRV;
    private javax.swing.JComboBox<Empresa> RVEmpresa;
    private javax.swing.JButton TrabajadorRV;
    private javax.swing.JButton VolverRV;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
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
    // End of variables declaration//GEN-END:variables
}
