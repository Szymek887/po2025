public class Choinka {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Provide height as a parameter");
            return;
        }

        int height = Integer.valueOf(args[0]);

        for (int i = 1; i <= height; i++) {
            printSpaces(height - i);
            printStars(2 * i - 1);
        }

        printStump(height);
    }

    public static void printSpaces(int spaces) {
        for (int i = 0; i < spaces; i++) {
            System.out.print(" ");
        }
    }

    public static void printStars(int stars) {
        for (int i = 0; i < stars; i++) {
            System.out.print("*");
        }
        System.out.println();
    }

    public static void printStump(int height) {
        for (int i = 0; i < 2; i++) {
            printSpaces(height - 2);
            printStars(3);
        }
    }
}

