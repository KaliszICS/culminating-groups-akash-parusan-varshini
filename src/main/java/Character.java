/**
 * Abstract base class representing a character in the turn-based RPG game.
 * Provides common attributes and methods for managing character health,
 * attacks, and turn-based actions. All specific character types (player,
 * enemies, etc.) must extend this class and implement their own turn logic.
 * 
 * @author [Parusan]
 * @author [Akash]
 * @author [Varshini]
 * @version 1.0
 */
public abstract class Character {

     /**
     * The current health points of the character. When this reaches 0,
     * the character is considered defeated.
     */
    protected int health;

     /**
     * The base attack power of the character. Represents the amount of
     * damage the character can deal in a single attack.
     */
    protected int attack;
    /**
     * The maximum health a character can have. Health cannot exceed this value.
     * Default value is 100 for all characters.
     */
    protected final int MAX_HEALTH = 100;
     /**
     * Executes the character's turn in combat.
     * This is an abstract method that must be implemented by subclasses
     * to define specific behavior for each character type (player input,
     * enemy AI, etc.).
     */

    public abstract void takeTurn();
     /**
     * Checks if the character is currently alive.
     * 
     * @return {@code true} if the character's health is greater than 0,
     *         {@code false} otherwise
     */

    public boolean isAlive() {
        return health > 0;
    }
     /**
     * Returns the current health of the character.
     * 
     * @return the current health points
     */

    public int getHealth() {
        return health;
    }
     /**
     * Reduces the character's health by the specified damage amount.
     * Ensures health does not fall below 0.
     * 
     * @param dmg the amount of damage to inflict (must be non-negative)
     */

    public void takeDamage(int dmg) {
        health -= dmg;
        if (health < 0) health = 0;
    }

    /**
     * Increases the character's health by the specified amount.
     * Ensures health does not exceed {@link #MAX_HEALTH}.
     * 
     * @param amount the amount of health to restore (must be non-negative)
     */

    public void heal(int amount) {
        health += amount;
        if (health > MAX_HEALTH) {
            health = MAX_HEALTH;
        }
    }
}