
package Controller;

import Model.RolDAO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class CTRLrole {
    RolDAO dao = new RolDAO();
    public void Enter_Interface(String id_NUmber,String password,JFrame user,JFrame adm){
        String role=this.dao.determineRole(id_NUmber, password);
        if("Votante".equals(role)){
           user.setVisible(true);
        }else if ("Administrador".equals(role)){
          adm.setVisible(true);
        }else{JOptionPane.showMessageDialog(null, "Ingrese bien los datos");}
    }
    
}
