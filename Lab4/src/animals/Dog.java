package animals;

public class Dog extends Animal {

    public Dog() {
        this.legs = 4;
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public void makeSound() {
        System.out.println("hau hau");
    }
}
