package ru.stqa.pft.sandbox;

import org.junit.Assert;
import org.junit.Test;
import ru.stqa.pft.sangbox.Primes;

public class PrimeTests {

    @Test
    public void testPrimes() {
        Assert.assertTrue(Primes.isPrime(Integer.MAX_VALUE));
    }

    @Test
    public void testNonPrimes() {
        Assert.assertFalse(Primes.isPrime(Integer.MAX_VALUE - 2));
    }

    @Test (enabled = false)
    public void testPrimesLong() {
        long n = Integer.MAX_VALUE;
        Assert.assertTrue(Primes.isPrime(n));
    }

    @Test
    public void testPrimesFast() {
        Assert.assertTrue(Primes.isPrimeFast(Integer.MAX_VALUE));
    }

    @Test
    public void testPrimesFast2() {
        Assert.assertTrue(Primes.isPrimeFast2(Integer.MAX_VALUE));
    }
}
