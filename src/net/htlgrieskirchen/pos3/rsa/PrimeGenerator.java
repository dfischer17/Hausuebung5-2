/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.htlgrieskirchen.pos3.rsa;

import java.util.stream.IntStream;

public class PrimeGenerator {

    public int[] generatePrimeNumbers(int n) {
        IntStream numbers = IntStream.range(1, n);
        return numbers.filter(nr -> isPrime(nr)).toArray();
    }

    public int[] generatePrimeNumbers(int s, int n) {
        IntStream numbers = IntStream.range(s, n);
        return numbers.filter(nr -> isPrime(nr)).toArray();
    }

    public static boolean isPrime(int value) {
        if (value < 2) {
            return false;
        } else {
            for (int i = 2; i < value; i++) {
                if (value % i == 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
