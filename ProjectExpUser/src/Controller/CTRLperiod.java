
package Controller;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JButton;
import javax.swing.JTextField;


public class CTRLperiod {
    
    public  void mostrarDiferenciaDeFechas(String  date1, String date2,JTextField txtInformation,JButton btn) {
         // Format to print dates in a readable format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
         // Parse input strings into LocalDateTime objects
        LocalDateTime fechaInicial = LocalDateTime.parse(date1, formatter);
        LocalDateTime fechaLimite = LocalDateTime.parse(date2, formatter);
         // Format to print dates in a readable format
        Duration duracion = Duration.between(fechaInicial, fechaLimite);
        // Extract components of the duration
        long dias = duracion.toDays();
        long horas = duracion.toHoursPart();
        long minutos = duracion.toMinutesPart();
        long segundos = duracion.toSecondsPart();
        System.out.println("Diferencia: " + dias + " días, " + horas + " horas, " + minutos + " minutos, " + segundos + " segundos");
        // Check if the current date is after the deadline date
        if (LocalDateTime.now().isAfter(fechaLimite)) { 
            btn.setVisible(false);// Hide the button if the voting period has ended
                txtInformation.setText("Se termino el ´periodo de votaciones");
            
        }
    }
}
    

