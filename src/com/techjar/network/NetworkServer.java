/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.techjar.network;

import com.techjar.minichat.Main;
import com.techjar.network.packet.Packet1Login;
import java.net.*;
import java.io.*;
import java.util.*;

/**
 *
 * @author Techjar
 */
public class NetworkServer {
    public static final List<NetworkManager> pendingConn = Collections.synchronizedList(new ArrayList<NetworkManager>());
    
    private volatile boolean isListening;
    private ServerSocket serverSocket;
    private Thread acceptThread;
    
    
    public NetworkServer(InetAddress ip, int port) throws IOException {
        isListening = false;
        serverSocket = new ServerSocket(port, 0, ip);
        serverSocket.setPerformancePreferences(0, 2, 1);
        isListening = true;
        System.out.println("Started listening on port " + port);
        acceptThread = new NetworkAcceptThread("Network Accept Thread", this);
        acceptThread.start();
    }
    
    public ServerSocket getServerSocket() {
        return serverSocket;
    }
    
    public boolean isListening() {
        return isListening;
    }
    
    public void shutdown() throws IOException {
        isListening = false;
        serverSocket.close();
    }
}
