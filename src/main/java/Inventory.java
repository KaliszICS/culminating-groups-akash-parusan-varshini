import java.util.ArrayList;

public class Inventory {

    private ArrayList<String> items;

    public Inventory() {
        items = new ArrayList<>();
        items.add("Map");
        items.add("Potion"); // Player starts with 1 Potion
        // No Apple here; it must be found in the chest!
    }

    // This method removes an item by name after it's been used
    private void removeItem(String target) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).equalsIgnoreCase(target)) {
                items.remove(i);
                break; // Stop after removing one instance
            }
        }
    }

    public boolean usePotion(Player player) {
        if (contains("Potion")) {
            removeItem("Potion");
            player.heal(25);
            return true;
        }
        return false;
    }

    public boolean useApple(Player player) {
        if (contains("Apple")) {
            removeItem("Apple");
            player.heal(10);
            return true;
        }
        return false;
    }

    public boolean contains(String target) {
        for (String item : items) {
            if (item.equalsIgnoreCase(target)) {
                return true;
            }
        }
        return false;
    }

    public void addItem(String item) {
        items.add(item);
    }

    public void display() {
        int potions = 0;
        int apples = 0;
        boolean hasMap = false;

        // Count items for explicit display
        for (String item : items) {
            if (item.equalsIgnoreCase("Potion"))
                potions++;
            else if (item.equalsIgnoreCase("Apple"))
                apples++;
            else if (item.equalsIgnoreCase("Map"))
                hasMap = true;
        }

        System.out.println("\n--- INVENTORY ---");
        if (hasMap) {
            System.out.println("- [MAP]");
        }
        System.out.println("- [" + potions + "] Potion(s)");
        System.out.println("- [" + apples + "] Apple(s)");
        System.out.println("-----------------");
    }

    public boolean hasPotion() {
        for (String item : items) {
            if (item.equalsIgnoreCase("Potion"))
                return true;
        }
        return false;
    }

    public void removePotion() {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).equalsIgnoreCase("Potion")) {
                items.remove(i);
                return;
            }
        }
    }

    public void addMultipleItems(String itemName, int count) {
        for (int i = 0; i < count; i++) {
            items.add(itemName);
        }
    }
}