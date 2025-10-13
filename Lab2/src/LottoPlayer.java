import java.util.ArrayList;

public class LottoPlayer {
    public static void main(String[] args) {
        int numbersCount = 6;
        Lotto lotto = new Lotto(numbersCount);

        lotto.generateNumbers(numbersCount);

        ArrayList<Integer> guesses = new ArrayList<>();

        for (int i = 0; i < numbersCount; i++) {
            guesses.add(Integer.parseInt(args[i]));
        }

        int correctGuesses = lotto.checkGuesses(guesses);

        System.out.println("Your guesses:");
        System.out.println(guesses);

        System.out.println("Generated numbers:");
        System.out.println(lotto.getGeneratedNumbers());

        System.out.println("Correct guesses: " + correctGuesses);
    }
}
