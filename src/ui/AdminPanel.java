package ui;

import dao.ShoeDAO;
import model.Shoe;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.List;

public class AdminPanel extends JFrame {

    private DefaultTableModel tableModel;

    public AdminPanel() {
        setTitle("Panel Admina");
        setSize(700, 500);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String[] columns = {"ID", "Nazwa", "Marka", "Rozmiar", "Cena"};
        tableModel = new DefaultTableModel(columns, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 20, 650, 200);
        add(scrollPane);

        JTextField nameField = new JTextField();
        nameField.setBounds(20, 240, 150, 50);
        nameField.setBorder(BorderFactory.createTitledBorder("Nazwa"));

        JTextField brandField = new JTextField();
        brandField.setBounds(180, 240, 150, 50);
        brandField.setBorder(BorderFactory.createTitledBorder("Marka"));

        JTextField sizeField = new JTextField();
        sizeField.setBounds(340, 240, 100, 50);
        sizeField.setBorder(BorderFactory.createTitledBorder("Rozmiar"));

        JTextField priceField = new JTextField();
        priceField.setBounds(450, 240, 100, 50);
        priceField.setBorder(BorderFactory.createTitledBorder("Cena"));

        JButton addButton = new JButton("Dodaj");
        addButton.setBounds(20, 300, 650, 30);

        JButton refreshBtn = new JButton("Odśwież");
        refreshBtn.setBounds(20, 340, 650, 30);

        add(nameField);
        add(brandField);
        add(sizeField);
        add(priceField);
        add(addButton);
        add(refreshBtn);

        addButton.addActionListener(e -> {
            try {
                Shoe shoe = new Shoe(
                        0,
                        nameField.getText(),
                        brandField.getText(),
                        "", // description (brak w bazie)
                        Double.parseDouble(priceField.getText()),
                        Integer.parseInt(sizeField.getText()),
                        "" // imagePath (brak w bazie)
                );

                ShoeDAO.addShoe(shoe);
                JOptionPane.showMessageDialog(this, "Dodano but!");

                loadData();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Zły format danych!");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Błąd: " + ex.getMessage());
            }
        });

        refreshBtn.addActionListener(e -> loadData());

        loadData();
        setVisible(true);
    }

    private void loadData() {
        tableModel.setRowCount(0);

        try {
            List<Shoe> shoes = ShoeDAO.getShoes();

            for (Shoe s : shoes) {
                tableModel.addRow(new Object[]{
                        s.getId(),
                        s.getName(),
                        s.getBrand(),
                        s.getSize(),
                        s.getPrice()
                });
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Błąd ładowania!");
        }
    }
}