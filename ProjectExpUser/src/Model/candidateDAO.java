package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class candidateDAO {

   // Insert a candidate into the database.
     
    public void createCandidate(candidates cand) {
        DBConnection db = new DBConnection();
        try {
            PreparedStatement ps = db.getConnection().prepareStatement("INSERT INTO candidates (name,last_name,second_name,age,photo,party,votes,id_number) VALUES(?,?,?,?,?,?,?,?)");
            ps.setString(1, cand.getName());
            ps.setString(2, cand.getLast_name());
            ps.setString(3, cand.getSecond_name());
            ps.setInt(4, cand.getAge());
            ps.setBytes(5, cand.getPhoto()); // Save the photo as bytes
            ps.setString(6, cand.getParty());
            ps.setInt(7, cand.getVotes());
            ps.setInt(8, cand.getId_number());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Se insertó correctamente el candidato");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se insertó correctamente el candidato, error: " + e.toString());
        } finally {
            db.disconnect();
        }
    }
    // Retrieve the photo bytes of a candidate based on their ID.
     
    public byte[] bitesPhoto(int id) {
        DBConnection db = new DBConnection();
        byte[] photoBytes = null;
        try {
            PreparedStatement ps = db.getConnection().prepareStatement("SELECT photo FROM candidates WHERE id = ?");
            ps.setInt(1, id);

            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                photoBytes = resultSet.getBytes("photo");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontraron datos para el candidato con ID: " + id);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener la foto del candidato, error: " + e.toString());
        } finally {
            db.disconnect();
        }
        return photoBytes;
    }
    //Retrieve the photo bytes of a candidate based on their name.
     
    public byte[] returnPhoto(String name) {
        DBConnection db = new DBConnection();
        byte[] photoBytes = null;
        try {
            PreparedStatement ps = db.getConnection().prepareStatement("SELECT photo FROM candidates WHERE id = (SELECT id FROM candidates WHERE name =?)");
            ps.setString(1, name);

            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                photoBytes = resultSet.getBytes("photo");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontraron datos para el candidato ");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener la foto del candidato, error: " + e.toString());
        } finally {
            db.disconnect();
        }
        return photoBytes;
    }
    // Update candidate information in the database.
     
    public void upCandidates(candidates cand) {
        DBConnection db = new DBConnection();
        try {
            PreparedStatement ps = db.getConnection().prepareStatement("UPDATE candidates SET  name=?, last_name=?, second_name=?,age=?,photo=?,party=?,id_number=? WHERE id=?");
            ps.setString(1, cand.getName());
            ps.setString(2, cand.getLast_name());
            ps.setString(3, cand.getSecond_name());
            ps.setInt(4, cand.getAge());
            ps.setBytes(5, cand.getPhoto());
            ps.setString(6, cand.getParty());
            ps.setInt(7, cand.getId_number());
            ps.setInt(8, cand.getId());
            ps.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se insertó correctamente el candidato, error: " + e.toString());
            System.err.println(e);
        } finally {
            db.disconnect();
        }
    }
    
    // Retrieve a list of all candidates from the database.
     
    public List<candidates> readCandidates() {
        DBConnection db = new DBConnection();
        List<candidates> cnadidates = new ArrayList<>();
        String sql = "SELECT * FROM candidates";
        try {
            PreparedStatement ps = db.getConnection().prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String last_Name = resultSet.getString("last_name");
                String second_Name = resultSet.getString("second_name");
                int age = resultSet.getInt("age");
                byte[] photo = resultSet.getBytes("photo");
                String party = resultSet.getString("party");
                int vote =resultSet.getInt("votes");
                int id_number = resultSet.getInt("id_number");
                cnadidates.add(new candidates(id, name, last_Name, second_Name, id_number, age, photo, party,vote));
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            db.disconnect();
        }
        return cnadidates;
    }
    //Delete a candidate from the database based on their ID.
    
     public void delete(int id) {
        DBConnection db = new DBConnection();
        try {
            PreparedStatement ps = db.getConnection().prepareStatement("DELETE FROM candidates WHERE id=?");
            ps.setInt(1, id);
            ps.execute();
            JOptionPane.showMessageDialog(null, "Se eliminó correctamente");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo eliminar, error: " + e.toString());
        } finally {
            db.disconnect();
        }
    }
     
    //Select the vote count for a candidate by name, increment it by one, and update it in the database.
     public List<candidates> QuantityVoteCandidate(String name) {
        DBConnection db = new DBConnection();
        List<candidates> candidatesList = new ArrayList<>();
        String selectSql = "SELECT votes  FROM candidates WHERE name =?";
        String insertSql = "UPDATE  candidates set votes=? WHERE name=?";
        try {
            PreparedStatement selectStatement = db.getConnection().prepareStatement(selectSql);
            selectStatement.setString(1, name);
            ResultSet resultSet = selectStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("votes");
                try (PreparedStatement insertStatement = db.getConnection().prepareStatement(insertSql)) {
                    id+=1;
                    insertStatement.setInt(1,id );
                    insertStatement.setString(2, name);
                    insertStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            db.disconnect();
        }
        return candidatesList;
    }
    
     // Reorganize candidate IDs in the database.
    public void reorganizarIDsCan() {
        DBConnection db = new DBConnection();

        // Consulta SQL para obtener todos los IDs de los estudiantes ordenados
        String consultaSQL = "SELECT id FROM candidates ORDER BY id";
        try (PreparedStatement preparedStatement = db.getConnection().prepareStatement(consultaSQL); ResultSet resultSet = preparedStatement.executeQuery())  {

            int nuevoID = 1;
            while (resultSet.next()) {
                int antiguoID = resultSet.getInt("id");
                if (nuevoID != antiguoID) {
                    try (PreparedStatement updateStatement = db.getConnection().prepareStatement("UPDATE candidates SET id = ? WHERE id = ?")) {
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
}
