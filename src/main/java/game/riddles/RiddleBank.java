package game.riddles;

import java.util.ArrayList;

/**
 * The RiddleBank class stores and manages a collection of riddles
 * used throughout the game.
 * It provides functionality to load riddles and randomly shuffle
 * them using a recursive algorithm.
 *
 * @author Akash K.
 * @author Parusan P.
 * @author Varshini B.
 * @version 1.0
 */
// [Classes]
// [Recursion]
public class RiddleBank {

    /**
     * Loads all riddles into an ArrayList.
     *
     * @return an ArrayList containing all available riddles
     */
    // [Classes]
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

    /**
     * Public method that initiates a recursive shuffle of the riddle list.
     *
     * @param list the list of riddles to be shuffled
     */
    // [Recursion]
    public static void shuffle(ArrayList<Riddle> list) {
        shuffle(list, list.size());
    }

    /**
     * Recursively shuffles the list of riddles by swapping elements.
     * This method reduces the problem size on each recursive call
     * until the base case is reached.
     *
     * @param list the list of riddles to shuffle
     * @param n the number of elements remaining to shuffle
     */
    // [Recursion]
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