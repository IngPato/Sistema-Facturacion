/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectointegradorweb;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import org.apache.catalina.startup.Tomcat;
import java.io.IOException;
import java.util.Random;
import org.apache.catalina.Context;
/**
 *
 * @author GPatr
 */
// Servlet que responderá al endpoint /mensaje
public class ServidorTomcat {

    public static void main(String[] args) throws Exception {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8081);
        tomcat.getConnector();

        String contextPath = "";
        String docBase = System.getProperty("java.io.tmpdir");
        Context ctx = tomcat.addContext(contextPath, docBase);

        Tomcat.addServlet(ctx, "mensajeServlet", new HttpServlet() {
            @Override
            protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
                int codigo = (int)(Math.random() * 100000); // <- Mover esta línea arriba

                String userAgent = req.getHeader("User-Agent");
                boolean esSwing = (userAgent == null || userAgent.contains("Java"));

                if (esSwing) {
                    resp.setContentType("text/plain; charset=UTF-8");
                    resp.getWriter().write(
                        "Este comprobante ya fue registrado correctamente\n" +
                        "en el sistema contable interno.\n" +
                        "Área de Contabilidad\n" +
                        "Código Ref: CONT-" + codigo
                    );
                    return;
                }
                resp.setContentType("text/html");
                resp.setCharacterEncoding("UTF-8");

                int ingresos = (int)(Math.random() * 10000 + 1000);
                int egresos = (int)(Math.random() * 5000 + 500);
                int facturas = (int)(Math.random() * 50 + 1);
                int boletas = (int)(Math.random() * 100 + 10);
                
                StringBuilder html = new StringBuilder();
                html.append("<!DOCTYPE html>")
                    .append("<html lang=\"es\">")
                    .append("<head>")
                    .append("<meta charset=\"UTF-8\">")
                    .append("<title>Confirmación de Comprobante</title>")
                    .append("<style>")
                    .append("body { font-family: Arial, sans-serif; background-color: #D8FFD8; margin: 0; padding: 0; }")
                    .append("header { background-color: #FFFFFF; border-bottom: 5px solid #83B3FF; padding: 20px 0; text-align: center; font-size: 28px; font-weight: bold; color: #333; }")
                    .append(".container { display: flex; justify-content: center; gap: 20px; padding: 40px 20px; flex-wrap: wrap; }")
                    .append(".card { background-color: #ffffff; border-left: 8px solid #83B3FF; padding: 20px; width: 200px; box-shadow: 0 2px 8px rgba(0,0,0,0.1); border-radius: 10px; text-align: center; }")
                    .append(".card h2 { font-size: 20px; margin: 0; color: #333; }")
                    .append(".card p { font-size: 24px; color: #2c3e50; margin: 10px 0 0; font-weight: bold; }")
                    .append(".grafico-contenedor { display: flex; justify-content: center; align-items: flex-start; gap: 20px; margin: 30px auto; max-width: 1000px; padding: 0 20px; }")
                    .append(".grafico { flex: 2; background: #fff; border: 3px solid #83B3FF; padding: 20px; border-radius: 10px; position: relative; }")
                    .append(".grafico::before { content: ''; position: absolute; top: 0; left: 0; width: 100%; height: 100%;")
                    .append("background-image: linear-gradient(to right, rgba(0,0,0,0.05) 1px, transparent 1px), ")
                    .append("linear-gradient(to bottom, rgba(0,0,0,0.05) 1px, transparent 1px); background-size: 40px 40px; z-index: 0; }")
                    .append(".grafico-content { position: relative; z-index: 1; }")
                    .append(".bar { height: 25px; margin: 10px 0; background: #83B3FF; text-align: right; padding-right: 10px; color: #fff; border-radius: 5px; }")
                    .append(".bar.egreso { background: #FF6B6B; }")
                    .append(".mensaje { flex: 1; background-color: #FFFFFF; border: 3px solid #83B3FF; padding: 20px; border-radius: 10px; text-align: center; font-size: 16px; color: #333; box-shadow: 0 2px 8px rgba(0,0,0,0.1); }")
                    .append("</style>")
                    .append("</head>")
                    .append("<body>")
                    .append("<header>CONTABILIDAD</header>")
                    .append("<div class=\"container\">")
                    .append("<div class=\"card\"><h2>Ingresos</h2><p>S/. ").append(ingresos).append("</p></div>")
                    .append("<div class=\"card\"><h2>Egresos</h2><p>S/. ").append(egresos).append("</p></div>")
                    .append("<div class=\"card\"><h2>Facturas</h2><p>").append(facturas).append("</p></div>")
                    .append("<div class=\"card\"><h2>Boletas</h2><p>").append(boletas).append("</p></div>")
                    .append("</div>")

                    // Gráfico y mensaje al costado
                    .append("<div class=\"grafico-contenedor\">")

                    // Gráfico
                    .append("<div class=\"grafico\"><div class='grafico-content'>")
                    .append("<h2 style='text-align:center;color:#333;'>Resumen Mensual</h2>")
                    .append("<div style='margin: 20px 0;'>")
                    .append("<div style='color:#333;'>Ingresos</div>")
                    .append("<div class='bar' style='width:").append(Math.min(ingresos / 120.0, 100)).append("%;'>S/. ").append(ingresos).append("</div>")
                    .append("<div style='color:#333;'>Egresos</div>")
                    .append("<div class='bar egreso' style='width:").append(Math.min(egresos / 120.0, 100)).append("%;'>S/. ").append(egresos).append("</div>")
                    .append("</div></div></div>")

                    // Mensaje comprobante
                    .append("<div class=\"mensaje\">")
                    .append("<h2>Agregar Comprobantes</h2>")
                    .append("<p style='margin: 20px 0 0;'>Área de Contabilidad</p>")
                    .append("<p><strong>Código Ref: CONT-").append(codigo).append("</strong></p>")
                    .append("</div>")

                    .append("</div>") // grafico-contenedor
                    .append("</body></html>");

                resp.getWriter().write(html.toString());
            }
        });

        ctx.addServletMappingDecoded("/mensaje", "mensajeServlet");

        System.out.println("✅ Servidor iniciado en: http://localhost:8081/mensaje");
        tomcat.start();
        tomcat.getServer().await();
    }
}
