package game.combat;

import java.util.Scanner;
import game.util.Utils;
import game.GridQuestRPG;
import game.characters.Enemy;
import game.characters.Player;
import game.characters.Sentinel;
import game.ui.WizardHandler;

/**
 * The SentinelBattle class manages the final boss encounter in the game.
 * It controls the cinematic introduction, player decision-making,
 * spell-enhanced combat, and the conclusion of the storyline.
 *
 * This class exists separately from the regular BattleSystem so that
 * the Sentinel fight can include unique dialogue, pacing, and mechanics.
 *
 * @author Akash K.
 * @author Parusan P.
 * @author Varshini B.
 * @version 1.0
 */
public class SentinelBattle { // Classes

    /**
     * Runs the Sentinel boss encounter.
     * Displays a cinematic introduction, allows the player to choose whether
     * to engage or retreat, and manages a spell-enhanced boss combat loop.
     *
     * @param player the player participating in the boss fight
     * @param input  scanner used for player input
     * @return true if the Sentinel is defeated, false if the player retreats
     */
    public static boolean fight(Player player, Scanner input) { // Classes
        Utils.clear();

        String[] castleArt = GridQuestRPG.loadArtFromFile("sentinel_road_intro.txt");
        // File I/O
        for (String line : castleArt) {
            System.out.println(line);
        }

        Utils.pause();

        try {
            Thread.sleep(1000);
            System.out.println("The road narrows. The air turns heavy...");
            Thread.sleep(1000);
            System.out.println("Iron footsteps echo ahead.");
            Thread.sleep(1000);
            System.out.println("A towering figure emerges from the fog.");
            Thread.sleep(1000);
            System.out.println("\nTHE SENTINEL: \"Glass breaks. Iron remains.\"");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }

        System.out.println("\n[1] Draw your weapon");
        System.out.println("[2] Retreat to the road");
        System.out.print("> ");

        if (!input.nextLine().trim().equals("1")) {
            System.out.println("\nYou step back as the fog swallows the figure...");
            Utils.pause();
            return false;
        }

        Enemy sentinel = new Sentinel();
        // Inheritance
        // Polymorphism

        boolean fireBoltPrimed = false;
        boolean healingUsed = false;
        boolean timeSlowActive = false;
        boolean fireBoltUsed = false;
        boolean timeSlowUsed = false;

        try {
            while (player.isAlive() && sentinel.isAlive()) {
                // Polymorphism

                Utils.clear();

                System.out.println("================================");
                System.out.println("Player HP: " + player.getHealth() + "/100");
                System.out.println("Sentinel HP: " + sentinel.getHealth() + "/150");
                System.out.println("================================");

                System.out.println("[1] Attack");
                System.out.println("[2] Use Item");
                System.out.println("[3] Cast Spell");
                System.out.print("> ");

                String choice = input.nextLine().trim();

                if (!choice.equals("1") && !choice.equals("2") && !choice.equals("3")) {
                    System.out.println("\nUnknown command.");
                    Thread.sleep(1200);
                    continue;
                }

                if (choice.equals("2")) {
                    game.inventory.InventoryMenu.open(player, input);
                    // Abstract classes and interfaces
                    continue;
                }

                if (choice.equals("3")) {

                    System.out.println("\n[1] Fire Bolt");
                    System.out.println("[2] Healing Light");
                    System.out.println("[3] Time Slow");
                    System.out.print("> ");

                    String spell = input.nextLine().trim();

                    if (spell.equals("1") && WizardHandler.getFireBolt()) {

                        if (fireBoltUsed) {
                            System.out.println("\nThe flames have already been spent.");
                            Thread.sleep(1200);
                            continue;
                        }

                        fireBoltPrimed = true;
                        fireBoltUsed = true;
                        System.out.println("\nFlames gather around your weapon...");
                        Thread.sleep(1200);
                        continue;
                    }

                    if (spell.equals("2") && WizardHandler.getHealingLight()) {

                        if (!healingUsed) {
                            player.setHealth(player.getMaxHealth());
                            healingUsed = true;
                            System.out.println("\nHealing Light restores you completely!");
                            Thread.sleep(1200);
                        } else {
                            System.out.println("\nHealing Light has already been used.");
                            Thread.sleep(1200);
                        }
                        continue;
                    }

                    if (spell.equals("3") && WizardHandler.getTimeSlow()) {

                        if (timeSlowUsed) {
                            System.out.println("\nTime refuses to bend again.");
                            Thread.sleep(1200);
                            continue;
                        }

                        timeSlowActive = true;
                        timeSlowUsed = true;
                        System.out.println("\nTime bends around the Sentinel...");
                        Thread.sleep(1200);
                        continue;
                    }

                    System.out.println("\nYou have not learned that spell.");
                    Thread.sleep(1200);
                    continue;
                }

                if (choice.equals("1")) {
                    int dmg = (int) (Math.random() * 11) + 10;
                    // Randomized damage calculation

                    if (fireBoltPrimed) {
                        dmg += 10;
                        fireBoltPrimed = false;
                    }

                    sentinel.takeDamage(dmg);
                    // Polymorphism

                    System.out.println("\nYou strike the Sentinel for " + dmg + "!");
                    Thread.sleep(1200);
                }

                if (sentinel.isAlive()) {

                    if (timeSlowActive) {
                        System.out.println("\nThe Sentinel is frozen in time!");
                        timeSlowActive = false;
                        Thread.sleep(1200);

                    } else {
                        int sDmg = (int) (Math.random() * 16) + 15;
                        player.takeDamage(sDmg);
                        // Polymorphism

                        System.out.println("\nThe Sentinel smashes you for " + sDmg + "!");
                        Thread.sleep(1200);
                    }
                }

            }

            if (!player.isAlive()) {
                System.out.println("\nYOU HAVE FALLEN.");
                Thread.sleep(1200);
                System.exit(0);
            }

            Thread.sleep(1200);
            System.out.println("\nTHE SENTINEL: \"You... are iron.\"");
            Thread.sleep(1200);
            System.out.println("The giant shatters into cold dust.");
            Thread.sleep(1200);
            System.out.println("\nYou have beaten the RPG.");
            Thread.sleep(1200);
            System.out.println("But the world remains open - so feel free to explore!");
            Thread.sleep(1200);
            System.out.println("\n(Press ENTER)");
            input.nextLine();

        } catch (InterruptedException e) {
        }

        return true;
    }
}