package animals;

public class Snake extends Animal {

    public Snake() {
        this.legs = 0;
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public void makeSound() {
        System.out.println("sssssssssss");
    }
}
