package com.szymczak.car;

public class Gearbox extends Component {
    private volatile int gear;
    private final int numberOfGears;

    public Gearbox(String manufacturer, String model, int weight, int price) {
        this(manufacturer, model, 6, weight, price);
    }

    public Gearbox(String manufacturer, String model, int numberOfGears, int weight, int price) {
        super(manufacturer, model, weight, price);
        if (numberOfGears < 1) {
            throw new IllegalArgumentException("numberOfGears must be at least 1");
        }
        this.gear = 1;
        this.numberOfGears = numberOfGears;
    }

    public void increaseGear() {
        if (this.gear < numberOfGears) {
            this.gear++;
        }
    }

    public void decreaseGear() {
        if (this.gear > 1) {
            this.gear--;
        }
    }

    public void setFirstGear() {
        this.gear = 1;
    }

    public int getGear() {
        return this.gear;
    }
}
