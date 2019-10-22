/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.htlgrieskirchen.pos3.rsa;

import java.math.BigInteger;
import org.junit.Test;
import static org.junit.Assert.*;

public class RSAAttackTest {
    /**
     * Für den Test im Überprüfungsprogramm werden andere Zahlen verwendet!
     */
    
    /*
    @Test
    public void testDeterminePrimesSerial() {
        RSAAttack rsaAttack = new RSAAttack(new BigInteger("997241904391"));
        
        Pair<Integer> result = rsaAttack.determinePrimesSerial();
        
        assertEquals((int)998617, (int)result.getFirst());
        assertEquals((int)998623, (int)result.getSecond());
    }
    */
    
    @Test
    public void testDeterminePrimesSerial2() {
        RSAAttack rsaAttack = new RSAAttack(new BigInteger("10"));
        
        Pair<Integer> result = rsaAttack.determinePrimesSerial();
        
        assertEquals((int)2, (int)result.getFirst());
        assertEquals((int)5, (int)result.getSecond());
    }
        
    @Test
    public void testDeterminePrimesParalell() throws Exception {
        RSAAttack rsaAttack = new RSAAttack(new BigInteger("997241904391"));
        
        Pair<Integer> result = rsaAttack.determinePrimesParallel();
        
        assertEquals((int)998617, (int)result.getFirst());
        assertEquals((int)998623, (int)result.getSecond());
    }    
}
