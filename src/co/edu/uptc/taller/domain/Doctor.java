package co.edu.uptc.taller.domain;
 
import co.edu.uptc.taller.enums.IdentificationType;
 
/**
 * Entidad que representa a un médico registrado en el sistema de la clínica.
 * <p>
 * Almacena los datos personales y profesionales del médico, incluyendo
 * su tipo y número de identificación, nombre completo, especialidad
 * y años de experiencia.
 * </p>
 *
 * @author Narwill Daniel Zabala Mata
 * @version 1.0
 */
public class Doctor {
 
    /** Tipo de documento de identificación del médico. */
    private IdentificationType identificationType;
 
    /** Número de identificación único del médico. */
    private long medicalId;
 
    /** Nombre del médico. */
    private String firstName;
 
    /** Apellido del médico. */
    private String lastName;
 
    /** Especialidad médica del doctor (ej. Cardiología, Pediatría). */
    private String specialty;
 
    /** Años de experiencia profesional del médico. */
    private int yearsOfExperience;
 
    /**
     * Construye un nuevo médico con todos sus datos.
     *
     * @param identificationType tipo de documento de identificación
     * @param medicalId          número de identificación del médico
     * @param firstName          nombre del médico
     * @param lastName           apellido del médico
     * @param specialty          especialidad médica
     * @param yearsOfExperience  años de experiencia profesional
     */
    public Doctor(IdentificationType identificationType, long medicalId,
                  String firstName, String lastName, String specialty, int yearsOfExperience) {
        this.identificationType = identificationType;
        this.medicalId = medicalId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialty = specialty;
        this.yearsOfExperience = yearsOfExperience;
    }
 
    /**
     * Retorna el tipo de identificación del médico.
     *
     * @return tipo de identificación
     */
    public IdentificationType getIdentificationType() {
        return identificationType;
    }
 
    /**
     * Establece el tipo de identificación del médico.
     *
     * @param identificationType nuevo tipo de identificación
     */
    public void setIdentificationType(IdentificationType identificationType) {
        this.identificationType = identificationType;
    }
 
    /**
     * Retorna el número de identificación del médico.
     *
     * @return número de identificación
     */
    public long getMedicalId() {
        return medicalId;
    }
 
    /**
     * Establece el número de identificación del médico.
     *
     * @param medicalId nuevo número de identificación
     */
    public void setMedicalId(long medicalId) {
        this.medicalId = medicalId;
    }
 
    /**
     * Retorna el nombre del médico.
     *
     * @return nombre
     */
    public String getFirstName() {
        return firstName;
    }
 
    /**
     * Establece el nombre del médico.
     *
     * @param firstName nuevo nombre
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
 
    /**
     * Retorna el apellido del médico.
     *
     * @return apellido
     */
    public String getLastName() {
        return lastName;
    }
 
    /**
     * Establece el apellido del médico.
     *
     * @param lastName nuevo apellido
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
 
    /**
     * Retorna la especialidad médica del doctor.
     *
     * @return especialidad
     */
    public String getSpecialty() {
        return specialty;
    }
 
    /**
     * Establece la especialidad médica del doctor.
     *
     * @param specialty nueva especialidad
     */
    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }
 
    /**
     * Retorna los años de experiencia profesional del médico.
     *
     * @return años de experiencia
     */
    public int getYearsOfExperience() {
        return yearsOfExperience;
    }
 
    /**
     * Establece los años de experiencia profesional del médico.
     *
     * @param yearsOfExperience nuevos años de experiencia
     */
    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }
 
    /**
     * Retorna el nombre completo del médico (nombre + apellido).
     *
     * @return nombre completo
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }
 
    /**
     * Retorna una representación en texto del médico con sus datos principales.
     *
     * @return cadena con el formato: {@code Dr. Nombre Apellido - Especialidad (X años exp)}
     */
    @Override
    public String toString() {
        return String.format("Dr. %s %s - %s (%d años exp)",
                firstName, lastName, specialty, yearsOfExperience);
    }
}