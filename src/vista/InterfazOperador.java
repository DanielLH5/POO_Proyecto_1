package vista;

import logica.PerfilOperativo;

import javax.swing.*;
import java.awt.*;

public class InterfazOperador extends JFrame {
    private PerfilOperativo perfilOperativo;
    private JPanel panelPrincipal;
    private CardLayout cardLayout;

    public InterfazOperador(PerfilOperativo perfilOperativo) {
        this.perfilOperativo = perfilOperativo;
        configurarVentana();
        inicializarComponentes();
    }

    private void configurarVentana() {
        setTitle("Perfil Operador");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);
        setResizable(true);
    }

    private void inicializarComponentes() {
        // Configurar CardLayout para cambiar entre paneles
        cardLayout = new CardLayout();
        panelPrincipal = new JPanel(cardLayout);

        // Crear los diferentes paneles
        JPanel panelMenu = crearPanelMenu();
        JPanel panelGestionCiudadanos = crearPanelGestionCiudadanos();
        JPanel panelGestionRobots = crearPanelGestionRobots();
        JPanel panelGestionDrones = crearPanelGestionDrones();
        JPanel panelSimulacion = crearPanelSimulacion();
        JPanel panelDashboard = crearPanelDashboard();

        // Agregar paneles al CardLayout
        panelPrincipal.add(panelMenu, "Menu");
        panelPrincipal.add(panelGestionCiudadanos, "GestionCiudadanos");
        panelPrincipal.add(panelGestionRobots, "GestionRobots");
        panelPrincipal.add(panelGestionDrones, "GestionDrones");
        panelPrincipal.add(panelSimulacion, "Simulacion");
        panelPrincipal.add(panelDashboard, "Dashboard");

        // Mostrar el menú principal primero
        cardLayout.show(panelPrincipal, "Menu");

        add(panelPrincipal);
    }

    private JPanel crearPanelMenu() {
        JPanel panel = new JPanel(new GridLayout(6, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(40, 50, 40, 50));
        panel.setBackground(new Color(240, 248, 255));

        JLabel titulo = new JLabel("PERFIL OPERADOR - NEO-URBE", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setForeground(new Color(25, 25, 112));

        // Botones con íconos y colores
        JButton btnGestionCiudadanos = crearBotonMenu("Gestión de Ciudadanos", new Color(70, 130, 180));
        JButton btnGestionRobots = crearBotonMenu("Gestión de Robots", new Color(60, 179, 113));
        JButton btnGestionDrones = crearBotonMenu("Gestión de Drones", new Color(255, 165, 0));
        JButton btnSimulacion = crearBotonMenu("Ejecutar Simulación", new Color(220, 20, 60));
        JButton btnDashboard = crearBotonMenu("Dashboard del Sistema", new Color(75, 0, 130));

        // Acciones de los botones
        btnGestionCiudadanos.addActionListener(e -> cardLayout.show(panelPrincipal, "GestionCiudadanos"));
        btnGestionRobots.addActionListener(e -> cardLayout.show(panelPrincipal, "GestionRobots"));
        btnGestionDrones.addActionListener(e -> cardLayout.show(panelPrincipal, "GestionDrones"));
        btnSimulacion.addActionListener(e -> cardLayout.show(panelPrincipal, "Simulacion"));
        btnDashboard.addActionListener(e -> {
            cardLayout.show(panelPrincipal, "Dashboard");
        });

        panel.add(titulo);
        panel.add(btnGestionCiudadanos);
        panel.add(btnGestionRobots);
        panel.add(btnGestionDrones);
        panel.add(btnSimulacion);
        panel.add(btnDashboard);

        return panel;
    }

    private JButton crearBotonMenu(String texto, Color color) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Arial", Font.BOLD, 14));
        boton.setBackground(color);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        return boton;
    }

    private JPanel crearPanelGestionCiudadanos() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Panel de pestañas para diferentes operaciones
        JTabbedPane tabbedPane = new JTabbedPane();

        // Pestaña para crear ciudadano individual
        tabbedPane.addTab("Crear Ciudadano", crearPanelCrearCiudadano());

        // Pestaña para crear ciudadanos en bloque
        tabbedPane.addTab("Crear en Bloque", crearPanelCiudadanosBloque());

        // Pestaña para listar ciudadanos
        tabbedPane.addTab("Listar Ciudadanos", crearPanelListarCiudadanos());

        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout());
        JButton btnVolver = new JButton("Volver al Menú");
        btnVolver.addActionListener(e -> cardLayout.show(panelPrincipal, "Menu"));

        panelBotones.add(btnVolver);

        panel.add(new JLabel("GESTIÓN DE CIUDADANOS", SwingConstants.CENTER), BorderLayout.NORTH);
        panel.add(tabbedPane, BorderLayout.CENTER);
        panel.add(panelBotones, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel crearPanelCrearCiudadano() {
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField txtId = new JTextField();
        JTextField txtNombre = new JTextField();
        JComboBox<String> cmbEdificio = new JComboBox<>();
        JComboBox<String> cmbRobot = new JComboBox<>();

        // Llenar combobox con edificios disponibles (simulado)
        cmbEdificio.addItem("EDF001 - Edificio Central");
        cmbEdificio.addItem("EDF002 - Torre Norte");
        cmbEdificio.addItem("EDF003 - Residencia Este");

        // Llenar combobox con robots disponibles (simulado)
        cmbRobot.addItem("Ninguno");
        cmbRobot.addItem("ROB001 - Robot Asistente A");
        cmbRobot.addItem("ROB002 - Robot Asistente B");

        panel.add(new JLabel("ID único:"));
        panel.add(txtId);
        panel.add(new JLabel("Nombre:"));
        panel.add(txtNombre);
        panel.add(new JLabel("Edificio:"));
        panel.add(cmbEdificio);
        panel.add(new JLabel("Robot Asistente:"));
        panel.add(cmbRobot);

        JButton btnCrear = new JButton("Crear Ciudadano");
        btnCrear.addActionListener(e -> crearCiudadanoIndividual(txtId, txtNombre, cmbEdificio, cmbRobot));

        panel.add(new JLabel()); // Espacio vacío
        panel.add(btnCrear);

        return panel;
    }

    private JPanel crearPanelCiudadanosBloque() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        JLabel lblInfo = new JLabel("Cantidad de ciudadanos a generar:");
        JSpinner spinner = new JSpinner(new SpinnerNumberModel(5, 1, 50, 1));
        JButton btnGenerar = new JButton("Generar Ciudadanos en Bloque");
        btnGenerar.addActionListener(e -> {
            int cantidad = (int) spinner.getValue();
            generarCiudadanosBloque(cantidad);
        });

        panel.add(lblInfo);
        panel.add(spinner);
        panel.add(btnGenerar);

        return panel;
    }

    private JPanel crearPanelListarCiudadanos() {
        JPanel panel = new JPanel(new BorderLayout());

        JTextArea areaCiudadanos = new JTextArea();
        areaCiudadanos.setEditable(false);
        areaCiudadanos.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(areaCiudadanos);

        JButton btnActualizar = new JButton("Actualizar Lista");
        btnActualizar.addActionListener(e -> actualizarListaCiudadanos(areaCiudadanos));

        panel.add(new JLabel("LISTA DE CIUDADANOS", SwingConstants.CENTER), BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(btnActualizar, BorderLayout.SOUTH);

        // Cargar lista inicial
        actualizarListaCiudadanos(areaCiudadanos);

        return panel;
    }

    private JPanel crearPanelGestionRobots() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTabbedPane tabbedPane = new JTabbedPane();

        // Pestañas para gestión de robots
        tabbedPane.addTab("Crear Robot", crearPanelCrearRobot());
        tabbedPane.addTab("Gestionar Batería", crearPanelGestionBateria());
        tabbedPane.addTab("Estado Robots", crearPanelEstadoRobots());

        JPanel panelBotones = new JPanel(new FlowLayout());
        JButton btnVolver = new JButton("Volver al Menú");
        btnVolver.addActionListener(e -> cardLayout.show(panelPrincipal, "Menu"));

        panelBotones.add(btnVolver);

        panel.add(new JLabel("GESTIÓN DE ROBOTS ASISTENTES", SwingConstants.CENTER), BorderLayout.NORTH);
        panel.add(tabbedPane, BorderLayout.CENTER);
        panel.add(panelBotones, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel crearPanelCrearRobot() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField txtId = new JTextField();
        JSpinner spinnerBateria = new JSpinner(new SpinnerNumberModel(50, 20, 100, 5));
        JComboBox<String> cmbCiudadano = new JComboBox<>();

        // Datos simulados
        cmbCiudadano.addItem("Sin asignar");
        cmbCiudadano.addItem("CIU001 - Juan Pérez");
        cmbCiudadano.addItem("CIU002 - María García");

        panel.add(new JLabel("ID del Robot:"));
        panel.add(txtId);
        panel.add(new JLabel("Batería inicial (%):"));
        panel.add(spinnerBateria);
        panel.add(new JLabel("Asignar a Ciudadano:"));
        panel.add(cmbCiudadano);

        JButton btnCrearIndividual = new JButton("Crear Robot Individual");
        JButton btnCrearBloque = new JButton("Crear 5 Robots Automáticos");

        btnCrearIndividual.addActionListener(e -> crearRobotIndividual(txtId, spinnerBateria, cmbCiudadano));
        btnCrearBloque.addActionListener(e -> crearRobotsBloque());

        panel.add(btnCrearIndividual);
        panel.add(btnCrearBloque);

        return panel;
    }

    private JPanel crearPanelGestionDrones() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel panelContenido = new JPanel(new GridLayout(4, 1, 15, 15));

        JButton btnGenerarDrones = new JButton("Generar Drones Automáticamente");
        JButton btnEstadoDrones = new JButton("Ver Estado de Drones");
        JButton btnPatrullaje = new JButton("🛡Iniciar Patrullaje");
        JButton btnRecargarDrones = new JButton("Recargar Drones Críticos");

        // Acciones de los botones
        btnGenerarDrones.addActionListener(e -> generarDronesAutomaticos());
        btnEstadoDrones.addActionListener(e -> mostrarEstadoDrones());
        btnPatrullaje.addActionListener(e -> iniciarPatrullaje());
        btnRecargarDrones.addActionListener(e -> recargarDronesCriticos());

        panelContenido.add(btnGenerarDrones);
        panelContenido.add(btnEstadoDrones);
        panelContenido.add(btnPatrullaje);
        panelContenido.add(btnRecargarDrones);

        JPanel panelBotones = new JPanel(new FlowLayout());
        JButton btnVolver = new JButton("Volver al Menú");
        btnVolver.addActionListener(e -> cardLayout.show(panelPrincipal, "Menu"));

        panelBotones.add(btnVolver);

        panel.add(new JLabel("GESTIÓN DE DRONES DE VIGILANCIA", SwingConstants.CENTER), BorderLayout.NORTH);
        panel.add(panelContenido, BorderLayout.CENTER);
        panel.add(panelBotones, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel crearPanelSimulacion() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        JPanel panelContenido = new JPanel(new GridLayout(3, 1, 20, 20));

        JLabel lblTitulo = new JLabel("SIMULACIÓN DEL SISTEMA", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));

        JTextArea areaLog = new JTextArea(10, 50);
        areaLog.setEditable(false);
        areaLog.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollLog = new JScrollPane(areaLog);

        JButton btnEjecutarSimulacion = new JButton("Ejecutar Simulación Completa");
        JButton btnSimularTareas = new JButton("Simular Tareas de Robots");
        JButton btnSimularPatrullaje = new JButton("Simular Patrullaje de Drones");

        // Acciones de simulación
        btnEjecutarSimulacion.addActionListener(e -> ejecutarSimulacionCompleta(areaLog));
        btnSimularTareas.addActionListener(e -> simularTareasRobots(areaLog));
        btnSimularPatrullaje.addActionListener(e -> simularPatrullajeDrones(areaLog));

        JPanel panelBotonesSuperior = new JPanel(new FlowLayout());
        panelBotonesSuperior.add(btnEjecutarSimulacion);
        panelBotonesSuperior.add(btnSimularTareas);
        panelBotonesSuperior.add(btnSimularPatrullaje);

        JPanel panelBotonesInferior = new JPanel(new FlowLayout());
        JButton btnVolver = new JButton("Volver al Menú");
        JButton btnLimpiarLog = new JButton("🗑Limpiar Log");

        btnVolver.addActionListener(e -> cardLayout.show(panelPrincipal, "Menu"));
        btnLimpiarLog.addActionListener(e -> areaLog.setText(""));

        panelBotonesInferior.add(btnVolver);
        panelBotonesInferior.add(btnLimpiarLog);

        panel.add(lblTitulo, BorderLayout.NORTH);
        panel.add(panelBotonesSuperior, BorderLayout.CENTER);
        panel.add(scrollLog, BorderLayout.CENTER);
        panel.add(panelBotonesInferior, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel crearPanelDashboard() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTabbedPane tabbedPane = new JTabbedPane();

        // Diferentes vistas del dashboard
        tabbedPane.addTab("Estado General", crearPanelEstadoGeneral());
        tabbedPane.addTab("Estado Energético", crearPanelEstadoEnergetico());
        tabbedPane.addTab("Seguridad", crearPanelSeguridad());
        tabbedPane.addTab("Bienestar", crearPanelBienestar());

        JPanel panelBotones = new JPanel(new FlowLayout());
        JButton btnVolver = new JButton("↩Volver al Menú");
        JButton btnActualizar = new JButton("Actualizar Dashboard");

        btnVolver.addActionListener(e -> cardLayout.show(panelPrincipal, "Menu"));

        panelBotones.add(btnVolver);
        panelBotones.add(btnActualizar);

        panel.add(new JLabel("DASHBOARD DEL SISTEMA NEO-URBE", SwingConstants.CENTER), BorderLayout.NORTH);
        panel.add(tabbedPane, BorderLayout.CENTER);
        panel.add(panelBotones, BorderLayout.SOUTH);

        return panel;
    }

    // Métodos de acción (simulados - necesitarás implementar la lógica real)

    private void crearCiudadanoIndividual(JTextField txtId, JTextField txtNombre,
                                          JComboBox<String> cmbEdificio, JComboBox<String> cmbRobot) {
        // Implementar creación de ciudadano
        JOptionPane.showMessageDialog(this, "Ciudadano creado exitosamente");
    }

    private void generarCiudadanosBloque(int cantidad) {
        // Implementar creación en bloque
        JOptionPane.showMessageDialog(this, cantidad + " ciudadanos generados exitosamente");
    }

    private void actualizarListaCiudadanos(JTextArea area) {
        // Implementar obtención de lista de ciudadanos
        area.setText("CIU001 - Juan Pérez - EDF001 - Con Robot\n" +
                "CIU002 - María García - EDF002 - Sin Robot\n" +
                "CIU003 - Carlos López - EDF001 - Con Robot");
    }

    private void crearRobotIndividual(JTextField txtId, JSpinner spinnerBateria, JComboBox<String> cmbCiudadano) {
        // Implementar creación de robot
        JOptionPane.showMessageDialog(this, "Robot creado exitosamente");
    }

    private void crearRobotsBloque() {
        // Implementar creación en bloque
        JOptionPane.showMessageDialog(this, "5 robots creados automáticamente");
    }

    private void generarDronesAutomaticos() {
        // Implementar generación de drones
        JOptionPane.showMessageDialog(this, "Drones generados automáticamente");
    }

    private void mostrarEstadoDrones() {
        // Implementar visualización de estado
        JOptionPane.showMessageDialog(this, "Estado de drones mostrado");
    }

    private void iniciarPatrullaje() {
        // Implementar patrullaje
        JOptionPane.showMessageDialog(this, "Patrullaje iniciado");
    }

    private void recargarDronesCriticos() {
        // Implementar recarga
        JOptionPane.showMessageDialog(this, "Drones críticos recargados");
    }

    private void ejecutarSimulacionCompleta(JTextArea areaLog) {
        // Implementar simulación completa
        areaLog.append("=== INICIANDO SIMULACIÓN COMPLETA ===\n");
        areaLog.append("Tareas de robots simuladas\n");
        areaLog.append("Patrullaje de drones completado\n");
        areaLog.append("Anomalías detectadas: 3\n");
        areaLog.append("Recargas realizadas: 2\n");
        areaLog.append("=== SIMULACIÓN FINALIZADA ===\n\n");
    }

    private void simularTareasRobots(JTextArea areaLog) {
        // Implementar simulación de tareas
        areaLog.append("Simulando tareas de robots...\n");
        areaLog.append("15 tareas ejecutadas\n");
        areaLog.append("Consumo energético: 120%\n\n");
    }

    private void simularPatrullajeDrones(JTextArea areaLog) {
        // Implementar simulación de patrullaje
        areaLog.append("Simulando patrullaje de drones...\n");
        areaLog.append("8 drones patrullaron\n");
        areaLog.append("2 anomalías detectadas\n");
        areaLog.append("Consumo batería: 200%\n\n");
    }

    private JPanel crearPanelEstadoGeneral() {
        JPanel panel = new JPanel(new BorderLayout());
        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setText("=== ESTADO GENERAL DEL SISTEMA ===\n\n" +
                "Ciudadanos: 150\n" +
                "Robots: 120\n" +
                "Drones: 25\n" +
                "Edificios: 8\n" +
                "Estaciones de energía: 6\n\n" +
                "Sistema operando normalmente");
        panel.add(new JScrollPane(area), BorderLayout.CENTER);
        return panel;
    }

    private JPanel crearPanelEstadoEnergetico() {
        JPanel panel = new JPanel(new BorderLayout());
        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setText("=== ESTADO ENERGÉTICO ===\n\n" +
                "Robots en alerta: 8/120 (6.7%)\n" +
                "Drones en alerta: 3/25 (12%)\n" +
                "Estaciones disponibles: 5/6\n" +
                "Ocupación promedio: 45%\n\n" +
                "Alerta: 2 edificios con alta demanda");
        panel.add(new JScrollPane(area), BorderLayout.CENTER);
        return panel;
    }

    private JPanel crearPanelGestionBateria() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField txtIdRobot = new JTextField();
        JSpinner spinnerBateria = new JSpinner(new SpinnerNumberModel(50, 0, 100, 5));
        JButton btnActualizar = new JButton("Actualizar Batería");

        panel.add(new JLabel("ID del Robot:"));
        panel.add(txtIdRobot);
        panel.add(new JLabel("Nueva Batería (%):"));
        panel.add(spinnerBateria);
        panel.add(new JLabel());
        panel.add(btnActualizar);

        return panel;
    }

    private JPanel crearPanelEstadoRobots() {
        JPanel panel = new JPanel(new BorderLayout());
        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setText("ROB001 - Batería: 85% - Asignado: CIU001\n" +
                "ROB002 - Batería: 45% - Asignado: CIU003\n" +
                "ROB003 - Batería: 100% - Sin asignar\n" +
                "ROB004 - Batería: 20% - Asignado: CIU005");
        panel.add(new JScrollPane(area), BorderLayout.CENTER);
        return panel;
    }

    private JPanel crearPanelSeguridad() {
        JPanel panel = new JPanel(new BorderLayout());
        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setText("=== SEGURIDAD ===\n\n" +
                "🚨 Incidentes últimas 24h: 5\n" +
                "✅ Respuestas efectivas: 5\n" +
                "🛡️ Drones activos: 18/25\n" +
                "📊 Tiempo respuesta promedio: 4.2 min\n\n" +
                "✅ Sistema de seguridad operativo");
        panel.add(new JScrollPane(area), BorderLayout.CENTER);
        return panel;
    }

    private JPanel crearPanelBienestar() {
        JPanel panel = new JPanel(new BorderLayout());
        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setText("=== BIENESTAR CIUDADANO ===\n\n" +
                "🏠 Ocupación promedio: 78%\n" +
                "🤖 Ciudadanos con robot: 85%\n" +
                "📈 Relación robot/ciudadano: 0.8\n" +
                "💡 Edificios al 90%+: 2\n\n" +
                "✅ Bienestar general: Alto");
        panel.add(new JScrollPane(area), BorderLayout.CENTER);
        return panel;
    }
}
