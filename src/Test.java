public class Test {
    public static void main(String[] args){
        Field f = new Field(7,0,1);

        Lagrange res = new Lagrange(f);
        Polynomial finalResult = res.GetPolynomial();

        Newton n = new Newton(f);
        Polynomial finalResult2 = n.GetP();

        PrintResults(f,finalResult, finalResult2);
    }

    public static void PrintResults(Field f, Polynomial firstP, Polynomial secondP){
        Point[] array = f.GetArray();
        int counter = 1;
        System.out.println();
        System.out.print("LAGRANGE: ");
        firstP.Print();
        System.out.print("NEWTON: ");
        secondP.Print();
        System.out.println();
        System.out.printf("%15s%15s%15s%15s%15s%15s\n", "", "X", "Y", "F(X)", "LAGRANGE", "NEWTON");
        for (int i = 0; i < array.length - 1; i++){
            System.out.printf("%15d%15.6E\t%15.6E\t%15.6E\t%15.6E\t%15.6E\t\n",
                    counter, array[i].GetX(), array[i].GetY(), array[i].GetY(), firstP.GetValueAtPoint(array[i].GetX()), secondP.GetValueAtPoint(array[i].GetX()));

            counter++;
            double point = (array[i + 1].GetX() + array[i].GetX())/2;

            System.out.printf("%15d%15.6E\t%15s\t%15.6E\t%15.6E\t%15.6E\t\n",
                    counter,   point, "", f.Function(point), firstP.GetValueAtPoint(point), secondP.GetValueAtPoint(point));

            counter++;

        }

    }
}
