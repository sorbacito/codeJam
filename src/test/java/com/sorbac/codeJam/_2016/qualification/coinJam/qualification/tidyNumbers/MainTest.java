package com.sorbac.codeJam._2016.qualification.coinJam;

import org.testng.annotations.Test;

import java.math.BigInteger;
import java.util.List;

import static org.testng.Assert.*;

public class MainTest {

    @Test
    public void testSmall() throws Exception {
        List<String> myStrings = new Main(16).generateSpecialCoins(50);
        assert myStrings.size() == 50;
        for (String myCoin : myStrings) {
            testAllValues(myCoin);
        }
    }

    @Test
    public void testLarge() throws Exception {
        List<String> myStrings = new Main(32).generateSpecialCoins(500);
        assert myStrings.size() == 500;
        for (String myCoin : myStrings) {
            testAllValues(myCoin);
        }
    }

    private void testAllValues(String myCoin) {
        assertEquals(generateSum(myCoin, 2).mod(new BigInteger(String.valueOf(3))).intValue(), 0, myCoin);
        assertEquals(generateSum(myCoin, 3).mod(new BigInteger(String.valueOf(2))).intValue(), 0, myCoin);
        assertEquals(generateSum(myCoin, 4).mod(new BigInteger(String.valueOf(5))).intValue(), 0, myCoin);
        assertEquals(generateSum(myCoin, 5).mod(new BigInteger(String.valueOf(2))).intValue(), 0, myCoin);
        assertEquals(generateSum(myCoin, 6).mod(new BigInteger(String.valueOf(7))).intValue(), 0, myCoin);
        assertEquals(generateSum(myCoin, 7).mod(new BigInteger(String.valueOf(2))).intValue(), 0, myCoin);
        assertEquals(generateSum(myCoin, 8).mod(new BigInteger(String.valueOf(3))).intValue(), 0, myCoin);
        assertEquals(generateSum(myCoin, 9).mod(new BigInteger(String.valueOf(2))).intValue(), 0, myCoin);
        assertEquals(generateSum(myCoin, 10).mod(new BigInteger(String.valueOf(11))).intValue(), 0, myCoin);
    }

    private BigInteger generateSum(String aCoin, int aI) {
        String myBase = String.valueOf(aI);
        BigInteger myBigInteger = BigInteger.ZERO;
        for (int i = 0; i < aCoin.length(); i++) {
            if (aCoin.charAt(i) == '1') {
                myBigInteger = myBigInteger.add(new BigInteger(myBase).pow(aCoin.length() - 1 - i));
            }
        }
        return myBigInteger;
    }


}