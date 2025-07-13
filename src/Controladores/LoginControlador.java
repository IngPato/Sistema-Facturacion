/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;
import Vistas.Login;
import Vistas.VistaAdministrador;
import Vistas.VistaTrabajador;
import Modelos.Usuario;
import com.google.common.base.Preconditions;
import javax.swing.*;
/**
 *
 * @author GPatr
 */
public class LoginControlador {
    private final Login vista;

    public LoginControlador(Login vista) {
        this.vista = vista;
        this.vista.getBtnIngresar().addActionListener(e -> procesarLogin());
    }

    private void procesarLogin() {
        try {
            String nombreUsuario = vista.getTxtUsuario().getText().trim();
            String contraseña = new String(vista.getTxtContraseña().getPassword()).trim();

            // Validación
            Preconditions.checkArgument(!nombreUsuario.isEmpty(), "El nombre de usuario es obligatorio.");
            Preconditions.checkArgument(!contraseña.isEmpty(), "La contraseña es obligatoria.");

            // Verificación
            Usuario usuario = Usuario.verificarCredenciales(nombreUsuario, contraseña);

            if (usuario != null) {
                Usuario.setUsuarioActual(usuario);
                switch (usuario.getTipoUsuario()) {
                    case "Administrador":
                        new VistaAdministrador().setVisible(true);
                        break;
                    case "Trabajador":
                        new VistaTrabajador().setVisible(true);
                        break;
                    default:
                        JOptionPane.showMessageDialog(vista, "Tipo de usuario desconocido.");
                        return;
                }
                vista.dispose();
            } else {
                JOptionPane.showMessageDialog(vista, "Credenciales incorrectas o usuario no contratado.");
            }

        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(vista, ex.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Ocurrió un error: " + ex.getMessage());
        }
    }
}
