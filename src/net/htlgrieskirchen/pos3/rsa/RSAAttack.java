/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.htlgrieskirchen.pos3.rsa;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Beantworten Sie die Laufzeitfrage INNERHALB der folgenden Tags:
 * <answer>
 * Antwort: Mit dem Parameter 3127 brauchen beide Methoden ca. 10ms, dies liegt daran, dass keine groeßerer Zahl fuer N verwendet werden konnte.
 * </answer>
 */
public class RSAAttack {

    private BigInteger n;
    private List<Integer> primeNumbers;
    private int amountOfRunnables = 4;

    public RSAAttack(BigInteger n) {
        this.n = n;
        // prime numbers from 1 to n
        primeNumbers = IntStream.range(1, n.intValue()).filter(a -> PrimeGenerator.isPrime(a)).boxed().collect(Collectors.toList());
    }

    // loeschen!
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        //RSAAttack rsa = new RSAAttack(BigInteger.valueOf(33616579)); //143
        
        // Geschwindigkeitsunterschied
        benchmark();                
    }

    public Pair<Integer> determinePrimesSerial() {
        int p = 0;
        int q = 0;

        for (int primeNumber : primeNumbers) {
            for (int primeNumber2 : primeNumbers) {
                if (primeNumber * primeNumber2 == n.intValue()) {
                    p = primeNumber;
                    q = primeNumber2;
                    break;
                }
            }
        }
        return new Pair(p, q);
    }

    /*
     * Verwenden Sie einen ExecutorService. Das Überprüfungsprogramm schaut ob 
     * in dieser Methode ein ExecutorService verwendet wird!
     */
    public Pair<Integer> determinePrimesParallel() throws InterruptedException, ExecutionException {
        //primeNumbers gleichmaessig in Listen aufteilen
        int chunksize = primeNumbers.size() / amountOfRunnables;
        List<List<Integer>> sublists = spread(primeNumbers, chunksize, n.intValue());

        // Callables erstellen und in Liste speichern
        List<Callable<Pair<Integer>>> callables = new ArrayList<>();

        for (List<Integer> sublist : sublists) {
            callables.add(new Cracker(sublist, n));
        }

        // Liste von Callables von Executorservice ausfuehren lassen
        ExecutorService executor = Executors.newWorkStealingPool();

        // Erste/einzig richitge Loesung zurueckgeben
        Pair<Integer> result = executor.invokeAny(callables);
        
        return result;
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

    private List<List<Integer>> spread(List<Integer> numbers, int chunksize, int max) {
        List<List<Integer>> sublists = new ArrayList<>();

        // Sublists mit groesse chunksize erstellen
        for (int i = chunksize; i < numbers.size(); i += chunksize) {
            sublists.add(numbers.subList(i - chunksize, i));
        }

        return sublists;
    }

    private static void benchmark() throws InterruptedException, ExecutionException {
        RSAAttack instance = new RSAAttack(BigInteger.valueOf(3127));
        
        
        long serialStart = System.currentTimeMillis();
        Pair<Integer> serial = instance.determinePrimesSerial();
        long serialEnd = System.currentTimeMillis();        
        long serialDuration = serialEnd - serialStart;
        System.out.println("determinePrimesSerial took " + serialDuration + "ms");
        
        
        long parallelStart = System.currentTimeMillis();
        Pair<Integer> parallel = instance.determinePrimesParallel();
        long parallelEnd = System.currentTimeMillis();
        long parallelDuration = parallelEnd - parallelStart;                 
        System.out.println("determinePrimesParallel took " + parallelDuration +"ms");
    }
}
