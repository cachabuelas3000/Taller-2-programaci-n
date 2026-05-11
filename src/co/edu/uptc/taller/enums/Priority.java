package co.edu.uptc.taller.enums;

public enum Priority {
	
	BAJO(0),
	MEDIO(1),
	ALTO(2),
	CRÍTICO(3);
	
	private int level;
	
	
	Priority(int level) {
		this.level = level;
	}
	
	public int getLevel() {
		return level;
	}

}
