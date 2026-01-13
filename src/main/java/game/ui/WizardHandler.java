package game.ui;

import java.util.ArrayList;
import java.util.Scanner;

import game.characters.Enemy;
import game.characters.Player;
import game.combat.BattleSystem;
import game.riddles.Riddle;
import game.riddles.RiddleBank;
import game.util.Utils;

/**
 * Handles all interactions with the wizard character.
 * This includes spell trials, unlocking abilities, and
 * managing spell availability for combat.
 *
 * @author Akash K.
 * @author Parusan P.
 * @author Varshini B.
 * @version 1.0
 */
public class WizardHandler { // Classes
    /**
     * The starting and maximum health value for the Temporal Wisp summoned in the
     * Trial of Time.
     */
    private static final int TEMPORAL_WISP_HEALTH = 75;

    /**
     * The base attack damage for the Temporal Wisp summoned in the Trial of Time.
     */
    private static final int TEMPORAL_WISP_ATTACK = 16;

    /**
     * Number of riddles the player must answer correctly to unlock the Fire Bolt
     * spell.
     */
    private static final int FLAME_TRIAL_RIDDLES_REQUIRED = 2;

    /**
     * Number of potions the player must donate to unlock the Healing Light spell.
     */
    private static final int HEALING_TRIAL_POTIONS_REQUIRED = 2;
    private static boolean fireUnlocked = false;
    private static boolean healUnlocked = false;
    private static boolean slowUnlocked = false;

    /**
     * Controls the wizard interaction menu.
     * Allows the player to select and complete spell trials
     * or leave the Arcane Study.
     *
     * @param player the player interacting with the wizard
     * @param input  scanner used for player input
     */
    public static void interact(Player player, Scanner input) { // Classes

        boolean done = false;

        while (!done) { // Iteration
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

    /**
     * Runs the Trial of Flame.
     * The player must correctly answer two riddles in a row
     * to unlock the Fire Bolt spell.
     *
     * @param input scanner used for player input
     */
    private static void trialOfFlame(Scanner input) {
        if (fireUnlocked) {
            System.out.println("The wizard says: \"You already wield Fire Bolt.\"");
            Utils.pause();
            return;
        }

        System.out.println("WIZARD: \"Two truths, spoken without error.\"");
        Utils.pause();

        ArrayList<Riddle> riddles = RiddleBank.load(); // File I/O
        RiddleBank.shuffle(riddles); // Sorting

        for (int i = 0; i < FLAME_TRIAL_RIDDLES_REQUIRED; i++) { // Iteration
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

    /**
     * Runs the Trial of Light.
     * The player must donate two potions to unlock
     * the Healing Light spell.
     *
     * @param player the player completing the trial
     * @param input  scanner used for player input
     */
    private static void trialOfLight(Player player, Scanner input) {
        if (healUnlocked) {
            System.out.println("The wizard says: \"You already know this spell.\"");
            Utils.pause();
            return;
        }

        System.out.println("WIZARD: \"Healing Light requires sacrifice.\"");
        System.out.println("Donate 2 Potions to learn Healing Light? (y/n)");
        System.out.print("> ");

        String choice = input.nextLine().trim().toLowerCase();

        if (!choice.equals("y")) {
            System.out.println("You step back from the altar.");
            Utils.pause();
            return;
        }

        for (int i = 0; i < HEALING_TRIAL_POTIONS_REQUIRED; i++) { // Iteration
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

    /**
     * Runs the Trial of Time.
     * The player must defeat a summoned enemy in combat
     * to unlock the Time Slow spell.
     *
     * @param player the player completing the trial
     * @param input  scanner used for player input
     */
    private static void trialOfTime(Player player, Scanner input) { // Polymorphism
        if (slowUnlocked) {
            System.out.println("The wizard says: \"Time already bends for you.\"");
            Utils.pause();
            return;
        }

        System.out.println("WIZARD: \"Time does not yield easily.\"");
        System.out.println("A creature from between moments will be summoned.");
        System.out.println("Do you wish to face the Trial of Time? (y/n)");
        System.out.print("> ");

        String choice = input.nextLine().trim().toLowerCase();

        if (!choice.equals("y")) {
            System.out.println("The wizard lowers his staff.");
            Utils.pause();
            return;
        }

        Utils.clear();

        Enemy wisp = new Enemy("Temporal Wisp", TEMPORAL_WISP_HEALTH, TEMPORAL_WISP_ATTACK); // Classes
        int result = BattleSystem.fight(player, wisp, input); // Polymorphism

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

    /**
     * Checks whether the Fire Bolt spell has been unlocked.
     *
     * @return true if Fire Bolt is unlocked
     */
    public static boolean getFireBolt() {
        return fireUnlocked;
    }

    /**
     * Checks whether the Healing Light spell has been unlocked.
     *
     * @return true if Healing Light is unlocked
     */
    public static boolean getHealingLight() {
        return healUnlocked;
    }

    /**
     * Checks whether the Time Slow spell has been unlocked.
     *
     * @return true if Time Slow is unlocked
     */
    public static boolean getTimeSlow() {
        return slowUnlocked;
    }

    /**
     * Loads saved spell unlock states.
     *
     * @param fire whether Fire Bolt is unlocked
     * @param heal whether Healing Light is unlocked
     * @param slow whether Time Slow is unlocked
     */
    public static void setSpells(boolean fire, boolean heal, boolean slow) { // Classes
        fireUnlocked = fire;
        healUnlocked = heal;
        slowUnlocked = slow;
    }
}