package Controller;
 import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JTextField;

public class Validations {

    public Validations() {

    }

    public boolean validarLetras(JTextField valor) {
        // Valida letras, "ñ", tildes y espacios
        Pattern pat = Pattern.compile("[a-zA-ZñÑáéíóúÁÉÍÓÚüÜ ]*");
        Matcher mat = pat.matcher((CharSequence) valor);
        return mat.matches();
    }


    public boolean validarNumeros(String valor) {
        //Validates regular numbers
        Pattern pat = Pattern.compile("[0-9]*");
        Matcher mat = pat.matcher(valor);
        return mat.matches();
    }

    public boolean validarNumerosDecimales(String valor) {
        //Validates decimal numbers
        Pattern pat = Pattern.compile("[0-9.0-9]");
        Matcher mat = pat.matcher(valor);
        return mat.matches();
    }

    public boolean validarAlfanumericos(String valor) {
        //Validates letters and numbers
        Pattern pat = Pattern.compile("^[a-zA-Z0-9]*$");
        Matcher mat = pat.matcher(valor);
        return mat.matches();

    }

    public boolean validarCedulaJuridica(String valor) {
        //Validates the format to be according legal compliances
        Pattern pat = Pattern.compile("\\d\\d{3}\\d{6}");
        Matcher mat = pat.matcher(valor);
        return mat.matches();

    }


    public boolean validarTelefono(String valor) {
        //Validates numbers to be used
        Pattern pat = Pattern.compile("[0-9]+");
        Matcher mat = pat.matcher(valor);
        return mat.matches();
    }

    public boolean validarCedula(String valor) {
        //Validates id numbers legally used in Costa Rica
        Pattern pat = Pattern.compile("^[0-9]{1}[0-9]{4}[0-9]{4}$");
        Matcher mat = pat.matcher(valor);
        return mat.matches();
   }
}
