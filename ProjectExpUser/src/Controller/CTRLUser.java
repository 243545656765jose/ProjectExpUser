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
    int RoleID;
    
    
    //Loads user data into a JTable from a database and displays it.
    public void loadDataUser(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        TableRowSorter<TableModel> order = new TableRowSorter<TableModel>(model);
        table.setRowSorter(order);
        model.setRowCount(0);

        List<User> users = ud.read();// Load all users
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
                user.getRol_id()
            };
            model.addRow(row);
        }
    }   
            
    //Adds a new user to the database based on the input from text fields.
    public void addUser(JTextField name, JTextField last_name, JTextField secund_name, JTextField id_number, JComboBox age, JTextField address, JTextField password, JComboBox rol_id) {
        // Validations here              
        if (name.getText().isEmpty() || last_name.getText().isEmpty() || id_number.getText().isEmpty() || age.getSelectedItem() == null || address.getText().isEmpty() || password.getText().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
        return; // Detener la operación si hay campos vacíos
    }

        // Validate the length of the identification number
    if (id_number.getText().length() < 9) {
        JOptionPane.showMessageDialog(null, "La identificación debe tener al menos 9 dígitos", "Error", JOptionPane.ERROR_MESSAGE);
        return;  // Stop operation if the length is less than 9
    }
    
    try {
        // Validate age as an integer
        Integer.parseInt(id_number.getText());
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "La identificación debe ser números enteros", "Error", JOptionPane.ERROR_MESSAGE);
        return;  // Stop operation if there is an error converting to integer
    }

    // Validate that the name and last name contain only letters
    if (!name.getText().matches("^[a-z A-ZáéíóúÁÉÍÓÚñÑ]+$") || !last_name.getText().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ]+$") || !secund_name.getText().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ]+$") ) {
    JOptionPane.showMessageDialog(null, "El nombre y apellidos deben contener solo letras", "Error", JOptionPane.ERROR_MESSAGE);
    return; // Stop operation if there are disallowed characters
} 
        
        this.ud.create(new User(name.getText(), last_name.getText(), secund_name.getText(), Integer.parseInt(id_number.getText()), Integer.parseInt(age.getSelectedItem().toString()), address.getText(), password.getText(), rol_id.getSelectedItem().toString()));
        this.ud.reorganizarIDs();  
        
    }

    public void upDatauSERS(JTextField txtNameVot, JTextField txtLastNameVot, JTextField txtSecundNameVot, JTextField txtIdentificationVot, JComboBox cbxAgeVot, JTextField txtAddressVot, JTextField txtPasswordVot, JComboBox rol_id) {
        if (txtNameVot.getText().isEmpty() || txtLastNameVot.getText().isEmpty() || txtIdentificationVot.getText().isEmpty() || cbxAgeVot.getSelectedItem() == null || txtAddressVot.getText().isEmpty() || txtPasswordVot.getText().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
        return; // Stop operation if there are empty fields
    }

        // Validate the length of the identification number
    if (txtIdentificationVot.getText().length() < 9) {
        JOptionPane.showMessageDialog(null, "La identificación debe tener al menos 9 dígitos", "Error", JOptionPane.ERROR_MESSAGE);
        return;  // Stop operation if the length is less than 9
    }
    try {
         // Validate age as an integer
        Integer.parseInt(txtIdentificationVot.getText());
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "La identificación debe ser un número entero", "Error", JOptionPane.ERROR_MESSAGE);
        return;  // Stop operation if there is an error converting to integer
    }

    // Validate that the name and last name contain only letters
    if (!txtNameVot.getText().matches("^[a-z A-ZáéíóúÁÉÍÓÚñÑ]+$") || !txtLastNameVot.getText().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ]+$") || !txtSecundNameVot.getText().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ]+$") ) {
    JOptionPane.showMessageDialog(null, "El nombre y apellidos deben contener solo letras", "Error", JOptionPane.ERROR_MESSAGE);
    return;  // Stop operation if there are disallowed characters
} 
        
        this.ud.update(new User(id,txtNameVot.getText(), txtLastNameVot.getText(), txtSecundNameVot.getText(),  Integer.parseInt(txtIdentificationVot.getText()), Integer.parseInt(cbxAgeVot.getSelectedItem().toString()), txtAddressVot.getText(), txtPasswordVot.getText(), rol_id.getSelectedItem().toString()));
        this.ud.reorganizarIDs();  
        
    }   
    
    // Deletes the current user from the database and reorganizes user IDs.
    public void deleteUser(){
        this.ud.delete(this.id);
        this.ud.reorganizarIDs();
    }
    
    // Populates input fields and combo boxes with data from the selected row in the JTable.
    public void selectedRow(JTable table, JTextField name, JTextField last_name, JTextField secund_name, JTextField id_number, JComboBox age, JTextField address, JTextField password, JComboBox rol_id) {
        try {
            int row = table.getSelectedRow();
            if (row >= 0) {
                this.id = Integer.parseInt(table.getValueAt(row, 0).toString());
                name.setText((table.getValueAt(row, 1).toString()));
                last_name.setText((table.getValueAt(row, 2).toString()));
                secund_name.setText((table.getValueAt(row, 3).toString()));
                id_number.setText((table.getValueAt(row, 4).toString()));
                age.setSelectedItem(table.getValueAt(row, 5).toString());
                address.setText(table.getValueAt(row, 6).toString());
                password.setText((table.getValueAt(row, 7).toString()));
                rol_id.setSelectedItem((table.getValueAt(row, 8).toString()));
            } else {
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de seleccion, error: " + e.toString());
            
            System.out.println(e.toString());
        }
    }
    
    // Clears the content of the provided text fields
    public void clearFields(JTextField name, JTextField last_name, JTextField secund_name, JTextField id_number, JTextField address, JTextField password) {
        name.setText("");
        last_name.setText("");
        secund_name.setText("");
        id_number.setText("");
        address.setText("");
        password.setText("");
    }
    
   //his method is used to get the ID of the selected role in the JComboBox. 
    public void getIdRole(JComboBox role) {
        this.RoleID = this.rd.getIDRole(role.getSelectedItem().toString());
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
