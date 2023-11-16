
package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


public class UserDAO {
   
    public UserDAO() {
    }
    
    // Agrega un nuevo usuario con un rol de votante a la base de datos.
public void createVoter(User user) {
    DBConnection db = new DBConnection();
    String consultaSQL = "INSERT INTO users (name, last_name, secund_name, id_number, age, address, password, rol_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    
    try {
        PreparedStatement ps = db.getConnection().prepareStatement(consultaSQL);
        ps.setString(1, user.getName());
        ps.setString(2, user.getLast_name());
        ps.setString(3, user.getSecund_name());
        ps.setInt(4, user.getId_number());
        ps.setInt(5, user.getAge());
        ps.setString(6, user.getAddress());
        ps.setString(7, user.getPassword());
        // Asigna el rol de votante (puedes ajustar el ID según la estructura de tu base de datos).
        ps.setInt(8, 2); // Supongamos que el ID del rol de votante es 2.
        
        ps.execute();
        JOptionPane.showMessageDialog(null, "Se insertó correctamente el usuario como votante");
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "No se insertó correctamente el usuario, error: " + e.toString());
    } finally {
        db.disconnect();
    }
}


    /*Creates a new user record in the database.
     The User object containing user information to be inserted.*/
    public void create(User User) {

        DBConnection db = new DBConnection();
        String consultaSQL = "INSERT INTO users (name,last_name, secund_name, id_number, age, address, password, rol_id ) VALUES (?, ?, ?, ?,?,?,?,?)";
        try {
            PreparedStatement ps = db.getConnection().prepareStatement(consultaSQL);
            ps.setString(1, User.getName());
            ps.setString(2, User.getLast_name());
            ps.setString(3, User.getSecund_name());
            ps.setInt(4, User.getId_number());
            ps.setInt(5, User.getAge());
            ps.setString(6, User.getAddress());
            ps.setString(7, User.getPassword());
            ps.setString(8, User.getRol_id());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Se insertó correctamente el usuario ");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se insertó correctamente el usuario, error: " + e.toString());
        } finally {
            db.disconnect();
        }
    }

    /*Retrieves a list of User objects from the database based on specified criteria.
     The entity ID to filter users by (0 if not used for filtering).  
     The user ID to filter users by (0 if not used for filtering).
     A list of User objects matching the specified criteria.*/

  public List<User> read() {

        DBConnection db = new DBConnection();
        List<User> Users = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try {
            PreparedStatement ps = db.getConnection().prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String last_name = resultSet.getString("last_name");
                String secund_name = resultSet.getString("secund_name");
                int id_number = resultSet.getInt("id_number");
                int age = resultSet.getInt("age");
                String address = resultSet.getString("address");
                String password = resultSet.getString("password");
                String rol_id = resultSet.getString("rol_id");
                Users.add(new User(id,name, last_name,secund_name, id_number, age,address,password,rol_id));
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            db.disconnect();
        }
        return Users;
    }

    
    //Updates user information in the database based on the provided User object.
    public void update(User User) {

        DBConnection db = new DBConnection();
        String consultaSQL = "UPDATE users SET name=?, last_name=?, secund_name=?, id_number=?, age=?, address=?, password=?, rol_id=? WHERE id=?";

        try {
            PreparedStatement ps = db.getConnection().prepareStatement(consultaSQL);
            ps.setString(1, User.getName());
            ps.setString(2, User.getLast_name());
            ps.setString(3, User.getSecund_name());
            ps.setInt(4, User.getId_number());            
            ps.setInt(5, User.getAge());
            ps.setString(6, User.getAddress());
            ps.setString(7, User.getPassword());
            ps.setString(8, User.getRol_id());
            ps.setInt(9, User.getId());
            ps.executeUpdate();  // Utiliza executeUpdate en lugar de execute para sentencias de actualización.

            JOptionPane.showMessageDialog(null, "Modificación Exitosa");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se modificó, error:" + e.toString());
        } finally {
            db.disconnect();
        }
    }
    
    //Deletes a user from the database based on the provided user ID.
 
    public void delete(int id) {

        DBConnection db = new DBConnection();

        String consultaSQL = "DELETE FROM users WHERE id=?";

        try {
            PreparedStatement ps = db.getConnection().prepareStatement(consultaSQL);
            ps.setInt(1, id);
            ps.execute();
            JOptionPane.showMessageDialog(null, "Se eliminó correctamente el usuario ");

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "No se pudo eliminar, error: " + e.toString());
        } finally {
            db.disconnect();
        }
    }
    
     public void reorganizarIDs() {
        DBConnection db = new DBConnection();

        // Consulta SQL para obtener todos los IDs de los estudiantes ordenados
        String consultaSQL = "SELECT id FROM users ORDER BY id";
        try (PreparedStatement preparedStatement = db.getConnection().prepareStatement(consultaSQL); ResultSet resultSet = preparedStatement.executeQuery())  {

            int nuevoID = 1;
            while (resultSet.next()) {
                int antiguoID = resultSet.getInt("id");
                if (nuevoID != antiguoID) {
                    try (PreparedStatement updateStatement = db.getConnection().prepareStatement("UPDATE users SET id = ? WHERE id = ?")) {
                        updateStatement.setInt(1, nuevoID);
                        updateStatement.setInt(2, antiguoID);
                        updateStatement.executeUpdate();
                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null, "Error al actualizar el ID: " + e.toString());
                    }
                }
                nuevoID++;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener los IDs: " + e.toString());
        } finally {
            db.disconnect();
        }
    }   
    
    //Retrieves a User object from the database based on the provided username.
    public User getUserByUsername(String username) {
    DBConnection db = new DBConnection();
    User user = null;
    String sql = "SELECT * FROM users WHERE name = ?";
    
    try {
        PreparedStatement ps = db.getConnection().prepareStatement(sql);
        ps.setString(1, username);
        ResultSet resultSet = ps.executeQuery();
        
        if (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String last_name = resultSet.getString("last_name");
            String secund_name = resultSet.getString("secund_name");
            int id_number = resultSet.getInt("id_number");
            int age = resultSet.getInt("age");
            String address = resultSet.getString("address");
            String password = resultSet.getString("password");
            String rol_id = resultSet.getString("rol_id");
            user = new User(id, name, last_name, secund_name,  id_number, age, address, password,rol_id);
        }
    } catch (SQLException e) {
        System.err.println("Error: " + e.getMessage());
    } finally {
        db.disconnect();
    }
    
    return user;
}
}