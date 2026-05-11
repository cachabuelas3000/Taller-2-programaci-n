package co.edu.uptc.taller.domain;

import java.util.LinkedHashSet;
import java.util.Set;

import co.edu.uptc.taller.enums.IdentificationType;
import co.edu.uptc.taller.enums.Priority;

/**
 * Entidad que representa a un paciente del sistema
 * @author Narwill Daniel Zabala Mata
 */

public class Patient {
    private IdentificationType identificationType;
    private long idPatient;
    private String firstName;
    private String lastName;
    private String email;
    private LinkedHashSet<String> medicationHistory; 
    private Priority priority;
    
    public Patient(IdentificationType identificationType, long idPatient, 
                   String firstName, String lastName, String email, Priority priority) {
        this.identificationType = identificationType;
        this.idPatient = idPatient;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.medicationHistory = new LinkedHashSet<>();
        this.priority = priority;
    }
    
    public IdentificationType getIdentificationType() { 
    	return identificationType;
    }
    
    public void setIdentificationType(IdentificationType identificationType) {
    	this.identificationType = identificationType;
    }
    
    public long getIdPatient() {
    	return idPatient;
    }
    
    public void setIdPatient(long idPatient) {
    	this.idPatient = idPatient;
    }
    
    public String getFirstName() {
    	return firstName;
    }
    
    public void setFirstName(String firstName) {
    	this.firstName = firstName;
    }
    
    public String getLastName() {
    	return lastName;
    }
    
    public void setLastName(String lastName) {
    	this.lastName = lastName;
    }
    
    public String getEmail() {
    	return email;
    }
    
    public void setEmail(String email) {
    	this.email = email;
    }
    
    public LinkedHashSet<String> getMedicationHistory() {
    	return medicationHistory;
    }
    
    public Priority getPriority() {
    	return priority;
    }
    
    public void setPriority(Priority priority) {
    	this.priority = priority;
    }
    
    public String getFullName() {
        return firstName + " " + lastName;
    }
    
    public boolean addMedicine(String medicine) {
        return medicationHistory.add(medicine);
    }
    
    @Override
    public String toString() {
        return String.format("Paciente: %s %s (%s: %d) - Prioridad: %s", 
                firstName, lastName, identificationType, idPatient, priority);
    }

    
}