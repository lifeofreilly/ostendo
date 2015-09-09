package com.gmail.lifeofreilly.ostendo;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for OPReturnPeerEventListener
 */
public class OPReturnListenerTest
        extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public OPReturnListenerTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(OPReturnListenerTest.class);
    }

    /**
     * Extracts message from transaction output string
     */
    public void testExtractMessage() {
        assertEquals("69643b31323334353637383931323334353637382e75736572737573657273757365727374737463",
                OPReturnListener.extractMessage("TxOut of 0.0001 BTC (unknown type) script:RETURN PUSHDATA(40)[69643b31323334353637383931323334353637382e75736572737573657273757365727374737463]"));
    }

}
