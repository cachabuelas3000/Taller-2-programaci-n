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
            
        switch (option) {
        case 1:
            // Registrar Paciente
            String[] types = {"CC", "TI", "CE", "PA"};
            String selectedType = (String) JOptionPane.showInputDialog(null, "Tipo de identificación:",
                    "Seleccionar", JOptionPane.QUESTION_MESSAGE, null, types, types[0]);
            IdentificationType type = IdentificationType.valueOf(selectedType);
            
            long id = Long.parseLong(JOptionPane.showInputDialog("Número de identificación:"));
            String firstName = JOptionPane.showInputDialog("Nombre:");
            String lastName = JOptionPane.showInputDialog("Apellido:");
            String email = JOptionPane.showInputDialog("Email:");
            
            boolean successPatient = service.registerPatient(type, id, firstName, lastName, email, priority);
            if (successPatient) {
                JOptionPane.showMessageDialog(null, "Paciente registrado exitosamente");
            } else {
                JOptionPane.showMessageDialog(null, "Error: Paciente ya existe (misma ID o email)");
            }
            break;
            
        case 2:
            // Registrar Médico
            String[] typesDoc = {"CC", "TI", "CE", "PA"};
            String selectedTypeDoc = (String) JOptionPane.showInputDialog(null, "Tipo de identificación:",
                    "Seleccionar", JOptionPane.QUESTION_MESSAGE, null, typesDoc, typesDoc[0]);
            IdentificationType typeDoc = IdentificationType.valueOf(selectedTypeDoc);
            
            long medicalId = Long.parseLong(JOptionPane.showInputDialog("ID Médico:"));
            String firstNameDoc = JOptionPane.showInputDialog("Nombre:");
            String lastNameDoc = JOptionPane.showInputDialog("Apellido:");
            String specialty = JOptionPane.showInputDialog("Especialidad:");
            int years = Integer.parseInt(JOptionPane.showInputDialog("Años de experiencia:"));
            
            boolean successDoctor = service.registerDoctor(typeDoc, medicalId, firstNameDoc, lastNameDoc, specialty, years);
            if (successDoctor) {
                JOptionPane.showMessageDialog(null, "Médico registrado exitosamente");
            } else {
                JOptionPane.showMessageDialog(null, "Error: Médico ya existe");
            }
            break;
            
        case 3:
            // Agregar Medicamento a Historial
            String[] typesMed = {"CC", "TI", "CE", "PA"};
            String selectedTypeMed = (String) JOptionPane.showInputDialog(null, "Tipo de identificación del paciente:",
                    "Seleccionar", JOptionPane.QUESTION_MESSAGE, null, typesMed, typesMed[0]);
            IdentificationType typeMed = IdentificationType.valueOf(selectedTypeMed);
            
            long patientId = Long.parseLong(JOptionPane.showInputDialog("ID del paciente:"));
            String medicine = JOptionPane.showInputDialog("Nombre del medicamento:");
            
            boolean successMedicine = service.addMedicineToPatient(typeMed, patientId, medicine);
            if (successMedicine) {
                JOptionPane.showMessageDialog(null, "Medicamento agregado al historial");
            } else {
                JOptionPane.showMessageDialog(null, "Error: Paciente no encontrado o medicamento duplicado");
            }
            break;
            
        case 4:
            // Agendar Cita Médica
            long appId = Long.parseLong(JOptionPane.showInputDialog("ID de cita:"));
            String dateTimeStr = JOptionPane.showInputDialog("Fecha y hora (YYYY-MM-DD HH:MM):");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime time = LocalDateTime.parse(dateTimeStr, formatter);
            
            JOptionPane.showMessageDialog(null, "--- DATOS DEL PACIENTE ---");
            String[] typesPatient = {"CC", "TI", "CE", "PA"};
            String selectedTypePatient = (String) JOptionPane.showInputDialog(null, "Tipo de identificación del paciente:",
                    "Seleccionar", JOptionPane.QUESTION_MESSAGE, null, typesPatient, typesPatient[0]);
            IdentificationType patientType = IdentificationType.valueOf(selectedTypePatient);
            
            long patientIdApp = Long.parseLong(JOptionPane.showInputDialog("ID del paciente:"));
            
            JOptionPane.showMessageDialog(null, "--- DATOS DEL MÉDICO ---");
            String[] typesDoctor = {"CC", "TI", "CE", "PA"};
            String selectedTypeDoctor = (String) JOptionPane.showInputDialog(null, "Tipo de identificación del médico:",
                    "Seleccionar", JOptionPane.QUESTION_MESSAGE, null, typesDoctor, typesDoctor[0]);
            IdentificationType doctorType = IdentificationType.valueOf(selectedTypeDoctor);
            
            long doctorId = Long.parseLong(JOptionPane.showInputDialog("ID del médico:"));
            
            String[] priorities = {"LOW", "MEDIUM", "HIGH", "CRITICAL"};
            String selectedPriority = (String) JOptionPane.showInputDialog(null, "Prioridad de la cita:",
                    "Seleccionar", JOptionPane.QUESTION_MESSAGE, null, priorities, priorities[0]);
            Priority priority = Priority.valueOf(selectedPriority);
            
            boolean successAppointment = service.scheduleAppointment(appId, time, patientType, patientIdApp, doctorType, doctorId, priority);
            if (successAppointment) {
                JOptionPane.showMessageDialog(null, "Cita agendada exitosamente");
            } else {
                JOptionPane.showMessageDialog(null, "Error: Paciente o médico no existen, o ID de cita duplicado");
            }
            break;
            
        case 5:
            // Ver Cola de Atención
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
            
            
        
    }
}