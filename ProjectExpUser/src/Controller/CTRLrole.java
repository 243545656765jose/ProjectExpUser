
package Controller;

import Model.RolDAO;
import Model.UserDAO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class CTRLrole {
    RolDAO dao = new RolDAO();
    UserDAO UD =new UserDAO();
    public void Enter_Interface(String id_NUmber,String password,JFrame user,JFrame adm){
        this.UD.getUserByUsername(id_NUmber);
        String role=this.dao.determineRole(id_NUmber, password);
        if("Votante".equals(role)){
           user.setVisible(true);
        }else if ("Administrador".equals(role)){
          adm.setVisible(true);
        }else{JOptionPane.showMessageDialog(null, "Ingrese bien los datos");}
    }
    
}
