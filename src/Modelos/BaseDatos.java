/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

// Importaciones necesarias para conexiones y logs.
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase BaseDatos
 *
 * Esta clase gestiona la conexión con la base de datos MySQL utilizada en el
 * sistema. Ofrece dos métodos para conectarse: - Uno para obtener una nueva
 * conexión cada vez que se invoque (`conectar()`). - Otro para mantener una
 * única conexión persistente durante la ejecución (`getConnection()`).
 *
 * La base de datos utilizada se llama "minimarket_db", y los datos de acceso
 * están definidos como constantes.
 *
 * Es recomendable utilizar `getConnection()` cuando se desea mantener una
 * conexión abierta a lo largo del ciclo de vida de la aplicación, especialmente
 * en aplicaciones de escritorio.
 * 
 * @author GPatr
 */
public class BaseDatos {
    private static final Logger logger = LoggerFactory.getLogger(BaseDatos.class);
    // URL de conexión a la base de datos (nombre de la base de datos: minimarket_db)
    private static final String URL = "jdbc:mysql://localhost:3306/minimarket_db";
    // Usuario y contraseña para la base de datos
    private static final String USUARIO = "root";
    private static final String CONTRASEÑA = "root";
    // Objeto Connection persistente
    private static Connection con = null;

    /**
     * Método para obtener una nueva conexión cada vez que se llame. Ideal para
     * operaciones que requieren conexiones temporales.
     *
     * @return Nueva conexión a la base de datos.
     * @throws SQLException Si ocurre un error al establecer la conexión.
     */
    public static Connection conectar() throws SQLException {
        logger.info("Intentando conectar a la base de datos (método conectar)");
        Connection nuevaConexion = DriverManager.getConnection(URL, USUARIO, CONTRASEÑA);
        logger.info("Conexión temporal establecida con éxito");
        return nuevaConexion;
    }

    /**
     * Método que devuelve una única conexión persistente. Si ya existe una
     * conexión activa, la retorna; de lo contrario, crea una nueva.
     *
     * @return Conexión persistente a la base de datos.
     */
    public static Connection getConnection() {
        // Si no hay una conexión creada, se crea una nueva.
        if (con == null) {
            try {
                logger.info("Estableciendo conexión persistente a la base de datos");
                con = DriverManager.getConnection(URL, USUARIO, CONTRASEÑA);
                logger.info("Conexión persistente establecida correctamente");
            } catch (SQLException ex) {
                logger.error("Error al establecer conexión persistente con la base de datos", ex);
            }
        } else {
            // Si ya existe una conexión, se reutiliza.
            logger.debug("Conexión persistente reutilizada");
        }
        return con;
    }
}