package game.combat;

import java.util.Scanner;
import game.util.Utils;
import game.characters.Player;
import game.characters.Enemy;

/**
 * The BattleSystem class manages turn-based combat between the player
 * and enemies.
 * It coordinates player choices, enemy responses, and determines
 * the outcome of battles.
 *
 * This class demonstrates polymorphism by calling the overridden
 * takeTurn() method on Enemy objects.
 *
 * @author Akash K.
 * @author Parusan P.
 * @author Varshini B.
 * @version 1.0
 */
// [Classes]
// [Polymorphism]
public class BattleSystem {

    /**
     * Runs a turn-based fight between a player and an enemy.
     * The method continues until either the player or the enemy
     * is defeated, or the player chooses to run.
     *
     * @param player the player participating in the fight
     * @param enemy the enemy being fought
     * @param input the scanner used for user input
     * @return 0 if enemy is defeated, 1 if player runs, -1 if player dies
     */
    // [Classes]
    // [Polymorphism]
    public static int fight(Player player, Enemy enemy, Scanner input) {

        while (player.isAlive() && enemy.isAlive()) {
            Utils.clear();
            displayStatus(player, enemy);

            System.out.println("[1] Attack");
            System.out.println("[2] Use Item");
            System.out.println("[3] Run");
            System.out.print("> ");

            String choice = input.nextLine().trim();
            boolean turnConsumed = false;

            // ---------- PLAYER TURN ----------
            if (choice.equals("1")) {
                int dmg = player.getAttack();
                System.out.println("\nYou attack for " + dmg + " damage!");
                enemy.takeDamage(dmg);
                turnConsumed = true;
                Utils.pause();

            } else if (choice.equals("2")) {

                if (player.getHealth() >= player.getMaxHealth()) {
                    System.out.println("\nAlready at max health!");
                    Utils.pause();
                    continue;
                }

                player.getInventory().display();
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

            } else {
                System.out.println("\nInvalid choice.");
                Utils.pause();
                continue;
            }

            // ---------- ENEMY TURN ----------
            if (turnConsumed && enemy.isAlive()) {
                enemy.takeTurn(player); // [Polymorphism]
                Utils.pause();
            }
        }

        return player.isAlive() ? 0 : -1;
    }

    /**
     * Displays the current health status of the player and enemy.
     *
     * @param p the player
     * @param e the enemy
     */
    // [Classes]
    private static void displayStatus(Player p, Enemy e) {
        System.out.println("================================");
        System.out.println("Player HP: " + p.getHealth() + "/" + p.getMaxHealth());
        System.out.println(e.getName() + " HP: " + e.getHealth() + "/" + e.getMaxHealth());
        System.out.println("================================");
    }
}
