package zadania;

import animals.Animal;
import animals.Dog;
import animals.Parrot;
import animals.Snake;

import java.util.Random;

public class Zoo {
    public static void main(String[] args) {
        Random rand = new Random();
        Animal[] animals = new Animal[100];

        for (int i = 0; i < animals.length; i++) {
            int randomNumber = rand.nextInt(3);
            if (randomNumber == 0) {
                animals[i] = new Dog();
            } else if (randomNumber == 1) {
                animals[i] = new Snake();
            } else {
                animals[i] = new Parrot();
            }
            animals[i].getDescription();
            int legs = animals[i].getLegs();
            animals[i].makeSound();
        }

        int sumOfLegs = countLegs(animals);
        System.out.println("Suma wszystkich nóg: " + sumOfLegs);
    }

    private static int countLegs(Animal[] animals) {
        int sumOfLegs = 0;
        for (Animal animal : animals) {
            sumOfLegs += animal.getLegs();
        }
        return sumOfLegs;
    }
}
