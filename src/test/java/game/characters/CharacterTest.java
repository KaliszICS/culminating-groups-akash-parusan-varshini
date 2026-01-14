package game.characters;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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
        assertEquals(player.getHealth(), 70);
    }

    }
