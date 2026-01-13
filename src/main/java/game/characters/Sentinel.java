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
    /**
     * Health value at or below which the Sentinel's armor is considered cracked and
     * it becomes weakened.
     */
    private static final int ARMOR_CRACK_HEALTH_THRESHOLD = 50;

    /**
     * Minimum damage the Sentinel can deal after its armor has cracked (weakened
     * state).
     */
    private static final int WEAKENED_MIN_DAMAGE = 10;

    /**
     * Maximum damage the Sentinel can deal after its armor has cracked (weakened
     * state).
     */
    private static final int WEAKENED_MAX_DAMAGE = 19;

    /**
     * Minimum damage the Sentinel can deal while its armor is intact (armored
     * state).
     */
    private static final int ARMORED_MIN_DAMAGE = 15;

    /**
     * Maximum damage the Sentinel can deal while its armor is intact (armored
     * state).
     */
    private static final int ARMORED_MAX_DAMAGE = 30;

    /**
     * The Sentinel's starting and maximum health value.
     */
    private static final int SENTINEL_HEALTH = 150;

    /**
     * The Sentinel's base attack damage.
     */
    private static final int SENTINEL_ATTACK = 25;

    /** Tracks whether the Sentinel's armor has cracked */
    private boolean armorCracked = false;

    /** Random number generator for damage variation */
    private Random rand = new Random();

    /**
     * Constructs a Sentinel with predefined boss stats.
     */
    // [Classes]
    public Sentinel() {
        super("Sentinel", SENTINEL_HEALTH, SENTINEL_ATTACK);
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

        if (health <= ARMOR_CRACK_HEALTH_THRESHOLD) {
            if (!armorCracked) {
                System.out.println("\nCracks form in the Sentinel's armor...");
                armorCracked = true;
            }
            damage = rand.nextInt(WEAKENED_MAX_DAMAGE - WEAKENED_MIN_DAMAGE + 1) + WEAKENED_MIN_DAMAGE;
        } else {
            damage = rand.nextInt(ARMORED_MAX_DAMAGE - ARMORED_MIN_DAMAGE + 1) + ARMORED_MIN_DAMAGE;
        }

        player.takeDamage(damage);
        System.out.println("\nThe Sentinel smashes you for " + damage + "!");
    }
}