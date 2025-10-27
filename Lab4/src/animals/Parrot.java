package animals;

public class Parrot extends Animal{

    public Parrot() {
        this.legs = 2;
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public void makeSound() {
        System.out.println("ćwir ćwir");
    }
}
