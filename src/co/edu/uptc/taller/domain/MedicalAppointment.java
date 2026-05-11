package co.edu.uptc.taller.domain;
 
import java.time.LocalDateTime;
 
import co.edu.uptc.taller.enums.Priority;
 
/**
 * Entidad que representa una cita médica agendada en el sistema de la clínica.
 * <p>
 * Asocia un {@link Patient} con un {@link Doctor} en una fecha y hora específica.
 * La prioridad de la cita se delega al paciente asociado.
 * </p>
 *
 * @author Narwill Daniel Zabala Mata
 * @version 1.0
 */
public class MedicalAppointment {
 
    /** Identificador único de la cita médica. */
    private long idMedicalAppointment;
 
    /** Fecha y hora en que está programada la cita. */
    private LocalDateTime timeAppointment;
 
    /** Paciente al que corresponde la cita. */
    private Patient patient;
 
    /** Médico que atenderá la cita. */
    private Doctor doctor;
 
    /**
     * Construye una nueva cita médica con todos sus datos.
     *
     * @param idMedicalAppointment identificador único de la cita
     * @param timeAppointment      fecha y hora de la cita
     * @param patient              paciente que asistirá a la cita
     * @param doctor               médico que atenderá la cita
     */
    public MedicalAppointment(long idMedicalAppointment, LocalDateTime timeAppointment,
                              Patient patient, Doctor doctor) {
        this.idMedicalAppointment = idMedicalAppointment;
        this.timeAppointment = timeAppointment;
        this.patient = patient;
        this.doctor = doctor;
    }
 
    /**
     * Retorna el identificador único de la cita médica.
     *
     * @return id de la cita
     */
    public long getIdMedicalAppointment() {
        return idMedicalAppointment;
    }
 
    /**
     * Establece el identificador único de la cita médica.
     *
     * @param idMedicalAppointment nuevo id de la cita
     */
    public void setIdMedicalAppointment(long idMedicalAppointment) {
        this.idMedicalAppointment = idMedicalAppointment;
    }
 
    /**
     * Retorna la fecha y hora programada de la cita.
     *
     * @return fecha y hora de la cita
     */
    public LocalDateTime getTimeAppointment() {
        return timeAppointment;
    }
 
    /**
     * Establece la fecha y hora de la cita.
     *
     * @param timeAppointment nueva fecha y hora
     */
    public void setTimeAppointment(LocalDateTime timeAppointment) {
        this.timeAppointment = timeAppointment;
    }
 
    /**
     * Retorna el paciente asociado a la cita.
     *
     * @return paciente de la cita
     */
    public Patient getPatient() {
        return patient;
    }
 
    /**
     * Establece el paciente asociado a la cita.
     *
     * @param patient nuevo paciente
     */
    public void setPatient(Patient patient) {
        this.patient = patient;
    }
 
    /**
     * Retorna el médico que atenderá la cita.
     *
     * @return médico de la cita
     */
    public Doctor getDoctor() {
        return doctor;
    }
 
    /**
     * Establece el médico que atenderá la cita.
     *
     * @param doctor nuevo médico
     */
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
 
    /**
     * Retorna la prioridad de atención de la cita, tomada del paciente asociado.
     * Si no hay paciente asignado, retorna {@link Priority#LOW} por defecto.
     *
     * @return prioridad de la cita
     */
    public Priority getPriority() {
        return patient != null ? patient.getPriority() : Priority.LOW;
    }
 
    /**
     * Retorna una representación en texto de la cita médica.
     *
     * @return cadena con el formato: {@code Cita ID: FECHA - Dr. Nombre (Prioridad: PRIORIDAD)}
     */
    @Override
    public String toString() {
        return String.format("Cita %d: %s - Dr. %s (Prioridad: %s)",
                idMedicalAppointment, timeAppointment.toString(),
                doctor.getFullName(), getPriority());
    }
}