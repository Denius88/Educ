/**
 * Клас для генерації чисел Фібоначчі та їх фільтрації.
 */
public class Fibonacci {

    /**
     * Виводить перші {@code n} чисел Фібоначчі.
     * @param n Кількість чисел для виводу
     */
    public void printFibonacci(int n) {
        int a = 0, b = 1;
        for (int i = 0; i < n; i++) {
            System.out.print(a + " ");
            int temp = a + b;
            a = b;
            b = temp;
        }
    }

    /**
     * Виводить ті числа Фібоначчі з перших {@code n}, які є трикутними.
     * <p>Використовує {@link Trianglenums#isTriangleNumber(int)} для перевірки.</p>
     * @param n Кількість чисел Фібоначчі для перевірки
     */
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
