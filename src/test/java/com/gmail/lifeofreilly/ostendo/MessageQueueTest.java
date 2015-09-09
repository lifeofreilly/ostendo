package com.gmail.lifeofreilly.ostendo;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

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
        MessageQueue messageQueue = new MessageQueue();
        messageQueue.addMessage("6a14215477656e74792062797465206469676573742e");
        assertEquals(messageQueue.takeMessage(), "6a14215477656e74792062797465206469676573742e");
    }
}
