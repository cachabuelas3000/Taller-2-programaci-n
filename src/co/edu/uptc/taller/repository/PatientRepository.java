package co.edu.uptc.taller.repository;


import java.util.*;
import co.edu.uptc.taller.domain.Patient;
import co.edu.uptc.taller.enums.IdentificationType;

/**
 * Repositorio para gestionar pacientes garantizando unicidad
 * @author Narwill Daniel Zabala Mata
 */

public class PatientRepository {
    private Map<String, Patient> patientsById;      // Clave: tipoID:numeroID
    private Map<String, Patient> patientsByEmail;   // Clave: email
    
    public PatientRepository() {
        this.patientsById = new HashMap<>();
        this.patientsByEmail = new HashMap<>();
    }
    
    private String generateIdKey(IdentificationType type, long id) {
        return type.name() + ":" + id;
    }
    
    public Patient findByIdentification(IdentificationType type, long id) {
        return patientsById.get(generateIdKey(type, id));
    }
    
    public Patient findByEmail(String email) {
        return patientsByEmail.get(email);
    }
    
    public List<Patient> getAllPatients() {
        return new ArrayList<>(patientsById.values());
    }
    
}