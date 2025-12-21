<<<<<<< HEAD
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
=======
/**
 * Represents the player character in the GridQuest RPG.
 * Extends the base Character class and implements the Saveable interface
 * for game state persistence. Manages player-specific attributes including
 * inventory management and positional coordinates on the game map.
 * 
 * Demonstrates key ICS4U concepts:
 * - Inheritance (extends Character)
 * - Interfaces (implements Saveable)
 * - Encapsulation with getters/setters
 * - Object composition (has-an Inventory)
 * 
 * @author [Parusan]
 * @author [Akash]
 * @author [Varshini]
 * @version 1.0
 */
public class Player extends Character implements Saveable {
    
    /**
     * The player's inventory system for storing and managing items.
     */
    private Inventory inventory;
    
    /**
     * X-coordinate position on the game map grid.
     */
    private int x = 1; 
    
    /**
     * Y-coordinate position on the game map grid.
     */
    private int y = 1;

    /**
     * Default constructor creating a Player with standard stats:
     * - Health: 100 points
     * - Attack: 15 points
     * Initializes a new inventory with default starting items.
     */
    public Player() {
        this(100, 15);
    }

    /**
     * Parameterized constructor for creating a Player with custom stats.
     * 
     * @param health the initial health points
     * @param attack the base attack power
     */
>>>>>>> e5a5c4dc231ee3fb8b8824259ad1490b7c593ccf
    public Player(int health, int attack) {
        this.health = health;
        this.attack = attack;
        inventory = new Inventory();
    }
<<<<<<< HEAD

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
=======
    
    /**
     * Executes the player's turn during combat.
     * Currently unimplemented as player actions are handled through
     * command input in the main game loop.
     */
    @Override
    public void takeTurn() {
        // Player turn logic is handled through user input in GridQuestRPG.gameLoop()
    }

    /**
     * Returns the player's inventory for item management.
     * 
     * @return the player's Inventory object
>>>>>>> e5a5c4dc231ee3fb8b8824259ad1490b7c593ccf
     */
    public Inventory getInventory() {
        return inventory;
    }

    /**
<<<<<<< HEAD
     * Converts player data into a savable string format.
     *
     * @return a string containing health and attack values
     */
    // [File I/O]
=======
     * Generates a string representation of player data for saving.
     * Implements the Saveable interface for game state persistence.
     * Format: "health,attack"
     * 
     * @return comma-separated string of player attributes
     */
>>>>>>> e5a5c4dc231ee3fb8b8824259ad1490b7c593ccf
    @Override
    public String saveData() {
        return health + "," + attack;
    }

<<<<<<< HEAD
    /**
     * Sets the player's health.
     *
     * @param health new health value
     */
=======
   /**
 * Sets the player's current health points.
 * Note: This bypasses the normal MAX_HEALTH limit for save/load functionality.
 * Used when loading saved games where health may exceed normal maximum.
 * 
 * @param health the new health value
 */
>>>>>>> e5a5c4dc231ee3fb8b8824259ad1490b7c593ccf
    public void setHealth(int health) {
        this.health = health;
    }

    /**
<<<<<<< HEAD
     * Returns the player's X-coordinate.
     *
     * @return x position
=======
     * Gets the player's current X-coordinate on the game map.
     * 
     * @return the X-coordinate
>>>>>>> e5a5c4dc231ee3fb8b8824259ad1490b7c593ccf
     */
    public int getX() {
        return x;
    }

    /**
<<<<<<< HEAD
     * Returns the player's Y-coordinate.
     *
     * @return y position
=======
     * Gets the player's current Y-coordinate on the game map.
     * 
     * @return the Y-coordinate
>>>>>>> e5a5c4dc231ee3fb8b8824259ad1490b7c593ccf
     */
    public int getY() {
        return y;
    }

    /**
<<<<<<< HEAD
     * Sets the player's X-coordinate.
     *
     * @param x new x position
=======
     * Sets the player's X-coordinate on the game map.
     * 
     * @param x the new X-coordinate
>>>>>>> e5a5c4dc231ee3fb8b8824259ad1490b7c593ccf
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
<<<<<<< HEAD
     * Sets the player's Y-coordinate.
     *
     * @param y new y position
=======
     * Sets the player's Y-coordinate on the game map.
     * 
     * @param y the new Y-coordinate
>>>>>>> e5a5c4dc231ee3fb8b8824259ad1490b7c593ccf
     */
    public void setY(int y) {
        this.y = y;
    }
}