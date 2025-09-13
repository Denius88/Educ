import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number: ");
        int n = scanner.nextInt();

        Fibonacci fib = new Fibonacci();

        System.out.print("Fibonacci: ");
        fib.printFibonacci(n);

        System.out.print("\nTriangle-like: ");
        fib.printTriangleFibonacci(n);
    }
}
