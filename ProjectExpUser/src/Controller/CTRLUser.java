
package Controller;


import Model.Rol;
import Model.RolDAO;
import Model.User;
import Model.UserDAO;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


public class CTRLUser {
    
    UserDAO ud = new UserDAO();
    RolDAO rd = new RolDAO();
    int id;
    int RolID;
    
    
    //Loads user data into a JTable from a database and displays it.
    public void loadDataUser(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        TableRowSorter<TableModel> order = new TableRowSorter<TableModel>(model);
        table.setRowSorter(order);
        model.setRowCount(0);

        List<User> users = ud.read(); // Cargar todos los usuarios

        for (User user : users) {
            Object[] row = {
                user.getId(),
                user.getName(),
                user.getLast_name(),
                user.getSecund_name(),
                user.getId_number(),
                user.getAge(),
                user.getAddress(),
                user.getPassword(),
                this.rd.getNameRole(user.getRol_id())
            };
            model.addRow(row);
        }
    }
 
       
    //Adds a new user to the database based on the input from text fields.
    public void addUser(JTextField name, JTextField last_name, JTextField secund_name, JTextField id_number, JComboBox age, JTextField address, JTextField password) {
        //Validaciones aqui
        String Name = name.getText();
        String Last_name = last_name.getText();
        String Secund_name = secund_name.getText();
        String Id_number = id_number.getText();
        String Age = age.getSelectedItem().toString();
        String Address = address.getText();
        String Password = password.getText();

       
        this.ud.create(new User(name.getText(), last_name.getText(), secund_name.getText(), Integer.parseInt(id_number.getText()), Integer.parseInt(age.getSelectedItem().toString()), address.getText(), password.getText(), this.RolID));
        this.ud.reorganizarIDs();

    }

    
    //Updates an existing user's information in the database based on input from text fields.
    /*public void updateUser(JTextField name, JTextField first_name, JTextField second_name, JTextField email, JTextField password) {
        //Validaciones aqui
        JTextField[] campos = {name, first_name, second_name, email, password};
        String Name = name.getText();
        String First_name = first_name.getText();
        String Second_name = second_name.getText();
        String Email = email.getText();
        String Password = password.getText();

       
        this.ud.update(new User(this.id, name.getText(), first_name.getText(), second_name.getText(), email.getText(), password.getText(),  this.RolID));
        this.ud.reorganizarIDs();

    }*/
    
    //Deletes the current user from the database and reorganizes user IDs.
    public void deleteUser(){
        this.ud.delete(this.id);
        this.ud.reorganizarIDs();
    }
    
    //Populates input fields and combo boxes with data from the selected row in the JTable.
    public void selectedRow(JTable table, JTextField name, JTextField last_name, JTextField secund_name, JTextField id_number, JComboBox age, JTextField address, JTextField password) {
        try {
            int row = table.getSelectedRow();
            if (row >= 0) {
                this.id = Integer.parseInt(table.getValueAt(row, 0).toString());
                name.setText((table.getValueAt(row, 1).toString()));
                last_name.setText((table.getValueAt(row, 2).toString()));
                secund_name.setText((table.getValueAt(row, 3).toString()));
                id_number.setText((table.getValueAt(row, 4).toString()));
                password.setText((table.getValueAt(row, 5).toString()));
            } else {
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de seleccion, error: " + e.toString());
        }
    }
    
    //Clears the content of the provided text fields
    public void clearFields(JTextField name, JTextField last_name, JTextField secund_name, JTextField id_number,JTextField address, JTextField password) {
        name.setText("");
        last_name.setText("");
        secund_name.setText("");
        id_number.setText("");
        address.getText();
        password.setText("");
    }
    
    

   //his method is used to get the ID of the selected role in the JComboBox. 
    public void getIdRole(JComboBox role) {
        this.RolID = this.rd.getIDRole(role.getSelectedItem().toString());
    }

    //This method loads roles into the JComboBox
    public void loadRole(JComboBox c, boolean isAdmin, boolean isVotante) {
        List<Rol> roles = this.rd.read();
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();

        for (Rol role : roles) {
            if ((isAdmin && (role.getName().equals("Administrador") || role.getName().equals("Votante")))
                    || (isVotante && role.getName().equals("Votante"))
                    || (!isAdmin && !isVotante)) {
                model.addElement(role.getName());
            }
        }

        c.setModel(model);
    }

}
