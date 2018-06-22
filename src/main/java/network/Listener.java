package network;

import config.NodeConfig;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

class Listener {
    private NodeConfig nodeConfig;

    Listener(NodeConfig nodeConfig) {
        this.nodeConfig = nodeConfig;
    }

    void init(){
        //add netty server channels
    }
}
