/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectointegrador;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author GPatr
 */
public abstract class Comprobante {

    protected Empresa empresa;
    protected DefaultTableModel modelo;
    protected String total;
    protected String descuento;
    protected BaseDatos BaseDatos;
    protected Venta VentaActual;
    

    /**
     * Constructor base para comprobantes.
     * 
     * @param empresa    Empresa seleccionada
     * @param total      Total de la venta (ya con descuento)
     * @param descuento  Descuento aplicado
     * @param modelo     Modelo de la tabla con productos
     */
    public Comprobante(Empresa empresa, String total, String descuento, DefaultTableModel modelo) {
        this.empresa = empresa;
        this.total = total;
        this.descuento = descuento;
        this.modelo = modelo; 
    }
    
    public Comprobante(BaseDatos bd) {
        this.BaseDatos = bd;
    }

    public Comprobante(Venta VentaActual) {
        this.VentaActual = VentaActual;
    }
    
    

    /**
     * Método abstracto que debe generar el PDF del comprobante.
     */
    public abstract String generarPDF();

    /**
     * Método abstracto que debe registrar el comprobante en la base de datos.
     */
    public abstract void registrarComprobanteEnBD(BaseDatos bd, int idVenta);
}