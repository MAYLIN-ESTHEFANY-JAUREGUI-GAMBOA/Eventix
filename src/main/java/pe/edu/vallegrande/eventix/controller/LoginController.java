package pe.edu.vallegrande.eventix.controller;

import pe.edu.vallegrande.eventix.dao.UsuarioDAO;
import pe.edu.vallegrande.eventix.view.LoginView;
import pe.edu.vallegrande.eventix.view.DashboardView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LoginController {
    private LoginView loginView;
    private UsuarioDAO usuarioDAO;
    private DashboardView dashboardView;
    
    public LoginController(LoginView loginView) {
        this.loginView = loginView;
        this.usuarioDAO = new UsuarioDAO();
        
        initController();
    }
    
    private void initController() {
        loginView.getBtnLogin().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
        
        loginView.getBtnSalir().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        loginView.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
    
    private void login() {
        String usuario = loginView.getTxtUsuario().getText();
        String contraseña = new String(loginView.getTxtPassword().getPassword());
        
        if (usuario.isEmpty() || contraseña.isEmpty()) {
            loginView.mostrarMensaje("Por favor, complete todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (usuarioDAO.autenticar(usuario, contraseña)) {
            loginView.mostrarMensaje("¡Bienvenido! Acceso concedido", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            loginView.dispose();
            dashboardView = new DashboardView(usuario);
            dashboardView.setVisible(true);
            
            new DashboardController(dashboardView, loginView);
        } else {
            loginView.mostrarMensaje("Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
            loginView.limpiarCampos();
        }
    }
}
