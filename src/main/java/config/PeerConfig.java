package config;

import network.Neighbour;

import java.util.ArrayList;
import java.util.List;

public class PeerConfig {
    private final Long peerID;
    private int listenerPort;
    private List<Neighbour> neighbours;


    public PeerConfig(Long peerID) {
        this.peerID = peerID;
        this.neighbours = new ArrayList<>();
    }

    public Long getPeerID() {
        return peerID;
    }

    public final void setListenerPort(int listenerPort) {
        this.listenerPort = listenerPort;
    }

    public int getListenerPort() {
        return listenerPort;
    }

    public void addNeighbour(Neighbour neighbour) {
        this.neighbours.add(neighbour);
    }

    public List<Neighbour> getNeighbours() {
        return neighbours;
    }
}
