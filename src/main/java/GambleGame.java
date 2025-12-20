// Classes, Recursion
// Uses recursion for repeated gambling rounds.
import java.util.Scanner;

public class GambleGame {

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
            gambleRound(player, input, 1);
        }
    }

    private static void gambleRound(Player player, Scanner input, int wager) {
        int cur = (int) (Math.random() * 10) + 1;
        int next = (int) (Math.random() * 10) + 1;

        System.out.println("\nDealer shows: [" + cur + "]. Next card Higher or Lower? (h/l)");
        String choice = input.nextLine().toLowerCase().trim();

        if ((choice.equals("h") && next >= cur) || (choice.equals("l") && next <= cur)) {
            System.out.println("Win! Card was " + next + ". Total: " + (wager * 2) + " potions.");
            System.out.println("Double or nothing? (y/n)");

            if (input.nextLine().equalsIgnoreCase("y")) {
                gambleRound(player, input, wager * 2);
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
