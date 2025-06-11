/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectointegrador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.FileOutputStream;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.poi.util.Units;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

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

    private String dni;
    private String nombreCliente;
    private String codigoBoleta;

    public Boleta(Empresa empresa, String dni,  String nombreCliente, String total, String descuento, DefaultTableModel modelo) {
        super(empresa, total, descuento, modelo);
        this.dni = dni;
        this.nombreCliente = nombreCliente;
    }

    public Boleta(BaseDatos bd, String dni, String nombreCliente, String codigoBoleta) {
        super(bd);
        this.dni = dni;
        this.nombreCliente = nombreCliente;
        this.codigoBoleta = codigoBoleta;
    }

    public Boleta(String dni, String nombreCliente, Venta VentaActual) {
        super(VentaActual);
        this.dni = dni;
        this.nombreCliente = nombreCliente;
    }
    
    //METODOS
    
    @Override
    public String generarPDF() {
         try {
        String carpeta = "D:/Documentos/Netbeans/Proyectos/ProyectoIntegrador/BoletasWord";
        Files.createDirectories(Paths.get(carpeta));

        String nombreArchivo = "Boleta_" + System.currentTimeMillis() + ".docx";
        String ruta = carpeta + "/" + nombreArchivo;

        XWPFDocument document = new XWPFDocument();
        FileOutputStream out = new FileOutputStream(ruta);

        // LOGO (si existe)
        int idEmpresaSeleccionada = empresa.getIdempresa();
        String logoPath = Empresa.getLogoPathPorEmpresaId(idEmpresaSeleccionada);
        try {
            InputStream pic = new FileInputStream(logoPath);
            XWPFParagraph logoParagraph = document.createParagraph();
            logoParagraph.setAlignment(ParagraphAlignment.LEFT);
            XWPFRun logoRun = logoParagraph.createRun();
            logoRun.addPicture(pic, XWPFDocument.PICTURE_TYPE_PNG, logoPath, Units.toEMU(100), Units.toEMU(100));
            pic.close();
        } catch (Exception e) {
            System.out.println("No se encontró el logo en la ruta: " + logoPath);
        }

        // Datos de la empresa
        XWPFParagraph datosEmpresa = document.createParagraph();
        XWPFRun runEmpresa = datosEmpresa.createRun();
        runEmpresa.setText(empresa.getNombreEmpresa());
        runEmpresa.addBreak();
        runEmpresa.setText("RUC: " + empresa.getRUC());
        runEmpresa.addBreak();
        runEmpresa.setText("Dirección: " + empresa.getDireccion());
        runEmpresa.addBreak();
        runEmpresa.setText("Teléfono: " + empresa.getTelefono());
        runEmpresa.addBreak();
        runEmpresa.setText("Correo: " + empresa.getCorreo());

        document.createParagraph(); // espacio

        // Cliente y DNI
        XWPFParagraph clienteP = document.createParagraph();
        XWPFRun clienteRun = clienteP.createRun();
        clienteRun.setText("Cliente: " + nombreCliente);
        clienteRun.addBreak();
        clienteRun.setText("DNI: " + dni);

        document.createParagraph(); // espacio

        // Tabla de productos
        XWPFTable tabla = document.createTable();
        XWPFTableRow header = tabla.getRow(0);
        header.getCell(0).setText("Nombre");
        header.addNewTableCell().setText("Cantidad");
        header.addNewTableCell().setText("Subtotal");

        for (int i = 0; i < modelo.getRowCount(); i++) {
            XWPFTableRow fila = tabla.createRow();
            fila.getCell(0).setText(modelo.getValueAt(i, 0).toString());
            fila.getCell(1).setText(modelo.getValueAt(i, 1).toString());
            fila.getCell(2).setText(modelo.getValueAt(i, 2).toString());
        }

        document.createParagraph(); // espacio

        // Descuento
        XWPFParagraph descP = document.createParagraph();
        descP.setAlignment(ParagraphAlignment.RIGHT);
        XWPFRun descRun = descP.createRun();
        descRun.setText("Descuento: " + descuento + " %");

        // Total
        XWPFParagraph totalP = document.createParagraph();
        totalP.setAlignment(ParagraphAlignment.RIGHT);
        XWPFRun totalRun = totalP.createRun();
        totalRun.setText("Total: S/ " + total);

        // Guardar documento
        document.write(out);
        out.close();
        document.close();

        System.out.println("Word generado en: " + ruta);
        JOptionPane.showMessageDialog(null, "Boleta Word generada correctamente.");

        return nombreArchivo;

    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al generar la boleta.");
        return null;
    }
    }

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
