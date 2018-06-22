package network;

import config.PeerConfig;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Listener {
    private ServerSocket serverSocket;
    private PeerConfig peerConfig;

    public Listener(PeerConfig peerConfig) {
        this.peerConfig = peerConfig;
    }

    public void init(){
        try {
            serverSocket = new ServerSocket(this.peerConfig.getListenerPort());
            Socket clientSocket = serverSocket.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
