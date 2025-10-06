package NeoUrbeVista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.*;
import java.util.List;

/** UI base de Neo-Urbe (independiente del .form) */
public class NeoUrbe {
    // layout base
    private JPanel rootPanel;
    private JPanel navContainer;
    private JPanel contentCards;

    // paleta
    private static final Color PRIMARY        = Color.decode("#3EC8FF");
    private static final Color NAV_BTN_BG     = Color.decode("#7BD0F4");
    private static final Color NAV_BTN_ACTIVE = Color.decode("#0C86C6");
    private static final Color TITLE_DARK     = Color.decode("#2C3E50");

    // imágenes (classpath: src/Imagenes)
    private static final String WINDOW_ICON = "/Imagenes/Administrador (2).png";
    private static final String NAVBAR_IMG  = "/Imagenes/Administrador (2).png";
    private static final String HERO_IMG    = "/Imagenes/Administrador (1).png";

    // medida barra superior
    private static final int TOPBAR_H = 72;
    private static final int BADGE_W  = 140;
    private static final int BADGE_H  = 40;

    // navbar
    private final JToggleButton btnInicio = roundedNavButton("Inicio", true);
    private final JToggleButton btnAdmin  = roundedNavButton("Administrador", false);
    private final JToggleButton btnOper   = roundedNavButton("Operador", false);
    private final JToggleButton btnGen    = roundedNavButton("General", false);

    // datos de Admin (edificios/estaciones)
    private final Random rnd = new Random();
    private final List<Edificio> edificios = new ArrayList<>();
    private final List<Estacion> estaciones = new ArrayList<>();
    private int nextEdifId = 1;
    private int nextEstId  = 1;

    // Consejo de Inteligencia
    private static final String[] EVENTOS_BASE = {
            "Colisión vehicular",
            "Congestión vehicular",
            "Desarrollo de obra pública",
            "Derrames de sustancias peligrosas en carretera",
            "Incendio",
            "Presencia de humo",
            "Presencia de gases",
            "Accidente grave",
            "Presencia de ambulancias en estado de emergencia"
    };
    private final DefaultListModel<String> accionesModel = new DefaultListModel<>();
    private final DefaultListModel<String> eventosModel  = new DefaultListModel<>();
    private final Map<String, LinkedHashSet<String>> accionesPorEvento = new LinkedHashMap<>();

    // Reglas
    private final Reglas reglas = new Reglas(25, true, 20, true);

    public NeoUrbe() { buildUI(); }

    // arranque
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            NeoUrbe app = new NeoUrbe();
            JFrame f = new JFrame("Neo-Urbe");
            f.setContentPane(app.rootPanel);
            f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            f.setMinimumSize(new Dimension(980, 640));
            f.setLocationRelativeTo(null);
            f.setIconImage(load(WINDOW_ICON));
            f.setVisible(true);
        });
    }

    // construcción de la UI
    private void buildUI() {
        rootPanel     = new JPanel(new BorderLayout());
        navContainer  = new JPanel(new FlowLayout(FlowLayout.CENTER, 22, 12));
        contentCards  = new JPanel(new CardLayout());

        rootPanel.add(buildTopBar(), BorderLayout.NORTH);
        rootPanel.add(contentCards,  BorderLayout.CENTER);

        initConsejoDatos();

        JPanel vistaInicio  = buildHomeHero();
        JPanel vistaAdmin   = buildAdminView();
        JPanel vistaOper    = buildWhitePlaceholder("Operador");
        JPanel vistaGeneral = buildWhitePlaceholder("General");

        contentCards.add(vistaInicio,  "INICIO");
        contentCards.add(vistaAdmin,   "ADMIN");
        contentCards.add(vistaOper,    "OPERADOR");
        contentCards.add(vistaGeneral, "GENERAL");

        showCard("INICIO");
    }

    // topbar
    private JComponent buildTopBar() {
        JPanel top = new JPanel(new BorderLayout());
        top.setBackground(Color.WHITE);
        top.setPreferredSize(new Dimension(0, TOPBAR_H));

        JPanel left = new JPanel(new FlowLayout(FlowLayout.LEFT, 16, (TOPBAR_H - BADGE_H)/2));
        left.setOpaque(false);
        left.add(new ImageBadge(load(NAVBAR_IMG), BADGE_W, BADGE_H));
        top.add(left, BorderLayout.WEST);

        ButtonGroup g = new ButtonGroup();
        g.add(btnInicio); g.add(btnAdmin); g.add(btnOper); g.add(btnGen);

        btnInicio.addActionListener(e -> showCard("INICIO"));
        btnAdmin.addActionListener(e  -> showCard("ADMIN"));
        btnOper.addActionListener(e   -> showCard("OPERADOR"));
        btnGen.addActionListener(e    -> showCard("GENERAL"));

        navContainer.setOpaque(false);
        navContainer.add(btnInicio);
        navContainer.add(btnAdmin);
        navContainer.add(btnOper);
        navContainer.add(btnGen);

        top.add(navContainer, BorderLayout.CENTER);
        return top;
    }

    // cambio de tarjeta
    private void showCard(String key) {
        ((CardLayout) contentCards.getLayout()).show(contentCards, key);
        contentCards.revalidate();
        contentCards.repaint();
    }

    // botón redondeado
    private JToggleButton roundedNavButton(String text, boolean selected) {
        JToggleButton b = new JToggleButton(text) {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                boolean hover = Boolean.TRUE.equals(getClientProperty("hover"));
                Color fill = isSelected() ? NAV_BTN_ACTIVE : NAV_BTN_BG;
                if (hover && !isSelected()) fill = fill.darker();
                g2.setColor(fill);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 24, 24);
                FontMetrics fm = g2.getFontMetrics(getFont());
                int tx = (getWidth() - fm.stringWidth(getText())) / 2;
                int ty = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;
                g2.setColor(Color.WHITE);
                g2.drawString(getText(), tx, ty);
                g2.dispose();
            }
        };
        b.setSelected(selected);
        b.setContentAreaFilled(false);
        b.setBorderPainted(false);
        b.setFocusPainted(false);
        b.setForeground(Color.WHITE);
        b.setFont(b.getFont().deriveFont(Font.BOLD, 14f));
        b.setPreferredSize(new Dimension(150, 42));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override public void mouseEntered(java.awt.event.MouseEvent e){ b.putClientProperty("hover", true);  b.repaint(); }
            @Override public void mouseExited (java.awt.event.MouseEvent e){ b.putClientProperty("hover", false); b.repaint(); }
        });
        return b;
    }

    // portada
    private JPanel buildHomeHero() {
        JPanel p = new JPanel(new BorderLayout()) {
            @Override protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(PRIMARY);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        p.setBackground(PRIMARY);

        JPanel center = new JPanel();
        center.setOpaque(false);
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.setBorder(new EmptyBorder(28, 0, 0, 0));

        JComponent logo = new HeroImage(load(HERO_IMG), 600, 260);
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subt = new JLabel("BIENVENIDO A");
        subt.setAlignmentX(Component.CENTER_ALIGNMENT);
        subt.setForeground(new Color(245, 252, 255));
        subt.setFont(subt.getFont().deriveFont(Font.PLAIN, 18f));

        JLabel title = new JLabel("NEO-URBE");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setForeground(TITLE_DARK);
        title.setFont(title.getFont().deriveFont(Font.BOLD, 56f));

        center.add(logo);
        center.add(Box.createVerticalStrut(2));
        center.add(subt);
        center.add(Box.createVerticalStrut(2));
        center.add(title);

        p.add(center, BorderLayout.NORTH);
        return p;
    }

    // ADMIN raíz
    private JPanel buildAdminView() {
        JPanel admin = new JPanel(new BorderLayout());
        admin.setBackground(Color.WHITE);

        JPanel line = new JPanel();
        line.setBackground(PRIMARY);
        line.setPreferredSize(new Dimension(0, 4));
        admin.add(line, BorderLayout.NORTH);

        JPanel west = new JPanel();
        west.setLayout(new BoxLayout(west, BoxLayout.Y_AXIS));
        west.setBackground(Color.WHITE);
        west.setBorder(new EmptyBorder(12, 12, 12, 12));

        JButton bEdif  = sideButton("Edificios");
        JButton bEst   = sideButton("Estaciones");
        JButton bConse = sideButton("Consejo de Inteligencia");
        JButton bReg   = sideButton("Reglas");

        west.add(bEdif);  west.add(Box.createVerticalStrut(8));
        west.add(bEst);   west.add(Box.createVerticalStrut(8));
        west.add(bConse); west.add(Box.createVerticalStrut(8));
        west.add(bReg);

        admin.add(west, BorderLayout.WEST);

        JPanel centerCards = new JPanel(new CardLayout());
        centerCards.setBackground(Color.WHITE);
        admin.add(centerCards, BorderLayout.CENTER);

        JPanel cardEdif = buildEdificiosPanel();
        JPanel cardEst  = buildEstacionesPanel();
        JPanel cardConsejo = buildConsejoPanel();
        JPanel cardReglas  = buildReglasPanel();

        centerCards.add(cardEdif,   "EDIFICIOS");
        centerCards.add(cardEst,    "ESTACIONES");
        centerCards.add(cardConsejo,"CONSEJO");
        centerCards.add(cardReglas, "REGLAS");

        bEdif.addActionListener(e  -> ((CardLayout)centerCards.getLayout()).show(centerCards,"EDIFICIOS"));
        bEst.addActionListener(e   -> ((CardLayout)centerCards.getLayout()).show(centerCards,"ESTACIONES"));
        bConse.addActionListener(e -> ((CardLayout)centerCards.getLayout()).show(centerCards,"CONSEJO"));
        bReg.addActionListener(e   -> ((CardLayout)centerCards.getLayout()).show(centerCards,"REGLAS"));

        return admin;
    }

    // ADMIN: Edificios
    private JPanel buildEdificiosPanel() {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(Color.WHITE);
        p.setBorder(new EmptyBorder(12,12,12,12));

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 2));
        top.setOpaque(false);

        JLabel l = new JLabel("Crear edificios");
        JTextField txt = new JTextField(10);
        txt.setToolTipText("Insertar número [3-10]");
        JButton bAle = smallPill("Aleatorio");
        JButton bGen = smallPill("Generar");

        top.add(l); top.add(txt); top.add(bAle); top.add(bGen);
        p.add(top, BorderLayout.NORTH);

        EdificioTableModel model = new EdificioTableModel(edificios);
        JTable table = styledTable(model);
        p.add(new JScrollPane(table), BorderLayout.CENTER);

        bAle.addActionListener(e -> {
            int n = 3 + rnd.nextInt(8 - 3 + 1);
            genEdificios(n);
            model.fireTableDataChanged();
        });

        bGen.addActionListener(e -> {
            try {
                int n = Integer.parseInt(txt.getText().trim());
                if (n < 3 || n > 10) {
                    JOptionPane.showMessageDialog(p,"Ingrese un número entre 3 y 10","Aviso",JOptionPane.WARNING_MESSAGE);
                    return;
                }
                genEdificios(n);
                model.fireTableDataChanged();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(p,"Valor inválido","Aviso",JOptionPane.WARNING_MESSAGE);
            }
        });

        return p;
    }

    // ADMIN: Estaciones
    private JPanel buildEstacionesPanel() {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(Color.WHITE);
        p.setBorder(new EmptyBorder(12,12,12,12));

        JPanel north = new JPanel(new BorderLayout());
        north.setOpaque(false);

        JPanel left = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 2));
        left.setOpaque(false);
        JLabel l = new JLabel("Crear estaciones");
        JTextField txt = new JTextField(10);
        txt.setToolTipText("Insertar número [5-8]");
        JButton bAle = smallPill("Aleatorio");
        JButton bGen = smallPill("Generar");
        left.add(l); left.add(txt); left.add(bAle); left.add(bGen);

        JPanel right = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 2));
        right.setOpaque(false);
        JLabel l2 = new JLabel("Actualizar estado");
        JComboBox<Estado> cb = new JComboBox<>(Estado.values());
        JButton bUp = smallPill("Actualizar");
        right.add(l2); right.add(cb); right.add(bUp);

        north.add(left, BorderLayout.WEST);
        north.add(right, BorderLayout.EAST);
        p.add(north, BorderLayout.NORTH);

        EstacionTableModel model = new EstacionTableModel(estaciones);
        JTable table = styledTable(model);
        p.add(new JScrollPane(table), BorderLayout.CENTER);

        bAle.addActionListener(e -> {
            int n = 5 + rnd.nextInt(8 - 5 + 1);
            genEstaciones(n);
            model.fireTableDataChanged();
        });

        bGen.addActionListener(e -> {
            try {
                int n = Integer.parseInt(txt.getText().trim());
                if (n < 5 || n > 8) {
                    JOptionPane.showMessageDialog(p,"Ingrese un número entre 5 y 8","Aviso",JOptionPane.WARNING_MESSAGE);
                    return;
                }
                genEstaciones(n);
                model.fireTableDataChanged();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(p,"Valor inválido","Aviso",JOptionPane.WARNING_MESSAGE);
            }
        });

        bUp.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row < 0) {
                JOptionPane.showMessageDialog(p,"Seleccione una estación en la tabla","Aviso",JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            Estado nuevo = (Estado) cb.getSelectedItem();
            estaciones.get(row).estado = nuevo;
            model.fireTableRowsUpdated(row, row);
        });

        return p;
    }

    // ADMIN: Consejo de Inteligencia
    private JPanel buildConsejoPanel() {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(Color.WHITE);
        p.setBorder(new EmptyBorder(12,12,12,12));

        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 2));
        header.setOpaque(false);
        header.add(new JLabel("Consejo de Inteligencia"));
        p.add(header, BorderLayout.NORTH);

        JPanel center = new JPanel(new BorderLayout(10,10));
        center.setOpaque(false);
        p.add(center, BorderLayout.CENTER);

        // eventos
        JList<String> lstEventos = new JList<>(eventosModel);
        lstEventos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane spEventos = new JScrollPane(lstEventos);
        spEventos.setBorder(BorderFactory.createTitledBorder("Eventos"));
        center.add(spEventos, BorderLayout.WEST);

        // acciones disponibles y asociadas
        DefaultListModel<String> asociadasModel = new DefaultListModel<>();
        JList<String> lstDisponibles = new JList<>(accionesModel);
        JList<String> lstAsociadas   = new JList<>(asociadasModel);
        lstDisponibles.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lstAsociadas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JPanel mid = new JPanel(new BorderLayout(8,8));
        mid.setOpaque(false);
        JPanel lists = new JPanel(new GridLayout(1,2,8,8));
        lists.add(wrapList(spTitled(lstDisponibles,"Acciones disponibles")));
        lists.add(wrapList(spTitled(lstAsociadas,"Acciones del evento")));
        mid.add(lists, BorderLayout.CENTER);

        // botones de asociación
        JPanel move = new JPanel(new GridLayout(2,1,6,6));
        JButton bAdd = smallPill(">>");
        JButton bRem = smallPill("<<");
        move.add(bAdd); move.add(bRem);
        mid.add(move, BorderLayout.EAST);

        center.add(mid, BorderLayout.CENTER);

        // CRUD acciones
        JPanel crud = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 2));
        crud.setOpaque(false);
        JTextField txtAccion = new JTextField(18);
        JButton bCrear = smallPill("Crear");
        JButton bRen   = smallPill("Renombrar");
        JButton bDel   = smallPill("Eliminar");
        crud.add(new JLabel("Acción:"));
        crud.add(txtAccion);
        crud.add(bCrear); crud.add(bRen); crud.add(bDel);
        center.add(crud, BorderLayout.SOUTH);

        // selección de evento
        lstEventos.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                asociadasModel.clear();
                String ev = lstEventos.getSelectedValue();
                if (ev != null) {
                    for (String a : accionesPorEvento.get(ev)) asociadasModel.addElement(a);
                }
            }
        });
        if (!eventosModel.isEmpty()) lstEventos.setSelectedIndex(0);

        // asociar acción
        bAdd.addActionListener(e -> {
            String ev = lstEventos.getSelectedValue();
            String ac = lstDisponibles.getSelectedValue();
            if (ev == null || ac == null) return;
            LinkedHashSet<String> set = accionesPorEvento.get(ev);
            if (set.add(ac)) asociadasModel.addElement(ac);
        });

        // quitar acción (no dejar vacío)
        bRem.addActionListener(e -> {
            String ev = lstEventos.getSelectedValue();
            String ac = lstAsociadas.getSelectedValue();
            if (ev == null || ac == null) return;
            LinkedHashSet<String> set = accionesPorEvento.get(ev);
            if (set.size() == 1) {
                JOptionPane.showMessageDialog(p,"Cada evento debe tener al menos 1 acción","Aviso",JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (set.remove(ac)) {
                asociadasModel.removeElement(ac);
            }
        });

        // crear acción
        bCrear.addActionListener(e -> {
            String name = txtAccion.getText().trim();
            if (name.isEmpty()) return;
            if (!contains(accionesModel, name)) accionesModel.addElement(name);
            txtAccion.setText("");
        });

        // renombrar acción (actualiza en todos los eventos)
        bRen.addActionListener(e -> {
            String sel = lstDisponibles.getSelectedValue();
            String name = txtAccion.getText().trim();
            if (sel == null || name.isEmpty()) return;
            if (contains(accionesModel, name)) {
                JOptionPane.showMessageDialog(p,"Ya existe una acción con ese nombre","Aviso",JOptionPane.WARNING_MESSAGE);
                return;
            }
            for (int i=0;i<accionesModel.size();i++) if (accionesModel.get(i).equals(sel)) accionesModel.set(i, name);
            for (Map.Entry<String, LinkedHashSet<String>> en : accionesPorEvento.entrySet()) {
                if (en.getValue().remove(sel)) en.getValue().add(name);
            }
            // refrescar asociadas del evento actual
            String ev = lstEventos.getSelectedValue();
            if (ev != null) {
                asociadasModel.clear();
                for (String a : accionesPorEvento.get(ev)) asociadasModel.addElement(a);
            }
            txtAccion.setText("");
        });

        // eliminar acción (no dejar eventos sin acciones)
        bDel.addActionListener(e -> {
            String sel = lstDisponibles.getSelectedValue();
            if (sel == null) return;
            for (String ev : eventosModelToList()) {
                LinkedHashSet<String> set = accionesPorEvento.get(ev);
                if (set.contains(sel) && set.size()==1) {
                    JOptionPane.showMessageDialog(p,"No se puede eliminar. Dejaría '"+ev+"' sin acciones.","Aviso",JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }
            for (int i=0;i<accionesModel.size();i++) if (accionesModel.get(i).equals(sel)) accionesModel.remove(i);
            for (String ev : eventosModelToList()) {
                accionesPorEvento.get(ev).remove(sel);
            }
            String ev = lstEventos.getSelectedValue();
            if (ev != null) {
                asociadasModel.clear();
                for (String a : accionesPorEvento.get(ev)) asociadasModel.addElement(a);
            }
        });

        return p;
    }

    // ADMIN: Reglas
    private JPanel buildReglasPanel() {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(Color.WHITE);
        p.setBorder(new EmptyBorder(12,12,12,12));

        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 2));
        header.setOpaque(false);
        header.add(new JLabel("Reglas del sistema"));
        p.add(header, BorderLayout.NORTH);

        JPanel grid = new JPanel(new GridBagLayout());
        grid.setOpaque(false);
        p.add(grid, BorderLayout.CENTER);

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(8,8,8,8);
        c.anchor = GridBagConstraints.WEST;

        JCheckBox chkDrone = new JCheckBox("Regla 1: batería de drone mínima (%)");
        JSpinner  spDrone  = new JSpinner(new SpinnerNumberModel(reglas.droneMin, 0, 100, 1));
        chkDrone.setSelected(reglas.droneActiva);
        spDrone.setEnabled(reglas.droneActiva);

        JCheckBox chkRobot = new JCheckBox("Regla 2: batería de robot mínima (%)");
        JSpinner  spRobot  = new JSpinner(new SpinnerNumberModel(reglas.robotMin, 0, 100, 1));
        chkRobot.setSelected(reglas.robotActiva);
        spRobot.setEnabled(reglas.robotActiva);

        JButton btnGuardar = smallPill("Guardar");

        chkDrone.addActionListener(e -> spDrone.setEnabled(chkDrone.isSelected()));
        chkRobot.addActionListener(e -> spRobot.setEnabled(chkRobot.isSelected()));

        c.gridx=0; c.gridy=0; grid.add(chkDrone, c);
        c.gridx=1; c.gridy=0; grid.add(spDrone, c);
        c.gridx=0; c.gridy=1; grid.add(chkRobot, c);
        c.gridx=1; c.gridy=1; grid.add(spRobot, c);
        c.gridx=0; c.gridy=2; c.gridwidth=2; grid.add(btnGuardar, c);

        btnGuardar.addActionListener(e -> {
            reglas.droneActiva = chkDrone.isSelected();
            reglas.robotActiva = chkRobot.isSelected();
            reglas.droneMin   = (Integer) spDrone.getValue();
            reglas.robotMin   = (Integer) spRobot.getValue();
            JOptionPane.showMessageDialog(p,"Reglas actualizadas","OK",JOptionPane.INFORMATION_MESSAGE);
        });

        return p;
    }

    // Consejo: datos iniciales
    private void initConsejoDatos() {
        accionesModel.addElement("Contactar a los bomberos");
        accionesModel.addElement("Contactar a oficiales de tránsito");
        accionesModel.addElement("Llamar al 911");
        accionesModel.addElement("Convocar ambulancias");

        for (String e : EVENTOS_BASE) eventosModel.addElement(e);
        for (String e : EVENTOS_BASE) {
            LinkedHashSet<String> s = new LinkedHashSet<>();
            s.add("Llamar al 911"); // base para no quedar vacío
            accionesPorEvento.put(e, s);
        }
    }

    // helpers Consejo
    private JScrollPane spTitled(JList<String> list, String title) {
        JScrollPane sp = new JScrollPane(list);
        sp.setBorder(BorderFactory.createTitledBorder(title));
        return sp;
    }
    private JPanel wrapList(JComponent c){ JPanel p=new JPanel(new BorderLayout()); p.setOpaque(false); p.add(c); return p; }
    private boolean contains(DefaultListModel<String> m, String x){ for(int i=0;i<m.size();i++) if(m.get(i).equals(x)) return true; return false; }
    private List<String> eventosModelToList(){ List<String> l=new ArrayList<>(); for(int i=0;i<eventosModel.size();i++) l.add(eventosModel.get(i)); return l; }

    // placeholder blanco
    private JPanel buildWhitePlaceholder(String title) {
        JPanel p = new JPanel(new GridBagLayout());
        p.setBackground(Color.WHITE);
        JLabel l = new JLabel(title + " (pendiente)");
        l.setFont(l.getFont().deriveFont(Font.BOLD, 24f));
        l.setForeground(TITLE_DARK);
        p.add(l);
        return p;
    }

    // botones laterales
    private JButton sideButton(String text) {
        JButton b = new JButton(text) {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.WHITE);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                g2.setColor(PRIMARY);
                g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 10, 10);
                super.paintComponent(g);
                g2.dispose();
            }
        };
        b.setOpaque(false);
        b.setContentAreaFilled(false);
        b.setBorder(new EmptyBorder(10, 16, 10, 16));
        b.setForeground(TITLE_DARK);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.setAlignmentX(Component.LEFT_ALIGNMENT);
        return b;
    }

    // pill buttons
    private JButton smallPill(String text) {
        JButton b = new JButton(text) {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(NAV_BTN_BG);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 18, 18);
                super.paintComponent(g);
                g2.dispose();
            }
        };
        b.setForeground(Color.WHITE);
        b.setBorderPainted(false);
        b.setContentAreaFilled(false);
        b.setFocusPainted(false);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.setPreferredSize(new Dimension(100, 28));
        return b;
    }

    // tabla estilizada
    private JTable styledTable(AbstractTableModel model) {
        JTable t = new JTable(model);
        t.setRowHeight(26);
        t.setGridColor(PRIMARY.darker());
        t.setShowHorizontalLines(true);
        t.setShowVerticalLines(true);
        t.getTableHeader().setBackground(PRIMARY.brighter());
        t.getTableHeader().setForeground(TITLE_DARK);
        t.getTableHeader().setFont(t.getTableHeader().getFont().deriveFont(Font.BOLD, 12f));
        return t;
    }

    // generación de datos
    private void genEdificios(int n) {
        edificios.clear();
        nextEdifId = 1;
        for (int i = 0; i < n; i++) {
            Edificio e = new Edificio();
            e.id = nextEdifId++;
            e.nombre = "Edificio " + (char)('A' + i);
            e.calle = "Calle " + (1 + rnd.nextInt(50));
            e.avenida = "Avenida " + (1 + rnd.nextInt(50));
            e.capacidadMax = 50 + rnd.nextInt(151);
            edificios.add(e);
        }
    }
    private void genEstaciones(int n) {
        estaciones.clear();
        nextEstId = 1;
        for (int i = 0; i < n; i++) {
            Estacion s = new Estacion();
            s.id = nextEstId++;
            s.descripcion = "Estación " + s.id;
            s.calle = "Calle " + (1 + rnd.nextInt(50));
            s.avenida = "Avenida " + (1 + rnd.nextInt(50));
            s.capacidadMax = 2 + rnd.nextInt(5);
            s.estado = Estado.DISPONIBLE;
            estaciones.add(s);
        }
    }

    // entidades y modelos
    private static class Edificio {
        int id; String nombre; String calle; String avenida; int capacidadMax;
    }
    private static class Estacion {
        int id; String descripcion; String calle; String avenida; int capacidadMax; Estado estado;
    }
    private enum Estado { DISPONIBLE, EN_MANTENIMIENTO, MAL_ESTADO }

    private static class EdificioTableModel extends AbstractTableModel {
        private final List<Edificio> data;
        private final String[] cols = {"ID","Nombre","Calle","Avenida","Capacidad Máxima"};
        EdificioTableModel(List<Edificio> d){ this.data = d; }
        @Override public int getRowCount(){ return data.size(); }
        @Override public int getColumnCount(){ return cols.length; }
        @Override public String getColumnName(int c){ return cols[c]; }
        @Override public Object getValueAt(int r, int c){
            Edificio e = data.get(r);
            return switch (c){
                case 0 -> e.id;
                case 1 -> e.nombre;
                case 2 -> e.calle;
                case 3 -> e.avenida;
                case 4 -> e.capacidadMax;
                default -> "";
            };
        }
    }
    private static class EstacionTableModel extends AbstractTableModel {
        private final List<Estacion> data;
        private final String[] cols = {"ID","Descripción","Calle","Avenida","Capacidad Máxima","Estado"};
        EstacionTableModel(List<Estacion> d){ this.data = d; }
        @Override public int getRowCount(){ return data.size(); }
        @Override public int getColumnCount(){ return cols.length; }
        @Override public String getColumnName(int c){ return cols[c]; }
        @Override public Object getValueAt(int r, int c){
            Estacion s = data.get(r);
            return switch (c){
                case 0 -> s.id;
                case 1 -> s.descripcion;
                case 2 -> s.calle;
                case 3 -> s.avenida;
                case 4 -> s.capacidadMax;
                case 5 -> s.estado;
                default -> "";
            };
        }
        @Override public boolean isCellEditable(int r,int c){ return false; }
    }

    // reglas
    private static class Reglas {
        int droneMin; boolean droneActiva;
        int robotMin; boolean robotActiva;
        Reglas(int dMin, boolean dAct, int rMin, boolean rAct){
            this.droneMin=dMin; this.droneActiva=dAct; this.robotMin=rMin; this.robotActiva=rAct;
        }
    }

    // util carga imagen
    private static Image load(String path) {
        try {
            java.net.URL url = NeoUrbe.class.getResource(path);
            if (url != null) return new ImageIcon(url).getImage();
            return new ImageIcon(path).getImage();
        } catch (Exception e) { return null; }
    }

    // util ajuste proporcional
    private static Rectangle fitInside(int sw, int sh, int bw, int bh) {
        double r = Math.min(bw / (double) sw, bh / (double) sh);
        int w = (int) Math.round(sw * r);
        int h = (int) Math.round(sh * r);
        int x = (bw - w) / 2;
        int y = (bh - h) / 2;
        return new Rectangle(x, y, w, h);
    }

    // icono navbar
    private static class ImageBadge extends JComponent {
        private final Image img; private final int bw, bh;
        ImageBadge(Image img, int bw, int bh) { this.img = img; this.bw = bw; this.bh = bh; setPreferredSize(new Dimension(bw, bh)); }
        @Override protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            if (img != null) {
                g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                Rectangle r = fitInside(img.getWidth(this), img.getHeight(this), bw, bh);
                g2.drawImage(img, r.x, r.y, r.width, r.height, this);
            } else {
                g2.setColor(Color.decode("#1AA6E8"));
                g2.fillRoundRect(4, 6, bw - 8, bh - 12, 8, 8);
                g2.setColor(Color.WHITE);
                g2.fillRect(10, bh - 20, 8, 12);
                g2.fillRect(22, bh - 24, 8, 16);
                g2.fillRect(34, bh - 28, 8, 20);
            }
            g2.dispose();
        }
    }

    // logo hero
    private static class HeroImage extends JComponent {
        private final Image img;
        private final int prefW, prefH;
        HeroImage(Image img, int prefW, int prefH) { this.img = img; this.prefW = prefW; this.prefH = prefH; setPreferredSize(new Dimension(prefW, prefH)); }
        @Override protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            if (img != null) {
                g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                Rectangle r = fitInside(img.getWidth(this), img.getHeight(this), getWidth(), getHeight());
                g2.drawImage(img, r.x, r.y, r.width, r.height, this);
            } else {
                g2.setColor(new Color(228, 249, 255));
                g2.fillRect(24, getHeight() - 12, getWidth() - 48, 4);
            }
            g2.dispose();
        }
    }
}
