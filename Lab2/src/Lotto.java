import java.util.Random;
import java.util.ArrayList;

public class Lotto {
    private ArrayList<Integer> generatedNumbers;

    public Lotto(int numbersCount) {
        this.generateNumbers(numbersCount);
    }

    public void generateNumbers(int numbersCount) {
        Random rand = new Random();

        this.generatedNumbers = new ArrayList<>();

        while (this.generatedNumbers.size() < numbersCount) {
            int number = rand.nextInt(49) + 1;
            if (this.generatedNumbers.contains(number)) {
                continue;
            }
            this.generatedNumbers.add(number);
        }
    }

    public int checkGuesses(ArrayList<Integer> guesses) {
        int correctGuesses = 0;

        for (int guess : guesses) {
            if (this.generatedNumbers.contains(guess)) {
                correctGuesses++;
            }
        }

        return correctGuesses;
    }

    public ArrayList<Integer> getGeneratedNumbers() {
        return generatedNumbers;
    }
}
