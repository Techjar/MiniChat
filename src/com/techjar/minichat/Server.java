/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.techjar.minichat;

import java.io.*;
import java.net.*;
import com.techjar.network.*;
import com.techjar.network.handler.*;
import com.techjar.network.packet.*;

/**
 *
 * @author Techjar
 */
public class Server {
    public static Server server;
    public final int port;
    public NetworkServer netServer;
    public String name = "MiniChat Server";
    
    
    public Server(int port) {
        if (server != null) throw new RuntimeException("Server has already been created!");
        server = this;
        this.port = port;
    }
    
    public void start() throws IOException {
        System.out.println("Starting server...");
        netServer = new NetworkServer(null, port);
    }
}
