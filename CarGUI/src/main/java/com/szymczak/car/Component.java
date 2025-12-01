package com.szymczak.car;

public abstract class Component {
    private final String manufacturer;
    private final String model;
    private final int weight;
    private final int price;

    public Component(String manufacturer, String model, int weight, int price) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.weight = weight;
        this.price = price;
    }

    public String getManufacturer() {
        return this.manufacturer;
    }

    public String getModel() {
        return this.model;
    }

    public int getWeight() {
        return this.weight;
    }

    public int getPrice() {
        return this.price;
    }
}
