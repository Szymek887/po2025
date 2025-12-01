package com.szymczak.car;

public class Clutch extends Component {
    private boolean clutchState;

    public Clutch(String manufacturer, String model, int weight, int price) {
        super(manufacturer, model, weight, price);
        this.clutchState = false;
    }

    public void pressClutch() {
        this.clutchState = true;
    }

    public void releaseClutch() {
        this.clutchState = false;
    }

    public boolean isPressed() {
        return this.clutchState;
    }
}
