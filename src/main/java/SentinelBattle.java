// Classes
// Dedicated boss battle logic separated from normal combat.
import java.util.Random;
import java.util.Scanner;

public class SentinelBattle {

    public static boolean fight(Player player, Scanner input) {
        Utils.clear();
        String[] castleArt = GridQuestRPG.loadArtFromFile("sentinel_road_intro.txt");
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
        } catch (InterruptedException e) {}

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
            System.out.print("> ");

            String choice = input.nextLine().trim();

            if (choice.equals("1")) {
                int pDamage = rand.nextInt(16) + 15;
                bossHP -= pDamage;
                System.out.println("\nYou strike the Sentinel for " + pDamage + "!");
                Utils.pause();

            } else if (choice.equals("2")) {

                player.getInventory().display();
                System.out.println("\n[1] Potion  [2] Apple  [3] Back");
                System.out.print("> ");
                String itemChoice = input.nextLine().trim();

                if (itemChoice.equals("3")) continue;

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

                } catch (InterruptedException e) {}
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
            } catch (InterruptedException e) {}
            return true;
        }

        System.out.println("\nYOU HAVE FALLEN.");
        System.exit(0);
        return false;
    }
}
