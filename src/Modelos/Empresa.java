/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase Empresa
 *
 * Representa una empresa con información relevante como nombre, RUC, dirección,
 * teléfono y correo. Permite registrar y recuperar información desde una base
 * de datos.
 * 
 * @author GPatr
 */
public class Empresa {
    // Atributos.
    private int idempresa;
    private String nombreEmpresa;
    private String RUC;
    private String direccion;
    private String telefono;
    private String correo;

    /**
     * Constructor vacío.
     */
    public Empresa() {
    }

    /**
     * Constructor que inicializa todos los campos de la empresa.
     *
     * @param nombreEmpresa Nombre de la empresa.
     * @param RUC RUC de la empresa.
     * @param direccion Dirección de la empresa.
     * @param telefono Teléfono de contacto.
     * @param correo Correo electrónico de contacto.
     */
    public Empresa(int idempresa,String nombreEmpresa, String RUC, String direccion, String telefono, String correo) {
        this.idempresa = idempresa;
        this.nombreEmpresa = nombreEmpresa;
        this.RUC = RUC;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
    }

    // Getters y Setters

    public int getIdempresa() {
        return idempresa;
    }

    public void setIdempresa(int idempresa) {
        this.idempresa = idempresa;
    }
    
    
    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getRUC() {
        return RUC;
    }

    public void setRUC(String RUC) {
        this.RUC = RUC;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    /**
     * Método que define qué se muestra al imprimir un objeto Empresa.
     */
    @Override
    public String toString() {
        return nombreEmpresa;
    }
    //METODO
    /**
     * Obtiene todas las empresas registradas en la base de datos.
     *
     * @return Lista de objetos Empresa obtenidos de la base de datos.
     */
    public static List<Empresa> obtenerEmpresas() {
        List<Empresa> lista = new ArrayList<>();
        String sql = "SELECT * FROM empresa";

        try (Connection con = BaseDatos.getConnection(); Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Empresa emp = new Empresa(
                        rs.getInt("id"),
                        rs.getString("nombre_empresa"),
                        rs.getString("RUC"),
                        rs.getString("direccion"),
                        rs.getString("telefono"),
                        rs.getString("correo")
                );
                lista.add(emp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    /**
     * Guarda la empresa actual en la base de datos.
     *
     * @return true si el registro fue exitoso, false en caso de error.
     */
    public boolean guardarEnBD() {
        String sql = "INSERT INTO empresa (nombre_empresa, RUC, direccion, telefono, correo) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = BaseDatos.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, this.nombreEmpresa);
            ps.setString(2, this.RUC);
            ps.setString(3, this.direccion);
            ps.setString(4, this.telefono);
            ps.setString(5, this.correo);

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    //obtener logo de la empresa para PDF
    public static String getLogoPathPorEmpresaId(int idEmpresa) {
        switch (idEmpresa) {
            case 1:
                return "src/imagenes/logoFrancachela.png";
            case 2:
                return "src/imagenes/logoMedicare.png";
            case 3:
                return "src/imagenes/logoTambo.png";
            default:
                return "src/imagenes/logo.png";
        }
    }
}
