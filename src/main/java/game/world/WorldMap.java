package game.world;

/**
 * The WorldMap class represents a 2-D mini-map of the game world.
 * It visually displays the player's current location relative
 * to other important areas such as the town, forest, tavern,
 * storage, road, and castle.
 *
 * This class uses a two-dimensional array to model spatial layout
 * and dynamically updates the player's position.
 *
 * @author Akash K.
 * @author Parusan P.
 * @author Varshini B.
 * @version 1.0
 */
// [Classes]
// [2-D arrays]
public class WorldMap {

    /** 2-D grid representing the world layout */
    private String[][] grid;

    /**
     * Constructs the world mini-map and initializes
     * all locations in the grid.
     */
    // [2-D arrays]
    public WorldMap() {
        grid = new String[4][3];

        // Initialize empty tiles
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = "[ ]";
            }
        }

        // Static world landmarks
        grid[0][1] = "[C]"; // Castle
        grid[1][1] = "[R]"; // Road
        grid[2][1] = "[T]"; // Town
        grid[2][0] = "[V]"; // Tavern
        grid[2][2] = "[S]"; // Storage
        grid[3][1] = "[F]"; // Forest
    }

    /**
     * Displays the world mini-map and highlights
     * the player's current position.
     *
     * @param roomIndex the index of the player's current room
     */
    // [2-D arrays]
    public void display(int roomIndex) {
        System.out.println("\n--- WORLD MINI-MAP ---");

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                if (isCurrentRoom(r, c, roomIndex)) {
                    System.out.print("[P]");
                } else {
                    System.out.print(grid[r][c]);
                }
            }
            System.out.println();
        }

        System.out.println(
            "P: Player | F: Forest | T: Town | V: Tavern | S: Storage | R: Road | C: Castle"
        );
    }

    /**
     * Determines whether a given grid position
     * corresponds to the player's current room.
     *
     * @param r grid row
     * @param c grid column
     * @param index current room index
     * @return true if the position matches the player's location
     */
    // [2-D arrays]
    private boolean isCurrentRoom(int r, int c, int index) {
        if (index == 0 && r == 3 && c == 1) return true; // Forest
        if (index == 1 && r == 2 && c == 1) return true; // Town
        if (index == 2 && r == 2 && c == 2) return true; // Storage
        if (index == 3 && r == 2 && c == 0) return true; // Tavern
        if (index == 4 && r == 1 && c == 1) return true; // Road
        return false;
    }
}