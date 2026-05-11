package co.edu.uptc.taller.ui;
 
import co.edu.uptc.taller.domain.*;
import co.edu.uptc.taller.enums.IdentificationType;
import co.edu.uptc.taller.enums.Priority;
import co.edu.uptc.taller.service.*;
import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
 
/**
 * Clase principal que contiene la interfaz de usuario del sistema de la clínica.
 * <p>
 * Implementa un menú interactivo usando ventanas de diálogo de {@link JOptionPane},
 * que permite al usuario registrar pacientes y médicos, agregar medicamentos al
 * historial, agendar citas y consultar listados ordenados.
 * </p>
 * <p>
 * El flujo principal se ejecuta en un ciclo {@code do-while} que repite el menú
 * hasta que el usuario seleccione la opción de salir (7).
 * </p>
 *
 * @author Narwill Daniel Zabala Mata
 * @version 1.0
 */
public class Main {
 
    /**
     * Punto de entrada de la aplicación.
     * <p>
     * Crea una instancia de {@link ClinicService} y presenta al usuario un menú
     * con las siguientes opciones:
     * </p>
     * <ol>
     *   <li>Registrar Paciente</li>
     *   <li>Registrar Médico</li>
     *   <li>Agregar Medicamento a Historial</li>
     *   <li>Agendar Cita Médica</li>
     *   <li>Ver Cola de Atención (por hora y prioridad)</li>
     *   <li>Ver Médicos por Experiencia</li>
     *   <li>Salir</li>
     * </ol>
     *
     * @param args argumentos de línea de comandos (no se utilizan)
     */
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
            
        switch (option) {
 
        case 1:
            // --- Registrar Paciente ---
            // Se solicita el tipo de identificación mediante una lista desplegable
            String[] types = {"CC", "TI", "CE", "PA"};
            String selectedType = (String) JOptionPane.showInputDialog(null, "Tipo de identificación:",
                    "Seleccionar", JOptionPane.QUESTION_MESSAGE, null, types, types[0]);
            IdentificationType type = IdentificationType.valueOf(selectedType);
            
            // Se solicitan los datos personales del paciente
            long id = Long.parseLong(JOptionPane.showInputDialog("Número de identificación:"));
            String firstName = JOptionPane.showInputDialog("Nombre:");
            String lastName = JOptionPane.showInputDialog("Apellido:");
            String email = JOptionPane.showInputDialog("Email:");
            
            String[] priorities = {"LOW", "MEDIUM", "HIGH", "CRITICAL"};
            String selectedPriority = (String) JOptionPane.showInputDialog(null, "Prioridad de la cita:",
                    "Seleccionar", JOptionPane.QUESTION_MESSAGE, null, priorities, priorities[0]);
            Priority priority = Priority.valueOf(selectedPriority);
            
            // Se intenta registrar; el servicio valida duplicados por ID y email
            boolean successPatient = service.registerPatient(type, id, firstName, lastName, email, priority);
            if (successPatient) {
                JOptionPane.showMessageDialog(null, "Paciente registrado exitosamente");
            } else {
                JOptionPane.showMessageDialog(null, "Error: Paciente ya existe (misma ID o email)");
            }
            break;
            
        case 2:
            // --- Registrar Médico ---
            // Se solicita el tipo de identificación del médico
            String[] typesDoc = {"CC", "TI", "CE", "PA"};
            String selectedTypeDoc = (String) JOptionPane.showInputDialog(null, "Tipo de identificación:",
                    "Seleccionar", JOptionPane.QUESTION_MESSAGE, null, typesDoc, typesDoc[0]);
            IdentificationType typeDoc = IdentificationType.valueOf(selectedTypeDoc);
            
            // Se solicitan los datos profesionales del médico
            long medicalId = Long.parseLong(JOptionPane.showInputDialog("ID Médico:"));
            String firstNameDoc = JOptionPane.showInputDialog("Nombre:");
            String lastNameDoc = JOptionPane.showInputDialog("Apellido:");
            String specialty = JOptionPane.showInputDialog("Especialidad:");
            int years = Integer.parseInt(JOptionPane.showInputDialog("Años de experiencia:"));
            
            // Se intenta registrar; el servicio valida duplicados por ID
            boolean successDoctor = service.registerDoctor(typeDoc, medicalId, firstNameDoc, lastNameDoc, specialty, years);
            if (successDoctor) {
                JOptionPane.showMessageDialog(null, "Médico registrado exitosamente");
            } else {
                JOptionPane.showMessageDialog(null, "Error: Médico ya existe");
            }
            break;
            
        case 3:
            // --- Agregar Medicamento a Historial ---
            // Se identifica al paciente para buscar su historial
            String[] typesMed = {"CC", "TI", "CE", "PA"};
            String selectedTypeMed = (String) JOptionPane.showInputDialog(null, "Tipo de identificación del paciente:",
                    "Seleccionar", JOptionPane.QUESTION_MESSAGE, null, typesMed, typesMed[0]);
            IdentificationType typeMed = IdentificationType.valueOf(selectedTypeMed);
            
            long patientId = Long.parseLong(JOptionPane.showInputDialog("ID del paciente:"));
            String medicine = JOptionPane.showInputDialog("Nombre del medicamento:");
            
            // El servicio busca al paciente y agrega el medicamento si no está duplicado
            boolean successMedicine = service.addMedicineToPatient(typeMed, patientId, medicine);
            if (successMedicine) {
                JOptionPane.showMessageDialog(null, "Medicamento agregado al historial");
            } else {
                JOptionPane.showMessageDialog(null, "Error: Paciente no encontrado o medicamento duplicado");
            }
            break;
            
        case 4:
            // --- Agendar Cita Médica ---
            // Se solicita el identificador único de la cita y la fecha/hora
            long appId = Long.parseLong(JOptionPane.showInputDialog("ID de cita:"));
            String dateTimeStr = JOptionPane.showInputDialog("Fecha y hora (YYYY-MM-DD HH:MM):");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime time = LocalDateTime.parse(dateTimeStr, formatter);
            
            // Se solicitan los datos del paciente para la cita
            JOptionPane.showMessageDialog(null, "--- DATOS DEL PACIENTE ---");
            String[] typesPatient = {"CC", "TI", "CE", "PA"};
            String selectedTypePatient = (String) JOptionPane.showInputDialog(null, "Tipo de identificación del paciente:",
                    "Seleccionar", JOptionPane.QUESTION_MESSAGE, null, typesPatient, typesPatient[0]);
            IdentificationType patientType = IdentificationType.valueOf(selectedTypePatient);
            
            long patientIdApp = Long.parseLong(JOptionPane.showInputDialog("ID del paciente:"));
            
            // Se solicitan los datos del médico para la cita
            JOptionPane.showMessageDialog(null, "--- DATOS DEL MÉDICO ---");
            String[] typesDoctor = {"CC", "TI", "CE", "PA"};
            String selectedTypeDoctor = (String) JOptionPane.showInputDialog(null, "Tipo de identificación del médico:",
                    "Seleccionar", JOptionPane.QUESTION_MESSAGE, null, typesDoctor, typesDoctor[0]);
            IdentificationType doctorType = IdentificationType.valueOf(selectedTypeDoctor);
            
            long doctorId = Long.parseLong(JOptionPane.showInputDialog("ID del médico:"));
            
            // Se solicita la prioridad de atención de la cita
            String[] prioritiesApp = {"LOW", "MEDIUM", "HIGH", "CRITICAL"};
            String selectedPriorityApp = (String) JOptionPane.showInputDialog(null, "Prioridad de la cita:",
                    "Seleccionar", JOptionPane.QUESTION_MESSAGE, null, prioritiesApp, prioritiesApp[0]);
            Priority priorityApp = Priority.valueOf(selectedPriorityApp);
            
            // El servicio valida que paciente y médico existan antes de agendar
            boolean successAppointment = service.scheduleAppointment(appId, time, patientType, patientIdApp, doctorType, doctorId, priorityApp);
            if (successAppointment) {
                JOptionPane.showMessageDialog(null, "Cita agendada exitosamente");
            } else {
                JOptionPane.showMessageDialog(null, "Error: Paciente o médico no existen, o ID de cita duplicado");
            }
            break;
            
        case 5:
            // --- Ver Cola de Atención ---
            // Muestra las citas ordenadas primero por hora y luego por prioridad descendente
            List<MedicalAppointment> appointments = service.getSortedAppointmentsByTimeAndPriority();
            if (appointments.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No hay citas agendadas");
            } else {
                StringBuilder sb = new StringBuilder("=== COLA DE ATENCIÓN (por hora y prioridad) ===\n");
                for (MedicalAppointment a : appointments) {
                    sb.append(a.toString()).append("\n");
                }
                JOptionPane.showMessageDialog(null, sb.toString());
            }
            break;
   
        case 6:
            // --- Ver Médicos por Experiencia ---
            // Muestra los médicos ordenados por años de experiencia de menor a mayor
            List<Doctor> doctors = service.getDoctorsSortedByExperienceAndName();
            if (doctors.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No hay médicos registrados");
            } else {
                StringBuilder sb = new StringBuilder("=== MÉDICOS POR EXPERIENCIA (ascendente) ===\n");
                for (Doctor d : doctors) {
                    sb.append(d.toString()).append("\n");
                }
                JOptionPane.showMessageDialog(null, sb.toString());
            }
            break;
            
        case 7:
            // --- Salir ---
            JOptionPane.showMessageDialog(null, "¡Hasta luego!");
            break;
            
        default:
            JOptionPane.showMessageDialog(null, "Opción inválida");
            break;
    }
} while (option != 7);
}
 
}