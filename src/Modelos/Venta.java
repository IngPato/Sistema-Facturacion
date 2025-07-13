/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
/**
 * Clase Venta
 * 
 * Representa una venta en el sistema. Gestiona el carrito, descuentos, totales
 * y el proceso de registro en la base de datos.
 * @author GPatr
 */

public class Venta {

    private int idventa;
    private static Venta ventaActual;
    private Carrito carrito;
    private double descuento;
    private double cancelado;

     // Constructor.
    public Venta() {
        carrito = Carrito.getInstancia(); 
    }
    // Gestión de venta actual.
    public static void setVentaActual(Venta venta) {
        ventaActual = venta;
    }

    public static Venta getVentaActual() {
        return ventaActual;
    }
    // Getters y Setters
    public int getIdVenta() {
        return idventa;
    }

    public void setIdventa(int idventa) {
        this.idventa = idventa;
    }
    
    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    
    public void setCancelado(double cancelado) {
        this.cancelado = cancelado;
    }

    
    public double getSubtotal() {
        return carrito.calcularSubtotal();
    }

    
    public double getTotalConDescuento() {
        double subtotal = getSubtotal();
        return subtotal - (subtotal * descuento / 100.0);
    }

    
    public double getCambio() {
        return cancelado - getTotalConDescuento();
    }
    
    public double getDescuento() {
    return descuento;
    }

    public double getCancelado() {
        return cancelado;
    }
    // Registro en base de datos.
     /**
     * Registra la venta completa en la base de datos.
     *
     * @param bd Instancia de la base de datos
     * @param idTrabajador ID del trabajador que realizó la venta
     * @param fecha Fecha en formato string (idealmente yyyy-MM-dd)
     * @param hora Hora en formato string (HH:mm:ss)
     * @param idEmpresa ID de la empresa que emite la venta
     * @return true si toda la operación fue exitosa, false si hubo error
     */
    public boolean registrarVentaEnBD(BaseDatos bd, int idTrabajador, String fecha, String hora, int idEmpresa, String comprobante) {
        try {
            Connection con = bd.conectar();
            double total = ventaActual.getTotalConDescuento();
            

            
            String sqlVenta = "INSERT INTO venta (idtrabajador, fecha, hora, total, comprobante) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement psVenta = con.prepareStatement(sqlVenta, PreparedStatement.RETURN_GENERATED_KEYS);
            psVenta.setInt(1, idTrabajador);
            psVenta.setString(2, fecha);
            psVenta.setString(3, hora);
            psVenta.setDouble(4, total);
            psVenta.setString(5, comprobante);

            int filasAfectadas = psVenta.executeUpdate();
            if (filasAfectadas == 0) return false;
             // Obtiene ID generado.
            ResultSet rs = psVenta.getGeneratedKeys();
            int idVenta = -1;
            if (rs.next()) {
                idVenta = rs.getInt(1);
                this.idventa = idVenta;
            }
            rs.close();
            psVenta.close();

            // Insertar detalle de venta.
            List<Producto> productos = Carrito.getInstancia().getProductos();
            boolean detallesInsertados = DetalleVenta.registrarDetalles(con, idVenta, idEmpresa, productos);

            // Actualiza stock de productos vendidos.
            for (Producto producto : productos) {
                int idProducto = producto.getId(); 
                int cantidadVendida = producto.getStock(); 

                String sqlUpdate = "UPDATE productos SET cantidad = cantidad - ? WHERE id = ?";
                try (PreparedStatement psUpdate = con.prepareStatement(sqlUpdate)) {
                    psUpdate.setInt(1, cantidadVendida);
                    psUpdate.setInt(2, idProducto);
                    psUpdate.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return false; 
                }
            }

            return detallesInsertados;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}