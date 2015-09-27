package com.gmail.lifeofreilly.ostendo;

import org.apache.log4j.Logger;
import org.bitcoinj.core.Peer;
import org.bitcoinj.core.AbstractPeerEventListener;
import org.bitcoinj.core.Transaction;

import java.util.concurrent.BlockingQueue;

/**
 * A custom PeerEventListener that adds all OP_RETURN messages to a message queue
 */
class OPReturnListener extends AbstractPeerEventListener {
    private final BlockingQueue<String> messageQueue;
    private static final Logger log = Logger.getLogger(OPReturnListener.class);
    private static final String OP_RETURN_CODE = "RETURN PUSHDATA";

    /**
     * Sole constructor for OPReturnListener
     *
     * @param messageQueue new messages will be added to this queue
     */
    public OPReturnListener(final BlockingQueue<String> messageQueue) {
        this.messageQueue = messageQueue;
    }

    @Override
    public void onTransaction(Peer peer, Transaction transaction) {
        transaction.getOutputs().stream()
                .filter(transactionOutput -> transactionOutput.toString().contains(OP_RETURN_CODE))
                .forEach(transactionOutput -> {
                    messageQueue.add(extractMessage(transactionOutput.toString()));
                    log.info("Found a message in Transaction: " + transaction.toString());
                });
    }

    /**
     * Extracts the message content from the output string
     *
     * @param transactionOutput the output string
     * @return the extracted message
     */
    public static String extractMessage(String transactionOutput) {
        return transactionOutput.substring(transactionOutput.indexOf("[") + 1, transactionOutput.indexOf("]"));
    }

}
