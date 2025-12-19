import java.io.*;
import java.util.Scanner;

public class SaveManager {
    private static final String SAVE_FILE = "savegame.txt";

    public static void saveGame(int currentRoom, int health, Inventory inventory) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(SAVE_FILE))) {
            writer.println(currentRoom);
            writer.println(health);
            for (String item : inventory.getItems()) {
                writer.println(item);
            }
            System.out.println("\nGame saved successfully!");
        } catch (IOException e) {
            System.out.println("\nError saving game.");
        }
        Utils.pause();
    }

    public static int[] loadGame(Inventory inventory) {
        File file = new File(SAVE_FILE);
        if (!file.exists()) {
            System.out.println("\nNo save file found.");
            Utils.pause();
            return null;
        }

        try (Scanner reader = new Scanner(file)) {
            int room = Integer.parseInt(reader.nextLine());
            int hp = Integer.parseInt(reader.nextLine());
            
            inventory.getItems().clear();
            while (reader.hasNextLine()) {
                inventory.addItem(reader.nextLine());
            }
            
            System.out.println("\nGame loaded successfully!");
            Utils.pause();
            return new int[]{room, hp};
        } catch (Exception e) {
            System.out.println("\nError loading save file.");
            Utils.pause();
            return null;
        }
    }
}