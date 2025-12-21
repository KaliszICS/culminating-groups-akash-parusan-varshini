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
    public Player(int health, int attack) {
        this.health = health;
        this.attack = attack;
        inventory = new Inventory();
    }
    
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
     */
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * Generates a string representation of player data for saving.
     * Implements the Saveable interface for game state persistence.
     * Format: "health,attack"
     * 
     * @return comma-separated string of player attributes
     */
    @Override
    public String saveData() {
        return health + "," + attack;
    }

   /**
 * Sets the player's current health points.
 * Note: This bypasses the normal MAX_HEALTH limit for save/load functionality.
 * Used when loading saved games where health may exceed normal maximum.
 * 
 * @param health the new health value
 */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Gets the player's current X-coordinate on the game map.
     * 
     * @return the X-coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the player's current Y-coordinate on the game map.
     * 
     * @return the Y-coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the player's X-coordinate on the game map.
     * 
     * @param x the new X-coordinate
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Sets the player's Y-coordinate on the game map.
     * 
     * @param y the new Y-coordinate
     */
    public void setY(int y) {
        this.y = y;
    }
}