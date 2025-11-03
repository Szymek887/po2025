package simulator;

public class Gearbox extends Component {
    private int gear;
    private final int numberOfGears;

    public Gearbox(String manufacturer, String model, int weight) {
        this(manufacturer, model, 6, weight);
    }

    public Gearbox(String manufacturer, String model, int numberOfGears, int weight) {
        super(manufacturer, model, weight);
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
