/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.techjar.network.packet;

import com.techjar.network.handler.NetHandler;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 *
 * @author Techjar
 */
public class Packet3Chat extends Packet {
    public String message;
    
    
    public Packet3Chat() {
    }
    
    public Packet3Chat(String message) {
        this.message = message;
    }
    
    @Override
    public void readData(DataInputStream stream) throws IOException {
        message = readString(stream, Short.MAX_VALUE);
    }

    @Override
    public void writeData(DataOutputStream stream) throws IOException {
        writeString(stream, message);
    }

    @Override
    public void process(NetHandler handler) {
        handler.handleChat(this);
    }

    @Override
    public int getSize() {
        return 2 + (message.length() * 2);
    }
}
