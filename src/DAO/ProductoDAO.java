/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import java.util.List;
import Modelos.Producto;
/**
 *
 * @author GPatr
 */
public interface ProductoDAO {

    boolean agregarProducto(Producto producto);

    boolean editarProducto(Producto producto);

    boolean eliminarProducto(int id);

    List<Producto> listarProductos();

    List<Producto> buscarProducto(String valor);
}
