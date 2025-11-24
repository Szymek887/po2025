package com.szymczak.car;

public class Car {
    private final String name;
    private final Engine engine;
    private final Gearbox gearbox;
    private final Position position;
    private final Clutch clutch;

    private boolean runningState;

    public Car() {
        this.name = "DefaultCar";
        this.engine = new Engine("DefaultEngineManufacturer", "DefaultEngineModel", 100);
        this.gearbox = new Gearbox("DefaultGearboxManufacturer", "DefaultGearboxModel", 100);
        this.clutch = new Clutch("DefaultClutchManufacturer", "DefaultClutchModel", 100);
        this.position = new Position();
        this.runningState = false;
    }

    public Car(String name, Engine engine, Gearbox gearbox, Clutch clutch) {
        this.name = name;
        this.engine = engine;
        this.gearbox = gearbox;
        this.clutch = clutch;
        this.position = new Position();
        this.runningState = false;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public void turnOn() {
        this.engine.turnOn();
        this.gearbox.setFirstGear();
        this.runningState = true;
    }

    public void turnOff() {
        this.engine.turnOff();
        this.runningState = false;
    }

    public void setPosition(double x, double y) {
        this.position.setPosition(x, y);
    }

    public Position getPosition() {
        return this.position;
    }

    public void increaseGear() {
        this.clutch.pressClutch();
        this.gearbox.increaseGear();
        this.engine.decreaseRevs(3000);
        this.clutch.releaseClutch();
    }

    public void decreaseGear() {
        this.clutch.pressClutch();
        this.gearbox.decreaseGear();
        this.engine.increaseRevs(3000);
        this.clutch.releaseClutch();
    }

    public void speedUp(int rpm) {
        if (this.runningState) {
            this.engine.increaseRevs(rpm);
        }
    }

    public void slowDown(int rpm) {
        if (this.runningState) {
            this.engine.decreaseRevs(rpm);
        }
    }

    public int getGear() {
        return this.gearbox.getGear();
    }
}
