package pe.edu.vallegrande.eventix.view;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DashboardView extends JFrame {

    private JButton btnGestionEventos;
    private JButton btnReportes;
    private JButton btnCerrarSesion;
    private JLabel lblBienvenido;

    public DashboardView(String usuario) {
        setTitle("🎉 Eventix - Dashboard");
        setSize(1100, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        initComponents(usuario);
    }

    private void initComponents(String usuario) {

        // Panel principal con gradiente
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(
                        RenderingHints.KEY_RENDERING,
                        RenderingHints.VALUE_RENDER_QUALITY);

                int w = getWidth();
                int h = getHeight();

                Color color1 = new Color(129, 212, 250);
                Color color2 = new Color(255, 183, 77);

                GradientPaint gp = new GradientPaint(
                        0, 0, color1,
                        w, h, color2);

                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);
            }
        };

        mainPanel.setLayout(new BorderLayout(20, 20));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // ==================================================
        // HEADER
        // ==================================================

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(255, 255, 255, 230));

        headerPanel.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(255, 152, 0), 2, true),
                new EmptyBorder(20, 30, 20, 30)));

        JPanel welcomePanel = new JPanel(
                new FlowLayout(FlowLayout.LEFT, 20, 5));

        welcomePanel.setBackground(new Color(255, 255, 255, 230));

        JLabel lblEmoji = new JLabel("🎉");
        lblEmoji.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 65));
        welcomePanel.add(lblEmoji);

        lblBienvenido = new JLabel("¡Hola, " + usuario + "!");
        lblBienvenido.setFont(new Font("Segoe UI", Font.BOLD, 32));
        lblBienvenido.setForeground(new Color(255, 87, 34));
        welcomePanel.add(lblBienvenido);

        headerPanel.add(welcomePanel, BorderLayout.WEST);

        JLabel lblSubtitulo =
                new JLabel("Bienvenido al Sistema de Gestión de Eventos");

        lblSubtitulo.setFont(new Font("Segoe UI", Font.ITALIC, 20));
        lblSubtitulo.setForeground(new Color(117, 117, 117));
        lblSubtitulo.setHorizontalAlignment(SwingConstants.CENTER);

        headerPanel.add(lblSubtitulo, BorderLayout.CENTER);

        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // ==================================================
        // CENTRO
        // ==================================================

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);

        // Título

        JLabel lblTitulo = new JLabel("Panel de Control");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 36));
        lblTitulo.setForeground(new Color(255, 87, 34));

        gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(lblTitulo, gbc);

        // Tarjetas

        JPanel cardsPanel = new JPanel(new GridLayout(1, 3, 30, 0));
        cardsPanel.setOpaque(false);
        cardsPanel.setPreferredSize(new Dimension(850, 280));

        JPanel card1 = createCard(
                "📅",
                "Gestión de Eventos",
                "Administra tus eventos",
                new Color(76, 175, 80));

        btnGestionEventos = createCardButton(card1);
        cardsPanel.add(card1);

        JPanel card2 = createCard(
                "📊",
                "Reportes",
                "Genera reportes PDF",
                new Color(33, 150, 243));

        btnReportes = createCardButton(card2);
        cardsPanel.add(card2);

        JPanel card3 = createCard(
                "🚪",
                "Cerrar Sesión",
                "Salir del sistema",
                new Color(244, 67, 54));

        btnCerrarSesion = createCardButton(card3);
        cardsPanel.add(card3);

        gbc.gridy = 1;
        centerPanel.add(cardsPanel, gbc);

        // IMPORTANTE
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // ==================================================
        // FOOTER
        // ==================================================

        JPanel footerPanel = new JPanel(
                new FlowLayout(FlowLayout.CENTER));

        footerPanel.setBackground(new Color(255, 255, 255, 200));

        JLabel lblFooter =
                new JLabel("Eventix - Sistema de Gestión de Eventos © 2026");

        lblFooter.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblFooter.setForeground(new Color(84, 110, 122));

        footerPanel.add(lblFooter);

        mainPanel.add(footerPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private JPanel createCard(
            String emoji,
            String title,
            String subtitle,
            Color color) {

        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(new Color(255, 255, 255, 240));

        card.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(color, 3, true),
                new EmptyBorder(20, 20, 20, 20)));

        card.setPreferredSize(new Dimension(250, 250));

        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.setBackground(new Color(255, 255, 255, 240));

        JLabel lblEmoji = new JLabel(emoji);
        lblEmoji.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 60));
        topPanel.add(lblEmoji);

        card.add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(2, 1));
        centerPanel.setBackground(new Color(255, 255, 255, 240));

        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel lblSubtitle = new JLabel(subtitle);
        lblSubtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblSubtitle.setForeground(Color.GRAY);
        lblSubtitle.setHorizontalAlignment(SwingConstants.CENTER);

        centerPanel.add(lblTitle);
        centerPanel.add(lblSubtitle);

        card.add(centerPanel, BorderLayout.CENTER);

        JButton btn = new JButton("Acceder");
        btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(255, 255, 255, 240));
        bottomPanel.add(btn);

        card.add(bottomPanel, BorderLayout.SOUTH);

        return card;
    }

    private JButton createCardButton(JPanel card) {
        return findButton(card);
    }

    private JButton findButton(Container container) {

        for (Component comp : container.getComponents()) {

            if (comp instanceof JButton) {
                return (JButton) comp;
            }

            if (comp instanceof Container) {
                JButton found = findButton((Container) comp);

                if (found != null) {
                    return found;
                }
            }
        }

        return null;
    }

    public JButton getBtnGestionEventos() {
        return btnGestionEventos;
    }

    public JButton getBtnReportes() {
        return btnReportes;
    }

    public JButton getBtnCerrarSesion() {
        return btnCerrarSesion;
    }

    public JLabel getLblBienvenido() {
        return lblBienvenido;
    }

    public void mostrarMensaje(String mensaje, String titulo, int tipo) {
        JOptionPane.showMessageDialog(this, mensaje, titulo, tipo);
    }
}