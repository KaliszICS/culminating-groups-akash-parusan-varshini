/**
 * Interface for objects that can serialize their state for game saving.
 * Implementing classes define their own data format for persistence.
 * 
 * @author [Parusan]
 * @author [Akash]
 * @author [Varshini]
 * @version 1.0
 */
public interface Saveable {
    
    /**
     * Returns object state as a string for saving.
     * 
     * @return serialized state data
     */
    String saveData();
}