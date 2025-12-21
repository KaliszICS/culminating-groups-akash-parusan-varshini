<<<<<<< HEAD
package game.world;
=======
/**
 * Represents a location/room in the GridQuest RPG world.
 * Each room contains a name, description, ASCII art, potential enemies,
 * and connections to other rooms. Serves as the fundamental building block
 * for the game's navigation and exploration system.
 * 
 * Demonstrates key ICS4U concepts including:
 * - HashMap usage for dynamic key-value storage (exits)
 * - Object composition (has-an Enemy, has-many exits)
 * - String array manipulation for ASCII art display
 * 
 * @author [Parusan]
 * @author [Akash]
 * @author [Varshini]
 * @version 1.0
 */
import java.util.HashMap;
>>>>>>> e5a5c4dc231ee3fb8b8824259ad1490b7c593ccf

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
<<<<<<< HEAD

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
     * @param name the name of the room
     * @param description the description shown to the player
     */
    // [Classes]
=======
    
    /**
     * The name of the room displayed to the player.
     */
    private String name;
    
    /**
     * Descriptive text explaining the room's appearance and atmosphere.
     */
    private String description;
    
    /**
     * ASCII art lines to visually represent the room.
     * Loaded from external text files for modular design.
     */
    private String[] art;
    
    /**
     * Enemy present in this room, or null if the room is empty.
     */
    private Enemy enemy;
    
    /**
     * HashMap mapping direction strings ("up", "down", "left", "right")
     * to room indices in the world map. Uses -1 for invalid exits.
     */
    private HashMap<String, Integer> exits;

    /**
     * Constructs a new Room with the specified name and description.
     * Initializes with empty art, no enemy, and no exits.
     * 
     * @param name the display name of the room
     * @param description the descriptive text for the room
     */
>>>>>>> e5a5c4dc231ee3fb8b8824259ad1490b7c593ccf
    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        this.art = new String[0];
        this.exits = new HashMap<>();
    }

    /**
<<<<<<< HEAD
     * Sets the ASCII art for the room.
     *
     * @param art an array of strings representing ASCII art
     */
    // [Classes]
=======
     * Sets the ASCII art for this room.
     * Typically loaded from external text files via loadArtFromFile().
     * 
     * @param art array of strings representing ASCII art lines
     */
>>>>>>> e5a5c4dc231ee3fb8b8824259ad1490b7c593ccf
    public void setArt(String[] art) {
        this.art = art;
    }

    /**
<<<<<<< HEAD
     * Assigns an enemy to the room.
     *
     * @param enemy the enemy to place in the room
     */
    // [Classes]
=======
     * Places an enemy in this room.
     * 
     * @param enemy the Enemy object to place in the room
     */
>>>>>>> e5a5c4dc231ee3fb8b8824259ad1490b7c593ccf
    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

    /**
<<<<<<< HEAD
     * Checks whether the room currently has a living enemy.
     *
     * @return true if an enemy exists and is alive, false otherwise
     */
    // [Classes]
=======
     * Checks if this room contains a living enemy.
     * 
     * @return true if an enemy exists and is alive, false otherwise
     */
>>>>>>> e5a5c4dc231ee3fb8b8824259ad1490b7c593ccf
    public boolean hasEnemy() {
        return enemy != null && enemy.isAlive();
    }

    /**
<<<<<<< HEAD
     * Returns the enemy in the room.
     *
     * @return the enemy object, or null if none exists
     */
    // [Classes]
=======
     * Returns the enemy currently in this room.
     * 
     * @return the Enemy object, or null if no enemy present
     */
>>>>>>> e5a5c4dc231ee3fb8b8824259ad1490b7c593ccf
    public Enemy getEnemy() {
        return enemy;
    }

    /**
<<<<<<< HEAD
     * Removes the enemy from the room after defeat.
     */
    // [Classes]
=======
     * Removes the enemy from this room (used after defeating an enemy).
     */
>>>>>>> e5a5c4dc231ee3fb8b8824259ad1490b7c593ccf
    public void defeatEnemy() {
        this.enemy = null;
    }

    /**
<<<<<<< HEAD
     * Adds an exit in a specified direction leading to another room.
     *
     * @param direction the direction of the exit
     * @param roomIndex the index of the connected room
     */
    // [Classes]
=======
     * Adds an exit from this room to another room.
     * Directions are normalized to lowercase for case-insensitive lookup.
     * 
     * @param direction the direction string (e.g., "up", "down", "left", "right")
     * @param roomIndex the index of the destination room in the world map
     */
>>>>>>> e5a5c4dc231ee3fb8b8824259ad1490b7c593ccf
    public void addExit(String direction, int roomIndex) {
        exits.put(direction.toLowerCase(), roomIndex);
    }

    /**
<<<<<<< HEAD
     * Retrieves the room index associated with a given direction.
     *
     * @param direction the direction to move
     * @return the connected room index, or -1 if no exit exists
     */
    // [Classes]
=======
     * Gets the destination room index for a given direction.
     * Returns -1 if no exit exists in that direction.
     * 
     * @param direction the direction to check
     * @return the destination room index, or -1 if no exit
     */
>>>>>>> e5a5c4dc231ee3fb8b8824259ad1490b7c593ccf
    public int getExit(String direction) {
        return exits.getOrDefault(direction.toLowerCase(), -1);
    }

    /**
<<<<<<< HEAD
     * Returns the name of the room.
     *
     * @return the room name
     */
    // [Classes]
=======
     * Returns the name of this room.
     * 
     * @return the room name
     */
>>>>>>> e5a5c4dc231ee3fb8b8824259ad1490b7c593ccf
    public String getName() {
        return name;
    }

    /**
<<<<<<< HEAD
     * Displays the room's name, description, ASCII art,
     * exits, and available commands to the player.
     */
    // [Classes]
=======
     * Displays the complete room information to the player.
     * Includes name, description, ASCII art, available exits,
     * and context-sensitive commands based on room type.
     * 
     * Format:
     * ========================================
     * ROOM NAME
     * ========================================
     * Description...
     * 
     * [ASCII Art]
     * 
     * Exits: [up, down, left, right]
     * Commands: go <up|down|left|right>, attack, inventory...
     * ========================================
     */
>>>>>>> e5a5c4dc231ee3fb8b8824259ad1490b7c593ccf
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
        System.out.println("\nExits: " + exits.keySet());

<<<<<<< HEAD
        String commands =
            "go <up|down|left|right>, attack, inventory, map, save, load, help, quit";

=======
        // Base commands available in all rooms
        String commands = "go <up|down|left|right>, attack, inventory, map, save, load, help, quit";
        
        // Room-specific commands
>>>>>>> e5a5c4dc231ee3fb8b8824259ad1490b7c593ccf
        if (name.equals("The Lucky Tavern")) {
            commands += ", gamble";
        } else if (name.equals("Abandoned Storage")) {
            commands += ", open chest";
        }

        System.out.println("Commands: " + commands);
        System.out.println("========================================");
    }
}