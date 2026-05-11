package co.edu.uptc.taller.ui;

import co.edu.uptc.taller.domain.*;
import co.edu.uptc.taller.service.*;
import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Interfaz de usuario para el sistema de la clínica
 * @author Narwill Daniel Zabala Mata
 */
public class Main{
    
    public static void main(String[] args) {
        ClinicService service = new ClinicService();
        int option;
        
        do {
            String menu = """
                    === CLÍNICA EL LAGUITO ===
                    1. Registrar Paciente
                    2. Registrar Médico
                    3. Agregar Medicamento a Historial
                    4. Agendar Cita Médica
                    5. Ver Cola de Atención (por hora y prioridad)
                    6. Ver Médicos por Experiencia
                    7. Salir
                    """;
            String input = JOptionPane.showInputDialog(null, menu, "Menú Principal", JOptionPane.PLAIN_MESSAGE);
            if (input == null) break;
            
            option = Integer.parseInt(input);
        }
    }
}