// Classes
// Creates and initializes the game world structure.
import java.util.ArrayList;

public class WorldBuilder {

    public static void createWorld(ArrayList<Room> map) {

        // Room 0: Forest Clearing
        Room r0 = new Room("Forest Clearing", "Trees close in around you...");
        r0.setArt(GridQuestRPG.loadArtFromFile("forest_art.txt"));
        r0.setEnemy(new Enemy("Goblin", 40, 10));
        r0.addExit("right", 1);
        map.add(r0);

        // Room 1: Town Square
        Room r1 = new Room(
            "Town Square",
            "The town center. Left: Tavern, Right: Storage, Up: The Forgotten Road."
        );
        r1.setArt(GridQuestRPG.loadArtFromFile("town_art.txt"));
        r1.addExit("right", 2);
        r1.addExit("left", 3);
        r1.addExit("up", 4);
        map.add(r1);

        // Room 2: Abandoned Storage
        Room r2 = new Room("Abandoned Storage", "A quiet room with a single chest.");
        r2.setArt(GridQuestRPG.loadArtFromFile("storage_art.txt"));
        r2.addExit("left", 1);
        map.add(r2);

        // Room 3: The Lucky Tavern
        Room r3 = new Room("The Lucky Tavern", "Smells of old wood. Type 'gamble' to play.");
        r3.setArt(GridQuestRPG.loadArtFromFile("arcade_art.txt"));
        r3.addExit("right", 1);
        map.add(r3);

        // Room 4: The Forgotten Road
        Room r4 = new Room(
            "The Forgotten Road",
            "The path ahead is clear and silent. Something massive watches from beyond the fog."
        );
        r4.setArt(GridQuestRPG.loadArtFromFile("road_art.txt"));
        r4.addExit("down", 1);
        r4.addExit("up", -1);
        map.add(r4);
    }
}
