package vista;

import logica.PerfilAdministrativo;
import logica.PerfilGeneral;
import logica.PerfilOperativo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfazPrincipal extends JFrame {
    private PerfilAdministrativo perfilAdmin;
    private PerfilOperativo perfilOperador;
    private PerfilGeneral perfilGeneral;

    public InterfazPrincipal() {
        perfilAdmin = new PerfilAdministrativo();
        perfilOperador = new PerfilOperativo();
        perfilGeneral = new PerfilGeneral();
        configurarVentanaPrincipal();
    }

    private void configurarVentanaPrincipal() {
        setTitle("Sistema de Edificios Inteligentes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Título
        JLabel titulo = new JLabel("SELECCIONE SU PERFIL", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));

        // Panel de botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(3, 1, 10, 10));

        // Botón Perfil Administrador
        JButton btnAdmin = new JButton("Perfil Administrador");
        btnAdmin.setFont(new Font("Arial", Font.PLAIN, 14));
        btnAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirInterfazAdministrador();
            }
        });

        // Botones para otros perfiles
        JButton btnOperador = new JButton("Perfil Opertador");
        btnOperador.setFont(new Font("Arial", Font.PLAIN, 14));
        btnOperador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirInterfazOperador();
            }
        });

        JButton btnGeneral = new JButton("Perfil general");
        btnGeneral.setFont(new Font("Arial", Font.PLAIN, 14));
        btnGeneral.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirInterfazGeneral();
            }
        });

        // Agregar botones
        panelBotones.add(btnAdmin);
        panelBotones.add(btnOperador);
        panelBotones.add(btnGeneral);

        panelPrincipal.add(titulo, BorderLayout.NORTH);
        panelPrincipal.add(panelBotones, BorderLayout.CENTER);
        add(panelPrincipal);
    }

    private void abrirInterfazAdministrador() {
        new InterfazAdministrador(perfilAdmin).setVisible(true);
    }

    private void abrirInterfazOperador() {
        new InterfazOperador(perfilOperador).setVisible(true);
    }

    private void abrirInterfazGeneral() {
        new InterfazGeneral(perfilGeneral).setVisible(true);
    }
}