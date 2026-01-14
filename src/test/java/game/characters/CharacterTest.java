package game.characters;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CharacterTest {

    @Test
    void testPlayerinitialHP(){
        Player player = new Player();
        assertEquals(100, player.getHealth());
    }
    @Test
    void testPlayerinitialATK(){
        Player player = new Player ();
        assertEquals(15, player.getAttack());
    }
    @Test
    void testdmgTakenByPlayer(){
        Player player = new Player ();
        player.takeDamage(30);
        assertEquals(70, player.getHealth());
    }

    @Test
    void testOverHealing() {
        Player player = new Player();
        player.takeDamage(60);
        player.heal(20);
        assertEquals(60, player.getHealth());
    }
    @Test
    void testHealing() {
        Player player = new Player();
        player.takeDamage(20);
        player.heal(30);
        assertEquals(100, player.getHealth());
    }
    @Test
    void testEnemyspawn(){
        Enemy enemy = new Enemy("Sentinel", 150, 25);
        assertEquals("Sentinel", enemy.getName());
        assertEquals(150, enemy.getHealth());
        assertEquals(25, enemy.getAttack());
    }

}
