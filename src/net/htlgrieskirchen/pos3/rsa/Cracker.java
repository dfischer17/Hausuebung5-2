/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.htlgrieskirchen.pos3.rsa;

import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.Callable;

/**
 *
 * @author Daniel Fischer
 */
public class Cracker implements Callable<Pair<Integer>> {

    final private List<Integer> numbers;
    final private BigInteger N;

    public Cracker(List<Integer> numbers, BigInteger N) {
        this.numbers = numbers;
        this.N = N;
    }

    @Override
    public Pair<Integer> call() {
        int p = 0;
        int q = 0;
        
        for (int primeNumber : numbers) {
            for (int primeNumber2 : numbers) {
                if (primeNumber * primeNumber2 == N.intValue()) {
                    p = primeNumber;
                    q = primeNumber2;
                    break;
                }
            }
        }
        return new Pair(p, q);
    }
}
