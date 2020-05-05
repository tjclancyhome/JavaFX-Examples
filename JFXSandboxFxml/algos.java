
class Algos {

    public static int gcd(int a, int b) {
        if (a >= b && b >= 0) {
            if (b == 0) {
                return a;
            }
            return gcd(b, a % b);
        }
        return -1;
    }
}
