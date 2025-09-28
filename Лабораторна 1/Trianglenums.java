/**
 * Клас для перевірки, чи є число трикутним.
 */
public class Trianglenums {

    /**
     * Перевіряє, чи є число {@code x} трикутним.
     * <p>Трикутне число має вигляд {@code T_n = n(n+1)/2}.</p>
     * @param x Число для перевірки
     * @return {@code true}, якщо число трикутне; {@code false} — інакше
     */
    public boolean isTriangleNumber(int x) {
        if (x < 0) return false;
        double n = (-1 + Math.sqrt(1 + 8.0 * x)) / 2;
        return n == Math.floor(n);
    }
}
