import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.event.*;

public class ClawBoxFrame extends JFrame {

    // Database
    Connection conn;

    // ========== Tab Komponenti ==========
    int id = -1;
    int idkategoriya = -1;
    int iddostavchik = -1;

    JTextField tfName = new JTextField(15);
    JComboBox<String> comboKategoriya = new JComboBox<String>();
    JComboBox<String> comboDostavchik = new JComboBox<String>();
    JTextField tfOpis = new JTextField(15);
    JTextField tfKol = new JTextField(15);
    JTextField tfCena = new JTextField(15);

    JButton btnAddKomponent = new JButton("\u0414\u043E\u0431\u0430\u0432\u0438");
    JButton btnDeleteKomponent = new JButton("\u0418\u0437\u0442\u0440\u0438\u0439");
    JButton btnUpdateKomponent = new JButton("\u041F\u0440\u043E\u043C\u0435\u043D\u0438");

    JTable tableKomponenti = new JTable();
    JScrollPane spKomponenti = new JScrollPane(tableKomponenti);

    // ========== Tab Kategorii ==========
    int idKat = -1;

    JTextField tfKatName = new JTextField(15);

    JButton btnAddKat = new JButton("\u0414\u043E\u0431\u0430\u0432\u0438");
    JButton btnDeleteKat = new JButton("\u0418\u0437\u0442\u0440\u0438\u0439");
    JButton btnUpdateKat = new JButton("\u041F\u0440\u043E\u043C\u0435\u043D\u0438");

    JTable tableKategorii = new JTable();
    JScrollPane spKategorii = new JScrollPane(tableKategorii);

    // ========== Tab Dostavchici ==========
    int idDost = -1;

    JTextField tfDostName = new JTextField(15);
    JTextField tfDostAdres = new JTextField(15);

    JButton btnAddDost = new JButton("\u0414\u043E\u0431\u0430\u0432\u0438");
    JButton btnDeleteDost = new JButton("\u0418\u0437\u0442\u0440\u0438\u0439");
    JButton btnUpdateDost = new JButton("\u041F\u0440\u043E\u043C\u0435\u043D\u0438");

    JTable tableDostavchici = new JTable();
    JScrollPane spDostavchici = new JScrollPane(tableDostavchici);

    // ========== Tab Klienti ==========
    int idKlient = -1;

    JTextField tfKlientFname = new JTextField(15);
    JTextField tfKlientLname = new JTextField(15);
    JTextField tfKlientTelefon = new JTextField(15);

    JButton btnAddKlient = new JButton("\u0414\u043E\u0431\u0430\u0432\u0438");
    JButton btnDeleteKlient = new JButton("\u0418\u0437\u0442\u0440\u0438\u0439");
    JButton btnUpdateKlient = new JButton("\u041F\u0440\u043E\u043C\u0435\u043D\u0438");

    JTable tableKlienti = new JTable();
    JScrollPane spKlienti = new JScrollPane(tableKlienti);

    // ========== Tab Poruchki ==========
    int idPoruchka = -1;
    int idPorKomponent = -1;
    int idPorKlient = -1;

    JComboBox<String> comboPorKomponent = new JComboBox<String>();
    JComboBox<String> comboPorKlient = new JComboBox<String>();
    JTextField tfPorKol = new JTextField(15);
    JTextField tfPorDataPr = new JTextField(15);
    JTextField tfPorDataIz = new JTextField(15);

    JButton btnAddPoruchka = new JButton("\u0414\u043E\u0431\u0430\u0432\u0438");
    JButton btnDeletePoruchka = new JButton("\u0418\u0437\u0442\u0440\u0438\u0439");
    JButton btnUpdatePoruchka = new JButton("\u041F\u0440\u043E\u043C\u0435\u043D\u0438");

    JTable tablePoruchki = new JTable();
    JScrollPane spPoruchki = new JScrollPane(tablePoruchki);

    // ========== Tab Spravka ==========
    JComboBox<String> comboSpravkaKat = new JComboBox<String>();
    JComboBox<String> comboSpravkaDost = new JComboBox<String>();
    JTextField tfSpravkaCena = new JTextField(15);

    JButton btnSearch = new JButton("\u0422\u044A\u0440\u0441\u0438");

    JTable tableSpravka = new JTable();
    JScrollPane spSpravka = new JScrollPane(tableSpravka);

    int idSpravkaKat = -1;
    int idSpravkaDost = -1;

    // ========== Tabbed Pane ==========
    JTabbedPane tabbedPane = new JTabbedPane();

    // ========== Constructor ==========
    public ClawBoxFrame() {
        super("ClawBox - \u0423\u043F\u0440\u0430\u0432\u043B\u0435\u043D\u0438\u0435 \u043D\u0430 \u043A\u043E\u043C\u043F\u043E\u043D\u0435\u043D\u0442\u0438");
        conn = MyDBConnection.getConnection();

        // ===== Tab Komponenti =====
        JPanel panelKomponenti = new JPanel(new GridLayout(3, 1));

        JPanel formKomp = new JPanel(new GridLayout(6, 2));
        formKomp.add(new JLabel("\u041D\u0430\u0438\u043C\u0435\u043D\u043E\u0432\u0430\u043D\u0438\u0435:"));
        formKomp.add(tfName);
        formKomp.add(new JLabel("\u041A\u0430\u0442\u0435\u0433\u043E\u0440\u0438\u044F:"));
        formKomp.add(comboKategoriya);
        formKomp.add(new JLabel("\u0414\u043E\u0441\u0442\u0430\u0432\u0447\u0438\u043A:"));
        formKomp.add(comboDostavchik);
        formKomp.add(new JLabel("\u041E\u043F\u0438\u0441\u0430\u043D\u0438\u0435:"));
        formKomp.add(tfOpis);
        formKomp.add(new JLabel("\u041A\u043E\u043B\u0438\u0447\u0435\u0441\u0442\u0432\u043E:"));
        formKomp.add(tfKol);
        formKomp.add(new JLabel("\u0426\u0435\u043D\u0430:"));
        formKomp.add(tfCena);

        JPanel btnsKomp = new JPanel();
        btnsKomp.add(btnAddKomponent);
        btnsKomp.add(btnDeleteKomponent);
        btnsKomp.add(btnUpdateKomponent);

        panelKomponenti.add(formKomp);
        panelKomponenti.add(btnsKomp);
        panelKomponenti.add(spKomponenti);

        btnAddKomponent.addActionListener(new AddKomponentDB());
        btnDeleteKomponent.addActionListener(new DeleteKomponentDB());
        btnUpdateKomponent.addActionListener(new UpdateKomponentDB());
        tableKomponenti.addMouseListener(new MouseActionKomponentiTable());

        comboKategoriya.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    refreshIdKategoriya();
                }
            }
        });

        comboDostavchik.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    refreshIdDostavchik();
                }
            }
        });

        // ===== Tab Kategorii =====
        JPanel panelKategorii = new JPanel(new GridLayout(3, 1));

        JPanel formKat = new JPanel(new GridLayout(1, 2));
        formKat.add(new JLabel("\u041D\u0430\u0438\u043C\u0435\u043D\u043E\u0432\u0430\u043D\u0438\u0435:"));
        formKat.add(tfKatName);

        JPanel btnsKat = new JPanel();
        btnsKat.add(btnAddKat);
        btnsKat.add(btnDeleteKat);
        btnsKat.add(btnUpdateKat);

        panelKategorii.add(formKat);
        panelKategorii.add(btnsKat);
        panelKategorii.add(spKategorii);

        btnAddKat.addActionListener(new AddKategoriyaDB());
        btnDeleteKat.addActionListener(new DeleteKategoriyaDB());
        btnUpdateKat.addActionListener(new UpdateKategoriyaDB());
        tableKategorii.addMouseListener(new MouseActionKategoriiTable());

        // ===== Tab Dostavchici =====
        JPanel panelDostavchici = new JPanel(new GridLayout(3, 1));

        JPanel formDost = new JPanel(new GridLayout(2, 2));
        formDost.add(new JLabel("\u041D\u0430\u0438\u043C\u0435\u043D\u043E\u0432\u0430\u043D\u0438\u0435:"));
        formDost.add(tfDostName);
        formDost.add(new JLabel("\u0410\u0434\u0440\u0435\u0441:"));
        formDost.add(tfDostAdres);

        JPanel btnsDost = new JPanel();
        btnsDost.add(btnAddDost);
        btnsDost.add(btnDeleteDost);
        btnsDost.add(btnUpdateDost);

        panelDostavchici.add(formDost);
        panelDostavchici.add(btnsDost);
        panelDostavchici.add(spDostavchici);

        btnAddDost.addActionListener(new AddDostavchikDB());
        btnDeleteDost.addActionListener(new DeleteDostavchikDB());
        btnUpdateDost.addActionListener(new UpdateDostavchikDB());
        tableDostavchici.addMouseListener(new MouseActionDostavchiciTable());

        // ===== Tab Klienti =====
        JPanel panelKlienti = new JPanel(new GridLayout(3, 1));

        JPanel formKlient = new JPanel(new GridLayout(3, 2));
        formKlient.add(new JLabel("\u0418\u043C\u0435:"));
        formKlient.add(tfKlientFname);
        formKlient.add(new JLabel("\u0424\u0430\u043C\u0438\u043B\u0438\u044F:"));
        formKlient.add(tfKlientLname);
        formKlient.add(new JLabel("\u0422\u0435\u043B\u0435\u0444\u043E\u043D:"));
        formKlient.add(tfKlientTelefon);

        JPanel btnsKlient = new JPanel();
        btnsKlient.add(btnAddKlient);
        btnsKlient.add(btnDeleteKlient);
        btnsKlient.add(btnUpdateKlient);

        panelKlienti.add(formKlient);
        panelKlienti.add(btnsKlient);
        panelKlienti.add(spKlienti);

        btnAddKlient.addActionListener(new AddKlientDB());
        btnDeleteKlient.addActionListener(new DeleteKlientDB());
        btnUpdateKlient.addActionListener(new UpdateKlientDB());
        tableKlienti.addMouseListener(new MouseActionKlientiTable());

        // ===== Tab Poruchki =====
        JPanel panelPoruchki = new JPanel(new GridLayout(3, 1));

        JPanel formPor = new JPanel(new GridLayout(5, 2));
        formPor.add(new JLabel("\u041A\u043E\u043C\u043F\u043E\u043D\u0435\u043D\u0442:"));
        formPor.add(comboPorKomponent);
        formPor.add(new JLabel("\u041A\u043B\u0438\u0435\u043D\u0442:"));
        formPor.add(comboPorKlient);
        formPor.add(new JLabel("\u041A\u043E\u043B\u0438\u0447\u0435\u0441\u0442\u0432\u043E:"));
        formPor.add(tfPorKol);
        formPor.add(new JLabel("\u0414\u0430\u0442\u0430 \u043F\u0440\u0438\u0435\u0442\u0430:"));
        formPor.add(tfPorDataPr);
        formPor.add(new JLabel("\u0414\u0430\u0442\u0430 \u0438\u0437\u043F\u044A\u043B\u043D\u0435\u043D\u0430:"));
        formPor.add(tfPorDataIz);

        JPanel btnsPor = new JPanel();
        btnsPor.add(btnAddPoruchka);
        btnsPor.add(btnDeletePoruchka);
        btnsPor.add(btnUpdatePoruchka);

        panelPoruchki.add(formPor);
        panelPoruchki.add(btnsPor);
        panelPoruchki.add(spPoruchki);

        btnAddPoruchka.addActionListener(new AddPoruchkaDB());
        btnDeletePoruchka.addActionListener(new DeletePoruchkaDB());
        btnUpdatePoruchka.addActionListener(new UpdatePoruchkaDB());
        tablePoruchki.addMouseListener(new MouseActionPoruchkiTable());

        comboPorKomponent.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    refreshIdPorKomponent();
                }
            }
        });

        comboPorKlient.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    refreshIdPorKlient();
                }
            }
        });

        // ===== Tab Spravka =====
        JPanel panelSpravka = new JPanel(new GridLayout(3, 1));

        JPanel formSpravka = new JPanel(new GridLayout(3, 2));
        formSpravka.add(new JLabel("\u041A\u0430\u0442\u0435\u0433\u043E\u0440\u0438\u044F:"));
        formSpravka.add(comboSpravkaKat);
        formSpravka.add(new JLabel("\u0414\u043E\u0441\u0442\u0430\u0432\u0447\u0438\u043A:"));
        formSpravka.add(comboSpravkaDost);
        formSpravka.add(new JLabel("\u041C\u0430\u043A\u0441\u0438\u043C\u0430\u043B\u043D\u0430 \u0446\u0435\u043D\u0430:"));
        formSpravka.add(tfSpravkaCena);

        JPanel btnsSpravka = new JPanel();
        btnsSpravka.add(btnSearch);

        panelSpravka.add(formSpravka);
        panelSpravka.add(btnsSpravka);
        panelSpravka.add(spSpravka);

        btnSearch.addActionListener(new SearchSpravkaDB());

        comboSpravkaKat.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    refreshIdSpravkaKat();
                }
            }
        });

        comboSpravkaDost.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    refreshIdSpravkaDost();
                }
            }
        });

        // ===== Add tabs =====
        tabbedPane.addTab("\u041A\u043E\u043C\u043F\u043E\u043D\u0435\u043D\u0442\u0438", panelKomponenti);
        tabbedPane.addTab("\u041A\u0430\u0442\u0435\u0433\u043E\u0440\u0438\u0438", panelKategorii);
        tabbedPane.addTab("\u0414\u043E\u0441\u0442\u0430\u0432\u0447\u0438\u0446\u0438", panelDostavchici);
        tabbedPane.addTab("\u041A\u043B\u0438\u0435\u043D\u0442\u0438", panelKlienti);
        tabbedPane.addTab("\u041F\u043E\u0440\u044A\u0447\u043A\u0438", panelPoruchki);
        tabbedPane.addTab("\u0421\u043F\u0440\u0430\u0432\u043A\u0430", panelSpravka);

        add(tabbedPane);

        // Refresh all data
        refreshComboKategorii();
        refreshComboDostavchik();
        refreshTableKomponenti();
        refreshTableKategorii();
        refreshTableDostavchici();
        refreshTableKlienti();
        refreshComboPorKomponent();
        refreshComboPorKlient();
        refreshTablePoruchki();
        refreshComboSpravkaKat();
        refreshComboSpravkaDost();

        setSize(650, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // ==========================================
    // REFRESH COMBOS
    // ==========================================

    public void refreshComboKategorii() {
        try {
            idkategoriya = -1;
            comboKategoriya.removeAllItems();
            String sql = "SELECT ID, NAME FROM KATEGORII ORDER BY ID";
            try (PreparedStatement st = conn.prepareStatement(sql);
                 ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    idkategoriya = Integer.parseInt(rs.getObject(1).toString());
                    do {
                        comboKategoriya.addItem(rs.getObject(2).toString());
                    } while (rs.next());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void refreshComboDostavchik() {
        try {
            iddostavchik = -1;
            comboDostavchik.removeAllItems();
            String sql = "SELECT ID, NAME FROM DOSTAVCHICI ORDER BY ID";
            try (PreparedStatement st = conn.prepareStatement(sql);
                 ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    iddostavchik = Integer.parseInt(rs.getObject(1).toString());
                    do {
                        comboDostavchik.addItem(rs.getObject(2).toString());
                    } while (rs.next());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void refreshIdKategoriya() {
        try {
            int selectedIndex = comboKategoriya.getSelectedIndex();
            if (selectedIndex < 0) return;
            String sql = "SELECT ID, NAME FROM KATEGORII ORDER BY ID";
            try (PreparedStatement st = conn.prepareStatement(sql);
                 ResultSet rs = st.executeQuery()) {
                int i = 0;
                while (rs.next()) {
                    if (i == selectedIndex) {
                        idkategoriya = Integer.parseInt(rs.getObject(1).toString());
                        break;
                    }
                    i++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void refreshIdDostavchik() {
        try {
            int selectedIndex = comboDostavchik.getSelectedIndex();
            if (selectedIndex < 0) return;
            String sql = "SELECT ID, NAME FROM DOSTAVCHICI ORDER BY ID";
            try (PreparedStatement st = conn.prepareStatement(sql);
                 ResultSet rs = st.executeQuery()) {
                int i = 0;
                while (rs.next()) {
                    if (i == selectedIndex) {
                        iddostavchik = Integer.parseInt(rs.getObject(1).toString());
                        break;
                    }
                    i++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void refreshComboPorKomponent() {
        try {
            idPorKomponent = -1;
            comboPorKomponent.removeAllItems();
            String sql = "SELECT ID, NAME FROM KOMPONENTI ORDER BY ID";
            try (PreparedStatement st = conn.prepareStatement(sql);
                 ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    idPorKomponent = Integer.parseInt(rs.getObject(1).toString());
                    do {
                        comboPorKomponent.addItem(rs.getObject(2).toString());
                    } while (rs.next());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void refreshComboPorKlient() {
        try {
            idPorKlient = -1;
            comboPorKlient.removeAllItems();
            String sql = "SELECT ID, FNAME, LNAME FROM KLIENTI ORDER BY ID";
            try (PreparedStatement st = conn.prepareStatement(sql);
                 ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    idPorKlient = Integer.parseInt(rs.getObject(1).toString());
                    do {
                        comboPorKlient.addItem(rs.getObject(2).toString() + " " + rs.getObject(3).toString());
                    } while (rs.next());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void refreshIdPorKomponent() {
        try {
            int selectedIndex = comboPorKomponent.getSelectedIndex();
            if (selectedIndex < 0) return;
            String sql = "SELECT ID, NAME FROM KOMPONENTI ORDER BY ID";
            try (PreparedStatement st = conn.prepareStatement(sql);
                 ResultSet rs = st.executeQuery()) {
                int i = 0;
                while (rs.next()) {
                    if (i == selectedIndex) {
                        idPorKomponent = Integer.parseInt(rs.getObject(1).toString());
                        break;
                    }
                    i++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void refreshIdPorKlient() {
        try {
            int selectedIndex = comboPorKlient.getSelectedIndex();
            if (selectedIndex < 0) return;
            String sql = "SELECT ID, FNAME FROM KLIENTI ORDER BY ID";
            try (PreparedStatement st = conn.prepareStatement(sql);
                 ResultSet rs = st.executeQuery()) {
                int i = 0;
                while (rs.next()) {
                    if (i == selectedIndex) {
                        idPorKlient = Integer.parseInt(rs.getObject(1).toString());
                        break;
                    }
                    i++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void refreshComboSpravkaKat() {
        try {
            idSpravkaKat = -1;
            comboSpravkaKat.removeAllItems();
            String sql = "SELECT ID, NAME FROM KATEGORII ORDER BY ID";
            try (PreparedStatement st = conn.prepareStatement(sql);
                 ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    idSpravkaKat = Integer.parseInt(rs.getObject(1).toString());
                    do {
                        comboSpravkaKat.addItem(rs.getObject(2).toString());
                    } while (rs.next());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void refreshComboSpravkaDost() {
        try {
            idSpravkaDost = -1;
            comboSpravkaDost.removeAllItems();
            String sql = "SELECT ID, NAME FROM DOSTAVCHICI ORDER BY ID";
            try (PreparedStatement st = conn.prepareStatement(sql);
                 ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    idSpravkaDost = Integer.parseInt(rs.getObject(1).toString());
                    do {
                        comboSpravkaDost.addItem(rs.getObject(2).toString());
                    } while (rs.next());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void refreshIdSpravkaKat() {
        try {
            int selectedIndex = comboSpravkaKat.getSelectedIndex();
            if (selectedIndex < 0) return;
            String sql = "SELECT ID, NAME FROM KATEGORII ORDER BY ID";
            try (PreparedStatement st = conn.prepareStatement(sql);
                 ResultSet rs = st.executeQuery()) {
                int i = 0;
                while (rs.next()) {
                    if (i == selectedIndex) {
                        idSpravkaKat = Integer.parseInt(rs.getObject(1).toString());
                        break;
                    }
                    i++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void refreshIdSpravkaDost() {
        try {
            int selectedIndex = comboSpravkaDost.getSelectedIndex();
            if (selectedIndex < 0) return;
            String sql = "SELECT ID, NAME FROM DOSTAVCHICI ORDER BY ID";
            try (PreparedStatement st = conn.prepareStatement(sql);
                 ResultSet rs = st.executeQuery()) {
                int i = 0;
                while (rs.next()) {
                    if (i == selectedIndex) {
                        idSpravkaDost = Integer.parseInt(rs.getObject(1).toString());
                        break;
                    }
                    i++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ==========================================
    // REFRESH TABLES
    // ==========================================

    public void refreshTableKomponenti() {
        try {
            String sql = "SELECT KOMPONENTI.ID, KOMPONENTI.NAME, KATEGORII.NAME, KOMPONENTI.IDKATEGORIYA, "
                + "DOSTAVCHICI.NAME, KOMPONENTI.IDDOSTAVCHIK, OPIS, KOL, CENA "
                + "FROM KOMPONENTI "
                + "LEFT JOIN KATEGORII ON KOMPONENTI.IDKATEGORIYA=KATEGORII.ID "
                + "LEFT JOIN DOSTAVCHICI ON KOMPONENTI.IDDOSTAVCHIK=DOSTAVCHICI.ID";
            try (PreparedStatement st = conn.prepareStatement(sql);
                 ResultSet rs = st.executeQuery()) {
                MyModel model = new MyModel(rs);
                tableKomponenti.setModel(model);
            }
            // Hide ID columns: 0, 3, 5
            tableKomponenti.getColumnModel().getColumn(0).setMinWidth(0);
            tableKomponenti.getColumnModel().getColumn(0).setMaxWidth(0);
            tableKomponenti.getColumnModel().getColumn(0).setWidth(0);
            tableKomponenti.getColumnModel().getColumn(3).setMinWidth(0);
            tableKomponenti.getColumnModel().getColumn(3).setMaxWidth(0);
            tableKomponenti.getColumnModel().getColumn(3).setWidth(0);
            tableKomponenti.getColumnModel().getColumn(5).setMinWidth(0);
            tableKomponenti.getColumnModel().getColumn(5).setMaxWidth(0);
            tableKomponenti.getColumnModel().getColumn(5).setWidth(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void refreshTableKategorii() {
        try {
            String sql = "SELECT * FROM KATEGORII";
            try (PreparedStatement st = conn.prepareStatement(sql);
                 ResultSet rs = st.executeQuery()) {
                MyModel model = new MyModel(rs);
                tableKategorii.setModel(model);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void refreshTableDostavchici() {
        try {
            String sql = "SELECT * FROM DOSTAVCHICI";
            try (PreparedStatement st = conn.prepareStatement(sql);
                 ResultSet rs = st.executeQuery()) {
                MyModel model = new MyModel(rs);
                tableDostavchici.setModel(model);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void refreshTableKlienti() {
        try {
            String sql = "SELECT * FROM KLIENTI";
            try (PreparedStatement st = conn.prepareStatement(sql);
                 ResultSet rs = st.executeQuery()) {
                MyModel model = new MyModel(rs);
                tableKlienti.setModel(model);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void refreshTablePoruchki() {
        try {
            String sql = "SELECT PORUCHKI.ID, KOMPONENTI.NAME, "
                + "CONCAT(KLIENTI.FNAME, ' ', KLIENTI.LNAME), "
                + "PORUCHKI.IDKOMPONENT, PORUCHKI.IDKLIENT, "
                + "PORUCHKI.KOL, PORUCHKI.DATAPR, PORUCHKI.DATAIZ "
                + "FROM PORUCHKI "
                + "LEFT JOIN KOMPONENTI ON PORUCHKI.IDKOMPONENT=KOMPONENTI.ID "
                + "LEFT JOIN KLIENTI ON PORUCHKI.IDKLIENT=KLIENTI.ID";
            try (PreparedStatement st = conn.prepareStatement(sql);
                 ResultSet rs = st.executeQuery()) {
                MyModel model = new MyModel(rs);
                tablePoruchki.setModel(model);
            }
            // Hide ID columns: 0, 3, 4
            tablePoruchki.getColumnModel().getColumn(0).setMinWidth(0);
            tablePoruchki.getColumnModel().getColumn(0).setMaxWidth(0);
            tablePoruchki.getColumnModel().getColumn(0).setWidth(0);
            tablePoruchki.getColumnModel().getColumn(3).setMinWidth(0);
            tablePoruchki.getColumnModel().getColumn(3).setMaxWidth(0);
            tablePoruchki.getColumnModel().getColumn(3).setWidth(0);
            tablePoruchki.getColumnModel().getColumn(4).setMinWidth(0);
            tablePoruchki.getColumnModel().getColumn(4).setMaxWidth(0);
            tablePoruchki.getColumnModel().getColumn(4).setWidth(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ==========================================
    // CLEAR FORMS
    // ==========================================

    public void clearFormKomponenti() {
        id = -1;
        tfName.setText("");
        tfOpis.setText("");
        tfKol.setText("");
        tfCena.setText("");
    }

    public void clearFormKategorii() {
        idKat = -1;
        tfKatName.setText("");
    }

    public void clearFormDostavchici() {
        idDost = -1;
        tfDostName.setText("");
        tfDostAdres.setText("");
    }

    public void clearFormKlienti() {
        idKlient = -1;
        tfKlientFname.setText("");
        tfKlientLname.setText("");
        tfKlientTelefon.setText("");
    }

    public void clearFormPoruchki() {
        idPoruchka = -1;
        tfPorKol.setText("");
        tfPorDataPr.setText("");
        tfPorDataIz.setText("");
    }

    // ==========================================
    // INNER CLASSES — Komponenti
    // ==========================================

    class AddKomponentDB implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                int kol;
                double cena;
                try {
                    kol = Integer.parseInt(tfKol.getText().trim());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(ClawBoxFrame.this,
                        "\u041A\u043E\u043B\u0438\u0447\u0435\u0441\u0442\u0432\u043E\u0442\u043E \u0442\u0440\u044F\u0431\u0432\u0430 \u0434\u0430 \u0435 \u0446\u044F\u043B\u043E \u0447\u0438\u0441\u043B\u043E!",
                        "\u0413\u0440\u0435\u0448\u043A\u0430", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (kol <= 0) {
                    JOptionPane.showMessageDialog(ClawBoxFrame.this,
                        "\u041A\u043E\u043B\u0438\u0447\u0435\u0441\u0442\u0432\u043E\u0442\u043E \u0442\u0440\u044F\u0431\u0432\u0430 \u0434\u0430 \u0435 \u043F\u043E\u043B\u043E\u0436\u0438\u0442\u0435\u043B\u043D\u043E!",
                        "\u0413\u0440\u0435\u0448\u043A\u0430", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    cena = Double.parseDouble(tfCena.getText().trim());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(ClawBoxFrame.this,
                        "\u0426\u0435\u043D\u0430\u0442\u0430 \u0442\u0440\u044F\u0431\u0432\u0430 \u0434\u0430 \u0435 \u0447\u0438\u0441\u043B\u043E!",
                        "\u0413\u0440\u0435\u0448\u043A\u0430", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (cena <= 0) {
                    JOptionPane.showMessageDialog(ClawBoxFrame.this,
                        "\u0426\u0435\u043D\u0430\u0442\u0430 \u0442\u0440\u044F\u0431\u0432\u0430 \u0434\u0430 \u0435 \u043F\u043E\u043B\u043E\u0436\u0438\u0442\u0435\u043B\u043D\u0430!",
                        "\u0413\u0440\u0435\u0448\u043A\u0430", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String sql = "INSERT INTO KOMPONENTI(NAME, IDKATEGORIYA, IDDOSTAVCHIK, OPIS, KOL, CENA) VALUES(?,?,?,?,?,?)";
                try (PreparedStatement st = conn.prepareStatement(sql)) {
                    st.setString(1, tfName.getText());
                    st.setInt(2, idkategoriya);
                    st.setInt(3, iddostavchik);
                    st.setString(4, tfOpis.getText());
                    st.setInt(5, kol);
                    st.setDouble(6, cena);
                    st.executeUpdate();
                }
                refreshTableKomponenti();
                refreshComboPorKomponent();
                clearFormKomponenti();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    class DeleteKomponentDB implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                if (id == -1) return;
                String sql = "DELETE FROM KOMPONENTI WHERE ID=?";
                try (PreparedStatement st = conn.prepareStatement(sql)) {
                    st.setInt(1, id);
                    st.executeUpdate();
                }
                refreshTableKomponenti();
                refreshComboPorKomponent();
                clearFormKomponenti();
            } catch (SQLException ex) {
                if ("23503".equals(ex.getSQLState())) {
                    JOptionPane.showMessageDialog(ClawBoxFrame.this,
                        "\u041D\u0435 \u043C\u043E\u0436\u0435 \u0434\u0430 \u0441\u0435 \u0438\u0437\u0442\u0440\u0438\u0435 - \u043A\u043E\u043C\u043F\u043E\u043D\u0435\u043D\u0442\u044A\u0442 \u0441\u0435 \u0438\u0437\u043F\u043E\u043B\u0437\u0432\u0430 \u0432 \u043F\u043E\u0440\u044A\u0447\u043A\u0438!",
                        "\u0413\u0440\u0435\u0448\u043A\u0430", JOptionPane.ERROR_MESSAGE);
                } else {
                    ex.printStackTrace();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    class UpdateKomponentDB implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                if (id == -1) return;
                int kol;
                double cena;
                try {
                    kol = Integer.parseInt(tfKol.getText().trim());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(ClawBoxFrame.this,
                        "\u041A\u043E\u043B\u0438\u0447\u0435\u0441\u0442\u0432\u043E\u0442\u043E \u0442\u0440\u044F\u0431\u0432\u0430 \u0434\u0430 \u0435 \u0446\u044F\u043B\u043E \u0447\u0438\u0441\u043B\u043E!",
                        "\u0413\u0440\u0435\u0448\u043A\u0430", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (kol <= 0) {
                    JOptionPane.showMessageDialog(ClawBoxFrame.this,
                        "\u041A\u043E\u043B\u0438\u0447\u0435\u0441\u0442\u0432\u043E\u0442\u043E \u0442\u0440\u044F\u0431\u0432\u0430 \u0434\u0430 \u0435 \u043F\u043E\u043B\u043E\u0436\u0438\u0442\u0435\u043B\u043D\u043E!",
                        "\u0413\u0440\u0435\u0448\u043A\u0430", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    cena = Double.parseDouble(tfCena.getText().trim());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(ClawBoxFrame.this,
                        "\u0426\u0435\u043D\u0430\u0442\u0430 \u0442\u0440\u044F\u0431\u0432\u0430 \u0434\u0430 \u0435 \u0447\u0438\u0441\u043B\u043E!",
                        "\u0413\u0440\u0435\u0448\u043A\u0430", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (cena <= 0) {
                    JOptionPane.showMessageDialog(ClawBoxFrame.this,
                        "\u0426\u0435\u043D\u0430\u0442\u0430 \u0442\u0440\u044F\u0431\u0432\u0430 \u0434\u0430 \u0435 \u043F\u043E\u043B\u043E\u0436\u0438\u0442\u0435\u043B\u043D\u0430!",
                        "\u0413\u0440\u0435\u0448\u043A\u0430", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String sql = "UPDATE KOMPONENTI SET NAME=?, IDKATEGORIYA=?, IDDOSTAVCHIK=?, OPIS=?, KOL=?, CENA=? WHERE ID=?";
                try (PreparedStatement st = conn.prepareStatement(sql)) {
                    st.setString(1, tfName.getText());
                    st.setInt(2, idkategoriya);
                    st.setInt(3, iddostavchik);
                    st.setString(4, tfOpis.getText());
                    st.setInt(5, kol);
                    st.setDouble(6, cena);
                    st.setInt(7, id);
                    st.executeUpdate();
                }
                refreshTableKomponenti();
                refreshComboPorKomponent();
                clearFormKomponenti();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    class MouseActionKomponentiTable implements MouseListener {
        public void mouseClicked(MouseEvent e) {
            int row = tableKomponenti.getSelectedRow();
            if (row >= 0) {
                id = Integer.parseInt(tableKomponenti.getValueAt(row, 0).toString());
                tfName.setText(tableKomponenti.getValueAt(row, 1).toString());
                // Set combo to matching category name
                String katName = tableKomponenti.getValueAt(row, 2).toString();
                comboKategoriya.setSelectedItem(katName);
                // Set combo to matching dostavchik name
                String dostName = tableKomponenti.getValueAt(row, 4).toString();
                comboDostavchik.setSelectedItem(dostName);
                tfOpis.setText(tableKomponenti.getValueAt(row, 6).toString());
                tfKol.setText(tableKomponenti.getValueAt(row, 7).toString());
                tfCena.setText(tableKomponenti.getValueAt(row, 8).toString());
            }
        }
        public void mousePressed(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
    }

    // ==========================================
    // INNER CLASSES — Kategorii
    // ==========================================

    class AddKategoriyaDB implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                String sql = "INSERT INTO KATEGORII(NAME) VALUES(?)";
                try (PreparedStatement st = conn.prepareStatement(sql)) {
                    st.setString(1, tfKatName.getText());
                    st.executeUpdate();
                }
                refreshTableKategorii();
                refreshComboKategorii();
                refreshComboSpravkaKat();
                clearFormKategorii();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    class DeleteKategoriyaDB implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                if (idKat == -1) return;
                String sql = "DELETE FROM KATEGORII WHERE ID=?";
                try (PreparedStatement st = conn.prepareStatement(sql)) {
                    st.setInt(1, idKat);
                    st.executeUpdate();
                }
                refreshTableKategorii();
                refreshComboKategorii();
                refreshComboSpravkaKat();
                clearFormKategorii();
            } catch (SQLException ex) {
                if ("23503".equals(ex.getSQLState())) {
                    JOptionPane.showMessageDialog(ClawBoxFrame.this,
                        "\u041D\u0435 \u043C\u043E\u0436\u0435 \u0434\u0430 \u0441\u0435 \u0438\u0437\u0442\u0440\u0438\u0435 - \u043A\u0430\u0442\u0435\u0433\u043E\u0440\u0438\u044F\u0442\u0430 \u0441\u0435 \u0438\u0437\u043F\u043E\u043B\u0437\u0432\u0430 \u043E\u0442 \u043A\u043E\u043C\u043F\u043E\u043D\u0435\u043D\u0442\u0438!",
                        "\u0413\u0440\u0435\u0448\u043A\u0430", JOptionPane.ERROR_MESSAGE);
                } else {
                    ex.printStackTrace();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    class UpdateKategoriyaDB implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                if (idKat == -1) return;
                String sql = "UPDATE KATEGORII SET NAME=? WHERE ID=?";
                try (PreparedStatement st = conn.prepareStatement(sql)) {
                    st.setString(1, tfKatName.getText());
                    st.setInt(2, idKat);
                    st.executeUpdate();
                }
                refreshTableKategorii();
                refreshComboKategorii();
                refreshComboSpravkaKat();
                clearFormKategorii();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    class MouseActionKategoriiTable implements MouseListener {
        public void mouseClicked(MouseEvent e) {
            int row = tableKategorii.getSelectedRow();
            if (row >= 0) {
                idKat = Integer.parseInt(tableKategorii.getValueAt(row, 0).toString());
                tfKatName.setText(tableKategorii.getValueAt(row, 1).toString());
            }
        }
        public void mousePressed(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
    }

    // ==========================================
    // INNER CLASSES — Dostavchici
    // ==========================================

    class AddDostavchikDB implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                String sql = "INSERT INTO DOSTAVCHICI(NAME, ADRES) VALUES(?,?)";
                try (PreparedStatement st = conn.prepareStatement(sql)) {
                    st.setString(1, tfDostName.getText());
                    st.setString(2, tfDostAdres.getText());
                    st.executeUpdate();
                }
                refreshTableDostavchici();
                refreshComboDostavchik();
                refreshComboSpravkaDost();
                clearFormDostavchici();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    class DeleteDostavchikDB implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                if (idDost == -1) return;
                String sql = "DELETE FROM DOSTAVCHICI WHERE ID=?";
                try (PreparedStatement st = conn.prepareStatement(sql)) {
                    st.setInt(1, idDost);
                    st.executeUpdate();
                }
                refreshTableDostavchici();
                refreshComboDostavchik();
                refreshComboSpravkaDost();
                clearFormDostavchici();
            } catch (SQLException ex) {
                if ("23503".equals(ex.getSQLState())) {
                    JOptionPane.showMessageDialog(ClawBoxFrame.this,
                        "\u041D\u0435 \u043C\u043E\u0436\u0435 \u0434\u0430 \u0441\u0435 \u0438\u0437\u0442\u0440\u0438\u0435 - \u0434\u043E\u0441\u0442\u0430\u0432\u0447\u0438\u043A\u044A\u0442 \u0441\u0435 \u0438\u0437\u043F\u043E\u043B\u0437\u0432\u0430 \u043E\u0442 \u043A\u043E\u043C\u043F\u043E\u043D\u0435\u043D\u0442\u0438!",
                        "\u0413\u0440\u0435\u0448\u043A\u0430", JOptionPane.ERROR_MESSAGE);
                } else {
                    ex.printStackTrace();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    class UpdateDostavchikDB implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                if (idDost == -1) return;
                String sql = "UPDATE DOSTAVCHICI SET NAME=?, ADRES=? WHERE ID=?";
                try (PreparedStatement st = conn.prepareStatement(sql)) {
                    st.setString(1, tfDostName.getText());
                    st.setString(2, tfDostAdres.getText());
                    st.setInt(3, idDost);
                    st.executeUpdate();
                }
                refreshTableDostavchici();
                refreshComboDostavchik();
                refreshComboSpravkaDost();
                clearFormDostavchici();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    class MouseActionDostavchiciTable implements MouseListener {
        public void mouseClicked(MouseEvent e) {
            int row = tableDostavchici.getSelectedRow();
            if (row >= 0) {
                idDost = Integer.parseInt(tableDostavchici.getValueAt(row, 0).toString());
                tfDostName.setText(tableDostavchici.getValueAt(row, 1).toString());
                tfDostAdres.setText(tableDostavchici.getValueAt(row, 2).toString());
            }
        }
        public void mousePressed(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
    }

    // ==========================================
    // INNER CLASSES — Klienti
    // ==========================================

    class AddKlientDB implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                String sql = "INSERT INTO KLIENTI(FNAME, LNAME, TELEFON) VALUES(?,?,?)";
                try (PreparedStatement st = conn.prepareStatement(sql)) {
                    st.setString(1, tfKlientFname.getText());
                    st.setString(2, tfKlientLname.getText());
                    st.setString(3, tfKlientTelefon.getText());
                    st.executeUpdate();
                }
                refreshTableKlienti();
                refreshComboPorKlient();
                clearFormKlienti();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    class DeleteKlientDB implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                if (idKlient == -1) return;
                String sql = "DELETE FROM KLIENTI WHERE ID=?";
                try (PreparedStatement st = conn.prepareStatement(sql)) {
                    st.setInt(1, idKlient);
                    st.executeUpdate();
                }
                refreshTableKlienti();
                refreshComboPorKlient();
                clearFormKlienti();
            } catch (SQLException ex) {
                if ("23503".equals(ex.getSQLState())) {
                    JOptionPane.showMessageDialog(ClawBoxFrame.this,
                        "\u041D\u0435 \u043C\u043E\u0436\u0435 \u0434\u0430 \u0441\u0435 \u0438\u0437\u0442\u0440\u0438\u0435 - \u043A\u043B\u0438\u0435\u043D\u0442\u044A\u0442 \u0438\u043C\u0430 \u043F\u043E\u0440\u044A\u0447\u043A\u0438!",
                        "\u0413\u0440\u0435\u0448\u043A\u0430", JOptionPane.ERROR_MESSAGE);
                } else {
                    ex.printStackTrace();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    class UpdateKlientDB implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                if (idKlient == -1) return;
                String sql = "UPDATE KLIENTI SET FNAME=?, LNAME=?, TELEFON=? WHERE ID=?";
                try (PreparedStatement st = conn.prepareStatement(sql)) {
                    st.setString(1, tfKlientFname.getText());
                    st.setString(2, tfKlientLname.getText());
                    st.setString(3, tfKlientTelefon.getText());
                    st.setInt(4, idKlient);
                    st.executeUpdate();
                }
                refreshTableKlienti();
                refreshComboPorKlient();
                clearFormKlienti();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    class MouseActionKlientiTable implements MouseListener {
        public void mouseClicked(MouseEvent e) {
            int row = tableKlienti.getSelectedRow();
            if (row >= 0) {
                idKlient = Integer.parseInt(tableKlienti.getValueAt(row, 0).toString());
                tfKlientFname.setText(tableKlienti.getValueAt(row, 1).toString());
                tfKlientLname.setText(tableKlienti.getValueAt(row, 2).toString());
                tfKlientTelefon.setText(tableKlienti.getValueAt(row, 3).toString());
            }
        }
        public void mousePressed(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
    }

    // ==========================================
    // INNER CLASSES — Poruchki
    // ==========================================

    class AddPoruchkaDB implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                int kol;
                try {
                    kol = Integer.parseInt(tfPorKol.getText().trim());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(ClawBoxFrame.this,
                        "\u041A\u043E\u043B\u0438\u0447\u0435\u0441\u0442\u0432\u043E\u0442\u043E \u0442\u0440\u044F\u0431\u0432\u0430 \u0434\u0430 \u0435 \u0446\u044F\u043B\u043E \u0447\u0438\u0441\u043B\u043E!",
                        "\u0413\u0440\u0435\u0448\u043A\u0430", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (kol <= 0) {
                    JOptionPane.showMessageDialog(ClawBoxFrame.this,
                        "\u041A\u043E\u043B\u0438\u0447\u0435\u0441\u0442\u0432\u043E\u0442\u043E \u0442\u0440\u044F\u0431\u0432\u0430 \u0434\u0430 \u0435 \u043F\u043E\u043B\u043E\u0436\u0438\u0442\u0435\u043B\u043D\u043E!",
                        "\u0413\u0440\u0435\u0448\u043A\u0430", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                java.sql.Date dataPr;
                java.sql.Date dataIz;
                try {
                    dataPr = java.sql.Date.valueOf(tfPorDataPr.getText().trim());
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(ClawBoxFrame.this,
                        "\u0414\u0430\u0442\u0430 \u043F\u0440\u0438\u0435\u0442\u0430 \u0442\u0440\u044F\u0431\u0432\u0430 \u0434\u0430 \u0435 \u0432\u044A\u0432 \u0444\u043E\u0440\u043C\u0430\u0442 YYYY-MM-DD!",
                        "\u0413\u0440\u0435\u0448\u043A\u0430", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    dataIz = java.sql.Date.valueOf(tfPorDataIz.getText().trim());
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(ClawBoxFrame.this,
                        "\u0414\u0430\u0442\u0430 \u0438\u0437\u043F\u044A\u043B\u043D\u0435\u043D\u0430 \u0442\u0440\u044F\u0431\u0432\u0430 \u0434\u0430 \u0435 \u0432\u044A\u0432 \u0444\u043E\u0440\u043C\u0430\u0442 YYYY-MM-DD!",
                        "\u0413\u0440\u0435\u0448\u043A\u0430", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (dataIz.before(dataPr)) {
                    JOptionPane.showMessageDialog(ClawBoxFrame.this,
                        "\u0414\u0430\u0442\u0430 \u0438\u0437\u043F\u044A\u043B\u043D\u0435\u043D\u0430 \u043D\u0435 \u043C\u043E\u0436\u0435 \u0434\u0430 \u0435 \u043F\u0440\u0435\u0434\u0438 \u0434\u0430\u0442\u0430 \u043F\u0440\u0438\u0435\u0442\u0430!",
                        "\u0413\u0440\u0435\u0448\u043A\u0430", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String sql = "INSERT INTO PORUCHKI(IDKOMPONENT, IDKLIENT, KOL, DATAPR, DATAIZ) VALUES(?,?,?,?,?)";
                try (PreparedStatement st = conn.prepareStatement(sql)) {
                    st.setInt(1, idPorKomponent);
                    st.setInt(2, idPorKlient);
                    st.setInt(3, kol);
                    st.setDate(4, dataPr);
                    st.setDate(5, dataIz);
                    st.executeUpdate();
                }
                refreshTablePoruchki();
                clearFormPoruchki();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    class DeletePoruchkaDB implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                if (idPoruchka == -1) return;
                String sql = "DELETE FROM PORUCHKI WHERE ID=?";
                try (PreparedStatement st = conn.prepareStatement(sql)) {
                    st.setInt(1, idPoruchka);
                    st.executeUpdate();
                }
                refreshTablePoruchki();
                clearFormPoruchki();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    class UpdatePoruchkaDB implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                if (idPoruchka == -1) return;
                int kol;
                try {
                    kol = Integer.parseInt(tfPorKol.getText().trim());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(ClawBoxFrame.this,
                        "\u041A\u043E\u043B\u0438\u0447\u0435\u0441\u0442\u0432\u043E\u0442\u043E \u0442\u0440\u044F\u0431\u0432\u0430 \u0434\u0430 \u0435 \u0446\u044F\u043B\u043E \u0447\u0438\u0441\u043B\u043E!",
                        "\u0413\u0440\u0435\u0448\u043A\u0430", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (kol <= 0) {
                    JOptionPane.showMessageDialog(ClawBoxFrame.this,
                        "\u041A\u043E\u043B\u0438\u0447\u0435\u0441\u0442\u0432\u043E\u0442\u043E \u0442\u0440\u044F\u0431\u0432\u0430 \u0434\u0430 \u0435 \u043F\u043E\u043B\u043E\u0436\u0438\u0442\u0435\u043B\u043D\u043E!",
                        "\u0413\u0440\u0435\u0448\u043A\u0430", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                java.sql.Date dataPr;
                java.sql.Date dataIz;
                try {
                    dataPr = java.sql.Date.valueOf(tfPorDataPr.getText().trim());
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(ClawBoxFrame.this,
                        "\u0414\u0430\u0442\u0430 \u043F\u0440\u0438\u0435\u0442\u0430 \u0442\u0440\u044F\u0431\u0432\u0430 \u0434\u0430 \u0435 \u0432\u044A\u0432 \u0444\u043E\u0440\u043C\u0430\u0442 YYYY-MM-DD!",
                        "\u0413\u0440\u0435\u0448\u043A\u0430", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    dataIz = java.sql.Date.valueOf(tfPorDataIz.getText().trim());
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(ClawBoxFrame.this,
                        "\u0414\u0430\u0442\u0430 \u0438\u0437\u043F\u044A\u043B\u043D\u0435\u043D\u0430 \u0442\u0440\u044F\u0431\u0432\u0430 \u0434\u0430 \u0435 \u0432\u044A\u0432 \u0444\u043E\u0440\u043C\u0430\u0442 YYYY-MM-DD!",
                        "\u0413\u0440\u0435\u0448\u043A\u0430", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (dataIz.before(dataPr)) {
                    JOptionPane.showMessageDialog(ClawBoxFrame.this,
                        "\u0414\u0430\u0442\u0430 \u0438\u0437\u043F\u044A\u043B\u043D\u0435\u043D\u0430 \u043D\u0435 \u043C\u043E\u0436\u0435 \u0434\u0430 \u0435 \u043F\u0440\u0435\u0434\u0438 \u0434\u0430\u0442\u0430 \u043F\u0440\u0438\u0435\u0442\u0430!",
                        "\u0413\u0440\u0435\u0448\u043A\u0430", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String sql = "UPDATE PORUCHKI SET IDKOMPONENT=?, IDKLIENT=?, KOL=?, DATAPR=?, DATAIZ=? WHERE ID=?";
                try (PreparedStatement st = conn.prepareStatement(sql)) {
                    st.setInt(1, idPorKomponent);
                    st.setInt(2, idPorKlient);
                    st.setInt(3, kol);
                    st.setDate(4, dataPr);
                    st.setDate(5, dataIz);
                    st.setInt(6, idPoruchka);
                    st.executeUpdate();
                }
                refreshTablePoruchki();
                clearFormPoruchki();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    class MouseActionPoruchkiTable implements MouseListener {
        public void mouseClicked(MouseEvent e) {
            int row = tablePoruchki.getSelectedRow();
            if (row >= 0) {
                idPoruchka = Integer.parseInt(tablePoruchki.getValueAt(row, 0).toString());
                // Set combo to matching component name
                String kompName = tablePoruchki.getValueAt(row, 1).toString();
                comboPorKomponent.setSelectedItem(kompName);
                // Set combo to matching client name
                String klientName = tablePoruchki.getValueAt(row, 2).toString();
                comboPorKlient.setSelectedItem(klientName);
                tfPorKol.setText(tablePoruchki.getValueAt(row, 5).toString());
                Object dataPr = tablePoruchki.getValueAt(row, 6);
                tfPorDataPr.setText(dataPr != null ? dataPr.toString() : "");
                Object dataIz = tablePoruchki.getValueAt(row, 7);
                tfPorDataIz.setText(dataIz != null ? dataIz.toString() : "");
            }
        }
        public void mousePressed(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
    }

    // ==========================================
    // INNER CLASSES — Spravka
    // ==========================================

    class SearchSpravkaDB implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                String sql = "SELECT KOMPONENTI.ID, KOMPONENTI.NAME, KATEGORII.NAME AS KATEGORIYA, "
                    + "DOSTAVCHICI.NAME AS DOSTAVCHIK, OPIS, KOL, CENA "
                    + "FROM KOMPONENTI "
                    + "LEFT JOIN KATEGORII ON KOMPONENTI.IDKATEGORIYA=KATEGORII.ID "
                    + "LEFT JOIN DOSTAVCHICI ON KOMPONENTI.IDDOSTAVCHIK=DOSTAVCHICI.ID "
                    + "WHERE KOMPONENTI.IDKATEGORIYA=? AND KOMPONENTI.IDDOSTAVCHIK=?";

                String cenaText = tfSpravkaCena.getText().trim();
                double maxCena = 0;
                if (!cenaText.isEmpty()) {
                    try {
                        maxCena = Double.parseDouble(cenaText);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(ClawBoxFrame.this,
                            "\u0426\u0435\u043D\u0430\u0442\u0430 \u0442\u0440\u044F\u0431\u0432\u0430 \u0434\u0430 \u0435 \u0447\u0438\u0441\u043B\u043E!",
                            "\u0413\u0440\u0435\u0448\u043A\u0430", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    sql += " AND CENA<=?";
                }

                try (PreparedStatement st = conn.prepareStatement(sql)) {
                    st.setInt(1, idSpravkaKat);
                    st.setInt(2, idSpravkaDost);

                    if (!cenaText.isEmpty()) {
                        st.setDouble(3, maxCena);
                    }

                    try (ResultSet rs = st.executeQuery()) {
                        MyModel model = new MyModel(rs);
                        tableSpravka.setModel(model);
                    }
                }
                // Hide ID column
                tableSpravka.getColumnModel().getColumn(0).setMinWidth(0);
                tableSpravka.getColumnModel().getColumn(0).setMaxWidth(0);
                tableSpravka.getColumnModel().getColumn(0).setWidth(0);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
