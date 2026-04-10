package dao;

import model.Shoe;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShoeDAO {
    private static final String DB_URL = "jdbc:sqlite:database.db";

    public static void addShoe(Shoe shoe) throws SQLException {
        try(Connection conn = DriverManager.getConnection(DB_URL)) {

            String sql = "INSERT INTO shoes (name, brand, size, price) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, shoe.getName());
            ps.setString(2, shoe.getBrand());
            ps.setInt(3, shoe.getSize());
            ps.setDouble(4, shoe.getPrice());

            ps.executeUpdate();
        }
    }

    public static List<Shoe> getShoes() throws SQLException {
        List<Shoe> list = new ArrayList<>();

        try(Connection conn = DriverManager.getConnection(DB_URL)) {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM shoes");

            while(rs.next()) {
                list.add(new Shoe(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("brand"),
                        "", // brak w bazie
                        rs.getDouble("price"),
                        rs.getInt("size"),
                        "" // brak w bazie
                ));
            }
        }

        return list;
    }
}