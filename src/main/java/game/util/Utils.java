package game.util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * The Utils class provides common utility methods used throughout
 * the game, such as pausing execution, clearing the console,
 * and saving simple data to a file.
 *
 * @author Akash K.
 * @author Parusan P.
 * @author Varshini B.
 * @version 1.0
 */
// [Classes]
public class Utils {
    private static final int BLANK_LINES = 25;
    /** Scanner used to pause execution until ENTER is pressed */
    private static Scanner pauseInput = new Scanner(System.in);

    /**
     * Pauses program execution until the user presses ENTER.
     */
    // [Classes]
    public static void pause() {
        System.out.println("\n(Press ENTER)");
        pauseInput.nextLine();
    }

    /**
     * Clears the console by printing multiple blank lines.
     */
    // [Classes]
    public static void clear() {
        for (int i = 0; i < BLANK_LINES; i++) {
            System.out.println();
        }
    }

    /**
     * Saves a string of data to a text file.
     *
     * @param data the data to be written to the file
     */
    // [File I/O]
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
}