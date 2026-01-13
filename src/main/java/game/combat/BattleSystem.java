package game.combat;

import java.util.Scanner;
import game.util.Utils;
import game.characters.Player;
import game.characters.Enemy;
import game.ui.WizardHandler;
import game.characters.Sentinel;

/**
 * The BattleSystem class manages turn-based combat between the player
 * and enemies.
 * It coordinates player choices, enemy responses, and determines
 * the outcome of battles.
 *
 * @author Akash K.
 * @author Parusan P.
 * @author Varshini B.
 * @version 1.0
 */
public class BattleSystem { // Classes
    /** Maximum damage the player can deal to the Sentinel when using the basic Attack option. */
    private static final int SENTINEL_PLAYER_MAX_DAMAGE = 20;
    /** Minimum damage the player can deal to the Sentinel when using the basic Attack option. */
    private static final int SENTINEL_PLAYER_MIN_DAMAGE = 10;

    /**
     * Runs a turn-based fight between a player and an enemy.
     *
     * @param player the player participating in the fight
     * @param enemy  the enemy being fought
     * @param input  the scanner used for user input
     * @return 0 if enemy is defeated, 1 if player runs, -1 if player dies
     */
    public static int fight(Player player, Enemy enemy, Scanner input) { // Classes
        boolean healingUsed = false;
        boolean fireBoltActive = false;
        boolean enemyFrozen = false;
        boolean freezePending = false;

        while (player.isAlive() && enemy.isAlive()) {
            // Polymorphism

            if (freezePending) {
                enemyFrozen = true;
                freezePending = false;
            }

            Utils.clear();
            displayStatus(player, enemy);

            System.out.println("[1] Attack");
            System.out.println("[2] Use Item");
            System.out.println("[3] Run");
            if (WizardHandler.getFireBolt()
                    || WizardHandler.getHealingLight()
                    || WizardHandler.getTimeSlow()) {
                System.out.println("[4] Cast Spell");
            }

            System.out.print("> ");

            String choice = input.nextLine().trim();
            boolean turnConsumed = false;

            if (choice.equals("1")) {

                int dmg;

                if (enemy instanceof Sentinel) {
                    int range = SENTINEL_PLAYER_MAX_DAMAGE - SENTINEL_PLAYER_MIN_DAMAGE + 1;
                    dmg = (int) (Math.random() * range) + SENTINEL_PLAYER_MIN_DAMAGE;
                } else {
                    dmg = player.getAttack();
                }

                if (fireBoltActive) {
                    dmg += 10;
                    fireBoltActive = false;
                }

                if (enemy instanceof Sentinel) {
                    System.out.println("\nYou strike the Sentinel for " + dmg + "!");
                } else {
                    System.out.println("\nYou attack for " + dmg + " damage!");
                }

                enemy.takeDamage(dmg);
                // Polymorphism

                turnConsumed = true;
                Utils.pause();
            }

            else if (choice.equals("2")) {

                if (player.getHealth() >= player.getMaxHealth()) {
                    System.out.println("\nAlready at max health!");
                    Utils.pause();
                    continue;
                }

                player.getInventory().display();
                // Abstract classes and interfaces

                System.out.println("\n[1] Potion  [2] Apple  [3] Back");
                System.out.print("> ");
                String itemChoice = input.nextLine().trim();

                if (itemChoice.equals("1")) {
                    if (player.getInventory().usePotion(player)) {
                        System.out.println("\nYou drank a Potion!");
                        System.out.println("Health restored to: "
                                + player.getHealth() + "/" + player.getMaxHealth());
                        turnConsumed = true;
                    } else {
                        System.out.println("\nNo Potions left!");
                    }
                    Utils.pause();

                } else if (itemChoice.equals("2")) {
                    if (player.getInventory().useApple(player)) {
                        System.out.println("\nYou ate an Apple!");
                        System.out.println("Health restored to: "
                                + player.getHealth() + "/" + player.getMaxHealth());
                        turnConsumed = true;
                    } else {
                        System.out.println("\nNo Apples left!");
                    }
                    Utils.pause();

                } else {
                    continue;
                }

            } else if (choice.equals("3")) {
                System.out.println("\nYou fled the battle!");
                Utils.pause();
                return 1;

            } else if (choice.equals("4")) {

                System.out.println("\n[1] Fire Bolt");
                System.out.println("[2] Healing Light");
                System.out.println("[3] Time Slow");
                System.out.print("> ");

                String spell = input.nextLine().trim();

                if (spell.equals("2")) {

                    if (!WizardHandler.getHealingLight()) {
                        System.out.println("You have not learned that spell.");
                        // Searching

                    } else if (healingUsed) {
                        System.out.println("Healing Light has already been used.");

                    } else {
                        player.setHealth(player.getMaxHealth());
                        healingUsed = true;
                        turnConsumed = true;
                        System.out.println("\nHealing Light restores you completely!");
                    }

                    Utils.pause();
                }

                else if (spell.equals("3")) {

                    if (!WizardHandler.getTimeSlow()) {
                        System.out.println("You have not learned that spell.");
                        // Searching

                    } else if (freezePending || enemyFrozen) {
                        System.out.println("Time is already distorted.");
                    } else {
                        turnConsumed = true;
                        freezePending = true;
                        System.out.println("\nTime slows around your enemy!");
                    }

                    Utils.pause();
                }

                else if (spell.equals("1")) {

                    if (!WizardHandler.getFireBolt()) {
                        System.out.println("You have not learned that spell.");
                        // Searching

                    } else if (fireBoltActive) {
                        System.out.println("Fire Bolt is already primed.");

                    } else {
                        fireBoltActive = true;
                        turnConsumed = true;
                        System.out.println("\nFlames gather around your weapon!");
                    }

                    Utils.pause();
                }

            }

            else {
                System.out.println("\nInvalid choice.");
                Utils.pause();
                continue;
            }

            if (turnConsumed && enemy.isAlive()) {
                if (enemyFrozen) {
                    System.out.println("\nThe enemy is frozen in time!");
                    enemyFrozen = false;
                    Utils.pause();
                } else {
                    enemy.takeTurn(player);
                    // Polymorphism
                    Utils.pause();
                }
            }
        }
        return player.isAlive() ? 0 : -1;
    }

    /**
     * Displays the current health status of both the player and the enemy.
     *
     * @param p the player
     * @param e the enemy
     */
    private static void displayStatus(Player p, Enemy e) { // Classes
        System.out.println("================================");
        System.out.println("Player HP: " + p.getHealth() + "/" + p.getMaxHealth());
        System.out.println(e.getName() + " HP: " + e.getHealth() + "/" + e.getMaxHealth());
        System.out.println("================================");
    }
}