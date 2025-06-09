/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectointegrador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * La clase Usuario representa un usuario del sistema con sus propiedades
 * básicas, como el identificador, nombre, contraseña, tipo de usuario y estado.
 * Además, proporciona un método para verificar las credenciales de un usuario
 * en la base de datos.
 *
 * @author GPatr
 */
public class Usuario {

    protected int id;
    protected String nombre;
    protected String contraseña;
    protected String tipoUsuario;
    protected String estado;

    /**
     * Constructor por defecto de la clase Usuario.
     */
    public Usuario() {
    }

    /**
     * Constructor de la clase Usuario que recibe los parámetros necesarios para
     * inicializar el objeto.
     *
     * @param id El identificador único del usuario
     * @param nombre El nombre del usuario
     * @param contraseña La contraseña del usuario
     * @param tipoUsuario El tipo de usuario (trabajador, administrador, etc.)
     * @param estado El estado del usuario (contratado, suspendido, etc.)
     */
    public Usuario(int id, String nombre, String contraseña, String tipoUsuario, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.tipoUsuario = tipoUsuario;
        this.estado = estado;
    }

    // Métodos getters y setters
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public String getEstado() {
        return estado;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    // Métodos
    
    /**
     * Verifica las credenciales de un usuario en la base de datos.
     *
     * @param nombreUsuario El nombre de usuario a verificar
     * @param contraseña La contraseña del usuario a verificar
     * @return Un objeto de tipo Usuario si las credenciales son correctas, null
     * si no lo son
     */
    public static Usuario verificarCredenciales(String nombreUsuario, String contraseña) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = BaseDatos.conectar(); // Asegúrate de tener este método configurado
            String sql = "SELECT * FROM usuarios WHERE nombre = ? AND contraseña = ? AND estado = 'contratado'";
            ps = conn.prepareStatement(sql);
            ps.setString(1, nombreUsuario);
            ps.setString(2, contraseña);
            rs = ps.executeQuery();
            // Si se encuentra al usuario con las credenciales correctas

            if (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String tipo = rs.getString("tipo_usuario");
                String estado = rs.getString("estado");

                // Retorna un objeto Usuario con los datos encontrados
                return new Usuario(id, nombre, contraseña, tipo, estado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrando los recursos
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {}
            
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {}
            
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {}
        }

        return null;// Si no se encuentra el usuario, retorna null
    }

}
