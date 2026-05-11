package co.edu.uptc.taller.domain;

import java.time.LocalDateTime;

import co.edu.uptc.taller.enums.Priority;

/**
 * Entidad que representa una cita médica
 * @author Narwill Daniel Zabala Mata 
 */

public class MedicalAppointment {
    private long idMedicalAppointment;
    private LocalDateTime timeAppointment;
    private Patient patient;
    private Doctor doctor;
    
    public MedicalAppointment(long idMedicalAppointment, LocalDateTime timeAppointment, 
                              Patient patient, Doctor doctor) {
        this.idMedicalAppointment = idMedicalAppointment;
        this.timeAppointment = timeAppointment;
        this.patient = patient;
        this.doctor = doctor;
    }

	public long getIdMedicalAppointment() {
		return idMedicalAppointment;
	}

	public void setIdMedicalAppointment(long idMedicalAppointment) {
		this.idMedicalAppointment = idMedicalAppointment;
	}

	public LocalDateTime getTimeAppointment() {
		return timeAppointment;
	}

	public void setTimeAppointment(LocalDateTime timeAppointment) {
		this.timeAppointment = timeAppointment;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
    
	public Priority getPriority() {
        return patient != null ? patient.getPriority() : Priority.LOW;
    }
	
	@Override
    public String toString() {
        return String.format("Cita %d: %s - Dr. %s (Prioridad: %s)", 
                idMedicalAppointment, timeAppointment.toString(), 
                doctor.getFullName(), getPriority());
    }
    
}