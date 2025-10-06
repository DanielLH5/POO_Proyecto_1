package vista;

import logica.PerfilAdministrativo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfazPrincipal extends JFrame {
    private PerfilAdministrativo perfilAdmin;

    public InterfazPrincipal() {
        perfilAdmin = new PerfilAdministrativo();
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
        JButton btnUsuario = new JButton("Perfil Usuario");
        JButton btnInvitado = new JButton("Perfil Invitado");

        // Agregar botones
        panelBotones.add(btnAdmin);
        panelBotones.add(btnUsuario);
        panelBotones.add(btnInvitado);

        panelPrincipal.add(titulo, BorderLayout.NORTH);
        panelPrincipal.add(panelBotones, BorderLayout.CENTER);
        add(panelPrincipal);
    }

    private void abrirInterfazAdministrador() {
        new InterfazAdministrador(perfilAdmin).setVisible(true);
    }
}