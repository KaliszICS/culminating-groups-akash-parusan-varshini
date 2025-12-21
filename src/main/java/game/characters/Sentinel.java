package game.characters;
// Inheritance, Overriding, Polymorphism
// Boss enemy that overrides attack behavior based on health state.
import java.util.Random;

public class Sentinel extends Enemy {

    private boolean armorCracked = false;
    private Random rand = new Random();

    public Sentinel() {
        super("Sentinel", 150, 25);
    }

    @Override
    public void takeTurn(Player player) {
        int damage;

        if (health <= 50) {
            if (!armorCracked) {
                System.out.println("\nCracks form in the Sentinel's armor...");
                armorCracked = true;
            }
            damage = rand.nextInt(10) + 10; // 10–19 weakened
        } else {
            damage = rand.nextInt(16) + 10; // 10–25 normal
        }

        player.takeDamage(damage);
        System.out.println("\nThe Sentinel smashes you for " + damage + "!");
        Utils.pause();
    }
}
