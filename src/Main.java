import ui.LoginForm;
import org.mindrot.jbcrypt.BCrypt;
import java.sql.*;

public class Main {
    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db")) {
            String hashed = BCrypt.hashpw("admin123", BCrypt.gensalt());
            PreparedStatement ps = conn.prepareStatement(
                    "INSERT OR REPLACE INTO users (id, username, password, role) " +
                            "VALUES ((SELECT id FROM users WHERE username=?), ?, ?, ?)"
            );
            ps.setString(1, "admin");
            ps.setString(2, "admin");
            ps.setString(3, hashed);
            ps.setString(4, "admin");
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        new LoginForm(); // uruchomienie GUI
    }
}