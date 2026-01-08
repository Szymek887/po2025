package com.szymczak.car;

import com.szymczak.interfaces.Listener;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class Car implements Runnable {
    private final String name;
    private final String regNumber;
    private final int weight;
    private int speed;
    private final Engine engine;
    private final Gearbox gearbox;
    private final Clutch clutch;
    private final Position currentPosition;
    private volatile Position targetPosition;
    private final AtomicBoolean runningState = new AtomicBoolean(false);

    private final AtomicBoolean simulationRunning = new AtomicBoolean(false);

    private final List<Listener> listeners = new CopyOnWriteArrayList<>();


    public Car() {
        this.name = "DefaultCar";
        this.regNumber = "XX 00000";
        this.weight = 100;
        this.speed = 100;
        this.engine = new Engine("DefaultEngineManufacturer", "DefaultEngineModel", 100, 1000);
        this.gearbox = new Gearbox("DefaultGearboxManufacturer", "DefaultGearboxModel", 100, 1000);
        this.clutch = new Clutch("DefaultClutchManufacturer", "DefaultClutchModel", 100, 1000);
        this.currentPosition = new Position();
        this.targetPosition = new Position();
    }

    public Car(String name, String regNumber, int weight, int maxSpeed, Engine engine, Gearbox gearbox, Clutch clutch) {
        this.name = name;
        this.regNumber = regNumber;
        this.weight = weight;
        this.speed = maxSpeed;
        this.engine = engine;
        this.gearbox = gearbox;
        this.clutch = clutch;
        this.currentPosition = new Position();
        this.targetPosition = new Position();
    }

    @Override
    public String toString() {
        return this.name;
    }

    public void startSimulation() {
        this.simulationRunning.set(true);
        Thread thread = new Thread(this);
        thread.start();
    }

    public void stopSimulation() {
        this.simulationRunning.set(false);
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
        long lastTime = System.currentTimeMillis();

        while (simulationRunning.get()) {
            long currentTime = System.currentTimeMillis();
            double deltaTime = (currentTime - lastTime) / 1000f;
            lastTime = currentTime;

            if (this.runningState.get()) {
                updatePhysics(deltaTime);
            }

            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    private void updatePhysics(double deltaTime) {
        if (targetPosition == null) return;

        double currentX = currentPosition.getX();
        double currentY = currentPosition.getY();
        double targetX = targetPosition.getX();
        double targetY = targetPosition.getY();

        double distance = Math.sqrt(Math.pow(currentX - targetX, 2) + Math.pow(currentY - targetY, 2));

        if (distance > 1f) {
            this.speed = this.gearbox.getCurrentSpeed(this.engine.getRevs(), this.weight);

            double dx = this.speed * deltaTime * (targetX - currentX) / distance;
            double dy = this.speed * deltaTime * (targetY - currentY) / distance;

            synchronized (currentPosition) {
                this.currentPosition.setPosition(currentX + dx, currentY + dy);
            }

            this.notifyListeners();
        } else {
            synchronized (currentPosition) {
                this.currentPosition.setPosition(targetX, targetY);
            }
            this.targetPosition = null;
            this.notifyListeners();
        }
    }

    public CarData getCarData() {
        Position safePosition = new Position();
        synchronized (this.currentPosition) {
            safePosition.setPosition(this.currentPosition.getX(), this.currentPosition.getY());
        }

        return new CarData(
                this.name,
                this.regNumber,
                this.weight,
                this.speed,
                safePosition,
                this.gearbox.getModel(),
                this.gearbox.getPrice(),
                this.gearbox.getWeight(),
                this.gearbox.getGear(),
                this.engine.getModel(),
                this.engine.getPrice(),
                this.engine.getWeight(),
                this.engine.getRevs(),
                this.clutch.getModel(),
                this.clutch.getPrice(),
                this.clutch.getWeight(),
                this.clutch.isPressed()
        );
    }

    public void turnOn() {
        this.engine.turnOn();
        this.gearbox.setFirstGear();
        this.runningState.set(true);
        this.notifyListeners();
    }

    public void turnOff() {
        this.engine.turnOff();
        this.runningState.set(false);
        this.notifyListeners();
    }

    public void increaseGear() {
        double oldRatio = this.gearbox.getGearRatio();

        this.clutch.pressClutch();

        if (this.gearbox.increaseGear()) {
            double newRatio = this.gearbox.getGearRatio();
            int newRevs = (int) (this.engine.getRevs() * (newRatio / oldRatio));
            this.engine.decreaseRevs(this.engine.getRevs() - newRevs);
        } else {
            return;
        }

        this.clutch.releaseClutch();
        this.notifyListeners();
    }

    public void decreaseGear() {
        double oldRatio = this.gearbox.getGearRatio();

        this.clutch.pressClutch();

        if (this.gearbox.increaseGear()) {
            double newRatio = this.gearbox.getGearRatio();
            int newRevs = (int) (this.engine.getRevs() * (newRatio / oldRatio));
            this.engine.decreaseRevs(this.engine.getRevs() - newRevs);
        } else {
            return;
        }

        this.clutch.releaseClutch();
        this.notifyListeners();
    }

    public void rideTo(Position newPosition) {
        this.targetPosition = newPosition;
    }

    public void speedUp() {
        this.engine.increaseRevs(500);
        this.notifyListeners();
    }

    public void slowDown() {
        this.engine.decreaseRevs(500);
        this.notifyListeners();
    }

    public void pressClutch() {
        this.clutch.pressClutch();
        this.notifyListeners();
    }

    public void releaseClutch() {
        this.clutch.releaseClutch();
        this.notifyListeners();
    }
}
