package ui;
import javax.swing.*;
import java.sql.*;
import org.mindrot.jbcrypt.BCrypt;

public class RegisterForm extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton registerBtn;

    public RegisterForm() {
        setTitle("Rejestracja");
        setSize(300, 200);
        setLayout(null);

        add(new JLabel("Username:")).setBounds(50, 20, 80, 25);
        usernameField = new JTextField(); usernameField.setBounds(130, 20, 120, 25); add(usernameField);

        add(new JLabel("Password:")).setBounds(50, 60, 80, 25);
        passwordField = new JPasswordField(); passwordField.setBounds(130, 60, 120, 25); add(passwordField);

        registerBtn = new JButton("Zarejestruj");
        registerBtn.setBounds(80, 110, 120, 25);
        registerBtn.addActionListener(e -> registerUser());
        add(registerBtn);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void registerUser() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        if(username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Wypełnij wszystkie pola");
            return;
        }

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db")) {
            String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO users (username, password, role) VALUES (?, ?, ?)"
            );
            ps.setString(1, username);
            ps.setString(2, hashed);
            ps.setString(3, "user");
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Rejestracja zakończona!");
            dispose();
        } catch(SQLException ex) {
            JOptionPane.showMessageDialog(this, "Błąd: " + ex.getMessage());
        }
    }
}