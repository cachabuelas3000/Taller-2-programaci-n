package co.edu.uptc.taller.enums;

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
