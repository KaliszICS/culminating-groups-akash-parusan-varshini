package game.inventory;
// Classes
// Handles user interaction with inventory features.
import java.util.Scanner;

public class InventoryMenu {

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
                player.getInventory().selectionSort();
                System.out.println("\nInventory organized alphabetically!");
                Utils.pause();

            } else if (invCmd.equals("4")) {
                System.out.print("\nEnter item name to find: ");
                String find = input.nextLine();
                int index = player.getInventory().sequentialSearch(find);

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

    private static void heal(Player player, Scanner input, boolean potion) {
        if (player.getHealth() >= 100) {
            System.out.println("\nAlready at max health (100/100)!");
        } else {
            boolean success = potion
                    ? player.getInventory().usePotion(player)
                    : player.getInventory().useApple(player);

            if (success) {
                System.out.println("\nHealed! Current health: " + player.getHealth() + "/100");
            } else {
                System.out.println("\nYou don't have that item!");
            }
        }
        Utils.pause();
    }
}
