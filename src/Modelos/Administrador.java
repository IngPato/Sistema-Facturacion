/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

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

}