package game.ui;

import java.util.ArrayList;
import java.util.Scanner;

import game.characters.Player;
import game.characters.Enemy;
import game.combat.BattleSystem;
import game.riddles.Riddle;
import game.riddles.RiddleBank;
import game.util.Utils;

public class WizardHandler {

    private static boolean fireUnlocked = false;
    private static boolean healUnlocked = false;
    private static boolean slowUnlocked = false;

    public static void interact(Player player, Scanner input) {

        boolean done = false;

        while (!done) {
            Utils.clear();
            System.out.println("WIZARD: \"Power must be earned.\"");
            System.out.println();

            System.out.println("[1] Trial of Flame (Fire Bolt)");
            System.out.println("[2] Trial of Light (Healing Light)");
            System.out.println("[3] Trial of Time (Time Slow)");
            System.out.println("[4] Leave");
            System.out.print("> ");

            String choice = input.nextLine().trim();

            if (choice.equals("1")) {
                trialOfFlame(input);

            } else if (choice.equals("2")) {
                trialOfLight(player, input);

            } else if (choice.equals("3")) {
                trialOfTime(player, input);

            } else if (choice.equals("4")) {
                System.out.println("The wizard returns to his studies.");
                Utils.pause();
                done = true;

            } else {
                System.out.println("The wizard does not understand.");
                Utils.pause();
            }
        }
    }

    // ---------------- FIRE BOLT ----------------
    private static void trialOfFlame(Scanner input) {
        if (fireUnlocked) {
            System.out.println("You already wield Fire Bolt.");
            Utils.pause();
            return;
        }

        System.out.println("WIZARD: \"Two truths, spoken without error.\"");
        Utils.pause();

        // Load riddles ONCE
        ArrayList<Riddle> riddles = RiddleBank.load();

        // Shuffle so order is random
        RiddleBank.shuffle(riddles);

        // Ask first two riddles (guaranteed different)
        for (int i = 0; i < 2; i++) {
            Riddle r = riddles.get(i);

            System.out.println("\nRiddle " + (i + 1) + ":");
            System.out.println(r.getQuestion());
            System.out.print("> ");

            if (!r.checkAnswer(input.nextLine())) {
                System.out.println("Incorrect. The flames fade.");
                Utils.pause();
                return;
            }
        }

        fireUnlocked = true;
        System.out.println("\nSPELL UNLOCKED: FIRE BOLT");
        Utils.pause();
    }

    // ---------------- HEALING LIGHT ----------------
    private static void trialOfLight(Player player, Scanner input) {
        if (healUnlocked) {
            System.out.println("The wizard says: \"You already know this spell.\"");
            Utils.pause();
            return;
        }

        System.out.println(
                "WIZARD: \"Healing Light requires sacrifice.\"");
        System.out.println(
                "Donate 2 Potions to learn Healing Light? (y/n)");
        System.out.print("> ");

        String choice = input.nextLine().trim().toLowerCase();

        if (!choice.equals("y")) {
            System.out.println("You step back from the altar.");
            Utils.pause();
            return;
        }

        // Check + remove exactly 2 potions
        for (int i = 0; i < 2; i++) {
            if (!player.getInventory().usePotion(player)) {
                System.out.println("You do not have enough Potions.");
                Utils.pause();
                return;
            }
        }

        healUnlocked = true;

        System.out.println("\nSPELL UNLOCKED: HEALING LIGHT");
        System.out.println("The wizard says: \"Call upon it when you are near defeat.\"");
        Utils.pause();
    }

    // ---------------- TIME SLOW ----------------
    private static void trialOfTime(Player player, Scanner input) {
        if (slowUnlocked) {
            System.out.println("The wizard says: \"Time already bends for you.\"");
            Utils.pause();
            return;
        }

        System.out.println(
                "WIZARD: \"Time does not yield easily.\"");
        System.out.println(
                "A creature from between moments will be summoned.");
        System.out.println(
                "Do you wish to face the Trial of Time? (y/n)");
        System.out.print("> ");

        String choice = input.nextLine().trim().toLowerCase();

        if (!choice.equals("y")) {
            System.out.println("The wizard lowers his staff.");
            Utils.pause();
            return;
        }

        Utils.clear();

        Enemy wisp = new Enemy("Temporal Wisp", 75, 16);
        int result = BattleSystem.fight(player, wisp, input);

        if (result == 0) {
            slowUnlocked = true;
            System.out.println("\nSPELL UNLOCKED: TIME SLOW");
            Utils.pause();
            return;
        }

        if (result == -1) {
            System.out.println("\nYOU HAVE FALLEN.");
            System.exit(0);
        }
    }

    // -------- SPELL CHECKERS (used by BattleSystem) --------

    public static boolean hasFireBolt() {
        return fireUnlocked;
    }

    public static boolean hasHealingLight() {
        return healUnlocked;
    }

    public static boolean hasTimeSlow() {
        return slowUnlocked;
    }
}
