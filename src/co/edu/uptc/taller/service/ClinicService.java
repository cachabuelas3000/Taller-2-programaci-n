package co.edu.uptc.taller.service;
 
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
 
import co.edu.uptc.taller.domain.Doctor;
import co.edu.uptc.taller.domain.MedicalAppointment;
import co.edu.uptc.taller.domain.Patient;
import co.edu.uptc.taller.enums.IdentificationType;
import co.edu.uptc.taller.enums.Priority;
import co.edu.uptc.taller.repository.DoctorRepository;
import co.edu.uptc.taller.repository.MedicalAppointmentRepository;
import co.edu.uptc.taller.repository.PatientRepository;

/**
 * Servicio principal de la clínica que actúa como intermediario entre la interfaz
 * de usuario y los repositorios de datos.
 * <p>
 * Concentra la lógica de negocio del sistema: registro de pacientes y médicos,
 * gestión del historial de medicamentos, agendamiento de citas y consulta de
 * listados ordenados.
 * </p>
 *
 * @author Narwill Daniel Zabala Mata
 * @version 1.0
 */
 
public class ClinicService {
 
    private PatientRepository patientRepository;
    private DoctorRepository doctorRepository;
    private MedicalAppointmentRepository appointmentRepository;
 
    public ClinicService() {
        this.patientRepository = new PatientRepository();
        this.doctorRepository = new DoctorRepository();
        this.appointmentRepository = new MedicalAppointmentRepository();
    }
 
    /**
     * Registra un nuevo paciente en el sistema.
     * CORRECCIÓN: ahora recibe Priority para satisfacer el constructor de Patient
     */
    public boolean registerPatient(IdentificationType type, long id,
                                   String firstName, String lastName,
                                   String email, Priority priority) {
        Patient patient = new Patient(type, id, firstName, lastName, email, priority);
        return patientRepository.save(patient);
    }
 
    /**
     * Registra un nuevo médico en el sistema.
     */
    public boolean registerDoctor(IdentificationType type, long medicalId,
                                  String firstName, String lastName,
                                  String specialty, int yearsOfExperience) {
        Doctor doctor = new Doctor(type, medicalId, firstName, lastName, specialty, yearsOfExperience);
        return doctorRepository.save(doctor);
    }
 
    /**
     * Agrega un medicamento al historial de un paciente.
     */
    public boolean addMedicineToPatient(IdentificationType type, long patientId, String medicine) {
        Patient patient = patientRepository.findByIdentification(type, patientId);
        if (patient == null) return false;
        return patient.addMedicine(medicine);
    }
 
    /**
     * Agenda una cita médica verificando que paciente y médico existan.
     */
    public boolean scheduleAppointment(long appId, LocalDateTime time,
                                       IdentificationType patientType, long patientId,
                                       IdentificationType doctorType, long doctorId,
                                       Priority priority) {
        Patient patient = patientRepository.findByIdentification(patientType, patientId);
        Doctor doctor = doctorRepository.findByIdentification(doctorType, doctorId);
        if (patient == null || doctor == null) return false;
 
        patient.setPriority(priority);
 
        MedicalAppointment appointment = new MedicalAppointment(appId, time, patient, doctor);
        return appointmentRepository.save(appointment);
    }
 
    /**
     * Retorna las citas ordenadas por hora y luego por prioridad descendente.
     */
    public List<MedicalAppointment> getSortedAppointmentsByTimeAndPriority() {
        List<MedicalAppointment> list = appointmentRepository.getAllAppointments();
        list.sort(Comparator
                .comparing(MedicalAppointment::getTimeAppointment)
                .thenComparing(a -> -a.getPriority().getValue()));
        return list;
    }
 
    /**
     * Retorna los médicos ordenados por experiencia ascendente y nombre alfabéticamente.
     */
    public List<Doctor> getDoctorsSortedByExperienceAndName() {
        List<Doctor> list = doctorRepository.getAllDoctors();
        list.sort(Comparator
                .comparingInt(Doctor::getYearsOfExperience)
                .thenComparing(Doctor::getLastName)
                .thenComparing(Doctor::getFirstName));
        return list;
    }
}