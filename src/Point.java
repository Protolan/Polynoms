public class Point {
    private double x;
    private double y;

    public Point(double a, double b){
        this.x = a;
        this.y = b;
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
