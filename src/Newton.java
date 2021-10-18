public class Newton {
    private final Polynomial _polynomial;
    public Newton(Field field){

        Point[] array = field.GetPoints();
        double[] differencesArray = FindDifferences(array);
        _polynomial = new Polynomial(0,array[0].GetY());
        Polynomial temp1 = new Polynomial(0,1);
        Polynomial temp2 = new Polynomial(0,1);
        int len = array.length;

        for (int i = 1; i < len; i++){
            for (int j = 0; j < i; j++){
                temp2.RewriteMonomial(1,1);
                Polynomial temp3 = new Polynomial(0,-array[j].GetX());
                temp2.AddPolynomials(temp3);
                temp1 = temp1.MultiplyPolynomials(temp2);
            }
            temp1.MultiplyWithNumber(differencesArray[i]);
            _polynomial.AddPolynomials(temp1);
            temp1 = new Polynomial(0,1);
            temp2.RewriteMonomial(0,0);
        }

    }

    public double[] FindDifferences(Point[] array){
        double[] result = new double[array.length];
        double[] temp1 = new double[array.length];
        double[] temp2 = new double[array.length];
        for (int i = 0; i < array.length; i ++){
            temp1[i] = array[i].GetY();
        }
        for (int i = 1; i < array.length; i++){
            for (int j = i; j < array.length; j++){
                temp2[j] = (temp1[j] - temp1[j - 1]) / (array[j].GetX() - array[j - i].GetX());
            }
            result[i] = temp2[i];
            temp1 = temp2;
            temp2 = new double[array.length];
        }
        return result;
    }

    public Polynomial GetPolynomial() {
        return _polynomial;
    }
}
