import java.util.ArrayList;
import java.util.Random;

public class LottoSimulation {
    public static void main(String[] args) {
        Random rand = new Random();

        int numbersCount = 6;

        ArrayList<Integer> guesses = new ArrayList<>();

        while (guesses.size() < numbersCount) {
            int number = rand.nextInt(49) + 1;
            if (guesses.contains(number)) {
                continue;
            }
            guesses.add(number);
        }

        int iterations = 0;

        long startTime = System.currentTimeMillis();

        Lotto lotto = new Lotto(numbersCount);
        while (lotto.checkGuesses(guesses) != numbersCount) {
            iterations++;
            lotto.generateNumbers(numbersCount);
        }

        long endTime = System.currentTimeMillis();

        System.out.println("Success!");
        System.out.println("Your guesses: " + guesses);
        System.out.println("Runtime: " + (endTime - startTime) + "ms");
        System.out.println("Iterations: " + iterations);
    }
}
