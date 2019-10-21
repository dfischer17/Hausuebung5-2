/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.htlgrieskirchen.pos3.rsa;

import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

/**
 * Beantworten Sie die Laufzeitfrage INNERHALB der folgenden Tags:
 * <answer>
 *  Antwort:
 * </answer>
 */
public class RSAAttack {
    public RSAAttack(BigInteger n) {
        // implement this
    }
    
    public Pair<Integer> determinePrimesSerial() {
        //implement this
        return null;
    }
    
    /*
     * Verwenden Sie einen ExecutorService. Das Überprüfungsprogramm schaut ob 
     * in dieser Methode ein ExecutorService verwendet wird!
     */
    public Pair<Integer> determinePrimesParallel() throws InterruptedException, ExecutionException {
        //implement this
        return null;
    }
    
    // Bonus
    public BigInteger determineD() throws InterruptedException, ExecutionException {
//        Pair<Integer> primePair = determinePrimesParallel();
//        // As phi is now a very large number, the PrimeGenerator is not sufficien
//        // any more, see generatePrimeNumbers(int n) while computing e in RSAEngine.
//        RSAEngine rsa = new RSAEngine(primePair.getFirst(), primePair.getSecond());
//        return rsa.getD();

//        Wenn diese Methode Implementiert wird, einfach in der nächsten Übung melden,
//        damit die Punkte für diese Methode hinzuaddiert werden!
        return null;
    }  
}

