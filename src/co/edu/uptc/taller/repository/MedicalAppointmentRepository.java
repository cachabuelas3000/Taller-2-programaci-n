package co.edu.uptc.taller.repository;
 
import java.util.*;
 
import co.edu.uptc.taller.domain.MedicalAppointment;
 
/**
 * Repositorio encargado de gestionar el almacenamiento de citas médicas en memoria,
 * garantizando que no haya dos citas con el mismo identificador.
 * <p>
 * Internamente usa un {@link HashMap} donde la clave es el id numérico de la cita
 * y el valor es el objeto {@link MedicalAppointment} correspondiente.
 * </p>
 *
 * @author Narwill Daniel Zabala Mata
 * @version 1.0
 */
public class MedicalAppointmentRepository {
 
    /**
     * Mapa que almacena las citas médicas indexadas por su identificador único.
     * Clave: id de la cita — Valor: objeto {@link MedicalAppointment}.
     */
    private Map<Long, MedicalAppointment> appointmentsById;
 
    /**
     * Construye un repositorio de citas médicas vacío.
     */
    public MedicalAppointmentRepository() {
        this.appointmentsById = new HashMap<>();
    }
 
    /**
     * Guarda una cita médica en el repositorio si no existe otra con el mismo id.
     *
     * @param appointment cita médica a guardar
     * @return {@code true} si fue guardada exitosamente; {@code false} si el id ya existía
     */
    public boolean save(MedicalAppointment appointment) {
        if (appointmentsById.containsKey(appointment.getIdMedicalAppointment())) {
            return false;
        }
        appointmentsById.put(appointment.getIdMedicalAppointment(), appointment);
        return true;
    }
 
    /**
     * Retorna una lista con todas las citas médicas registradas en el repositorio.
     *
     * @return lista de todas las citas; lista vacía si no hay ninguna
     */
    public List<MedicalAppointment> getAllAppointments() {
        return new ArrayList<>(appointmentsById.values());
    }
}