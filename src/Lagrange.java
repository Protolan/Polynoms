public class Lagrange {
    private Polynomial _result;
    
    public Lagrange(Field polynomial){

        Point[] array = polynomial.GetPoints();
        int len = array.length;

        for (int i = 0; i < len; i ++ ){
            double x = array[i].GetX();
            double sum;
            Polynomial p1 = new Polynomial(1,1);

            if (i == 0) {
                sum = x - array[1].GetX();
                p1.AddPolynomials(new Polynomial(0,-array[1].GetX()));
            }
            else {
                sum = x - array[0].GetX();
                p1.AddPolynomials(new Polynomial(0,-array[0].GetX()));
            }

            for (int j = 1; j < len; j ++){
                if (j == i || (i == 0 && j == 1)) continue;
                Polynomial p2 = new Polynomial(1,1);
                sum *= (x - array[j].GetX());
                p2.AddPolynomials(new Polynomial(0, -array[j].GetX()));
                p1 = p1.MultiplyPolynomials(p2);
            }
            p1.MultiplyWithNumber(array[i].GetY()/sum);
            if (i == 0) _result = new Polynomial(p1);
            else _result.AddPolynomials(p1);
        }
    }

    public Polynomial GetPolynomial(){
        return _result;
    }
}
