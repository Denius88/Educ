import java.util.Scanner;
/**
 * Головний клас програми, яка виводить послідовність чисел Фібоначчі
 * та ті з них, що є трикутними.
 * <p>Використовує класи {@link Fibonacci} та {@link Trianglenums}.</p>
 * @author Денис
 * @version 1.0
 */
public class Main {
    /**
     * Точка входу в програму.
     * @param args Аргументи командного рядка (не використовуються)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть число: ");
        int n = scanner.nextInt();

        Fibonacci fib = new Fibonacci();

        System.out.print("Числа Фібоначі: ");
        fib.printFibonacci(n);

        System.out.print("\nТрикутні числа: ");
        fib.printTriangleFibonacci(n);
    }
}
