/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.techjar.network;

import java.net.*;
import java.io.*;
import com.techjar.network.handler.*;

/**
 *
 * @author Techjar
 */
public class NetworkAcceptThread extends Thread {
    private NetworkServer network;
    
    
    public NetworkAcceptThread(String name, NetworkServer network) {
        super(name);
        this.network = network;
    }
    
    @Override
    public void run() {
        Socket socket;
        while(network.isListening()) {
            try {
                socket = network.getServerSocket().accept();
                if (socket == null) continue;
                NetworkServer.pendingConn.add(new NetworkManager(socket, new NetHandlerLogin()));
                System.out.println(socket.getRemoteSocketAddress() + " connected.");
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
