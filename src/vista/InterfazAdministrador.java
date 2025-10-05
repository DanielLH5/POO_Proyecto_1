package vista;

import logica.PerfilAdministrativo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfazAdministrador extends JFrame {
    private PerfilAdministrativo perfilAdmin;
    private JPanel panelPrincipal;
    private CardLayout cardLayout;

    public InterfazAdministrador(PerfilAdministrativo perfilAdmin) {
        this.perfilAdmin = perfilAdmin;
        configurarVentana();
        inicializarComponentes();
    }

    private void configurarVentana() {
        setTitle("Perfil Administrador - Gestión de Edificios");
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
        JPanel panelCrearEdificio = crearPanelCrearEdificio();
        JPanel panelEdificiosAleatorios = crearPanelEdificiosAleatorios();
        JPanel panelVerEdificios = crearPanelVerEdificios();

        // Agregar paneles al CardLayout
        panelPrincipal.add(panelMenu, "Menu");
        panelPrincipal.add(panelCrearEdificio, "CrearEdificio");
        panelPrincipal.add(panelEdificiosAleatorios, "EdificiosAleatorios");
        panelPrincipal.add(panelVerEdificios, "VerEdificios");

        // Mostrar el menú principal primero
        cardLayout.show(panelPrincipal, "Menu");

        add(panelPrincipal);
    }

    private JPanel crearPanelMenu() {
        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        JLabel titulo = new JLabel("MENÚ ADMINISTRADOR", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 16));

        JButton btnCrearEdificio = new JButton("Crear Edificio Manual");
        JButton btnGenerarAleatorios = new JButton("Generar Edificios Aleatorios");
        JButton btnVerEdificios = new JButton("Ver Lista de Edificios");

        // Estilizar botones
        Font fontBotones = new Font("Arial", Font.PLAIN, 14);
        btnCrearEdificio.setFont(fontBotones);
        btnGenerarAleatorios.setFont(fontBotones);
        btnVerEdificios.setFont(fontBotones);

        // Acciones de los botones
        btnCrearEdificio.addActionListener(e -> cardLayout.show(panelPrincipal, "CrearEdificio"));
        btnGenerarAleatorios.addActionListener(e -> cardLayout.show(panelPrincipal, "EdificiosAleatorios"));
        btnVerEdificios.addActionListener(e -> {
            cardLayout.show(panelPrincipal, "VerEdificios");
            actualizarListaEdificios();
        });

        panel.add(titulo);
        panel.add(btnCrearEdificio);
        panel.add(btnGenerarAleatorios);
        panel.add(btnVerEdificios);

        return panel;
    }

    private JPanel crearPanelCrearEdificio() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Panel de formulario
        JPanel panelFormulario = new JPanel(new GridLayout(5, 2, 10, 10));

        JTextField txtId = new JTextField();
        JTextField txtNombre = new JTextField();
        JTextField txtUbicacion = new JTextField();
        JTextField txtCapacidad = new JTextField();

        panelFormulario.add(new JLabel("ID único:"));
        panelFormulario.add(txtId);
        panelFormulario.add(new JLabel("Nombre:"));
        panelFormulario.add(txtNombre);
        panelFormulario.add(new JLabel("Ubicación:"));
        panelFormulario.add(txtUbicacion);
        panelFormulario.add(new JLabel("Capacidad máxima:"));
        panelFormulario.add(txtCapacidad);

        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout());
        JButton btnCrear = new JButton("Crear Edificio");
        JButton btnVolver = new JButton("Volver al Menú");

        btnCrear.addActionListener(e -> crearEdificio(txtId, txtNombre, txtUbicacion, txtCapacidad));
        btnVolver.addActionListener(e -> cardLayout.show(panelPrincipal, "Menu"));

        panelBotones.add(btnCrear);
        panelBotones.add(btnVolver);

        panel.add(new JLabel("CREAR EDIFICIO MANUAL", SwingConstants.CENTER), BorderLayout.NORTH);
        panel.add(panelFormulario, BorderLayout.CENTER);
        panel.add(panelBotones, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel crearPanelEdificiosAleatorios() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        JPanel panelContenido = new JPanel(new FlowLayout());

        JLabel lblInfo = new JLabel("Cantidad de edificios a generar (3-10):");
        JSpinner spinner = new JSpinner(new SpinnerNumberModel(3, 3, 10, 1));
        JButton btnGenerar = new JButton("Generar Edificios");
        JButton btnVolver = new JButton("Volver al Menú");
/*
        btnGenerar.addActionListener(e -> {
            int cantidad = (int) spinner.getValue();
            generarEdificiosAleatorios(cantidad);
        });

 */

        btnVolver.addActionListener(e -> cardLayout.show(panelPrincipal, "Menu"));

        panelContenido.add(lblInfo);
        panelContenido.add(spinner);
        panelContenido.add(btnGenerar);

        JPanel panelInferior = new JPanel(new FlowLayout());
        panelInferior.add(btnVolver);

        panel.add(new JLabel("GENERAR EDIFICIOS ALEATORIOS", SwingConstants.CENTER), BorderLayout.NORTH);
        panel.add(panelContenido, BorderLayout.CENTER);
        panel.add(panelInferior, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel crearPanelVerEdificios() {
        JPanel panel = new JPanel(new BorderLayout());

        // Área de texto para mostrar edificios
        JTextArea areaEdificios = new JTextArea();
        areaEdificios.setEditable(false);
        areaEdificios.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(areaEdificios);

        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout());
        JButton btnActualizar = new JButton("Actualizar Lista");
        JButton btnVolver = new JButton("Volver al Menú");

        btnActualizar.addActionListener(e -> actualizarListaEdificios());
        btnVolver.addActionListener(e -> cardLayout.show(panelPrincipal, "Menu"));

        panelBotones.add(btnActualizar);
        panelBotones.add(btnVolver);

        panel.add(new JLabel("LISTA DE EDIFICIOS", SwingConstants.CENTER), BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(panelBotones, BorderLayout.SOUTH);

        return panel;
    }

    // Métodos para las acciones
    private void crearEdificio(JTextField txtId, JTextField txtNombre,
                               JTextField txtUbicacion, JTextField txtCapacidad) {
        try {
            String id = txtId.getText().trim();
            String nombre = txtNombre.getText().trim();
            String ubicacion = txtUbicacion.getText().trim();
            int capacidad = Integer.parseInt(txtCapacidad.getText().trim());

            if (id.isEmpty() || nombre.isEmpty() || ubicacion.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios");
                return;
            }

            if (capacidad <= 0) {
                JOptionPane.showMessageDialog(this, "La capacidad debe ser mayor a 0");
                return;
            }

            // Llamar al método del perfil administrativo
            boolean exito = perfilAdmin.crearEdificiosInteligentes(id, nombre, ubicacion, capacidad);

            if (exito) {
                JOptionPane.showMessageDialog(this, "Edificio creado exitosamente!");
                // Limpiar campos
                txtId.setText("");
                txtNombre.setText("");
                txtUbicacion.setText("");
                txtCapacidad.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Error: El ID ya existe");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "La capacidad debe ser un número válido");
        }
    }
/*
    private void generarEdificiosAleatorios(int cantidad) {
        boolean exito = perfilAdmin.generarEdificiosAleatorios(cantidad);
        if (exito) {
            JOptionPane.showMessageDialog(this,
                    cantidad + " edificios aleatorios generados exitosamente!");
        } else {
            JOptionPane.showMessageDialog(this, "Error al generar edificios aleatorios");
        }
    }

 */

    private void actualizarListaEdificios() {
        // Este método se llamará cuando se entre al panel "Ver Edificios"
        String lista = perfilAdmin.obtenerListaEdificios();

        // Buscar el JTextArea en el panel actual
        Component[] components = panelPrincipal.getComponents();
        for (Component comp : components) {
            if (comp instanceof JPanel) {
                JScrollPane scrollPane = findScrollPane((JPanel) comp);
                if (scrollPane != null) {
                    JTextArea areaEdificios = (JTextArea) scrollPane.getViewport().getView();
                    areaEdificios.setText(lista);
                    break;
                }
            }
        }
    }

    private JScrollPane findScrollPane(Container container) {
        for (Component comp : container.getComponents()) {
            if (comp instanceof JScrollPane) {
                return (JScrollPane) comp;
            } else if (comp instanceof Container) {
                JScrollPane result = findScrollPane((Container) comp);
                if (result != null) return result;
            }
        }
        return null;
    }
}