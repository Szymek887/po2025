public class CodingBat {
    public static void main(String[] args) {
        CodingBat cb = new CodingBat();
    }

    public boolean sleepIn(boolean weekday, boolean vacation) {
        return !weekday || vacation;
    }

    public int diff21(int n) {
        if (n > 21) {
            return 2 * Math.abs(21 - n);
        } else {
            return Math.abs(21 - n);
        }
    }

    public String helloName(String name) {
        return "Hello " + name + "!";
    }

    public int countEvens(int[] nums) {
        int counter = 0;
        for (int num : nums) {
            if (num % 2 == 0) {
                counter++;
            }
        }
        return counter;
    }
}
