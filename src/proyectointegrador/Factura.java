/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectointegrador;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
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

    private String ruc;
    private String razonSocial;
    private String correo;
    private String codigoFactura;

    //constructores
    
    public Factura(Empresa empresa, String ruc, String razonSocial, String correo, String total, String descuento, DefaultTableModel modelo) {
        super(empresa, total, descuento, modelo);
        this.ruc = ruc;
        this.razonSocial = razonSocial;
        this.correo = correo;
    }

    public Factura(BaseDatos bd, String ruc, String razonSocial, String correo, String codigoFactura) {
        super(bd);
        this.ruc = ruc;
        this.razonSocial = razonSocial;
        this.correo = correo;
        this.codigoFactura = codigoFactura;
    }

    public Factura(String ruc, String razonSocial, String correo, Venta VentaActual) {
        super(VentaActual);
        this.ruc = ruc;
        this.razonSocial = razonSocial;
        this.correo = correo;
    }

    //METODOS

    @Override
    public String generarPDF() {
        try {
            // Validar campos
            Validate.notBlank(ruc, "El RUC del cliente no puede estar vacío.");
            Validate.notBlank(razonSocial, "La razón social no puede estar vacía.");
            Validate.notBlank(correo, "El correo del cliente no puede estar vacío.");

            // Crear carpeta si no existe Apache Commons IO
            String carpeta = "D:/Documentos/Netbeans/Proyectos/ProyectoIntegrador/FacturasWord";
            File directorio = new File(carpeta);
            FileUtils.forceMkdir(directorio);  // Crea la carpeta y subcarpetas si no existen

            String nombreArchivo = "Factura_" + System.currentTimeMillis() + ".docx";
            String ruta = carpeta + "/" + nombreArchivo;

            XWPFDocument document = new XWPFDocument();
            FileOutputStream out = new FileOutputStream(ruta);

            // LOGO (si existe)
            int idEmpresaSeleccionada = empresa.getIdempresa();
            String logoPath = Empresa.getLogoPathPorEmpresaId(idEmpresaSeleccionada);
            try (InputStream pic = new FileInputStream(logoPath)) {
                XWPFParagraph logoParagraph = document.createParagraph();
                logoParagraph.setAlignment(ParagraphAlignment.LEFT);
                XWPFRun logoRun = logoParagraph.createRun();
                logoRun.addPicture(pic, XWPFDocument.PICTURE_TYPE_PNG, logoPath, Units.toEMU(100), Units.toEMU(100));
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

            // Cliente
            XWPFParagraph clienteP = document.createParagraph();
            XWPFRun clienteRun = clienteP.createRun();
            clienteRun.setText("RUC Cliente: " + ruc);
            clienteRun.addBreak();
            clienteRun.setText("Razón Social: " + razonSocial);
            clienteRun.addBreak();
            clienteRun.setText("Correo: " + correo);

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
            descRun.setText("Descuento: " + " S/." + descuento);

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
            JOptionPane.showMessageDialog(null, "Factura Word generada correctamente.");

            return nombreArchivo;

        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al generar la factura.");
            return null;
        }
    }

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