package game.characters;

import game.inventory.Inventory;
import game.inventory.Saveable;

/**
 * The Player class represents the user-controlled character in the game.
 * The player has health, attack power, an inventory, and a position
 * within the game world.
 *
 * This class extends the abstract Character class and implements
 * the Saveable interface so that player data can be written to a file.
 *
 * @author Akash K.
 * @author Parusan P.
 * @author Varshini B.
 * @version 1.0
 */
// [Classes]
// [Inheritance]
// [Abstract classes and interfaces]
// [Polymorphism]
public class Player extends Character implements Saveable {

    /** The player's inventory */
    private Inventory inventory;

    /** Player's X-coordinate in the world */
    private int x = 1;

    /** Player's Y-coordinate in the world */
    private int y = 1;

    /**
     * Constructs a Player with default stats.
     * Initializes health, attack power, and inventory.
     */
    // [Classes]
    public Player() {
        this.maxHealth = 100;
        this.health = 100;
        this.attack = 15;
        inventory = new Inventory();
    }

    /**
     * Constructs a Player with custom health and attack values.
     *
     * @param health initial health value
     * @param attack initial attack value
     */
    // [Classes]
    public Player(int health, int attack) {
        this.health = health;
        this.attack = attack;
        inventory = new Inventory();
    }

    /**
     * Defines how the player takes a turn.
     * Overridden from Character, though player turns are
     * controlled externally by user input.
     *
     * @param player the player affected (not used here)
     */
    // [Overriding]
    @Override
    public void takeTurn(Player player) {
        // Player actions are handled via input, not automatic turns
    }

    /**
     * Returns the player's inventory.
     *
     * @return the Inventory object
     */
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * Converts player data into a savable string format.
     *
     * @return a string containing health and attack values
     */
    // [File I/O]
    @Override
    public String saveData() {
        return health + "," + attack;
    }

    /**
     * Sets the player's health.
     *
     * @param health new health value
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Returns the player's X-coordinate.
     *
     * @return x position
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the player's Y-coordinate.
     *
     * @return y position
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the player's X-coordinate.
     *
     * @param x new x position
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Sets the player's Y-coordinate.
     *
     * @param y new y position
     */
    public void setY(int y) {
        this.y = y;
    }
}