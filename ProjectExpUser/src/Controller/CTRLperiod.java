
package Controller;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JButton;
import javax.swing.JTextField;


public class CTRLperiod {
    public  void mostrarDiferenciaDeFechas(String  date1, String date2,JTextField txtInformation,JButton btn) {
        // Formato para imprimir las fechas en un formato legible
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime fechaInicial = LocalDateTime.parse(date1, formatter);
        LocalDateTime fechaLimite = LocalDateTime.parse(date2, formatter);
        Duration duracion = Duration.between(fechaInicial, fechaLimite);
        long dias = duracion.toDays();
        long horas = duracion.toHoursPart();
        long minutos = duracion.toMinutesPart();
        long segundos = duracion.toSecondsPart();
        System.out.println("Diferencia: " + dias + " días, " + horas + " horas, " + minutos + " minutos, " + segundos + " segundos");
        if (LocalDateTime.now().isAfter(fechaLimite)) { 
            btn.setVisible(false);
                txtInformation.setText("Se termino el ´periodo de votaciones");
            
        }
    }
}
    

