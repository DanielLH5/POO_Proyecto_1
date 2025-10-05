package vista;

import javax.swing.*;
import java.awt.*;


import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new InterfazPrincipal().setVisible(true);
        });
    }
}