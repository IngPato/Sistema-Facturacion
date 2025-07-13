/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Modelos.Producto;
import Modelos.BaseDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author GPatr
 */

public class ProductoDAOImpl implements ProductoDAO {

    @Override
    public boolean agregarProducto(Producto producto) {
        String sql = "INSERT INTO productos (nombre, categoria, precio, cantidad, codigo) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = BaseDatos.conectar();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, producto.getNombre());
            pst.setString(2, producto.getCategoria());
            pst.setDouble(3, producto.getPrecio());
            pst.setInt(4, producto.getStock());
            pst.setString(5, producto.getCodigo());
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean editarProducto(Producto producto) {
        String sql = "UPDATE productos SET nombre=?, categoria=?, precio=?, cantidad=?, codigo=? WHERE id=?";

        try (Connection con = BaseDatos.conectar();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, producto.getNombre());
            pst.setString(2, producto.getCategoria());
            pst.setDouble(3, producto.getPrecio());
            pst.setInt(4, producto.getStock());
            pst.setString(5, producto.getCodigo());
            pst.setInt(6, producto.getId());
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean eliminarProducto(int id) {
        String sql = "DELETE FROM productos WHERE id=?";

        try (Connection con = BaseDatos.conectar();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, id);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public List<Producto> listarProductos() {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT * FROM productos";

        try (Connection con = BaseDatos.conectar();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

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
            e.printStackTrace();
        }

        return lista;
    }

    @Override
    public List<Producto> buscarProducto(String valor) {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT * FROM productos WHERE nombre LIKE ? OR codigo LIKE ?";

        try (Connection con = BaseDatos.conectar();
             PreparedStatement pst = con.prepareStatement(sql)) {

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
            e.printStackTrace();
        }

        return lista;
    }
}

