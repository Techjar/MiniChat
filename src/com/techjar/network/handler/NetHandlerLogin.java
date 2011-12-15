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
        registerPacket(packet);
    }
    
    @Override
    public void handlePassword(Packet2Password packet) {
        registerPacket(packet);
    }
}
