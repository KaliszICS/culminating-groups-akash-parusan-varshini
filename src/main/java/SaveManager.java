// File I/O
// Saves and loads game data using files.
import java.io.*;
import java.util.Scanner;

public class SaveManager {
    private static final String SAVE_FILE = "savegame.txt";

    public static void saveGame(int currentRoom, Saveable entity, Inventory inventory) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(SAVE_FILE))) {
            writer.println(currentRoom);
            writer.println(entity.saveData());
            for (String item : inventory.getItems()) {
                writer.println(item);
            }
            System.out.println("\nGame saved successfully!");
        } catch (IOException e) {
            System.out.println("\nError saving game.");
        }
        Utils.pause();
    }

    public static int[] loadGame(Player player) {
    File file = new File(SAVE_FILE);
    if (!file.exists()) {
        System.out.println("\nNo save file found.");
        Utils.pause();
        return null;
    }

    try (Scanner reader = new Scanner(file)) {
        int room = Integer.parseInt(reader.nextLine());
        String[] stats = reader.nextLine().split(",");
        int health = Integer.parseInt(stats[0]);
        int attack = Integer.parseInt(stats[1]);

        player.setHealth(health);
        player.getInventory().getItems().clear();
        while (reader.hasNextLine()) {
            player.getInventory().addItem(reader.nextLine());
        }

        System.out.println("\nGame loaded successfully!");
        Utils.pause();
        return new int[] { room };

    } catch (Exception e) {
        System.out.println("\nError loading save file.");
        Utils.pause();
        return null;
    }
}
}