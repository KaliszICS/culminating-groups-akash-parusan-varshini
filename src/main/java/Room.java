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
        System.out.println("========================================");
        System.out.println(name.toUpperCase());
        System.out.println("========================================");
        System.out.println(description + "\n");

        if (art != null) {
            for (String line : art) {
                System.out.println(line);
            }
        }

        System.out.println("\nExits: " + exits.keySet());

        // Check if we are in the Tavern to add the extra command
        String commands = "go <up|down|left|right>, attack, inventory, help, quit";
        if (name.equals("The Lucky Tavern")) {
            commands += ", gamble";
        } else if (name.equals("Abandoned Storage")) {
            commands += ", open chest";
        }

        System.out.println("Commands: " + commands);
        System.out.println("========================================");
    }
}