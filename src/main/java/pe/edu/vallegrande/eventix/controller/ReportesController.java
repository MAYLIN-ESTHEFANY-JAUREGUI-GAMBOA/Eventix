package pe.edu.vallegrande.eventix.controller;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;
import pe.edu.vallegrande.eventix.util.MySQLConnection;
import pe.edu.vallegrande.eventix.view.ReportesView;

import javax.swing.*;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class ReportesController {

    private ReportesView vista;

    public ReportesController(ReportesView vista) {
        this.vista = vista;

        vista.btnVerReporte.addActionListener(e -> verReporte());
        vista.btnExportarPDF.addActionListener(e -> exportarPDF());
        vista.btnCerrar.addActionListener(e -> vista.dispose());
    }

    private void verReporte() {
        try {
            Connection cn = MySQLConnection.getConnection();

            if (cn == null) {
                JOptionPane.showMessageDialog(
                        vista,
                        "No se pudo conectar a la base de datos",
                        "Error de Conexión",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Compilar el reporte desde resources
            JasperReport reporte = JasperCompileManager.compileReport(
                    getClass().getResourceAsStream("/eventix_db.jrxml"));

            // Parámetros del reporte (vacío en este caso)
            Map<String, Object> parametros = new HashMap<>();

            // Llenar el reporte con datos de la base de datos
            JasperPrint print = JasperFillManager.fillReport(
                    reporte,
                    parametros,
                    cn);

            // Mostrar el reporte en el visor
            JasperViewer.viewReport(print, false);

            cn.close();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                    vista,
                    "Error al generar el reporte: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void exportarPDF() {
        try {
            Connection cn = MySQLConnection.getConnection();

            if (cn == null) {
                JOptionPane.showMessageDialog(
                        vista,
                        "No se pudo conectar a la base de datos",
                        "Error de Conexión",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Compilar el reporte desde resources
            JasperReport reporte = JasperCompileManager.compileReport(
                    getClass().getResourceAsStream("/eventix_db.jrxml"));

            // Parámetros del reporte
            Map<String, Object> parametros = new HashMap<>();

            // Llenar el reporte
            JasperPrint print = JasperFillManager.fillReport(
                    reporte,
                    parametros,
                    cn);

            // Exportar a PDF
            String rutaPDF = "Reporte_Eventos.pdf";
            JasperExportManager.exportReportToPdfFile(print, rutaPDF);

            cn.close();

            JOptionPane.showMessageDialog(
                    vista,
                    "PDF generado correctamente: " + rutaPDF,
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                    vista,
                    "Error al exportar PDF: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
