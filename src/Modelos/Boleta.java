/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// Importaciones necesarias para conexión SQL, creación de PDFs y tablas de Swing.
package Modelos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.FileOutputStream;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Clase Boleta
 *
 * Representa una boleta de venta en el sistema. Hereda de la clase Venta e
 * incluye datos adicionales específicos como el nombre del cliente y su DNI.
 *
 * Al instanciarse, automáticamente se define el tipo de comprobante como
 * "Boleta".
 * 
 * @author GPatr
 */
public class Boleta extends Comprobante {

    // Son los atributos propios de la boleta.
    private String dni;
    private String nombreCliente;
    private String codigoBoleta;

    public Boleta(Empresa empresa, String dni,  String nombreCliente, String total, String descuento, DefaultTableModel modelo) {
        super(empresa, total, descuento, modelo);
        this.dni = dni;
        this.nombreCliente = nombreCliente;
    }
    // Constructor que registra boleta en BD.
    public Boleta(BaseDatos bd, String dni, String nombreCliente, String codigoBoleta) {
        super(bd);
        this.dni = dni;
        this.nombreCliente = nombreCliente;
        this.codigoBoleta = codigoBoleta;
    }
    // Constructor que hereda datos de una Venta.
    public Boleta(String dni, String nombreCliente, Venta VentaActual) {
        super(VentaActual);
        this.dni = dni;
        this.nombreCliente = nombreCliente;
    }
    
    //METODOS
    
    @Override
    public String generarPDF() {
        try {
        
        String carpeta = "D:/Documentos/Netbeans/Proyectos/ProyectoIntegrador/BolPDF";
       

        
        String nombreArchivo = "Boleta_" + System.currentTimeMillis() + ".pdf";
        String ruta = carpeta + "/" + nombreArchivo;

       
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(ruta));
        document.open();

        
        int idEmpresaSeleccionada = empresa.getIdempresa();
        String logoPath = Empresa.getLogoPathPorEmpresaId(idEmpresaSeleccionada);

        
        try {
            Image logo = Image.getInstance(logoPath);
            logo.scaleToFit(100, 100);
            logo.setAlignment(Image.ALIGN_LEFT);
            document.add(logo);
        } catch (Exception e) {
            System.out.println("No se encontró el logo en la ruta: " + logoPath);
        }

        // Agrega datos de la empresa.
        Paragraph datosEmpresa = new Paragraph(
            empresa.getNombreEmpresa() + "\n" +
            "RUC: " + empresa.getRUC() + "\n" +
            "Dirección: " + empresa.getDireccion() + "\n" +
            "Teléfono: " + empresa.getTelefono() + "\n" +
            "Correo: " + empresa.getCorreo()
        );
        datosEmpresa.setAlignment(Element.ALIGN_LEFT);
        document.add(datosEmpresa);

        document.add(new Paragraph(" ")); 

        //CLIENTE y DNI
        document.add(new Paragraph("Cliente: " + nombreCliente));
        document.add(new Paragraph("DNI: " + dni));
        document.add(new Paragraph(" "));

        // Tabla con productos (nombre, cantidad, subtotal).
        PdfPTable tabla = new PdfPTable(3);
        tabla.addCell("Nombre");
        tabla.addCell("Cantidad");
        tabla.addCell("Subtotal");

        for (int i = 0; i < modelo.getRowCount(); i++) {
            tabla.addCell(modelo.getValueAt(i, 0).toString());
            tabla.addCell(modelo.getValueAt(i, 1).toString());
            tabla.addCell(modelo.getValueAt(i, 2).toString());
        }

        document.add(tabla);
        
        // Muestra el descuento aplicado.
        Paragraph descuentop = new Paragraph("Descuento: " + descuento + " %");
        descuentop.setAlignment(Element.ALIGN_RIGHT);
        document.add(descuentop);

        // Muestra el total final.
        Paragraph totalP = new Paragraph("Total: S/ " + total);
        totalP.setAlignment(Element.ALIGN_RIGHT);
        document.add(totalP);
        
        document.close();
        System.out.println("PDF generado en: " + ruta);
        JOptionPane.showMessageDialog(null, "Boleta PDF generada correctamente.");

        return nombreArchivo;

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al generar la boleta.");
            return null;
        }
    }
     /**
     * Registra los datos de la boleta en la base de datos.
     * @param bd Instancia de la base de datos.
     * @param idventa ID de la venta asociada.
     */
    @Override
    public void registrarComprobanteEnBD(BaseDatos bd, int idventa) {
        try {
            Connection con = bd.conectar();
            String sql = "INSERT INTO boleta (dni, nombre, codigo, idventa) VALUES (?, ?, ?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, dni);
            ps.setString(2, nombreCliente);
            ps.setString(3, codigoBoleta);
            ps.setInt(4, idventa);

            ps.executeUpdate();
            ps.close();
            System.out.println("Boleta registrada en la base de datos.");

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al registrar la boleta en la base de datos.");
        }
    }
    
}