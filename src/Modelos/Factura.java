/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 * Clase Factura que extiende de Venta.
 *
 * Representa una factura emitida a una empresa, incluyendo datos como RUC,
 * razón social y correo electrónico del cliente. Hereda atributos y métodos de
 * la clase Venta.
 * 
 * @author GPatr
 */
public class Factura extends Comprobante {
      //Atributos.
    private String ruc;
    private String razonSocial;
    private String correo;
    private String codigoFactura;

    //Constructores.
    // Constructor utilizado al generar factura desde formulario.
    public Factura(Empresa empresa, String ruc, String razonSocial, String correo, String total, String descuento, DefaultTableModel modelo) {
        super(empresa, total, descuento, modelo);
        this.ruc = ruc;
        this.razonSocial = razonSocial;
        this.correo = correo;
    }
    // Constructor para registrar en base de datos.
    public Factura(BaseDatos bd, String ruc, String razonSocial, String correo, String codigoFactura) {
        super(bd);
        this.ruc = ruc;
        this.razonSocial = razonSocial;
        this.correo = correo;
        this.codigoFactura = codigoFactura;
    }
    // Constructor para usar los datos de una venta ya realizada.
    public Factura(String ruc, String razonSocial, String correo, Venta VentaActual) {
        super(VentaActual);
        this.ruc = ruc;
        this.razonSocial = razonSocial;
        this.correo = correo;
    }

    //METODOS
    /**
     * Genera un archivo PDF que representa la factura actual.
     *
     * @return El nombre del archivo generado, o null si hubo error.
     */
    @Override
    public String generarPDF() {
        try {
            String carpeta = "D:/Documentos/Netbeans/Proyectos/ProyectoIntegrador/FacPDF";
            
            String nombreArchivo = "Factura_" + System.currentTimeMillis() + ".pdf";
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

            // Datos de la empresa emisora.
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

            // Datos del cliente empresa.
            document.add(new Paragraph("RUC Cliente: " + ruc));
            document.add(new Paragraph("Razón Social: " + razonSocial));
            document.add(new Paragraph("Correo: " + correo));
            document.add(new Paragraph(" "));

            // Tabla con detalle de productos.
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
            
            // Monto de descuento.
            Paragraph descuentop = new Paragraph("Descuento: " + descuento + " %");
            descuentop.setAlignment(Element.ALIGN_RIGHT);
            document.add(descuentop);

            // Total final a pagar.
            Paragraph totalP = new Paragraph("Total: S/ " + total);
            totalP.setAlignment(Element.ALIGN_RIGHT);
            document.add(totalP);

            document.close();
            System.out.println("PDF generado en: " + ruta);
            JOptionPane.showMessageDialog(null, "Factura PDF generada correctamente.");

            return nombreArchivo;

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al generar la Factura.");
                return null;
            }
    }

    /**
     * Registra la factura en la base de datos, asociándola a una venta ya realizada.
     *
     * @param bd Instancia de la base de datos.
     * @param idventa ID de la venta asociada.
     */
    @Override
    public void registrarComprobanteEnBD(BaseDatos bd, int idventa) {
        try {
            Connection con = bd.conectar();
            String sql = "INSERT INTO factura (ruc, razon, correo, codigo, idventa) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, ruc);
            ps.setString(2, razonSocial);
            ps.setString(3, correo);
            ps.setString(4, codigoFactura);
            ps.setInt(5, idventa);

            ps.executeUpdate();
            ps.close();
            System.out.println("Factura registrada en la base de datos.");

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al registrar la Factura en la base de datos.");
        }
    }
}