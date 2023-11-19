package Controller;

import Model.candidateDAO;
import Model.candidates;
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

    //add the created candidate
    public void SaveCandidate(JTextField txtName, JTextField txtLastName, JTextField txtSecondName, JTextField txtIdentification, JTextField txtAdrresPhoto, JComboBox cbxAgeCand, JTextField txtIPoliticParty) {
        String base64String = txtAdrresPhoto.getText();
        byte[] imageData = Base64.getDecoder().decode(base64String);
        this.dao.createCandidate(new candidates(txtName.getText(), txtLastName.getText(), txtSecondName.getText(), Integer.parseInt(txtIdentification.getText()), Integer.parseInt(cbxAgeCand.getSelectedItem().toString()), imageData, txtIPoliticParty.getText()));
    }
    //Busca la imagen  en archivos de la pc y la agrega  a un array de byte
    public byte[] selectImage(JTextField txtPhoto) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            try {
                File selectedFile = fileChooser.getSelectedFile();
                BufferedImage image = ImageIO.read(selectedFile);
                // Convertir la imagen a un arreglo de bytes
                byte[] imageData = convertImageToByteArray(image);
                String imageDataString = Base64.getEncoder().encodeToString(imageData);
                txtPhoto.setText(imageDataString); 
                return imageData;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    //Le damos formato segun el label
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
            } else {
                System.out.println("Datos de imagen vacíos o nulos");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private byte[] convertImageToByteArray(BufferedImage image) throws IOException {
        // Convertir la imagen a un arreglo de bytes (byte array)
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);
        return baos.toByteArray();
    }
    
    public byte[] PhotoVoteCandidate(String name){
       return this.dao.returnPhoto(name);
    }
    
    //Agrega la imajen a un label con su tamaño respectivo
    public void setImageInLabel(byte[] imageData, JLabel label) {
        try {
            if (imageData != null && imageData.length > 0) {
                // Crea un ImageIcon directamente desde los bytes de la imagen
                ImageIcon imageIcon = new ImageIcon(imageData);
                Image img = imageIcon.getImage();
                if (img != null) {
                    // Escalar la imagen al tamaño del JLabel
                    int labelWidth = label.getWidth();
                    int labelHeight = label.getHeight();
                    Image scaledImage = img.getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);
                    // Establecer el ImageIcon escalado en el JLabel
                    label.setIcon(new ImageIcon(scaledImage));
                } else {
                    label.setIcon(null);
                }
            } 
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                name.setText(table.getValueAt(row, 1).toString());
                last_Name.setText(table.getValueAt(row, 2).toString());
                Second_Name.setText(table.getValueAt(row, 3).toString());
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

   //Modify a candidate
    public void upDataCandidate(JTextField txtName, JTextField txtLastName, JTextField txtSecondName, JTextField txtIdentification, JTextField txtAdrresPhoto, JComboBox cbxAgeCand, JTextField txtIPoliticParty) {
        String base64String = txtAdrresPhoto.getText();
        byte[] imageData = Base64.getDecoder().decode(base64String);
        this.dao.upCandidates(new candidates(this.id, txtName.getText(), txtLastName.getText(), txtSecondName.getText(), Integer.parseInt(txtIdentification.getText()), Integer.parseInt(cbxAgeCand.getSelectedItem().toString()), imageData, txtIPoliticParty.getText()));
    }
    //Devuelve una lista de los nombre de los candidTOS
    public List listCandidateName() {
        List <candidates> candidate = this.dao.readCandidates();
        List<String> NameCandidates = new ArrayList<>();
        for (candidates cand : candidate) {
            NameCandidates.add(cand.getName());
        }return NameCandidates;
    }
    //Carga los candidatos al combobox
    public void loadCandidates(JComboBox cbx){
      List<String> candidatesName = this.listCandidateName();
        for (Object Name : candidatesName) {
            cbx.addItem(Name);
        }
    }
    //Agrega un voto al candidato seleccionado
    public void  addVote(String name){
      this.dao.QuantityVoteCandidate(name);
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
