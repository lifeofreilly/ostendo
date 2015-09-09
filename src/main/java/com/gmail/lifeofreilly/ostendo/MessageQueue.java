package com.gmail.lifeofreilly.ostendo;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * A simple blocking message queue
 */
class MessageQueue {
    private final BlockingQueue<String> messageQueue = new LinkedBlockingQueue<String>();

    /**
     * Add a message to the queue to be processed.
     *
     * @param message the message.
     */
    public void addMessage(final String message) {
        messageQueue.add(message);
    }

    /**
     * Removes and returns the head message in the queue, waiting if necessary until an element becomes available.
     *
     * @return the message.
     */
    public String takeMessage() throws InterruptedException {
        return messageQueue.take();
    }


}
