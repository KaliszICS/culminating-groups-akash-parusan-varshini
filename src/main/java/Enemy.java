// Inheritance, Overriding
// Enemy extends Character and overrides takeTurn() behavior.
public class Enemy extends Character {
    private String name;

    public Enemy(String name, int health, int attack) {
        this.name = name;
        this.maxHealth = health;
        this.health = health;
        this.attack = attack;
    }

    @Override
    public void takeTurn(Player player) {
        player.takeDamage(attack);
        System.out.println("\nThe " + name + " strikes for " + attack + " damage!");
    }

    public String getName() {
        return name;
    }
}
