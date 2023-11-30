package Controller;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class CTRLinterfaceMethods {

    
    //Adds a border to the specified JLabel with customized colors.
     
    public void AddBorder(JLabel lbl) {
        lbl.setOpaque(true);
        lbl.setBackground(new Color(0,0,102));// Set background color
        lbl.setForeground(Color.white);// Set text color
        Border borde = new LineBorder(Color.BLUE, 1);// Create a line border with blue color
        lbl.setBorder(borde);
    }

    //Removes the border from the specified JLabel and resets text color.
     
    public void NullBorder(JLabel lbl) {
        lbl.setForeground(Color.black);// Reset text color to black
        lbl.setOpaque(false); // Set background to transparent
        lbl.setBorder(null);// Remove the border
    }

}
