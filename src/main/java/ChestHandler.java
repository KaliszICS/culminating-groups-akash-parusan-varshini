// Classes
// Handles chest interaction and riddle validation.
import java.util.ArrayList;
import java.util.Scanner;

public class ChestHandler {

    public static boolean tryOpen(Room room, Player player, ArrayList<Riddle> riddles, Scanner input, boolean chestOpened) {

        if (!room.getName().equals("Abandoned Storage")) {
            return chestOpened;
        }

        if (chestOpened) {
            System.out.println("\nYou already took my stuff! What more could you want!");
            Utils.pause();
            return true;
        }

        RiddleBank.shuffle(riddles);
        Riddle r = riddles.get(0);

        Utils.clear();
        System.out.println("A chest blocks your path...\n");
        System.out.println(r.getQuestion());
        System.out.print("> ");

        if (r.checkAnswer(input.nextLine())) {
            System.out.println("\nOpened!");
            System.out.println("You found a Potion and an Apple. (Teachers like apples right?)");
            player.getInventory().addItem("Potion");
            player.getInventory().addItem("Apple");
            Utils.pause();
            return true;
        } else {
            System.out.println("\nThe chest remains locked.");
            Utils.pause();
            return false;
        }
    }
}
