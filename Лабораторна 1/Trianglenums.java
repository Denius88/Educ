public class Trianglenums {
    public boolean isTriangleNumber(int x) {
        if (x < 0) return false;
        double n = (-1 + Math.sqrt(1 + 8.0 * x)) / 2;
        return n == Math.floor(n);
    }
}
