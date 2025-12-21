/**
 * Main controller class for GridQuest RPG - a text-based adventure game.
 * Manages the game loop, world navigation, combat system, inventory management,
 * and special events. This class serves as the central coordinator for all
 * game systems and user interactions.
 * 
 * Implements various ICS4U concepts including:
 * - ArrayLists for dynamic collections
 * - Searching (sequential search in inventory)
 * - Sorting (selection sort for inventory)
 * - File I/O for art and game state
 * - Recursion in gambling mini-game
 * - 2D array simulation through world map
 * 
 * @author [Parusan]
 * @author [Akash]
 * @author [Varshini]
 * @version 1.0
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class GridQuestRPG {
    
    /**
     * Flag indicating whether the final boss (Sentinel) has been defeated.
     * Controls access to certain game areas and events.
     */
    private static boolean sentinelDefeated = false;
    
    /**
     * Shared Scanner instance for reading user input throughout the game.
     */
    private static Scanner input = new Scanner(System.in);
    
    /**
     * ArrayList representing the game world, where each Room contains
     * descriptions, exits, enemies, and ASCII art.
     */
    private static ArrayList<Room> map = new ArrayList<>();
    
    /**
     * The player character controlled by the user.
     */
    private static Player player;
    
    /**
     * Index of the current room the player is in within the map ArrayList.
     */
    private static int currentRoom = 0;
    
    /**
     * Collection of riddles used for puzzle challenges in the game.
     */
    private static ArrayList<Riddle> riddles = new ArrayList<>();
    
    /**
     * Flag indicating whether the storage room chest has been opened.
     */
    private static boolean chestOpened = false;
    
    /**
     * World map display component showing player location and game layout.
     */
    private static WorldMap worldMap = new WorldMap();

    /**
     * Main entry point for the GridQuest RPG game.
     * Initializes game systems, displays start menu, and begins game loop.
     * 
     * @param args command line arguments (not used in this game)
     */
    public static void main(String[] args) {
        player = new Player();
        showStartMenu();
        playIntro();
        createWorld();
        loadRiddles();
        gameLoop();
    }

    /**
     * Displays the main start menu with options to begin game, view help,
     * or quit. Runs in a loop until the user selects a valid option.
     */
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

    /**
     * Displays the help menu with available commands and game instructions.
     */
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

    /**
     * Plays the introductory scene of the game, setting up the story
     * and giving the player their first item (map).
     */
    private static void playIntro() {
        Utils.clear();
        System.out.println("A hooded wizard appears.");
        System.out.println("\"It's dangerous to go alone! Take this.\"");
        System.out.println("You received a MAP.");
        Utils.pause();
    }

    // ================= WORLD =================

    /**
     * Creates and initializes the game world by constructing Room objects
     * with descriptions, exits, enemies, and loading ASCII art from files.
     * Demonstrates object-oriented design and file I/O concepts.
     */
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

    /**
     * Loads a collection of riddles into the game for puzzle challenges.
     * Each riddle consists of a question and answer pair.
     */
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
    
    /**
     * Main game loop that processes player commands and manages game state.
     * Handles navigation, combat, inventory, saving/loading, and special events.
     * Runs continuously until the player quits or the game ends.
     */
    private static void gameLoop() {
        boolean redraw = true;

        while (true) {
            Room room = map.get(currentRoom);

            // BOSS TRIGGER: Forgotten Road
            if (room.getName().equals("The Forgotten Road") && room.hasEnemy()) {
                int result = battle(player, room.getEnemy());

                if (result == -1) {
                    System.out.println("GAME OVER.");
                    return;
                }

                if (result == 0) {
                    room.defeatEnemy();
                    System.out.println("\nThe road falls silent. You may proceed.");
                    Utils.pause();
                    redraw = true;
                }
            }

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

            // 3. INVENTORY - Demonstrates searching and sorting
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

            // 4. OPEN CHEST - Puzzle challenge using riddles
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

            // 5. ATTACK - Initiates combat with room enemy
            if (cmd.equals("attack")) {
                if (room.hasEnemy()) {
                    Enemy enemy = room.getEnemy();
                    int result = battle(player, enemy);

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

            // 6. SAVE/LOAD - File I/O demonstration
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

            // 7. MAP - World visualization
            if (cmd.equals("map")) {
                Utils.clear();
                worldMap.display(currentRoom);
                Utils.pause();
                redraw = true;
                continue;
            }

            // 8. GAMBLE - Mini-game with recursion
            if (cmd.equals("gamble")) {
                if (room.getName().contains("Arcade") || room.getName().contains("Tavern")) {
                    playGamble();
                } else {
                    System.out.println("No gambling equipment here.");
                }
                redraw = true;
                continue;
            }

            // 9. MOVEMENT - World navigation
            if (cmd.startsWith("go ")) {
                String dir = cmd.substring(3).trim();

                if (room.getName().equals("The Forgotten Road") && dir.equals("up")) {

                    if (sentinelDefeated) {
                        System.out.println("\nThe Sentinel has been defeated.");
                        System.out.println("Only silence remains beyond the fog.");
                        Utils.pause();
                        redraw = true;
                        continue;
                    }

                    sentinelBossBattle(player);
                    redraw = true;
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

    // ================= BATTLE =================
    
    /**
     * Manages turn-based combat between the player and an enemy.
     * Demonstrates game state management and user interaction in combat scenarios.
     * 
     * @param player the player character
     * @param enemy the enemy to fight
     * @return -1 if player died, 0 if enemy defeated, 1 if player ran away
     */
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
                        Utils.pause(); 
                    } else {
                        System.out.println("\nYou don't have any Potions!");
                        Utils.pause();
                        continue;
                    }
                } else if (itemChoice.equals("2")) {
                    if (player.getInventory().useApple(player)) {
                        System.out.println("\nSuccess: You ate an Apple!");
                        System.out.println("Health restored to: " + player.getHealth() + "/100");
                        Utils.pause();
                    } else {
                        System.out.println("\nYou don't have any Apples!");
                        Utils.pause();
                        continue;
                    }
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

    /**
     * Creates a visual health bar representation for display in combat.
     * 
     * @param hp current health points
     * @param max maximum health points
     * @return string representation of health bar (e.g., "[#####-----] 50/100")
     */
    private static String healthBar(int hp, int max) {
        int filled = (int) (((double) hp / max) * 20);
        String bar = "";
        for (int i = 0; i < 20; i++) {
            bar += (i < filled) ? "#" : "-";
        }
        return "[" + bar + "] " + hp + "/" + max;
    }

    /**
     * Loads ASCII art from a text file for room displays.
     * Demonstrates file I/O and error handling.
     * 
     * @param fileName name of the file containing ASCII art
     * @return array of strings representing the art lines
     */
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

    /**
     * Initiates the gambling mini-game in the tavern.
     * Checks if player has required items before starting.
     */
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

    /**
     * Recursive gambling game where players bet potions on card predictions.
     * Demonstrates recursion concept from ICS4U curriculum.
     * 
     * @param wager current number of potions being wagered
     */
    private static void playGamble(int wager) {
        int cur = (int) (Math.random() * 10) + 1;
        int next = (int) (Math.random() * 10) + 1;

        System.out.println("\nDealer shows: [" + cur + "]. Next card Higher or Lower? (h/l)");
        String choice = input.nextLine().toLowerCase().trim();

        if ((choice.equals("h") && next >= cur) || (choice.equals("l") && next <= cur)) {
            System.out.println("Win! Card was " + next + ". Total: " + (wager * 2) + " potions.");
            System.out.println("Double or nothing? (y/n)");

            if (input.nextLine().equalsIgnoreCase("y")) {
                playGamble(wager * 2); // Recursive call
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

    /**
     * Final boss battle sequence against The Sentinel.
     * Features enhanced combat mechanics, story elements, and visual effects.
     * 
     * @param player the player character entering the boss battle
     */
    public static void sentinelBossBattle(Player player) {
        java.util.Random rand = new java.util.Random();

        Utils.clear();

        String[] introArt = loadArtFromFile("sentinel_road_intro.txt");
        for (String line : introArt) {
            System.out.println(line);
        }
        Utils.pause();

        try {
            System.out.println("The road narrows. The air turns heavy...");
            Thread.sleep(1000);
            System.out.println("Iron footsteps echo ahead.");
            Thread.sleep(1000);
            System.out.println("A towering figure emerges from the fog.");
            Thread.sleep(1000);
            System.out.println("Behind it, the silhouette of a ruined castle looms, fused with iron and stone.");
            Thread.sleep(1000);
            System.out.println("\nTHE SENTINEL: \"Glass breaks. Iron remains.\"");
            Thread.sleep(1000);
            System.out.println("\n[1] Draw your weapon");
            System.out.println("[2] Retreat to the road");
            System.out.print("> ");

            String choice = input.nextLine().trim();

            if (choice.equals("2")) {
                System.out.println("\nYou step back as the fog swallows the figure once more...");
                Utils.pause();
                return;
            }
        } catch (InterruptedException e) {
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
            String choice = input.nextLine();

            if (choice.equals("1")) {
                int pDamage = rand.nextInt(16) + 15;
                bossHP -= pDamage;
                System.out.println("\nYou strike the Sentinel for " + pDamage + "!");
            } else if (choice.equals("2")) {
                if (player.getHealth() >= 100) {
                    System.out.println("\nAlready at max health (100/100)!");
                    Utils.pause();
                    continue;
                }

                player.getInventory().display();
                System.out.println("\n[1] Potion  [2] Apple  [3] Back");
                System.out.print("> ");
                String itemChoice = input.nextLine().trim();
                if (itemChoice.equals("3")) {
                    continue;
                }

                if (itemChoice.equals("1")) {
                    if (player.getInventory().usePotion(player)) {
                        System.out.println("\nSuccess: You drank a Potion!");
                        System.out.println("Health restored to: " + player.getHealth() + "/100");
                        Utils.pause();
                    } else {
                        System.out.println("\nYou don't have any Potions!");
                        Utils.pause();
                        continue;
                    }
                } else if (itemChoice.equals("2")) {
                    if (player.getInventory().useApple(player)) {
                        System.out.println("\nSuccess: You ate an Apple!");
                        System.out.println("Health restored to: " + player.getHealth() + "/100");
                        Utils.pause();
                    } else {
                        System.out.println("\nYou don't have any Apples!");
                        Utils.pause();
                        continue;
                    }
                }

            }

            if (bossHP > 0) {
                try {
                    Thread.sleep(1000);

                    int sDamage;

                    if (bossHP <= 50) {

                        if (!armorCracked) {
                            System.out.println("\nCracks form in the Sentinel's armor...");
                            armorCracked = true;
                        }

                        sDamage = rand.nextInt(10) + 10; // 10–19 dmg (weakened)
                    } else {
                        sDamage = rand.nextInt(16) + 10; // 10–25 dmg (normal)
                    }

                    player.takeDamage(sDamage);
                    System.out.println("\nThe Sentinel smashes you for " + sDamage + "!");
                    Thread.sleep(1000);
                    System.out.println("\n[ Press ENTER to continue ]");
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

            sentinelDefeated = true; 
        } else {
            System.out.println("\nYOU HAVE FALLEN.");
            System.exit(0);
        }

    }

    /**
     * Checks for special events when entering certain areas.
     * Currently handles the Sentinel boss encounter.
     * 
     * @param player the player character
     * @param scanner input scanner for user choices
     * @return true if a special event was processed, false otherwise
     */
    public static boolean checkSpecialEvents(Player player, Scanner scanner) {
        Utils.clear();
        System.out.println("\n--- THE IRON GATES ---");
        System.out.println("The Sentinel looms over the path to The Forgotten Road.");
        System.out.println("\n[1] Draw Weapon (Fight)");
        System.out.println("[2] Retreat (Go Back)");

        System.out.print("> ");
        String choice = scanner.nextLine();

        if (choice.equals("1")) {
            bossBattle(player);
            sentinelDefeated = true;
            currentRoom = 4; 
            player.setX(0);
            player.setY(1);
            System.out.println("\nThe Sentinel shatters. You proceed to The Forgotten Road.");
            Utils.pause();
            return true;
        } else {
            System.out.println("\nYou retreat to the center of the Town Square.");
            Utils.pause();
            return true;
        }
    }

    /**
     * Original boss battle implementation (simplified version).
     * Kept for compatibility with special events.
     * 
     * @param player the player character
     */
    public static void bossBattle(Player player) {
        java.util.Random rand = new java.util.Random();

        Utils.clear();

        try {
            System.out.println("The iron gates groan as they loom over you...");
            Thread.sleep(1000);
            System.out.println("THE SENTINEL: 'Glass breaks. Iron remains.'");
            Thread.sleep(1000);
            System.out.println("\n--- BOSS CHALLENGE: THE SENTINEL ---");
            Thread.sleep(1000);
            System.out.println("[ Press ENTER to Draw Your Weapon ]");
            input.nextLine();
        } catch (InterruptedException e) {
        }

        int bossHP = 150;
        boolean armorCracked = false;

        while (bossHP > 0 && player.getHealth() > 0) {
            Utils.clear();
            System.out.println("=== BOSS: THE SENTINEL ===");
            System.out.println("SENTINEL HP: " + bossHP);
            System.out.println("YOUR HP    : " + player.getHealth());
            System.out.println("==========================");

            System.out.print("\nAction (1: Attack, 2: Potion): ");
            String move = input.nextLine();

            if (move.equals("1")) {
                int pDam = rand.nextInt(15) + 10;
                bossHP -= pDam;
                System.out.println("\n>> You strike for " + pDam + "!");
            } else if (move.equals("2")) {
                int healAmount = 35;
                player.setHealth(player.getHealth() + healAmount);
                System.out.println("\n>> You chug a potion! (+ " + healAmount + " HP)");
            }

            if (bossHP > 0) {
                try {
                    Thread.sleep(1000); 
                    int bDam = rand.nextInt(12) + 15;
                    player.setHealth(player.getHealth() - bDam);
                    System.out.println(">> The Sentinel crushes you for " + bDam + "!");
                    Thread.sleep(1000); 
                    System.out.println("\n[ Press Enter to continue ]");
                    input.nextLine();
                } catch (InterruptedException e) {
                }
            }
        }

        if (player.getHealth() > 0) {
            System.out.println("\nTHE SENTINEL: 'You... are iron.'");
            System.out.println("The giant shatters into cold dust. The path is clear.");
            Utils.pause();
        } else {
            System.out.println("\nYOU HAVE PERISHED.");
            System.exit(0);
        }
    }
}