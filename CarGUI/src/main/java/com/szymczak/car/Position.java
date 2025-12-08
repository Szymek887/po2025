package com.szymczak.car;

public class Position {
    private double x;
    private double y;

    public Position() {
        this.x = 0.0;
        this.y = 0.0;
    }

    public synchronized void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public synchronized double getX() {
        return this.x;
    }

    public synchronized double getY() {
        return this.y;
    }

    @Override
    public synchronized String toString() {
        return "x: " + this.x + ", y: " + this.y;
    }
}
