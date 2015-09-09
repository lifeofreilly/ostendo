package com.gmail.lifeofreilly.ostendo;

import org.apache.log4j.Logger;
import org.bitcoinj.core.Block;
import org.bitcoinj.core.FilteredBlock;
import org.bitcoinj.core.GetDataMessage;
import org.bitcoinj.core.TransactionOutput;
import org.bitcoinj.core.Message;
import org.bitcoinj.core.Peer;
import org.bitcoinj.core.PeerAddress;
import org.bitcoinj.core.PeerEventListener;
import org.bitcoinj.core.Transaction;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Set;

/**
 * A custom PeerEventListener that adds all OP_RETURN messages to a message queue
 */
class OPReturnListener implements PeerEventListener {
    private final MessageQueue messageQueue;
    private static final Logger log = Logger.getLogger(OPReturnListener.class);

    /**
     * Sole constructor for OPReturnListener
     *
     * @param messageQueue new messages will be added to this queue
     */
    public OPReturnListener(final MessageQueue messageQueue) {
        this.messageQueue = messageQueue;
    }

    @Override
    public void onTransaction(Peer peer, Transaction transaction) {
        for (TransactionOutput transactionOutput : transaction.getOutputs()) {
            if (transactionOutput.toString().contains("RETURN PUSHDATA")) {
                String message = extractMessage(transactionOutput.toString());
                messageQueue.addMessage(message);
                log.info("Found a message in Transaction: " + transaction.toString());
            }
        }
    }

    @Override
    public void onPeersDiscovered(Set<PeerAddress> set) {

    }

    @Override
    public void onBlocksDownloaded(Peer peer, Block block, FilteredBlock filteredBlock, int i) {

    }

    @Override
    public void onChainDownloadStarted(Peer peer, int i) {

    }

    @Override
    public void onPeerConnected(Peer peer, int i) {

    }

    @Override
    public void onPeerDisconnected(Peer peer, int i) {

    }

    @Override
    public Message onPreMessageReceived(Peer peer, Message message) {
        return null;
    }

    @Nullable
    @Override
    public List<Message> getData(Peer peer, GetDataMessage getDataMessage) {
        return null;
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
