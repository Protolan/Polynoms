public class Test {
    public static void main(String[] args) {
        Field field = new Field(7, 0, 1);

        Lagrange lagrange = new Lagrange(field);
        Polynomial finalResult = lagrange.GetPolynomial();

        Newton newton = new Newton(field);
        Polynomial finalResult2 = newton.GetPolynomial();

        PrintResults(field, finalResult, finalResult2);
    }

    public static void PrintResults(Field field, Polynomial firstP, Polynomial secondP) {
        Point[] array = field.GetPoints();
        int counter = 1;
        System.out.println();
        System.out.print("LAGRANGE: ");
        firstP.Print();
        System.out.print("NEWTON: ");
        secondP.Print();
        System.out.println();
        System.out.printf("%15s%15s%15s%15s%15s%15s\n", "", "X", "Y", "F(X)", "LAGRANGE", "NEWTON");
        for (int i = 0; i < array.length - 1; i++) {
            System.out.printf("%15d%15.6E\t%15.6E\t%15.6E\t%15.6E\t%15.6E\t\n",
                    counter, array[i].GetX(), array[i].GetY(), array[i].GetY(), firstP.GetValueAtPoint(array[i].GetX()), secondP.GetValueAtPoint(array[i].GetX()));

            counter++;
            double point = (array[i + 1].GetX() + array[i].GetX()) / 2;

            System.out.printf("%15d%15.6E\t%15s\t%15.6E\t%15.6E\t%15.6E\t\n",
                    counter, point, "", field.Function(point), firstP.GetValueAtPoint(point), secondP.GetValueAtPoint(point));

            counter++;

        }

    }
}
