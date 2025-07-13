/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package APP;
import Vistas.Login;
import Controladores.LoginControlador;

/**
 * La clase Inicializador es la clase principal de la aplicación,
 responsable de establecer la conexión con la base de datos y lanzar la
 pantalla de inicio de sesión.
 *
 * @author GPatr
 */
public class Inicializador {

    /**
     * El método main es el punto de entrada de la aplicación. Intenta
     * establecer una conexión con la base de datos MySQL y, en caso de éxito,
     * imprime un mensaje indicando que la conexión fue exitosa. Si hay algún
     * error de conexión, se imprime el mensaje de error correspondiente.
     * Posteriormente, inicia la interfaz gráfica mostrando la pantalla de
     * login.
     *
     * @param args Argumentos de la línea de comandos (no utilizados en esta
     * implementación)
     */
    public static void main(String[] args) {  
        // PRUEBA DE CONEXION CON LA BD
        // PRUEBA DE CONEXION CON LA BD
        System.out.println("Iniciando prueba de conexión...");
        System.out.println(org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream.class
                .getProtectionDomain().getCodeSource().getLocation());

        // Iniciar la interfaz gráfica y conectar el controlador
        java.awt.EventQueue.invokeLater(() -> {
            Login login = new Login();
            LoginControlador controladorLogin = new LoginControlador(login);
            login.setVisible(true);
        });
        
    }
}

