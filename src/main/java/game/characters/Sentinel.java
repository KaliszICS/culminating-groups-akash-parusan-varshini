package game.characters;

import java.util.Random;

/**
 * The Sentinel class represents the boss enemy in the game.
 * It is a specialized Enemy with unique combat behavior that
 * changes based on its remaining health.
 *
 * The Sentinel becomes weaker once its armor cracks, demonstrating
 * overridden behavior compared to regular enemies.
 *
 * @author Akash K.
 * @author Parusan P.
 * @author Varshini B.
 * @version 1.0
 */
// [Classes]
// [Inheritance]
// [Overriding]
// [Polymorphism]
public class Sentinel extends Enemy {

    /** Tracks whether the Sentinel's armor has cracked */
    private boolean armorCracked = false;

    /** Random number generator for damage variation */
    private Random rand = new Random();

    /**
     * Constructs a Sentinel with predefined boss stats.
     */
    // [Classes]
    public Sentinel() {
        super("Sentinel", 150, 25);
    }

    /**
     * Executes the Sentinel's turn during combat.
     * Damage output changes when health drops below a threshold.
     *
     * @param player the player being attacked
     */
    // [Overriding]
    // [Polymorphism]
    @Override
    public void takeTurn(Player player) {
        int damage;

        if (health <= 50) {
            if (!armorCracked) {
                System.out.println("\nCracks form in the Sentinel's armor...");
                armorCracked = true;
            }
            damage = rand.nextInt(11) + 10; // 10–19 when weakened
        } else {
            damage = rand.nextInt(16) + 15; // 15–30 when armored
        }

        player.takeDamage(damage);
        System.out.println("\nThe Sentinel smashes you for " + damage + "!");
    }
}