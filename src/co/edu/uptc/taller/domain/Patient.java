package co.edu.uptc.taller.domain;
 
import java.util.LinkedHashSet;
import java.util.Set;
 
import co.edu.uptc.taller.enums.IdentificationType;
import co.edu.uptc.taller.enums.Priority;
 
/**
 * Entidad que representa a un paciente registrado en el sistema de la clínica.
 * <p>
 * Almacena los datos personales del paciente, su nivel de prioridad de atención
 * y el historial de medicamentos que le han sido asignados. El historial usa un
 * {@link LinkedHashSet} para garantizar que no haya medicamentos duplicados y que
 * se conserve el orden de inserción.
 * </p>
 *
 * @author Narwill Daniel Zabala Mata
 * @version 1.0
 */
public class Patient {
 
    /** Tipo de documento de identificación del paciente. */
    private IdentificationType identificationType;
 
    /** Número de identificación único del paciente. */
    private long idPatient;
 
    /** Nombre del paciente. */
    private String firstName;
 
    /** Apellido del paciente. */
    private String lastName;
 
    /** Correo electrónico del paciente, usado también como identificador único. */
    private String email;
 
    /**
     * Historial de medicamentos del paciente.
     * Se usa {@link LinkedHashSet} para evitar duplicados y mantener el orden de registro.
     */
    private LinkedHashSet<String> medicationHistory;
 
    /** Nivel de prioridad de atención del paciente. */
    private Priority priority;
 
    /**
     * Construye un nuevo paciente con todos sus datos iniciales.
     * El historial de medicamentos se inicializa vacío.
     *
     * @param identificationType tipo de documento de identificación
     * @param idPatient          número de identificación del paciente
     * @param firstName          nombre del paciente
     * @param lastName           apellido del paciente
     * @param email              correo electrónico del paciente
     * @param priority           nivel de prioridad de atención
     */
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
 
    /**
     * Retorna el tipo de identificación del paciente.
     *
     * @return tipo de identificación
     */
    public IdentificationType getIdentificationType() {
        return identificationType;
    }
 
    /**
     * Establece el tipo de identificación del paciente.
     *
     * @param identificationType nuevo tipo de identificación
     */
    public void setIdentificationType(IdentificationType identificationType) {
        this.identificationType = identificationType;
    }
 
    /**
     * Retorna el número de identificación del paciente.
     *
     * @return número de identificación
     */
    public long getIdPatient() {
        return idPatient;
    }
 
    /**
     * Establece el número de identificación del paciente.
     *
     * @param idPatient nuevo número de identificación
     */
    public void setIdPatient(long idPatient) {
        this.idPatient = idPatient;
    }
 
    /**
     * Retorna el nombre del paciente.
     *
     * @return nombre
     */
    public String getFirstName() {
        return firstName;
    }
 
    /**
     * Establece el nombre del paciente.
     *
     * @param firstName nuevo nombre
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
 
    /**
     * Retorna el apellido del paciente.
     *
     * @return apellido
     */
    public String getLastName() {
        return lastName;
    }
 
    /**
     * Establece el apellido del paciente.
     *
     * @param lastName nuevo apellido
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
 
    /**
     * Retorna el correo electrónico del paciente.
     *
     * @return email
     */
    public String getEmail() {
        return email;
    }
 
    /**
     * Establece el correo electrónico del paciente.
     *
     * @param email nuevo email
     */
    public void setEmail(String email) {
        this.email = email;
    }
 
    /**
     * Retorna el historial de medicamentos del paciente.
     * La colección no contiene duplicados y mantiene el orden de inserción.
     *
     * @return conjunto de nombres de medicamentos
     */
    public LinkedHashSet<String> getMedicationHistory() {
        return medicationHistory;
    }
 
    /**
     * Retorna el nivel de prioridad de atención del paciente.
     *
     * @return prioridad
     */
    public Priority getPriority() {
        return priority;
    }
 
    /**
     * Establece el nivel de prioridad de atención del paciente.
     *
     * @param priority nueva prioridad
     */
    public void setPriority(Priority priority) {
        this.priority = priority;
    }
 
    /**
     * Retorna el nombre completo del paciente (nombre + apellido).
     *
     * @return nombre completo
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }
 
    /**
     * Agrega un medicamento al historial del paciente.
     * Si el medicamento ya existe en el historial, no se agrega de nuevo.
     *
     * @param medicine nombre del medicamento a agregar
     * @return {@code true} si el medicamento fue agregado; {@code false} si ya existía
     */
    public boolean addMedicine(String medicine) {
        return medicationHistory.add(medicine);
    }
 
    /**
     * Retorna una representación en texto del paciente con sus datos principales.
     *
     * @return cadena con el formato: {@code Paciente: Nombre Apellido (TIPO: ID) - Prioridad: PRIORIDAD}
     */
    @Override
    public String toString() {
        return String.format("Paciente: %s %s (%s: %d) - Prioridad: %s",
                firstName, lastName, identificationType, idPatient, priority);
    }
}