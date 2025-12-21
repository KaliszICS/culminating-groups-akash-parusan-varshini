package game.characters;

/**
 * The Enemy class represents a hostile character in the game.
 * It extends the Character abstract class and provides a concrete
 * implementation of enemy-specific behavior.
 *
 * This class demonstrates inheritance and method overriding by
 * extending Character and overriding the takeTurn method.
 *
 * @author Akash K.
 * @author Parusan P.
 * @author Varshini B.
 * @version 1.0
 */
public class Enemy extends Character {

    /** The name of the enemy */
    private String name;

    /**
     * Constructs an Enemy with a name, health, and attack power.
     *
     * @param name the name of the enemy
     * @param health the starting and maximum health of the enemy
     * @param attack the attack damage of the enemy
     */
    public Enemy(String name, int health, int attack) {
        this.name = name;
        this.maxHealth = health;
        this.health = health;
        this.attack = attack;
    }

    /**
     * Executes the enemy's turn by attacking the player.
     * This method overrides the abstract takeTurn method
     * defined in the Character class.
     *
     * (Inheritance, Overriding, Polymorphism)
     *
     * @param player the player being attacked
     */
    @Override
    public void takeTurn(Player player) {
        player.takeDamage(attack);
        System.out.println("\nThe " + name + " strikes for " + attack + " damage!");
    }

    /**
     * Returns the enemy's name.
     *
     * @return the enemy name
     */
    public String getName() {
        return name;
    }
}
