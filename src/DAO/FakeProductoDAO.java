/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Modelos.Producto;
import java.util.ArrayList;
import java.util.List;

public class FakeProductoDAO implements ProductoDAO {

    private final List<Producto> productos = new ArrayList<>();

    @Override
    public boolean agregarProducto(Producto producto) {
        return productos.add(producto);
    }

    @Override
    public boolean editarProducto(Producto producto) {
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getId() == producto.getId()) {
                productos.set(i, producto);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean eliminarProducto(int id) {
        return productos.removeIf(p -> p.getId() == id);
    }

    @Override
    public List<Producto> listarProductos() {
        return new ArrayList<>(productos);
    }

    @Override
    public List<Producto> buscarProducto(String valor) {
        List<Producto> encontrados = new ArrayList<>();
        for (Producto p : productos) {
            if (p.getNombre().toLowerCase().contains(valor.toLowerCase())) {
                encontrados.add(p);
            }
        }
        return encontrados;
    }
}