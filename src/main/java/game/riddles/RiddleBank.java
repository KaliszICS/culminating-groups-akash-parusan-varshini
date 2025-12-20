package game.riddles;
// Classes, Recursion
// Stores riddles and uses a recursive shuffle algorithm.
import java.util.ArrayList;

public class RiddleBank {

    public static ArrayList<Riddle> load() {
        ArrayList<Riddle> riddles = new ArrayList<>();
        riddles.add(new Riddle("What has keys but can't open locks?", "keyboard"));
        riddles.add(new Riddle("What runs but never walks?", "water"));
        riddles.add(new Riddle("What has a head and a tail but no body?", "coin"));
        riddles.add(new Riddle("What gets wetter the more it dries?", "towel"));
        riddles.add(new Riddle("What can travel around the world while staying in one spot?", "stamp"));
        riddles.add(new Riddle("What has hands but cannot clap?", "clock"));
        riddles.add(new Riddle("What has an eye but cannot see?", "needle"));
        riddles.add(new Riddle("What goes up but never comes down?", "age"));
        riddles.add(new Riddle("What has a neck but no head?", "bottle"));
        riddles.add(new Riddle("What has words but never speaks?", "book"));
        return riddles;
    }

    public static void shuffle(ArrayList<Riddle> list) {
        shuffle(list, list.size());
    }

    private static void shuffle(ArrayList<Riddle> list, int n) {
        if (n <= 1) {
            return;
        }
        int j = (int) (Math.random() * n);
        Riddle temp = list.get(n - 1);
        list.set(n - 1, list.get(j));
        list.set(j, temp);
        shuffle(list, n - 1);
    }
}
