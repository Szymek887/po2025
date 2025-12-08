package com.szymczak.car;

import com.szymczak.interfaces.Listener;

import java.util.ArrayList;
import java.util.List;

public class Car extends Thread {
    private final String name;
    private final String regNumber;
    private final int weight;
    private final int speed;
    private final Engine engine;
    private final Gearbox gearbox;
    private final Clutch clutch;
    private Position currentPosition;
    private Position targetPosition;

    private List<Listener> listeners;

    private boolean runningState;

    public Car() {
        this.name = "DefaultCar";
        this.regNumber = "XX 00000";
        this.weight = 100;
        this.speed = 100;
        this.engine = new Engine("DefaultEngineManufacturer", "DefaultEngineModel", 100, 1000);
        this.gearbox = new Gearbox("DefaultGearboxManufacturer", "DefaultGearboxModel", 100, 1000);
        this.clutch = new Clutch("DefaultClutchManufacturer", "DefaultClutchModel", 100, 1000);
        this.runningState = false;
        this.currentPosition = new Position();
        this.targetPosition = new Position();
        this.listeners = new ArrayList<Listener>();

        this.start();
    }

    public Car(String name, String regNumber, int weight, Engine engine, Gearbox gearbox, Clutch clutch) {
        this.name = name;
        this.regNumber = regNumber;
        this.weight = weight;
        this.speed = 100;
        this.engine = engine;
        this.gearbox = gearbox;
        this.clutch = clutch;
        this.runningState = false;
        this.currentPosition = new Position();
        this.targetPosition = new Position();
        this.listeners = new ArrayList<Listener>();

        this.start();
    }

    public void addListener(Listener listener) {
        this.listeners.add(listener);
    }

    public void removeListener(Listener listener) {
        this.listeners.remove(listener);
    }

    private void notifyListeners() {
        for (Listener listener : this.listeners) {
            listener.update();
        }
    }

    @Override
    public void run() {

        double deltat = 0.1;

        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println(this.targetPosition);
            if (targetPosition != null) {
                double currentX = currentPosition.getX();
                double currentY = currentPosition.getY();
                double targetX = targetPosition.getX();
                double targetY = targetPosition.getY();

                double distance = Math.sqrt(Math.pow(targetX - currentX, 2) + Math.pow(targetY - currentY, 2));

                if (distance > 10) {
                    double velocity = (double) this.getSpeed();

                    double dx = velocity * deltat * (targetX - currentX) / distance;
                    double dy = velocity * deltat * (targetY - currentY) / distance;

                    this.currentPosition.setPosition(currentX + dx, currentY + dy);

                    this.notifyListeners();
                } else {
                    this.currentPosition.setPosition(targetX, targetY);
                    this.targetPosition = null;
                }
            }
        }
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
        this.currentPosition.setPosition(x, y);
    }

    public Position getPosition() {
        return this.currentPosition;
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

    public String getRegNumber() {
        return this.regNumber;
    }

    public int getWeight() {
        return this.weight;
    }

    public int getSpeed() {
        return this.speed;
    }

    public String getGearboxName() {
        return this.gearbox.getModel();
    }

    public int getGearboxPrice() {
        return this.gearbox.getPrice();
    }

    public int getGearboxWeight() {
        return this.gearbox.getWeight();
    }

    public String getEngineName() {
        return this.engine.getModel();
    }

    public int getEnginePrice() {
        return this.engine.getPrice();
    }

    public int getEngineWeight() {
        return this.engine.getWeight();
    }

    public int getEngineRPM() {
        return this.engine.getRevs();
    }

    public String getClutchName() {
        return this.clutch.getModel();
    }

    public int getClutchPrice() {
        return this.clutch.getPrice();
    }

    public int getClutchWeight() {
        return this.clutch.getWeight();
    }

    public boolean getClutchStatus() {
        return this.clutch.isPressed();
    }

    public void rideTo(Position newPosition) {
        this.targetPosition = newPosition;
    }
}
