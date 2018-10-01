package ru.testpoint;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.sandbox.Primes;

public class PrimeTests {

  @Test
  public void testPrime(){
    Assert.assertTrue(Primes.isPrimeFast(Integer.MAX_VALUE));
  }

  @Test
  public void testPrimeLong(){
    long n = Integer.MAX_VALUE;
    Assert.assertTrue(Primes.isPrime(n));
  }

  @Test (enabled = false)
  public void testNonPrime(){
    Assert.assertFalse(Primes.isPrime(Integer.MAX_VALUE - 2));
  }
}