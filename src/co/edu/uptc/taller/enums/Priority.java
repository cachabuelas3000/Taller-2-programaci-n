package co.edu.uptc.taller.enums;
 
/**
 * Enumeración que define los niveles de prioridad de atención médica en la clínica.
 * <p>
 * Cada nivel tiene un valor numérico entero asociado que permite comparar
 * prioridades de forma ordenada: a mayor valor, mayor urgencia.
 * </p>
 *
 * @author Narwill Daniel Zabala Mata
 * @version 1.0
 */
public enum Priority {
 
    /** Prioridad baja. Nivel 0. Pacientes que pueden esperar sin riesgo. */
    LOW(0),
 
    /** Prioridad media. Nivel 1. Pacientes que requieren atención pronta. */
    MEDIUM(1),
 
    /** Prioridad alta. Nivel 2. Pacientes con condición delicada que no debe esperar mucho. */
    HIGH(2),
 
    /** Prioridad crítica. Nivel 3. Pacientes en estado de emergencia, atención inmediata. */
    CRITICAL(3);
 
    /** Valor numérico del nivel de prioridad. Mayor valor indica mayor urgencia. */
    private int level;
 
    /**
     * Construye una constante de prioridad con su nivel numérico asociado.
     *
     * @param level valor entero que representa el nivel de urgencia
     */
    Priority(int level) {
        this.level = level;
    }
 
    /**
     * Retorna el valor numérico del nivel de prioridad.
     * Útil para comparar o ordenar prioridades de forma programática.
     *
     * @return nivel numérico de la prioridad (0 = LOW, 3 = CRITICAL)
     */
    public int getValue() {
        return level;
    }
}