package pe.edu.vallegrande.eventix.util;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReporteUtil {
    
    public static void generarReportePDF(List<?> datos, String tituloReporte) {
        try {
            // Cargar el archivo .jrxml desde la ruta especificada
            File archivoJrxml = new File("C:\\Users\\mailyn\\Downloads\\Eventix\\eventix_db.jrxml");
            if (!archivoJrxml.exists()) {
                throw new JRException("Archivo eventix_db.jrxml no encontrado en C:\\Users\\mailyn\\Downloads\\Eventix");
            }
            
            JasperDesign jasperDesign = JRXmlLoader.load(archivoJrxml);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            
            // Crear el datasource con los datos
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(datos);
            
            // Parámetros del reporte
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("TITULO", tituloReporte);
            
            // Llenar el reporte
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, dataSource);
            
            // Exportar a PDF en la ruta especificada
            String rutaSalida = "C:\\Users\\mailyn\\Downloads\\Eventix\\";
            String nombreArchivo = rutaSalida + "Reporte_Eventos_" + System.currentTimeMillis() + ".pdf";
            JasperExportManager.exportReportToPdfFile(jasperPrint, nombreArchivo);
            
            // Mostrar mensaje de éxito
            javax.swing.JOptionPane.showMessageDialog(null, 
                    "Reporte PDF generado exitosamente: " + nombreArchivo, 
                    "Éxito", 
                    javax.swing.JOptionPane.INFORMATION_MESSAGE);
            
            // Abrir el visor de PDF
            abrirPDF(nombreArchivo);
            
        } catch (JRException e) {
            e.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null, 
                    "Error al generar el reporte: " + e.getMessage(), 
                    "Error", 
                    javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void verReporte(List<?> datos, String tituloReporte) {
        try {
            // Cargar el archivo .jrxml desde la ruta especificada
            File archivoJrxml = new File("C:\\Users\\mailyn\\Downloads\\Eventix\\eventix_db.jrxml");
            if (!archivoJrxml.exists()) {
                throw new JRException("Archivo eventix_db.jrxml no encontrado en C:\\Users\\mailyn\\Downloads\\Eventix");
            }
            
            JasperDesign jasperDesign = JRXmlLoader.load(archivoJrxml);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            
            // Crear el datasource con los datos
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(datos);
            
            // Parámetros del reporte
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("TITULO", tituloReporte);
            
            // Llenar el reporte
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, dataSource);
            
            // Mostrar el visor de JasperReports
            JasperViewer viewer = new JasperViewer(jasperPrint, false);
            viewer.setTitle("Reporte de Eventos - Eventix");
            viewer.setVisible(true);
            
        } catch (JRException e) {
            e.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null, 
                    "Error al generar el reporte: " + e.getMessage(), 
                    "Error", 
                    javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private static void abrirPDF(String nombreArchivo) {
        try {
            File archivo = new File(nombreArchivo);
            if (archivo.exists()) {
                java.awt.Desktop.getDesktop().open(archivo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
