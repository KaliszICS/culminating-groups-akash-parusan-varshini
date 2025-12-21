package game.characters;

/**
 * The Goblin class represents a basic enemy type in the game.
 * It is a specific kind of Enemy with predefined health and attack values.
 *
 * @author Akash K.
 * @author Parusan P.
 * @author Varshini B.
 * @version 1.0
 */
// [Classes]
// [Inheritance]
public class Goblin extends Enemy {

    /**
     * Constructs a Goblin with fixed stats.
     * Calls the Enemy constructor to initialize name, health, and attack.
     */
    // [Inheritance]
    public Goblin() {
        super("Goblin", 40, 10);
    }
}
