package co.edu.uptc.taller.repository;


import java.util.*;

import co.edu.uptc.taller.domain.Doctor;
import co.edu.uptc.taller.enums.IdentificationType;

/**
 * Repositorio para gestionar médicos garantizando unicidad
 * @author Narwill Daniel Zabala Mata
 */
public class DoctorRepository {
    private Map<String, Doctor> doctorsById;
    
    public DoctorRepository() {
        this.doctorsById = new HashMap<>();
    }
    
    private String generateIdKey(IdentificationType type, long id) {
        return type.name() + ":" + id;
    }
    
    public boolean save(Doctor doctor) {
        String idKey = generateIdKey(doctor.getIdentificationType(), doctor.getMedicalId());
        if (doctorsById.containsKey(idKey)) return false;
        doctorsById.put(idKey, doctor);
        return true;
    }
    
    public Doctor findByIdentification(IdentificationType type, long id) {
        return doctorsById.get(generateIdKey(type, id));
    }
    
    public List<Doctor> getAllDoctors() {
        return new ArrayList<>(doctorsById.values());
    }
}