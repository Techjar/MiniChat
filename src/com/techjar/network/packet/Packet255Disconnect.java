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
public class Packet255Disconnect extends Packet {
    public String reason;
    
    
    public Packet255Disconnect() {
    }
    
    public Packet255Disconnect(String reason) {
        this.reason = reason;
    }
    
    @Override
    public void readData(DataInputStream stream) throws IOException {
        reason = readString(stream, Short.MAX_VALUE);
    }

    @Override
    public void writeData(DataOutputStream stream) throws IOException {
        writeString(stream, reason);
    }

    @Override
    public void process(NetHandler handler) {
        handler.handleDisconnect(this);
    }

    @Override
    public int getSize() {
        return 2 + (reason.length() * 2);
    }
}
