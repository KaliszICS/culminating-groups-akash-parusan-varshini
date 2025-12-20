public class Player extends Character implements Saveable {
    private Inventory inventory;
    private int x = 1; 
    private int y = 1;

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