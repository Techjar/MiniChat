/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.techjar.network.handler;

import com.techjar.network.*;
import com.techjar.network.packet.*;

/**
 *
 * @author Techjar
 */
public abstract class NetHandler {
    public NetworkManager netManager;
    
    
    public NetHandler() {
    }
    
    public abstract boolean isServer();
    
    public void handleNetworkShutdown(String reason, Object[] info) {
    }
    
    public void handleKeepAlive(Packet0KeepAlive packet) {
    }
    
    public void handleLogin(Packet1Login packet) {
        registerPacket(packet);
    }
    
    public void handlePassword(Packet2Password packet) {
        registerPacket(packet);
    }
    
    public void handleChat(Packet3Chat packet) {
        registerPacket(packet);
    }
    
    public void handleUserList(Packet4UserList packet) {
        registerPacket(packet);
    }
    
    public void handleNameChange(Packet5NameChange packet) {
        registerPacket(packet);
    }
    
    public void handleDisconnect(Packet255Disconnect packet) {
        netManager.shutdown(packet.reason);
    }
    
    protected void registerPacket(Packet packet) {
        netManager.shutdown("Protocol error!", new StringBuilder("Attempted to handle unexpected ").append(packet.getClass().getName()).append('.'));
    }
}
