package co.edu.uptc.taller.repository;
 
import java.util.*;
 
import co.edu.uptc.taller.domain.Doctor;
import co.edu.uptc.taller.enums.IdentificationType;
 
/**
 * Repositorio encargado de gestionar el almacenamiento de médicos en memoria,
 * garantizando que no haya duplicados por tipo y número de identificación.
 * <p>
 * Internamente usa un {@link HashMap} donde la clave es una cadena compuesta
 * por el tipo de identificación y el número, en el formato {@code "TIPO:numero"}
 * (ej. {@code "CC:12345678"}).
 * </p>
 *
 * @author Narwill Daniel Zabala Mata
 * @version 1.0
 */
public class DoctorRepository {
 
    /**
     * Mapa que almacena los médicos indexados por su clave de identificación.
     * Clave: {@code "TIPO:numero"} — Valor: objeto {@link Doctor}.
     */
    private Map<String, Doctor> doctorsById;
 
    /**
     * Construye un repositorio de médicos vacío.
     */
    public DoctorRepository() {
        this.doctorsById = new HashMap<>();
    }
 
    /**
     * Genera la clave única del médico a partir de su tipo y número de identificación.
     *
     * @param type tipo de identificación
     * @param id   número de identificación
     * @return cadena con el formato {@code "TIPO:numero"} (ej. {@code "CC:12345678"})
     */
    private String generateIdKey(IdentificationType type, long id) {
        return type.name() + ":" + id;
    }
 
    /**
     * Guarda un médico en el repositorio si no existe uno con la misma identificación.
     *
     * @param doctor médico a guardar
     * @return {@code true} si fue guardado exitosamente; {@code false} si ya existía
     */
    public boolean save(Doctor doctor) {
        String idKey = generateIdKey(doctor.getIdentificationType(), doctor.getMedicalId());
        if (doctorsById.containsKey(idKey)) return false;
        doctorsById.put(idKey, doctor);
        return true;
    }
 
    /**
     * Busca un médico por su tipo y número de identificación.
     *
     * @param type tipo de identificación
     * @param id   número de identificación
     * @return el {@link Doctor} encontrado, o {@code null} si no existe
     */
    public Doctor findByIdentification(IdentificationType type, long id) {
        return doctorsById.get(generateIdKey(type, id));
    }
 
    /**
     * Retorna una lista con todos los médicos registrados en el repositorio.
     *
     * @return lista de todos los médicos; lista vacía si no hay ninguno
     */
    public List<Doctor> getAllDoctors() {
        return new ArrayList<>(doctorsById.values());
    }
}