
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectointegrador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * La clase Producto representa un artículo que se puede vender en un
 * inventario. Contiene atributos como el id, nombre, categoría, precio,
 * cantidad en stock y código. Además, proporciona métodos para acceder y
 * modificar estos atributos, así como operaciones para buscar productos en una
 * base de datos y obtener el stock.
 *
 * @author GPatr
 */
public class Producto {

    private int id;
    private String nombre;
    private String categoria;
    private double precio;
    private int cantidad;
    private String codigo;

    // Constructores
    
    /**
     * Constructor vacío de la clase Producto. Se utiliza cuando se quiere crear
     * un objeto Producto sin inicializar sus atributos.
     */
    public Producto() {
    }

    /**
     * Constructor parametrizado de la clase Producto. Se utiliza para crear un
     * objeto Producto con los atributos especificados.
     *
     * @param id Identificador único del producto
     * @param nombre Nombre del producto
     * @param categoria Categoría a la que pertenece el producto
     * @param precio Precio del producto
     * @param cantidad Cantidad disponible en el inventario
     * @param codigo Código único del producto
     */
    public Producto(int id, String nombre, String categoria, double precio, int cantidad, String codigo) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.cantidad = cantidad;
        this.codigo = codigo;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    //MODIFICAREMOS DESDE FUERA DE LA CLASE
    public void setCategoria(String categoria) { 
        this.categoria = categoria;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    // Métodos para interactuar con la base de datos
    /**
     * Busca un producto en la base de datos por su nombre.
     *
     * @param nombre Nombre del producto a buscar
     * @param bd Instancia de la clase BaseDatos para realizar la conexión
     * @return Producto encontrado en la base de datos, o null si no se
     * encuentra
     */
    public static Producto buscarProductoPorNombre(String nombre, BaseDatos bd) {
        Producto producto = null;
        try {
            Connection con = bd.conectar();
            String sql = "SELECT * FROM productos WHERE nombre = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                producto = new Producto(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("categoria"),
                        rs.getDouble("precio"),
                        rs.getInt("cantidad"),
                        rs.getString("codigo")
                );
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return producto;
    }

    /**
     * Obtiene la cantidad de stock disponible de un producto en la base de
     * datos por su nombre.
     *
     * @param nombre Nombre del producto cuya cantidad se desea conocer
     * @param bd Instancia de la clase BaseDatos para realizar la conexión
     * @return La cantidad de stock disponible del producto, o -1 si no se
     * encuentra
     */
    public static int obtenerStockPorNombre(String nombre, BaseDatos bd) {
        int stock = -1;
        try {
            Connection con = bd.conectar();
            String sql = "SELECT cantidad FROM productos WHERE nombre = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                stock = rs.getInt("cantidad");
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stock;
    }

}
