/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Controladores;

import DAO.FakeProductoDAO;
import DAO.ProductoDAO;
import Modelos.Producto;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AdministradorControladorTest {

    private AdministradorControlador controlador;

    @Before
    public void setUp() {
        // Subclase anónima para reemplazar el DAO con un fake
        controlador = new AdministradorControlador() {
            private final ProductoDAO fakeDAO = new FakeProductoDAO();

            // Sobrescribimos todos los métodos para usar el fakeDAO
            @Override
            public boolean agregarProducto(Producto producto) {
                return fakeDAO.agregarProducto(producto);
            }

            @Override
            public boolean editarProducto(Producto producto) {
                return fakeDAO.editarProducto(producto);
            }

            @Override
            public boolean eliminarProducto(int id) {
                return fakeDAO.eliminarProducto(id);
            }

            @Override
            public List<Producto> listarProductos() {
                return fakeDAO.listarProductos();
            }

            @Override
            public List<Producto> buscarProducto(String valor) {
                return fakeDAO.buscarProducto(valor);
            }
        };
    }

    @Test
    public void testAgregarProducto() {
        Producto producto = new Producto(1, "Teclado", "Tecnología", 100.0, 10, "TEC-001");
        boolean resultado = controlador.agregarProducto(producto);
        assertTrue(resultado);
    }

    @Test
    public void testEditarProducto() {
        Producto producto = new Producto(2, "Mouse", "Tecnología", 50.0, 5, "MOU-001");
        controlador.agregarProducto(producto);

        Producto productoEditado = new Producto(2, "Mouse Inalámbrico", "Tecnología", 60.0, 6, "MOU-002");
        boolean resultado = controlador.editarProducto(productoEditado);

        assertTrue(resultado);
    }

    @Test
    public void testEliminarProducto() {
        Producto producto = new Producto(3, "Monitor", "Pantallas", 500.0, 2, "MON-001");
        controlador.agregarProducto(producto);

        boolean resultado = controlador.eliminarProducto(3);
        assertTrue(resultado);
    }

    @Test
    public void testListarProductos() {
        controlador.agregarProducto(new Producto(4, "CPU", "Hardware", 1200.0, 1, "CPU-001"));
        controlador.agregarProducto(new Producto(5, "RAM", "Hardware", 300.0, 4, "RAM-001"));

        List<Producto> lista = controlador.listarProductos();
        assertEquals(2, lista.size());
    }

    @Test
    public void testBuscarProducto() {
        controlador.agregarProducto(new Producto(6, "Cámara", "Tecnología", 800.0, 3, "CAM-001"));

        List<Producto> resultado = controlador.buscarProducto("Cámara");
        assertEquals(1, resultado.size());
        assertEquals("Cámara", resultado.get(0).getNombre());
    }
}
