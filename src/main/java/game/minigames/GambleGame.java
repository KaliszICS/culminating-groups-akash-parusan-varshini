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
    /** Minimum possible card value in the Higher/Lower gambling mini-game. */
    private static final int CARD_MIN = 1;

    /** Maximum possible card value in the Higher/Lower gambling mini-game. */
    private static final int CARD_MAX = 10;

    /** Multiplier applied when the player wins a round (double-or-nothing). */
    private static final int DOUBLE_MULTIPLIER = 2;

    /**
     * Starts the gambling mini-game.
     * The player must have at least one potion to participate.
     *
     * @param player the player participating in the gamble
     * @param input  the scanner used for user input
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
                "The dealer eyes your bag. He smirks, asking 'Will you wager 1 potion for a chance at 2?' (y/n)");

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
     * @param input  the scanner used for user input
     * @param wager  the current wager amount
     */
    // [Recursion]
    private static void gambleRound(Player player, Scanner input, int wager) {
        int cur = randomInRange(CARD_MIN, CARD_MAX);
        int next = randomInRange(CARD_MIN, CARD_MAX);

        System.out.println("\nDealer shows: [" + cur + "]. Next card Higher or Lower? (h/l)");
        String choice = input.nextLine().toLowerCase().trim();

        if ((choice.equals("h") && next >= cur) || (choice.equals("l") && next <= cur)) {
            System.out.println("Win! Card was " + next + ". Total: " + (wager * DOUBLE_MULTIPLIER) + " potions.");
            System.out.println("Double or nothing? (y/n)");

            if (input.nextLine().equalsIgnoreCase("y")) {
                gambleRound(player, input, wager * DOUBLE_MULTIPLIER); // [Recursion]
            } else {
                player.getInventory().addMultipleItems("Potion", (wager * DOUBLE_MULTIPLIER) - 1);
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

    /**
     * Generates a random integer in the inclusive range [min, max].
     *
     * @param min the minimum value (inclusive)
     * @param max the maximum value (inclusive)
     * @return a random integer between min and max, inclusive
     */
    private static int randomInRange(int min, int max) {
        return min + (int) (Math.random() * (max - min + 1));
    }

}
