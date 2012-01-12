/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.techjar.network.handler;

import com.techjar.minichat.Client;
import com.techjar.network.*;
import com.techjar.network.packet.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Techjar
 */
public class NetHandlerClient extends NetHandler {
    @Override
    public boolean isServer() {
        return false;
    }
    
    @Override
    public void handleNetworkShutdown(String reason, Object[] info) {
        Client.client.gui.toggleState(false);
        if (info.length >= 1) {
            if (info[0] instanceof Boolean) return;
            StringBuilder sb = new StringBuilder(reason).append('\n').append('\n');
            for (int i = 0; i < info.length; i++) { sb.append(info[i].toString()); if (i >= info.length - 1) sb.append('\n'); }
            Client.showMessageDialog(sb.toString(), "Disconnected", javax.swing.JOptionPane.WARNING_MESSAGE);
        }
        else {
            Client.showMessageDialog(reason, "Disconnected", javax.swing.JOptionPane.WARNING_MESSAGE);
        }
    }
    
    @Override
    public void handleLogin(Packet1Login packet) {
        Client.client.gui.toggleState(true);
    }
    
    @Override
    public void handleChat(Packet3Chat packet) {
        Client.client.gui.addChatMessage(packet.message);
    }
    
    @Override
    public void handleUserList(Packet4UserList packet) {
        if (packet.add) Client.client.gui.addUser(packet.name);
        else Client.client.gui.removeUser(packet.name);
    }
    
    @Override
    public void handleNameChange(Packet5NameChange packet) {
        if (packet.accepted) {
            Client.client.username = packet.name;
            Client.showMessageDialog("Your username has been changed.", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
        else Client.showMessageDialog("That username is already taken.", "Invalid Username", JOptionPane.WARNING_MESSAGE);
    }
    
    @Override
    public void handleDisconnect(Packet255Disconnect packet) {
        netManager.shutdown(packet.reason);
        Client.client.gui.toggleState(false);
        Client.showMessageDialog(packet.reason, "Disconnected", JOptionPane.INFORMATION_MESSAGE);
    }
}
