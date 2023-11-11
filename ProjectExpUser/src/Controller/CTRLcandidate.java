
package Controller;

import Model.candidateDAO;
import Model.candidates;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class CTRLcandidate {
    candidateDAO dao = new candidateDAO();
    public void  SaveCandidate( JTextField txtName,JTextField txtLastName,JTextField txtSecondName,JTextField txtIdentification,JTextField txtAdrresPhoto,JComboBox cbxAgeCand,JTextField txtIPoliticParty ){
      this.dao.createCandidate(new candidates (txtName.getText(),txtLastName.getText(),txtSecondName.getText(),Integer.parseInt(txtIdentification.getText()),Integer.parseInt(cbxAgeCand.getSelectedItem().toString()),txtAdrresPhoto.getText(),txtIPoliticParty.getText()));                                        
    }
    
}
