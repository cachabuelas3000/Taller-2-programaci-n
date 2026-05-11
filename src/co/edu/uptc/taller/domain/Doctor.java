package co.edu.uptc.taller.domain;

import co.edu.uptc.taller.enums.IdentificationType;

/**
 * Entidad que representa a un médico del sistema
 * @author Narwill Daniel Zabala Mata
 */

public class Doctor {
    private IdentificationType identificationType;
    private long medicalId;
    private String firstName;
    private String lastName;
    private String specialty;
    private int yearsOfExperience;
    
    public Doctor(IdentificationType identificationType, long medicalId, 
                  String firstName, String lastName, String specialty, int yearsOfExperience) {
        this.identificationType = identificationType;
        this.medicalId = medicalId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialty = specialty;
        this.yearsOfExperience = yearsOfExperience;
    }    
    
    public IdentificationType getIdentificationType() {
		return identificationType;
	}

	public void setIdentificationType(IdentificationType identificationType) {
		this.identificationType = identificationType;
	}

	public long getMedicalId() {
		return medicalId;
	}

	public void setMedicalId(long medicalId) {
		this.medicalId = medicalId;
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

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public int getYearsOfExperience() {
		return yearsOfExperience;
	}

	public void setYearsOfExperience(int yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}
    
    public String getFullName() {
        return firstName + " " + lastName;
    }


	@Override
    public String toString() {
        return String.format("Dr. %s %s - %s (%d años exp)", 
                firstName, lastName, specialty, yearsOfExperience);
    }
}