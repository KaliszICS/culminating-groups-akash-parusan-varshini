package game.ui;

import java.util.Scanner;
import game.util.Utils;

/**
 * The GameMenus class handles all menu-related user interactions
 * including the start menu, help menu, and introductory narration.
 * It is responsible for displaying menu options and responding
 * to user selections.
 *
 * @author Akash K.
 * @author Parusan P.
 * @author Varshini B.
 * @version 1.0
 */
// [Classes]
public class GameMenus {

    /**
     * Displays the start menu and processes user input until
     * a valid option is selected.
     *
     * @param input the Scanner used to read user input
     */
    // [Classes]
    public static void showStartMenu(Scanner input) {
        while (true) {
            Utils.clear();
            System.out.println("========================================");
            System.out.println("           GRIDQUEST RPG");
            System.out.println("========================================\n");
            System.out.println("   [1] Start Game");
            System.out.println("   [2] Help");
            System.out.println("   [3] Quit\n");
            System.out.println("========================================");
            System.out.print("> ");

            String choice = input.nextLine();

            if (choice.equals("1"))
                return;
            if (choice.equals("2"))
                showHelpMenu();
            if (choice.equals("3"))
                System.exit(0);
        }
    }

    /**
     * Displays the help menu with a list of available commands
     * and pauses execution until the user is ready to continue.
     */
    // [Classes]
    public static void showHelpMenu() {
        Utils.clear();
        System.out.println("========================================");
        System.out.println("                HELP");
        System.out.println("========================================");
        System.out.println("Commands:");
        System.out.println("go up | go down | go left | go right");
        System.out.println("attack             -> Fight an enemy in the room");
        System.out.println("inventory (or 'i') -> View and use your items");
        System.out.println("help               -> Show this menu");
        System.out.println("quit               -> Exit the game");
        System.out.println("========================================");
        Utils.pause();
    }

    /**
     * Displays the introductory story text at the beginning
     * of the game.
     */
    // [Classes]
    public static void playIntro() {
        Utils.clear();
        System.out.println("A hooded wizard appears.");
        System.out.println("\"It's dangerous to go alone! Take this.\"");
        System.out.println("You received a MAP.");
        Utils.pause();
    }
}