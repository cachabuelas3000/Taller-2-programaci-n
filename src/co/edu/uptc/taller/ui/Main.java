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
public class Main {
 
    public static void main(String[] args) {
        ClinicService service = new ClinicService();
        int option = 0;
 
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
 
            // CORRECCIÓN: si el usuario cierra el diálogo principal, salimos limpiamente
            if (input == null) break;
 
            option = Integer.parseInt(input.trim());
 
            switch (option) {
 
                case 1: {
                    // Registrar Paciente
                    String[] types = {"CC", "TI", "CE", "PA"};
                    String selectedType = (String) JOptionPane.showInputDialog(null,
                            "Tipo de identificación:", "Seleccionar",
                            JOptionPane.QUESTION_MESSAGE, null, types, types[0]);
                    // CORRECCIÓN: verificar null en cada diálogo para evitar NullPointerException al cancelar
                    if (selectedType == null) break;
                    IdentificationType type = IdentificationType.valueOf(selectedType);
 
                    String idInput = JOptionPane.showInputDialog("Número de identificación:");
                    if (idInput == null) break;
                    long id = Long.parseLong(idInput.trim());
 
                    String firstName = JOptionPane.showInputDialog("Nombre:");
                    if (firstName == null) break;
 
                    String lastName = JOptionPane.showInputDialog("Apellido:");
                    if (lastName == null) break;
 
                    String email = JOptionPane.showInputDialog("Email:");
                    if (email == null) break;
 
                    // CORRECCIÓN: faltaba pedir la prioridad — el constructor de Patient la exige obligatoriamente
                    String[] priorities = {"LOW", "MEDIUM", "HIGH", "CRITICAL"};
                    String selectedPriority = (String) JOptionPane.showInputDialog(null,
                            "Prioridad del paciente:", "Seleccionar",
                            JOptionPane.QUESTION_MESSAGE, null, priorities, priorities[0]);
                    if (selectedPriority == null) break;
                    Priority priority = Priority.valueOf(selectedPriority);
 
                    boolean success = service.registerPatient(type, id, firstName, lastName, email, priority);
                    if (success) {
                        JOptionPane.showMessageDialog(null, "Paciente registrado exitosamente.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error: Paciente ya existe (misma ID o email).");
                    }
                    break;
                }
 
                case 2: {
                    // Registrar Médico
                    String[] typesDoc = {"CC", "TI", "CE", "PA"};
                    String selectedTypeDoc = (String) JOptionPane.showInputDialog(null,
                            "Tipo de identificación:", "Seleccionar",
                            JOptionPane.QUESTION_MESSAGE, null, typesDoc, typesDoc[0]);
                    if (selectedTypeDoc == null) break;
                    IdentificationType typeDoc = IdentificationType.valueOf(selectedTypeDoc);
 
                    String medIdInput = JOptionPane.showInputDialog("ID Médico:");
                    if (medIdInput == null) break;
                    long medicalId = Long.parseLong(medIdInput.trim());
 
                    String firstNameDoc = JOptionPane.showInputDialog("Nombre:");
                    if (firstNameDoc == null) break;
 
                    String lastNameDoc = JOptionPane.showInputDialog("Apellido:");
                    if (lastNameDoc == null) break;
 
                    String specialty = JOptionPane.showInputDialog("Especialidad:");
                    if (specialty == null) break;
 
                    String yearsInput = JOptionPane.showInputDialog("Años de experiencia:");
                    if (yearsInput == null) break;
                    int years = Integer.parseInt(yearsInput.trim());
 
                    boolean success = service.registerDoctor(typeDoc, medicalId, firstNameDoc, lastNameDoc, specialty, years);
                    if (success) {
                        JOptionPane.showMessageDialog(null, "Médico registrado exitosamente.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error: Médico ya existe.");
                    }
                    break;
                }
 
                case 3: {
                    // Agregar Medicamento a Historial
                    String[] typesMed = {"CC", "TI", "CE", "PA"};
                    String selectedTypeMed = (String) JOptionPane.showInputDialog(null,
                            "Tipo de identificación del paciente:", "Seleccionar",
                            JOptionPane.QUESTION_MESSAGE, null, typesMed, typesMed[0]);
                    if (selectedTypeMed == null) break;
                    IdentificationType typeMed = IdentificationType.valueOf(selectedTypeMed);
 
                    String patIdInput = JOptionPane.showInputDialog("ID del paciente:");
                    if (patIdInput == null) break;
                    long patientId = Long.parseLong(patIdInput.trim());
 
                    String medicine = JOptionPane.showInputDialog("Nombre del medicamento:");
                    if (medicine == null) break;
 
                    boolean success = service.addMedicineToPatient(typeMed, patientId, medicine);
                    if (success) {
                        JOptionPane.showMessageDialog(null, "Medicamento agregado al historial.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error: Paciente no encontrado o medicamento duplicado.");
                    }
                    break;
                }
 
                case 4: {
                    // Agendar Cita Médica
                    String appIdInput = JOptionPane.showInputDialog("ID de cita:");
                    if (appIdInput == null) break;
                    long appId = Long.parseLong(appIdInput.trim());
 
                    String dateTimeStr = JOptionPane.showInputDialog("Fecha y hora (YYYY-MM-DD HH:MM):");
                    if (dateTimeStr == null) break;
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    LocalDateTime time = LocalDateTime.parse(dateTimeStr.trim(), formatter);
 
                    JOptionPane.showMessageDialog(null, "--- DATOS DEL PACIENTE ---");
                    String[] typesPatient = {"CC", "TI", "CE", "PA"};
                    String selectedTypePatient = (String) JOptionPane.showInputDialog(null,
                            "Tipo de identificación del paciente:", "Seleccionar",
                            JOptionPane.QUESTION_MESSAGE, null, typesPatient, typesPatient[0]);
                    if (selectedTypePatient == null) break;
                    IdentificationType patientType = IdentificationType.valueOf(selectedTypePatient);
 
                    String patIdAppInput = JOptionPane.showInputDialog("ID del paciente:");
                    if (patIdAppInput == null) break;
                    long patientIdApp = Long.parseLong(patIdAppInput.trim());
 
                    JOptionPane.showMessageDialog(null, "--- DATOS DEL MÉDICO ---");
                    String[] typesDoctor = {"CC", "TI", "CE", "PA"};
                    String selectedTypeDoctor = (String) JOptionPane.showInputDialog(null,
                            "Tipo de identificación del médico:", "Seleccionar",
                            JOptionPane.QUESTION_MESSAGE, null, typesDoctor, typesDoctor[0]);
                    if (selectedTypeDoctor == null) break;
                    IdentificationType doctorType = IdentificationType.valueOf(selectedTypeDoctor);
 
                    String docIdInput = JOptionPane.showInputDialog("ID del médico:");
                    if (docIdInput == null) break;
                    long doctorId = Long.parseLong(docIdInput.trim());
 
                    String[] priorities = {"LOW", "MEDIUM", "HIGH", "CRITICAL"};
                    String selectedPriority = (String) JOptionPane.showInputDialog(null,
                            "Prioridad de la cita:", "Seleccionar",
                            JOptionPane.QUESTION_MESSAGE, null, priorities, priorities[0]);
                    if (selectedPriority == null) break;
                    Priority priority = Priority.valueOf(selectedPriority);
 
                    boolean success = service.scheduleAppointment(appId, time, patientType, patientIdApp,
                            doctorType, doctorId, priority);
                    if (success) {
                        JOptionPane.showMessageDialog(null, "Cita agendada exitosamente.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error: Paciente o médico no existen, o ID de cita duplicado.");
                    }
                    break;
                }
 
                case 5: {
                    // Ver Cola de Atención
                    List<MedicalAppointment> appointments = service.getSortedAppointmentsByTimeAndPriority();
                    if (appointments.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No hay citas agendadas.");
                    } else {
                        StringBuilder sb = new StringBuilder("=== COLA DE ATENCIÓN (por hora y prioridad) ===\n");
                        for (MedicalAppointment a : appointments) {
                            sb.append(a.toString()).append("\n");
                        }
                        JOptionPane.showMessageDialog(null, sb.toString());
                    }
                    break;
                }
 
                case 6: {
                    // Ver Médicos por Experiencia
                    List<Doctor> doctors = service.getDoctorsSortedByExperienceAndName();
                    if (doctors.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No hay médicos registrados.");
                    } else {
                        StringBuilder sb = new StringBuilder("=== MÉDICOS POR EXPERIENCIA (ascendente) ===\n");
                        for (Doctor d : doctors) {
                            sb.append(d.toString()).append("\n");
                        }
                        JOptionPane.showMessageDialog(null, sb.toString());
                    }
                    break;
                }
 
                case 7:
                    JOptionPane.showMessageDialog(null, "¡Hasta luego!");
                    break;
 
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida. Ingresa un número del 1 al 7.");
                    break;
            }
 
        } while (option != 7);
    }
}