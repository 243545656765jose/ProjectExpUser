package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Model.Rol;

public class RolDAO {

    public RolDAO() {
    }

    public List<Rol> read() {

        DBConnection db = new DBConnection();
        List<Rol> Roles = new ArrayList<>();
        String sql = "SELECT * FROM roles";

        try {
            PreparedStatement ps = db.getConnection().prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Roles.add(new Rol(id, name));
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            db.disconnect();
        }
        return Roles;
    }

    public int getIDRole(String name) {
        int value = 0;
        DBConnection db = new DBConnection();
        String sql = "SELECT id FROM roles WHERE name = ?";
        try {
            PreparedStatement ps = db.getConnection().prepareStatement(sql);
            ps.setString(1, name);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                value = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            db.disconnect();
        }
        return value;
    }

    public String getNameRole(int id) {
        String value = "";
        DBConnection db = new DBConnection();
        String sql = "SELECT name FROM roles WHERE id = ?";
        try {
            PreparedStatement ps = db.getConnection().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                value = resultSet.getString("name");
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            db.disconnect();
        }
        return value;
    }

    public String determineRole(String user, String pasword) {
        DBConnection db = new DBConnection();
        String rol = "";
        try {
            PreparedStatement ps = db.getConnection().prepareStatement("SELECT rol_id FROM users WHERE id_number = ? AND password = ?");
            ps.setString(1, user);
            ps.setString(2, pasword);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                rol = resultSet.getString("rol_id");
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            db.disconnect();
        }
        return rol;
    }
}
