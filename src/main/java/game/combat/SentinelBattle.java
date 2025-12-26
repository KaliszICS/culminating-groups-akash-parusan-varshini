package game.combat;

import java.util.Random;
import java.util.Scanner;
import game.util.Utils;
import game.GridQuestRPG;
import game.characters.Player;
import game.ui.WizardHandler;

/**
 * The SentinelBattle class handles the final boss encounter in the game.
 * It presents narrative dialogue, manages a unique combat loop,
 * and determines the outcome of the final battle.
 *
 * This class is separated from the normal BattleSystem to keep
 * the boss-specific logic isolated from regular fights and to keep it
 * organized.
 *
 * @author Akash K.
 * @author Parusan P.
 * @author Varshini B.
 * @version 1.0
 */
// [Classes]
public class SentinelBattle {

    /**
     * Initiates and controls the Sentinel boss fight.
     * The player may choose to engage or retreat before combat begins.
     *
     * @param player the player participating in the boss battle
     * @param input  the scanner used for user input
     * @return true if the player defeats the Sentinel, false otherwise
     */
    // [Classes]
    // [File I/O] (loads ASCII art from external file)
    public static boolean fight(Player player, Scanner input) {
        boolean healingUsed = false;
        boolean timeSlowActive = false;
        Utils.clear();
        String[] castleArt = GridQuestRPG.loadArtFromFile("sentinel_road_intro.txt"); // [File I/O]
        for (String line : castleArt) {
            System.out.println(line);
        }

        Utils.pause();

        Random rand = new Random();

        try {
            Thread.sleep(1000);
            System.out.println("The road narrows. The air turns heavy...");
            Thread.sleep(1000);
            System.out.println("Iron footsteps echo ahead.");
            Thread.sleep(1000);
            System.out.println("A towering figure emerges from the fog.");
            Thread.sleep(1000);
            System.out.println("Behind it, the silhouette of a ruined castle looms.");
            Thread.sleep(1000);
            System.out.println("\nTHE SENTINEL: \"Glass breaks. Iron remains.\"");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }

        System.out.println("\n[1] Draw your weapon");
        System.out.println("[2] Retreat to the road");
        System.out.print("> ");

        String preChoice = input.nextLine().trim();

        if (preChoice.equals("2")) {
            System.out.println("\nYou step back as the fog swallows the figure once more...");
            Utils.pause();
            return false;
        }

        int bossHP = 150;
        boolean armorCracked = false;

        while (bossHP > 0 && player.getHealth() > 0) {
            Utils.clear();

            System.out.println("========================================");
            System.out.println("           BOSS: THE SENTINEL");
            System.out.println("========================================");
            System.out.println("Sentinel HP: " + bossHP + "/150");
            System.out.println("Your HP    : " + player.getHealth() + "/100");
            System.out.println("\n[1] Attack");
            System.out.println("[2] Use Item");
            if (WizardHandler.hasFireBolt()
                    || WizardHandler.hasHealingLight()
                    || WizardHandler.hasTimeSlow()) {
                System.out.println("[3] Cast Spell");
            }

            System.out.print("> ");

            String choice = input.nextLine().trim();
            // -------- CAST SPELL --------
            if (choice.equals("3")) {

                System.out.println("\n[1] Fire Bolt");
                System.out.println("[2] Healing Light");
                System.out.println("[3] Time Slow");
                System.out.print("> ");

                String spell = input.nextLine().trim();

                // Healing Light
                if (spell.equals("2")) {

                    if (!WizardHandler.hasHealingLight()) {
                        System.out.println("You have not learned that spell.");

                    } else if (healingUsed) {
                        System.out.println("Healing Light has already been used.");

                    } else {
                        player.setHealth(100);
                        healingUsed = true;
                        System.out.println("\nHealing Light restores you completely!");
                    }

                    Utils.pause();
                    continue;
                }

                // Time Slow
                if (spell.equals("3")) {

                    if (!WizardHandler.hasTimeSlow()) {
                        System.out.println("You have not learned that spell.");

                    } else if (timeSlowActive) {
                        System.out.println("Time is already distorted.");

                    } else {
                        timeSlowActive = true;
                        System.out.println("\nTime slows around the Sentinel!");
                    }

                    Utils.pause();
                    continue;
                }

                // Fire Bolt
                System.out.println("Fire Bolt empowers your attacks passively.");
                Utils.pause();
                continue;
            }

            if (choice.equals("1")) {
                int pDamage = rand.nextInt(16) + 15;

                if (WizardHandler.hasFireBolt()) {
                    pDamage += 10;
                }

                if (WizardHandler.hasFireBolt()) {
                    pDamage += 10;
                }
                bossHP -= pDamage;
                System.out.println("\nYou strike the Sentinel for " + pDamage + "!");
                Utils.pause();

            } else if (choice.equals("2")) {

                player.getInventory().display();
                System.out.println("\n[1] Potion  [2] Apple  [3] Back");
                System.out.print("> ");
                String itemChoice = input.nextLine().trim();

                if (itemChoice.equals("3"))
                    continue;

                if (itemChoice.equals("1")) {
                    if (player.getInventory().usePotion(player)) {
                        System.out.println("\nYou drink a Potion.");
                        System.out.println("Health restored to: " + player.getHealth() + "/100");
                    } else {
                        System.out.println("\nYou have no Potions!");
                    }
                } else if (itemChoice.equals("2")) {
                    if (player.getInventory().useApple(player)) {
                        System.out.println("\nYou eat an Apple.");
                        System.out.println("Health restored to: " + player.getHealth() + "/100");
                    } else {
                        System.out.println("\nYou have no Apples!");
                    }
                }

                Utils.pause();
            }

            if (bossHP > 0) {
                if (timeSlowActive) {
                    System.out.println("\nThe Sentinel is frozen in time!");
                    timeSlowActive = false;
                    Utils.pause();
                    continue;
                }
                try {
                    Thread.sleep(1000);

                    int sDamage;
                    if (bossHP <= 50) {
                        if (!armorCracked) {
                            System.out.println("\nCracks form in the Sentinel's armor...");
                            armorCracked = true;
                            Thread.sleep(1000);
                        }
                        sDamage = rand.nextInt(10) + 10;
                    } else {
                        sDamage = rand.nextInt(16) + 10;
                    }

                    player.takeDamage(sDamage);
                    System.out.println("\nThe Sentinel smashes you for " + sDamage + "!");
                    Thread.sleep(1000);
                    System.out.println("[ Press ENTER to continue ]");
                    input.nextLine();

                } catch (InterruptedException e) {
                }
            }
        }

        if (player.getHealth() > 0) {
            try {
                Thread.sleep(1000);
                System.out.println("\nTHE SENTINEL: \"You... are iron.\"");
                Thread.sleep(1000);
                System.out.println("The giant shatters into cold dust.");
                Thread.sleep(1000);
                System.out.println("\nYou have beaten the RPG.");
                Thread.sleep(1000);
                System.out.println("But the world remains open - so feel free to explore!");
                Thread.sleep(1000);
                System.out.println("\n(Press ENTER)");
                input.nextLine();
            } catch (InterruptedException e) {
            }
            return true;
        }

        System.out.println("\nYOU HAVE FALLEN.");
        System.exit(0);
        return false;
    }
}
