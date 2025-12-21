/**
 * Handles save and load operations for GridQuest RPG game state.
 * Implements file I/O to persist game progress between sessions.
 * Saves player stats, current room, and inventory items to a text file.
 * 
 * Demonstrates key ICS4U concepts:
 * - File input/output (FileWriter, PrintWriter, Scanner)
 * - Exception handling for robust file operations
 * - String parsing and serialization
 * - Working with interfaces (Saveable)
 * 
 * @author [Parusan]
 * @author [Akash]
 * @author [Varshini]
 * @version 1.0
 */
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class SaveManager {
    
    /**
     * The filename where game data is stored.
     * Using a constant ensures consistent file naming throughout the game.
     */
    private static final String SAVE_FILE = "savegame.txt";

    /**
     * Saves the current game state to a text file.
     * Format:
     * Line 1: currentRoom index
     * Line 2: player stats (from Saveable interface)
     * Remaining lines: inventory items (one per line)
     * 
     * @param currentRoom the index of the player's current room in the world map
     * @param entity the Saveable object containing player stats (typically Player)
     * @param inventory the player's inventory to save
     */
    public static void saveGame(int currentRoom, Saveable entity, Inventory inventory) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(SAVE_FILE))) {
            // Save room location
            writer.println(currentRoom);
            
            // Save player stats using Saveable interface
            writer.println(entity.saveData());
            
            // Save inventory items
            for (String item : inventory.getItems()) {
                writer.println(item);
            }
            
            System.out.println("\nGame saved successfully!");
        } catch (IOException e) {
            System.out.println("\nError saving game.");
            // In a production game, you might log the error: e.printStackTrace();
        }
        Utils.pause();
    }

    /**
     * Loads a previously saved game state from file.
     * Restores player stats, inventory, and room position.
     * 
     * File format expectations:
     * - First line: integer room index
     * - Second line: comma-separated player stats (e.g., "100,15")
     * - Remaining lines: inventory items (one per line)
     * 
     * @param player the Player object to restore data into
     * @return an array containing the saved room index at position [0],
     *         or null if loading failed
     */
    public static int[] loadGame(Player player) {
        File file = new File(SAVE_FILE);
        
        // Check if save file exists
        if (!file.exists()) {
            System.out.println("\nNo save file found.");
            Utils.pause();
            return null;
        }

        try (Scanner reader = new Scanner(file)) {
            // Read room location
            int room = Integer.parseInt(reader.nextLine());
            
            // Read and parse player stats (expected format: "health,attack")
            String[] stats = reader.nextLine().split(",");
            int health = Integer.parseInt(stats[0]);
            int attack = Integer.parseInt(stats[1]);

            // Restore player state
            player.setHealth(health);
            player.attack = attack; // Note: assumes attack is accessible
            
            // Clear and restore inventory
            player.getInventory().getItems().clear();
            while (reader.hasNextLine()) {
                player.getInventory().addItem(reader.nextLine());
            }

            System.out.println("\nGame loaded successfully!");
            Utils.pause();
            return new int[] { room };

        } catch (Exception e) {
            System.out.println("\nError loading save file. File may be corrupted.");
            Utils.pause();
            return null;
        }
    }
}