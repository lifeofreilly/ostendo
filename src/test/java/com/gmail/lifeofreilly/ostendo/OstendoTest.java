package com.gmail.lifeofreilly.ostendo;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for Ostendo
 */
public class OstendoTest
        extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public OstendoTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(OstendoTest.class);
    }

    /**
     * Converts the raw hex message to UTF-8
     */
    public void testHexToString() throws Exception {
        assertEquals("id;12345678912345678.usersusersuserststc",
                Ostendo.hexToUTF8("69643b31323334353637383931323334353637382e75736572737573657273757365727374737463"));

    }
}
