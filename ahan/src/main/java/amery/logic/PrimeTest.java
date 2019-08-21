package amery.logic;

import org.junit.Test;

public class PrimeTest {

    @Test
    public void testNextPrice() {
        findNextPrime(-5);
        findNextPrime(20);
        findNextPrime(3001);
        findNextPrime(8888);
        findNextPrime(88888);
    }

    static int findNextPrime(int input) {
        int i, j, minPrime;
        j = minPrime = 2;

        if (input <= minPrime) {
            System.out.println(minPrime);
            return minPrime;
        }
        for (i = input + 1; i < input + 2; i++) {
            for (j = minPrime; j <= i; j++) {
                if (i % j == 0) {
                    break;
                }
            }
            if (j > i / minPrime) {
                System.out.println(j);
                return j;
            }
        }
        if (j == minPrime || j <= input) {
            return findNextPrime(input + 1);
        }
        System.out.println(minPrime);
        return minPrime;
    }
}
