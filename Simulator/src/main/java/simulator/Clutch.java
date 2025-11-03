package simulator;

public class Clutch extends Component {
    private boolean clutchState;

    public Clutch(String manufacturer, String model, int weight) {
        super(manufacturer, model, weight);
        this.clutchState = false;
    }

    public void pressClutch() {
        this.clutchState = true;
    }

    public void releaseClutch() {
        this.clutchState = false;
    }
}
