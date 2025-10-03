package vista;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        // Pruba de UI
        // Crear ventana
        JFrame frame = new JFrame("Mi aplicación Swing");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear panel y botón
        JPanel panel = new JPanel();
        JButton buttonAdmin = new JButton("Perfil Administrador");
        buttonAdmin.addActionListener(e -> System.out.println("Button adm!"));
        JButton buttonOpe = new JButton("Perfil Operador");
        buttonOpe.addActionListener(e -> System.out.println("Button ope!"));
        JButton buttonGen = new JButton("Perfil General");
        buttonGen.addActionListener(e -> System.out.println("Button gen!"));

        panel.add(buttonAdmin);
        panel.add(buttonOpe);
        panel.add(buttonGen);
        frame.add(panel);

        // Mostrar ventana
        frame.setVisible(true);
    }
}