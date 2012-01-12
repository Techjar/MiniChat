/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.techjar.minichat.server;

import com.techjar.network.NetworkManager;
import com.techjar.network.packet.Packet;
import com.techjar.network.packet.Packet255Disconnect;
import com.techjar.network.packet.Packet3Chat;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Techjar
 */
public class User {
    public static final List<User> users = Collections.synchronizedList(new ArrayList<User>());
    
    public final NetworkManager netManager;
    public String username;
    
    
    public User(NetworkManager netManager, String username) {
        this.netManager = netManager;
        this.username = username;
    }
    
    public static User find(String username) {
        username = username.toLowerCase();
        User tempUser = null; boolean returnNull = false;
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            if (user.username.toLowerCase().equals(username)) return user;
            if (user.username.toLowerCase().indexOf(username) != -1) {
                if (tempUser == null) tempUser = user;
                else { returnNull = true; break; }
            }
        }
        if (returnNull) return null;
        return tempUser;
    }
    
    public static User findExact(String username) {
        username = username.toLowerCase();
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            if (user.username.toLowerCase().equals(username)) return user;
        }
        return null;
    }
    
    public static void globalPacket(Packet packet) {
        for (int i = 0; i < users.size(); i++) {
            users.get(i).netManager.queuePacket(packet);
        }
    }
    
    public static void globalMessage(String message) {
        for (int i = 0; i < users.size(); i++) {
            users.get(i).sendMessage(message);
        }
    }
    
    public void sendMessage(String message) {
        netManager.queuePacket(new Packet3Chat(message));
    }
    
    public void kick(String reason) {
        try {
            Packet.writePacket(netManager.getOutputStream(), new Packet255Disconnect(reason));
        }
        catch (IOException ex) { }
        
        users.remove(this);
        netManager.user = null;
        String allMsg = new StringBuilder(username).append(" was kicked. (").append(reason).append(')').toString();
        netManager.shutdown(allMsg);
        globalMessage(allMsg);
    }
}
