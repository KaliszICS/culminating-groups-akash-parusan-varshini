<<<<<<< HEAD
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
=======
/**
 * Represents a Goblin enemy in the RPG game. A basic enemy type with moderate
 * health and attack stats. Extends the Enemy class to provide specific
 * attributes and behavior for goblin-type creatures.
 * 
 * @author [Parusan]
 * @author [Akash]
 * @author [Varshini]
 * @version 1.0
 */
public class Goblin extends Enemy {
    
    /**
     * Constructs a new Goblin enemy with predefined stats:
     * - Name: "Goblin"
     * - Health: 40 points
     * - Attack: 10 points
     * These values represent a standard goblin enemy in the game.
     */
    public Goblin() {
        super("Goblin", 40, 10);
    }

     // Note: This class inherits the takeTurn() method from Enemy
    // If you want goblin-specific AI behavior, override takeTurn() here
}
>>>>>>> e5a5c4dc231ee3fb8b8824259ad1490b7c593ccf
