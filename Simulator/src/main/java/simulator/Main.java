package simulator;

public class Main {
    public static void main(String[] args) {
        Engine engine = new Engine("Toyota", "V8 Turbo", 250);
        Gearbox gearbox = new Gearbox("Toyota", "6-Speed Manual", 300);
        Clutch clutch = new Clutch("Toyota", "6-Speed Manual", 400);

        Car car = new Car(engine, gearbox, clutch);

        car.turnOn();

        car.speedUp(3000);
        car.increaseGear();
        car.speedUp(3000);
        car.increaseGear();

        car.slowDown(200);
        car.decreaseGear();
        car.slowDown(3000);
        car.decreaseGear();

        car.setPosition(5, 10);

        car.turnOff();
    }
}
