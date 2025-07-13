/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;
import DAO.ProductoDAO;
import DAO.ProductoDAOImpl;
import Modelos.Producto;
import java.util.List;
/**
 *
 * @author GPatr
 */
public class AdministradorControlador {
    
    private final ProductoDAO productoDAO;

    public AdministradorControlador() {
        this.productoDAO = new ProductoDAOImpl(); 
    }

    public boolean agregarProducto(Producto producto) {
        return productoDAO.agregarProducto(producto);
    }

    public boolean editarProducto(Producto producto) {
        return productoDAO.editarProducto(producto);
    }

    public boolean eliminarProducto(int id) {
        return productoDAO.eliminarProducto(id);
    }

    public List<Producto> listarProductos() {
        return productoDAO.listarProductos();
    }

    public List<Producto> buscarProducto(String valor) {
        return productoDAO.buscarProducto(valor);
    }
}
