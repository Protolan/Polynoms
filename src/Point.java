public class Point {
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double GetX() {
        return x;
    }

    public double GetY() {
        return y;
    }

    public void SetX(double x) {
        this.x = x;
    }

    public void SetY(double y) {
        this.y = y;
    }
}
