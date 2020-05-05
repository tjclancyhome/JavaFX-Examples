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

     public static long fib(int n) {
        if (n == 0) {
            return 0;
        }
        long[] f = new long[n + 1];
        f[0] = 0;
        f[1] = 1;

        for (int i = 2; i <= n; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f[n];
    }
}
Algos.gcd(12, 13);
Algos.gcd(12, 24);
int x = Algos.gcd(2, 4);