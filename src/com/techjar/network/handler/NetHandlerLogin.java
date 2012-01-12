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
        netManager.user = new User(netManager, packet.name);
        
        if (packet.version < Main.version) {
            netManager.user.kick("Outdated client!");
            return;
        }
        if (packet.version > Main.version) {
            netManager.user.kick("Outdated server!");
            return;
        }
        if (User.findExact(packet.name) != null) {
            netManager.user.kick("Username is already taken!");
            return;
        }
        
        netManager.queuePacket(new Packet1Login(Main.version, Server.server.name, false));
        netManager.setNetHandler(new NetHandlerServer());
        User.users.add(netManager.user);
        
        for (int i = 0; i < User.users.size(); i++) {
            User user = User.users.get(i);
            if (!user.username.equals(packet.name)) {
                netManager.queuePacket(new Packet4UserList(user.username, true));
            }
        }
        User.globalPacket(new Packet4UserList(packet.name, true));
        User.globalMessage(new StringBuilder(packet.name).append(" has joined the chat.").toString());
    }
    
    @Override
    public void handlePassword(Packet2Password packet) {
        registerPacket(packet);
    }
}
