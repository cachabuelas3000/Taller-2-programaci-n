package co.edu.uptc.taller.enums;

/**
 * Enum que representa los tipos de identificación permitidos en el sistema
 * @author Narwill Daniel Zabala Mata
 */

public enum IdentificationType {
	
	CC ("CÉDULA DE CIUDADANIA"),
	TI ("TARJETA DE IDENTIDAD"),
	CE ("CÉDULA DE EXTRANGERÍA"),
	PA ("PASAPORTE");
	
	private String description;
	
	IdentificationType(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	
}
