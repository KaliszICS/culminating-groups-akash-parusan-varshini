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

public class GridQuestRPG { // Classes

    private static boolean sentinelDefeated = false;
    private static Scanner input = new Scanner(System.in);
    private static ArrayList<Room> map = new ArrayList<>();
    // Classes

    private static Player player;
    private static int currentRoom = 0;
    private static boolean chestOpened = false;
    private static ArrayList<Riddle> riddles;
    private static WorldMap worldMap = new WorldMap();
    // 2-D arrays

    /**
     * Launches the game.
     * Initializes the player, displays the start menu and introduction,
     * builds the game world, loads riddles, and enters the main game loop.
     */

    // [Classes]
    public static void main(String[] args) { // Classes
        player = new Player();
        GameMenus.showStartMenu(input);
        GameMenus.playIntro();
        WorldBuilder.createWorld(map);
        riddles = RiddleBank.load();
        gameLoop();
    }

    /**
     * Controls the main runtime loop of the game.
     * Continuously displays the current room, processes player commands,
     * handles movement, combat, interactions, saving/loading, and updates
     * the game state until the player quits.
     */
    private static void gameLoop() { // Classes
        boolean redraw = true;

        while (true) {
            Room room = map.get(currentRoom);
            // Classes

            if (redraw) {
                Utils.clear();
                room.display();
                redraw = false;
            }

            System.out.print("> ");
            String cmd = input.nextLine().toLowerCase().trim();

            if (cmd.equals("quit"))
                return;

            if (cmd.equals("help")) {
                GameMenus.showHelpMenu();
                redraw = true;
                continue;
            }

            if (cmd.equals("inventory") || cmd.equals("i") || cmd.equals("items")) {
                InventoryMenu.open(player, input);
                // Abstract classes and interfaces
                redraw = true;
                continue;
            }

            if (cmd.equals("open") || cmd.equals("open chest") || cmd.equals("chest")) {
                chestOpened = ChestHandler.tryOpen(room, player, riddles, input, chestOpened);
                // Searching
                redraw = true;
                continue;
            }

            if (cmd.equals("attack")) {
                if (room.hasEnemy()) {
                    Enemy enemy = room.getEnemy();
                    int result = BattleSystem.fight(player, enemy, input);
                    // Polymorphism

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

            if (cmd.equals("save")) {
                SaveManager.saveGame(currentRoom, chestOpened, player, player.getInventory());
                // File I/O
                redraw = true;
                continue;
            }

            if (cmd.equals("load")) {
                int[] data = SaveManager.loadGame(player);
                // File I/O
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
                // 2-D arrays
                Utils.pause();
                redraw = true;
                continue;
            }

            if (cmd.equals("gamble")) {
                if (room.getName().contains("Tavern")) {
                    GambleGame.play(player, input);
                    // Recursion
                } else {
                    System.out.println("No gambling equipment here.");
                    Utils.pause();
                    redraw = true;
                }
                redraw = true;
                continue;
            }

            if (cmd.equals("talk") && room.getName().equals("The Arcane Study")) {
                WizardHandler.interact(player, input);
                // Classes
                redraw = true;
                continue;
            }

            if (cmd.startsWith("go ")) {
                String dir = cmd.substring(3).trim();

                if (room.getName().equals("The Forgotten Road") && dir.equals("up")) {
                    if (!sentinelDefeated) {
                        sentinelDefeated = SentinelBattle.fight(player, input);
                        // Polymorphism
                    } else {
                        System.out.println("\nOnly silence remains beyond the fog.");
                        Utils.pause();
                    }
                    redraw = true;
                    continue;
                }

                if (room.hasEnemy()) {
                    if (room.getName().equals("The Forgotten Road") && dir.equals("down")) {
                    } else {
                        System.out.println("The enemy blocks the way!");
                        continue;
                    }
                }

                int nextRoomIndex = room.getExit(dir);
                // Searching
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
    public static String[] loadArtFromFile(String fileName) { // File I/O
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
