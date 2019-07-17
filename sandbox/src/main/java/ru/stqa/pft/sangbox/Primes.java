package ru.stqa.pft.sangbox;

public class Primes {

    public static boolean isPrime(int n) {
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isPrime(long n) {
        for (long i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isPrimeFast(int n) {
        for (int i = 2; i < n / 2; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isPrimeFast2(int n) {
        int m = (int) Math.sqrt(n);
        for (int i = 2; i < m / 2; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
