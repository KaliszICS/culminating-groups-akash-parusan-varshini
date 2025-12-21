package game.inventory;

import game.characters.Player;
import java.util.ArrayList;

/**
 * The Inventory class manages all items owned by the player.
 * It supports item usage, storage, display, sorting, searching,
 * and saving inventory data to a file.
 *
 * This class demonstrates sorting, searching, interfaces,
 * and object-oriented design.
 *
 * @author Akash K.
 * @author Parusan P.
 * @author Varshini B.
 * @version 1.0
 */
// [Classes]
// [Sorting (Selection)]
// [Searching (Sequential)]
// [Abstract classes and interfaces]
public class Inventory implements Saveable {

    /** Stores all item instances owned by the player */
    private ArrayList<String> items;

    /** Maintains the display order of item types */
    private ArrayList<String> order;

    /**
     * Constructs a new Inventory object with default items
     * and predefined item display order.
     */
    // [Classes]
    public Inventory() {
        items = new ArrayList<>();
        order = new ArrayList<>();

        items.add("Potion");

        order.add("Potion");
        order.add("Apple");
    }

    /**
     * Removes a single instance of an item from the inventory.
     *
     * @param target the item to remove
     */
    // [Searching (Sequential)]
    private void removeItem(String target) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).equalsIgnoreCase(target)) {
                items.remove(i);
                break;
            }
        }
    }

    /**
     * Uses a potion to heal the player.
     *
     * @param player the player receiving healing
     * @return true if the potion was used, false otherwise
     */
    // [Classes]
    public boolean usePotion(Player player) {
        if (contains("Potion")) {
            removeItem("Potion");
            player.heal(25);
            return true;
        }
        return false;
    }

    /**
     * Uses an apple to heal the player.
     *
     * @param player the player receiving healing
     * @return true if the apple was used, false otherwise
     */
    // [Classes]
    public boolean useApple(Player player) {
        if (contains("Apple")) {
            removeItem("Apple");
            player.heal(10);
            return true;
        }
        return false;
    }

    /**
     * Checks whether the inventory contains a specific item.
     *
     * @param target the item to search for
     * @return true if found, false otherwise
     */
    // [Searching (Sequential)]
    public boolean contains(String target) {
        for (String item : items) {
            if (item.equalsIgnoreCase(target)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds an item to the inventory.
     *
     * @param item the item to add
     */
    // [Classes]
    public void addItem(String item) {
        items.add(item);
    }

    /**
     * Displays the inventory contents and item counts.
     */
    // [Classes]
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
     * Checks whether the inventory contains at least one potion.
     *
     * @return true if a potion exists, false otherwise
     */
    // [Searching (Sequential)]
    public boolean hasPotion() {
        return contains("Potion");
    }

    /**
     * Removes a single potion from the inventory.
     */
    // [Classes]
    public void removePotion() {
        removeItem("Potion");
    }

    /**
     * Adds multiple instances of an item to the inventory.
     *
     * @param itemName the name of the item
     * @param count the number of items to add
     */
    // [Classes]
    public void addMultipleItems(String itemName, int count) {
        for (int i = 0; i < count; i++) {
            items.add(itemName);
        }
    }

    /**
     * Returns the list of all stored items.
     *
     * @return the inventory item list
     */
    // [Classes]
    public ArrayList<String> getItems() {
        return items;
    }

    /**
     * Sorts the inventory display order alphabetically
     * using the Selection Sort algorithm.
     */
    // [Sorting (Selection)]
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
     * Searches for an item in the inventory order list.
     *
     * @param target the item to search for
     * @return the index if found, or -1 if not found
     */
    // [Searching (Sequential)]
    public int sequentialSearch(String target) {
        for (int i = 0; i < order.size(); i++) {
            if (order.get(i).equalsIgnoreCase(target)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Converts the inventory contents into a saveable string format.
     *
     * @return formatted inventory data
     */
    // [File I/O]
    @Override
    public String saveData() {
        StringBuilder sb = new StringBuilder();
        for (String item : items) {
            sb.append(item).append("\n");
        }
        return sb.toString();
    }
}
