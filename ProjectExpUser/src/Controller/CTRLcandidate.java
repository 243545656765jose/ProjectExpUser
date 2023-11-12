package Controller;

import Model.candidateDAO;
import Model.candidates;
import java.awt.Image;
import java.io.File;
import java.util.List;
import javax.swing.*;
import javax.swing.table.*;

public class CTRLcandidate {

    candidateDAO dao = new candidateDAO();
    int id;
    //add the created candidate
    public void SaveCandidate(JTextField txtName, JTextField txtLastName, JTextField txtSecondName, JTextField txtIdentification, JTextField txtAdrresPhoto, JComboBox cbxAgeCand, JTextField txtIPoliticParty) {
        this.dao.createCandidate(new candidates(txtName.getText(), txtLastName.getText(), txtSecondName.getText(), Integer.parseInt(txtIdentification.getText()), Integer.parseInt(cbxAgeCand.getSelectedItem().toString()), txtAdrresPhoto.getText(), txtIPoliticParty.getText()));
    }
   //Access PC files, choose the file, size and add it
    public void addImage(JTextField txtImage, JLabel lblImage) {
        JFileChooser n = new JFileChooser();
        int ventana = n.showOpenDialog(null);
        File file = n.getSelectedFile();
        txtImage.setText(String.valueOf(file));
        ImageIcon imageIcon = new ImageIcon(txtImage.getText());
        Image image = imageIcon.getImage();
        Image newImage = image.getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon newImageIcon = new ImageIcon(newImage);
        lblImage.setIcon(newImageIcon);
    }
   //receive a table and load the list of candidates
    public void loadDataCandidates(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        TableRowSorter<TableModel> order = new TableRowSorter<TableModel>(model);
        table.setRowSorter(order);
        model.setRowCount(0);
        List<candidates> Users = dao.readCandidates();
        for (candidates cand : Users) {
            Object[] row = {cand.getId(), cand.getId_number(), cand.getName(), cand.getLast_name(), cand.getSecond_name(),
                cand.getPhoto(), cand.getAge(), cand.getParty()};
            model.addRow(row);
        }
    }
   //selects the fields from the table and sets them in the txt and lbl
    public void selectedRowCandidates(JTable table, JTextField name, JTextField last_Name, JTextField Second_Name, JTextField photo, JLabel lblPhoto, JTextField party, JComboBox age, JTextField id_number) {
        try {
            int row = table.getSelectedRow();
            if (row >= 0) {
                this.id = Integer.parseInt(table.getValueAt(row, 0).toString());
                name.setText(table.getValueAt(row, 2).toString());
                last_Name.setText(table.getValueAt(row, 3).toString());
                Second_Name.setText(table.getValueAt(row, 4).toString());
                photo.setText(table.getValueAt(row, 5).toString());
                String pathToImage = table.getValueAt(row, 5).toString();
                photo.setText(pathToImage);
                party.setText(table.getValueAt(row, 7).toString());
                age.setSelectedItem(table.getValueAt(row, 6).toString());
                id_number.setText(table.getValueAt(row, 1).toString());
                // Load image from file path
                ImageIcon originalIcon = new ImageIcon(pathToImage);
                // Get the size of the JLabel
                int width = lblPhoto.getWidth();
                int height = lblPhoto.getHeight();
                // Resize the image to the size of the JLabel
                Image img = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(img);
                lblPhoto.setIcon(scaledIcon);
            } else {
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de selección, error: " + e.toString());
        }
    }

    //Modify a candidate
    public void upDataCandidate(JTextField txtName, JTextField txtLastName, JTextField txtSecondName, JTextField txtIdentification, JTextField txtAdrresPhoto, JComboBox cbxAgeCand, JTextField txtIPoliticParty) {
        this.dao.upCandidates(new candidates(this.id, txtName.getText(), txtLastName.getText(), txtSecondName.getText(), Integer.parseInt(txtIdentification.getText()), Integer.parseInt(cbxAgeCand.getSelectedItem().toString()), txtAdrresPhoto.getText(), txtIPoliticParty.getText()));
    }
    //Clear the txt and lbl fields

    public void cleanTexField(JTextField name, JTextField last_Name, JTextField Second_Name, JTextField photo, JLabel lblPhoto, JTextField party, JTextField id_number) {
        name.setText("");
        last_Name.setText("");
        Second_Name.setText("");
        photo.setText("");
        party.setText("");
        id_number.setText("");
        lblPhoto.setIcon(null);
    }

}
