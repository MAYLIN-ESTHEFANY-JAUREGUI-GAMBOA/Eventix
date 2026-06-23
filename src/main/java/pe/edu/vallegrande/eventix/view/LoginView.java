package pe.edu.vallegrande.eventix.view;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LoginView extends JFrame {
    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JButton btnSalir;
    
    public LoginView() {
        setTitle("🦁 Eventix - Sistema de Gestión de Eventos");
        setSize(1100, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        initComponents();
    }
    
    private void initComponents() {
        // Panel principal con gradiente
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                int w = getWidth();
                int h = getHeight();
                Color color1 = new Color(255, 183, 77);  // Naranja suave
                Color color2 = new Color(129, 212, 250); // Azul cielo
                GradientPaint gp = new GradientPaint(0, 0, color1, w, h, color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);
            }
        };
        mainPanel.setLayout(new BorderLayout());
        
        // Panel central con tarjeta
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new GridBagLayout());
        cardPanel.setBackground(new Color(255, 255, 255, 230));
        cardPanel.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(255, 152, 0), 3, true),
                new EmptyBorder(30, 40, 30, 40)
        ));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Logo/Emoji
        JLabel lblEmoji = new JLabel("🎪");
        lblEmoji.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 48));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        cardPanel.add(lblEmoji, gbc);
        
        // Título
        JLabel lblTitulo = new JLabel("Eventix");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitulo.setForeground(new Color(255, 87, 34));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        cardPanel.add(lblTitulo, gbc);
        
        JLabel lblSubtitulo = new JLabel("Sistema de Gestión de Eventos");
        lblSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblSubtitulo.setForeground(new Color(117, 117, 117));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        cardPanel.add(lblSubtitulo, gbc);
        
        // Separador
        JSeparator separator = new JSeparator();
        separator.setForeground(new Color(255, 152, 0));
        separator.setBackground(new Color(255, 152, 0));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(15, 10, 15, 10);
        cardPanel.add(separator, gbc);
        
        // Usuario
        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblUsuario.setForeground(new Color(84, 110, 122));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(5, 5, 5, 5);
        cardPanel.add(lblUsuario, gbc);
        
        txtUsuario = new JTextField(15);
        txtUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtUsuario.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(255, 152, 0), 2),
                new EmptyBorder(8, 12, 8, 12)
        ));
        txtUsuario.setBackground(new Color(255, 248, 225));
        gbc.gridx = 1;
        gbc.gridy = 3;
        cardPanel.add(txtUsuario, gbc);
        
        // Contraseña
        JLabel lblPassword = new JLabel("Contraseña:");
        lblPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblPassword.setForeground(new Color(84, 110, 122));
        gbc.gridx = 0;
        gbc.gridy = 4;
        cardPanel.add(lblPassword, gbc);
        
        txtPassword = new JPasswordField(15);
        txtPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtPassword.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(255, 152, 0), 2),
                new EmptyBorder(8, 12, 8, 12)
        ));
        txtPassword.setBackground(new Color(255, 248, 225));
        gbc.gridx = 1;
        gbc.gridy = 4;
        cardPanel.add(txtPassword, gbc);
        
        // Panel de botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        buttonPanel.setBackground(new Color(255, 255, 255, 230));
        
        btnLogin = new JButton("Ingresar");
        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnLogin.setPreferredSize(new Dimension(120, 40));
        btnLogin.setBackground(new Color(255, 87, 34));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setBorderPainted(false);
        btnLogin.setFocusPainted(false);
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttonPanel.add(btnLogin);
        
        btnSalir = new JButton("Salir");
        btnSalir.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnSalir.setPreferredSize(new Dimension(120, 40));
        btnSalir.setBackground(new Color(158, 158, 158));
        btnSalir.setForeground(Color.WHITE);
        btnSalir.setBorderPainted(false);
        btnSalir.setFocusPainted(false);
        btnSalir.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttonPanel.add(btnSalir);
        
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 5, 5, 5);
        cardPanel.add(buttonPanel, gbc);
        
        // Footer
        JLabel lblFooter = new JLabel("Bienvenido al sistema");
        lblFooter.setFont(new Font("Segoe UI", Font.ITALIC, 11));
        lblFooter.setForeground(new Color(255, 112, 67));
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.insets = new Insets(5, 5, 5, 5);
        cardPanel.add(lblFooter, gbc);
        
        // Agregar tarjeta al panel principal
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centerPanel.setBackground(new Color(0, 0, 0, 0));
        centerPanel.add(cardPanel);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        
        add(mainPanel);
    }
    
    public JTextField getTxtUsuario() {
        return txtUsuario;
    }
    
    public JPasswordField getTxtPassword() {
        return txtPassword;
    }
    
    public JButton getBtnLogin() {
        return btnLogin;
    }
    
    public JButton getBtnSalir() {
        return btnSalir;
    }
    
    public void mostrarMensaje(String mensaje, String titulo, int tipo) {
        JOptionPane.showMessageDialog(this, mensaje, titulo, tipo);
    }
    
    public void limpiarCampos() {
        txtUsuario.setText("");
        txtPassword.setText("");
    }
}
