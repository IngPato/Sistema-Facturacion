/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectointegrador;

import java.sql.Date;

/**
 * La clase RegistroVenta representa una venta de un producto específico.
 * Contiene los atributos relacionados con la venta, como el ID del producto, el
 * nombre del producto, la fecha de la venta y la cantidad vendida.
 *
 * @author GPatr
 */
public class RegistroVenta {

    private int idProducto;
    private String nombreProducto;
    private java.sql.Date fecha;
    private int cantidadVendida;

    /**
     * Constructor de la clase RegistroVenta. Inicializa los atributos de la
     * venta con los valores proporcionados.
     *
     * @param idProducto Identificador del producto
     * @param nombreProducto Nombre del producto
     * @param fecha Fecha de la venta
     * @param cantidadVendida Cantidad de unidades vendidas
     */
    public RegistroVenta(int idProducto, String nombreProducto, java.sql.Date fecha, int cantidadVendida) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.fecha = fecha;
        this.cantidadVendida = cantidadVendida;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public java.sql.Date getFecha() {
        return fecha;
    }

    public int getCantidadVendida() {
        return cantidadVendida;
    }

    // SETTERS
    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setCantidadVendida(int cantidadVendida) {
        this.cantidadVendida = cantidadVendida;
    }

}
