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