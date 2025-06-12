/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyectointegrador;

import java.sql.Connection;
import java.sql.SQLException;
import Vistas.Login;

/**
 * La clase ProyectoIntegrador es la clase principal de la aplicación,
 * responsable de establecer la conexión con la base de datos y lanzar la
 * pantalla de inicio de sesión.
 *
 * @author GPatr
 */
public class ProyectoIntegrador {

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
        System.out.println("niciando prueba de conexión...");
        System.out.println(org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream.class.getProtectionDomain().getCodeSource().getLocation());

        try {
            // Intenta cargar el driver manualmente (por si el IDE no lo hace)
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("river JDBC encontrado.");
        } catch (ClassNotFoundException e) {
            System.out.println("No se encontró el driver JDBC de MySQL.");
            System.out.println("Asegúrate de tener el archivo 'mysql-connector-j-x.x.xx.jar' en las librerías del proyecto.");
            return;
        }

        try (Connection conn = BaseDatos.conectar()) {
            if (conn != null && !conn.isClosed()) {
                System.out.println("Conexión exitosa a la base de datos.");
            } else {
                System.out.println("La conexión se estableció pero está cerrada.");
            }
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos:");
            System.out.println("Mensaje: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("Código de error: " + e.getErrorCode());

            switch (e.getSQLState()) {
                case "08001":
                    System.out.println("Posible causa: el servidor de base de datos no está disponible (host o puerto incorrecto).");
                    break;
                case "28000":
                    System.out.println("Error de autenticación: usuario o contraseña incorrectos.");
                    break;
                case "42000":
                    System.out.println("La base de datos no existe o hay un error en la URL.");
                    break;
                default:
                    System.out.println("Error desconocido. Revisa la configuración y asegúrate de que MySQL esté ejecutándose.");
            }
            return; // Detener si hay error de conexión
        }

        // Si todo va bien, lanzar la interfaz gráfica
        java.awt.EventQueue.invokeLater(() -> new Login().setVisible(true));

        
        
        //crear empresa
        
        /**
        Empresa empresa = new Empresa(
            "TIENDAS TAMBO S.A.C.",
            "20563529378",
            "Av. Javier Prado Este 6210, Lima, Lima.",
            "963286105",
            "Contacto@tambo.pe"
        );

        // Llamar al método guardarEnBD() de la instancia creada
        boolean insertado = empresa.guardarEnBD();  // Llamar el método de la instancia

        if (insertado) {
            System.out.println("Empresa guardada correctamente.");
        } else {
            System.out.println("Error al guardar la empresa.");
        }
        
        //agregar empresa 
    
    /* //pruebas de clase productos - venta - carrito - boleta - factura
     *
     * // Crear productos Producto producto1 = new Producto(0, "Coca Cola
     * 500ml", "Gaseosa", 3.00, 12, "113367354"); Producto producto2 = new
     * Producto(1, "Galleta Margarita", "Galleta", 2.50, 6, "125379307");
     *
     * ejem de boleta Boleta boleta = new Boleta("Patrick Arribasplata",
     * "75607940"); boleta.agregarProducto(producto1, 2);
     * boleta.agregarProducto(producto2, 3); boleta.setDescuento(10); *
     * System.out.println("********** BOLETA **********");
     * System.out.println("Cliente: " + boleta.getNombreCliente());
     * System.out.println("DNI: " + boleta.getDni());
     * System.out.println("Subtotal: S/. " + boleta.getSubtotal());
     * System.out.println("Total con descuento: S/. " + boleta.getTotal());
     *
     * //ejm de factura Factura factura = new Factura("20165465009", "Policía
     * Nacional del Perú", "contacto@pnp.gob.pe");
     * //factura.agregarProducto(producto1, 4);
     * //factura.agregarProducto(producto2, 2); //factura.setDescuento(5); *
     * //System.out.println("\n********** FACTURA **********");
     * //System.out.println("RUC: " + factura.getRuc());
     * //System.out.println("Razón Social: " + factura.getRazonSocial());
     * //System.out.println("Correo: " + factura.getCorreo());
     * //System.out.println("Subtotal: S/. " + factura.getSubtotal());
     * //System.out.println("Total con descuento: S/. " + factura.getTotal());
     *
     */
        
    }
}

