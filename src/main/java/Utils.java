import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Utils {

    private static Scanner pauseInput = new Scanner(System.in);

    public static void pause() {
        System.out.println("\n(Press ENTER)");
        pauseInput.nextLine();
    }

    public static void clear() {
        for (int i = 0; i < 25; i++) {
            System.out.println();
        }
    }

    // File I/O
    public static void save(String data) {
        try {
            FileWriter fw = new FileWriter("save.txt");
            fw.write(data);
            fw.close();
            System.out.println("Game saved.");
        } catch (IOException e) {
            System.out.println("Save failed.");
        }
    }
}
