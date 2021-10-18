public class Polynomial {

    private static class Node {
        private int _degree;     //polynomial degree
        private double _coefficient;       //coefficient
        private Node _next;

        public Node(int degree, double coefficient) {
            _degree = degree;
            _coefficient = coefficient;
            _next = null;
        }
    }

    private Node _head;


    public Polynomial(int degree, double coefficient) {
        _head = new Node(degree, coefficient);
    }

    public Polynomial(Polynomial polynomial) {
        Node mainhead = polynomial._head;
        this._head = new Node(mainhead._degree, mainhead._coefficient);
        Node temphead = this._head;
        while (mainhead._next != null) {
            temphead._next = new Node(mainhead._degree, mainhead._coefficient);
            mainhead = mainhead._next;
        }
    }

    public void MultiplyWithNumber(double digit) {
        if (IsZero(digit)) {
            _head = new Node(1, 0);
            return;
        }
        Node temp = this._head;
        while (temp != null) {
            temp._coefficient = temp._coefficient * digit;
            temp = temp._next;
        }
    }

    public void AddPolynomials(Polynomial p) {
        if (p._head == null || _head == null) return;
        if ((p._head._coefficient == 0 && p._head._next == null)) return;
        if (this._head._coefficient == 0 && this._head._next == null) new Polynomial(p);
        Node temp, newP;
        if (this._head._degree <= p._head._degree) {
            Node tempH = p._head;
            p._head = this._head;
            this._head = tempH;
        }
        newP = this._head;
        temp = p._head;
        Node previous = null;
        while (temp != null && newP != null) {
            if (IsZero(newP._coefficient)) {
                if (newP._next == null)
                    newP = null;
                else
                    RewriteNode(newP, newP._next, newP._coefficient, newP._degree);
                continue;
            }

            double tempCoef = newP._coefficient + temp._coefficient;
            if (IsZero(tempCoef) && newP._degree == temp._degree) {
                if (newP._next != null)
                    RewriteNode(newP, newP._next._next, newP._next._coefficient, newP._next._degree);
                else
                    newP = null;
                temp = temp._next;
                continue;
            }

            if (newP._degree == temp._degree) {
                newP._coefficient = tempCoef;
                previous = newP;
                newP = newP._next;
                temp = temp._next;
            } else {
                if (newP._degree > temp._degree) {
                    previous = newP;
                    newP = newP._next;
                } else {
                    Node temp2 = previous._next;
                    previous._next = temp;
                    newP = temp2;
                    temp = temp._next;
                }
            }
        }


        if (newP == null && previous != null) previous._next = temp;
    }

    public Polynomial MultiplyPolynomials(Polynomial p) {
        if (p._head == null || _head == null) return null;
        if ((IsZero(p._head._coefficient) && p._head._next == null) || (IsZero(this._head._coefficient) && this._head._next == null))
            return new Polynomial(1, 0);
        Node head1 = this._head;
        Node head2 = p._head;
        Polynomial result = new Polynomial(head1._degree + head2._degree, head1._coefficient * head2._coefficient);
        Node head3 = result._head;
        head2 = head2._next;

        while (head1 != null) {
            if (IsZero(head1._coefficient)) {
                RewriteNode(head1, head1._next._next, head1._coefficient, head1._degree);
                continue;
            }
            while (head2 != null) {
                int tempDegree = head1._degree + head2._degree;
                double tempCoefficient = head1._coefficient * head2._coefficient;
                if (head3._degree > tempDegree) {
                    head3._next = new Node(tempDegree, tempCoefficient);
                    head3 = head3._next;
                } else if (head3._degree == tempDegree) {
                    head3._coefficient += tempCoefficient;
                }
                head2 = head2._next;
            }
            head2 = p._head;
            head1 = head1._next;
        }
        return result;
    }

    public double GetValueAtPoint(double x) {
        Node tempHead = this._head;
        int max_degree = tempHead._degree;
        double result = 0;
        for (int i = max_degree; i > 0; i--) {
            if (tempHead != null && tempHead._degree == i) {
                result += tempHead._coefficient;
                result *= x;
                tempHead = tempHead._next;
            } else result *= x;
        }
        if (tempHead != null && tempHead._degree == 0) result += tempHead._coefficient;
        return result;
    }


    public void Print() {
        if (this._head == null) return;
        Node temp = this._head;
        System.out.printf("%15.6E", temp._coefficient);
        System.out.print(" * X^" + temp._degree);
        temp = temp._next;
        while (temp != null) {
            if (IsZero(temp._coefficient)) {
                temp = temp._next;
                continue;
            }
            if (temp._coefficient >= 0) System.out.print("   +");
            System.out.printf("%15.6E", temp._coefficient);
            if (!IsZero(temp._degree)) {
                if (temp._degree == 1) System.out.print(" * X");
                else System.out.print(" * X^" + temp._degree);
            }
            temp = temp._next;
        }
        System.out.println();
    }


    public void RewriteMonomial(double c, int d) {
        this._head._coefficient = c;
        this._head._degree = d;
        this._head._next = null;
    }

    private void RewriteNode(Node node1, Node next, double c, int d) {
        node1._next = next;
        node1._degree = d;
        node1._coefficient = c;
    }

    private boolean IsZero(double x) {
        double epsilon = 1e-5;
        return (Math.abs(x) <= epsilon);
    }
}
