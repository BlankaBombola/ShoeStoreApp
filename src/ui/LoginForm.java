package ui;
import dao.UserDAO;
import javax.swing.*;

public class LoginForm extends JFrame {

    public LoginForm() {
        setTitle("Logowanie");
        setSize(300, 250);
        setLayout(null);

        JTextField login = new JTextField();
        login.setBounds(50, 30, 200, 25);
        add(login);

        JPasswordField pass = new JPasswordField();
        pass.setBounds(50, 70, 200, 25);
        add(pass);

        JButton btnLogin = new JButton("Zaloguj");
        btnLogin.setBounds(50, 110, 200, 25);
        btnLogin.addActionListener(e -> {
            String role = UserDAO.login(login.getText(), new String(pass.getPassword()));
            if(role != null) {
                JOptionPane.showMessageDialog(this, "Zalogowano!");
                new MainForm(role);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Błąd logowania");
            }
        });
        add(btnLogin);

        JButton btnRegister = new JButton("Rejestracja");
        btnRegister.setBounds(50, 150, 200, 25);
        btnRegister.addActionListener(e -> new RegisterForm());
        add(btnRegister);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}