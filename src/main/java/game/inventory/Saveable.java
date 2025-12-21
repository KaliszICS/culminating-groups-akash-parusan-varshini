package game.inventory;

/**
 * The Saveable interface defines a contract for objects
 * whose data can be saved to a file.
 * Any class that implements this interface must provide
 * a string representation of its data for persistence.
 *
 * @author Akash K.
 * @author Parusan P.
 * @author Varshini B.
 * @version 1.0
 */
// [Abstract classes and interfaces]
// [File I/O]
public interface Saveable {

    /**
     * Converts the object's state into a string format
     * suitable for saving to a file.
     *
     * @return a string containing the object's save data
     */
    String saveData();
}
