package pe.edu.vallegrande.eventix;

import pe.edu.vallegrande.eventix.controller.LoginController;
import pe.edu.vallegrande.eventix.view.LoginView;

import javax.swing.*;

public class EventixApplication {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
                LoginView loginView = new LoginView();
                loginView.setVisible(true);
                
                new LoginController(loginView);
            }
        });
    }
}
