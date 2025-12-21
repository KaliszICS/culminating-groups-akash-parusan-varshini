/**
 * Manages and displays the world map visualization for GridQuest RPG.
 * Uses a 2D array to represent game locations and tracks player position.
 * Demonstrates 2D array manipulation and coordinate mapping concepts from ICS4U.
 * 
 * Map Legend:
 * [F] Forest Clearing     (Room 0)
 * [T] Town Square         (Room 1)  
 * [S] Abandoned Storage   (Room 2)
 * [V] The Lucky Tavern    (Room 3)
 * [R] The Forgotten Road  (Room 4)
 * [C] Castle (Final Area) (Room -1/Boss)
 * [P] Player's current location
 * [ ] Empty/unused grid space
 * 
 * Grid Layout (4x3):
 * Row 0: [ ] [C] [ ]
 * Row 1: [ ] [R] [ ] 
 * Row 2: [V] [T] [S]
 * Row 3: [ ] [F] [ ]
 * 
 * @author [Parusan]
 * @author [Akash]
 * @author [Varshini]
 * @version 1.0
 */
public class WorldMap {
    
    /**
     * 2D string array representing the world map grid.
     * Each cell contains a string representation of a location.
     * Dimensions: 4 rows × 3 columns
     */
    private String[][] grid;

    /**
     * Constructs and initializes the world map grid.
     * Sets up all location markers in their fixed positions.
     * Demonstrates 2D array initialization and nested loops.
     */
    public WorldMap() {
        grid = new String[4][3];

        // Initialize all cells with empty markers
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = "[ ]";
            }
        }

        // Place location markers at specific coordinates
        grid[0][1] = "[C]"; // Castle (Row 0, Column 1)
        grid[1][1] = "[R]"; // Forgotten Road (Row 1, Column 1)
        grid[2][1] = "[T]"; // Town Square (Row 2, Column 1)
        grid[2][0] = "[V]"; // Tavern (Row 2, Column 0)
        grid[2][2] = "[S]"; // Storage (Row 2, Column 2)
        grid[3][1] = "[F]"; // Forest (Row 3, Column 1)
    }

    /**
     * Displays the world map with the player's current location highlighted.
     * Overwrites the location marker with [P] where the player currently is.
     * 
     * @param roomIndex the index of the player's current room in the game world
     */
    public void display(int roomIndex) {
        System.out.println("\n--- WORLD MINI-MAP ---");

        // Print grid with player position
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                if (isCurrentRoom(r, c, roomIndex)) {
                    System.out.print("[P]"); // Player marker
                } else {
                    System.out.print(grid[r][c]); // Location marker
                }
            }
            System.out.println(); // New line after each row
        }

        // Display legend
        System.out.println(
            "P: Player | F: Forest | T: Town | V: Tavern | S: Storage | R: Road | C: Castle"
        );
    }

    /**
     * Determines if the given grid coordinates correspond to the player's current room.
     * Maps room indices (0-4) to specific grid coordinates.
     * 
     * @param r the row coordinate in the grid
     * @param c the column coordinate in the grid  
     * @param index the room index from the game world
     * @return true if the grid cell at (r,c) represents the player's current room
     */
    private boolean isCurrentRoom(int r, int c, int index) {
        // Hard-coded mapping of room indices to grid coordinates
        if (index == 0 && r == 3 && c == 1) return true; // Forest
        if (index == 1 && r == 2 && c == 1) return true; // Town Square
        if (index == 2 && r == 2 && c == 2) return true; // Storage
        if (index == 3 && r == 2 && c == 0) return true; // Tavern
        if (index == 4 && r == 1 && c == 1) return true; // Forgotten Road
        return false;
    }
}