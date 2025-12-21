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

public class Room {
    
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
    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        this.art = new String[0];
        this.exits = new HashMap<>();
    }

    /**
     * Sets the ASCII art for this room.
     * Typically loaded from external text files via loadArtFromFile().
     * 
     * @param art array of strings representing ASCII art lines
     */
    public void setArt(String[] art) {
        this.art = art;
    }

    /**
     * Places an enemy in this room.
     * 
     * @param enemy the Enemy object to place in the room
     */
    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

    /**
     * Checks if this room contains a living enemy.
     * 
     * @return true if an enemy exists and is alive, false otherwise
     */
    public boolean hasEnemy() {
        return enemy != null && enemy.isAlive();
    }

    /**
     * Returns the enemy currently in this room.
     * 
     * @return the Enemy object, or null if no enemy present
     */
    public Enemy getEnemy() {
        return enemy;
    }

    /**
     * Removes the enemy from this room (used after defeating an enemy).
     */
    public void defeatEnemy() {
        this.enemy = null;
    }

    /**
     * Adds an exit from this room to another room.
     * Directions are normalized to lowercase for case-insensitive lookup.
     * 
     * @param direction the direction string (e.g., "up", "down", "left", "right")
     * @param roomIndex the index of the destination room in the world map
     */
    public void addExit(String direction, int roomIndex) {
        exits.put(direction.toLowerCase(), roomIndex);
    }

    /**
     * Gets the destination room index for a given direction.
     * Returns -1 if no exit exists in that direction.
     * 
     * @param direction the direction to check
     * @return the destination room index, or -1 if no exit
     */
    public int getExit(String direction) {
        return exits.getOrDefault(direction.toLowerCase(), -1);
    }

    /**
     * Returns the name of this room.
     * 
     * @return the room name
     */
    public String getName() {
        return name;
    }

    /**
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

        // Base commands available in all rooms
        String commands = "go <up|down|left|right>, attack, inventory, map, save, load, help, quit";
        
        // Room-specific commands
        if (name.equals("The Lucky Tavern")) {
            commands += ", gamble";
        } else if (name.equals("Abandoned Storage")) {
            commands += ", open chest";
        }

        System.out.println("Commands: " + commands);
        System.out.println("========================================");
    }
}