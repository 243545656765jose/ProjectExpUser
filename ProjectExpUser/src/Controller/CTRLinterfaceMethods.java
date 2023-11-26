package Controller;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class CTRLinterfaceMethods {

    public void AddBorder(JLabel lbl) {
        lbl.setOpaque(true);
        lbl.setBackground(new Color(0,0,102));
        lbl.setForeground(Color.white);
        Border borde = new LineBorder(Color.BLUE, 1);
        lbl.setBorder(borde);
    }

    public void NullBorder(JLabel lbl) {
        lbl.setForeground(Color.black);
        lbl.setOpaque(false);
        lbl.setBorder(null);
    }

}
