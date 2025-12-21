/**
 * Represents an enemy character in the RPG game. Extends the base Character class
 * and includes enemy-specific attributes such as a name. Enemies have their own
 * AI-driven turn behavior implemented in {@code takeTurn()}.
 * 
 * @author [Parusan]
 * @author [Akash]
 * @author [Varshini]
 * @version 1.0
 */
public class Enemy extends Character {
    
    /**
     * The name of the enemy character (e.g., "Goblin", "Dragon").
     * Used for display purposes and identification in combat.
     */
    private String name;

    /**
     * Constructs a new Enemy with the specified attributes.
     * 
     * @param name the name of the enemy
     * @param health the initial health points of the enemy
     * @param attack the base attack power of the enemy
     */
    public Enemy(String name, int health, int attack) {
        this.name = name;
        this.health = health;
        this.attack = attack;
    }

    /**
     * Executes the enemy's turn during combat.
     * This method should implement the enemy's AI behavior, such as
     * choosing targets, using abilities, or performing attacks.
     * Currently unimplemented - requires enemy AI logic.
     */
    @Override
    public void takeTurn() {
        // TODO: Implement enemy AI logic for combat turns
        // Example: target player, choose attack or ability, etc.
    }

    /**
     * Returns the name of this enemy.
     * 
     * @return the enemy's name
     */
    public String getName() {
        return name;
    }
}