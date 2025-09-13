public class Fibonacci {
    public void printFibonacci(int n) {
        int a = 0, b = 1;
        for (int i = 0; i < n; i++) {
            System.out.print(a + " ");
            int temp = a + b;
            a = b;
            b = temp;
        }
    }

    public void printTriangleFibonacci(int n) {
        int a = 0, b = 1;
        Trianglenums checker = new Trianglenums();
        for (int i = 0; i < n; i++) {
            if (checker.isTriangleNumber(a)) {
                System.out.print(a + " ");
            }
            int temp = a + b;
            a = b;
            b = temp;
        }
    }
}
