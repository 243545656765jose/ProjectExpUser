package Controller;

import Model.candidateDAO;
import Model.candidates;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.*;

public class CTRLcandidate {

    candidateDAO dao = new candidateDAO();
    int id;
   //delete Candidation
    public void DeleCandidates() {
        this.dao.delete(this.id);
        this.dao.reorganizarIDsCan();
    }

    //add the created candidate
    public void SaveCandidate(JTextField txtName, JTextField txtLastName, JTextField txtSecondName, JTextField txtIdentification, JTextField txtAdrresPhoto, JComboBox cbxAgeCand, JTextField txtIPoliticParty) {
        if (txtName.getText().isEmpty() || txtLastName.getText().isEmpty() || txtIdentification.getText().isEmpty() || cbxAgeCand.getSelectedItem() == null || txtAdrresPhoto.getText().isEmpty() || txtIPoliticParty.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validate the length of the identification number
    if (txtIdentification.getText().length() < 9) {
        JOptionPane.showMessageDialog(null, "La identificación debe tener al menos 9 dígitos", "Error", JOptionPane.ERROR_MESSAGE);
        return;  // Stop operation if the length is less than 9
    }
        try {
           // Validate identification as integers
            Integer.parseInt(txtIdentification.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "La identificación debe ser números enteros", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!txtName.getText().matches("^[a-z A-ZáéíóúÁÉÍÓÚñÑ]+$") || !txtLastName.getText().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ]+$") || !txtSecondName.getText().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ]+$") || !txtIPoliticParty.getText().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ]+$")) {
    JOptionPane.showMessageDialog(null, "El nombre,apellidos y partido deben contener solo letras", "Error", JOptionPane.ERROR_MESSAGE);
    return; // Stop operation if there are disallowed characters
}
        
        
        String base64String = txtAdrresPhoto.getText();
        byte[] imageData = Base64.getDecoder().decode(base64String);
        this.dao.createCandidate(new candidates(txtName.getText(), txtLastName.getText(), txtSecondName.getText(), Integer.parseInt(txtIdentification.getText()), Integer.parseInt(cbxAgeCand.getSelectedItem().toString()), imageData, txtIPoliticParty.getText()));
        this.dao.reorganizarIDsCan();
    }

    // Search for an image in PC files and add it to a byte array
    public byte[] selectImage(JTextField txtPhoto) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            try {
                File selectedFile = fileChooser.getSelectedFile();
                BufferedImage image = ImageIO.read(selectedFile);
                // Convert to a byte array
                byte[] imageData = convertImageToByteArray(image);
                String imageDataString = Base64.getEncoder().encodeToString(imageData);
                txtPhoto.setText(imageDataString);
                return imageData;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } return null;
    }
    // Access the icon of the labelv
    public byte[] getIamge(JLabel photo) throws IOException {
        Icon icon = photo.getIcon();
        Image imagen = ((ImageIcon) icon).getImage();
        return this.convertImageToByteArray(this.toBufferedImage(imagen));
    }
   // Convert the image to a byte array
    public byte[] convertImageToByteArray(BufferedImage image) throws IOException {
        // Convert the image to a byte array
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);
        return baos.toByteArray();
    }
    // Convert to a BufferedImage
    public BufferedImage toBufferedImage(Image imagen) {
        BufferedImage bufferedImage = new BufferedImage(imagen.getWidth(null), imagen.getHeight(null),BufferedImage.TYPE_INT_ARGB );
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.drawImage(imagen, 0, 0, null);
        g2d.dispose();
        return bufferedImage;
    }
     // Return the photo of a candidate
    public byte[] PhotoVoteCandidate(String name) {
        return this.dao.returnPhoto(name);
    }

    // Add the image to a label with its respective size
    public void setImageInLabel(byte[] imageData, JLabel label) {
        try {
            if (imageData != null && imageData.length > 0) {
                ImageIcon imageIcon = new ImageIcon(imageData);
                Image img = imageIcon.getImage();
                if (img != null) {
                    int labelWidth = label.getWidth();
                    int labelHeight = label.getHeight();
                    Image scaledImage = img.getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);
                    label.setIcon(new ImageIcon(scaledImage));
                } else {
                    label.setIcon(null);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Receive a table and load the list of candidates
    public void loadDataCandidates(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        TableRowSorter<TableModel> order = new TableRowSorter<TableModel>(model);
        table.setRowSorter(order);
        model.setRowCount(0);
        List<candidates> Users = dao.readCandidates();
        for (candidates cand : Users) {
            Object[] row = {cand.getId(), cand.getId_number(), cand.getName(), cand.getLast_name(), cand.getSecond_name(),
                cand.getAge(), cand.getParty()};
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
                this.displayImageFromBytes(lblPhoto, dao.bitesPhoto(Integer.parseInt(table.getValueAt(row, 0).toString())));
                party.setText(table.getValueAt(row, 6).toString());
                age.setSelectedItem(table.getValueAt(row, 5).toString());
                id_number.setText(table.getValueAt(row, 1).toString());
            } else {
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de selección, error: " + e.toString());
            e.printStackTrace();
        }
    }
    // Give it the format according to the label
    public void displayImageFromBytes(JLabel label, byte[] imageData) {
        try {
            if (imageData != null && imageData.length > 0) {
                ByteArrayInputStream bis = new ByteArrayInputStream(imageData);
                BufferedImage bufferedImage = ImageIO.read(bis);
                if (bufferedImage != null) {
                    int labelWidth = label.getWidth();
                    int labelHeight = label.getHeight();
                    Image scaledImage = bufferedImage.getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);
                    label.setIcon(new ImageIcon(scaledImage));
                } else {
                    System.out.println("No se pudo leer la imagen");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

     // Modify a candidate
    public void upDataCandidate(JTextField txtName, JTextField txtLastName, JTextField txtSecondName, JTextField txtIdentification, byte[] photo, JComboBox cbxAgeCand, JTextField txtIPoliticParty) {
        if (txtName.getText().isEmpty() || txtLastName.getText().isEmpty() || txtIdentification.getText().isEmpty() || cbxAgeCand.getSelectedItem() == null || txtIPoliticParty.getText().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

        // Validate the length of the identification number
    if (txtIdentification.getText().length() < 9) {
        JOptionPane.showMessageDialog(null, "La identificación debe tener al menos 9 dígitos", "Error", JOptionPane.ERROR_MESSAGE);
        return;  // Stop operation if the length is less than 9
    }
    
    try {
        // Validate identification and age as integers
        Integer.parseInt(txtIdentification.getText());
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "La identificación debe ser números enteros", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    
   if (!txtName.getText().matches("^[a-z A-ZáéíóúÁÉÍÓÚñÑ]+$") || !txtLastName.getText().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ]+$") || !txtSecondName.getText().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ]+$") || !txtIPoliticParty.getText().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ]+$")) {
    JOptionPane.showMessageDialog(null, "El nombre,apellidos y partido deben contener solo letras", "Error", JOptionPane.ERROR_MESSAGE);
    return;// Stop operation if there are disallowed characters
}
        
        this.dao.upCandidates(new candidates(this.id, txtName.getText(), txtLastName.getText(), txtSecondName.getText(), Integer.parseInt(txtIdentification.getText()), Integer.parseInt(cbxAgeCand.getSelectedItem().toString()), photo, txtIPoliticParty.getText()));
        this.dao.reorganizarIDsCan();
    }

    // Return a list of candidate names
    public List listCandidateName() {
        List<candidates> candidate = this.dao.readCandidates();
        List<String> NameCandidates = new ArrayList<>();
        for (candidates cand : candidate) {
            NameCandidates.add(cand.getName());
        }
        return NameCandidates;
    }

    // Load candidates into the combobox
    public void loadCandidates(JComboBox cbx) {
        List<String> candidatesName = this.listCandidateName();
        for (Object Name : candidatesName) {
            cbx.addItem(Name);
        }
    }

    // Add a vote to the selected candidate
    public void addVote(String name) {
        this.dao.QuantityVoteCandidate(name);
    }

    //Clear the txt and lbl fields
    public void cleanTexField(JTextField name, JTextField last_Name, JTextField Second_Name, JLabel lblPhoto, JTextField party, JTextField id_number) {
        name.setText("");
        last_Name.setText("");
        Second_Name.setText("");
        party.setText("");
        id_number.setText("");
        lblPhoto.setIcon(null);
    }

    
}
