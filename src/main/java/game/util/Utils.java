/**
 * Utility class providing common helper functions for GridQuest RPG.
 * Contains reusable methods for console I/O operations, screen management,
 * and basic file operations used throughout the game.
 * 
 * Demonstrates key ICS4U concepts:
 * - Static utility methods (no instantiation needed)
 * - File I/O operations (FileWriter)
 * - Console input/output handling
 * - Scanner usage for user input
 * 
 * This class follows the utility class pattern with a private constructor
 * to prevent instantiation, as all methods are static.
 * 
 * @author [Parusan]
 * @author [Akash]
 * @author [Varshini]
 * @version 1.0
 */
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Utils {

    /**
     * Shared Scanner instance for reading pause input.
     * Static to avoid creating multiple Scanner objects on System.in.
     */
    private static Scanner pauseInput = new Scanner(System.in);

    /**
     * Private constructor to prevent instantiation.
     * This is a utility class with only static methods.
     */
    private Utils() {
        throw new IllegalStateException("Utility class - do not instantiate");
    }

    /**
     * Pauses program execution and waits for user to press ENTER.
     * Useful for controlling game flow and ensuring players read messages.
     * Displays a prompt and blocks until Enter key is pressed.
     */
    public static void pause() {
        System.out.println("\n(Press ENTER)");
        pauseInput.nextLine();
    }

    /**
     * Clears the console by printing multiple blank lines.
     * Simulates a console clear operation for better visual presentation.
     * Note: This approach works in most terminals but may not work in all IDEs.
     * For more robust clearing, platform-specific commands would be needed.
     */
    public static void clear() {
        for (int i = 0; i < 25; i++) {
            System.out.println();
        }
    }

    /**
     * Saves a string of data to a file named "save.txt".
     * Demonstrates basic file I/O operations for game state persistence.
     * Note: Overwrites existing file without warning.
     * 
     * @param data the string data to save to file
     */
    public static void save(String data) {
        try {
            FileWriter fw = new FileWriter("save.txt");
            fw.write(data);
            fw.close();
            System.out.println("Game saved.");
        } catch (IOException e) {
            System.out.println("Save failed.");
        }
    }
    
    /**
     * Alternative clear method using platform-specific commands.
     * More reliable than printing blank lines but less portable.
     * Uncomment if needed for specific environments.
     */
    /*
    public static void clearAdvanced() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            // Fall back to simple clear
            clear();
        }
    }
    */
}