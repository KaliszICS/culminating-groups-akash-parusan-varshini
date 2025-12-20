package game;
// Classes
// Main driver class that controls program flow and coordinates all other classes.
import java.util.ArrayList;
import java.util.Scanner;

public class GridQuestRPG {
    private static boolean sentinelDefeated = false;
    private static Scanner input = new Scanner(System.in);
    private static ArrayList<Room> map = new ArrayList<>();
    private static Player player;
    private static int currentRoom = 0;
    private static boolean chestOpened = false;
    private static ArrayList<Riddle> riddles;
    private static WorldMap worldMap = new WorldMap();

    public static void main(String[] args) {
        player = new Player();
        GameMenus.showStartMenu(input);
        GameMenus.playIntro();
        WorldBuilder.createWorld(map);
        riddles = RiddleBank.load();
        gameLoop();
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
                GameMenus.showHelpMenu();
                redraw = true;
                continue;
            }

            // 3. INVENTORY
            if (cmd.equals("inventory") || cmd.equals("i") || cmd.equals("items")) {
                InventoryMenu.open(player, input);
                redraw = true;
                continue;
            }

            // 4. OPEN CHEST
            if (cmd.equals("open") || cmd.equals("open chest") || cmd.equals("chest")) {
                chestOpened = ChestHandler.tryOpen(room, player, riddles, input, chestOpened);
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
                SaveManager.saveGame(currentRoom, chestOpened, player, player.getInventory());
                redraw = true;
                continue;
            }

            if (cmd.equals("load")) {
                int[] data = SaveManager.loadGame(player);
                if (data != null) {
                    currentRoom = data[0];
                    chestOpened = data[1] == 1;
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
                    GambleGame.play(player, input);
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

                    boolean won = SentinelBattle.fight(player, input);

                    if (won) {
                        sentinelDefeated = true;
                        redraw = true;
                    }
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

    // ================= UI =================

    public static String[] loadArtFromFile(String fileName) {
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
}
