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
public class Packet5NameChange extends Packet {
    public String name;
    public boolean accepted;
    
    
    public Packet5NameChange() {
    }
    
    public Packet5NameChange(String name, boolean accepted) {
        this.name = name;
        this.accepted = accepted;
    }
    
    @Override
    public void readData(DataInputStream stream) throws IOException {
        name = readString(stream, 32);
        accepted = stream.readBoolean();
    }

    @Override
    public void writeData(DataOutputStream stream) throws IOException {
        writeString(stream, name);
        stream.writeBoolean(accepted);
    }

    @Override
    public void process(NetHandler handler) {
        handler.handleNameChange(this);
    }

    @Override
    public int getSize() {
        return 3 + (name.length() * 2);
    }
}
