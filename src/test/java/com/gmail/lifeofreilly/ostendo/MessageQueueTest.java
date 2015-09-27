package com.gmail.lifeofreilly.ostendo;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Unit test for MessageQueue
 */
public class MessageQueueTest
        extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public MessageQueueTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(MessageQueueTest.class);
    }

    /**
     * Add and remove message from MessageQueue
     */
    public void testMessageQueue() throws Exception {
        final BlockingQueue<String> messageQueue = new LinkedBlockingQueue<String>();
        messageQueue.add("6a14215477656e74792062797465206469676573742e");
        assertEquals(messageQueue.take(), "6a14215477656e74792062797465206469676573742e");
    }
}
