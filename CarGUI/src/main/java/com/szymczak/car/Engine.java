package com.szymczak.car;

public class Engine extends Component {
    private int revs;
    private int maxRevs;

    public Engine(String manufacturer, String model, int weight) {
        super(manufacturer, model, weight);
        this.revs = 0;
        this.maxRevs = 4000;
    }

    public void turnOn() {
        this.revs = 800;
    }

    public void turnOff() {
        this.revs = 0;
    }

    public void increaseRevs(int increment) {
        this.revs = Math.min(this.revs + increment, this.maxRevs);
    }

    public void decreaseRevs(int decrement) {
        this.revs = Math.max(0, this.revs - decrement);
    }
}
