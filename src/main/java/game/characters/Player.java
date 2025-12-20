package game.characters;
// Inheritance, Abstract classes and interfaces
// Player extends Character and implements Saveable for file saving.
public class Player extends Character implements Saveable {
    private Inventory inventory;
    private int x = 1;
    private int y = 1;

    public Player() {
        this.maxHealth = 100;
        this.health = 100;
        this.attack = 15;
        inventory = new game.inventory.Inventory();
    }

    public Player(int health, int attack) {
        this.health = health;
        this.attack = attack;
        inventory = new Inventory();
    }

    @Override
    public void takeTurn(Player player) {
    }

    public Inventory getInventory() {
        return inventory;
    }

    @Override
    public String saveData() {
        return health + "," + attack;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
