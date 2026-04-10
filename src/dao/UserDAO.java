package dao;
import java.sql.*;
import org.mindrot.jbcrypt.BCrypt;

public class UserDAO {
    private static final String DB_URL = "jdbc:sqlite:database.db";

    public static String login(String username, String password) {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            PreparedStatement ps = conn.prepareStatement("SELECT password, role FROM users WHERE username=?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                String hashed = rs.getString("password");
                String role = rs.getString("role");
                if(BCrypt.checkpw(password, hashed)) return role;
            }
        } catch(SQLException e) { e.printStackTrace(); }
        return null;
    }
}