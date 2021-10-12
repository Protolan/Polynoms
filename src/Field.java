public class Field {

    private Point[] array;
    //найти amount точек на отрезке [a,b]
    public Field(int amount, double a, double b){
        array = new Point[amount];
        array[0] = new Point(a, Function(a));
        array[amount - 1] = new Point(b, Function(b));
        double counter = (b-a)/Double.parseDouble(String.valueOf(amount - 1));
        for (int i = 1; i < amount - 1; i++){
            a += counter;
            array[i] = new Point(a, Function(a));
        }
    }

    //0.8213*x*x*x*x*x + 4*x*x*x*x + 123.608*x*x*x + 199*x*x + x + 8
    public double Function(double x){
        return (Math.sin(x * x / 2));
    }

    public Point[] GetArray() {
        return array;
    }


}
