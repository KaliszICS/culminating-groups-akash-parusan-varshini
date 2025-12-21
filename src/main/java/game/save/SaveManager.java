package game.save;

import java.io.*;
import java.util.Scanner;

import game.inventory.Saveable;
import game.util.Utils;
import game.characters.Player;

/**
 * The SaveManager class handles saving and loading game data
 * using external files.
 * It writes the current game state to a text file and restores
 * the player's progress when loading.
 *
 * @author Akash K.
 * @author Parusan P.
 * @author Varshini B.
 * @version 1.0
 */
// [Classes]
// [File I/O]
// [Abstract classes and interfaces]
public class SaveManager {

    /** Name of the save file */
    private static final String SAVE_FILE = "savegame.txt";

    /**
     * Saves the current game state to a file.
     * This includes the player's location, chest state,
     * player stats, and inventory contents.
     *
     * @param currentRoom the index of the current room
     * @param chestOpened whether the chest has been opened
     * @param player the player object implementing Saveable
     * @param inventory the inventory object implementing Saveable
     */
    // [File I/O]
    // [Abstract classes and interfaces]
    public static void saveGame(
            int currentRoom,
            boolean chestOpened,
            Saveable player,
            Saveable inventory) {

        try (PrintWriter writer = new PrintWriter(new FileWriter(SAVE_FILE))) {
            writer.println(currentRoom);
            writer.println(chestOpened);
            writer.println(player.saveData());
            writer.print(inventory.saveData());

            System.out.println("\nGame saved successfully!");
        } catch (IOException e) {
            System.out.println("\nError saving game.");
        }
        Utils.pause();
    }

    /**
     * Loads the game state from a save file and restores
     * the player's progress.
     *
     * @param player the player object whose state will be restored
     * @return an int array containing the room index and chest state,
     *         or null if loading fails
     */
    // [File I/O]
    public static int[] loadGame(Player player) {
        File file = new File(SAVE_FILE);

        if (!file.exists()) {
            System.out.println("\nNo save file found.");
            Utils.pause();
            return null;
        }

        try (Scanner reader = new Scanner(file)) {
            int room = Integer.parseInt(reader.nextLine());
            boolean chestOpened = Boolean.parseBoolean(reader.nextLine());

            String[] stats = reader.nextLine().split(",");
            int health = Integer.parseInt(stats[0]);

            player.setHealth(health);
            player.getInventory().getItems().clear();

            while (reader.hasNextLine()) {
                player.getInventory().addItem(reader.nextLine());
            }

            System.out.println("\nGame loaded successfully!");
            Utils.pause();

            return new int[] { room, chestOpened ? 1 : 0 };

        } catch (Exception e) {
            System.out.println("\nError loading save file.");
            Utils.pause();
            return null;
        }
    }
}
