package pe.edu.vallegrande.eventix.controller;

import pe.edu.vallegrande.eventix.dao.EventoDAO;
import pe.edu.vallegrande.eventix.model.Evento;
import pe.edu.vallegrande.eventix.util.ReporteUtil;
import pe.edu.vallegrande.eventix.view.EventoView;
import pe.edu.vallegrande.eventix.view.DashboardView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class EventoController {
    private EventoView eventoView;
    private DashboardView dashboardView;
    private EventoDAO eventoDAO;
    
    public EventoController(EventoView eventoView, DashboardView dashboardView) {
        this.eventoView = eventoView;
        this.dashboardView = dashboardView;
        this.eventoDAO = new EventoDAO();
        
        cargarEventos();
        initController();
    }
    
    private void initController() {
        eventoView.getBtnNuevo().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eventoView.limpiarCampos();
                eventoView.getTxtNombreEvento().requestFocus();
            }
        });
        
        eventoView.getBtnGuardar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarEvento();
            }
        });
        
        eventoView.getBtnModificar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificarEvento();
            }
        });
        
        eventoView.getBtnEliminar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarEvento();
            }
        });
        
        eventoView.getBtnBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarEvento();
            }
        });
        
        eventoView.getBtnLimpiar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eventoView.limpiarCampos();
                cargarEventos();
                eventoView.getTxtBuscar().setText("");
            }
        });
        
        eventoView.getBtnVolver().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                volverDashboard();
            }
        });
        
        eventoView.getBtnReporte().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generarReporte();
            }
        });
    }
    
    private void guardarEvento() {
        try {
            String nombre = eventoView.getTxtNombreEvento().getText();
            String costoStr = eventoView.getTxtCosto().getText();
            
            if (nombre.isEmpty() || costoStr.isEmpty()) {
                eventoView.mostrarMensaje("Por favor, complete el nombre y el costo", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            String tipo = (String) eventoView.getCboTipoEvento().getSelectedItem();
            String modalidad = eventoView.getRbPresencial().isSelected() ? "Presencial" : "Virtual";
            
            StringBuilder servicios = new StringBuilder();
            if (eventoView.getChkCertificado().isSelected()) {
                servicios.append("Certificado");
            }
            if (eventoView.getChkMaterialDigital().isSelected()) {
                if (servicios.length() > 0) servicios.append(",");
                servicios.append("Material Digital");
            }
            if (eventoView.getChkCoffeeBreak().isSelected()) {
                if (servicios.length() > 0) servicios.append(",");
                servicios.append("Coffee Break");
            }
            
            java.util.Date fecha = (java.util.Date) eventoView.getSpinnerFecha().getValue();
            LocalDate fechaEvento = new java.sql.Date(fecha.getTime()).toLocalDate();
            
            BigDecimal costo = new BigDecimal(costoStr);
            
            Evento evento = new Evento(nombre, tipo, modalidad, servicios.toString(), fechaEvento, costo, "ACTIVO");
            
            if (eventoDAO.insertar(evento)) {
                eventoView.mostrarMensaje("Evento guardado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                eventoView.limpiarCampos();
                cargarEventos();
            } else {
                eventoView.mostrarMensaje("Error al guardar el evento", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            eventoView.mostrarMensaje("El costo debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            eventoView.mostrarMensaje("Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void modificarEvento() {
        int fila = eventoView.obtenerFilaSeleccionada();
        if (fila == -1) {
            eventoView.mostrarMensaje("Seleccione un evento de la tabla para modificar", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try {
            int id = (int) eventoView.getModeloTabla().getValueAt(fila, 0);
            String nombre = eventoView.getTxtNombreEvento().getText();
            String costoStr = eventoView.getTxtCosto().getText();
            
            if (nombre.isEmpty() || costoStr.isEmpty()) {
                eventoView.mostrarMensaje("Por favor, complete el nombre y el costo", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            String tipo = (String) eventoView.getCboTipoEvento().getSelectedItem();
            String modalidad = eventoView.getRbPresencial().isSelected() ? "Presencial" : "Virtual";
            
            StringBuilder servicios = new StringBuilder();
            if (eventoView.getChkCertificado().isSelected()) {
                servicios.append("Certificado");
            }
            if (eventoView.getChkMaterialDigital().isSelected()) {
                if (servicios.length() > 0) servicios.append(",");
                servicios.append("Material Digital");
            }
            if (eventoView.getChkCoffeeBreak().isSelected()) {
                if (servicios.length() > 0) servicios.append(",");
                servicios.append("Coffee Break");
            }
            
            java.util.Date fecha = (java.util.Date) eventoView.getSpinnerFecha().getValue();
            LocalDate fechaEvento = new java.sql.Date(fecha.getTime()).toLocalDate();
            
            BigDecimal costo = new BigDecimal(costoStr);
            
            Evento evento = new Evento(id, nombre, tipo, modalidad, servicios.toString(), fechaEvento, costo, "ACTIVO");
            
            if (eventoDAO.actualizar(evento)) {
                eventoView.mostrarMensaje("Evento modificado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                eventoView.limpiarCampos();
                cargarEventos();
            } else {
                eventoView.mostrarMensaje("Error al modificar el evento", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            eventoView.mostrarMensaje("El costo debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            eventoView.mostrarMensaje("Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void eliminarEvento() {
        int fila = eventoView.obtenerFilaSeleccionada();
        if (fila == -1) {
            eventoView.mostrarMensaje("Seleccione un evento de la tabla para eliminar", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int confirmacion = JOptionPane.showConfirmDialog(eventoView, 
                "¿Está seguro de eliminar este evento?", 
                "Confirmar Eliminación", 
                JOptionPane.YES_NO_OPTION);
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            int id = (int) eventoView.getModeloTabla().getValueAt(fila, 0);
            
            if (eventoDAO.eliminarLogico(id)) {
                eventoView.mostrarMensaje("Evento eliminado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                eventoView.limpiarCampos();
                cargarEventos();
            } else {
                eventoView.mostrarMensaje("Error al eliminar el evento", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void buscarEvento() {
        String nombre = eventoView.getTxtBuscar().getText();
        if (nombre.isEmpty()) {
            cargarEventos();
            return;
        }
        
        List<Evento> eventos = eventoDAO.buscarPorNombre(nombre);
        actualizarTabla(eventos);
    }
    
    private void cargarEventos() {
        List<Evento> eventos = eventoDAO.listarTodos();
        actualizarTabla(eventos);
    }
    
    private void actualizarTabla(List<Evento> eventos) {
        DefaultTableModel modelo = eventoView.getModeloTabla();
        modelo.setRowCount(0);
        
        for (Evento evento : eventos) {
            Object[] fila = {
                evento.getId(),
                evento.getNombreEvento(),
                evento.getTipoEvento(),
                evento.getModalidad(),
                evento.getServicios(),
                evento.getFechaEvento(),
                evento.getCosto(),
                evento.getEstado()
            };
            modelo.addRow(fila);
        }
    }
    
    private void volverDashboard() {
        eventoView.dispose();
        dashboardView.setVisible(true);
    }
    
    private void generarReporte() {
        List<Evento> eventos = eventoDAO.listarTodos();
        
        if (eventos.isEmpty()) {
            eventoView.mostrarMensaje("No hay eventos para generar el reporte", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int opcion = JOptionPane.showConfirmDialog(eventoView, 
                "¿Desea exportar el reporte a PDF?", 
                "Generar Reporte", 
                JOptionPane.YES_NO_OPTION);
        
        if (opcion == JOptionPane.YES_OPTION) {
            ReporteUtil.generarReportePDF(eventos, "Listado de Eventos");
        } else {
            ReporteUtil.verReporte(eventos, "Listado de Eventos");
        }
    }
}
