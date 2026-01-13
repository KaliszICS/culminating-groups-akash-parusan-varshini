package game.ui;

import java.util.ArrayList;
import java.util.Scanner;

import game.characters.Player;
import game.riddles.Riddle;
import game.riddles.RiddleBank;
import game.util.Utils;
import game.world.Room;

/**
 * The ChestHandler class manages player interaction with chests
 * and validates riddle answers before allowing access to rewards.
 * It ensures chests can only be opened once per game session.
 *
 * @author Akash K.
 * @author Parusan P.
 * @author Varshini B.
 * @version 1.0
 */
// [Classes]
// [Recursion] (uses recursive shuffle from RiddleBank)
public class ChestHandler {

    /**
     * Attempts to open the chest in the current room.
     * A riddle is presented to the player, and rewards are
     * given only if the correct answer is provided.
     *
     * @param room        the current room the player is in
     * @param player      the player attempting to open the chest
     * @param riddles     the list of available riddles
     * @param input       scanner used for user input
     * @param chestOpened whether the chest has already been opened
     * @return true if the chest is opened or already opened, false otherwise
     */
    // [Classes]
    // [Recursion]
    public static boolean tryOpen(
            Room room,
            Player player,
            ArrayList<Riddle> riddles,
            Scanner input,
            boolean chestOpened) {

        if (!room.getName().equals("Abandoned Storage")) {
            return chestOpened;
        }

        if (chestOpened) {
            System.out.println("\nYou already took my stuff! What more could you want!");
            Utils.pause();
            return true;
        }

        RiddleBank.shuffle(riddles); // [Recursion]
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