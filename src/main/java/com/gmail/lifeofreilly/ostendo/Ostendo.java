package com.gmail.lifeofreilly.ostendo;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.log4j.Logger;
import org.bitcoinj.store.BlockStoreException;

class Ostendo {
    private static final Logger log = Logger.getLogger(Ostendo.class);

    /**
     * A command line application for listening to OP_RETURN output messages on the Bitcoin blockchain.
     * This application leverages the production network. To use the test network, please see:
     * https://bitcoinj.github.io/javadoc/0.12/org/bitcoinj/core/NetworkParameters.html
     *
     * @param args none
     */
    public static void main(String[] args) {
        MessageQueue messageQueue = new MessageQueue();
        OPReturnListener OPReturnListener = new OPReturnListener(messageQueue);

        try {
            PeerListener peerListener = new PeerListener(OPReturnListener);
            peerListener.start();
            log.info("Listening for OP_Return messages...");
            System.out.println("Additional information is available at: " + System.getProperty("user.dir") + "/logs/error.log");

            String leftAlignFormat = "| %-60s | %-30s |%n";
            System.out.format("+--------------------------------------------------------------+--------------------------+%n");
            System.out.printf("| Hex Value                                                    | UTF-8 Value              |%n");
            System.out.format("+--------------------------------------------------------------+--------------------------+%n");

            while (true) {
                try {
                    String message = messageQueue.takeMessage();
                    System.out.format(leftAlignFormat, message, hexToUTF8(message));
                } catch (DecoderException e) {
                    log.error("Unable to decode the message: " + e);
                } catch (InterruptedException e) {
                    log.error(e.getStackTrace());
                }
            }
        } catch (BlockStoreException e) {
            log.error("Failed to initialize the blockchain: " + e);
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
