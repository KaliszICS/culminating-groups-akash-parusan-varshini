import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class GridQuestRPG {
    private static boolean sentinelDefeated = false;
    private static Scanner input = new Scanner(System.in);
    private static ArrayList<Room> map = new ArrayList<>();
    private static Player player;
    private static int currentRoom = 0;
    private static ArrayList<Riddle> riddles = new ArrayList<>();
    private static boolean chestOpened = false;
    private static WorldMap worldMap = new WorldMap();

    public static void main(String[] args) {
        player = new Player();
        showStartMenu();
        playIntro();
        createWorld();
        loadRiddles();
        gameLoop();
    }

    private static void showStartMenu() {
        while (true) {
            Utils.clear();
            System.out.println("========================================");
            System.out.println("           GRIDQUEST RPG");
            System.out.println("========================================\n");
            System.out.println("   [1] Start Game");
            System.out.println("   [2] Help");
            System.out.println("   [3] Quit\n");
            System.out.println("========================================");
            System.out.print("> ");

            String choice = input.nextLine();

            if (choice.equals("1"))
                return;
            if (choice.equals("2"))
                showHelpMenu();
            if (choice.equals("3"))
                System.exit(0);
        }
    }

    private static void showHelpMenu() {
        Utils.clear();
        System.out.println("========================================");
        System.out.println("                HELP");
        System.out.println("========================================");
        System.out.println("Commands:");
        System.out.println("go up | go down | go left | go right");
        System.out.println("attack             -> Fight an enemy in the room");
        System.out.println("inventory (or 'i') -> View and use your items");
        System.out.println("help               -> Show this menu");
        System.out.println("quit               -> Exit the game");
        System.out.println("========================================");
        Utils.pause();
    }
    // ================= INTRO =================

    private static void playIntro() {
        Utils.clear();
        System.out.println("A hooded wizard appears.");
        System.out.println("\"It's dangerous to go alone! Take this.\"");
        System.out.println("You received a MAP.");
        Utils.pause();
    }

    // ================= WORLD =================

    private static void createWorld() {
        Room r0 = new Room("Forest Clearing", "Trees close in around you...");
        r0.setArt(loadArtFromFile("forest_art.txt"));
        r0.setEnemy(new Enemy("Goblin", 40, 10));
        r0.addExit("right", 1);
        map.add(r0);

        // Room 1: Town Square
        Room r1 = new Room("Town Square", "The town center. Left: Tavern, Right: Storage, Up: The Forgotten Road.");
        r1.setArt(loadArtFromFile("town_art.txt"));
        r1.addExit("right", 2);
        r1.addExit("left", 3);
        r1.addExit("up", 4);
        map.add(r1);

        // Room 2: Storage
        Room r2 = new Room("Abandoned Storage", "A quiet room with a single chest.");
        r2.setArt(loadArtFromFile("storage_art.txt"));
        r2.addExit("left", 1);
        map.add(r2);

        // Room 3: The Lucky Tavern
        Room r3 = new Room("The Lucky Tavern", "Smells of old wood. Type 'gamble' to play.");
        r3.setArt(loadArtFromFile("arcade_art.txt"));
        r3.addExit("right", 1);
        map.add(r3);

        // Room 4: The Forgotten Road
        Room r4 = new Room("The Forgotten Road",
                "The path ahead is clear and silent. Something massive watches from beyond the fog.");
        r4.setArt(loadArtFromFile("road_art.txt"));
        r4.addExit("down", 1);
        r4.addExit("up", -1);
        map.add(r4);
    }

    private static void loadRiddles() {
        riddles.add(new Riddle("What has keys but can't open locks?", "keyboard"));
        riddles.add(new Riddle("What runs but never walks?", "water"));
        riddles.add(new Riddle("What has a head and a tail but no body?", "coin"));
        riddles.add(new Riddle("What gets wetter the more it dries?", "towel"));
        riddles.add(new Riddle("What can travel around the world while staying in one spot?", "stamp"));
        riddles.add(new Riddle("What has hands but cannot clap?", "clock"));
        riddles.add(new Riddle("What has an eye but cannot see?", "needle"));
        riddles.add(new Riddle("What goes up but never comes down?", "age"));
        riddles.add(new Riddle("What has a neck but no head?", "bottle"));
        riddles.add(new Riddle("What has words but never speaks?", "book"));
    }

    // ================= GAME LOOP =================
    private static void gameLoop() {
        boolean redraw = true;

        while (true) {
            Room room = map.get(currentRoom);

            if (redraw) {
                Utils.clear();
                room.display();
                redraw = false;
            }

            System.out.print("> ");
            String cmd = input.nextLine().toLowerCase().trim();

            // 1. QUIT
            if (cmd.equals("quit"))
                return;

            // 2. HELP
            if (cmd.equals("help")) {
                showHelpMenu();
                redraw = true;
                continue;
            }

            // 3. INVENTORY
            if (cmd.equals("inventory") || cmd.equals("i") || cmd.equals("items")) {
                Utils.clear();
                player.getInventory().display();
                System.out.println("\nActions: [1] Potion [2] Apple [3] Organize Bag [4] Search [5] Back");
                System.out.print("> ");
                String invCmd = input.nextLine().trim();

                if (invCmd.equals("1")) {
                    if (player.getHealth() >= 100) {
                        System.out.println("\nAlready at max health (100/100)!");
                    } else if (player.getInventory().usePotion(player)) {
                        System.out.println("\nHealed! Current health: " + player.getHealth() + "/100");
                    } else {
                        System.out.println("\nYou don't have any Potions!");
                    }
                    Utils.pause();
                } else if (invCmd.equals("2")) {
                    if (player.getHealth() >= 100) {
                        System.out.println("\nAlready at max health (100/100)!");
                    } else if (player.getInventory().useApple(player)) {
                        System.out.println("\nHealed! Current health: " + player.getHealth() + "/100");
                    } else {
                        System.out.println("\nYou don't have any Apples!");
                    }
                    Utils.pause();
                } else if (invCmd.equals("3")) {
                    player.getInventory().selectionSort();
                    System.out.println("\nInventory organized alphabetically!");
                    Utils.pause();
                } else if (invCmd.equals("4")) {
                    System.out.print("\nEnter item name to find: ");
                    String find = input.nextLine();
                    int index = player.getInventory().sequentialSearch(find);
                    if (index != -1) {
                        System.out.println("'" + find + "' found in backpack slot " + (index + 1) + ".");
                    } else {
                        System.out.println("Item not found in your inventory.");
                    }
                    Utils.pause();
                }
                redraw = true;
                continue;
            }

            // 4. OPEN CHEST
            if ((cmd.equals("open chest") || cmd.equals("open") || cmd.equals("chest"))
                    && room.getName().equals("Abandoned Storage")) {
                if (chestOpened) {
                    System.out.println("\nYou already took my stuff! What more could you want!");
                    Utils.pause();
                } else {
                    Collections.shuffle(riddles);
                    Riddle r = riddles.get(0);
                    Utils.clear();
                    System.out.println("A chest blocks your path...:\n" + r.getQuestion());
                    System.out.print("> ");
                    if (r.checkAnswer(input.nextLine())) {
                        System.out.println("\nOpened!");
                        System.out.println("You found a Potion and an Apple. (Teachers like apples right?)");
                        player.getInventory().addItem("Potion");
                        player.getInventory().addItem("Apple");
                        chestOpened = true;
                    } else {
                        System.out.println("\nThe chest remains locked.");
                    }
                    Utils.pause();
                }
                redraw = true;
                continue;
            }

            // 5. ATTACK
            if (cmd.equals("attack")) {
                if (room.hasEnemy()) {
                    Enemy enemy = room.getEnemy();
                    int result = BattleSystem.fight(player, enemy, input);

                    if (result == 1) {
                        if (currentRoom == 0) {
                            room.defeatEnemy();
                            currentRoom = 1;
                            player.setX(1);
                            player.setY(1);
                            redraw = true;
                            continue;
                        }

                        redraw = true;
                        continue;
                    }

                    if (result == -1) {
                        System.out.println("GAME OVER.");
                        return;
                    }

                    if (result == 0) {
                        room.defeatEnemy();

                        if (currentRoom == 0) {
                            System.out.println("\nVictory! The path is clear.");
                            Utils.pause();

                            currentRoom = 1;
                            player.setX(1);
                            player.setY(1);
                            redraw = true;
                            continue;
                        }

                        System.out.println("\nVictory! The path is clear.");
                        Utils.pause();
                    }
                    redraw = true;
                } else {
                    System.out.println("Nothing to fight here.");
                }
                continue;
            }

            if (cmd.equals("save")) {
                SaveManager.saveGame(currentRoom, player, player.getInventory());
                redraw = true;
                continue;
            }

            if (cmd.equals("load")) {
                int[] data = SaveManager.loadGame(player);
                if (data != null) {
                    currentRoom = data[0];
                }
                redraw = true;
                continue;
            }

            if (cmd.equals("map")) {
                Utils.clear();
                worldMap.display(currentRoom);
                Utils.pause();
                redraw = true;
                continue;
            }

            if (cmd.equals("gamble")) {
                if (room.getName().contains("Arcade") || room.getName().contains("Tavern")) {
                    playGamble();
                } else {
                    System.out.println("No gambling equipment here.");
                }
                redraw = true;
                continue;
            }

            if (cmd.startsWith("go ")) {
                String dir = cmd.substring(3).trim();

                if (room.getName().equals("The Forgotten Road") && dir.equals("up")) {

                    if (sentinelDefeated) {
                        System.out.println("\nThe Sentinel has already been defeated.");
                        System.out.println("Only silence remains beyond the fog.");
                        Utils.pause();
                        redraw = true;
                        continue;
                    }

                    boolean won = sentinelBossBattle(player);

                    if (won) {
                        sentinelDefeated = true;
                        redraw = true;
                    }
                    continue;

                }

                if (!dir.equals("up") && !dir.equals("down") && !dir.equals("left") && !dir.equals("right")) {
                    System.out.println("Invalid direction! Use: go <up|down|left|right>");
                    continue;
                }

                if (room.hasEnemy()) {
                    System.out.println("The enemy blocks the way! You must fight or run.");
                    continue;
                }

                if (room.getName().equals("Town Square") && dir.equals("down")) {
                    System.out.println("\n[!] The path back to the forest looks dark and unsafe.");
                    System.out.println("The wizard's voice echoes: 'It is better to stay in the light of the town.'");
                    Utils.pause();
                    redraw = true;
                    continue;
                }

                int nextRoomIndex = room.getExit(dir);
                if (nextRoomIndex >= 0) {
                    currentRoom = nextRoomIndex;
                    redraw = true;
                } else {
                    System.out.println("There is no exit to the " + dir + "!");
                }

                continue;
            }

            System.out.println("Unknown command.");
        }
    }

    // ================= UI =================

    private static String[] loadArtFromFile(String fileName) {
        ArrayList<String> lines = new ArrayList<>();
        java.io.File file = new java.io.File(fileName);

        if (!file.exists()) {
            return new String[] { " [ ERROR ] File not found: " + fileName };
        }

        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                lines.add(fileScanner.nextLine());
            }
        } catch (Exception e) {
            return new String[] { " [ ERROR ] Could not read file: " + fileName };
        }
        return lines.toArray(new String[0]);
    }

    private static void playGamble() {
        if (!player.getInventory().hasPotion()) {
            System.out.println("You need at least 1 Potion to gamble!");
            System.out.println("Press Enter to continue...");
            input.nextLine();
            return;
        }

        System.out.println(
                "The dealer eyes your bag. He smirks, asking 'Will you wager 1 potion for a chance at 2?' (y/n)");
        if (input.nextLine().equalsIgnoreCase("y")) {
            playGamble(1);
        }
    }

    private static void playGamble(int wager) {
        int cur = (int) (Math.random() * 10) + 1;
        int next = (int) (Math.random() * 10) + 1;

        System.out.println("\nDealer shows: [" + cur + "]. Next card Higher or Lower? (h/l)");
        String choice = input.nextLine().toLowerCase().trim();

        if ((choice.equals("h") && next >= cur) || (choice.equals("l") && next <= cur)) {
            System.out.println("Win! Card was " + next + ". Total: " + (wager * 2) + " potions.");
            System.out.println("Double or nothing? (y/n)");

            if (input.nextLine().equalsIgnoreCase("y")) {
                playGamble(wager * 2);
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

    public static boolean sentinelBossBattle(Player player) {
        Utils.clear();
        String[] castleArt = loadArtFromFile("sentinel_road_intro.txt");
        for (String line : castleArt) {
            System.out.println(line);
        }

        Utils.pause(); // Press ENTER after castle

        Random rand = new Random();

        // DO NOT clear here – old behavior kept the text flowing

        // Intro narration
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

        // ================= NEW PART (THIS WAS MISSING) =================
        System.out.println("\n[1] Draw your weapon");
        System.out.println("[2] Retreat to the road");
        System.out.print("> ");

        String preChoice = input.nextLine().trim();

        if (preChoice.equals("2")) {
            System.out.println("\nYou step back as the fog swallows the figure once more...");
            Utils.pause();
            return false; // EXIT BOSS, go back to road
        }
        // ===============================================================

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

            // ---------- PLAYER TURN ----------
            if (choice.equals("1")) {
                int pDamage = rand.nextInt(16) + 15; // 15–30 dmg
                bossHP -= pDamage;
                System.out.println("\nYou strike the Sentinel for " + pDamage + "!");
                Utils.pause();

            } else if (choice.equals("2")) {

                player.getInventory().display();
                System.out.println("\n[1] Potion  [2] Apple  [3] Back");
                System.out.print("> ");
                String itemChoice = input.nextLine().trim();

                if (itemChoice.equals("3")) {
                    continue;
                }

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

            // ---------- SENTINEL TURN ----------
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
                        sDamage = rand.nextInt(10) + 10; // 10–19 dmg
                    } else {
                        sDamage = rand.nextInt(16) + 10; // 10–25 dmg
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

        // ---------- ENDING ----------
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
        } else {
            System.out.println("\nYOU HAVE FALLEN.");
            System.exit(0);
        }
        return true;
    }

    private static void playCastleEpilogue() {
        Utils.clear();

        String[] castleArt = loadArtFromFile("sentinel_road_intro.txt");
        for (String line : castleArt) {
            System.out.println(line);
        }

        Utils.pause();

        try {
            System.out.println("Iron and stone stand silent.");
            Thread.sleep(1000);
            System.out.println("The Sentinel lies shattered at your feet.");
            Thread.sleep(1000);
            System.out.println("Beyond the gates, the world breathes again.");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }

        System.out.println("\nYou have beaten the RPG.");
        System.out.println("But the world remains open — feel free to explore.");
        Utils.pause();
    }

}
