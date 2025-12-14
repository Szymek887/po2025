package com.szymczak.car;

public class Gearbox extends Component {
    private volatile int gear;
    private final int numberOfGears;

    private final double[] gearRatios = {
        0.0,   // Neutral
        3.5,   // 1st Gear
        2.5,   // 2nd Gear
        1.8,   // 3rd Gear
        1.3,   // 4th Gear
        1.0,   // 5th Gear
        0.8    // 6th Gear
    };
    private final double FINAL_DRIVE_RATIO = 3.4;
    private final double WHEEL_CIRCUMFERENCE = 2.0; // in meters

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

    public boolean increaseGear() {
        if (this.gear < numberOfGears) {
            this.gear++;
            return true;
        }
        return false;
    }

    public boolean decreaseGear() {
        if (this.gear > 1) {
            this.gear--;
            return true;
        }
        return false;
    }

    public void setFirstGear() {
        this.gear = 1;
    }

    public int getGear() {
        return this.gear;
    }

    public double getGearRatio() {
        return gearRatios[this.gear];
    }

    public int getCurrentSpeed(int revs, int weight) {
        double gearRatio = gearRatios[this.gear];

        double wheelRpm = revs / (gearRatio * FINAL_DRIVE_RATIO);
        double speedMetersPerMinute = wheelRpm * WHEEL_CIRCUMFERENCE;
        double speedKmPerHour = (speedMetersPerMinute * 60) / 1000;

        return (int) Math.round(speedKmPerHour);
    }
}
