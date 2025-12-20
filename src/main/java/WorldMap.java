public class WorldMap {
    private String[][] grid;

    public WorldMap() {
        grid = new String[4][3];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = "[ ]";
            }
        }

        grid[0][1] = "[C]"; 
        grid[1][1] = "[R]"; 
        grid[2][1] = "[T]"; 
        grid[2][0] = "[V]"; 
        grid[2][2] = "[S]"; 
        grid[3][1] = "[F]"; 
    }

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

    private boolean isCurrentRoom(int r, int c, int index) {
        if (index == 0 && r == 3 && c == 1) return true; 
        if (index == 1 && r == 2 && c == 1) return true; 
        if (index == 2 && r == 2 && c == 2) return true; 
        if (index == 3 && r == 2 && c == 0) return true; 
        if (index == 4 && r == 1 && c == 1) return true; 
        return false;
    }
}
