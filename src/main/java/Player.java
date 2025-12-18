public class Player extends Character implements Saveable {
    private int gold = 20;
    private Inventory inventory;

    // Overloaded constructors
    public Player() {
        this(100, 15);
    }

    public Player(int health, int attack) {
        this.health = health;
        this.attack = attack;
        inventory = new Inventory();
    }

    @Override
    public void takeTurn() {
        // handled by user input
    }

    public Inventory getInventory() {
        return inventory;
    }

    @Override
    public String saveData() {
        return health + "," + attack;
    }
}
