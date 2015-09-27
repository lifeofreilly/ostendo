package com.gmail.lifeofreilly.ostendo;

import org.bitcoinj.core.BlockChain;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.PeerEventListener;
import org.bitcoinj.core.PeerGroup;
import org.bitcoinj.net.discovery.DnsDiscovery;
import org.bitcoinj.params.MainNetParams;
import org.bitcoinj.store.BlockStoreException;
import org.bitcoinj.store.MemoryBlockStore;

/**
 * A bitcoin network peer configured to use a custom listener
 * This leverages the production network. To use testnet please see:
 * https://bitcoinj.github.io/javadoc/0.12/org/bitcoinj/core/NetworkParameters.html
 */
class PeerListener {
    private static final NetworkParameters NET_PARAMS = MainNetParams.get();
    private final PeerEventListener peerEventListener;

    /**
     * Sole constructor for PeerListener
     *
     * @param peerEventListener the custom peer event listener you would like to use
     */
    public PeerListener(PeerEventListener peerEventListener) {
        this.peerEventListener = peerEventListener;
    }

    /**
     * Starts a bitcoin network peer and begins to listen
     *
     * @throws BlockStoreException
     */
    public void start() throws BlockStoreException {
        BlockChain blockChain = new BlockChain(NET_PARAMS, new MemoryBlockStore(NET_PARAMS));
        PeerGroup peerGroup = new PeerGroup(NET_PARAMS, blockChain);
        peerGroup.addPeerDiscovery(new DnsDiscovery(NET_PARAMS));
        peerGroup.addEventListener(peerEventListener);
        peerGroup.startAsync();


    }
}
