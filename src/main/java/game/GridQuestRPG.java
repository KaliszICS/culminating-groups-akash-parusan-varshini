package game;

import java.util.ArrayList;
import java.util.Scanner;

import game.characters.Player;
import game.characters.Enemy;
import game.inventory.InventoryMenu;
import game.world.Room;
import game.world.WorldBuilder;
import game.world.WorldMap;
import game.ui.ChestHandler;
import game.ui.GameMenus;
import game.ui.WizardHandler;
import game.util.Utils;
import game.combat.BattleSystem;
import game.combat.SentinelBattle;
import game.minigames.GambleGame;
import game.riddles.Riddle;
import game.riddles.RiddleBank;
import game.save.SaveManager;

/**
 * The GridQuestRPG class is the main driver of the game.
 * It controls overall program flow, initializes the world,
 * processes player input, and coordinates interactions
 * between all major systems.
 *
 * @author Akash K.
 * @author Parusan P.
 * @author Varshini B.
 * @version 1.0
 */
// [Classes]
public class GridQuestRPG {

    private static boolean sentinelDefeated = false;
    private static Scanner input = new Scanner(System.in);
    private static ArrayList<Room> map = new ArrayList<>();
    private static Player player;
    private static int currentRoom = 0;
    private static boolean chestOpened = false;
    private static ArrayList<Riddle> riddles;
    private static WorldMap worldMap = new WorldMap(); // [2-D arrays]

    /**
     * Program entry point.
     * Initializes the player, world, riddles, and starts the game loop.
     *
     * @param args command-line arguments (unused)
     */
    // [Classes]
    public static void main(String[] args) {
        player = new Player();
        GameMenus.showStartMenu(input);
        GameMenus.playIntro();
        WorldBuilder.createWorld(map);
        riddles = RiddleBank.load();
        gameLoop();
    }

    /**
     * Main game loop that continuously processes user input
     * and updates game state.
     */
    // [Classes]
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

            // Quit game
            if (cmd.equals("quit"))
                return;

            // Help menu
            if (cmd.equals("help")) {
                GameMenus.showHelpMenu();
                redraw = true;
                continue;
            }

            // Inventory menu
            if (cmd.equals("inventory") || cmd.equals("i") || cmd.equals("items")) {
                InventoryMenu.open(player, input);
                redraw = true;
                continue;
            }

            // Chest interaction
            if (cmd.equals("open") || cmd.equals("open chest") || cmd.equals("chest")) {
                chestOpened = ChestHandler.tryOpen(room, player, riddles, input, chestOpened);
                redraw = true;
                continue;
            }

            // Combat system
            if (cmd.equals("attack")) {
                if (room.hasEnemy()) {
                    Enemy enemy = room.getEnemy();
                    int result = BattleSystem.fight(player, enemy, input); // [Polymorphism]

                    if (result == 1) {
                        if (currentRoom == 0) {
                            currentRoom = 1;
                            player.setX(1);
                            player.setY(1);
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
                        System.out.println("\nVictory! The path is clear.");
                        Utils.pause();

                        if (currentRoom == 0) {
                            currentRoom = 1;
                            player.setX(1);
                            player.setY(1);
                        }

                        redraw = true;
                        continue;
                    }

                } else {
                    System.out.println("Nothing to fight here.");
                    Utils.pause();
                    redraw = true;
                }
                continue;
            }

            // Save game
            if (cmd.equals("save")) {
                SaveManager.saveGame(currentRoom, chestOpened, player, player.getInventory()); // [File I/O]
                redraw = true;
                continue;
            }

            // Load game
            if (cmd.equals("load")) {
                int[] data = SaveManager.loadGame(player); // [File I/O]
                if (data != null) {
                    currentRoom = data[0];
                    chestOpened = data[1] == 1;
                }
                redraw = true;
                continue;
            }

            // World map
            if (cmd.equals("map")) {
                Utils.clear();
                worldMap.display(currentRoom); // [2-D arrays]
                Utils.pause();
                redraw = true;
                continue;
            }

            // Gambling mini-game
            if (cmd.equals("gamble")) {
                if (room.getName().contains("Tavern")) {
                    GambleGame.play(player, input); // [Recursion]
                } else {
                    System.out.println("No gambling equipment here.");
                    Utils.pause();
                    redraw = true;
                }
                redraw = true;
                continue;
            }

            // Wizard Interaction
            if (cmd.equals("talk") && room.getName().equals("The Arcane Study")) {
                WizardHandler.interact(player, input);
                redraw = true;
                continue;
            }

            // Movement
            if (cmd.startsWith("go ")) {
                String dir = cmd.substring(3).trim();

                if (room.getName().equals("The Forgotten Road") && dir.equals("up")) {
                    if (!sentinelDefeated) {
                        sentinelDefeated = SentinelBattle.fight(player, input);
                    } else {
                        System.out.println("\nOnly silence remains beyond the fog.");
                        Utils.pause();
                    }
                    redraw = true;
                    continue;
                }

                if (room.hasEnemy()) {
                    System.out.println("The enemy blocks the way!");
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
            Utils.pause();
            redraw = true;
        }
    }

    /**
     * Loads ASCII art from a text file.
     *
     * @param fileName the name of the file to load
     * @return an array of strings representing the art
     */
    // [File I/O]
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