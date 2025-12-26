package game.world;

import java.util.HashMap;
import game.characters.Enemy;

/**
 * The Room class represents a single location in the game world.
 * Each room contains a name, description, optional ASCII art,
 * exits to other rooms, and may contain an enemy.
 *
 * This class is responsible for storing room data and
 * displaying the room to the player.
 *
 * @author Akash K.
 * @author Parusan P.
 * @author Varshini B.
 * @version 1.0
 */
// [Classes]
public class Room {

    /** Name of the room */
    private String name;

    /** Description of the room */
    private String description;

    /** ASCII art associated with the room */
    private String[] art;

    /** Enemy present in the room, if any */
    private Enemy enemy;

    /** Stores exits and their corresponding room indices */
    private HashMap<String, Integer> exits;

    /**
     * Constructs a Room with a name and description.
     *
     * @param name        the name of the room
     * @param description the description shown to the player
     */
    // [Classes]
    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        this.art = new String[0];
        this.exits = new HashMap<>();
    }

    /**
     * Sets the ASCII art for the room.
     *
     * @param art an array of strings representing ASCII art
     */
    // [Classes]
    public void setArt(String[] art) {
        this.art = art;
    }

    /**
     * Assigns an enemy to the room.
     *
     * @param enemy the enemy to place in the room
     */
    // [Classes]
    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

    /**
     * Checks whether the room currently has a living enemy.
     *
     * @return true if an enemy exists and is alive, false otherwise
     */
    // [Classes]
    public boolean hasEnemy() {
        return enemy != null && enemy.isAlive();
    }

    /**
     * Returns the enemy in the room.
     *
     * @return the enemy object, or null if none exists
     */
    // [Classes]
    public Enemy getEnemy() {
        return enemy;
    }

    /**
     * Removes the enemy from the room after defeat.
     */
    // [Classes]
    public void defeatEnemy() {
        this.enemy = null;
    }

    /**
     * Adds an exit in a specified direction leading to another room.
     *
     * @param direction the direction of the exit
     * @param roomIndex the index of the connected room
     */
    // [Classes]
    public void addExit(String direction, int roomIndex) {
        exits.put(direction.toLowerCase(), roomIndex);
    }

    /**
     * Retrieves the room index associated with a given direction.
     *
     * @param direction the direction to move
     * @return the connected room index, or -1 if no exit exists
     */
    // [Classes]
    public int getExit(String direction) {
        return exits.getOrDefault(direction.toLowerCase(), -1);
    }

    /**
     * Returns the name of the room.
     *
     * @return the room name
     */
    // [Classes]
    public String getName() {
        return name;
    }

    /**
     * Displays the room's name, description, ASCII art,
     * exits, and available commands to the player.
     */
    // [Classes]
    public void display() {
        System.out.println("========================================");
        System.out.println(name.toUpperCase());
        System.out.println("========================================");
        System.out.println(description + "\n");

        // Display ASCII art if available
        if (art != null) {
            for (String line : art) {
                System.out.println(line);
            }
        }

        // Show available exits
        if (hasEnemy()) {
            System.out.println("\nExits: (blocked)");
        } else {
            System.out.println("\nExits: " + exits.keySet());
        }

        String commands = "go <up|down|left|right>, attack, inventory, map, save, load, help, quit";

        if (name.equals("The Lucky Tavern")) {
            commands += ", gamble";
        } else if (name.equals("Abandoned Storage")) {
            commands += ", open chest";
        } else if (name.equals("The Arcane Study")) {
            commands += ", talk";
        }

        System.out.println("Commands: " + commands);
        System.out.println("========================================");
    }
}