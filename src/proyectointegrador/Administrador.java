/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectointegrador;

import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author GPatr
 */
public class Administrador extends Usuario {
    
    private String registros;

    public Administrador() {
        super();
    }

    public Administrador(int id, String nombre, String contraseña, String tipoUsuario, String estado) {
        super(id, nombre, contraseña, tipoUsuario, estado);
    }

    // METODOS
    /**
     * Lista todos los productos almacenados en la base de datos.
     *
     * @return Lista de objetos Producto obtenidos desde la tabla 'productos'.
     */
    
    public List<Producto> listarProductos() {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT * FROM productos";

        try (Connection con = new BaseDatos().conectar(); PreparedStatement pst = con.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Producto p = new Producto();
                p.setId(rs.getInt("id"));
                p.setNombre(rs.getString("nombre"));
                p.setCategoria(rs.getString("categoria"));
                p.setPrecio(rs.getDouble("precio"));
                p.setCantidad(rs.getInt("cantidad"));
                p.setCodigo(rs.getString("codigo"));
                lista.add(p);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar productos: " + e.getMessage());
        }

        return lista;
    }

    /**
     * Agrega un nuevo producto a la base de datos.
     *
     * @param producto Objeto Producto que contiene los datos a insertar.
     * @return true si el producto fue agregado exitosamente, false en caso
     * contrario.
     */
    public boolean agregarProducto(Producto producto) {
        String sql = "INSERT INTO productos (nombre, categoria, precio, cantidad, codigo) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = new BaseDatos().conectar(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, producto.getNombre());
            pst.setString(2, producto.getCategoria());
            pst.setDouble(3, producto.getPrecio());
            pst.setInt(4, producto.getStock());
            pst.setString(5, producto.getCodigo());

            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al agregar producto: " + e.getMessage());
            return false;
        }
    }

    /**
     * Edita un producto existente en la base de datos.
     *
     * @param producto Objeto Producto que contiene los datos actualizados.
     * @return true si el producto fue actualizado correctamente, false si
     * ocurrió un error.
     */
    public boolean editarProducto(Producto producto) {
        String sql = "UPDATE productos SET nombre=?, categoria=?, precio=?, cantidad=?, codigo=? WHERE id=?";

        try (Connection con = new BaseDatos().conectar(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, producto.getNombre());
            pst.setString(2, producto.getCategoria());
            pst.setDouble(3, producto.getPrecio());
            pst.setInt(4, producto.getStock());
            pst.setString(5, producto.getCodigo());
            pst.setInt(6, producto.getId());

            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al editar producto: " + e.getMessage());
            return false;
        }
    }

    /**
     * Elimina un producto de la base de datos según su ID.
     *
     * @param id Identificador del producto que se desea eliminar.
     * @return true si el producto fue eliminado exitosamente, false si ocurrió
     * un error.
     */
    public boolean eliminarProducto(int id) {
        String sql = "DELETE FROM productos WHERE id=?";

        try (Connection con = new BaseDatos().conectar(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, id);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar producto: " + e.getMessage());
            return false;
        }
    }

    /**
     * Busca productos en la base de datos que coincidan parcial o totalmente
     * con el valor ingresado, comparando contra los campos 'nombre' o 'codigo'.
     *
     * @param valor El valor a buscar en los campos 'nombre' y 'codigo' de los
     * productos.
     * @return Una lista de objetos Producto que coinciden con el criterio de
     * búsqueda.
     */
    public List<Producto> buscarProducto(String valor) {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT * FROM productos WHERE nombre LIKE ? OR codigo LIKE ?";

        try (Connection con = new BaseDatos().conectar(); PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, "%" + valor + "%");
            pst.setString(2, "%" + valor + "%");

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Producto p = new Producto();
                p.setId(rs.getInt("id"));
                p.setNombre(rs.getString("nombre"));
                p.setCategoria(rs.getString("categoria"));
                p.setPrecio(rs.getDouble("precio"));
                p.setCantidad(rs.getInt("cantidad"));
                p.setCodigo(rs.getString("codigo"));
                lista.add(p);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar producto: " + e.getMessage());
        }
        return lista;
    }
}
