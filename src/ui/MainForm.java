package ui;

import dao.ShoeDAO;
import model.Shoe;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class MainForm extends JFrame {

    DefaultListModel<String> model = new DefaultListModel<>();

    public MainForm(String role) {
        setTitle("Sklep z butami");
        setSize(400, 400);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JList<String> list = new JList<>(model);
        JScrollPane pane = new JScrollPane(list);
        pane.setBounds(20, 20, 340, 200);

        JButton refresh = new JButton("Odśwież");
        refresh.setBounds(20, 230, 150, 30);
        refresh.addActionListener(e -> loadData());

        add(pane);
        add(refresh);

        if (role.equals("admin")) {
            JButton adminBtn = new JButton("Panel admina");
            adminBtn.setBounds(200, 230, 150, 30);
            adminBtn.addActionListener(e -> new AdminPanel());
            add(adminBtn);
        }

        loadData(); // załaduj dane przy starcie

        setVisible(true);
    }

    private void loadData() {
        model.clear();
        try {
            List<Shoe> shoes = ShoeDAO.getShoes(); // <-- poprawiona nazwa metody
            for (Shoe s : shoes) {
                model.addElement(s.getName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Błąd ładowania danych: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new MainForm("user"); // lub "admin"
    }
}