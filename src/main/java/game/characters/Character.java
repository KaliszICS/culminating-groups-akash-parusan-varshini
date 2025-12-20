package game.characters;
// Abstract classes and interfaces
// Abstract base class that defines shared behavior for Player and Enemy.
public abstract class Character {

    protected int health;
    protected int attack;
    protected int maxHealth;

    // Each subclass (Player, Enemy) MUST define how it takes a turn
    public abstract void takeTurn(Player player);

    public boolean isAlive() {
        return health > 0;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void takeDamage(int dmg) {
        health -= dmg;
        if (health < 0) {
            health = 0;
        }
    }

    public void heal(int amount) {
        health += amount;
        if (health > maxHealth) {
            health = maxHealth;
        }
    }

    public int getAttack() {
        return attack;
    }

}
