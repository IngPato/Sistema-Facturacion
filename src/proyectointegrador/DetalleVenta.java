/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectointegrador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DetalleVenta {
    private int idVenta;
    private int idProducto;
    private int cantidad;
    private int idEmpresa;

    public DetalleVenta() {
    }

    public DetalleVenta(int idVenta, int idProducto, int cantidad, int idEmpresa) {
        this.idVenta = idVenta;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.idEmpresa = idEmpresa;
    }

    // Getters y Setters

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    //METHODS
    /**
     * Registra los detalles de la venta (productos vendidos) en la tabla detalle_venta.
     *
     * @param con Conexión abierta a la base de datos
     * @param idVenta ID de la venta (generado en la tabla venta)
     * @param idEmpresa ID de la empresa asociada
     * @param productos Lista de productos vendidos (cantidad debe estar en getStock())
     * @return true si se insertaron todos los detalles correctamente
     */
    public static boolean registrarDetalles(Connection con, int idVenta, int idEmpresa, List<Producto> productos) {
        String sql = "INSERT INTO detalle_venta (idventa, idproducto, cantidad, idempresa) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            for (Producto p : productos) {
                ps.setInt(1, idVenta);
                ps.setInt(2, p.getId());
                ps.setInt(3, p.getStock()); // Aquí se usa stock como cantidad seleccionada
                ps.setInt(4, idEmpresa);
                ps.executeUpdate();
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}