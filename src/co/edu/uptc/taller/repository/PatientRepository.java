package co.edu.uptc.taller.repository;
 
import java.util.*;
import co.edu.uptc.taller.domain.Patient;
import co.edu.uptc.taller.enums.IdentificationType;
 
/**
 * Repositorio encargado de gestionar el almacenamiento de pacientes en memoria,
 * garantizando unicidad tanto por identificación como por correo electrónico.
 * <p>
 * Usa dos {@link HashMap} internos para permitir búsqueda rápida por
 * cualquiera de los dos criterios:
 * </p>
 * <ul>
 *   <li><b>patientsById</b>: clave {@code "TIPO:numero"} (ej. {@code "CC:12345678"})</li>
 *   <li><b>patientsByEmail</b>: clave email del paciente</li>
 * </ul>
 *
 * @author Narwill Daniel Zabala Mata
 * @version 1.0
 */
public class PatientRepository {
 
    /**
     * Mapa que almacena pacientes indexados por tipo y número de identificación.
     * Clave: {@code "TIPO:numero"} — Valor: objeto {@link Patient}.
     */
    private Map<String, Patient> patientsById;
 
    /**
     * Mapa que almacena pacientes indexados por correo electrónico.
     * Clave: email — Valor: objeto {@link Patient}.
     */
    private Map<String, Patient> patientsByEmail;
 
    /**
     * Construye un repositorio de pacientes vacío.
     */
    public PatientRepository() {
        this.patientsById = new HashMap<>();
        this.patientsByEmail = new HashMap<>();
    }
 
    /**
     * Genera la clave única del paciente a partir de su tipo y número de identificación.
     *
     * @param type tipo de identificación
     * @param id   número de identificación
     * @return cadena con el formato {@code "TIPO:numero"} (ej. {@code "CC:12345678"})
     */
    private String generateIdKey(IdentificationType type, long id) {
        return type.name() + ":" + id;
    }
 
    /**
     * Busca un paciente por su tipo y número de identificación.
     *
     * @param type tipo de identificación
     * @param id   número de identificación
     * @return el {@link Patient} encontrado, o {@code null} si no existe
     */
    public Patient findByIdentification(IdentificationType type, long id) {
        return patientsById.get(generateIdKey(type, id));
    }
 
    /**
     * Busca un paciente por su correo electrónico.
     *
     * @param email correo electrónico del paciente
     * @return el {@link Patient} encontrado, o {@code null} si no existe
     */
    public Patient findByEmail(String email) {
        return patientsByEmail.get(email);
    }
 
    /**
     * Retorna una lista con todos los pacientes registrados en el repositorio.
     *
     * @return lista de todos los pacientes; lista vacía si no hay ninguno
     */
    public List<Patient> getAllPatients() {
        return new ArrayList<>(patientsById.values());
    }
}