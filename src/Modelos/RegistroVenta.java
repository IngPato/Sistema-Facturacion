/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * La clase RegistroVenta representa una venta de un producto específico.
 * Contiene los atributos relacionados con la venta, como el ID del producto, el
 * nombre del producto, la fecha de la venta y la cantidad vendida.
 *
 * @author GPatr
 */
public class RegistroVenta {

    //METODO DE CATEGORIA
    public static void generarExcelPorCategoria() {
        // Consulta para obtener la categoría y la suma de cantidades vendidas
        String sql = "SELECT p.categoria AS categoria, SUM(dv.cantidad) AS total_vendido " +
                     "FROM detalle_venta dv " +
                     "JOIN productos p ON dv.idproducto = p.id " +
                     "GROUP BY p.categoria " +
                     "ORDER BY total_vendido DESC";

        // Ruta donde se guardará el archivo
        String rutaSalida = "ExcelCategoriaRV/VentasPorCategoria.xlsx";

        try (
            Connection conn = BaseDatos.conectar(); // tu clase de conexión
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            Workbook workbook = new XSSFWorkbook()
        ) {
            Sheet sheet = workbook.createSheet("Ventas por Categoría");

            // Crear cabecera
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("Categoría");
            header.createCell(1).setCellValue("Total Vendido");

            int rowIndex = 1;
            while (rs.next()) {
                Row row = sheet.createRow(rowIndex++);
                row.createCell(0).setCellValue(rs.getString("categoria"));
                row.createCell(1).setCellValue(rs.getInt("total_vendido"));
            }

            // Ajustar ancho de columnas
            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);

            // Crear carpeta si no existe
            java.io.File carpeta = new java.io.File("ExcelCategoriaRV");
            if (!carpeta.exists()) carpeta.mkdirs();

            // Guardar el archivo Excel
            try (FileOutputStream fileOut = new FileOutputStream(rutaSalida)) {
                workbook.write(fileOut);
                System.out.println("Excel generado exitosamente: " + rutaSalida);
            }

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
    
    //METODO DE TRABAJADOR
    
    public static void generarExcelPorTrabajador() {
    String sql = "SELECT u.nombre AS trabajador, " +
             "       v.comprobante AS tipo_comprobante, " +
             "       IF(v.comprobante = 'boleta', b.codigo, f.codigo) AS codigo_comprobante, " +
             "       v.fecha AS fecha_venta, " +
             "       v.total AS total_venta " +
             "FROM venta v " +
             "JOIN usuarios u ON v.idtrabajador = u.id " +
             "LEFT JOIN boleta b ON v.id = b.idventa " +
             "LEFT JOIN factura f ON v.id = f.idventa " +
             "WHERE u.tipo_usuario = 'Trabajador' " +
             "ORDER BY u.nombre, v.total DESC";

    String rutaSalida = "ExcelTrabajadoreRV/VentasPorTrabajador.xlsx";

    try (
        Connection conn = BaseDatos.conectar();
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        Workbook workbook = new XSSFWorkbook()
    ) {
        Sheet sheet = workbook.createSheet("Ventas por Trabajador");

        // Cabecera
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Trabajador");
        header.createCell(1).setCellValue("Tipo");
        header.createCell(2).setCellValue("Código Comprobante");
        header.createCell(3).setCellValue("Fecha Venta");
        header.createCell(4).setCellValue("Total Venta");

        int rowIndex = 1;
        while (rs.next()) {
            Row row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue(rs.getString("trabajador"));
            row.createCell(1).setCellValue(rs.getString("tipo_comprobante"));
            row.createCell(2).setCellValue(rs.getString("codigo_comprobante"));
            row.createCell(3).setCellValue(rs.getString("fecha_venta"));
            row.createCell(4).setCellValue(rs.getDouble("total_venta"));
        }

        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);
        sheet.autoSizeColumn(3);
        sheet.autoSizeColumn(4);

        java.io.File carpeta = new java.io.File("ExcelTrabajadoreRV");
            if (!carpeta.exists()) carpeta.mkdirs();

        try (FileOutputStream fileOut = new FileOutputStream(rutaSalida)) {
            workbook.write(fileOut);
            System.out.println("Excel generado exitosamente: " + rutaSalida);
        }

    } catch (SQLException | IOException e) {
        e.printStackTrace();
    }
}
    
    // METODO DE PRODUCTO
    
    public static void generarExcelPorProducto() {
    String sql = "SELECT p.nombre AS nombreProducto, " +
                 "       COALESCE(SUM(dv.cantidad), 0) AS total_vendido " +
                 "FROM productos p " +
                 "LEFT JOIN detalle_venta dv ON p.id = dv.idproducto " +
                 "GROUP BY p.id, p.nombre " +
                 "ORDER BY total_vendido DESC";

    String rutaSalida = "ExcelProductoRV/VentasPorProducto.xlsx";

    try (
        Connection conn = BaseDatos.conectar();
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        Workbook workbook = new XSSFWorkbook()
    ) {
        Sheet sheet = workbook.createSheet("Ventas por Producto");

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Producto");
        header.createCell(1).setCellValue("Cantidad Vendida");

        int rowIndex = 1;
        while (rs.next()) {
            Row row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue(rs.getString("nombreProducto"));
            row.createCell(1).setCellValue(rs.getInt("total_vendido"));
        }

        // Asegúrate de que el directorio exista
        java.io.File carpeta = new java.io.File("ExcelProductoRV");
            if (!carpeta.exists()) carpeta.mkdirs();

        try (FileOutputStream fileOut = new FileOutputStream(rutaSalida)) {
            workbook.write(fileOut);
            System.out.println("Excel generado exitosamente: " + rutaSalida);
        }

        System.out.println("Archivo Excel generado correctamente en: " + rutaSalida);

    } catch (SQLException | IOException e) {
        e.printStackTrace();
        throw new RuntimeException("Error al generar el Excel de ventas por producto: " + e.getMessage());
    }
}
    
    //METODO MES
    public static void generarExcelPorMes(int mesSeleccionado) {
    String sql = "SELECT p.nombre AS nombreProducto, SUM(dp.cantidad) AS total_vendido "
               + "FROM productos p "
               + "JOIN detalle_venta dp ON p.id = dp.idproducto "
               + "JOIN venta v ON dp.idventa = v.id "
               + "WHERE MONTH(v.fecha) = ? "
               + "GROUP BY p.id, p.nombre "
               + "ORDER BY total_vendido DESC";

    String rutaSalida = "ExcelMesRV/VentasPorProducto.xlsx";

    try (
        Connection conn = BaseDatos.conectar();
        PreparedStatement ps = conn.prepareStatement(sql);
        Workbook workbook = new XSSFWorkbook()
    ) {
        ps.setInt(1, mesSeleccionado + 1); // Porque JMonthChooser va de 0 a 11

        try (ResultSet rs = ps.executeQuery()) {
            Sheet sheet = workbook.createSheet("Ventas por Mes");

            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("Producto");
            header.createCell(1).setCellValue("Cantidad Vendida");

            int rowIndex = 1;
            while (rs.next()) {
                Row row = sheet.createRow(rowIndex++);
                row.createCell(0).setCellValue(rs.getString("nombreProducto"));
                row.createCell(1).setCellValue(rs.getInt("total_vendido"));
            }

            // Crear carpeta si no existe
            java.io.File carpeta = new java.io.File("ExcelMesRV");
            if (!carpeta.exists()) carpeta.mkdirs();

            try (FileOutputStream fileOut = new FileOutputStream(rutaSalida)) {
                workbook.write(fileOut);
                System.out.println("Excel generado exitosamente: " + rutaSalida);
            }
        }

    } catch (SQLException | IOException e) {
        e.printStackTrace();
        throw new RuntimeException("Error al generar el Excel de ventas por mes: " + e.getMessage());
    }
}

//METODO PARA EMPRESA
    public static void generarExcelPorEmpresa(int idEmpresa) {
        String sql = "SELECT " +
                "    e.nombre_empresa, " +
                "    v.id AS id_venta, " +
                "    v.fecha, " +
                "    v.total, " +
                "    IF(b.id IS NOT NULL, 'Boleta', IF(f.id IS NOT NULL, 'Factura', 'Desconocido')) AS tipo_comprobante, " +
                "    COALESCE(b.codigo, f.codigo) AS codigo_comprobante " +
                "FROM venta v " +
                "JOIN detalle_venta dv ON v.id = dv.idventa " +
                "JOIN empresa e ON dv.idempresa = e.id " +
                "LEFT JOIN boleta b ON v.id = b.idventa " +
                "LEFT JOIN factura f ON v.id = f.idventa " +
                "WHERE e.id = ? " +
                "GROUP BY e.nombre_empresa, v.id, v.fecha, v.total, b.id, f.id, b.codigo, f.codigo " +
                "ORDER BY v.fecha DESC";

        String rutaSalida = "ExcelEmpresaRV/VentasPorEmpresa.xlsx";

        try (
            Connection conn = BaseDatos.conectar();
            PreparedStatement ps = conn.prepareStatement(sql);
            Workbook workbook = new XSSFWorkbook()
        ) {
            ps.setInt(1, idEmpresa);

            try (ResultSet rs = ps.executeQuery()) {
                Sheet sheet = workbook.createSheet("Ventas Empresa");

                // Encabezados
                Row header = sheet.createRow(0);
                header.createCell(0).setCellValue("Empresa");
                header.createCell(1).setCellValue("ID Venta");
                header.createCell(2).setCellValue("Fecha");
                header.createCell(3).setCellValue("Total");
                header.createCell(4).setCellValue("Tipo Comprobante");
                header.createCell(5).setCellValue("Código Comprobante");

                int rowIndex = 1;
                while (rs.next()) {
                    Row row = sheet.createRow(rowIndex++);
                    row.createCell(0).setCellValue(rs.getString("nombre_empresa"));
                    row.createCell(1).setCellValue(rs.getInt("id_venta"));
                    row.createCell(2).setCellValue(rs.getDate("fecha").toString());
                    row.createCell(3).setCellValue(rs.getDouble("total"));
                    row.createCell(4).setCellValue(rs.getString("tipo_comprobante"));
                    row.createCell(5).setCellValue(rs.getString("codigo_comprobante"));
                }

                // Crear carpeta si no existe
                java.io.File carpeta = new java.io.File("ExcelEmpresaRV");
                if (!carpeta.exists()) carpeta.mkdirs();

                try (FileOutputStream fileOut = new FileOutputStream(rutaSalida)) {
                    workbook.write(fileOut);
                    System.out.println("Excel generado exitosamente: " + rutaSalida);
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al generar el Excel de ventas por empresa: " + e.getMessage());
        }
    }
}
