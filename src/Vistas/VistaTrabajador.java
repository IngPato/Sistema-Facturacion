/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vistas;

import java.util.ArrayList;
import Adicionales.PlaceHolder;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import proyectointegrador.Producto;
import proyectointegrador.Trabajador;
import proyectointegrador.Carrito;
import proyectointegrador.BaseDatos;
import proyectointegrador.Venta;
import proyectointegrador.Boleta;
import proyectointegrador.Factura;

/**
 * Esta clase representa la interfaz gráfica para la vista del trabajador en el
 * sistema. Permite gestionar la venta de productos, incluyendo la visualización
 * de productos en una tabla, el cálculo de totales con descuento, el manejo de
 * la cantidad de productos, y la actualización del cambio tras realizar el
 * pago. Extiende de javax.swing.JFrame, lo que le permite ser una ventana
 * dentro de la aplicación.
 *
 * @author GPatr
 */
public class VistaTrabajador extends javax.swing.JFrame {

    /**
     * Constructor de la clase VistaTrabajador. Inicializa los componentes de la
     * ventana, carga los productos en la tabla, y agrega los placeholders en
     * los campos de texto para facilitar la entrada de datos. Además, configura
     * los listeners para actualizar los valores de total y cambio de forma
     * dinámica.
     */
    public VistaTrabajador() {
        initComponents();
        cargarProductosEnTabla(); // Carga los productos en la tabla
        // Asigna placeholders en los campos de texto
        PlaceHolder nombre = new PlaceHolder("Nombre", TraNombre);
        PlaceHolder cantidad = new PlaceHolder("Cantidad", TraCantidad);
        PlaceHolder subtotal = new PlaceHolder("SubTotal", TraSubtotal);
        PlaceHolder descuento = new PlaceHolder("Descuento %", TraDescuento);
        PlaceHolder cancela = new PlaceHolder("Cancela", TraCancela);
        PlaceHolder total = new PlaceHolder("Total a Pagar", TraTotal);
        PlaceHolder cambio = new PlaceHolder("Vuelto", TraCambio);

        // Llenar categorías
        ArrayList<String> categorias = Trabajador.obtenerCategoriasUnicas();
        for (String categoria : categorias) {
            TraCategorias.addItem(categoria);
        }

        // Agrega DocumentListeners para actualizar el total y el cambio al cambiar los campos
        TraDescuento.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                actualizarSoloTotal();
            }

            public void removeUpdate(DocumentEvent e) {
                actualizarSoloTotal();
            }

            public void changedUpdate(DocumentEvent e) {
                actualizarSoloTotal();
            }
        });

        TraCancela.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                actualizarSoloCambio();
            }

            public void removeUpdate(DocumentEvent e) {
                actualizarSoloCambio();
            }

            public void changedUpdate(DocumentEvent e) {
                actualizarSoloCambio();
            }
        });

    }

    //METODOS
    /**
     * Este método se encarga de cargar los productos disponibles en el sistema
     * en la tabla (TablaTraSel). Primero limpia la tabla y luego obtiene la
     * lista de productos utilizando el método listarProductos() de la clase
     * Trabajador. Luego, agrega cada producto como una fila en la tabla con la
     * información relevante: ID, nombre, categoría, precio, stock y código.
     */
    //METODOS
    
    public void cargarProductosEnTabla() {
        DefaultTableModel modelo = (DefaultTableModel) TablaTraSel.getModel();
        modelo.setRowCount(0); 
        Trabajador tra = new Trabajador();
        List<Producto> lista = tra.listarProductos();

        for (Producto p : lista) {
            modelo.addRow(new Object[]{
                p.getId(),
                p.getNombre(),
                p.getCategoria(),
                p.getPrecio(),
                p.getStock(),
                p.getCodigo()
            });
        }
    }

    private Venta venta = new Venta();

    
    private void actualizarSoloTotal() {
        try {
            double descuento = 0;
            if (!TraDescuento.getText().isEmpty()) {
                descuento = Double.parseDouble(TraDescuento.getText());
            }

            venta.setDescuento(descuento);
            double total = venta.getTotalConDescuento();
            TraTotal.setText(String.format("%.2f", total));
        } catch (NumberFormatException e) {
            TraTotal.setText("");
        }
    }

   
    private void actualizarSoloCambio() {
        try {
            double cancelado = 0;
            if (!TraCancela.getText().isEmpty()) {
                cancelado = Double.parseDouble(TraCancela.getText());
            }

            venta.setCancelado(cancelado);
            double cambio = venta.getCambio();
            TraCambio.setText(String.format("%.2f", cambio));
        } catch (NumberFormatException e) {
            TraCambio.setText("");
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
        TraNombre = new javax.swing.JTextField();
        TraCantidad = new javax.swing.JTextField();
        BotonAñadirT = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        TraCategorias = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        BuscarTra = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TablaTraSel = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        TraSubtotal = new javax.swing.JTextField();
        TraDescuento = new javax.swing.JTextField();
        TraTotal = new javax.swing.JTextField();
        TraCancela = new javax.swing.JTextField();
        TraCambio = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        BotonGeneBolT = new javax.swing.JButton();
        BotonGeneFacT = new javax.swing.JButton();
        BotonCerrarT = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablaTra = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        CancelarTra = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(135, 255, 153));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(131, 179, 255), 5));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("TRABAJADOR");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 22, -1, -1));

        TraNombre.setBackground(new java.awt.Color(135, 255, 153));
        TraNombre.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        TraNombre.setForeground(new java.awt.Color(0, 0, 0));
        jPanel2.add(TraNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(263, 11, 134, -1));

        TraCantidad.setBackground(new java.awt.Color(135, 255, 153));
        TraCantidad.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        TraCantidad.setForeground(new java.awt.Color(0, 0, 0));
        jPanel2.add(TraCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(474, 11, 131, -1));

        BotonAñadirT.setBackground(new java.awt.Color(131, 179, 255));
        BotonAñadirT.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        BotonAñadirT.setForeground(new java.awt.Color(0, 0, 0));
        BotonAñadirT.setText("AÑADIR");
        BotonAñadirT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonAñadirTActionPerformed(evt);
            }
        });
        jPanel2.add(BotonAñadirT, new org.netbeans.lib.awtextra.AbsoluteConstraints(416, 45, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/logo56.png"))); // NOI18N
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 11, -1, -1));

        TraCategorias.setBackground(new java.awt.Color(135, 255, 153));
        TraCategorias.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        TraCategorias.setForeground(new java.awt.Color(0, 0, 0));
        TraCategorias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TraCategoriasActionPerformed(evt);
            }
        });
        jPanel2.add(TraCategorias, new org.netbeans.lib.awtextra.AbsoluteConstraints(264, 45, 134, -1));

        jLabel3.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Producto");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 14, -1, -1));

        jLabel4.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Cantidad");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(416, 14, -1, -1));

        jLabel5.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Categoria");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 48, -1, -1));

        BuscarTra.setBackground(new java.awt.Color(131, 179, 255));
        BuscarTra.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        BuscarTra.setForeground(new java.awt.Color(0, 0, 0));
        BuscarTra.setText("BUSCAR");
        BuscarTra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarTraActionPerformed(evt);
            }
        });
        jPanel2.add(BuscarTra, new org.netbeans.lib.awtextra.AbsoluteConstraints(512, 45, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, 719, 76));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(131, 179, 255), 5));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TablaTraSel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOMBRE", "CATEGORIA", "PRECIO", "CANTIDAD", "CODIGO"
            }
        ));
        TablaTraSel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaTraSelMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(TablaTraSel);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 697, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 5, 709, 110));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 109, 720, 120));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(131, 179, 255), 5));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TraSubtotal.setBackground(new java.awt.Color(135, 255, 153));
        TraSubtotal.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        TraSubtotal.setForeground(new java.awt.Color(0, 0, 0));
        TraSubtotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TraSubtotalActionPerformed(evt);
            }
        });
        jPanel4.add(TraSubtotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(68, 11, 134, -1));

        TraDescuento.setBackground(new java.awt.Color(135, 255, 153));
        TraDescuento.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        TraDescuento.setForeground(new java.awt.Color(0, 0, 0));
        jPanel4.add(TraDescuento, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 10, 134, -1));

        TraTotal.setBackground(new java.awt.Color(135, 255, 153));
        TraTotal.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        TraTotal.setForeground(new java.awt.Color(0, 0, 0));
        jPanel4.add(TraTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(68, 50, 134, -1));

        TraCancela.setBackground(new java.awt.Color(135, 255, 153));
        TraCancela.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        TraCancela.setForeground(new java.awt.Color(0, 0, 0));
        jPanel4.add(TraCancela, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 10, 134, -1));

        TraCambio.setBackground(new java.awt.Color(135, 255, 153));
        TraCambio.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        TraCambio.setForeground(new java.awt.Color(0, 0, 0));
        jPanel4.add(TraCambio, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 50, 134, -1));

        jLabel6.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("SubTotal");
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 14, -1, -1));

        jLabel7.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Descuento (%)");
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(214, 14, 90, -1));

        jLabel8.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Total");
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 53, 51, -1));

        jLabel9.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Cancela");
        jPanel4.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 10, 58, -1));

        jLabel10.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Cambio");
        jPanel4.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 50, -1, 20));

        BotonGeneBolT.setBackground(new java.awt.Color(131, 179, 255));
        BotonGeneBolT.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        BotonGeneBolT.setForeground(new java.awt.Color(0, 0, 0));
        BotonGeneBolT.setText("Generar Boleta");
        BotonGeneBolT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonGeneBolTActionPerformed(evt);
            }
        });
        jPanel4.add(BotonGeneBolT, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 50, -1, -1));

        BotonGeneFacT.setBackground(new java.awt.Color(131, 179, 255));
        BotonGeneFacT.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        BotonGeneFacT.setForeground(new java.awt.Color(0, 0, 0));
        BotonGeneFacT.setText("Generar Factura");
        BotonGeneFacT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonGeneFacTActionPerformed(evt);
            }
        });
        jPanel4.add(BotonGeneFacT, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 50, -1, -1));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 408, 720, 90));

        BotonCerrarT.setBackground(new java.awt.Color(131, 179, 255));
        BotonCerrarT.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        BotonCerrarT.setForeground(new java.awt.Color(0, 0, 0));
        BotonCerrarT.setText("Cerrar sesión");
        BotonCerrarT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonCerrarTActionPerformed(evt);
            }
        });
        jPanel1.add(BotonCerrarT, new org.netbeans.lib.awtextra.AbsoluteConstraints(593, 507, 129, -1));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(131, 179, 255), 5));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TablaTra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOMBRE", "CATEGORIA", "PRECIO UNI", "CANTIDAD", "PRECIO TOT"
            }
        ));
        jScrollPane2.setViewportView(TablaTra);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 698, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel6.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 5, 710, 130));

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 260, 719, 140));

        jLabel11.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("LISTA DE PRODUCTOS");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(297, 239, -1, -1));

        jLabel12.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("SELECCION DE PRODUCTOS");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 88, -1, -1));

        CancelarTra.setBackground(new java.awt.Color(131, 179, 255));
        CancelarTra.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        CancelarTra.setForeground(new java.awt.Color(0, 0, 0));
        CancelarTra.setText("Cancelar compra");
        CancelarTra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelarTraActionPerformed(evt);
            }
        });
        jPanel1.add(CancelarTra, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 510, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 740, 550));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TraSubtotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TraSubtotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TraSubtotalActionPerformed

    /**
     * Este método se ejecuta cuando el usuario realiza una acción en el campo
     * de búsqueda (TraNombre). Toma el texto ingresado en el campo de nombre,
     * busca productos que coincidan con ese texto en la base de datos, y carga
     * los resultados en la tabla (TablaTraSel). Si no se encuentran productos
     * que coincidan, la tabla permanecerá vacía.
     */
    private void BuscarTraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarTraActionPerformed
        // TODO add your handling code here:
        String valor = TraNombre.getText().trim();
        DefaultTableModel modelo = (DefaultTableModel) TablaTraSel.getModel();
        modelo.setRowCount(0);

        Trabajador tra = new Trabajador();
        List<Producto> lista = tra.buscarProducto(valor);

        for (Producto p : lista) {
            modelo.addRow(new Object[]{
                p.getId(), p.getNombre(), p.getCategoria(), p.getPrecio(), p.getStock(), p.getCodigo()
            });
        }
    }//GEN-LAST:event_BuscarTraActionPerformed

    private void TraCategoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TraCategoriasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TraCategoriasActionPerformed

    /**
     * Este método se ejecuta cuando el usuario hace clic en una fila de la
     * tabla (TablaTraSel). Si la fila seleccionada es válida, llena los campos
     * de nombre y categoría con la información de la fila seleccionada. Esto
     * permite que el usuario vea los detalles del producto al hacer clic en la
     * tabla.
     */
    private void TablaTraSelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaTraSelMouseClicked
        // TODO add your handling code here:
        int fila = TablaTraSel.getSelectedRow();
        if (fila >= 0) {
            TraNombre.setText(TablaTraSel.getValueAt(fila, 1).toString());
            TraCategorias.setSelectedItem(TablaTraSel.getValueAt(fila, 2).toString());
        }

    }//GEN-LAST:event_TablaTraSelMouseClicked

    /**
     * Este método se ejecuta cuando el usuario hace clic en el botón de cerrar
     * (BotonCerrarT). Cierra la ventana actual (VistaTrabajador) y abre la
     * ventana de Login.
     */
    private void BotonCerrarTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonCerrarTActionPerformed
        // TODO add your handling code here:
        // Cerrar esta ventana
        this.dispose();

        // Abrir la ventana de Login
        Vistas.Login login = new Vistas.Login();
        login.setVisible(true);
    }//GEN-LAST:event_BotonCerrarTActionPerformed

    /**
     * Este método se ejecuta cuando el usuario hace clic en el botón de añadir
     * producto (BotonAñadirT). Toma el nombre del producto y la cantidad
     * ingresada, valida si el producto existe y si hay suficiente stock. Si
     * todo es válido, agrega el producto al carrito y actualiza la tabla con
     * los productos añadidos. También calcula y muestra el subtotal del
     * carrito.
     */
    private void BotonAñadirTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonAñadirTActionPerformed
        // TODO add your handling code here:
        // Obtener el nombre del producto y la cantidad

        String nombre = TraNombre.getText();
        int cantidad;

        try {
            cantidad = Integer.parseInt(TraCantidad.getText());
        } catch (NumberFormatException e) {
            // Si la cantidad es inválida, mostrar un mensaje de error

            JOptionPane.showMessageDialog(null, "Cantidad inválida");
            return;
        }
        // Crear una nueva instancia de la base de datos y el carrito
        BaseDatos bd = new BaseDatos();
        Carrito carrito = Carrito.getInstancia();
        // Buscar el producto por nombre

        Producto producto = Producto.buscarProductoPorNombre(nombre, bd);

        if (producto == null) {
            JOptionPane.showMessageDialog(null, "Producto no encontrado.");
            return;
        }

        int stock = Producto.obtenerStockPorNombre(nombre, bd);
        if (stock < cantidad) {
            JOptionPane.showMessageDialog(null, "Stock insuficiente.");
            return;
        }

        // Agregar al carrito (sin tocar base de datos)
        Producto nuevo = new Producto(producto.getId(), producto.getNombre(), producto.getCategoria(), producto.getPrecio(), cantidad, producto.getCodigo());

        carrito.getProductos().add(nuevo);

        // Mostrar en tabla
        DefaultTableModel modelo = (DefaultTableModel) TablaTra.getModel();
        modelo.addRow(new Object[]{
            nuevo.getId(),
            nuevo.getNombre(),
            nuevo.getCategoria(),
            nuevo.getPrecio(),
            cantidad,
            nuevo.getPrecio() * cantidad
        });

        // Calcular y mostrar subtotal
        TraSubtotal.setText(String.format("%.2f", carrito.calcularSubtotal()));
    }//GEN-LAST:event_BotonAñadirTActionPerformed

    /**
     * Este método se ejecuta cuando el usuario hace clic en el botón de
     * cancelar (CancelarTra). Limpia la tabla de productos, el carrito de
     * compras y todos los campos de la vista relacionados con la venta. Esta
     * acción permite reiniciar el proceso de venta.
     */
    private void CancelarTraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelarTraActionPerformed
        // TODO add your handling code here:
        // Limpiar la tabla visual
        DefaultTableModel modelo = (DefaultTableModel) TablaTra.getModel();
        modelo.setRowCount(0); // Elimina todas las filas

        // Limpiar el carrito
        Carrito carrito = new Carrito(); // Nueva instancia vacía

        // Limpiar los campos relacionados
        TraSubtotal.setText("");
        TraDescuento.setText("");
        TraTotal.setText("");
        TraCancela.setText("");
        TraCambio.setText("");
    }//GEN-LAST:event_CancelarTraActionPerformed

    private void BotonGeneBolTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonGeneBolTActionPerformed
        // TODO add your handling code here:
            Venta venta = new Venta();
        venta.setDescuento(Double.parseDouble(TraDescuento.getText()));
        venta.setCancelado(Double.parseDouble(TraCancela.getText()));

        Venta.setVentaActual(venta);

        VistaBoleta vb = new VistaBoleta();
        vb.setVisible(true);
        dispose();
    }//GEN-LAST:event_BotonGeneBolTActionPerformed

    private void BotonGeneFacTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonGeneFacTActionPerformed
        // TODO add your handling code here:
        Venta venta = new Venta();
        venta.setDescuento(Double.parseDouble(TraDescuento.getText()));
        venta.setCancelado(Double.parseDouble(TraCancela.getText()));

        Venta.setVentaActual(venta); // almacenamos la venta para que la use VistaFactura

        VistaFactura vf = new VistaFactura();
        vf.setVisible(true);
        dispose();
    }//GEN-LAST:event_BotonGeneFacTActionPerformed

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
            java.util.logging.Logger.getLogger(VistaTrabajador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaTrabajador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaTrabajador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaTrabajador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaTrabajador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonAñadirT;
    private javax.swing.JButton BotonCerrarT;
    private javax.swing.JButton BotonGeneBolT;
    private javax.swing.JButton BotonGeneFacT;
    private javax.swing.JButton BuscarTra;
    private javax.swing.JButton CancelarTra;
    private javax.swing.JTable TablaTra;
    private javax.swing.JTable TablaTraSel;
    private javax.swing.JTextField TraCambio;
    private javax.swing.JTextField TraCancela;
    private javax.swing.JTextField TraCantidad;
    private javax.swing.JComboBox<String> TraCategorias;
    private javax.swing.JTextField TraDescuento;
    private javax.swing.JTextField TraNombre;
    private javax.swing.JTextField TraSubtotal;
    private javax.swing.JTextField TraTotal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables
}
