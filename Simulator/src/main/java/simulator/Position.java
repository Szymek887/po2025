package simulator;

public class Position {
    private double x;
    private double y;

    public Position() {
        this.x = 0.0;
        this.y = 0.0;
    }

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    @Override
    public String toString() {
        return "x: " + this.x + ", y: " + this.y;
    }
}
