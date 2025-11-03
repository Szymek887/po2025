package simulator;

public abstract class Component {
    private final String manufacturer;
    private final String model;
    private final int weight;

    public Component(String manufacturer, String model, int weight) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.weight = weight;
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
}
