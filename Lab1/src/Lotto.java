import java.util.Random;
import java.util.ArrayList;

public class Lotto {
    public static void main(String[] args) {
        Random rand = new Random();

        int numbersCount = 6;

        ArrayList<Integer> numbers = new ArrayList<>();

        while (numbers.size() < numbersCount) {
            int number = rand.nextInt(49) + 1;
            if (numbers.contains(number)) {
                continue;
            }
            numbers.add(number);
        }

        System.out.println("Generated numbers:");

        for (int number: numbers) {
            System.out.println(number);
        }
    }
}
