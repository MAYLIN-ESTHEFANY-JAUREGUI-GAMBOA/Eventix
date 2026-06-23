package pe.edu.vallegrande.eventix.view;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class EventoView extends JFrame {
    private JTextField txtNombreEvento;
    private JTextField txtCosto;
    private JComboBox<String> cboTipoEvento;
    private JRadioButton rbPresencial;
    private JRadioButton rbVirtual;
    private JCheckBox chkCertificado;
    private JCheckBox chkMaterialDigital;
    private JCheckBox chkCoffeeBreak;
    private JTable tablaEventos;
    private DefaultTableModel modeloTabla;
    private JButton btnNuevo;
    private JButton btnGuardar;
    private JButton btnModificar;
    private JButton btnEliminar;
    private JButton btnBuscar;
    private JButton btnLimpiar;
    private JButton btnReporte;
    private JButton btnVolver;
    private JTextField txtBuscar;
    private JSpinner spinnerFecha;
    
    public EventoView() {
        setTitle("🦁 Eventix - Gestión de Eventos");
        setSize(1100, 750);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
        mainPanel.setLayout(new BorderLayout(15, 15));
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        
        // Header
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(255, 255, 255, 240));
        headerPanel.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(255, 152, 0), 2),
                new EmptyBorder(15, 25, 15, 25)
        ));
        
        JPanel headerContent = new JPanel(new GridLayout(2, 1, 5, 5));
        headerContent.setBackground(new Color(255, 255, 255, 240));
        
        // Primera fila: emoji y título
        JPanel row1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        row1.setBackground(new Color(255, 255, 255, 240));
        
        JLabel lblEmoji = new JLabel("📅");
        lblEmoji.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 35));
        row1.add(lblEmoji);
        
        JLabel lblTitulo = new JLabel("Gestión de Eventos");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitulo.setForeground(new Color(255, 87, 34));
        row1.add(lblTitulo);
        
        headerContent.add(row1);
        
        // Segunda fila: subtítulo
        JPanel row2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        row2.setBackground(new Color(255, 255, 255, 240));
        
        JLabel lblSubtitulo = new JLabel("Sistema de Gestión de Eventos");
        lblSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblSubtitulo.setForeground(new Color(117, 117, 117));
        row2.add(lblSubtitulo);
        
        headerContent.add(row2);
        
        headerPanel.add(headerContent, BorderLayout.CENTER);
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        
        // Panel central dividido: Formulario arriba, Tabla abajo
        JPanel centerPanel = new JPanel(new BorderLayout(15, 15));
        centerPanel.setBackground(new Color(0, 0, 0, 0));
        
        // Panel de formulario (parte superior)
        JPanel panelFormulario = new JPanel(new BorderLayout(15, 15));
        panelFormulario.setBackground(new Color(255, 255, 255, 240));
        panelFormulario.setBorder(new EmptyBorder(15, 15, 15, 15));
        
        // Panel de campos con tarjeta
        JPanel panelCampos = new JPanel(new GridBagLayout());
        panelCampos.setBackground(new Color(255, 255, 255, 240));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Nombre del evento
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblNombre.setForeground(new Color(84, 110, 122));
        panelCampos.add(lblNombre, gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 3;
        txtNombreEvento = new JTextField(30);
        txtNombreEvento.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        txtNombreEvento.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(255, 152, 0), 2, false),
                new EmptyBorder(5, 10, 5, 10)
        ));
        txtNombreEvento.setBackground(new Color(255, 248, 225));
        panelCampos.add(txtNombreEvento, gbc);
        
        // Tipo de evento
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        JLabel lblTipo = new JLabel("Tipo:");
        lblTipo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblTipo.setForeground(new Color(84, 110, 122));
        panelCampos.add(lblTipo, gbc);
        gbc.gridx = 1;
        cboTipoEvento = new JComboBox<>(new String[]{"Taller", "Seminario", "Conferencia", "Webinar"});
        cboTipoEvento.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        panelCampos.add(cboTipoEvento, gbc);
        
        // Modalidad
        gbc.gridx = 2;
        JLabel lblModalidad = new JLabel("Modalidad:");
        lblModalidad.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblModalidad.setForeground(new Color(84, 110, 122));
        panelCampos.add(lblModalidad, gbc);
        gbc.gridx = 3;
        JPanel panelModalidad = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        panelModalidad.setBackground(new Color(255, 255, 255, 240));
        ButtonGroup grupoModalidad = new ButtonGroup();
        rbPresencial = new JRadioButton("Presencial");
        rbVirtual = new JRadioButton("Virtual");
        rbPresencial.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        rbVirtual.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        rbPresencial.setSelected(true);
        grupoModalidad.add(rbPresencial);
        grupoModalidad.add(rbVirtual);
        panelModalidad.add(rbPresencial);
        panelModalidad.add(rbVirtual);
        panelCampos.add(panelModalidad, gbc);
        
        // Servicios
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel lblServicios = new JLabel("Servicios:");
        lblServicios.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblServicios.setForeground(new Color(84, 110, 122));
        panelCampos.add(lblServicios, gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 3;
        JPanel panelServicios = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        panelServicios.setBackground(new Color(255, 255, 255, 240));
        chkCertificado = new JCheckBox("Certificado");
        chkMaterialDigital = new JCheckBox("Material");
        chkCoffeeBreak = new JCheckBox("Coffee");
        chkCertificado.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        chkMaterialDigital.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        chkCoffeeBreak.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        panelServicios.add(chkCertificado);
        panelServicios.add(chkMaterialDigital);
        panelServicios.add(chkCoffeeBreak);
        panelCampos.add(panelServicios, gbc);
        
        // Costo
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        JLabel lblCosto = new JLabel("Costo:");
        lblCosto.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblCosto.setForeground(new Color(84, 110, 122));
        panelCampos.add(lblCosto, gbc);
        gbc.gridx = 1;
        txtCosto = new JTextField(15);
        txtCosto.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        txtCosto.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(255, 152, 0), 2, false),
                new EmptyBorder(5, 10, 5, 10)
        ));
        txtCosto.setBackground(new Color(255, 248, 225));
        panelCampos.add(txtCosto, gbc);
        
        // Fecha del evento
        gbc.gridx = 2;
        JLabel lblFecha = new JLabel("Fecha:");
        lblFecha.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblFecha.setForeground(new Color(84, 110, 122));
        panelCampos.add(lblFecha, gbc);
        gbc.gridx = 3;
        SpinnerDateModel dateModel = new SpinnerDateModel();
        spinnerFecha = new JSpinner(dateModel);
        spinnerFecha.setEditor(new JSpinner.DateEditor(spinnerFecha, "yyyy-MM-dd"));
        spinnerFecha.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        panelCampos.add(spinnerFecha, gbc);
        
        panelFormulario.add(panelCampos, BorderLayout.CENTER);
        
        // Panel de botones de operaciones (lado derecho del formulario)
        JPanel panelBotones = new JPanel(new GridLayout(5, 1, 5, 5));
        panelBotones.setBackground(new Color(255, 255, 255, 240));
        panelBotones.setBorder(new EmptyBorder(0, 10, 0, 0));
        
        btnNuevo = createStyledButton("Nuevo", new Color(76, 175, 80));
        btnGuardar = createStyledButton("Guardar", new Color(33, 150, 243));
        btnModificar = createStyledButton("Modificar", new Color(255, 152, 0));
        btnEliminar = createStyledButton("Eliminar", new Color(244, 67, 54));
        btnLimpiar = createStyledButton("Limpiar", new Color(158, 158, 158));
        
        panelBotones.add(btnNuevo);
        panelBotones.add(btnGuardar);
        panelBotones.add(btnModificar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnLimpiar);
        
        panelFormulario.add(panelBotones, BorderLayout.EAST);
        
        centerPanel.add(panelFormulario, BorderLayout.NORTH);
        
        // Panel de estadísticas (entre formulario y tabla)
        JPanel statsPanel = new JPanel(new GridLayout(1, 3, 15, 0));
        statsPanel.setBackground(new Color(0, 0, 0, 0));
        statsPanel.setBorder(new EmptyBorder(10, 0, 10, 0));
        
        JPanel stat1 = createStatCard("🎯", "Eventos Activos", "4");
        JPanel stat2 = createStatCard("⭐", "Eventos Totales", "4");
        JPanel stat3 = createStatCard("🔥", "Popularidad", "Alta");
        
        statsPanel.add(stat1);
        statsPanel.add(stat2);
        statsPanel.add(stat3);
        
        centerPanel.add(statsPanel, BorderLayout.CENTER);
        
        // Panel de listado (parte inferior)
        JPanel panelListado = new JPanel(new BorderLayout(10, 10));
        panelListado.setBackground(new Color(255, 255, 255, 240));
        panelListado.setBorder(new EmptyBorder(15, 15, 15, 15));
        
        // Panel de búsqueda
        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        panelBusqueda.setBackground(new Color(255, 255, 255, 240));
        
        JLabel lblBuscar = new JLabel("Buscar:");
        lblBuscar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblBuscar.setForeground(new Color(84, 110, 122));
        panelBusqueda.add(lblBuscar);
        
        txtBuscar = new JTextField(25);
        txtBuscar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        txtBuscar.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(255, 152, 0), 2, false),
                new EmptyBorder(5, 10, 5, 10)
        ));
        txtBuscar.setBackground(new Color(255, 248, 225));
        panelBusqueda.add(txtBuscar);
        
        btnBuscar = createStyledButton("Buscar", new Color(76, 175, 80));
        panelBusqueda.add(btnBuscar);
        
        panelListado.add(panelBusqueda, BorderLayout.NORTH);
        
        // Tabla de eventos
        String[] columnas = {"ID", "NOMBRE", "TIPO", "MODALIDAD", "SERVICIOS", "FECHA", "COSTO", "ESTADO"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaEventos = new JTable(modeloTabla);
        tablaEventos.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        tablaEventos.setRowHeight(25);
        tablaEventos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaEventos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 11));
        tablaEventos.getTableHeader().setBackground(new Color(255, 152, 0));
        tablaEventos.getTableHeader().setForeground(Color.BLACK);
        tablaEventos.getTableHeader().setReorderingAllowed(false);
        
        JScrollPane scrollPane = new JScrollPane(tablaEventos);
        scrollPane.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(255, 152, 0), 2, false),
                new EmptyBorder(5, 5, 5, 5)
        ));
        panelListado.add(scrollPane, BorderLayout.CENTER);
        
        // Panel de botones inferiores
        JPanel panelBotonesInferiores = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panelBotonesInferiores.setBackground(new Color(255, 255, 255, 240));
        
        btnReporte = createStyledButton("Generar Reporte", new Color(156, 39, 176));
        btnVolver = createStyledButton("Volver al Dashboard", new Color(96, 125, 139));
        
        panelBotonesInferiores.add(btnReporte);
        panelBotonesInferiores.add(btnVolver);
        
        panelListado.add(panelBotonesInferiores, BorderLayout.SOUTH);
        
        centerPanel.add(panelListado, BorderLayout.CENTER);
        
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        
        add(mainPanel);
    }
    
    private JButton createStyledButton(String text, Color color) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setPreferredSize(new Dimension(140, 40));
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }
    
    private JPanel createStatCard(String emoji, String title, String value) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(new Color(255, 255, 255, 240));
        card.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(255, 152, 0), 2, true),
                new EmptyBorder(15, 15, 15, 15)
        ));
        card.setPreferredSize(new Dimension(150, 80));
        
        JLabel lblEmoji = new JLabel(emoji);
        lblEmoji.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 40));
        card.add(lblEmoji, BorderLayout.WEST);
        
        JPanel rightPanel = new JPanel(new GridLayout(2, 1, 0, 0));
        rightPanel.setBackground(new Color(255, 255, 255, 240));
        
        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblTitle.setForeground(new Color(117, 117, 117));
        rightPanel.add(lblTitle);
        
        JLabel lblValue = new JLabel(value);
        lblValue.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblValue.setForeground(new Color(255, 87, 34));
        rightPanel.add(lblValue);
        
        card.add(rightPanel, BorderLayout.CENTER);
        
        return card;
    }
    
    // Getters
    public JTextField getTxtNombreEvento() {
        return txtNombreEvento;
    }
    
    public JTextField getTxtCosto() {
        return txtCosto;
    }
    
    public JComboBox<String> getCboTipoEvento() {
        return cboTipoEvento;
    }
    
    public JRadioButton getRbPresencial() {
        return rbPresencial;
    }
    
    public JRadioButton getRbVirtual() {
        return rbVirtual;
    }
    
    public JCheckBox getChkCertificado() {
        return chkCertificado;
    }
    
    public JCheckBox getChkMaterialDigital() {
        return chkMaterialDigital;
    }
    
    public JCheckBox getChkCoffeeBreak() {
        return chkCoffeeBreak;
    }
    
    public JTable getTablaEventos() {
        return tablaEventos;
    }
    
    public DefaultTableModel getModeloTabla() {
        return modeloTabla;
    }
    
    public JButton getBtnNuevo() {
        return btnNuevo;
    }
    
    public JButton getBtnGuardar() {
        return btnGuardar;
    }
    
    public JButton getBtnModificar() {
        return btnModificar;
    }
    
    public JButton getBtnEliminar() {
        return btnEliminar;
    }
    
    public JButton getBtnBuscar() {
        return btnBuscar;
    }
    
    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }
    
    public JButton getBtnReporte() {
        return btnReporte;
    }
    
    public JButton getBtnVolver() {
        return btnVolver;
    }
    
    public JTextField getTxtBuscar() {
        return txtBuscar;
    }
    
    public JSpinner getSpinnerFecha() {
        return spinnerFecha;
    }
    
    public void mostrarMensaje(String mensaje, String titulo, int tipo) {
        JOptionPane.showMessageDialog(this, mensaje, titulo, tipo);
    }
    
    public void limpiarCampos() {
        txtNombreEvento.setText("");
        txtCosto.setText("");
        cboTipoEvento.setSelectedIndex(0);
        rbPresencial.setSelected(true);
        chkCertificado.setSelected(false);
        chkMaterialDigital.setSelected(false);
        chkCoffeeBreak.setSelected(false);
    }
    
    public int obtenerFilaSeleccionada() {
        return tablaEventos.getSelectedRow();
    }
}
