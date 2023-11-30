
package Controller;

import Model.RolDAO;
import Model.UserDAO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class CTRLrole {
    RolDAO dao = new RolDAO();
    UserDAO UD =new UserDAO();
    
    //Determines the user role based on provided credentials and navigates to the corresponding interface.
     
    public void Enter_Interface(String id_NUmber,String password,JFrame user,JFrame adm){
        // Retrieve user information based on the provided identification number
        this.UD.getUserByUsername(id_NUmber);
         // Determine the user role using the DAO
        String role=this.dao.determineRole(id_NUmber, password);
        // Show the corresponding interface based on the determined role
        if("Votante".equals(role)){            
           user.setVisible(true);
        }else if ("Administrador".equals(role)){
          adm.setVisible(true);
        }else{JOptionPane.showMessageDialog(null, "Ingrese bien los datos");}
    }
    
}
