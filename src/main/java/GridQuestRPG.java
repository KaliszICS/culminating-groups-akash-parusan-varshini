import java.util.Collections;
import java.util.ArrayList;
import java.util.Scanner;

public class GridQuestRPG {
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
        Room r1 = new Room("Town Square", "The town center. Left: Tavern, Right: Storage.");
        r1.setArt(loadArtFromFile("town_art.txt"));
        r1.addExit("right", 2);
        r1.addExit("left", 3);
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
                    int result = battle(player, enemy);

                    if (result == -1) {
                        System.out.println("GAME OVER.");
                        return;
                    }

                    if (result == 0 || result == 1) {
                        if (result == 0) {
                            room.defeatEnemy();
                            System.out.println("\nVictory! The path is clear.");
                            Utils.pause();
                        }

                        int next = room.getExit("right");
                        if (next >= 0) {
                            currentRoom = next;
                        }
                    }
                    redraw = true;
                } else {
                    System.out.println("Nothing to fight here.");
                }
                continue;
            }

            if (cmd.equals("save")) {
                SaveManager.saveGame(currentRoom, player.getHealth(), player.getInventory());
                redraw = true;
                continue;
            }

            if (cmd.equals("load")) {
                int[] data = SaveManager.loadGame(player.getInventory());
                if (data != null) {
                    currentRoom = data[0];
                    player.setHealth(data[1]);
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

                // 1. Validate direction
                if (!dir.equals("up") && !dir.equals("down") && !dir.equals("left") && !dir.equals("right")) {
                    System.out.println("Invalid direction! Use: go <up|down|left|right>");
                    continue;
                }

                // 2. Battle Lock: Blocks movement if enemy is alive
                if (room.hasEnemy()) {
                    System.out.println("The enemy blocks the way! You must fight or run.");
                }

                // 3. ADD THIS: Forest Safety Lock
                // Inside your "go" command logic
                else if (room.getName().equals("Town Square") && dir.equals("down")) {
                    System.out.println("\n[!] The path back to the forest looks dark and unsafe.");
                    System.out.println("The wizard's voice echoes: 'It is better to stay in the light of the town.'");

                    Utils.pause(); // Wait for ENTER
                    redraw = true; // This tells the loop to clear and reprint the Town Square art
                }

                // 4. Normal Movement
                else {
                    int nextRoomIndex = room.getExit(dir);
                    if (nextRoomIndex >= 0) {
                        currentRoom = nextRoomIndex;
                        redraw = true;
                    } else {
                        System.out.println("You can't go that way.");
                    }
                }
                continue;
            }

            System.out.println("Unknown command.");
        }
    }

    // ================= BATTLE =================
    private static int battle(Player player, Enemy enemy) {
        Scanner battleInput = new Scanner(System.in);

        while (player.isAlive() && enemy.isAlive()) {
            Utils.clear();
            System.out.println("========================================");
            System.out.println("                BATTLE");
            System.out.println("========================================");

            System.out.println("Player HP: " + healthBar(player.getHealth(), 100));
            System.out.println("Enemy  HP: " + healthBar(enemy.getHealth(), 40));

            System.out.println("\nChoose an action:");
            System.out.println("[1] Attack");
            System.out.println("[2] Use Item");
            System.out.println("[3] Run");

            System.out.print("> ");
            String choice = battleInput.nextLine().toLowerCase().trim();

            boolean turnConsumed = false;

            if (choice.equals("1")) {
                System.out.println("\nYou attack for " + player.attack + " damage!");
                enemy.takeDamage(player.attack);
                turnConsumed = true;
            } else if (choice.equals("2")) {
                if (player.getHealth() >= 100) {
                    System.out.println("\nAlready at max health (100/100)!");
                    Utils.pause();
                    continue;
                }

                player.getInventory().display();
                System.out.println("\nWhat will you use?");
                System.out.println("[1] Potion  [2] Apple  [3] Back");
                System.out.print("> ");
                String itemChoice = battleInput.nextLine().trim();

                if (itemChoice.equals("1")) {
                    if (player.getInventory().usePotion(player)) {
                        System.out.println("\nSuccess: You drank a Potion!");
                        System.out.println("Health restored to: " + player.getHealth() + "/100");
                    } else {
                        System.out.println("\nYou don't have any Potions!");
                    }
                    turnConsumed = true;
                } else if (itemChoice.equals("2")) {
                    if (player.getInventory().useApple(player)) {
                        System.out.println("\nSuccess: You ate an Apple!");
                        System.out.println("Health restored to: " + player.getHealth() + "/100");
                    } else {
                        System.out.println("\nYou don't have any Apples!");
                    }
                    turnConsumed = true;
                }
            } else if (choice.equals("3")) {
                System.out.println("\nYou managed to escape safely!");
                Utils.pause();
                return 1;
            } else {
                System.out.println("\nInvalid choice! Please choose 1, 2, or 3.");
                Utils.pause();
                continue;
            }

            if (turnConsumed) {
                Utils.pause();

                if (enemy.isAlive()) {
                    System.out.println("\nThe " + enemy.getName() + " attacks for " + enemy.attack + " damage!");
                    player.takeDamage(enemy.attack);

                    if (!player.isAlive()) {
                        return -1;
                    }
                    Utils.pause();
                } else {
                    return 0;
                }
            }
        }
        return -1;
    }
    // ================= UI =================

    private static String healthBar(int hp, int max) {
        int filled = (int) (((double) hp / max) * 20);
        String bar = "";
        for (int i = 0; i < 20; i++) {
            bar += (i < filled) ? "#" : "-";
        }
        return "[" + bar + "] " + hp + "/" + max;
    }

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
}