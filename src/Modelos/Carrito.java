/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.SQLException;
import java.util.List;

/**
 * Clase Carrito
 *
 * Representa un carrito de compras que almacena productos seleccionados por el
 * usuario. Implementa el patrón Singleton para garantizar una única instancia
 * del carrito. También se encarga de actualizar el stock de productos en la
 * base de datos.
 * 
 * @author GPatr
 */
public class Carrito {
    // Lista de productos agregados al carrito
    private List<Producto> productosEnCarrito = new ArrayList<>();

    // Instancia única del carrito (Singleton)
    private static Carrito instancia;

    /**
     * Obtiene la instancia única del carrito.
     *
     * @return Instancia del carrito.
     */
    public static Carrito getInstancia() {
        if (instancia == null) {
            instancia = new Carrito();
        }
        return instancia;
    }

    /**
     * Actualiza el stock del producto en la base de datos, restando la cantidad
     * especificada.
     *
     * @param nombre Nombre del producto.
     * @param cantidadReducida Cantidad a restar del stock actual.
     * @param bd Instancia de la base de datos.
     * @return true si la operación fue exitosa, false en caso contrario.
     */
    public boolean actualizarStock(String nombre, int cantidadReducida, BaseDatos bd) {
        // Obtiene el stock actual del producto.
        int stockActual = Producto.obtenerStockPorNombre(nombre, bd);
        // Verifica que haya stock suficiente.
        if (stockActual >= cantidadReducida) {
            try {
                Connection con = bd.conectar();
                String sql = "UPDATE productos SET cantidad = ? WHERE nombre = ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, stockActual - cantidadReducida);
                ps.setString(2, nombre); // producto a actualizar.
                int filas = ps.executeUpdate();
                ps.close();
                return filas > 0;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * Agrega un producto al carrito si hay stock disponible.
     *
     * @param nombre Nombre del producto.
     * @param cantidad Cantidad solicitada.
     * @param bd Instancia de la base de datos.
     * @return true si el producto fue agregado correctamente, false si no hay
     * stock suficiente o no se encontró.
     */
    public boolean agregarProducto(String nombre, int cantidad, BaseDatos bd) {
        // Busca el producto por nombre.
        Producto producto = Producto.buscarProductoPorNombre(nombre, bd);
        if (producto == null) {
            return false;
        }
        // Verifica el stock disponible.
        int stock = Producto.obtenerStockPorNombre(nombre, bd);
        if (stock < cantidad) {
            return false;
        }
        // Crea un nuevo objeto Producto con la cantidad deseada.
        Producto nuevo = new Producto(
                producto.getId(),
                producto.getNombre(),
                producto.getCategoria(),
                producto.getPrecio(),
                cantidad,
                producto.getCodigo()
        );
        // Agrega el producto al carrito.
        productosEnCarrito.add(nuevo);
        return true;
    }

    /**
     * Calcula el subtotal del carrito sumando el precio por cantidad de cada
     * producto.
     *
     * @return Subtotal en formato double.
     */
    public double calcularSubtotal() {
        double subtotal = 0;
        for (Producto p : productosEnCarrito) {
            subtotal += p.getPrecio() * p.getStock();
        }
        return subtotal;
    }

    /**
     * Devuelve la lista de productos en el carrito.
     *
     * @return Lista de objetos Producto en el carrito.
     */
    public List<Producto> getProductos() {
        return productosEnCarrito;
    }

    /**
     * Elimina todos los productos del carrito.
     */
    public void limpiarCarrito() {
        productosEnCarrito.clear();
    }
}
