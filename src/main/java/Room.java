import java.util.HashMap;

public class Room {
    private String name;
    private String description;
    private String[] art;
    private Enemy enemy;
    private HashMap<String, Integer> exits;

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        this.art = new String[0];
        this.exits = new HashMap<>();
    }

    public void setArt(String[] art) {
        this.art = art;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

    public boolean hasEnemy() {
        return enemy != null && enemy.isAlive();
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public void defeatEnemy() {
        this.enemy = null;
    }

    public void addExit(String direction, int roomIndex) {
        exits.put(direction.toLowerCase(), roomIndex);
    }

    public int getExit(String direction) {
        return exits.getOrDefault(direction.toLowerCase(), -1);
    }

    public String getName() {
        return name;
    }

    public void display() {
        // 1. Display the Room Header
        System.out.println("========================================");
        System.out.println(name.toUpperCase());
        System.out.println("========================================");

        // 2. Display Description and ASCII Art
        System.out.println(description + "\n");
        for (String line : art) {
            System.out.println(line);
        }

        // 3. Display the Exits (using the keys from the HashMap)
        System.out.println("\nExits: " + exits.keySet());

        // 4. Clean Command List (Removed <dir> and restored original style)
        System.out.print("Commands: go <up|down|left|right>, attack, inventory, help, quit");

        // 5. Special condition for Chest Room commands
        if (name.equals("Abandoned Storage")) {
            System.out.print(", open chest");
        }

        System.out.println("\n========================================");
    }
}