package game.characters;

/**
 * The Character class represents a generic character in the game.
 * It defines shared attributes and combat-related behavior for all
 * characters such as Player and Enemy.
 *
 * This class enforces that all subclasses define their own turn behavior.
 *
 * @author Akash K.
 * @author Parusan P.
 * @author Varshini B.
 * @version 1.0
 */
// [Abstract Class]
// [Inheritance]
public abstract class Character {

    /** Current health of the character */
    protected int health;

    /** Base attack damage of the character */
    protected int attack;

    /** Maximum possible health of the character */
    protected int maxHealth;

    /**
     * Defines how the character takes a turn during combat.
     * This method must be implemented by all subclasses.
     *
     * @param player the player affected by this character's turn
     */
    // [Polymorphism]
    public abstract void takeTurn(Player player);

    /**
     * Checks whether the character is still alive.
     *
     * @return true if health is greater than zero, false otherwise
     */
    public boolean isAlive() {
        return health > 0;
    }

    /**
     * Returns the current health of the character.
     *
     * @return the current health value
     */
    public int getHealth() {
        return health;
    }

    /**
     * Returns the maximum health of the character.
     *
     * @return the maximum health value
     */
    public int getMaxHealth() {
        return maxHealth;
    }

    /**
     * Reduces the character's health by a specified amount.
     * Health will not drop below zero.
     *
     * @param dmg the amount of damage taken
     */
    public void takeDamage(int dmg) {
        health -= dmg;
        if (health < 0) {
            health = 0;
        }
    }

    /**
     * Restores health to the character by a specified amount.
     * Health will not exceed the maximum health.
     *
     * @param amount the amount of health restored
     */
    // [Overloading] (used when subclasses is defining multiple heal methods)
    public void heal(int amount) {
        health += amount;
        if (health > maxHealth) {
            health = maxHealth;
        }
    }

    /**
     * Returns the attack value of the character.
     *
     * @return the attack damage value
     */
    public int getAttack() {
        return attack;
    }
}
