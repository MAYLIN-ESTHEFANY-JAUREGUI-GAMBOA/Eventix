package pe.edu.vallegrande.eventix.controller;

import pe.edu.vallegrande.eventix.view.DashboardView;
import pe.edu.vallegrande.eventix.view.LoginView;
import pe.edu.vallegrande.eventix.view.EventoView;
import pe.edu.vallegrande.eventix.view.ReportesView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DashboardController {
    private DashboardView dashboardView;
    private LoginView loginView;
    private EventoView eventoView;
    private ReportesView reportesView;
    
    public DashboardController(DashboardView dashboardView, LoginView loginView) {
        this.dashboardView = dashboardView;
        this.loginView = loginView;
        
        initController();
    }
    
    private void initController() {
        dashboardView.getBtnGestionEventos().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirGestionEventos();
            }
        });
        
        dashboardView.getBtnReportes().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirReportes();
            }
        });
        
        dashboardView.getBtnCerrarSesion().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cerrarSesion();
            }
        });
    }
    
    private void abrirGestionEventos() {
        dashboardView.dispose();
        eventoView = new EventoView();
        eventoView.setVisible(true);
        
        new EventoController(eventoView, dashboardView);
    }
    
    private void abrirReportes() {
        reportesView = new ReportesView();
        reportesView.setVisible(true);
        
        new ReportesController(reportesView);
    }
    
    private void cerrarSesion() {
        dashboardView.dispose();
        loginView.setVisible(true);
        loginView.limpiarCampos();
    }
}
