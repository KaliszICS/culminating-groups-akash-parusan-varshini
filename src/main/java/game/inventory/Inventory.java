package game.inventory;
// Classes, Sorting (Selection), Searching (Sequential)
// Manages items and implements selection sort and sequential search.
import java.util.ArrayList;

public class Inventory implements Saveable {

    private ArrayList<String> items;
    private ArrayList<String> order;

    public Inventory() {
        items = new ArrayList<>();
        order = new ArrayList<>();

        items.add("Potion");

        order.add("Potion");
        order.add("Apple");
    }

    private void removeItem(String target) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).equalsIgnoreCase(target)) {
                items.remove(i);
                break;
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

    public boolean hasPotion() {
        return contains("Potion");
    }

    public void removePotion() {
        removeItem("Potion");
    }

    public void addMultipleItems(String itemName, int count) {
        for (int i = 0; i < count; i++) {
            items.add(itemName);
        }
    }

    public ArrayList<String> getItems() {
        return items;
    }

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

    public int sequentialSearch(String target) {
        for (int i = 0; i < order.size(); i++) {
            if (order.get(i).equalsIgnoreCase(target)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String saveData() {
        StringBuilder sb = new StringBuilder();
        for (String item : items) {
            sb.append(item).append("\n");
        }
        return sb.toString();
    }

}