package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class candidateDAO {

   // Inserta un candidato a la base de datos
    public void createCandidate(candidates cand) {
        DBConnection db = new DBConnection();
        try {
            PreparedStatement ps = db.getConnection().prepareStatement("INSERT INTO candidates (name,last_name,second_name,age,photo,party,votes,id_number) VALUES(?,?,?,?,?,?,?,?)");
            ps.setString(1, cand.getName());
            ps.setString(2, cand.getLast_name());
            ps.setString(3, cand.getSecond_name());
            ps.setInt(4, cand.getAge());
            ps.setBytes(5, cand.getPhoto()); // Guarda la foto como byte
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
    //Devuelve la foto mientras si el id es el mismo que netra por parametros
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
    //Devuelve una foto segun el nombre del candidato
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
    //Actualiza un candidato
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
    
    //Devuelve una lista de candidatos
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
    //Selecciona la cantidad de votos segun el nombre y le suma uno y lo añade a la DB
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
}
