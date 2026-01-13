package game.inventory;

import game.characters.Player;
import game.util.Utils;
import java.util.Scanner;

/**
 * The InventoryMenu class handles all user interaction related to
 * viewing and managing the player's inventory.
 * It allows players to use items, organize their inventory,
 * search for items, and exit back to the game.
 *
 * @author Akash K.
 * @author Parusan P.
 * @author Varshini B.
 * @version 1.0
 */
// [Classes]
public class InventoryMenu {
    private static final int MAX_HEALTH = 100;

    /**
     * Opens the inventory menu and processes user input
     * until the player chooses to exit.
     *
     * @param player the current player accessing the inventory
     * @param input  the scanner used for user input
     */
    // [Classes]
    // [Sorting (Selection)]
    // [Searching (Sequential)]
    public static void open(Player player, Scanner input) {
        boolean done = false;

        while (!done) {
            Utils.clear();
            player.getInventory().display();
            System.out.println(
                    "\nActions: [1] Potion [2] Apple [3] Organize Bag [4] Search [5] Back");
            System.out.print("> ");

            String invCmd = input.nextLine().trim();

            if (invCmd.equals("1")) {
                heal(player, input, true);

            } else if (invCmd.equals("2")) {
                heal(player, input, false);

            } else if (invCmd.equals("3")) {
                player.getInventory().selectionSort(); // [Sorting (Selection)]
                System.out.println("\nInventory organized alphabetically!");
                Utils.pause();

            } else if (invCmd.equals("4")) {
                System.out.print("\nEnter item name to find: ");
                String find = input.nextLine();
                int index = player.getInventory().sequentialSearch(find); // [Searching (Sequential)]

                if (index != -1) {
                    System.out.println(
                            "'" + find + "' found in backpack slot " + (index + 1) + ".");
                } else {
                    System.out.println("Item not found in your inventory.");
                }
                Utils.pause();

            } else if (invCmd.equals("5")) {
                done = true;

            } else {
                System.out.println("Invalid choice.");
                Utils.pause();
            }
        }
    }

    /**
     * Heals the player using either a potion or an apple.
     *
     * @param player the player being healed
     * @param input  the scanner used for pauses
     * @param potion true to use a potion, false to use an apple
     */
    // [Classes]
    // [Polymorphism] (uses Player methods inherited from Character)
    private static void heal(Player player, Scanner input, boolean potion) {
        if (player.getHealth() >= MAX_HEALTH) {
            System.out.println("\nAlready at max health (100/100)!");
        } else {
            boolean success = potion
                    ? player.getInventory().usePotion(player)
                    : player.getInventory().useApple(player);

            if (success) {
                System.out.println(
                        "\nHealed! Current health: " + player.getHealth() + "/100");
            } else {
                System.out.println("\nYou don't have that item!");
            }
        }
        Utils.pause();
    }
}
