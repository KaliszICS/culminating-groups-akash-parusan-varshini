package game.minigames;

import java.util.Scanner;
import game.characters.Player;

/**
 * The GambleGame class represents a gambling mini-game
 * where the player can wager potions for a chance to win more.
 * The game uses recursion to allow repeated "double or nothing"
 * rounds until the player chooses to stop or loses.
 *
 * @author Akash K.
 * @author Parusan P.
 * @author Varshini B.
 * @version 1.0
 */
// [Classes]
// [Recursion]
public class GambleGame {

    /**
     * Starts the gambling mini-game.
     * The player must have at least one potion to participate.
     *
     * @param player the player participating in the gamble
     * @param input the scanner used for user input
     */
    // [Classes]
    public static void play(Player player, Scanner input) {
        if (!player.getInventory().hasPotion()) {
            System.out.println("You need at least 1 Potion to gamble!");
            System.out.println("Press Enter to continue...");
            input.nextLine();
            return;
        }

        System.out.println(
            "The dealer eyes your bag. He smirks, asking 'Will you wager 1 potion for a chance at 2?' (y/n)"
        );

        if (input.nextLine().equalsIgnoreCase("y")) {
            gambleRound(player, input, 1); // [Recursion]
        }
    }

    /**
     * Executes a single gambling round.
     * If the player wins and chooses to continue, this method
     * calls itself with a doubled wager.
     *
     * @param player the player gambling
     * @param input the scanner used for user input
     * @param wager the current wager amount
     */
    // [Recursion]
    private static void gambleRound(Player player, Scanner input, int wager) {
        int cur = (int) (Math.random() * 10) + 1;
        int next = (int) (Math.random() * 10) + 1;

        System.out.println("\nDealer shows: [" + cur + "]. Next card Higher or Lower? (h/l)");
        String choice = input.nextLine().toLowerCase().trim();

        if ((choice.equals("h") && next >= cur) || (choice.equals("l") && next <= cur)) {
            System.out.println("Win! Card was " + next + ". Total: " + (wager * 2) + " potions.");
            System.out.println("Double or nothing? (y/n)");

            if (input.nextLine().equalsIgnoreCase("y")) {
                gambleRound(player, input, wager * 2); // [Recursion]
            } else {
                player.getInventory().addMultipleItems("Potion", (wager * 2) - 1);
                System.out.println("Winnings added. Press Enter to continue...");
                input.nextLine();
            }
        } else {
            System.out.println("Loss! Card was " + next + ". Wager lost.");
            player.getInventory().removePotion();
            System.out.println("\nPress Enter to return to the Tavern...");
            input.nextLine();
        }
    }
}
