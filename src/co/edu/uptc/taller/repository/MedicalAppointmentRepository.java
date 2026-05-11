package co.edu.uptc.taller.repository;

import java.util.*;

import co.edu.uptc.taller.domain.MedicalAppointment;

/**
 * Repositorio para gestionar citas médicas
 * @author TuNombre
 */
public class MedicalAppointmentRepository {
    private Map<Long, MedicalAppointment> appointmentsById;
    
    public MedicalAppointmentRepository() {
        this.appointmentsById = new HashMap<>();
    }
    
    public boolean save(MedicalAppointment appointment) {
        if (appointmentsById.containsKey(appointment.getIdMedicalAppointment())) {
            return false;
        }
        appointmentsById.put(appointment.getIdMedicalAppointment(), appointment);
        return true;
    }
    
    public List<MedicalAppointment> getAllAppointments() {
        return new ArrayList<>(appointmentsById.values());
    }
}