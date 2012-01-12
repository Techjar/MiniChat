/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.techjar.network.handler;

import com.techjar.minichat.Main;
import com.techjar.minichat.Server;
import com.techjar.minichat.server.User;
import com.techjar.network.*;
import com.techjar.network.packet.*;

/**
 *
 * @author Techjar
 */
public class NetHandlerLogin extends NetHandler {
    @Override
    public boolean isServer() {
        return true;
    }
    
    @Override
    public void handleNetworkShutdown(String reason, Object[] info) {
        System.out.println(reason);
        for (int i = 0; i < info.length; i++) { System.out.println(info[i].toString()); }
    }
    
    @Override
    public void handleLogin(Packet1Login packet) {
        NetworkServer.pendingConn.remove(netManager);
        
        if (packet.version < Main.version) {
            netManager.shutdown("Outdated client!");
            return;
        }
        if (packet.version > Main.version) {
            netManager.shutdown("Outdated server!");
            return;
        }
        if (User.find(packet.name) != null) {
            netManager.shutdown("Username is already taken!");
            return;
        }
        
        netManager.queuePacket(new Packet1Login(Main.version, Server.server.name, false));
        netManager.setNetHandler(new NetHandlerServer());
        netManager.user = new User(netManager, packet.name);
        User.users.add(netManager.user);
        
        User.globalPacket(new Packet4UserList(packet.name, true));
        User.globalMessage(new StringBuilder(packet.name).append(" has joined the chat.").toString());
    }
    
    @Override
    public void handlePassword(Packet2Password packet) {
        registerPacket(packet);
    }
}
