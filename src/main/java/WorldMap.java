public class WorldMap {
    private String[][] grid;

    public WorldMap() {
        grid = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = "[ ]";
            }
        }
        grid[2][1] = "[F]"; // Forest
        grid[1][1] = "[T]"; // Town Square
        grid[1][0] = "[V]"; // Tavern
        grid[1][2] = "[S]"; // Storage
    }

    public void display(int roomIndex) {
        System.out.println("\n--- WORLD MINI-MAP ---");
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                if (isCurrentRoom(r, c, roomIndex)) {
                    System.out.print("[P]"); // P for Player
                } else {
                    System.out.print(grid[r][c]);
                }
            }
            System.out.println();
        }
        System.out.println("F: Forest | T: Town | V: Tavern | S: Storage");
    }

    private boolean isCurrentRoom(int r, int c, int index) {
        if (index == 0 && r == 2 && c == 1) return true;
        if (index == 1 && r == 1 && c == 1) return true;
        if (index == 2 && r == 1 && c == 2) return true;
        if (index == 3 && r == 1 && c == 0) return true;
        return false;
    }
}