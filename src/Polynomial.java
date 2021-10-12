public class Polynomial {
    private static class node {
        private int degree;     //polynomial degree
        private double coefficient;       //coefficient
        private node next;
        public node(int d, double c){
            degree = d;
            coefficient = c;
            next = null;
        }
    }
    private node head;


    public Polynomial(int d, double c){
        head = new node(d, c);
    }

    public Polynomial(Polynomial p1){
        node mainhead = p1.head;
        this.head = new node(mainhead.degree, mainhead.coefficient);
        node temphead = this.head;
        while (mainhead.next != null){
            temphead.next = new node(mainhead.degree, mainhead.coefficient);
            mainhead = mainhead.next;
        }
    }

    public void MultiplyWithNumber(double digit) {
        if (IsZero(digit)) {
            head = new node(1,0);
            return;
        }
        node temp = this.head;
        while (temp != null){
            temp.coefficient = temp.coefficient * digit;
            temp = temp.next;
        }
    }
    public void AddPolynomials(Polynomial p){
        if (p.head == null || head == null) return;
        if ((p.head.coefficient == 0 && p.head.next == null)) return;
        if (this.head.coefficient == 0 && this.head.next == null) new Polynomial(p);
        node temp, newP;
        if (this.head.degree <= p.head.degree) {
            node tempH = p.head;
            p.head = this.head;
            this.head = tempH;
        }
        newP = this.head;
        temp = p.head;
        node previous = null;
        while (temp != null && newP != null){
            if (IsZero(newP.coefficient)){
                if (newP.next == null)
                    newP = null;
                else
                    RewriteNode(newP, newP.next, newP.coefficient, newP.degree);
                continue;
            }

            double tempCoef = newP.coefficient + temp.coefficient;
            if (IsZero(tempCoef) && newP.degree == temp.degree){
                if (newP.next != null)
                    RewriteNode(newP, newP.next.next, newP.next.coefficient, newP.next.degree);
                else
                    newP = null;
                temp = temp.next;
                continue;
            }

            if (newP.degree == temp.degree){
                newP.coefficient = tempCoef;
                previous = newP;
                newP = newP.next;
                temp = temp.next;
            }
            else {
                if (newP.degree > temp.degree) {
                    previous = newP;
                    newP = newP.next;
                }
                else {
                    node temp2 = previous.next;
                    previous.next = temp;
                    newP = temp2;
                    temp = temp.next;
                }
            }
        }


        if (newP == null && previous != null) previous.next = temp;
    }

    public Polynomial MultiplyPolynomials(Polynomial p){
        if (p.head == null || head == null) return null;
        if ((IsZero(p.head.coefficient) && p.head.next == null) || (IsZero(this.head.coefficient) && this.head.next == null)) return new Polynomial(1,0);
        node head1 = this.head;
        node head2 = p.head;
        Polynomial result = new Polynomial(head1.degree + head2.degree, head1.coefficient * head2.coefficient);
        node head3 = result.head;
        head2 = head2.next;

        while (head1 != null){
            if (IsZero(head1.coefficient)){
                RewriteNode(head1, head1.next.next, head1.coefficient, head1.degree);
                continue;
            }
            while (head2 != null){
                int tempDegree = head1.degree + head2.degree;
                double tempCoefficient = head1.coefficient * head2.coefficient;
                if (head3.degree > tempDegree){
                    head3.next = new node(tempDegree, tempCoefficient);
                    head3 = head3.next;
                }
                else
                if (head3.degree == tempDegree){
                    head3.coefficient += tempCoefficient;
                }
                head2 = head2.next;
            }
            head2 = p.head;
            head1 = head1.next;
        }
        return result;
    }

    public double GetValueAtPoint(double x){
        node tempHead = this.head;
        int max_degree = tempHead.degree;
        double result = 0;
        for (int i = max_degree; i > 0; i--){
            if (tempHead != null && tempHead.degree == i) {
                result += tempHead.coefficient;
                result *= x;
                tempHead = tempHead.next;
            }
            else result *= x;
        }
        if (tempHead != null && tempHead.degree == 0) result += tempHead.coefficient;
        return result;
    }


    public void Print(){

        if (this.head == null) return;
        node temp = this.head;
        System.out.printf("%15.6E", temp.coefficient);
        System.out.print(" * X^" + temp.degree);
        temp = temp.next;
        while (temp != null){
            if (IsZero(temp.coefficient)){
                temp = temp.next;
                continue;
            }
            if (temp.coefficient >= 0) System.out.print("   +");
            System.out.printf("%15.6E", temp.coefficient);
            if (!IsZero(temp.degree)){
                if (temp.degree == 1) System.out.print(" * X");
                else System.out.print(" * X^" + temp.degree);
            }
            temp = temp.next;
        }
        System.out.println();
    }

    private void RewriteNode(node node1, node next, double c, int d){
        node1.next = next;
        node1.degree = d;
        node1.coefficient = c;
    }

    public void RewriteMonomial(double c, int d){
        this.head.coefficient = c;
        this.head.degree = d;
        this.head.next = null;
    }

    private boolean IsZero(double x) {
        double epsilon = 1e-5;
        return(Math.abs(x) <= epsilon);
    }
}
