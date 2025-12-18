public class Enemy extends Character {
    private String name;

    // This constructor allows subclasses like Goblin to work
    public Enemy(String name, int health, int attack) {
        this.name = name;
        this.health = health;
        this.attack = attack;
    }

    @Override
    public void takeTurn() {
        // Handled in battle loop
    }

    public String getName() {
        return name;
    }
}