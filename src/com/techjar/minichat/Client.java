/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.techjar.minichat;

import java.io.*;
import java.net.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import com.techjar.network.*;
import com.techjar.network.handler.*;
import com.techjar.network.packet.*;
import com.techjar.minichat.gui.*;

/**
 *
 * @author Techjar
 */
public class Client {
    public static Client client;
    public GUIClient gui;
    public NetworkManager netManager;
    public String username = "Guest-" + Main.random.nextInt(Short.MAX_VALUE);
    
    
    public Client() {
        if (client != null) throw new RuntimeException("Client has already been created!");
        client = this;
    }
    
    public void connect(String ip, int port) throws IOException, UnknownHostException {
        if (netManager != null) netManager.shutdown("Connecting to new server!", true);
        netManager = new NetworkManager(new Socket(InetAddress.getByName(ip), port), new NetHandlerClient());
        netManager.queuePacket(new Packet1Login(Main.version, username, false));
    }
    
    public void sendChatMessage(String message) {
        netManager.queuePacket(new Packet3Chat(message));
    }
    
    public void loadMainGUI() {
        if (gui != null) gui.dispose();
        gui = new GUIClient(this);
    }
    
    public static void showMessageDialog(Object message, String title, int messageType) {
        JOptionPane.showMessageDialog(client.gui, message, title, messageType);
    }
}
