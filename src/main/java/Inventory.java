/**
 * Manages the player's inventory system for the GridQuest RPG.
 * Handles item storage, usage, display, searching, and sorting.
 * Demonstrates key ICS4U concepts including ArrayLists, searching algorithms
 * (sequential search), and sorting algorithms (selection sort).
 * 
 * The inventory maintains two separate lists:
 * - `items`: Actual items the player possesses (can have duplicates)
 * - `order`: Reference order for displaying items (no duplicates)
 * 
 * @author [Parusan]
 * @author [Akash]
 * @author [Varshini]
 * @version 1.0
 */
import java.util.ArrayList;

public class Inventory {

    /**
     * List of items currently in the player's inventory.
     * May contain duplicate items (e.g., multiple Potions).
     */
    private ArrayList<String> items;
    
    /**
     * Reference list defining the display order and categories of items.
     * Contains unique item names in a specific order for organized display.
     */
    private ArrayList<String> order;

    /**
     * Constructs a new Inventory with default starting items.
     * Initializes the player with one Potion and sets up the display order.
     */
    public Inventory() {
        items = new ArrayList<>();
        order = new ArrayList<>();
        
        items.add("Potion");
        
        order.add("Potion");
        order.add("Apple");
    }

    /**
     * Removes a single instance of the specified item from the inventory.
     * Uses case-insensitive comparison and removes only the first match found.
     * 
     * @param target the name of the item to remove
     */
    private void removeItem(String target) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).equalsIgnoreCase(target)) {
                items.remove(i);
                break;
            }
        }
    }

    /**
     * Uses a Potion item to heal the player.
     * Removes one Potion from inventory and restores 25 health points.
     * 
     * @param player the player to heal
     * @return true if a Potion was available and used, false otherwise
     */
    public boolean usePotion(Player player) {
        if (contains("Potion")) {
            removeItem("Potion");
            player.heal(25);
            return true;
        }
        return false;
    }

    /**
     * Uses an Apple item to heal the player.
     * Removes one Apple from inventory and restores 10 health points.
     * 
     * @param player the player to heal
     * @return true if an Apple was available and used, false otherwise
     */
    public boolean useApple(Player player) {
        if (contains("Apple")) {
            removeItem("Apple");
            player.heal(10);
            return true;
        }
        return false;
    }

    /**
     * Checks if the inventory contains at least one of the specified item.
     * Uses case-insensitive comparison.
     * 
     * @param target the name of the item to search for
     * @return true if the item is found, false otherwise
     */
    public boolean contains(String target) {
        for (String item : items) {
            if (item.equalsIgnoreCase(target)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a single item to the inventory.
     * 
     * @param item the name of the item to add
     */
    public void addItem(String item) {
        items.add(item);
    }

    /**
     * Displays the inventory contents in an organized format.
     * Shows item counts based on the predefined display order.
     * Example output:
     * --- INVENTORY ---
     * - [2] Potion(s)
     * - [1] Apple(s)
     * -----------------
     */
    public void display() {
        System.out.println("\n--- INVENTORY ---");
        for (String name : order) {
            int count = 0;
            for (String item : items) {
                if (item.equalsIgnoreCase(name)) {
                    count++;
                }
            }
            System.out.println("- [" + count + "] " + name + "(s)");
        }
        System.out.println("-----------------");
    }

    /**
     * Checks if the inventory contains at least one Potion.
     * Convenience method equivalent to contains("Potion").
     * 
     * @return true if at least one Potion is available, false otherwise
     */
    public boolean hasPotion() {
        return contains("Potion");
    }

    /**
     * Removes one Potion from the inventory if available.
     * Does nothing if no Potions are present.
     */
    public void removePotion() {
        removeItem("Potion");
    }

    /**
     * Adds multiple copies of the same item to the inventory.
     * 
     * @param itemName the name of the item to add
     * @param count the number of copies to add
     */
    public void addMultipleItems(String itemName, int count) {
        for (int i = 0; i < count; i++) {
            items.add(itemName);
        }
    }

    /**
     * Returns the complete list of items in the inventory.
     * Warning: This returns the actual ArrayList, not a copy.
     * 
     * @return the items ArrayList
     */
    public ArrayList<String> getItems() {
        return items;
    }

    /**
     * Sorts the display order alphabetically using selection sort algorithm.
     * Demonstrates the selection sort concept from ICS4U curriculum.
     * The actual items list is not sorted, only the display order.
     */
    public void selectionSort() {
        for (int i = 0; i < order.size() - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < order.size(); j++) {
                if (order.get(j).compareToIgnoreCase(order.get(minIdx)) < 0) {
                    minIdx = j;
                }
            }
            String temp = order.get(minIdx);
            order.set(minIdx, order.get(i));
            order.set(i, temp);
        }
    }

    /**
     * Searches for an item in the display order using sequential search.
     * Demonstrates the sequential search algorithm from ICS4U curriculum.
     * 
     * @param target the name of the item to find
     * @return the index of the item in the order list, or -1 if not found
     */
    public int sequentialSearch(String target) {
        for (int i = 0; i < order.size(); i++) {
            if (order.get(i).equalsIgnoreCase(target)) {
                return i;
            }
        }
        return -1;
    }
}