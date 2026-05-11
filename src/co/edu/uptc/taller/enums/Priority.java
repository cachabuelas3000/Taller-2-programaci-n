package co.edu.uptc.taller.enums;

/**
 * Enum que define la prioridad de atención médica
 * @author Narwill Daniel Zabala Mata
 */

public enum Priority {
    LOW(0),
    MEDIUM(1),
    HIGH(2),
    CRITICAL(3);
    
    private int level;
    
    Priority(int level) {
        this.level = level;
    }
    
    public int getValue() {
        return level;
    }
}