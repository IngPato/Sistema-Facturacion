/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectointegrador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * La clase Trabajador es una extensión de la clase Usuario, representando a un
 * trabajador que tiene acceso a métodos relacionados con productos, como la
 * obtención de categorías únicas, la lista de productos y la búsqueda de
 * productos por nombre o código.
 *
 * @author GPatr
 */
public class Trabajador extends Usuario {
    
    private String cargo;

    /**
     * Constructor por defecto de la clase Trabajador. Llama al constructor de
     * la clase base (Usuario).
     */
    public Trabajador() {
        super();
    }

    /**
     * Constructor de la clase Trabajador que recibe los parámetros necesarios
     * para inicializar el objeto. Llama al constructor de la clase base
     * (Usuario) para establecer los atributos del usuario.
     *
     * id El identificador único del trabajador
     * nombre El nombre del trabajador
     * contraseña La contraseña del trabajador
     * tipoUsuario El tipo de usuario (trabajador)
     * estado El estado del trabajador (activo/inactivo)
     */
    public Trabajador(int id, String nombre, String contraseña, String tipoUsuario, String estado) {
        super(id, nombre, contraseña, tipoUsuario, estado);
    }

    // METODOS
    /**
     * Obtiene una lista de categorías únicas de los productos disponibles en la
     * base de datos.
     *
     * @return Una lista de categorías únicas
     */
    public static ArrayList<String> obtenerCategoriasUnicas() {
        ArrayList<String> categorias = new ArrayList<>();
        try {
            // Conexión a la base de datos
            Connection conn = BaseDatos.conectar();
            String sql = "SELECT DISTINCT categoria FROM productos";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            // Se agregan las categorías a la lista
            while (rs.next()) {
                categorias.add(rs.getString("categoria"));
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categorias;
    }

    /**
     * Lista todos los productos disponibles en la base de datos.
     *
     * @return Una lista con los productos
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
     * Busca productos en la base de datos por nombre o código.
     *
     * @param valor El valor de búsqueda (puede ser nombre o código de producto)
     * @return Una lista de productos que coinciden con la búsqueda
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
