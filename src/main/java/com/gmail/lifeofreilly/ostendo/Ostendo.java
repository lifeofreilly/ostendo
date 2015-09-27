package com.gmail.lifeofreilly.ostendo;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.log4j.Logger;
import org.bitcoinj.store.BlockStoreException;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class Ostendo {
    private static final Logger log = Logger.getLogger(Ostendo.class);

    /**
     * A command line application for listening to OP_RETURN output messages on the Bitcoin blockchain.
     * This application leverages the production network.
     * To use the test network, please see:{@link org.bitcoinj.core.NetworkParameters}
     *
     * @param args none
     */
    public static void main(String[] args) {
        final BlockingQueue<String> messageQueue = new LinkedBlockingQueue<String>();
        OPReturnListener opReturnListener = new OPReturnListener(messageQueue);

        try {
            PeerListener peerListener = new PeerListener(opReturnListener);
            peerListener.start();
            System.out.println("Log is available at: " + System.getProperty("user.dir") + "/logs/error.log");
            log.info("Listening for OP_Return messages...");
            System.out.println("Listening for OP_Return messages...");
            System.out.println("| Hex Value | UTF-8 Value |");

            while (true) {
                try {
                    String message = messageQueue.take();
                    System.out.println("| " + message + " | " + hexToUTF8(message) + " |");
                } catch (DecoderException e) {
                    log.error("Unable to decode the message: " + e);
                }
            }
        } catch (BlockStoreException | InterruptedException e) {
            log.error(e.getStackTrace());
            System.out.println("Application Exiting. See log for more information.");
            System.exit(-1);
        }

    }

    /**
     * Converts hex to a UTF-8 string
     *
     * @param hex the string to decode
     * @return the decoded string
     * @throws DecoderException
     */
    public static String hexToUTF8(String hex) throws DecoderException {
        return new String(Hex.decodeHex(hex.toCharArray()));
    }

}
