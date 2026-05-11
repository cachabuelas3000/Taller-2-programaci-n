package co.edu.uptc.taller.enums;
 
/**
 * Enumeración que representa los tipos de documento de identificación
 * permitidos en el sistema de la clínica.
 * <p>
 * Cada constante lleva asociada una descripción legible del tipo de documento,
 * accesible mediante {@link #getDescription()}.
 * </p>
 *
 * @author Narwill Daniel Zabala Mata
 * @version 1.0
 */
public enum IdentificationType {
 
    /** Cédula de Ciudadanía, documento principal para ciudadanos colombianos adultos. */
    CC("CÉDULA DE CIUDADANIA"),
 
    /** Tarjeta de Identidad, documento para menores de edad colombianos. */
    TI("TARJETA DE IDENTIDAD"),
 
    /** Cédula de Extranjería, documento para ciudadanos extranjeros residentes en Colombia. */
    CE("CÉDULA DE EXTRANGERÍA"),
 
    /** Pasaporte, documento de identificación internacional. */
    PA("PASAPORTE");
 
    /** Descripción completa y legible del tipo de identificación. */
    private String description;
 
    /**
     * Construye una constante del enum con su descripción asociada.
     *
     * @param description descripción completa del tipo de documento
     */
    IdentificationType(String description) {
        this.description = description;
    }
 
    /**
     * Retorna la descripción completa del tipo de identificación.
     *
     * @return descripción del documento (ej. {@code "CÉDULA DE CIUDADANIA"})
     */
    public String getDescription() {
        return description;
    }
}