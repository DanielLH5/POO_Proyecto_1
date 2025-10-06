package vista;

import logica.PerfilGeneral;

import javax.swing.*;
import java.awt.*;

public class InterfazGeneral extends JFrame {
    private PerfilGeneral perfilGeneral;
    private JPanel panelPrincipal;
    private CardLayout cardLayout;

    public InterfazGeneral(PerfilGeneral perfilGeneral) {
        this.perfilGeneral = perfilGeneral;
        configurarVentana();
        //inicializarComponentes();
    }

    private void configurarVentana() {
        setTitle("Perfil Administrador - Gestión de Edificios");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);
        setResizable(true);
    }

}
/*
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

 */